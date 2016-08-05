package com.jabava.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;
import com.jabava.dao.hro.BdEmpBaseInfoMapper;
import com.jabava.dao.hro.BdEmpRecInfoMapper;
import com.jabava.dao.hro.BdSbMapper;
import com.jabava.dao.hro.BdSbReportDetailMapper;
import com.jabava.pojo.hro.BdEmpBaseInfo;
import com.jabava.pojo.hro.BdEmpRecInfo;
import com.jabava.pojo.hro.BdSb;
import com.jabava.pojo.hro.BdSbReportDetail;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.service.hro.BdSbService;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;

/**
 * 导入社保公积金信息以及详情信息
 * @author xueping.liu
 *
 */
public class ImportPolicyPackageTask {
	
	@Autowired
	private OutsourcingService outsourcingService;
	
	@Resource
	private BdSbMapper bdSbMapper;   
	
	@Resource
	private BdSbReportDetailMapper bdSbReportDetailMapper;   
	@Resource
	private BdEmpBaseInfoMapper  bdEmpBaseInfoMapper;
	@Resource
	private BdEmpRecInfoMapper  bdEmpRecInfoMapper;
	private HROFetchService requestService;
	private static final Logger logger = Logger.getLogger(ImportOrderTask.class);
	// String server="http://king.ezhiyang.com";
	SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
	JSONArray jsonList = null;
	JSONObject resultData = null;
	
	
	public ImportPolicyPackageTask(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		// String server="http://king.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}
	
	public void execute() throws Exception {
		
		logger.info(" ImportPolicyTask  Start .................");
		//社保公积金
		//List<BdSb> bdSbList = new ArrayList<BdSb>();
		
		//社保公积金详情
		List<BdSbReportDetail> bdSbReportDetailList = new ArrayList<BdSbReportDetail>();
		
		//员工基本信息
		List<BdEmpBaseInfo> hdEmpBaseInfoList = new ArrayList<BdEmpBaseInfo>();	
		//员工信息详情
		List<BdEmpRecInfo> bdEmpRecInfoList = new ArrayList<BdEmpRecInfo>();
		
		List<HroPactInfo> hroPactInfoList= outsourcingService.queryPactInfoList();
		
		if(hroPactInfoList == null ||hroPactInfoList.size()==0){
			logger.info(" 没有可以执行的协议号 ...");
			return ;
			// throw new Exception("没有可以执行的协议号 ...");
		} 
		Map<String, Object> result =null ;
		
	  	 for(HroPactInfo hroPactInfo:hroPactInfoList){
			Map<String, Object> parameter = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();	
			Map<String, Object> page = new HashMap<String, Object>();
			page.put("pageSize", 9999);
			data.put("page",page);
			//parameter.put("PROTOCOL_CODE", "ZY-HP-20160224-0002");
			parameter.put("PROTOCOL_CODE", hroPactInfo.getPactCode());
			parameter.put("code", "querySbDetailInfo");
			data.put("parameter", parameter);			
			result = requestService.invoke("SqlQuery", data);		
			jsonList = TaskUtil.getJSONArray(result);
			System.out.println("jsonArray="+jsonList);
			if(jsonList!=null){
				System.out.println("jsonList.size()====================================="+jsonList.size());
			}
			
			if(jsonList!=null && jsonList.size()>0){
				for(int i=0;i<jsonList.size();i++){
					com.alibaba.fastjson.JSONObject json = (com.alibaba.fastjson.JSONObject) jsonList.get(i);	
					if(json!=null && json.size()>0){
						 json.put("companyId", hroPactInfo.getCompanyId());
						 //HashMap<String,Object> hashMap=getSBAndGJJPojo(json);
						/*//社保公积金
						 bdSbList.add((BdSb) hashMap.get("bdSb"));*/
						 
						//社保公积金详情
						bdSbReportDetailList.add(getSBAndGJJPojo(json));
						
						//员工基本信息
						hdEmpBaseInfoList.add(getHdEmpBaseInfo(json));
						//员工详细信息
						bdEmpRecInfoList.add(getHdEmpRecInfo(json));
					}				

				}
			}	
	  	 }
		
		
		
		
		if (bdSbReportDetailList != null && bdSbReportDetailList.size() > 0) {
			
			// 社保
			
		   /*int res = bdSbMapper.insertSbUpdateList(bdSbList);
			logger.info("新增或者更新社保公积金数     " + res + " listSize "
					+ bdSbList.size());*/
			
			// 社保详情	
			 int res = bdSbReportDetailMapper.insertSbReportDetailUpdateList(bdSbReportDetailList);
				logger.info("新增或者更新社保公积金数     " + res + " listSize "
						+ bdSbReportDetailList.size());
				// 个人基本信息  
				int infoRes = bdEmpBaseInfoMapper
						.insertOrUpdateList(hdEmpBaseInfoList);
				logger.info("新增或者更新个人基本信息    " + infoRes + " listSize "
						+ hdEmpBaseInfoList.size());
				// 个人详细信息
				int recInfoRes = bdEmpRecInfoMapper
						.insertOrUpdateList(bdEmpRecInfoList);
				logger.info("新增或者更新个人详细信息    " + recInfoRes + " listSize "
						+ bdEmpRecInfoList.size());
			
		}
		
		logger.info(" ImportPolicyTask  End .................");
	}
	
	/**
	 * 组装社保公积金&社保公积金详情
	 * @param json
	 * @return
	 */
	public BdSbReportDetail getSBAndGJJPojo(com.alibaba.fastjson.JSONObject json){
		//BdSb bdSb=new BdSb();
		BdSbReportDetail bdSbReportDetail=new BdSbReportDetail();
		//HashMap<String,Object> hashMap=new HashMap<String,Object>();
		bdSbReportDetail.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		
		if(StringUtils.isNotBlank(json.getString("SB_ID"))){//社保ID
			bdSbReportDetail.setSbId(Long.parseLong(json.getString("SB_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){//订单ID
			bdSbReportDetail.setOrderId(Long.parseLong(json.getString("ORDER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_ID"))){//政策包ID
			bdSbReportDetail.setPolicyGroupId(Long.parseLong(json.getString("POLICY_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_TYPE"))){//政策包类型 
			bdSbReportDetail.setPolicyGroupType(Integer.parseInt(json.getString("POLICY_GROUP_TYPE").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_NAME"))){//政策包名称 
			bdSbReportDetail.setPolicyName(json.getString("POLICY_GROUP_NAME"));
		}
		if(StringUtils.isNotBlank(json.getString("HANDLE_PARTY_ID"))){//办理方ID
			bdSbReportDetail.setHandlePartyId(Long.parseLong(json.getString("HANDLE_PARTY_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORG_ID"))){//企业ID
			bdSbReportDetail.setOrgId(Long.parseLong(json.getString("HANDLE_PARTY_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("EMPLOYEE_ID"))){//雇员ID
			bdSbReportDetail.setEmployeeId( Long.parseLong(json.getString("EMPLOYEE_ID").toString()));
		}
		
		if(StringUtils.isNotBlank(json.getString("REC_INFO_ID"))){//雇员详情ID
			 
			bdSbReportDetail.setRecInfoId( Long.parseLong(json.getString("REC_INFO_ID").toString()));
		}
		 
		if(StringUtils.isNotBlank(json.getString("SB_REPORT_DETAIL_ID"))){//社保明细ID
			bdSbReportDetail.setSbReportDetailId(Long.parseLong(json.getString("SB_REPORT_DETAIL_ID").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("SB_REPORT_ID"))){//社保报表ID
			bdSbReportDetail.setSbReportId(Long.parseLong(json.getString("SB_REPORT_ID").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("PROD_ID"))){//产品ID
			bdSbReportDetail.setProdId(Long.parseLong(json.getString("PROD_ID").toString()) ); 
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){//产品名称 、
			bdSbReportDetail.setProdName(json.getString("PROD_NAME") );
		}
		if(StringUtils.isNotBlank(json.getString("REPORT_MONTH"))){//报表月--年月
			bdSbReportDetail.setReportMonth( json.getString("REPORT_MONTH"));
		}
		if(StringUtils.isNotBlank(json.getString("SB_MONTH"))){//社保月
			bdSbReportDetail.setSbMonth(json.getString("SB_MONTH") );
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BASE"))){// 企业基数
			bdSbReportDetail.setCompanyBase(new BigDecimal(json.getString("COMPANY_BASE").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BASE"))){// 个人基数
			bdSbReportDetail.setIndividualBase( new BigDecimal(json.getString("INDIVIDUAL_BASE").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO"))){// 企业比例
			bdSbReportDetail.setCompanyRatio(new BigDecimal(json.getString("COMPANY_RATIO").toString())  );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO"))){// 个人比例
			bdSbReportDetail.setIndividualRatio(new BigDecimal(json.getString("INDIVIDUAL_RATIO").toString())  );
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_SUM"))){// 企业金额
			bdSbReportDetail.setCompanySum( new BigDecimal(json.getString("COMPANY_SUM").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_SUM"))){// 个人金额
			bdSbReportDetail.setIndividualSum( new BigDecimal(json.getString("INDIVIDUAL_SUM").toString()) );
		}
		
		if(StringUtils.isNotBlank(json.getString("COMPANY_APPEND"))){// 企业附加额
			bdSbReportDetail.setCompanyAppend( new BigDecimal(json.getString("COMPANY_APPEND").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_APPEND"))){// 个人附加额
			bdSbReportDetail.setIndividualAppend(new BigDecimal(json.getString("INDIVIDUAL_APPEND").toString())  );
		}
		if(StringUtils.isNotBlank(json.getString("ALL_SUM"))){//总金额 
			bdSbReportDetail.setAllSum(new BigDecimal(json.getString("ALL_SUM").toString())  );
		}
		if(StringUtils.isNotBlank(json.getString("PAY_TYPE"))){// 状态：1.汇缴；2.补缴
			bdSbReportDetail.setPayType(json.getString("PAY_TYPE").toString() );
		}
//		if(StringUtils.isNotBlank(json.getString("LOCK_STATUS"))){// 状态：1.未锁定；2.已锁定
//			bdSbReportDetail.setLockStatus(json.getString("LOCK_STATUS").toString() );
//		}
//		if(StringUtils.isNotBlank(json.getString("LOCK_BY"))){  
//			bdSbReportDetail.setLockBy( Long.parseLong(json.getString("LOCK_BY").toString()) );
//		}
		
//		if(StringUtils.isNotBlank(json.getString("LOCK_DT"))){ 
//			bdSbReportDetail.setLockDt( );
//		}
//		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){ 
//			bdSbReportDetail.setIsDeleted( );
//		}
//		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){ 
//			bdSbReportDetail.setCreateBy( );
//		}
//		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){ 
//			bdSbReportDetail.setCreateDt( );
//		}
//		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){ 
//			 
//		}
//		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){  
//			 
//		}
		if(StringUtils.isNotBlank(json.getString("BACKPAY_STYLE"))){// 补缴类型：1补缴，2补差
			bdSbReportDetail.setBackpayStyle(json.getString("BACKPAY_STYLE") );
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_ACCURATE_SUM"))){// 企业精确金额
			bdSbReportDetail.setCompanyAccurateSum( new BigDecimal(json.getString("COMPANY_ACCURATE_SUM").toString()) );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_ACCURATE_SUM"))){// 个人精确金额
			bdSbReportDetail.setIndividualAccurateSum(new BigDecimal(json.getString("INDIVIDUAL_ACCURATE_SUM").toString())  );
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_PAY_TYPE"))){// 
			bdSbReportDetail.setCompanyPayType( Integer.parseInt(json.getString("COMPANY_PAY_TYPE")));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_PAY_TYPE"))){// 
			bdSbReportDetail.setIndividualPayType(Integer.parseInt(json.getString("INDIVIDUAL_PAY_TYPE")) );
		}
		if(StringUtils.isNotBlank(json.getString("SEND_ORG_ID"))){// 
			bdSbReportDetail.setSendOrgId(Long.parseLong(json.getString("SEND_ORG_ID").toString()));
		}
		
		if(StringUtils.isNotBlank(json.getString("RECEIVE_ORG_ID"))){  
			bdSbReportDetail.setReceiveOrgId(Long.parseLong(json.getString("RECEIVE_ORG_ID").toString()) );
		}
//		if(StringUtils.isNotBlank(json.getString("NOT_SBGJJ"))){  
//			bdSbReportDetail.setNotSbgjj( );
//		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_COMPENSATION_SUM"))){// 企业补差金额
			bdSbReportDetail.setCompanyCompensationSum(new BigDecimal(json.getString("COMPANY_COMPENSATION_SUM").toString())   );
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_COMPENSATION_SUM"))){// 个人补差金额
			bdSbReportDetail.setIndividualCompensationSum(new BigDecimal(json.getString("INDIVIDUAL_COMPENSATION_SUM").toString())  );
		}
		
		//hashMap.put("bdSb", bdSb);
		//hashMap.put("bdSbReportDetail", bdSbReportDetail);
		
		return bdSbReportDetail;
		
	}
	
	public static void main(String[] args) {
		SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
		 
			//System.out.println(format2.parse("20151223143707"));
			System.out.println(new java.util.Date(20151223143707L));
	       format2.format(new Date(20151223143707L));
	       System.out.println(  format2.format( 20151223143707L ));
	       
	       
	       Date date = new Date();
	       Long time = date.getTime();
	       System.out.println(time);

	       Date d = new Date(time);
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	       System.out.println(sdf.format(d));
	}
	//组装个人基本信息
		private BdEmpBaseInfo getHdEmpBaseInfo(com.alibaba.fastjson.JSONObject json){
			BdEmpBaseInfo bdEmpBaseInfo = new BdEmpBaseInfo();
			if(StringUtils.isNotBlank(json.getString("BD_EMP_BASE_INFO_ID"))){
				bdEmpBaseInfo.setBaseInfoId(Long.parseLong(json.getString("BD_EMP_BASE_INFO_ID").toString()));
			}		
			
			bdEmpBaseInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
			
			if(StringUtils.isNotBlank(json.getString("BASE_MARRIAGE_STATUS"))){
				bdEmpBaseInfo.setMarriageStatus(json.getString("BASE_MARRIAGE_STATUS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_MOBILE"))){
				bdEmpBaseInfo.setMobile(json.getString("BASE_MOBILE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_NATIONALITY"))){
				bdEmpBaseInfo.setNationality(json.getString("BASE_NATIONALITY").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
				bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_PHONE"))){
				bdEmpBaseInfo.setPhone(json.getString("BASE_PHONE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_PROXY_BY"))){
				bdEmpBaseInfo.setProxyBy(Long.parseLong(json.getString("BASE_PROXY_BY").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_REGISTERED_ADDRESS"))){
				bdEmpBaseInfo.setRegisteredAddress(json.getString("BASE_REGISTERED_ADDRESS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_RESIDENCE_ADDRESS"))){
				bdEmpBaseInfo.setResidenceAddress(json.getString("BASE_RESIDENCE_ADDRESS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_RESIDENCE_ZIP"))){
				bdEmpBaseInfo.setResidenceZip(json.getString("BASE_RESIDENCE_ZIP").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_RESIDENT_TYPE"))){
				bdEmpBaseInfo.setResidentType(json.getString("BASE_RESIDENT_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_UPDATE_BY"))){
				bdEmpBaseInfo.setUpdateBy(Long.parseLong(json.getString("BASE_UPDATE_BY").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_UPDATE_DT"))){
				try {
					bdEmpBaseInfo.setUpdateDt(format.parse(json.getString("BASE_UPDATE_DT").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
				
			}
			if(StringUtils.isNotBlank(json.getString("BASE_WORK_ADDRESS"))){
				bdEmpBaseInfo.setWorkAddress(Integer.parseInt(json.getString("BASE_WORK_ADDRESS").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("WORK_CITY"))){
				bdEmpBaseInfo.setWorkCity(json.getString("WORK_CITY").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_EMPLOYEE_NAME"))){
				bdEmpBaseInfo.setEmployeeName(json.getString("BASE_EMPLOYEE_NAME").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_ADDRESS_BEGIN_DATE"))){
				try {
					bdEmpBaseInfo.setAddressBeginDate(format.parse(json.getString("BASE_ADDRESS_BEGIN_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			if(StringUtils.isNotBlank(json.getString("BASE_BIRTHDAY"))){
				try {
					bdEmpBaseInfo.setBirthday(format2.parse(json.getString("BASE_BIRTHDAY").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			if(StringUtils.isNotBlank(json.getString("BASE_CARD_ID"))){
				bdEmpBaseInfo.setCardId(json.getString("BASE_CARD_ID").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_CARD_TYPE"))){
				bdEmpBaseInfo.setCardType(json.getString("BASE_CARD_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_COUNTRY_ID"))){
				bdEmpBaseInfo.setCountryId(Long.parseLong(json.getString("BASE_COUNTRY_ID").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_CREATE_BY"))){
				bdEmpBaseInfo.setCreateBy(Long.parseLong(json.getString("BASE_CREATE_BY").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_CREATE_DT"))){
				try {
					bdEmpBaseInfo.setCreateDt(format.parse(json.getString("BASE_CREATE_DT").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			if(StringUtils.isNotBlank(json.getString("BASE_EDUCATION_LEVEL"))){
				bdEmpBaseInfo.setEducationLevel(json.getString("BASE_EDUCATION_LEVEL").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_EMAIL"))){
				bdEmpBaseInfo.setEmail(json.getString("BASE_EMAIL").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_FAMILY_INSURANCE_ADDRESS"))){
				bdEmpBaseInfo.setFamilyInsuranceAddress(json.getString("BASE_FAMILY_INSURANCE_ADDRESS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_GENDER"))){
				bdEmpBaseInfo.setGender(json.getString("BASE_GENDER").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_MARRIAGE_STATUS"))){
				bdEmpBaseInfo.setMarriageStatus(json.getString("BASE_MARRIAGE_STATUS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
				bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
				bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
			}		
			return bdEmpBaseInfo;		
		}
		//组装员工基本信息
		private BdEmpRecInfo getHdEmpRecInfo(com.alibaba.fastjson.JSONObject json){
			BdEmpRecInfo bdEmpRecInfo = new BdEmpRecInfo();
			if(StringUtils.isNotBlank(json.getString("REC_INFO_ID"))){
				bdEmpRecInfo.setRecInfoId(Long.parseLong(json.getString("REC_INFO_ID").toString()));
			}	
			if(StringUtils.isNotBlank(json.getString("EMPLOYEE_SYSTEM_ID"))){
				bdEmpRecInfo.setEmployeeSystemId(Long.parseLong(json.getString("EMPLOYEE_SYSTEM_ID").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("EMPLOYEE_CLIENT_ID"))){
				bdEmpRecInfo.setEmployeeClientId(json.getString("EMPLOYEE_CLIENT_ID").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_CLIENT_ID"))){
				bdEmpRecInfo.setClientId(Long.parseLong(json.getString("REC_CLIENT_ID").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("REC_HIRE_TYPE"))){
				bdEmpRecInfo.setHireType(json.getString("REC_HIRE_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_HIRE_DATE"))){
				try {
					bdEmpRecInfo.setHireDate(format.parse(json.getString("REC_HIRE_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}		
			}
			if(StringUtils.isNotBlank(json.getString("REC_HIRE_REMARK"))){
				bdEmpRecInfo.setHireRemark(json.getString("REC_HIRE_REMARK").toString());
			}		
			if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_TYPE"))){
				bdEmpRecInfo.setDimissionType(json.getString("REC_DIMISSION_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_DATE"))){
				try {
					bdEmpRecInfo.setDimissionDate(format.parse(json.getString("REC_DIMISSION_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_REMARK"))){
				bdEmpRecInfo.setDimissionRemark(json.getString("REC_DIMISSION_REMARK").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_HIRE_STATUS"))){
				bdEmpRecInfo.setHireStatus(json.getString("REC_HIRE_STATUS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ADDRESS"))){
				bdEmpRecInfo.setResidenceAddress(json.getString("REC_RESIDENCE_ADDRESS").toString());
			}
			
			bdEmpRecInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
			
			if(StringUtils.isNotBlank(json.getString("REC_MARRIAGE_STATUS"))){
				bdEmpRecInfo.setMarriageStatus(json.getString("REC_MARRIAGE_STATUS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_MOBILE"))){
				bdEmpRecInfo.setMobile(json.getString("REC_MOBILE").toString());
			}		
			if(StringUtils.isNotBlank(json.getString("REC_PHONE"))){
				bdEmpRecInfo.setPhone(json.getString("REC_PHONE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_PROXY_BY"))){
				bdEmpRecInfo.setProxyBy(Long.parseLong(json.getString("REC_PROXY_BY").toString()));
			}		
			if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ADDRESS"))){
				bdEmpRecInfo.setResidenceAddress(json.getString("REC_RESIDENCE_ADDRESS").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ZIP"))){
				bdEmpRecInfo.setResidenceZip(json.getString("REC_RESIDENCE_ZIP").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_RESIDENT_TYPE"))){
				bdEmpRecInfo.setResidentType(json.getString("REC_RESIDENT_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_UPDATE_BY"))){
				bdEmpRecInfo.setUpdateBy(Long.parseLong(json.getString("REC_UPDATE_BY").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("REC_UPDATE_DT"))){
				try {
					bdEmpRecInfo.setUpdateDt(format.parse(json.getString("REC_UPDATE_DT").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
				
			}
			if(StringUtils.isNotBlank(json.getString("REC_WORK_ADDRESS"))){
				bdEmpRecInfo.setWorkAddress(Integer.parseInt(json.getString("REC_WORK_ADDRESS").toString()));
			}	
			if(StringUtils.isNotBlank(json.getString("REC_CREATE_BY"))){
				bdEmpRecInfo.setCreateBy(Long.parseLong(json.getString("REC_CREATE_BY").toString()));
			}
			if(StringUtils.isNotBlank(json.getString("REC_CREATE_DT"))){
				try {
					bdEmpRecInfo.setCreateDt(format.parse(json.getString("BASE_CREATE_DT").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			if(StringUtils.isNotBlank(json.getString("REC_EDUCATION_LEVEL"))){
				bdEmpRecInfo.setEducationLevel(json.getString("REC_EDUCATION_LEVEL").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_EMAIL"))){
				bdEmpRecInfo.setEmail(json.getString("REC_EMAIL").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_RESIDENT_TYPE"))){
				bdEmpRecInfo.setResidentType(json.getString("REC_RESIDENT_TYPE").toString());
			}
			if(StringUtils.isNotBlank(json.getString("REC_CREATE_ORG_ID"))){
				bdEmpRecInfo.setCreateOrgId(Long.parseLong(json.getString("REC_CREATE_ORG_ID").toString()));
			}		
		
			return bdEmpRecInfo;
			
		}

}
