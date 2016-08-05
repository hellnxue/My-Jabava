package com.jabava.service.hro.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.hro.EhrEfArapMapper;
import com.jabava.dao.hro.HroEfArapDetailEmpMapper;
import com.jabava.dao.hro.HroEfArapDetailEmpSbMapper;
import com.jabava.dao.hro.HroEfArapMapper;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.pojo.hro.EfArap;
import com.jabava.pojo.hro.EhrEfArap;
import com.jabava.pojo.hro.HroEfArap;
import com.jabava.service.hro.IEhrEfArapService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.constants.ApiConstants;
import com.jabava.utils.enums.BillEnum;
import com.jabava.utils.enums.BillEnum.BillStatus;

/**
 * 账单Service实现
 */
@Service
public class EhrEfArapServiceImpl implements IEhrEfArapService {
	private Logger log = Logger.getLogger(EhrEfArapServiceImpl.class);
	
	@Autowired
	private HroPactInfoMapper hroPactInfoMapper;
	@Autowired
	private HroEfArapMapper hroEfArapMapper;
	@Autowired
	private EhrEfArapMapper ehrEfArapMapper;
	@Autowired
	private HroEfArapDetailEmpSbMapper hroEfArapDetailEmpSbMapper;
	@Autowired
	private HroEfArapDetailEmpMapper hroEfArapDetailEmpMapper;
	
	private HROFetchService hroFetchService;
	
	public EhrEfArapServiceImpl(){
		this.hroFetchService = new HROFetchService(ApiConstants.AUTHURI_HRO, ApiConstants.URI_HRO_REST);
	}

