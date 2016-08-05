package com.jabava.utils.employee.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.IDCard;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;

public class PersonPost implements IPersonImport, IPersonExport {
	public static Logger log = Logger.getLogger(PersonPost.class);

	private EhrPersonMapper personMapper;
	private EhrBaseDataMapper baseDataMapper;
	private EhrOrganizationMapper organizationMapper;
	private EhrCommonDataMapper commonDataMapper;
	private Row row;
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	private static HashMap<String,Integer> columnMapper = new HashMap<String,Integer>();
	
	static {
		columnMapper.put("工号", 1);
		columnMapper.put("姓名", 2);
		columnMapper.put("证件号码", 3);
		columnMapper.put("部门名称", 4);
		columnMapper.put("岗位名称", 5);
		columnMapper.put("成本中心", 6);
		columnMapper.put("用工性质", 7);
		columnMapper.put("职级", 8);
		columnMapper.put("是否关键岗位", 9);
		columnMapper.put("汇报对象", 10);
	}

	private String getColumnString(Row row,String columnName){
		Integer column = columnMapper.get(columnName);
		return JabavaUtil.getCellStr(row.getCell(column));
	}
	
	public PersonPost(EhrPersonMapper personMapper,EhrBaseDataMapper baseDatamapper,EhrOrganizationMapper organizationMapper,EhrCommonDataMapper commonDataMapper) {
		this.personMapper = personMapper;
		this.baseDataMapper = baseDatamapper;
		this.organizationMapper = organizationMapper;
		this.commonDataMapper = commonDataMapper;
		
		formatDate.setLenient(false);
	}
	
	public Map <String,Object> importPerson(int num, Sheet sheet,Map<String, String> map, EhrUser user) throws Exception {
		int totalRows = sheet.getLastRowNum();
		String sheetName = sheet.getSheetName();
		log.error("map-size 222:"+map.size());
		
		HashMap <String,Object> chuckList = new HashMap <String,Object>();
		
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {continue;}
			
			EhrPerson person = new EhrPerson();
			
			// 工号数据
			String jobNumber = getColumnString(row,"工号");
			
			if("".equals(jobNumber)){//第一行的第一个必填项为空直接break
//				 throw new JabavaServiceException(sheetName,i,"工号信息数据错误",jobNumber);
				break; 
			} 
			person.setJobNumber(jobNumber);
			
			// 员工姓名数据
			String name = getColumnString(row,"姓名");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"员工姓名数据错误",name);
			}
			person.setEmployeeName(name);
			
			// 证件号码
			String certId = getColumnString(row,"证件号码").trim().toUpperCase();
			if("".equals(certId)){
				throw new JabavaServiceException(sheetName,i,"证件号码数据错误",certId);
			}
			if(!IDCard.IDCardValidate(certId.toUpperCase())){
				throw new JabavaServiceException(sheetName,i,"身份证号码数据错误",certId);
			}
			person.setCertId(certId.toUpperCase());
			
			// 部门名称数据
			String orgName = getColumnString(row,"部门名称");
			List<EhrOrganization> orgs = organizationMapper.selectOrgByName(user.getCompanyId(), orgName);
//			if(orgs == null || orgs.isEmpty()){
//				throw new JabavaServiceException(sheetName,i,"部门名称数据错误",orgName);
//			}
			
			if(orgs != null &&!orgs.isEmpty()){
				person.setOrganizationId(orgs.get(0).getOrganizationId());
			}
		
			
			// 岗位名称数据
			String postionName = getColumnString(row,"岗位名称");
			List<EhrBaseData> list = null;
//			if (list == null || list.isEmpty()) {
//				throw new JabavaServiceException(sheetName,i,"本公司无此岗位名称，请联系管理员到系统中维护相关数据!",postionName);
//			}
			if(!"".equals(postionName)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_POSITION, postionName);
				if (list != null &&! list.isEmpty()) {
					person.setPostId( list.get(0).getBaseDataId());
				}
			}
			
			
			
			// 成本中心
			String costCenter = getColumnString(row,"成本中心");
			if(!"".equals(costCenter)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_COST_CENTER, costCenter);
				if (list != null && !list.isEmpty()) {
					 
					person.setCostId(list.get(0).getBaseDataId());
				}
				
			}
			
			// 用工性质数据
			String employeeType = getColumnString(row,"用工性质");
			//if(!"".equals(employeeType)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EMPLOYEE_TYPE, employeeType);
				if (list!= null && !list.isEmpty()) {
					 
					person.setEmploymentType((list.get(0).getBaseDataCode()));
				}
			//}
