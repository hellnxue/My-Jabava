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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.core.EnumConstents.IsDeleted;
import com.jabava.dao.hro.HroOrderSendMapper;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.dao.hro.HroSendDetailMapper;
import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.hro.HroSendDetail;
import com.jabava.service.announcement.model.OrderModel;
import com.jabava.service.hro.ChangeService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.JabavaPropertyCofigurer;
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
		map.put("orderBy", hroOrderSend.getOrderBy());
		map.put("page", page);
		return hroOrderSendMapper.findChangePage(map);
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
			return MessageUtil.ERROR;
		}
		
		// 读取excel
		String result = null;
		result = readExcel(hroOrderSend, pactList.get(0).getPactCode());
		if (!"success".equals(result)) {			
			return result;
		} else {//增减变成功后
			if (hroOrderSendMapper.insertSelective(hroOrderSend) == 1) {
				hroSendDetailMapper.insertList(detailList);
				detailList.clear();
				return MessageUtil.SUCCESS;
			} else {
				return MessageUtil.ERROR;
			}
			
		}
		
	}

	// 读取excel 文件，发送给HRO
	@SuppressWarnings("resource")
	public static String readExcel(HroOrderSend hroOrderSend, String pactCode) throws Exception {
		String path = hroOrderSend.getAttachmentUrl() + File.separator
				+ hroOrderSend.getAttachment();
		// 创建新的Excel 工作簿
		XSSFWorkbook workbook = null;
		try {
			InputStream instream = new FileInputStream(path);
			workbook = new XSSFWorkbook(instream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = null;
		int type = 1;// 增员
		int type2 = 2;// 变更
		int type3 = 3;// 减员
		// 发送增减变到HRO
		result = sendChange(workbook.getSheetAt(type - 1), type, hroOrderSend, pactCode);
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date now =new Date();//创建时间
		// 获取Sheet表中所包含的总行数
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		// 发送到HRO的List
		List<OrderModel> changeList = new ArrayList<OrderModel>();
		try{
		for (int i = 1; i <= rows; i++) {
			Cell firstCell = sheet.getRow(i).getCell(0);
			if(firstCell == null || "".equals(firstCell.toString())){
				continue;
			}
			
			HroSendDetail hroSendDetail = new HroSendDetail();
			OrderModel orderModel = new OrderModel();
			for (int j = 0; j < columns; j++)

			{
				Cell cell = sheet.getRow(i).getCell(j);
				String checkResult = checkChange(cell, j, type,i);
				if ("success".equals(checkResult)) {
					if (cell != null) {
						if (j == 0 ) {// 第一个单元格 员工姓名
							if(StringUtils.isNotBlank(cell.toString())){
								orderModel.setEmployeeName(cell.toString());
							}else{
								orderModel.setEmployeeName(null);
							}
							
						}
						if (j == 1) {
							if(StringUtils.isNotBlank(cell.toString())){
								orderModel.setMobile(cell.toString());
							}else{
								orderModel.setMobile(null);
							}
						
						}
						if (j == 2) {
							if(StringUtils.isBlank(cell.toString())){
								orderModel.setCardType(null);
							}					
							
							if ("身份证".equals(cell.toString().trim())) {
								orderModel.setCardType(1);
							} else if ("军人证".equals(cell.toString().trim())) {
								orderModel.setCardType(2);
							} else if ("港澳身份证".equals(cell.toString().trim())) {
								orderModel.setCardType(3);
							} else if ("台胞证".equals(cell.toString().trim())) {
								orderModel.setCardType(4);
							} else if ("护照".equals(cell.toString().trim())) {
								orderModel.setCardType(5);
							} else if ("其他".equals(cell.toString().trim())) {
								orderModel.setCardType(9);
							}

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
								orderModel.setHireDate(cell.toString());
							}else{
								orderModel.setHireDate(null);
							}
							
						}
						if (j == 5 ) {//离职日期
							if(StringUtils.isNotBlank(cell.toString())){
								orderModel.setDimissionDate(cell.toString());
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
								orderModel.setGjjIndividualRatio(new BigDecimal(cell.toString()));
							}else{
								orderModel.setGjjIndividualRatio(null);
							}
						}
						if (j == 12 ) {		//公积金企业比例					
							
							if(StringUtils.isNotBlank(cell.toString())){								
								orderModel.setGjjCompanyRatio(new BigDecimal(cell.toString()));							
								
							}else{
								orderModel.setGjjCompanyRatio(null);
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
						if (orderModel != null) {
							orderModel.setOperateType(type);// 操作类型
						}
						orderModel.setHandleType(2);// 单立户
						// orderModel.setDimissionType("1");
					}
				} else {
					return checkResult;
				}

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
				hroSendDetail.setHireDate(dateFormat.parse(orderModel.getHireDate()));
			}
			if(!StringUtils.isEmpty(orderModel.getDimissionDate())){
				hroSendDetail.setDimissionDate(dateFormat.parse(orderModel.getDimissionDate()));
			}
			changeList.add(orderModel);

			detailList.add(hroSendDetail);

		}
		}catch (Exception p){
			p.printStackTrace();
			System.out.println("     解析excel 出错");
			return "error";
		}
		
		if(changeList.isEmpty()){
			log.warn("增减变" + type + "没有数据");
			return "success";
		}else{
			//map.put("protocolCode","ZY-EHR-20160204-0001");
			map.put("protocolCode",pactCode);
			map.put("batchCode", dateFormat.format(new Date()));
			map.put("orderList", changeList);
			map.put("dataSource", "EHR");
			/*Map<String, Object> result = HROFetchService.callHRO(null, map,
					"handleOrder");*/
			Map<String, Object> result = requestService.invoke("handleOrder", map);	
			// 操作成功
			if ("0".equals(result.get("resultCode").toString())) {
				return "success";
			}else {
				// 操作失败返回操作信息
				if(result.get("resultMessage")!=null && StringUtils.isNotBlank(result.get("resultMessage").toString())){
					return result.get("resultMessage").toString() ;
				}
				
			}
		}

		return "error";
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
			if(cell == null || StringUtils.isNotBlank(cell.toString())){
				if(!JabavaStringUtils.isNum(cell.toString())){
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
			}
			
		}
		i++;
		if (j == i && type == 3 ) {// 离职日期
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 离职日期未通过校验";
			}
			
		}
		i++;
		if (j == i ) {// 服务月 不可为空
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 服务月未通过校验";
			}else{
				String result =checkMonth(cell.toString());
				if(!"success".equals(result)){
					return opType +"第"+row+"行"+result;
				}
			}			
		}
		i++;
		if (j == i) {// 账单月 不可为空
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 账单月未通过校验";
			}else{
				String result =checkMonth(cell.toString());
				if(!"success".equals(result)){
					return opType +"第"+row+"行"+result;
				}
			}
			
		}
		i++;
		if (j == i ) {// 社保发生月  可以为空   校验长度，是否是数字，长度，月份是否正确
			if(cell == null || StringUtils.isNotBlank(cell.toString())){
				String res =checkMonth(cell.toString());
				if(!"success".equals(res)){
					return opType +"第"+row+"行"+res;
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
		if (j == i && (type == 1 || type == 2)) {// 公积金基数
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 公积金基数未通过校验";
			}
		
		}
		i++;
		/*if (j == i && (type == 1 || type == 2) ) {// 公积金个人比例
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行 公积金个人比例未通过校验";
			}
			
		}*/
		i++;
		/*if (j == i && (type == 1 || type == 2) ) {// 公积金企业比例
			if(cell == null || StringUtils.isBlank(cell.toString())){
				return opType +"第"+row+"行  公积金企业比例未通过校验";
			}
			
		}*/
		// 公积金账号 无校验 所以要++ 两次
		i++;
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
	private static String checkMonth(String str){
					
		String month =str.substring(str.length()-2, str.length());
		if(!JabavaStringUtils.isNum(str)){
			return "只能是数字";
		}
		if(Integer.parseInt(month)>12){
			return "月份不能大于12";
		}
		if(str.length()>6){
			return "长度不能大于6";
		}
		return "success";
	}
	public static void main(String args[]) throws Exception {
		
	/*	String str = "20160101";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		System.out.println((sdf2.parse(str)));

		System.out.println(sdf2.format(sdf2.parse(str)));*/	
	
		System.out.println(checkMonth("201412"));

	}

}
