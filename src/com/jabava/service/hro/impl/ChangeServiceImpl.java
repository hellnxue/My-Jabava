package com.jabava.service.hro.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.utils.constants.SequenceConstants;
import com.jabava.utils.enums.JabavaEnumUtil;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;
import com.jabava.dao.hro.HroOrderSendMapper;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.dao.hro.HroSendDetailMapper;
import com.jabava.pojo.common.EhrSequence;
import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.hro.HroSendDetail;
import com.jabava.service.announcement.model.OrderModel;
import com.jabava.service.common.ISequenceService;
import com.jabava.service.hro.ChangeService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.JabavaStringUtils;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;

/**
 * 增减变表操作
 *
 * @version $Id: ChangeServiceImpl.java, v 0.1 2016年1月11日 下午4:42:53
 * 
 *          <pre>
 * @author steven.chen
 * @date 2016年1月11日 下午4:42:53
 * </pre>
 */
@Service
public class ChangeServiceImpl implements ChangeService {
	private static Logger log = Logger.getLogger(ChangeServiceImpl.class);

	@Autowired
	private HroOrderSendMapper hroOrderSendMapper;
	@Autowired
	private HroSendDetailMapper hroSendDetailMapper;
	@Autowired
	private HroPactInfoMapper hroPactInfoMapper;
	@Autowired
	private ISequenceService sequenceService;
	
	// jabava 保存发送到HRO详细信息的List
	private static List<HroSendDetail> detailList = new ArrayList<HroSendDetail>();
	
	private static HROFetchService requestService;
	public ChangeServiceImpl(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}
	@Override
	public HroOrderSend selectOrderSendById(Long hroOrderSendId)  {
		return hroOrderSendMapper.selectByPrimaryKey(hroOrderSendId);
	}