//			
			
			// 职级数据
			String rank = getColumnString(row,"职级");
			if(!"".equals(rank)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_LEVEL, rank);
				if (list != null && !list.isEmpty()) {
					 
					person.setRankId(list.get(0).getBaseDataId());
				}
			}

			// 是否关键岗位
			String keypos = getColumnString(row,"是否关键岗位");
			if(!"".equals(keypos)){
				
				if(keypos.equals("是")){
					person.setKeyPerson(1);
					
				}else if(keypos.equals("否")){
					
					person.setKeyPerson(0);
				}
			}
			
			// 汇报对象
			String reportObject  = getColumnString(row,"汇报对象");
			
			//汇报对象不为空 根据excel中填写的汇报对象，从数据表中查询此人的personId
			if(StringUtils.isNotBlank(reportObject)){
				person.setReportObject(reportObject);
				EhrPerson params=  new EhrPerson();
				params.setCompanyId(user.getCompanyId());
				params.setEmployeeName(reportObject);
				List<EhrPerson> repList = personMapper.searchPersonByEmployeeNameAndCompanyId(params);
				if(repList!=null && repList.size()>0){
					person.setReportObject(repList.get(0).getPersonId().toString());
				}else{
					throw new JabavaServiceException(sheetName,i,"汇报对象不存在",reportObject);
				}
			}
			
			
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());
			//person.setStatus((byte) 0);
			person.setCompanyId(user.getCompanyId());
			person.setCreateDate(new Date());
			person.setCreateUserId(user.getUserId());
			person.setCreateUserName(user.getUserName());
			
			String personId = map.get(certId);
			String personIdForJobNumber = map.get(jobNumber);
			if (personId != null) {
				
				
				//从员工表里查询工号是否存在    若存在要查证这个工号是不是这个人的
				if(personIdForJobNumber!=null){
					 if(Long.parseLong(personId)!=Long.parseLong(personIdForJobNumber)){
						 throw new JabavaServiceException(sheetName,i,"证件号码为"+certId+"的工号错误！",jobNumber);
					 }else{
						 person.setPersonId(Long.parseLong(personId));
					 }
				}else{
					person.setPersonId(Long.parseLong(personId));
				}
				
				
			}else{
				//从员工表里查询工号是否存在 如果存在 error
				
				if(personIdForJobNumber!=null){
					throw new JabavaServiceException(sheetName,i,"工号数据重复！",jobNumber);
				}else{
					
					person.setPersonId(null);
				}
				
				
			}

			// 更新当前身份证号码的重复次数
			Integer count = duplicatedMap.get(certId);
			if(count == null){
				duplicatedMap.put(certId, 1);
			}else{
				duplicatedMap.put(certId, count+1);
			}
			
			chuckList.put(certId, person);
		}

		return chuckList;
	}

	@Override
	public List<Map<String, Object>> exportPerson(List<EhrPerson> persons,EhrUser user) throws Exception {
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> data = null;
		int index = 1;
		
		//构建证件类型Map
		Map<String,String> certTypeMap = new HashMap<String,String>();
		List<EhrCommonData> certTypeList = commonDataMapper.listByCommonDataType(CommonDataConstants.COMMON_DATA_CERT_TYPE);
		if (certTypeList != null && !certTypeList.isEmpty()) {
			for(EhrCommonData cd : certTypeList){
				certTypeMap.put(cd.getCommonDataCode(), cd.getCommonDataName());
			}
		}
		
		for (EhrPerson person : persons) {
			data = new HashMap<String,Object>();
			data.put("index", index++);
			data.put("jobNumber", person.getJobNumber());
			data.put("employeeName", person.getEmployeeName());
			if(person.getCertType() == null || !certTypeMap.containsKey(person.getCertType().toString())){
				data.put("certType", "其他");
			}else{
				data.put("certType", certTypeMap.get(person.getCertType().toString()));
//				if (person.getCertType()==0) {// 证件类型
//					data.put("certType", "身份证");
//				} else if (person.getCertType()==0) {
//					data.put("certType", "军官证");
//				} else if (person.getCertType()==0) {
//					data.put("certType", "护照");
//				}
			}
			data.put("certId", person.getCertId());
			if(person.getCompanyId() != null){
				EhrOrganization org = organizationMapper.selectByPrimaryKey(person.getOrganizationId());
				if(org != null){
					data.put("org", org.getOrganizationName());
				}else{
					data.put("org", JabavaUtil.BLANK);
				}
			}else{
				data.put("org", JabavaUtil.BLANK);
			}
			if(person.getPostId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getPostId());
				if(baseData != null){
					data.put("post", baseData.getBaseDataName());
				}else{
					data.put("post", JabavaUtil.BLANK);
				}
			}else{
				data.put("post", JabavaUtil.BLANK);
			}
			if(person.getCostId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getCostId());
				if(baseData != null){
					data.put("cost", baseData.getBaseDataName());
				}else{
					data.put("cost", JabavaUtil.BLANK);
				}
			}else{
				data.put("cost", JabavaUtil.BLANK);
			}
			if(JabavaUtil.isNumeric(person.getEmploymentType()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getEmploymentType()));
				if(baseData != null){
					data.put("employmentType", baseData.getBaseDataName());
				}else{
					data.put("employmentType", person.getEmploymentType());
				}
			}else{
				data.put("employmentType", person.getEmploymentType());
			}
			if(person.getRankId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getRankId());
				if(baseData != null){
					data.put("rank", baseData.getBaseDataName());
				}else{
					data.put("rank", JabavaUtil.BLANK);
				}
			}else{
				data.put("rank", JabavaUtil.BLANK);
			}
			if(person.getKeyPerson() != null){
				if (person.getKeyPerson() == 0) { 
					data.put("keyPerson", "否");
				} else {
					data.put("keyPerson", "是");
				}
			}else{
				data.put("keyPerson", JabavaUtil.BLANK);
			}
			data.put("reportObject", person.getReportObject());
			datas.add(data);
		}
		
		return datas;
	}
	
	private HashMap <String,Integer> duplicatedMap = new HashMap <String,Integer>();
	public HashMap<String, Integer> duplicatedCertId() {
		return duplicatedMap;
	}

	@Override
	public Map<String, Object> importPersonSecurityProfile(int num,
			Sheet sheet, Map<String, Long> map, EhrUser user,
			List<Map<String, Object>> currentSecurityConfig,
			List<Map<String, Object>> currentGongjijinProducts)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
