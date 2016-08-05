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

import com.jabava.pojo.hro.EfSalaryInfo;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.IEfSalaryInfoService;
import com.jabava.service.hro.IHroPactInfoService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;

/**
 * 定时导入工资数据
 * 
 * @author 王振波
 */
@Service
public class ImportSalaryInfoTask {

	private static final Logger logger = Logger.getLogger(ImportSalaryInfoTask.class);

	/**
	 * 接口参数类型
	 */
	//private static final String TYPE = "querySalaryDetail";
	private static final String TYPE = "SqlQuery";
	
	@Autowired
	private IEfSalaryInfoService salaryInfoService;
	
	@Autowired
	private IHroPactInfoService pactInfoService;
	
	private HROFetchService requestService;
	
	public ImportSalaryInfoTask(){
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
		logger.info("ImportSalaryInfoTask Started .................");
		
		//获取协议号
		List<HroPactInfo> pactCodeList = pactInfoService.queryPactInfoList();
		if(pactCodeList != null && !pactCodeList.isEmpty()){
			for (HroPactInfo pactInfo : pactCodeList) {
				// 循环年月
				List<String> monthList = TaskUtil.getMonthList("month.sync.hro.salaryInfo");
				if(monthList == null || monthList.isEmpty()){
					continue ;
				}
				for (String ym : monthList) {
					this.querySalaryInfo(pactInfo, ym);
				}
			}
		}
		
		logger.info("ImportSalaryInfoTask Finished .................");
	}
	
	private boolean querySalaryInfo(HroPactInfo pactInfo,String ym){
		StringBuffer sb = new StringBuffer();
		sb.append("协议号：").append(pactInfo.getPactCode());
		
		// 拼接接口参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("protocolCode", pactInfo.getPactCode());
		parameter.put("code", "querySalaryDetailByPC");
		if(!StringUtils.isEmpty(ym)){
			//parameter.put("month", ym);
			parameter.put("salaryYm", ym);
			sb.append("，工资月：").append(ym);
		}
		
		// 执行接口
		Map<String, Object> source = null;
		try {
			//source = requestService.invoke(TYPE, parameter);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parameter", parameter);
			source = requestService.invoke(TYPE, params);
			logger.debug(com.alibaba.fastjson.JSONObject.toJSONString(source));
			
			// 更新数据库数据
			Map<String,Object> result = saveSalaryInfo(source, pactInfo);
			sb.append("同步成功数：").append(result.get("success"));

			logger.info(sb.toString());
		} catch (Exception e) {
			logger.error("获取工资出错！" + sb.toString() + "\n" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 根据工资信息更新数据库
	 * 
	 * @param source
	 *            原始工资
	 * @param pactCode
	 *            协议号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> saveSalaryInfo(Map<String, Object> source, HroPactInfo pactInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		int success = 0;
		if(source == null || source.isEmpty()){
			result.put("success", success);
			return result;
		}
		
		// 获取接口返回数据
		Map<String, Object> resultData = (Map<String, Object>) source.get("resultData");
		if(resultData == null || resultData.isEmpty()){
			result.put("success", success);
			return result;
		}
		
		List<Map<String, Object>> entities = (List<Map<String, Object>>) resultData.get("entities");
		List<EfSalaryInfo> recordList = new ArrayList<EfSalaryInfo>();
		for (Map<String, Object> entity : entities) {
			if(entity == null){
				continue;
			}
			
			// 将接口中返回字段和数据库映射实体类对应
			EfSalaryInfo salaryInfo = new EfSalaryInfo();
			salaryInfo.setProtocolCode(pactInfo.getPactCode());
			salaryInfo.setCompanyId(pactInfo.getCompanyId());
			if(entity.get("empId")!=null && StringUtils.isNotBlank(entity.get("empId").toString())){
				salaryInfo.setEmpId(Long.parseLong(entity.get("empId").toString()));
			}
			if(entity.get("salaryYm")!=null && StringUtils.isNotBlank(entity.get("salaryYm").toString())){
				salaryInfo.setSalaryYm(entity.get("salaryYm").toString());
			}
			if(entity.get("cityName")!=null && StringUtils.isNotBlank(entity.get("cityName").toString())){
				salaryInfo.setCityName(entity.get("cityName").toString());
			}
			if(entity.get("taxAmount")!=null && StringUtils.isNotBlank(entity.get("taxAmount").toString())){
				salaryInfo.setTaxAmount(new BigDecimal(entity.get("taxAmount").toString()));
			}
			if(entity.get("amount")!=null && StringUtils.isNotBlank(entity.get("amount").toString())){
				salaryInfo.setAmount(new BigDecimal(entity.get("amount").toString()));
			}
			if(entity.get("employeeName")!=null && StringUtils.isNotBlank(entity.get("employeeName").toString())){
				salaryInfo.setEmployeeName(entity.get("employeeName").toString());
			}
			if(entity.get("cardId")!=null && StringUtils.isNotBlank(entity.get("cardId").toString())){
				salaryInfo.setCardId(entity.get("cardId").toString());
			}
			
			try {
				if(entity.get("grantDate")!=null && StringUtils.isNotBlank(entity.get("grantDate").toString())){
					salaryInfo.setGrantDate(JabavaUtil.formatDate(entity.get("grantDate").toString(), "yyyyMMddHHmmss"));
				}
				
			} catch (Exception e) {
				logger.warn("保存工资时日期转换错误！：" + salaryInfo.getEmpId() + "-" + 
						salaryInfo.getSalaryYm() + "\n" + e.getMessage());
			}
			
			recordList.add(salaryInfo);
		}

		if(!recordList.isEmpty()){
			int res = salaryInfoService.insertOrUpdateList(recordList);
			if (res > 0) {
				success = recordList.size();
			}
		}
		
		result.put("success", success);
		return result;
	}
	
}