	@Override
	public List<HroOrderSend> findChangePage(Page<HroOrderSend> page,
			HroOrderSend hroOrderSend) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(hroOrderSend.getHroOrderSendId()!=null){
			map.put("hroOrderSendId", hroOrderSend.getHroOrderSendId());
		}
		map.put("companyId", hroOrderSend.getCompanyId());
		map.put("isDeleted", hroOrderSend.getIsDeleted());
		map.put("search", hroOrderSend.getSearchValue());
		map.put("orderBy", hroOrderSend.getOrderBy());
		map.put("page", page);
		return hroOrderSendMapper.findChangePage(map);
	}

	@Override
	public List<Map<String, Object>> loadDetail(HroOrderSend hos, String type) throws Exception{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("batchCode", hos.getBatchCode());
		Map<String, Object> hroResult = requestService.invoke("orderQuery", map);	
		// 操作成功
		if (!"0".equals(hroResult.get("resultCode").toString())) {
			log.error("调用订单回调接口失败：" + hroResult.get("resultMessage"));
			return result;
		}
		
		//处理返回数据
		List<Map<String, Object>> dataList = (List<Map<String, Object>>)hroResult.get("resultData");
		if(dataList == null || dataList.isEmpty()){
			return result;
		}
		
		Date d = null;
		for(Map<String, Object> data : dataList){
			if(type.equals(data.get("OPERATE_TYPE"))){
				String sendDate = (String)data.get("SEND_DATE");
				if(!StringUtils.isEmpty(sendDate)){
					d = JabavaDateUtils.parseDate(sendDate, "yyyyMMddHHmmss");
					data.put("SEND_DATE", JabavaDateUtils.formatDate(d, "yyyy-MM-dd HH:mm:ss"));
				}
				String dimissionDate = (String)data.get("DIMISSION_DATE");
				if(!StringUtils.isEmpty(dimissionDate)){
					d = JabavaDateUtils.parseDate(dimissionDate, "yyyyMMddHHmmss");
					data.put("DIMISSION_DATE", JabavaDateUtils.formatDate(d, "yyyy-MM-dd"));
				}
				result.add(data);
			}
		}
		
		return result;
	}
	
	/**
	 * 新增增减变
	 * 
	 * @author steven.chen
	 * @date 2016年1月15日 上午11:28:49
	 * @param hroOrderSend
	 * @return
	 * @throws Exception
	 * @see com.jabava.service.hro.ChangeService#addChange(com.jabava.pojo.hro.HroOrderSend)
	 */
	@Override
	public String addChange(HroOrderSend hroOrderSend,Long companyId) throws Exception {
		List<HroPactInfo> pactList = hroPactInfoMapper.queryPactInfoListByCompany(companyId);
		if(pactList == null || pactList.isEmpty()){
			//return MessageUtil.ERROR;
			return "未找到有效的协议号";
		}

		//设置批次号
		EhrSequence seq = new EhrSequence();
		seq.setPrefix(SequenceConstants.ORDERZJB_BATCHCODE_PREFIX + JabavaDateUtils.formatDate("yyyyMMdd"));
		Long next = sequenceService.next(seq);
		hroOrderSend.setBatchCode(seq.getPrefix() + JabavaStringUtils.fillWithChar(next.toString(), SequenceConstants.ORDERZJB_BATCHCODE_SEQ_LENGTH));
		
		// 读取excel(如果有多条，取最后一条)
		String result = readExcel(hroOrderSend, pactList.get(pactList.size() - 1).getPactCode());
		if (!"success".equals(result)) {
			return result;
		} else {//增减变成功后
			if(detailList == null || detailList.isEmpty()){
				return "没有增减变数据";
			}
			if (hroOrderSendMapper.insertSelective(hroOrderSend) == 1) {
				hroSendDetailMapper.insertList(detailList);
				detailList.clear();
				return MessageUtil.SUCCESS;
			} else {
				return "保存增减变失败";
			}
		}
	}

	// 读取excel 文件，发送给HRO
	@SuppressWarnings("resource")
	public static String readExcel(HroOrderSend hroOrderSend, String pactCode) throws Exception {
		// 创建新的Excel 工作簿
		XSSFWorkbook workbook = null;
		try {
			//String path = hroOrderSend.getAttachmentUrl() + File.separator + hroOrderSend.getAttachment();
			InputStream instream = new FileInputStream(hroOrderSend.getAttachmentUrl());
			workbook = new XSSFWorkbook(instream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int type = 1;// 增员
		int type2 = 2;// 变更
		int type3 = 3;// 减员
		// 发送增减变到HRO
		String result = sendChange(workbook.getSheetAt(type - 1), type, hroOrderSend, pactCode);
		if (!"success".equals(result)) {
			return result;
		}
		result = sendChange(workbook.getSheetAt(type2 - 1), type2, hroOrderSend, pactCode);
		if (!"success".equals(result)) {
			return result;
		}
		result = sendChange(workbook.getSheetAt(type3 - 1), type3, hroOrderSend, pactCode);
		if (!"success".equals(result)) {
			return result;
		}
		
		return "success";
	}

	private static String sendChange(XSSFSheet sheet, int type,
			HroOrderSend hroOrderSend, String pactCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now =new Date();//创建时间
		// 获取Sheet表中所包含的总行数
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		// 发送到HRO的List
		List<OrderModel> changeList = new ArrayList<OrderModel>();
		try{
			for (int i = 1; i <= rows; i++) {
				XSSFRow row = sheet.getRow(i);
				if(row == null){
					break;
				}
				
				Cell firstCell = sheet.getRow(i).getCell(0);
				if(firstCell == null || "".equals(firstCell.toString())){
					continue;
				}
				
				HroSendDetail hroSendDetail = new HroSendDetail();
				OrderModel orderModel = new OrderModel();
				for (int j = 0; j < columns; j++) {
					Cell cell = sheet.getRow(i).getCell(j);
					if (cell == null) {
						continue;
					}
					String checkResult = checkChange(cell, j, type,i);
					if (!"success".equals(checkResult)) {
						return checkResult;
					}
					
					if (j == 0 ) {	// 第一个单元格 员工姓名
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setEmployeeName(cell.toString());
						}else{
							orderModel.setEmployeeName(null);
						}
					}
					if (j == 1) {	//手机
						if(StringUtils.isNotBlank(cell.toString())){
							//orderModel.setMobile(cell.toString());
							orderModel.setPhone(cell.toString());
						}else{
							//orderModel.setMobile(null);
							orderModel.setPhone(null);
						}
					
					}
					if (j == 2) {	//证件类型
						if(StringUtils.isBlank(cell.toString())){
							orderModel.setCardType(null);
						}else{
							orderModel.setCardType(Integer.valueOf(JabavaEnumUtil.getCardTypeByName(cell.toString().trim())));
						}
						
//						if ("身份证".equals(cell.toString().trim())) {
//							orderModel.setCardType(1);
//						} else if ("军人证".equals(cell.toString().trim())) {
//							orderModel.setCardType(2);
//						} else if ("港澳身份证".equals(cell.toString().trim())) {
//							orderModel.setCardType(3);
//						} else if ("台胞证".equals(cell.toString().trim())) {
//							orderModel.setCardType(4);
//						} else if ("护照".equals(cell.toString().trim())) {
//							orderModel.setCardType(5);
//						} else if ("其他".equals(cell.toString().trim())) {
//							orderModel.setCardType(9);
//						}
					}
					if (j == 3 ) {
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setCardId(cell.toString());
						}else{
							orderModel.setCardId(null);
						}							
					}
					if (j == 4) {//入职日期
						if(StringUtils.isNotBlank(cell.toString())){
							Date d = JabavaDateUtils.parseDate(cell.toString(),"yyyy-MM-dd");
							orderModel.setHireDate(JabavaDateUtils.formatDate(d, "yyyyMMdd"));
						}else{
							orderModel.setHireDate(null);
						}
						
					}
					if (j == 5 ) {//离职日期
						if(StringUtils.isNotBlank(cell.toString())){
							Date d = JabavaDateUtils.parseDate(cell.toString(),"yyyy-MM-dd");
							orderModel.setDimissionDate(JabavaDateUtils.formatDate(d, "yyyyMMdd"));
						}else{
							orderModel.setDimissionDate(null);
						}
					}
					if (j == 6 ) {//服务月
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setPaymentMonth(cell.toString());
						}else{
							orderModel.setPaymentMonth(null);
						}
					}
					if (j == 7) {//账单月
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setBillMonth(cell.toString());
						}else{
							orderModel.setBillMonth(null);
						}
					}
					if (j == 8 ) {//社保发生月
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setSbMonth(cell.toString());
						}else{
							orderModel.setSbMonth(null);
						}							
					}
					if (j == 9 ) {//社保基数
						if(StringUtils.isNotBlank(cell.toString())){
							BigDecimal sbBase = new BigDecimal(cell.toString());
							orderModel.setSbBase(sbBase);
						}else{
							orderModel.setSbBase(null);
						}
					}
					if (j == 10 ) {//公积金基数
						if(StringUtils.isNotBlank(cell.toString())){
							orderModel.setGjjBase(new BigDecimal(cell.toString()));
						}else{
							orderModel.setGjjBase(null);
						}							
					}
					if (j == 11 ) {//公积金个人比例
						if(StringUtils.isNotBlank(cell.toString())){
							//orderModel.setGjjIndividualRatio(new BigDecimal(cell.toString()));
							orderModel.setGjjIndRatio(new BigDecimal(cell.toString()));
						}else{
							//orderModel.setGjjIndividualRatio(null);
							orderModel.setGjjIndRatio(null);
						}
					}
					if (j == 12 ) {		//公积金企业比例					
						if(StringUtils.isNotBlank(cell.toString())){								
							//orderModel.setGjjCompanyRatio(new BigDecimal(cell.toString()));
							orderModel.setGjjComRatio(new BigDecimal(cell.toString()));
						}else{
							//orderModel.setGjjCompanyRatio(null);
							orderModel.setGjjComRatio(null);
						}
					}
					if (j == 13) {//公积金账号
						if(StringUtils.isNotBlank(cell.toString())){								
							orderModel.setGjjAccount(cell.toString());					
						}else{
							orderModel.setGjjAccount(null);
						}
					}
					if (j == 14 && cell!=null) {//参保类型（社保组）
						if(StringUtils.isNotBlank(cell.toString())){								
							orderModel.setSbGroupName(cell.toString());				
						}else{
							orderModel.setSbGroupName(null);
						}
					}
					if (j == 15) {//离职原因
						if(StringUtils.isNotBlank(cell.toString())){								
							orderModel.setDimissionType(cell.toString());					
						}else{
							orderModel.setDimissionType(null);
						}
					}
					
					orderModel.setOperateType(type);// 操作类型
					orderModel.setHandleType(2);// 单立户
					orderModel.setSendRemark(hroOrderSend.getRemark());
					// orderModel.setWorkAddressName(null);
				}
	
				BeanUtils.copyProperties(orderModel, hroSendDetail,new String[]{"hireDate","dimissionDate"});
				hroSendDetail.setHroOrderSendId(hroOrderSend.getHroOrderSendId());
				hroSendDetail.setCompanyId(hroOrderSend.getCompanyId());
				hroSendDetail.setIsDeleted(IsDeleted.UN_DELETED.getValue());
				hroSendDetail.setCreateUserId(hroOrderSend.getCreateUserId());
				hroSendDetail.setCreateDate(now);
				hroSendDetail.setUpdateDate(now);
				hroSendDetail.setUpdateUserId(hroOrderSend.getCreateUserId());
				if(!StringUtils.isEmpty(orderModel.getHireDate())){
					hroSendDetail.setHireDate(JabavaDateUtils.parseDate(orderModel.getHireDate(),"yyyyMMdd"));
				}
				if(!StringUtils.isEmpty(orderModel.getDimissionDate())){
					hroSendDetail.setDimissionDate(JabavaDateUtils.parseDate(orderModel.getDimissionDate(),"yyyyMMdd"));
				}
				changeList.add(orderModel);
	
				detailList.add(hroSendDetail);
			}
		}catch (Exception p){
			p.printStackTrace();
			return " 解析excel 出错";
		}
		
		if(type == 1){
			hroOrderSend.setNumAdd(changeList.isEmpty() ? 0 : changeList.size());
		}else if(type == 2){
			hroOrderSend.setNumChange(changeList.isEmpty() ? 0 : changeList.size());
		}else{
			hroOrderSend.setNumSubtract(changeList.isEmpty() ? 0 : changeList.size());
		}
		if(changeList.isEmpty()){
			log.warn("增减变" + type + "没有数据");
			return "success";
		}else{
			//map.put("protocolCode","ZY-EHR-20160204-0001");
			map.put("protocolCode",pactCode);
			//map.put("batchCode", dateFormat.format(new Date()));
			map.put("batchCode", hroOrderSend.getBatchCode());
			map.put("orderList", changeList);
			//Map<String, Object> result = requestService.invoke("handleOrder", map);	
			Map<String, Object> result = requestService.invoke("orderTrans", map);
			// 操作成功
			if ("0".equals(result.get("resultCode").toString())) {
				return "success";
			}else {
				// 操作失败返回操作信息
				if(result.get("resultMessage")!=null && StringUtils.isNotBlank(result.get("resultMessage").toString())){
					return result.get("resultMessage").toString() ;
				}
			}
			log.info("增减变返回消息   "+result.toString());
		}

		return "success";
	}

	
	private static String checkChange(Cell cell, int j, int type,int row) {
		String opType = "";
		if(type == 1){
			opType ="增员";
		}else if(type == 2){
			opType ="变更";
		}else if(type == 3){
			opType ="减员";
		}	
		
		int i = 0;
		if (j == i && type == 1 ) {// 员工姓名
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 员工姓名未通过校验";
			}
		}
		i++;
		if (j == i ) {// 手机号码 可以为空
			if(cell != null && StringUtils.isNotBlank(cell.toString())){
				if(!JabavaStringUtils.isNum(cell.toString())){
					//log.info("i: " + i + ", j: " + j + "," + cell.toString() + "=====");
					return opType +"第"+row+"行 手机号码只能是数字";
				}
			}
		}
		i++;
		if (j == i && type == 1 ) {// 证件类型
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 证件类型未通过校验";
			}
		}
		i++;
		if (j == i) {// 证件号码
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 证件号码未通过校验";
			}		
		}
		i++;
		if (j == i && type == 1 ) {// 入职时间
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 入职时间未通过校验";
			}else if(!checkDate(cell.toString())){
				return opType +"第"+row+"行 入职时间未通过校验";
			}
		}
		i++;
		if (j == i && type == 3 ) {// 离职日期
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 离职日期未通过校验";
			}else if(!checkDate(cell.toString())){
				return opType +"第"+row+"行 离职日期未通过校验";
			}
		}
		i++;
		if (j == i ) {// 服务月 不可为空
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 服务月未通过校验";
			}else if(!checkMonth(cell.toString())){
				return opType +"第"+row+"行 服务月未通过校验";
			}			
		}
		i++;
		if (j == i) {// 账单月 不可为空
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 账单月未通过校验";
			}else if(!checkMonth(cell.toString())){
				return opType +"第"+row+"行 账单月未通过校验";
			}
		}
		i++;
		if (j == i ) {// 社保发生月  可以为空   校验长度，是否是数字，长度，月份是否正确
			if(cell != null && StringUtils.isNotBlank(cell.toString())){
				if(!checkMonth(cell.toString())){
					return opType +"第"+row+"行 社保发生月未通过校验";
				}
			}
		}
		i++;
		if (j == i && (type == 1 || type == 2) ) {// 社保基数
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 社保基数未通过校验";
			}
		}
		i++;
		//if (j == i && (type == 1 || type == 2)) {// 公积金基数(可以只交社保)
		//	if(cell == null || StringUtils.isBlank(cell.toString())){
		//		return opType +"第"+row+"行 公积金基数未通过校验";
		//	}
		//}
		i++;
		//if (j == i && (type == 1 || type == 2) ) {// 公积金个人比例
		if(j == i){
			if(cell != null && StringUtils.isNotBlank(cell.toString())){
				if(!JabavaStringUtils.isBigDecimal(cell.toString())){
					return opType +"第"+row+"行 公积金个人比例未通过校验";
				}
			}
		}
		//}
		i++;
		//if (j == i && (type == 1 || type == 2) ) {// 公积金企业比例
		if(j == i){
			if(cell != null && StringUtils.isNotBlank(cell.toString())){
				if(!JabavaStringUtils.isBigDecimal(cell.toString())){
					return opType +"第"+row+"行  公积金企业比例未通过校验";
				}
			}
		}
		//}
		i++;
		// 公积金账号 无校验
		i++;
		if (j == i && (type == 1 || type == 2) ) {// 参保类型（社保组）
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行  参保类型（社保组）未通过校验";
			}
		}
		i++;
		return "success";
	}
	/**
	 * 校验账单月 服务月
	 * 校验长度，是否是数字，月份是否正确
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月8日 下午4:44:40 
	 * </pre>
	 *
	 * @return
	 */
	private static boolean checkMonth(String str){
		try {
			JabavaDateUtils.parseStrictDate(str, "yyyyMM");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 校验入职日期 离职日期
	 * @param str
	 * @return
	 */
	private static boolean checkDate(String str){
		try {
			JabavaDateUtils.parseStrictDate(str, "yyyy-MM-dd");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
