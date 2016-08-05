package com.jabava.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.jabava.dao.policygroup.PolicyGroupMapper;
import com.jabava.dao.policygroup.PolicyProdRatioMapper;
import com.jabava.dao.policygroup.SbGroupDetailMapper;
import com.jabava.dao.policygroup.SbGroupMapper;
import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.pojo.policygroup.PolicyProdRatio;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.policygroup.SbGroupDetail;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;

/**
 * 
 * 导入 更新政策包数据
 * @version $Id: ImportPolicyGroup.java, 
 * v 0.1 2016年4月14日 上午11:11:19 
 * <pre>
 * @author steven.chen
 * @date 2016年4月14日 上午11:11:19 
 * </pre>
 */
public class ImportPolicyGroupTask {
	
	
	@Autowired
	private SbGroupMapper groupMapper;
	@Autowired
	private SbGroupDetailMapper groupDetailMapper;	
	@Autowired
	private PolicyGroupMapper policyGroupMapper;
	@Autowired
	private PolicyProdRatioMapper policyProdRatioMapper;
	
	private HROFetchService requestService;
	
	private static final Logger logger = Logger.getLogger(ImportPolicyGroupTask.class);
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
    
	public ImportPolicyGroupTask(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}	

	public void execute() throws Exception {
		logger.info(" ImportPolicyGroupTask  Start .................");
		int sum = 0;//统计导入的条数
		JSONArray jsonList = null;
		Map<String, Object> result =null ;	
		JSONArray ratioJsonList = null;
		Map<String, Object> ratioResult =null ;	
		//政策包组合主表
		List<SbGroup> groupList = new ArrayList<SbGroup>();
		//政策包组合详情表
		List<SbGroupDetail> groupDetailList = new ArrayList<SbGroupDetail>();
		//政策包主表
		List<PolicyGroup> policyGroupList = new ArrayList<PolicyGroup>();
		//政策包产品比例主表
		List<PolicyProdRatio> ratioList = new ArrayList<PolicyProdRatio>();
		//政策包导入
		Map<String, Object> parameter = new HashMap<String, Object>();		
		Map<String, Object> data = new HashMap<String, Object>();	
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("pageSize", 99999);
		data.put("page",page);			
		parameter.put("code", "getallpolicygroup");
		data.put("parameter", parameter);	
		try{
			result = requestService.invoke("SqlQuery", data);	
		}catch (Exception e){
			logger.error(" 获取政策包数据出错 ************************* ");
			e.printStackTrace();	
		}	
		if(result!=null ){
			jsonList = TaskUtil.getJSONArray(result);
		}		
		if(jsonList!=null && jsonList.size()>0){
			for(int i=0;i<jsonList.size();i++){
				//每两百条数据导入一次
				if(policyGroupList!=null && policyGroupList.size()>200){
					sum+= policyGroupList.size();
					//执行导入政策包
					importPolicyGroup(policyGroupList);
				}				
				com.alibaba.fastjson.JSONObject json =   (com.alibaba.fastjson.JSONObject) jsonList.get(i);	
				if(json!=null && json.size()>0){
					policyGroupList.add(getPolicyGroup(json));
					if(StringUtils.isNotBlank(json.getString("ID"))){
						if(ratioJsonList!=null){
							ratioJsonList.clear();//清空
						}
						if(ratioResult!=null){
							ratioResult.clear();//清空
						}
						Long policyGroupId = Long.parseLong(json.getString("ID").toString());
						Map<String, Object> parameter4 = new HashMap<String, Object>();		
						Map<String, Object> data4 = new HashMap<String, Object>();	
						Map<String, Object> page4 = new HashMap<String, Object>();
						page4.put("pageSize", 99999);
						data4.put("page",page4);		
						//TODO
						parameter4.put("code", "getRatioByPolicyId");
						parameter4.put("policyGroupId", policyGroupId);
						data4.put("parameter", parameter4);	
						try{
							ratioResult = requestService.invoke("SqlQuery", data4);	
						}catch (Exception e){
							logger.error(" 获取 比例数据出错 ************************* ");
							e.printStackTrace();	
						}
						if(ratioResult!=null ){
							ratioJsonList = TaskUtil.getJSONArray(ratioResult);
						}		
						if(ratioJsonList!=null && ratioJsonList.size()>0){
							for(int j=0;j<ratioJsonList.size();j++){
								//每两百条数据导入一次
								if(ratioList!=null && ratioList.size()>200){
									sum+= ratioList.size();
									//执行导入比例数据
									importPolicyProdRatio(ratioList);
								}				
								com.alibaba.fastjson.JSONObject json2 =   (com.alibaba.fastjson.JSONObject) ratioJsonList.get(j);	
								if(json2!=null && json2.size()>0){
									ratioList.add(getPolicyProdRatio(json2));
								}
							}
							sum+= ratioList.size();
							//执行导入比例数据
							importPolicyProdRatio(ratioList);	
							//TODO
						}
					}
				}
			}
			sum+= policyGroupList.size();
			//执行导入政策包
			importPolicyGroup(policyGroupList);			
		}
		//导入政策组合包主表
		if(jsonList!=null){
			jsonList.clear();//清空
		}
		if(result!=null){
			result.clear();//清空
		}		
		Map<String, Object> parameter2 = new HashMap<String, Object>();		
		Map<String, Object> data2 = new HashMap<String, Object>();	
		Map<String, Object> page2 = new HashMap<String, Object>();
		page2.put("pageSize", 99999);
		data2.put("page",page2);		
		parameter2.put("code", "querySBGroup");
		data2.put("parameter", parameter2);	
		try{
			result = requestService.invoke("SqlQuery", data2);	
		}catch (Exception e){
			logger.error(" 获取政策包组合数据出错 ************************* ");
			e.printStackTrace();	
		}	
		if(result!=null ){
			jsonList = TaskUtil.getJSONArray(result);
		}		
		if(jsonList!=null && jsonList.size()>0){
			for(int i=0;i<jsonList.size();i++){
				//每两百条数据导入一次
				if(groupList!=null && groupList.size()>200){
					sum+= groupList.size();
					//执行导入政策包组合
					importGroup(groupList);
				}				
				com.alibaba.fastjson.JSONObject json =   (com.alibaba.fastjson.JSONObject) jsonList.get(i);	
				if(json!=null && json.size()>0){
					groupList.add(getGroup(json));
				}
			}
			sum+= groupList.size();
			//执行导入政策包组合
			importGroup(groupList);			
		}
		//导入政策组合包详情表
		if(jsonList!=null){
			jsonList.clear();//清空
		}
		if(result!=null){
			result.clear();//清空
		}		
		Map<String, Object> parameter3 = new HashMap<String, Object>();		
		Map<String, Object> data3 = new HashMap<String, Object>();	
		Map<String, Object> page3 = new HashMap<String, Object>();
		page3.put("pageSize", 99999);
		data3.put("page",page3);
		parameter3.put("code", "getAllGroupDetail");
		data3.put("parameter", parameter3);	
		try{
			result = requestService.invoke("SqlQuery", data3);	
		}catch (Exception e){
			logger.error(" 获取政策包组合数据出错 ************************* ");
			e.printStackTrace();	
		}	
		if(result!=null ){
			jsonList = TaskUtil.getJSONArray(result);
		}		
		if(jsonList!=null && jsonList.size()>0){
			for(int i=0;i<jsonList.size();i++){
				//每两百条数据导入一次
				if(groupDetailList!=null && groupDetailList.size()>200){
					sum+= groupDetailList.size();
					//执行导入政策包组合详情
					importGroupDetail(groupDetailList);
				}				
				com.alibaba.fastjson.JSONObject json =   (com.alibaba.fastjson.JSONObject) jsonList.get(i);	
				if(json!=null && json.size()>0){
					groupDetailList.add(getGroupDetail(json));
				}
			}
			sum+= groupDetailList.size();
			//执行导入政策包组合详情
			importGroupDetail(groupDetailList);			
		}
		logger.info(" ImportPolicyGroupTask  End ................. 一共导入更新数据 " +sum+"条");
	}
	
