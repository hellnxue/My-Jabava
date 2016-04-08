package com.jabava.task;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.pojo.hro.EfArap;
import com.jabava.pojo.hro.EfArapDetailEmpNsb;
import com.jabava.pojo.hro.EfArapDetailEmpSb;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.IEfArapService;
import com.jabava.service.hro.IHroPactInfoService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;

/**
 * 定时导入账单数据
 * 
 * @author 郑长山
 */
@Service
public class GetBill {

	private static final Logger logger = Logger.getLogger(GetBill.class);

	/**
	 * 账单状态
	 */
	//private static final String STATUS_BILL = "v";
	/**
	 * 账单接口参数编码
	 */
	private static final String CODE_BILL_QUERY = "billquery";

	/**
	 * 账单明细查询接口参数编码
	 */
	private static final String CODE_BILLDETAIL = "billdetail";

	/**
	 * 账单明细查询非社保接口参数编码
	 */
	private static final String CODE_BILLDETAILNSB = "billdetailnsb";
	/**
	 * 接口参数类型
	 */
	private static final String TYPE = "SqlQuery";

	@Autowired
	private IEfArapService efArapService;
	
	@Autowired
	private IHroPactInfoService pactInfoService;
	
	private HROFetchService requestService;
	
	public GetBill(){
		String server =  JabavaPropertyCofigurer.getProperty("SERVER_URL");
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}

//	/**
//	 * 获取调用接口的方法
//	 * 
//	 * @return
//	 */
//	private HROFetchService getHROFetchService() {
//		String server = "http://neice.ezhiyang.com";
//		HROFetchToken fetchToken = new HROFetchToken(
//				server + "/open/authorize", "dayhr",
//				"0f9efee4-303e-11e5-8800-f39b0ce86986");
//		HROFetchService requestService = new HROFetchService(server
//				+ "/open/rest", fetchToken);
//		return requestService;
//	}

	/**
	 * 执行定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void execute() throws Exception {
		logger.info("ImportBillTask Started .................");
		
		//获取协议号
		List<HroPactInfo> pactCodeList = pactInfoService.queryPactInfoList();

//		List<String> pactCodeList = new ArrayList<String>();
//		pactCodeList.add("ZY-DH-20150721-0001");
//
//		List<String> billMonthList = new ArrayList<String>();
//		billMonthList.add("201405");
		
		getBill(pactCodeList);
		
		logger.info("ImportBillTask Finished .................");
	}
	
	public void executeWithPactInfo(HroPactInfo pactInfo) throws Exception{
		List<String> billMonthList = TaskUtil.getMonthList("month.sync.hro.bill");
		if(billMonthList == null || billMonthList.isEmpty()){
			return ;
		}
		for (String billMonth : billMonthList) {
			this.getBill(pactInfo, billMonth);
		}
	}

	/**
	 * 账单查询
	 * 
	 * @param pactCodeList
	 *            协议号-list
	 * @return
	 */
	private void getBill(List<HroPactInfo> pactCodeList) throws Exception{
		if(pactCodeList==null ||pactCodeList.size()<1){
			return ;
		}
		// 循环协议号
		for (HroPactInfo pactInfo : pactCodeList) {
//			//查询是否存在该协议号的账单。无-第一次，不需要指定账单月；有-非第一次，取当前月及上月
//			if (!efArapService.isBillExist(pactInfo.getPactCode())) {
//				this.getBill(pactInfo, null);
//			} else {
				// 循环年月
				List<String> billMonthList = TaskUtil.getMonthList("month.sync.hro.bill");
				if(billMonthList == null || billMonthList.isEmpty()){
					continue ;
				}
				for (String billMonth : billMonthList) {
					this.getBill(pactInfo, billMonth);
				}
//			}
//			//账单可能作废，所以全部同步(也可以在SQL中根据支付情况过滤)
//			this.getBill(pactInfo, null);
		}
	}
	