	@Override
	public List<Map<String,Object>> syncAndSearchBill(Long companyId, String year) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		//params.put("companyId", CompanyId);
		params.put("year", year);
		//params.put("search", search);
		//params.put("orderBy", orderBy);
		params.put("protocolList", hroPactInfoMapper.queryPactInfoListByCompany(companyId));
		//List<EfArap> list = efArapMapper.selectBill(params);
		List<Map<String,Object>> list = hroEfArapMapper.selectBill(params);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> bill : list){
			Long billId = Long.valueOf(bill.get("BILL_ID").toString());
			String billCode = bill.get("BILL_CODE").toString();
			//查询Jabava账单信息
			//EhrEfArap ehrBill = ehrEfArapMapper.selectByPrimaryKey(billId);
			EhrEfArap ehrBill = ehrEfArapMapper.selectByBillCode(billCode);
			if(ehrBill == null){
				//新增Jabava账单信息
				ehrBill = this.insertEhrBill(bill, companyId);
			}else if(!ehrBill.getBillId().equals(billId)){
				//重新试算的，先删除后新增
				ehrEfArapMapper.deleteByPrimaryKey(ehrBill.getBillId());
				
				ehrBill = this.insertEhrBill(bill, companyId);
			}else{
				//更新Jabava账单信息
				if(bill.get("HR_STATUS") != null){
					int hroStatus = Integer.parseInt(bill.get("HR_STATUS").toString());
					if(hroStatus == BillEnum.BillStatus.ToBeConfirmed.getValue()){
						if(ehrBill.getHrStatus().intValue() == BillEnum.BillStatus.ToBeConfirmed.getValue()){
							//Jabava驳回状态，HRO可以重新生成
							ehrBill.setHrStatus(BillEnum.BillStatus.ToBeConfirmed.getValue());
							ehrBill.setHrDate((Date)bill.get("HR_DATE"));
							ehrEfArapMapper.updateByPrimaryKey(ehrBill);
						}
					}else if(hroStatus == BillEnum.BillStatus.Canceled.getValue()){
						if(ehrBill.getHrStatus().intValue() == BillEnum.BillStatus.ToBeConfirmed.getValue() ||
								ehrBill.getHrStatus().intValue() == BillEnum.BillStatus.Confirmed.getValue() ||
								ehrBill.getHrStatus().intValue() == BillEnum.BillStatus.Rejected.getValue()){
							//Jabava任何一种状态下(已作废除外)，HRO都可以作废
							ehrBill.setHrStatus(BillEnum.BillStatus.Canceled.getValue());
							ehrBill.setHrDate((Date)bill.get("HR_DATE"));
							ehrEfArapMapper.updateByPrimaryKey(ehrBill);
						}
					}

					//查询并更新付款状态
					this.updatePayoffStatus(ehrBill, bill);
				}
			}
			
			//计算应付总额
			this.calAmount(bill);
			
			//修改显示信息
			bill.put("HR_STATUS", ehrBill.getHrStatus());			//账单状态
			bill.put("HR_DATE", ehrBill.getHrDate());				//操作时间
			bill.put("STATUS_PAYOFF", ehrBill.getStatusPayoff());	//付款状态
			bill.put("PAYOFF_DATE", ehrBill.getPayoffDate());		//付款时间
			if(ehrBill.getHrStatus() != null && ehrBill.getHrStatus().intValue() == BillStatus.Rejected.getValue()){
				bill.put("REMARK", ehrBill.getHrRejectRemark());	//驳回状态备注显示驳回原因
			}
//			if("2".equals(bill.get("STATUS_VERIFY"))){
//				//不显示部分核销(转为未核销)
//				bill.put("STATUS_VERIFY", "1");
//				bill.put("CONFIRM_DATE", null);
//			}
			
			//不显示已作废账单
			if(ehrBill.getHrStatus().intValue() != BillStatus.Canceled.getValue()){
				result.add(bill);
			}
		}
		
		return result;
	}
	
	private EhrEfArap insertEhrBill(Map<String,Object> bill, Long companyId) throws Exception{
		EhrEfArap ehrBill = new EhrEfArap();
		ehrBill.setCompanyId(companyId);
		ehrBill.setBillId(Long.valueOf(bill.get("BILL_ID").toString()));
		ehrBill.setBillCode(bill.get("BILL_CODE").toString());
		ehrBill.setHrStatus((Integer)bill.get("HR_STATUS"));
		ehrBill.setHrDate((Date)bill.get("HR_DATE"));
		ehrBill.setHrRejectRemark((String)bill.get("HR_REJECT_REMARK"));
		ehrEfArapMapper.insertSelective(ehrBill);
		return ehrBill;
	}
	
	private void updatePayoffStatus(EhrEfArap ehrBill, Map<String,Object> hroBill) throws Exception{
		if(ehrBill.getStatusPayoff() == 1 || StringUtils.isEmpty((String)hroBill.get("PAY_LINK"))){
			return ;
		}
		
		//调用接口查询支付状态
		Map<String, Object> hroResult = this.getPayoffStatus(ehrBill.getBillCode(), (Integer)hroBill.get("BILL_TYPE"));
		if (!"0".equals(hroResult.get("resultCode").toString())) {
			log.error("queryBizPayStatus失败：" + hroResult.get("resultMessage"));
			return ;
		}
		
		//处理返回数据
		Map<String, Object> data = (Map<String, Object>)hroResult.get("resultData");
		if(data == null || data.isEmpty()){
			log.warn("HRO返回数据为空:");
			return ;
		}
		
		if("PAID".equalsIgnoreCase((String)data.get("status"))){
			ehrBill.setStatusPayoff(1);
			ehrBill.setPayoffDate(JabavaDateUtils.parseDate(data.get("paidDate").toString(), "yyyyMMddHHmmss"));
			ehrEfArapMapper.updateByPrimaryKey(ehrBill);
		}
	}
	
	private void calAmount(Map<String,Object> bill){
		//应收应付总额
		BigDecimal amount = new BigDecimal(0);
		bill.put("AMOUNT", amount
				.add(bill.get("TOTAL_SB_AMOUNT_E") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_SB_AMOUNT_E"))
				.add(bill.get("TOTAL_SB_AMOUNT_P") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_SB_AMOUNT_P"))
				.add(bill.get("TOTAL_NSB_AMOUNT") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_NSB_AMOUNT"))
				.add(bill.get("TOTAL_EXTRA_AMOUNT") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_EXTRA_AMOUNT"))
				.subtract(bill.get("TOTAL_WITHOUTSUM_AMOUNT") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_WITHOUTSUM_AMOUNT"))
				.subtract(bill.get("AMOUNT_ADJ") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("AMOUNT_ADJ")));
		
		//账单总额
		BigDecimal amountTotal = new BigDecimal(0);
		bill.put("AMOUNT_TOTAL", amountTotal
				.add(bill.get("TOTAL_SB_AMOUNT_E") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_SB_AMOUNT_E"))
				.add(bill.get("TOTAL_SB_AMOUNT_P") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_SB_AMOUNT_P"))
				.add(bill.get("TOTAL_NSB_AMOUNT") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_NSB_AMOUNT"))
				.add(bill.get("TOTAL_EXTRA_AMOUNT") == null ? BigDecimal.ZERO : (BigDecimal)bill.get("TOTAL_EXTRA_AMOUNT")));
	}
	
	/**
	 * 确认或驳回
	 * @return
	 */
	@Override
	public Map<String,Object> operate(EhrEfArap ehrBill, Integer type) throws Exception{
		boolean checkStatus = false;
		HroEfArap hroBill = hroEfArapMapper.selectByPrimaryKey(ehrBill.getBillId());
		if(type.intValue() == BillEnum.BillStatus.Confirmed.getValue()){
			if(hroBill.getHrStatus() != null && 
					hroBill.getHrStatus().intValue() == BillEnum.BillStatus.ToBeConfirmed.getValue()){
				checkStatus = true;	//待确认状态下允许确认操作
			}
		}else if(type.intValue() == BillEnum.BillStatus.Rejected.getValue()){
			if(hroBill.getHrStatus() != null &&
					hroBill.getHrStatus().intValue() == BillEnum.BillStatus.ToBeConfirmed.getValue()){
				checkStatus = true;	//待确认状态下允许驳回操作
			}
		}
		if(!checkStatus){
			throw new JabavaServiceException("当前状态不允许该操作");
		}
		
		Date now = new Date();
		ehrBill.setHrStatus(type);
		ehrBill.setHrDate(now);
		ehrEfArapMapper.updateByPrimaryKey(ehrBill);
		
		//调用HRO接口
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("billId", ehrBill.getBillId());
		entity.put("operType", type);
		entity.put("hrDate", JabavaDateUtils.formatDate(now, "yyyy-MM-dd HH:mm:ss"));
		if(type.intValue() == BillEnum.BillStatus.Rejected.getValue()){
			entity.put("rejectRemark", ehrBill.getHrRejectRemark());
		}
		Map<String, Object> hroResult = hroFetchService.invoke("updateBillHrStatus", entity);
		if (!"0".equals(hroResult.get("resultCode").toString())) {
			//如果失败则抛出异常回滚
			throw new JabavaServiceException("调用HRO接口失败: " + hroResult.get("resultMessage"));
		}
		
		return MessageUtil.successMessage("操作成功");
	}
	
	@Override
	public EhrEfArap findByPrimaryKey(Long billId) {
		return ehrEfArapMapper.selectByPrimaryKey(billId);
	}
	
	@Override
	public EhrEfArap findByBillCode(String billCode) {
		return ehrEfArapMapper.selectByBillCode(billCode);
	}
	
	@Override
	public List<Map<String,Object>> searchBillDetailHeader(EhrEfArap bill){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("EMP_ID", "雇员编号"));
		result.add(this.getFieldHeader("EMPLOYEE_NAME", "姓名"));
		result.add(this.getFieldHeader("CARD_ID", "证件号"));
		result.add(this.getFieldHeader("CITY_NAME", "工作地"));
		result.add(this.getFieldHeader("BILL_YM", "账单年月"));
		result.add(this.getFieldHeader("SERVER_YM", "服务年月"));
		
		result.add(this.getFieldHeader("AMOUNT_E", "单位小计"));
		result.add(this.getFieldHeader("AMOUNT_P", "个人小计"));
		result.add(this.getFieldHeader("TOTAL", "合计"));
		
		//社保
		//城市/金额/单位基数/单位比例/单位附加额/单位金额/个人基数/个人比例/个人附加额/个人金额
		List<Map<String,Object>> sbHeaderList = hroEfArapDetailEmpSbMapper.selectHeaderListByBillId(bill.getBillId());
		for(Map<String,Object> header : sbHeaderList){
			String prodId = header.get("PROD_ID").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			//cols.add(this.getFieldHeader("cityName_" + prodId, "城市"));
			//cols.add(this.getFieldHeader("amount_" + prodId, "金额"));
			cols.add(this.getFieldHeader("BASE_E_" + prodId, "单位基数"));
			//cols.add(this.getFieldHeader("ratioE_" + prodId, "单位比例"));
			//cols.add(this.getFieldHeader("addAmtE_" + prodId, "单位附加额"));
			cols.add(this.getFieldHeader("BASE_P_" + prodId, "个人基数"));
			cols.add(this.getFieldHeader("AMOUNT_E_" + prodId, "单位金额"));
			//cols.add(this.getFieldHeader("ratioP_" + prodId, "个人比例"));
			//cols.add(this.getFieldHeader("addAmtP_" + prodId, "个人附加额"));
			cols.add(this.getFieldHeader("AMOUNT_P_" + prodId, "个人金额"));
			cols.add(this.getFieldHeader("AMOUNT_" + prodId, "总金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prodId", prodId);
			group.put("prodName",header.get("PROD_NAME"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		//非社保
		//城市/金额/单位基数/单位比例/单位附加额/单位金额/个人基数/个人比例/个人附加额/个人金额
		List<Map<String,Object>> nsbHeaderList = hroEfArapDetailEmpMapper.selectHeaderListByBillId(bill.getBillId());
		for(Map<String,Object> header : nsbHeaderList){
			String prodId = header.get("PROD_ID").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("AMOUNT_" + prodId, "个人金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prodId", prodId);
			group.put("prodName",header.get("PROD_NAME"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}
	
	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}

	@Override
	//public List<Object> searchBillDetail(Long billId,String search,Page<EfArap> page,String orderBy){
	public List<Map<String,Object>> searchBillDetailData(EhrEfArap bill){
		//社保增加：CITY_ID，SB_YM，POLICY_GROUP_ID，AMOUNT_E，AMOUNT_P，BASE_E，BASE_P，RATIO_E，RATIO_P，ADD_AMT_E，ADD_AMT_P
		//非社保增加：QUOT_ID，QUOT_TYPE
		
		//Map<Long,Map<String,Object>> personMap = new HashMap<Long,Map<String,Object>>();
		Map<String,Map<String,Object>> personMap = new LinkedHashMap<String,Map<String,Object>>();
		Map<String,Object> data = null;
		String key = null;
		
		//根据billId获取所有社保，并以serverYm+personId为主键、社保列表为值放入Map中
		List<Map<String,Object>> sbList = hroEfArapDetailEmpSbMapper.selectByBillId(bill.getBillId());
		for(Map<String,Object> sb : sbList){
			if(sb.get("EMP_ID") != null){
				key = sb.get("SERVER_YM").toString() + sb.get("EMP_ID").toString();
				//if(personMap.containsKey(sb.getPersonId())){
				//	data = personMap.get(sb.getPersonId());
				if(personMap.containsKey(key)){
					data = personMap.get(key);
				}else{
					data = new HashMap<String,Object>();
				    data.put("EMP_ID", sb.get("EMP_ID"));
					data.put("EMPLOYEE_NAME", sb.get("EMPLOYEE_NAME"));
					data.put("CARD_ID", sb.get("CARD_ID"));
					data.put("CITY_NAME", sb.get("CITY_NAME"));
					data.put("BILL_YM", sb.get("BILL_YM"));
					data.put("SERVER_YM", sb.get("SERVER_YM"));
					//personMap.put(sb.getPersonId(), data);
					personMap.put(key, data);
				}
				String prodId = sb.get("PROD_ID").toString();
				//data.put("cityName_" + prodId, sb.getCityName());
				data.put("AMOUNT_" + prodId, sb.get("AMOUNT"));
				data.put("BASE_E_" + prodId, sb.get("BASE_E"));
				//data.put("ratioE_" + prodId, sb.getRatioE());
				//data.put("addAmtE_" + prodId, sb.getAddAmtE());
				data.put("AMOUNT_E_" + prodId, sb.get("AMOUNT_E"));
				data.put("BASE_P_" + prodId, sb.get("BASE_P"));
				//data.put("ratioP_" + prodId, sb.getRatioP());
				//data.put("addAmtP_" + prodId, sb.getAddAmtP());
				data.put("AMOUNT_P_" + prodId, sb.get("AMOUNT_P"));
				this.countSb(data, (BigDecimal)sb.get("AMOUNT_E"), (BigDecimal)sb.get("AMOUNT_P"));
			}
		}
		
		//根据billId获取所有非社保，并以personId为主键、非社保列表为值放入Map中
		List<Map<String,Object>> nsbList = hroEfArapDetailEmpMapper.selectByBillId(bill.getBillId());
		for(Map<String,Object> nsb : nsbList){
			if(nsb.get("EMP_ID") != null){
				key = nsb.get("SERVER_YM").toString() + nsb.get("EMP_ID").toString();
				//if(personMap.containsKey(nsb.getPersonId())){
				//	data = personMap.get(nsb.getPersonId());
				if(personMap.containsKey(key)){
					data = personMap.get(key);
				}else{
					data = new HashMap<String,Object>();
					data.put("EMP_ID", nsb.get("EMP_ID"));
					data.put("EMPLOYEE_NAME", nsb.get("EMPLOYEE_NAME"));
					data.put("CARD_ID", nsb.get("CARD_ID"));
					data.put("CITY_NAME", nsb.get("CITY_NAME"));
					data.put("BILL_YM", nsb.get("BILL_YM"));
					data.put("SERVER_YM", nsb.get("SERVER_YM"));
					//personMap.put(nsb.getPersonId(), data);
					personMap.put(key, data);
				}
				String prodId = nsb.get("PROD_ID").toString();
				data.put("AMOUNT_" + prodId, nsb.get("AMOUNT"));
				this.countNsb(data, (BigDecimal)nsb.get("AMOUNT"));
			}
		}
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.addAll(personMap.values());
		
		return result;
	}
	
	private void countSb(Map<String,Object> base,BigDecimal e,BigDecimal p){
		if(base.get("TOTAL") == null){
			base.put("TOTAL", new BigDecimal(0));
		}
		
		if(e != null){
			if(base.get("AMOUNT_E") == null){
				base.put("AMOUNT_E", e);
			}else{
				base.put("AMOUNT_E", e.add((BigDecimal)base.get("AMOUNT_E")));
			}
			base.put("TOTAL", e.add((BigDecimal)base.get("TOTAL")));
		}
		
		if(p != null){
			if(base.get("AMOUNT_P") == null){
				base.put("AMOUNT_P", p);
			}else{
				base.put("AMOUNT_P", p.add((BigDecimal)base.get("AMOUNT_P")));
			}
			base.put("TOTAL", p.add((BigDecimal)base.get("TOTAL")));
		}
	}
	
	private void countNsb(Map<String,Object> base,BigDecimal amount){
		if(amount != null){
			if(base.get("TOTAL") == null){
				base.put("TOTAL", amount);
			}else{
				base.put("TOTAL", amount.add((BigDecimal)base.get("TOTAL")));
			}
			
			//非社保部分既不计入个人小计，也不计入企业小计
//			if(base.get("AMOUNT_P") == null){
//				base.put("AMOUNT_P", amount);
//			}else{
//				base.put("AMOUNT_P", amount.add((BigDecimal)base.get("AMOUNT_P")));
//			}
		}
	}

	@Override
	public Map<String, Object> checkPayoffStatus(EhrEfArap bill) throws Exception {
		//调用接口，检查支付状态
		HroEfArap hroBill = hroEfArapMapper.selectByPrimaryKey(bill.getBillId());
		Map<String,Object> hroResult = this.getPayoffStatus(bill.getBillCode(), hroBill.getBillType());
		if (!"0".equals(hroResult.get("resultCode").toString())) {
			log.error("queryBizPayStatus失败：" + hroResult.get("resultMessage"));
			return MessageUtil.errorMessage("调用HRO接口失败: " + hroResult.get("resultMessage"));
		}
		
		//处理返回数据
		Map<String, Object> data = (Map<String, Object>)hroResult.get("resultData");
		if(data == null || data.isEmpty()){	//数据为空表示未支付
			log.warn("HRO返回数据为空:");
			return MessageUtil.successMessage("检查通过");
		}
		
		if("PAID".equalsIgnoreCase((String)data.get("status"))){
			return MessageUtil.errorMessage("账单已支付");
		}

		Map<String,Object> result = MessageUtil.successMessage("检查通过");
		
		return result;
	}
	
	private Map<String,Object> getPayoffStatus(String billCode, Integer billType) throws Exception{
//      "id": 80,
//      "createUser": "10000",
//      "createDate": "20160201151223",
//      "name": "teset 1 2121",
//      "content": "1adfadw\nadsfwef\nbdfgnb\ngetrvbgs",
//      "code": "003c11be-b480-4614-a267-985d1810ac6f",
//      "bizOrderNo": "A000000000000080",
//      "payerOrgId": 0,
//      "payeeOrgId": 0,
//      "orgId": 0,
//      "status": "UNPAID",
//      "accountPayable": 1
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("code", billCode + "_" + billType);
		entity.put("type", "bill");	//通用账单
		return hroFetchService.invoke("queryBizPayStatus", entity);
	}

	@Override
	public Map<String, Object> hroExport(String billIds) throws Exception {
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("billIds", billIds);
		entity.put("billType", 1);
		entity.put("printType", 1);
		entity.put("groupBy", 0);
		//entity.put("billYms", "201605,201606");	//还需要月份？
		Map<String, Object> hroResult = hroFetchService.invoke("exportEfArapDetail", entity);
		if (!"0".equals(hroResult.get("resultCode").toString())) {
			return MessageUtil.errorMessage("调用HRO接口失败: " + hroResult.get("resultMessage"));
		}
		
		//处理返回数据
		Map<String, Object> data = (Map<String, Object>)hroResult.get("resultData");
		if(data == null || data.isEmpty()){
			return MessageUtil.errorMessage("HRO返回数据为空");
		}
		
		Map<String, Object> result = MessageUtil.successMessage("HRO返回成功");
		result.putAll(data);
		
//        "id": 79,
//        "fileNo": "e549b361-e863-4b34-8963-215d1a1bd58b",
//        "filename": "应收帐单_160629_165717.xls",
//        "fileStorageType": "FileSystem",
//        "relativePath": "e48647a3/2f0c/488d/8676/2ee89b81631d",
//        "createTime": "20160629165717"

		return result;
	}
	
}
