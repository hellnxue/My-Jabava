package com.jabava.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.pojo.hro.EfReceiptOwned;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.IEfReceiptOwnedService;
import com.jabava.service.hro.IHroPactInfoService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;

/**
 * 定时导入付款数据
 * 
 * @author 王振波
 */
@Service
public class ImportReceiptOwnedTask {

	private static final Logger logger = Logger.getLogger(ImportReceiptOwnedTask.class);

	/**
	 * 接口参数类型
	 */
	private static final String TYPE = "queryReceiptOwned";

	@Autowired
	private IEfReceiptOwnedService receiptOwnedService;
	
	@Autowired
	private IHroPactInfoService pactInfoService;
	
	private HROFetchService requestService;
	
	public ImportReceiptOwnedTask(){
		String server =  JabavaPropertyCofigurer.getProperty("SERVER_URL");
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}

	/**
	 * 执行定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void execute() throws Exception {
		logger.info("ImportReceiptOwnedTask Started .................");
		
		//获取协议号
		List<HroPactInfo> pactCodeList = pactInfoService.queryPactInfoList();
		if(pactCodeList != null && !pactCodeList.isEmpty()){
			for (HroPactInfo pactInfo : pactCodeList) {
				// 循环年月
				List<String> monthList = TaskUtil.getMonthList("month.sync.hro.receiptOwned");
				if(monthList == null || monthList.isEmpty()){
					continue ;
				}
				for (String billMonth : monthList) {
					this.queryReceiptOwned(pactInfo, billMonth);
				}
			}
		}
		
		logger.info("ImportReceiptOwnedTask Finished .................");
	}
	
	private boolean queryReceiptOwned(HroPactInfo pactInfo,String billMonth){
		StringBuffer sb = new StringBuffer();
		sb.append("协议号：").append(pactInfo.getPactCode());
		
		// 拼接接口参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("protocolCode", pactInfo.getPactCode());
		if(!StringUtils.isEmpty(billMonth)){
			parameter.put("month", billMonth);
			sb.append("，付款月：").append(billMonth);
		}
		
		// 执行接口
		Map<String, Object> source = null;
		try {
			source = requestService.invoke(TYPE, parameter);
			logger.debug(com.alibaba.fastjson.JSONObject.toJSONString(source));
			
			// 更新数据库数据
			Map<String,Object> result = saveReceiptOwned(source, pactInfo);
			sb.append("同步成功数：").append(result.get("success"));

			logger.info(sb.toString());
		} catch (Exception e) {
			logger.error("获取付款出错！" + sb.toString() + "\n" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 根据付款id更新数据库
	 * 
	 * @param source
	 *            原始付款
	 * @param pactCode
	 *            协议号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> saveReceiptOwned(Map<String, Object> source, HroPactInfo pactInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		int susccess = 0;
		if(source.isEmpty() || source.get("resultData") == null || StringUtils.isBlank(source.get("resultData").toString())){
			result.put("success", susccess);
			return result;
		}
		
		// 获取接口返回数据
		List<Map<String, Object>> resultData = (List<Map<String, Object>>) source.get("resultData");
		List<EfReceiptOwned> recordList = new ArrayList<EfReceiptOwned>();
		for (Map<String, Object> entity : resultData) {
			if(entity == null){
				continue;
			}
			
			// 将接口中返回字段和数据库映射实体类对应
			EfReceiptOwned receiptOwned = new EfReceiptOwned();
			receiptOwned.setProtocolCode(pactInfo.getPactCode());
			receiptOwned.setCompanyId(pactInfo.getCompanyId());
			if(entity.get("matchId")!=null && StringUtils.isNotBlank(entity.get("matchId").toString())){
				receiptOwned.setMatchId(Long.parseLong(entity.get("matchId").toString()));
			}
			if(entity.get("orgId")!=null && StringUtils.isNotBlank(entity.get("orgId").toString())){
				receiptOwned.setOrgId(Long.parseLong(entity.get("orgId").toString()));
			}
			if(entity.get("matchPayerId")!=null && StringUtils.isNotBlank(entity.get("matchPayerId").toString())){
				receiptOwned.setMatchPayerId(Long.parseLong(entity.get("matchPayerId").toString()));
			}
			if(entity.get("matchAmount")!=null && StringUtils.isNotBlank(entity.get("matchAmount").toString())){
				receiptOwned.setMatchAmount(new BigDecimal(entity.get("matchAmount").toString()));
			}
			if(entity.get("matchBy")!=null && StringUtils.isNotBlank(entity.get("matchBy").toString())){
				receiptOwned.setMatchBy(Long.parseLong(entity.get("matchBy").toString()));
			}
			if(entity.get("matchType")!=null && StringUtils.isNotBlank(entity.get("matchType").toString())){
				receiptOwned.setMatchType(Integer.parseInt(entity.get("matchType").toString()));
			}
			if(entity.get("receiptBankId")!=null && StringUtils.isNotBlank(entity.get("receiptBankId").toString())){
				receiptOwned.setReceiptBankId(Long.parseLong(entity.get("receiptBankId").toString()));
			}
			if(entity.get("receiptBankName")!=null && StringUtils.isNotBlank(entity.get("receiptBankName").toString())){
				receiptOwned.setReceiptBankName(entity.get("receiptBankName").toString());
			}
			if(entity.get("receiptBank")!=null && StringUtils.isNotBlank(entity.get("receiptBank").toString())){
				receiptOwned.setReceiptBank(entity.get("receiptBank").toString());
			}
			if(entity.get("receiptBankAccountName")!=null && StringUtils.isNotBlank(entity.get("receiptBankAccountName").toString())){
				receiptOwned.setReceiptBankAccountName(entity.get("receiptBankAccountName").toString());
			}
			if(entity.get("receiptBankAccount")!=null && StringUtils.isNotBlank(entity.get("receiptBankAccount").toString())){
				receiptOwned.setReceiptBankAccount(entity.get("receiptBankAccount").toString());
			}
			if(entity.get("receiptType")!=null && StringUtils.isNotBlank(entity.get("receiptType").toString())){
				receiptOwned.setReceiptType(Integer.parseInt(entity.get("receiptType").toString()));
			}
			if(entity.get("transId")!=null && StringUtils.isNotBlank(entity.get("transId").toString())){
				receiptOwned.setTransId(entity.get("transId").toString());
			}
			if(entity.get("payAccountName")!=null && StringUtils.isNotBlank(entity.get("payAccountName").toString())){
				receiptOwned.setPayAccountName(entity.get("payAccountName").toString());
			}
			if(entity.get("payAccountNo")!=null && StringUtils.isNotBlank(entity.get("payAccountNo").toString())){
				receiptOwned.setPayAccountNo(entity.get("payAccountNo").toString());
			}
			if(entity.get("amount")!=null && StringUtils.isNotBlank(entity.get("amount").toString())){
				receiptOwned.setAmount(new BigDecimal(entity.get("amount").toString()));
			}
			if(entity.get("memo")!=null && StringUtils.isNotBlank(entity.get("memo").toString())){
				receiptOwned.setMemo(entity.get("memo").toString());
			}
			if(entity.get("status")!=null && StringUtils.isNotBlank(entity.get("status").toString())){
				receiptOwned.setStatus(Integer.parseInt(entity.get("status").toString()));
			}
			if(entity.get("isDeleted")!=null && StringUtils.isNotBlank(entity.get("isDeleted").toString())){
				receiptOwned.setIsDeleted(Integer.parseInt(entity.get("isDeleted").toString()));
			}
			if(entity.get("createBy")!=null && StringUtils.isNotBlank(entity.get("createBy").toString())){
				receiptOwned.setCreateBy(Long.parseLong(entity.get("createBy").toString()));
			}
			if(entity.get("updateBy")!=null && StringUtils.isNotBlank(entity.get("updateBy").toString())){
				receiptOwned.setUpdateBy(Long.parseLong(entity.get("updateBy").toString()));
			}
			if(entity.get("mimicBy")!=null && StringUtils.isNotBlank(entity.get("mimicBy").toString())){
				receiptOwned.setMimicBy(Long.parseLong(entity.get("mimicBy").toString()));
			}
			if(entity.get("proxyBy")!=null && StringUtils.isNotBlank(entity.get("proxyBy").toString())){
				receiptOwned.setProxyBy(Long.parseLong(entity.get("proxyBy").toString()));
			}
			if(entity.get("impDetailId")!=null && StringUtils.isNotBlank(entity.get("impDetailId").toString())){
				receiptOwned.setImpDetailId(Long.parseLong(entity.get("impDetailId").toString()));
			}
			if(entity.get("balanceDirection")!=null && StringUtils.isNotBlank(entity.get("balanceDirection").toString())){
				receiptOwned.setBalanceDirection(entity.get("balanceDirection").toString());
			}
			
			try {
				if(entity.get("matchDate")!=null && StringUtils.isNotBlank(entity.get("matchDate").toString())){
					receiptOwned.setMatchDate(JabavaUtil.formatDate(entity.get("matchDate").toString(), "yyyy-MM-dd"));
				}
				if(entity.get("transDate")!=null && StringUtils.isNotBlank(entity.get("transDate").toString())){
					receiptOwned.setTransDate(JabavaUtil.formatDate(entity.get("transDate").toString(), "yyyy-MM-dd"));
				}
				if(entity.get("createDt")!=null && StringUtils.isNotBlank(entity.get("createDt").toString())){
					receiptOwned.setCreateDt(JabavaUtil.formatDate(entity.get("createDt").toString(), "yyyyMMddhhmmss"));
				}
				if(entity.get("updateDt")!=null && StringUtils.isNotBlank(entity.get("updateDt").toString())){
					receiptOwned.setUpdateDt(JabavaUtil.formatDate(entity.get("updateDt").toString(), "yyyyMMddhhmmss"));
				}
				
			} catch (Exception e) {
				logger.warn("保存付款时日期转换错误！matchId：" + receiptOwned.getMatchId() + "\n" + e.getMessage());
			}
			
			recordList.add(receiptOwned);
		}

		if(!recordList.isEmpty()){
			int res = receiptOwnedService.insertOrUpdateList(recordList);
			if (res > 0) {
				susccess = recordList.size();
			}
		}
		
		result.put("success", susccess);
		return result;
	}
	
}