	private boolean getBill(HroPactInfo pactInfo,String billMonth){
		StringBuffer sb = new StringBuffer();
		sb.append("协议号：").append(pactInfo.getPactCode());
		
		// 拼接接口参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("PROTOCOL_CODE", pactInfo.getPactCode());
		if(!StringUtils.isEmpty(billMonth)){
			parameter.put("BILL_YM", billMonth);
			sb.append("，账单月：").append(billMonth);
		}
//		parameter.put("STATUS_BILL", STATUS_BILL);
		// 获取拼接参数
		Map<String, Object> jsonParam = jsonParam(parameter,CODE_BILL_QUERY);
		
		// 执行接口
		Map<String, Object> source = null;
		try {
			source = requestService.invoke(TYPE, jsonParam);
			logger.debug(com.alibaba.fastjson.JSONObject.toJSONString(source));
			
			// 更新数据库数据
			Map<String,Object> result = saveBill(source, pactInfo);
			sb.append("新增成功数：").append(result.get("inssuccess"));
			sb.append("新增失败数：").append(result.get("inserror"));
			sb.append("修改成功数：").append(result.get("updsuccess"));
			sb.append("修改失败数：").append(result.get("upderror"));
			sb.append("数据转换失败数：").append(result.get("dataerror"));
			logger.info(sb.toString());
		} catch (Exception e) {
			logger.error("获取账单出错！" + sb.toString() + "\n" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 根据账单id更新数据库
	 * 
	 * @param source
	 *            原始账单
	 * @param pactCode
	 *            协议号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> saveBill(Map<String, Object> source, HroPactInfo pactInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		int updsusccess = 0, upderror = 0, inssusccess = 0, inserror = 0, dataerror = 0;
		if(source.isEmpty() || source.get("resultData") == null || StringUtils.isBlank(source.get("resultData").toString())){
			result.put("updsuccess", updsusccess);
			result.put("upderror", upderror);
			result.put("inssuccess", inssusccess);
			result.put("inserror", inserror);
			result.put("dataerror", dataerror);
			return result;
		}
		
		// 获取接口返回数据
		Map<String, Object> resultData = (Map<String, Object>) source.get("resultData");
		if(resultData.get("entities") == null || StringUtils.isBlank(resultData.get("entities").toString())){
			result.put("updsuccess", updsusccess);
			result.put("upderror", upderror);
			result.put("inssuccess", inssusccess);
			result.put("inserror", inserror);
			result.put("dataerror", dataerror);
			return result;
		}
		
		List<Map<String, Object>> entities = (List<Map<String, Object>>) resultData.get("entities");
		for (Map<String, Object> entitie : entities) {
			if(entitie == null){
				dataerror ++;
				continue;
			}
			
			// 将接口中返回字段和数据库映射实体类对应
			EfArap efArap = new EfArap();
			efArap.setProtocolCode(pactInfo.getPactCode());
			efArap.setCompanyId(pactInfo.getCompanyId());
			if(entitie.get("BILL_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_ID").toString())){
				efArap.setBillId(Long.parseLong(entitie.get("BILL_ID").toString()));
			}
			if(entitie.get("ORG_ID")!=null && StringUtils.isNotBlank(entitie.get("ORG_ID").toString())){
				efArap.setOrgId(Long.parseLong(entitie.get("ORG_ID").toString()));
			}
			if(entitie.get("BILL_TYPE")!=null && StringUtils.isNotBlank(entitie.get("BILL_TYPE").toString())){
				efArap.setBillType(Integer.parseInt(entitie.get("BILL_TYPE").toString()));
			}
			if(entitie.get("BILL_CODE")!=null && StringUtils.isNotBlank(entitie.get("BILL_CODE").toString())){
				efArap.setBillCode(entitie.get("BILL_CODE").toString());
			}
			if(entitie.get("PAYEE_ID")!=null && StringUtils.isNotBlank(entitie.get("PAYEE_ID").toString())){
				efArap.setPayeeId(Long.parseLong(entitie.get("PAYEE_ID").toString()));
			}
			if(entitie.get("BILL_YM")!=null && StringUtils.isNotBlank(entitie.get("BILL_YM").toString())){
				efArap.setBillYm(entitie.get("BILL_YM").toString());
			}
			if(entitie.get("BILL_TEMPLATE_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_TEMPLATE_ID").toString())){
				efArap.setBillTemplateId(Long.parseLong(entitie.get(
						"BILL_TEMPLATE_ID").toString()));
			}
			if(entitie.get("PAYMENT_DAY")!=null && StringUtils.isNotBlank(entitie.get("PAYMENT_DAY").toString())){
				efArap.setPaymentDay(Integer.valueOf(entitie.get(
						"PAYMENT_DAY").toString()));
			}
			if(entitie.get("PAYMENT_LOCK_DAY")!=null && StringUtils.isNotBlank(entitie.get("PAYMENT_LOCK_DAY").toString())){
				efArap.setPaymentLockDay(Integer.valueOf(entitie.get(
						"PAYMENT_LOCK_DAY").toString()));
			}
			if(entitie.get("STATUS_BILL")!=null && StringUtils.isNotBlank(entitie.get("STATUS_BILL").toString())){
				efArap.setStatusBill(entitie.get("STATUS_BILL").toString());
			}
			if(entitie.get("STATUS_IV")!=null && StringUtils.isNotBlank(entitie.get("STATUS_IV").toString())){
				efArap.setStatusIv(entitie.get("STATUS_IV").toString());
			}
			if(entitie.get("STATUS_VERIFY")!=null && StringUtils.isNotBlank(entitie.get("STATUS_VERIFY").toString())){
				efArap.setStatusVerify(entitie.get("STATUS_VERIFY").toString());
			}			
			
			try {
				if(entitie.get("DATE_BILL_CREATE_FIRST")!=null && StringUtils.isNotBlank(entitie.get("DATE_BILL_CREATE_FIRST").toString())){
					efArap.setDateBillCreateFirst(JabavaUtil.formatDate(entitie.get(
							"DATE_BILL_CREATE_FIRST").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("DATE_BILL_CREATE")!=null && StringUtils.isNotBlank(entitie.get("DATE_BILL_CREATE").toString())){
					efArap.setDateBillCreate(JabavaUtil.formatDate(entitie.get(
							"DATE_BILL_CREATE").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("DATE_BILL_CONFIRM")!=null && StringUtils.isNotBlank(entitie.get("DATE_BILL_CONFIRM").toString())){
					efArap.setDateBillConfirm(JabavaUtil.formatDate(entitie.get(
							"DATE_BILL_CONFIRM").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("PAY_DAY")!=null && StringUtils.isNotBlank(entitie.get("PAY_DAY").toString())){
					efArap.setPayDay(JabavaUtil.formatDate(entitie.get(
							"PAY_DAY").toString(), "yyyy-MM-dd"));
				}
				if(entitie.get("CREATE_DT")!=null && StringUtils.isNotBlank(entitie.get("CREATE_DT").toString())){
					efArap.setCreateDate(JabavaUtil.formatDate(entitie.get(
							"CREATE_DT").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("UPDATE_DT")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_DT").toString())){
					efArap.setUpdateDate(JabavaUtil.formatDate(entitie.get(
							"UPDATE_DT").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("DATE_BILL_CANCEL")!=null && StringUtils.isNotBlank(entitie.get("DATE_BILL_CANCEL").toString())){
					efArap.setDateBillCancel(JabavaUtil.formatDate(entitie.get(
							"DATE_BILL_CANCEL").toString(), "yyyyMMddhhmmss"));
				}
				
			} catch (Exception e) {
				logger.warn("保存账单时日期转换错误！账单号：" + efArap.getBillCode() + "\n" + e.getMessage());
			}
			if(entitie.get("PAYMENT_SETTLEMENT")!=null && StringUtils.isNotBlank(entitie.get("PAYMENT_SETTLEMENT").toString())){
				efArap.setPaymentSettlement(Integer.parseInt(entitie.get(
						"PAYMENT_SETTLEMENT").toString()));
			}
			if(entitie.get("AMOUNT_TOTAL")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT_TOTAL").toString())){
				efArap.setAmountTotal(new BigDecimal(entitie.get("AMOUNT_TOTAL").toString()));
			}
			if(entitie.get("AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT").toString())){
				efArap.setAmount(new BigDecimal(entitie.get("AMOUNT").toString()));
			}
			if(entitie.get("AMOUNT_ADJ")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT_ADJ").toString())){
				efArap.setAmountAdj(new BigDecimal(entitie.get("AMOUNT_ADJ").toString()));
			}
			
			efArap.setAmountAdjIv(null);
			efArap.setAmountAdjVerify(null);
			if(entitie.get("REMARK")!=null && StringUtils.isNotBlank(entitie.get("REMARK").toString())){
				efArap.setRemark(entitie.get("REMARK").toString());
			}
			if(entitie.get("IS_DELETED")!=null && StringUtils.isNotBlank(entitie.get("IS_DELETED").toString())){
				efArap.setIsDeleted(Integer.parseInt(entitie.get("IS_DELETED").toString()));
			}
			if(entitie.get("CREATE_BY")!=null && StringUtils.isNotBlank(entitie.get("CREATE_BY").toString())){
				efArap.setCreateUserId(Long.parseLong(entitie.get("CREATE_BY").toString()));
			}		
			
			//efArap.setCreateDate(new Date(Long.parseLong(entitie.get("CREATE_DT").toString())));
			if(entitie.get("UPDATE_BY")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_BY").toString())){
				efArap.setUpdateUserId(Long.parseLong(entitie.get("UPDATE_BY").toString()));
			}
			if(entitie.get("TOTAL_SB_AMOUNT_E")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_SB_AMOUNT_E").toString())){
				efArap.setTotalSbAmountE(new BigDecimal(entitie.get("TOTAL_SB_AMOUNT_E").toString()));
			}
			//efArap.setUpdateDate(new Date(Long.parseLong(entitie.get("UPDATE_DT").toString())));
			if(entitie.get("TOTAL_SB_AMOUNT_P")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_SB_AMOUNT_P").toString())){
				efArap.setTotalSbAmountP(new BigDecimal(entitie.get("TOTAL_SB_AMOUNT_P").toString()));
			}
			if(entitie.get("TOTAL_EXTRA_AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_EXTRA_AMOUNT").toString())){
				efArap.setTotalExtraAmount(new BigDecimal(entitie.get("TOTAL_EXTRA_AMOUNT").toString()));
			}
			if(entitie.get("TOTAL_NSB_AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_NSB_AMOUNT").toString())){
				efArap.setTotalNsbAmount(new BigDecimal(entitie.get("TOTAL_NSB_AMOUNT").toString()));
			}
			if(entitie.get("TOTAL_WITHOUTSUM_AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_WITHOUTSUM_AMOUNT").toString())){
				efArap.setTotalWithoutsumAmount(new BigDecimal(entitie.get("TOTAL_WITHOUTSUM_AMOUNT").toString()));
			}
			if(entitie.get("TOTAL_AGENT_AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_AGENT_AMOUNT").toString())){
				efArap.setTotalAgentAmount(new BigDecimal(entitie.get("TOTAL_AGENT_AMOUNT").toString()));
			}
			if(entitie.get("TOTAL_NOTAGENT_AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("TOTAL_NOTAGENT_AMOUNT").toString())){
				efArap.setTotalNotagentAmount(new BigDecimal(entitie.get("TOTAL_NOTAGENT_AMOUNT").toString()));
			}
			if(entitie.get("HAS_VOUCHER_CONFIRM")!=null && StringUtils.isNotBlank(entitie.get("HAS_VOUCHER_CONFIRM").toString())){
				efArap.setHasVoucherConfirm(Integer.parseInt(entitie.get("HAS_VOUCHER_CONFIRM").toString()));
			}
			//efArap.setDateBillCancel(new Date(Long.parseLong(entitie.get("DATE_BILL_CANCEL").toString())));			
			
			if(!this.addBillDetailNsb(efArap)){
				dataerror ++;
				continue;
			}
			if(!this.addBillDetailSb(efArap)){
				dataerror ++;
				continue;
			}
			
			//计算账单金额
			
			this.calAmount(efArap);
			logger.info(efArap.getBillCode() + "账单应收: Amount=" + efArap.getAmount().toString()
					+ ", 账单总额: TotalAmount=" + efArap.getAmountTotal());
			
			// 根据账单id获取是否存在此数据
			int isExitBill = efArapService.isExitBill(efArap.getBillCode());
			if (isExitBill > 0) {	// 修改账单
				int res = efArapService.updateByPrimaryKey(efArap);
				if (res > 0) {
					updsusccess ++;
				} else {
					upderror ++;
				}
			} else {				// 添加账单
				int res = efArapService.insertSelective(efArap);
				if (res > 0) {
					inssusccess ++;
				} else {
					inserror ++;
				}
			}
		}
		
	
		result.put("updsuccess", updsusccess);
		result.put("upderror", upderror);
		result.put("inssuccess", inssusccess);
		result.put("inserror", inserror);
		result.put("dataerror", dataerror);
		return result;
	}
	
	private void calAmount(EfArap efArap){
		//应收应付总额
		BigDecimal amount = new BigDecimal(0);
		efArap.setAmount(amount
				.add(efArap.getTotalSbAmountE() == null ? BigDecimal.ZERO : efArap.getTotalSbAmountE())
				.add(efArap.getTotalSbAmountP() == null ? BigDecimal.ZERO : efArap.getTotalSbAmountP())
				.add(efArap.getTotalNsbAmount() == null ? BigDecimal.ZERO : efArap.getTotalNsbAmount())
				.add(efArap.getTotalExtraAmount() == null ? BigDecimal.ZERO : efArap.getTotalExtraAmount())
				.subtract(efArap.getTotalWithoutsumAmount() == null ? BigDecimal.ZERO : efArap.getTotalWithoutsumAmount())
				.subtract(efArap.getAmountAdj() == null ? BigDecimal.ZERO : efArap.getAmountAdj()));
		
		//账单总额
		BigDecimal amountTotal = new BigDecimal(0);
		efArap.setAmountTotal(amountTotal
				.add(efArap.getTotalSbAmountE() == null ? BigDecimal.ZERO : efArap.getTotalSbAmountE())
				.add(efArap.getTotalSbAmountP() == null ? BigDecimal.ZERO : efArap.getTotalSbAmountP())
				.add(efArap.getTotalNsbAmount() == null ? BigDecimal.ZERO : efArap.getTotalNsbAmount())
				.add(efArap.getTotalExtraAmount() == null ? BigDecimal.ZERO : efArap.getTotalExtraAmount()));
	}

	/**
	 * 账单接口参数
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Object> jsonParam(Map<String, Object> param, String code) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		// 将传入参数进行处理
		Set set = param.entrySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			parameter.put(key, value);
		}
		// 拼接接口参数
		parameter.put("code", code);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("parameter", parameter);
		return data;
	}

	/**
	 * 获取账单明细
	 * 
	 * @param efArap
	 *            账单
	 * @param pactCode
	 *            协议号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean addBillDetailNsb(EfArap efArap) {
		// 拼接接口参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("BILL_CODE", efArap.getBillCode());
		parameter.put("PROTOCOL_CODE", efArap.getProtocolCode());
		// 获取拼接参数
		Map<String, Object> jsonParam = jsonParam(parameter, CODE_BILLDETAILNSB);
		try {
			// 获取接口返回数据
			Map<String, Object> result = requestService.invoke(TYPE, jsonParam);
			//如果为空就返回
			if(result == null ||result.isEmpty() || result.get("resultData") == null || StringUtils.isBlank(result.get("resultData").toString())){
				return false;
			}
			
			logger.debug(com.alibaba.fastjson.JSONObject.toJSONString(result));
			
			// 获取接口返回数据
			Map<String, Object> resultData = (Map<String, Object>) result.get("resultData");
			if(resultData == null || StringUtils.isBlank(resultData.toString())){
				return false;
			}
			
			List<Map<String, Object>> entities = (List<Map<String, Object>>) resultData.get("entities");
			if(entities == null || StringUtils.isBlank(entities.toString())){
				return false;
			}
			
			for (Map<String, Object> entitie : entities) {
				if(entitie == null){
					Log.warn("账单非社保明细有一条空数据");
					continue;
				}
				
				// 将接口中返回字段和数据库映射实体类对应
				EfArapDetailEmpNsb arapDetailEmp = new EfArapDetailEmpNsb();
				if(entitie.get("BILL_DETAIL_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_DETAIL_ID").toString())){
					arapDetailEmp.setBillDetailId(Long.parseLong(entitie.get("BILL_DETAIL_ID").toString()));
				}
				if(entitie.get("BILL_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_ID").toString())){
					arapDetailEmp.setBillId(Long.parseLong(entitie.get("BILL_ID").toString()));
				}
				if(entitie.get("BILL_YM")!=null && StringUtils.isNotBlank(entitie.get("BILL_YM").toString())){
					arapDetailEmp.setBillYm(entitie.get("BILL_YM").toString());
				}
				if(entitie.get("BILL_TEMPLATE_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_TEMPLATE_ID").toString())){
					arapDetailEmp.setBillTemplateId(Long.parseLong(entitie.get("BILL_TEMPLATE_ID").toString()));
				}
				if(entitie.get("EMP_ID")!=null && StringUtils.isNotBlank(entitie.get("EMP_ID").toString())){
					arapDetailEmp.setEmpId(Long.parseLong(entitie.get("EMP_ID").toString()));
				}
				if(entitie.get("CLIENT_ID")!=null && StringUtils.isNotBlank(entitie.get("CLIENT_ID").toString())){
					arapDetailEmp.setClientId(Long.parseLong(entitie.get("CLIENT_ID").toString()));
				}
				if(entitie.get("PREVIOUS_SEND_ID")!=null && StringUtils.isNotBlank(entitie.get("PREVIOUS_SEND_ID").toString())){
					arapDetailEmp.setPreviousSendId(Long.parseLong(entitie.get("PREVIOUS_SEND_ID").toString()));
				}
				if(entitie.get("SERVER_YM")!=null && StringUtils.isNotBlank(entitie.get("SERVER_YM").toString())){
					arapDetailEmp.setServerYm(entitie.get("SERVER_YM").toString());
				}
				if(entitie.get("QUOT_ID")!=null && StringUtils.isNotBlank(entitie.get("QUOT_ID").toString())){
					arapDetailEmp.setQuotId(Long.parseLong(entitie.get("QUOT_ID").toString()));
				}
				if(entitie.get("PROD_ID")!=null && StringUtils.isNotBlank(entitie.get("PROD_ID").toString())){
					arapDetailEmp.setProdId(Long.parseLong(entitie.get("PROD_ID").toString()));
				}
				if(entitie.get("PROD_NAME")!=null && StringUtils.isNotBlank(entitie.get("PROD_NAME").toString())){
					arapDetailEmp.setProdName(entitie.get("PROD_NAME").toString());
				}
				if(entitie.get("IS_ONE_OFF")!=null && StringUtils.isNotBlank(entitie.get("IS_ONE_OFF").toString())){
					arapDetailEmp.setIsOneOff(Integer.parseInt(entitie.get("IS_ONE_OFF").toString()));
				}
				if(entitie.get("AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT").toString())){
					arapDetailEmp.setAmount(new BigDecimal(entitie.get("AMOUNT").toString()));
				}
				if(entitie.get("IS_SUM")!=null && StringUtils.isNotBlank(entitie.get("IS_SUM").toString())){
					arapDetailEmp.setIsSum(Integer.parseInt(entitie.get("IS_SUM").toString()));
				}
				if(entitie.get("CONTRACT_ID")!=null && StringUtils.isNotBlank(entitie.get("CONTRACT_ID").toString())){
					arapDetailEmp.setContractId(Long.parseLong(entitie.get("CONTRACT_ID").toString()));
				}
				if(entitie.get("CONTRACT_TYPE")!=null && StringUtils.isNotBlank(entitie.get("CONTRACT_TYPE").toString())){
					arapDetailEmp.setContractType(entitie.get("CONTRACT_TYPE").toString());
				}
				if(entitie.get("SECOND_CONTRACT_ID")!=null && StringUtils.isNotBlank(entitie.get("SECOND_CONTRACT_ID").toString())){
					arapDetailEmp.setSecondContractId(Long.parseLong(entitie.get("SECOND_CONTRACT_ID").toString()));
				}
				if(entitie.get("IS_DELETED")!=null && StringUtils.isNotBlank(entitie.get("IS_DELETED").toString())){
					arapDetailEmp.setIsDeleted(Integer.parseInt(entitie.get("IS_DELETED").toString()));
				}
				if(entitie.get("CREATE_BY")!=null && StringUtils.isNotBlank(entitie.get("CREATE_BY").toString())){
					arapDetailEmp.setCreateUserId(Long.parseLong(entitie.get("CREATE_BY").toString()));
				}
				if(entitie.get("CREATE_DT")!=null && StringUtils.isNotBlank(entitie.get("CREATE_DT").toString())){
					arapDetailEmp.setCreateDate(JabavaUtil.formatDate(entitie.get(
							"CREATE_DT").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("UPDATE_BY")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_BY").toString())){
					arapDetailEmp.setUpdateUserId(Long.parseLong(entitie.get("UPDATE_BY").toString()));
				}
				if(entitie.get("UPDATE_DT")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_DT").toString())){
					arapDetailEmp.setUpdateDate(JabavaUtil.formatDate(entitie.get(
							"UPDATE_DT").toString(), "yyyyMMddhhmmss"));
				}
				if(entitie.get("QUOT_TYPE")!=null && StringUtils.isNotBlank(entitie.get("QUOT_TYPE").toString())){
					arapDetailEmp.setQuotType(Integer.parseInt(entitie.get("QUOT_TYPE").toString()));
				}
				if(entitie.get("ORDER_ID")!=null && StringUtils.isNotBlank(entitie.get("ORDER_ID").toString())){
					arapDetailEmp.setOrderId(Long.parseLong(entitie.get("ORDER_ID").toString()));
				}
				if(entitie.get("CARD_ID")!=null && StringUtils.isNotBlank(entitie.get("CARD_ID").toString())){
					arapDetailEmp.setCertId(entitie.get("CARD_ID").toString());
				}
				if(entitie.get("CARD_TYPE")!=null && StringUtils.isNotBlank(entitie.get("CARD_TYPE").toString())){
					arapDetailEmp.setCertType(TaskUtil.transformCardTypeFromHro(entitie.get("CARD_TYPE").toString()));
				}
				if(entitie.get("CARD_TYPE")!=null && StringUtils.isNotBlank(entitie.get("CARD_TYPE").toString())){
					arapDetailEmp.setCertType(TaskUtil.transformCardTypeFromHro(entitie.get("CARD_TYPE").toString()));
				}
				if(entitie.get("EMPLOYEE_REC_ID")!=null && StringUtils.isNotBlank(entitie.get("EMPLOYEE_REC_ID").toString())){
					arapDetailEmp.setEmployeeRecId(Long.parseLong(entitie.get("EMPLOYEE_REC_ID").toString()));
				}
				
				
				arapDetailEmp.setRedFlag(null);
				arapDetailEmp.setEmployeeRecId(null);
				arapDetailEmp.setRemark(null);
				
				efArap.addDetailEmpNsb(arapDetailEmp);
			}
		} catch (Exception e) {
			logger.warn("获取账单非社保出错！账单号：" + efArap.getBillCode() + "\n" + e.getMessage());
			return false;
		}
		
		return true;
	}

	/**
	 * 获取账单明细非社保
	 * 
	 * @param efArap
	 *            账单
	 * @param pactCode
	 *            协议号
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private boolean addBillDetailSb(EfArap efArap) {
		// 拼接接口参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("BILL_CODE", efArap.getBillCode());
		parameter.put("PROTOCOL_CODE", efArap.getProtocolCode());
		// 获取拼接参数
		Map<String, Object> jsonParam = jsonParam(parameter, CODE_BILLDETAIL);
		try {
			Map<String, Object> result = requestService.invoke(TYPE, jsonParam);
			if(result == null || StringUtils.isBlank(result.toString())){
				return false;
			}
			
			logger.debug(com.alibaba.fastjson.JSONObject.toJSONString(result));
			
			// 获取接口返回数据
			Map<String, Object> resultData = (Map<String, Object>) result.get("resultData");
			if(resultData == null || StringUtils.isBlank(resultData.toString())){
				return false;
			}
			
			List<Map<String, Object>> entities = (List<Map<String, Object>>) resultData.get("entities");
			if(entities == null || StringUtils.isBlank(entities.toString())){
				return false;
			}
			
			for (Map<String, Object> entitie : entities) {
				if(entitie == null){
					Log.warn("账单社保明细有一条空数据");
					continue;
				}
				
				// 将接口中返回字段和数据库映射实体类对应
				EfArapDetailEmpSb arapDetailEmpSb = new EfArapDetailEmpSb();
				if(entitie.get("BILL_DETAIL_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_DETAIL_ID").toString())){
					arapDetailEmpSb.setBillDetailId(Long.parseLong(entitie.get("BILL_DETAIL_ID").toString()));
				}
				if(entitie.get("BILL_YM")!=null && StringUtils.isNotBlank(entitie.get("BILL_YM").toString())){
					arapDetailEmpSb.setBillYm(entitie.get("BILL_YM").toString());
				}
				if(entitie.get("BILL_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_ID").toString())){
					arapDetailEmpSb.setBillId(Long.parseLong(entitie.get("BILL_ID").toString()));
				}
				if(entitie.get("BILL_TEMPLATE_ID")!=null && StringUtils.isNotBlank(entitie.get("BILL_TEMPLATE_ID").toString())){
					arapDetailEmpSb.setBillTemplateId(Long.parseLong(entitie.get("BILL_TEMPLATE_ID").toString()));
				}
				if(entitie.get("EMP_ID")!=null && StringUtils.isNotBlank(entitie.get("EMP_ID").toString())){
					arapDetailEmpSb.setEmpId(Long.parseLong(entitie.get("EMP_ID").toString()));
				}
				if(entitie.get("CLIENT_ID")!=null && StringUtils.isNotBlank(entitie.get("CLIENT_ID").toString())){
					arapDetailEmpSb.setClientId(Long.parseLong(entitie.get("CLIENT_ID").toString()));
				}
				if(entitie.get("PREVIOUS_SEND_ID")!=null && StringUtils.isNotBlank(entitie.get("PREVIOUS_SEND_ID").toString())){
					arapDetailEmpSb.setPreviousSendId(Long.parseLong(entitie.get("PREVIOUS_SEND_ID").toString()));
				}
				if(entitie.get("CITY_ID")!=null && StringUtils.isNotBlank(entitie.get("CITY_ID").toString())){
					arapDetailEmpSb.setCityId(Long.parseLong(entitie.get("CITY_ID").toString()));
				}
				if(entitie.get("NAME_OF_CITY")!=null && StringUtils.isNotBlank(entitie.get("NAME_OF_CITY").toString())){
					arapDetailEmpSb.setCityName(entitie.get("NAME_OF_CITY").toString());
				}
				if(entitie.get("SB_YM")!=null && StringUtils.isNotBlank(entitie.get("SB_YM").toString())){
					arapDetailEmpSb.setSbYm(entitie.get("SB_YM").toString());
				}				
				if(entitie.get("SERVER_YM")!=null && StringUtils.isNotBlank(entitie.get("SERVER_YM").toString())){
					arapDetailEmpSb.setServerYm(entitie.get("SERVER_YM").toString());
				}
				if(entitie.get("POLICY_GROUP_ID")!=null && StringUtils.isNotBlank(entitie.get("POLICY_GROUP_ID").toString())){
					arapDetailEmpSb.setPolicyGroupId(Long.parseLong(entitie.get("POLICY_GROUP_ID").toString()));
				}
				if(entitie.get("PROD_ID")!=null && StringUtils.isNotBlank(entitie.get("PROD_ID").toString())){
					arapDetailEmpSb.setProdId(Long.parseLong(entitie.get("PROD_ID").toString()));
				}
				if(entitie.get("PROD_NAME")!=null && StringUtils.isNotBlank(entitie.get("PROD_NAME").toString())){
					arapDetailEmpSb.setProdName(entitie.get("PROD_NAME").toString());
				}
				if(entitie.get("IS_SUM")!=null && StringUtils.isNotBlank(entitie.get("IS_SUM").toString())){
					arapDetailEmpSb.setIsSum(Integer.parseInt(entitie.get("IS_SUM").toString()));
				}
				if(entitie.get("IS_ONE_OFF")!=null && StringUtils.isNotBlank(entitie.get("IS_ONE_OFF").toString())){
					arapDetailEmpSb.setIsOneOff(Integer.parseInt(entitie.get("IS_ONE_OFF").toString()));
				}
				if(entitie.get("AMOUNT")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT").toString())){
					arapDetailEmpSb.setAmount(new BigDecimal(entitie.get("AMOUNT").toString()));
				}
				if(entitie.get("AMOUNT_E")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT_E").toString())){
					arapDetailEmpSb.setAmountE(new BigDecimal(entitie.get("AMOUNT_E").toString()));
				}
				if(entitie.get("AMOUNT_P")!=null && StringUtils.isNotBlank(entitie.get("AMOUNT_P").toString())){
					arapDetailEmpSb.setAmountP(new BigDecimal(entitie.get("AMOUNT_P").toString()));
				}
				if(entitie.get("BASE_E")!=null && StringUtils.isNotBlank(entitie.get("BASE_E").toString())){
					arapDetailEmpSb.setBaseE(new BigDecimal(entitie.get("BASE_E").toString()));
				}
				if(entitie.get("BASE_P")!=null && StringUtils.isNotBlank(entitie.get("BASE_P").toString())){
					arapDetailEmpSb.setBaseP(new BigDecimal(entitie.get("BASE_P").toString()));
				}
				if(entitie.get("RATIO_E")!=null && StringUtils.isNotBlank(entitie.get("RATIO_E").toString())){
					arapDetailEmpSb.setRatioE(new BigDecimal(entitie.get("RATIO_E").toString()));
				}
				if(entitie.get("RATIO_P")!=null && StringUtils.isNotBlank(entitie.get("RATIO_P").toString())){
					arapDetailEmpSb.setRatioP(new BigDecimal(entitie.get("RATIO_P").toString()));
				}
				if(entitie.get("ADD_AMT_E")!=null && StringUtils.isNotBlank(entitie.get("ADD_AMT_E").toString())){
					arapDetailEmpSb.setAddAmtE(new BigDecimal(entitie.get("ADD_AMT_E").toString()));
				}
				if(entitie.get("ADD_AMT_P")!=null && StringUtils.isNotBlank(entitie.get("ADD_AMT_P").toString())){
					arapDetailEmpSb.setAddAmtP(new BigDecimal(entitie.get("ADD_AMT_E").toString()));
				}
				if(entitie.get("CONTRACT_ID")!=null && StringUtils.isNotBlank(entitie.get("CONTRACT_ID").toString())){
					arapDetailEmpSb.setContractId(Long.parseLong(entitie.get("CONTRACT_ID").toString()));
				}
				if(entitie.get("CONTRACT_TYPE")!=null && StringUtils.isNotBlank(entitie.get("CONTRACT_TYPE").toString())){
					arapDetailEmpSb.setContractType(entitie.get("CONTRACT_TYPE").toString());
				}
				if(entitie.get("SECOND_CONTRACT_ID")!=null && StringUtils.isNotBlank(entitie.get("SECOND_CONTRACT_ID").toString())){
					arapDetailEmpSb.setSecondContractId(Long.parseLong(entitie.get("SECOND_CONTRACT_ID").toString()));
				}
				if(entitie.get("IS_DELETED")!=null && StringUtils.isNotBlank(entitie.get("IS_DELETED").toString())){
					arapDetailEmpSb.setIsDeleted(Integer.parseInt(entitie.get("IS_DELETED").toString()));
				}
				if(entitie.get("CREATE_BY")!=null && StringUtils.isNotBlank(entitie.get("CREATE_BY").toString())){
					arapDetailEmpSb.setCreateUserId(Long.parseLong(entitie.get("CREATE_BY").toString()));
				}
				if(entitie.get("CREATE_DT")!=null && StringUtils.isNotBlank(entitie.get("CREATE_DT").toString())){
					arapDetailEmpSb.setCreateDate(JabavaUtil.formatDate(entitie.get("CREATE_DT").toString(),"yyyyMMddhhmmss"));
				}
				if(entitie.get("UPDATE_BY")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_BY").toString())){
					arapDetailEmpSb.setUpdateUserId(Long.parseLong(entitie.get("UPDATE_BY").toString()));
				}
				if(entitie.get("UPDATE_DT")!=null && StringUtils.isNotBlank(entitie.get("UPDATE_DT").toString())){
					arapDetailEmpSb.setUpdateDate(JabavaUtil.formatDate(entitie.get("UPDATE_DT").toString(),"yyyyMMddhhmmss"));
				}
				if(entitie.get("ORDER_ID")!=null && StringUtils.isNotBlank(entitie.get("ORDER_ID").toString())){
					arapDetailEmpSb.setOrderId(Long.parseLong(entitie.get("ORDER_ID").toString()));
				}
				if(entitie.get("CARD_ID")!=null && StringUtils.isNotBlank(entitie.get("CARD_ID").toString())){
					arapDetailEmpSb.setCertId(entitie.get("CARD_ID").toString());
				}
				if(entitie.get("CARD_TYPE")!=null && StringUtils.isNotBlank(entitie.get("CARD_TYPE").toString())){
					arapDetailEmpSb.setCertType(TaskUtil.transformCardTypeFromHro(entitie.get("CARD_TYPE").toString()));
				}
				if(entitie.get("EMPLOYEE_REC_ID")!=null && StringUtils.isNotBlank(entitie.get("EMPLOYEE_REC_ID").toString())){
					arapDetailEmpSb.setEmployeeRecId(Long.parseLong(entitie.get("EMPLOYEE_REC_ID").toString()));
				}
				
				arapDetailEmpSb.setRedFlag(null);
				arapDetailEmpSb.setEmployeeRecId(null);
				arapDetailEmpSb.setRemark(null);
				
				efArap.addDetailEmpSb(arapDetailEmpSb);
			}
		} catch (Exception e) {e.printStackTrace();
			logger.warn("获取社保账单详情出错！账单号：" + efArap.getBillCode() + "\n" + e.getMessage());
			return false;
		}
		
		return true;
	}
}