	private void importPolicyProdRatio(List<PolicyProdRatio> ratioList){
		if(ratioList!=null && ratioList.size()>0){
			int sum = policyProdRatioMapper.insertOrUpdateList(ratioList);
			logger.info("本次导入更新  PolicyProdRatio  "+ sum +"条  ratioList "+ratioList.size());	
			ratioList.clear();//清空list
		}
	}
	
	/**
	 * 导入更新政策包组合
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月18日 下午2:42:52 
	 * </pre>
	 *
	 * @param groupList
	 */
	private void importGroup(List<SbGroup> groupList){
		if(groupList!=null && groupList.size()>0){
			int sum = groupMapper.insertOrUpdateList(groupList);
			logger.info("本次导入更新  SbGroup  "+ sum +"条  groupList "+groupList.size());	
			groupList.clear();//清空list
		}
	}
	/**
	 * 政策包组合详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月18日 下午2:43:45 
	 * </pre>
	 *
	 * @param groupDetailList
	 */
	private void importGroupDetail(List<SbGroupDetail> groupDetailList){
		if(groupDetailList!=null && groupDetailList.size()>0){
			int sum = groupDetailMapper.insertOrUpdateList(groupDetailList);
			logger.info("本次导入更新   SbGroupDetail  "+ sum +"条  groupDetailList "+groupDetailList.size());	
			groupDetailList.clear();//清空list
		}
	}
	/**
	 * 导入或者更新政策包数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午2:41:15 
	 * </pre>
	 *
	 * @param policyGroupList
	 */
	private void importPolicyGroup(List<PolicyGroup> policyGroupList){
		
		if(policyGroupList!=null && policyGroupList.size()>0){
			int sum = policyGroupMapper.insertOrUpdateList(policyGroupList);
			logger.info("本次导入更新  PolicyGroup  "+ sum +"条  policyGroupList "+policyGroupList.size());	
			policyGroupList.clear();//清空list
		}
		
	}
	//组装比例表数据
	private PolicyProdRatio getPolicyProdRatio(com.alibaba.fastjson.JSONObject json){
		PolicyProdRatio policyProdRatio = new PolicyProdRatio();
		if(StringUtils.isNotBlank(json.getString("ID"))){
			policyProdRatio.setId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("RATIO_NAME"))){
			policyProdRatio.setRatioName(json.getString("RATIO_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_ID"))){
			policyProdRatio.setPolicyGroupId(Long.valueOf(json.getString("POLICY_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CITY_ID"))){
			policyProdRatio.setCityId(Long.valueOf(json.getString("CITY_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CITY_NAME"))){
			policyProdRatio.setCityName(json.getString("CITY_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PROD_ID"))){
			policyProdRatio.setProdId(Long.valueOf(json.getString("PROD_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			policyProdRatio.setProdName(json.getString("PROD_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("HOUSE_HOLD_TYPE"))){
			policyProdRatio.setHouseHoldType(Integer.valueOf(json.getString("HOUSE_HOLD_TYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO_ID"))){
			policyProdRatio.setCompanyRatioId(Long.valueOf(json.getString("COMPANY_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO"))){
			policyProdRatio.setCompanyRatio(BigDecimal.valueOf(Double.valueOf(json.getString("COMPANY_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_APPEND"))){
			policyProdRatio.setCompanyAppend(BigDecimal.valueOf(Double.valueOf(json.getString("COMPANY_APPEND").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO_ID"))){
			policyProdRatio.setIndividualRatioId(Long.valueOf(json.getString("INDIVIDUAL_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO"))){
			policyProdRatio.setIndividualRatio(BigDecimal.valueOf(Double.valueOf(json.getString("INDIVIDUAL_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_APPEND"))){
			policyProdRatio.setIndividualAppend(BigDecimal.valueOf(Double.valueOf(json.getString("INDIVIDUAL_APPEND").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_PRECISE"))){
			policyProdRatio.setCompanyPrecise(json.getString("COMPANY_PRECISE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_PRECISE"))){
			policyProdRatio.setIndividualPrecise(json.getString("INDIVIDUAL_PRECISE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_CALCULATE_TYPE"))){
			policyProdRatio.setCompanyCalculateType(json.getString("COMPANY_CALCULATE_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_CALCULATE_TYPE"))){
			policyProdRatio.setIndividualCalculateType(json.getString("INDIVIDUAL_CALCULATE_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAY_TYPE"))){
			policyProdRatio.setPayType(Integer.valueOf(json.getString("PAY_TYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PAY_MONTH"))){
			policyProdRatio.setPayMonth(json.getString("PAY_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("MONTH_COMPANY_AMOUNT"))){
			policyProdRatio.setMonthCompanyAmount(BigDecimal.valueOf(Double.valueOf(json.getString("MONTH_COMPANY_AMOUNT").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("MONTH_INDIVIDUAL_AMOUNT"))){
			policyProdRatio.setMonthCompanyAmount(BigDecimal.valueOf(Double.valueOf(json.getString("MONTH_INDIVIDUAL_AMOUNT").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("EFFECTIVE_DATE"))){
			policyProdRatio.setEffectiveDate(json.getString("EFFECTIVE_DATE").toString());			
		}
		if(StringUtils.isNotBlank(json.getString("EXPIRY_DATE"))){
			policyProdRatio.setExpiryDate(json.getString("EXPIRY_DATE").toString());			
		}
		if(StringUtils.isNotBlank(json.getString("EXPIRY_DATE"))){
			policyProdRatio.setExpiryDate(json.getString("EXPIRY_DATE").toString());			
		}
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			policyProdRatio.setIsDeleted(Integer.valueOf(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			policyProdRatio.setCreateBy(Long.valueOf(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){			
			try{
				policyProdRatio.setCreateDt(format2.parse(json.getString("CREATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
		}
		
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			policyProdRatio.setUpdateBy(Long.valueOf(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try{
				policyProdRatio.setUpdateDt(format2.parse(json.getString("UPDATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("MIMIC_BY"))){
			policyProdRatio.setMimicBy(Long.valueOf(json.getString("MIMIC_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROXY_BY"))){
			policyProdRatio.setProxyBy(Long.valueOf(json.getString("PROXY_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("RELATIVE_RATIO_ID"))){
			policyProdRatio.setRelativeRatioId(Long.valueOf(json.getString("RELATIVE_RATIO_ID").toString()));
		}
		return policyProdRatio;
	}
	/**
	 * 组装组合包
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月18日 下午2:09:23 
	 * </pre>
	 *
	 * @param json
	 * @return
	 */
	public SbGroup getGroup(com.alibaba.fastjson.JSONObject json){
		
		SbGroup group = new SbGroup();
		if(StringUtils.isNotBlank(json.getString("ID"))){
			group.setId(Long.parseLong(json.getString("ID").toString()));
		}		
		if(StringUtils.isNotBlank(json.getString("SB_GROUP_NAME"))){
			group.setSbGroupName(json.getString("SB_GROUP_NAME"));
		}
		if(StringUtils.isNotBlank(json.getString("ORG_ID"))){
			group.setOrgId(Long.parseLong(json.getString("ORG_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PRIVATELY"))){
			group.setPrivately(Boolean.valueOf(json.getString("PRIVATELY").toString()));
		}		
		
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			group.setIsDeleted(Integer.valueOf(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			group.setCreateBy(Long.valueOf(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){			
			try{
				group.setCreateDt(format2.parse(json.getString("CREATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
		}
		
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			group.setUpdateBy(Long.valueOf(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try{
				group.setUpdateDt(format2.parse(json.getString("UPDATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("MIMIC_BY"))){
			group.setMimicBy(Long.valueOf(json.getString("MIMIC_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROXY_BY"))){
			group.setProxyBy(Long.valueOf(json.getString("PROXY_BY").toString()));
		}
		return group;
		
	
	}
	/***
	 * 政策组合包详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月18日 下午1:51:23 
	 * </pre>
	 *
	 * @param json
	 * @return
	 */
	public SbGroupDetail getGroupDetail(com.alibaba.fastjson.JSONObject json){
		SbGroupDetail groupDetail = new SbGroupDetail();
		if(StringUtils.isNotBlank(json.getString("ID"))){
			groupDetail.setId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("SB_GROUP_ID"))){
			groupDetail.setSbGroupId(Long.parseLong(json.getString("SB_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_ID"))){
			groupDetail.setPolicyGroupId(Long.parseLong(json.getString("POLICY_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_NAME"))){
			groupDetail.setPolicyGroupName(json.getString("POLICY_GROUP_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("CITY_ID"))){
			groupDetail.setCityId(Long.parseLong(json.getString("CITY_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROD_ID"))){
			groupDetail.setProdId(Long.parseLong(json.getString("PROD_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			groupDetail.setProdName(json.getString("PROD_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("IS_PAYMENT"))){
			groupDetail.setIsPayment(Boolean.valueOf(json.getString("IS_PAYMENT").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_ID"))){
			groupDetail.setBaseId(Long.valueOf(json.getString("BASE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BASE_SCOPE"))){
			groupDetail.setCompanyBaseScope(json.getString("COMPANY_BASE_SCOPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BASE_SCOPE"))){
			groupDetail.setIndividualBaseScope(json.getString("INDIVIDUAL_BASE_SCOPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("RATIO_ID"))){
			groupDetail.setRatioId(Long.valueOf(json.getString("RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("RATIO_NAME"))){
			groupDetail.setRatioName(json.getString("RATIO_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("COM_RATIO"))){
			groupDetail.setComRatio(BigDecimal.valueOf(Double.valueOf(json.getString("COM_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("IND_RATIO"))){
			groupDetail.setIndRatio(BigDecimal.valueOf(Double.valueOf(json.getString("IND_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COM_ADD_AMOUNT"))){
			groupDetail.setComAddAmount(BigDecimal.valueOf(Double.valueOf(json.getString("COM_ADD_AMOUNT").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("IND_ADD_AMOUNT"))){
			groupDetail.setIndAddAmount(BigDecimal.valueOf(Double.valueOf(json.getString("IND_ADD_AMOUNT").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("PAY_TYPE"))){
			groupDetail.setPayType(Integer.valueOf(json.getString("PAY_TYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BASE_GRADE"))){
			groupDetail.setCompanyBaseGrade(json.getString("COMPANY_BASE_GRADE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BASE_GRADE"))){
			groupDetail.setIndividualBaseGrade(json.getString("INDIVIDUAL_BASE_GRADE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO_ID"))){
			
			groupDetail.setCompanyRatioId(Long.valueOf(json.getString("COMPANY_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO_ID"))){
			groupDetail.setIndividualRatioId(Long.valueOf(json.getString("INDIVIDUAL_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			groupDetail.setIsDeleted(Integer.valueOf(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			groupDetail.setCreateBy(Long.valueOf(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){			
			try{
				groupDetail.setCreateDt(format2.parse(json.getString("CREATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
		}
		
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			groupDetail.setUpdateBy(Long.valueOf(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try{
				groupDetail.setUpdateDt(format2.parse(json.getString("UPDATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("MIMIC_BY"))){
			groupDetail.setMimicBy(Long.valueOf(json.getString("MIMIC_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROXY_BY"))){
			groupDetail.setProxyBy(Long.valueOf(json.getString("PROXY_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO"))){
			groupDetail.setCompanyRatio(json.getString("COMPANY_RATIO").toString());
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO"))){
			groupDetail.setIndividualRatio(json.getString("INDIVIDUAL_RATIO").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SERVICE_TYPE"))){
			groupDetail.setServiceType(Long.valueOf(json.getString("SERVICE_TYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("IS_LOAD"))){
			groupDetail.setIsLoad(Boolean.valueOf(json.getString("IS_LOAD").toString()));
		}
		
		return groupDetail;
		
	}
	/**
	 * 组装政策包数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午1:43:45 
	 * </pre>
	 *
	 * @param json
	 */
	private PolicyGroup getPolicyGroup(com.alibaba.fastjson.JSONObject json){
		PolicyGroup policyGroup = new PolicyGroup();
		if(StringUtils.isNotBlank(json.getString("ID"))){
			policyGroup.setId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CITY_CODE"))){
			policyGroup.setCityCode(Integer.parseInt(json.getString("CITY_CODE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_NAME"))){
			policyGroup.setPolicyGroupName(json.getString("POLICY_GROUP_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_CREATETYPE"))){
			policyGroup.setPolicyGroupCreatetype(Integer.parseInt(json.getString("POLICY_GROUP_CREATETYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_TYPE"))){
			policyGroup.setPolicyGroupType(json.getString("POLICY_GROUP_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_VALID_DATE"))){
			try {
				policyGroup.setPolicyValidDate(format.parse(json.getString("POLICY_VALID_DATE").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("parsrDate POLICY_VALID_DATE 出错", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("MANAGE_DAY"))){
			policyGroup.setManageDay(Integer.parseInt(json.getString("MANAGE_DAY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("STOP_DAY"))){
			policyGroup.setStopDay(Integer.parseInt(json.getString("STOP_DAY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("MANAGE_TYPE"))){
			policyGroup.setManageType(json.getString("MANAGE_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("STOP_TYPE"))){
			policyGroup.setStopType(json.getString("STOP_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("FIRST_BACKPAY_TYPE"))){
			policyGroup.setFirstBackpayType(json.getString("FIRST_BACKPAY_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ADD_IS_BACKPAY"))){
			policyGroup.setAddIsBackpay(Boolean.valueOf(json.getString("ADD_IS_BACKPAY").toString()));
		}		
		if(StringUtils.isNotBlank(json.getString("CHANGE_IS_BACKPAY"))){
			policyGroup.setChangeIsBackpay(Boolean.valueOf(json.getString("CHANGE_IS_BACKPAY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ADD_ARCHIVE_ISBACKPAY"))){
			policyGroup.setAddArchiveIsbackpay(Boolean.valueOf(json.getString("ADD_ARCHIVE_ISBACKPAY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CHANGE_ARCHIVE_ISBACKPAY"))){
			policyGroup.setChangeArchiveIsbackpay(Boolean.valueOf(json.getString("CHANGE_ARCHIVE_ISBACKPAY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("SUSPEND_STATUS"))){
			policyGroup.setSuspendStatus(Integer.valueOf(json.getString("SUSPEND_STATUS").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ADD_TYPE"))){
			policyGroup.setAddType(json.getString("ADD_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SUSPEND_TYPE"))){
			policyGroup.setSuspendType(json.getString("SUSPEND_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_CHANGE_MONTH"))){
			policyGroup.setBaseChangeMonth(json.getString("BASE_CHANGE_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			policyGroup.setIsDeleted(Integer.valueOf(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			policyGroup.setCreateBy(Long.valueOf(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){			
			try{
				policyGroup.setCreateDt(format2.parse(json.getString("CREATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
		}
		
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			policyGroup.setUpdateBy(Long.valueOf(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try{
				policyGroup.setUpdateDt(format2.parse(json.getString("UPDATE_DT")));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("解析 CREATE_DT 出错 ", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("MIMIC_BY"))){
			policyGroup.setMimicBy(Long.valueOf(json.getString("MIMIC_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROXY_BY"))){
			policyGroup.setProxyBy(Long.valueOf(json.getString("PROXY_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("IS_CREATE_WEB"))){
			policyGroup.setIsCreateWeb(Boolean.valueOf(json.getString("IS_CREATE_WEB").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("KEYWORD"))){
			policyGroup.setKeyword(json.getString("KEYWORD").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PROPERTY"))){
			policyGroup.setProperty(Integer.valueOf(json.getString("PROPERTY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORG_ID"))){
			policyGroup.setOrgId(Long.valueOf(json.getString("ORG_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("IS_RELATED_EMPLOYEEMENT"))){
			policyGroup.setIsRelatedEmployeement(Integer.valueOf(json.getString("IS_RELATED_EMPLOYEEMENT").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("HOUSEHOLD_TYPE"))){
			policyGroup.setHouseholdType(json.getString("HOUSEHOLD_TYPE").toString());
		}
		return policyGroup;
		
	}
	
}
