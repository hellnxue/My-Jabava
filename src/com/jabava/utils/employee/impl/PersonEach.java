package com.jabava.utils.employee.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.IDCard;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;

public class PersonEach implements IPersonImport, IPersonExport {
	public static Logger log = Logger.getLogger(PersonEach.class);

	private EhrPersonMapper personMapper;
	private EhrBaseDataMapper baseDataMapper;
	private EhrCommonDataMapper commonDataMapper;
	
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

	private static HashMap<String,Integer> columnMapper = new HashMap<String,Integer>();
	
	static{
		columnMapper.put("工号",1);
		columnMapper.put("姓名",2);
		columnMapper.put("性别",3);
		columnMapper.put("是否外籍",4);
		columnMapper.put("参加工作时间",5);
		columnMapper.put("证件类型",6);
		columnMapper.put("证件号",7);
		columnMapper.put("开户行",8);
		columnMapper.put("开户支行",9);
		columnMapper.put("工资卡号",10);
		columnMapper.put("最高学历",11);
		columnMapper.put("最高学位",12);
		columnMapper.put("海外留学",13);
		columnMapper.put("户口所在地",14);
		columnMapper.put("户籍类型",15);
		columnMapper.put("籍贯",16);
		columnMapper.put("民族",17);
		columnMapper.put("手机",18);
		columnMapper.put("联系电话",19);
		columnMapper.put("电子邮件",20);
		columnMapper.put("现居住地",21);
		columnMapper.put("家庭住址",22);
		columnMapper.put("邮编",23);
		columnMapper.put("档案存放地",24);
		columnMapper.put("出生日期",25);
		columnMapper.put("婚姻状况",26);
		columnMapper.put("电子简历",27);
		
		columnMapper.put("入职时间",28);
		columnMapper.put("转正时间",29);
		columnMapper.put("工作地",30);
		columnMapper.put("发薪地",31);
		columnMapper.put("社保缴纳地",32);
		columnMapper.put("停发标志",33);
		columnMapper.put("员工状态",34);
		columnMapper.put("团队",35);
	}
	
	HashMap <String,Integer> duplicatedMap = new HashMap <String,Integer>();
	public HashMap <String,Integer> duplicatedCertId(){
		return duplicatedMap;
	}
	
	private String getColumnString(Row row,String columnName){
		Integer column = columnMapper.get(columnName);
		return JabavaUtil.getCellStr(row.getCell(column));
	}
	
	public PersonEach(EhrPersonMapper personMapper, EhrBaseDataMapper baseDataMapper, EhrCommonDataMapper commonDataMapper) {
		this.personMapper = personMapper;
		this.baseDataMapper = baseDataMapper;
		this.commonDataMapper = commonDataMapper;
		
		formatDate.setLenient(false);
	}
	
	/**
	 * 导入个人信息
	 * @param num            sheet代码
	 * @param Sheet          excel sheet
	 * @param existingPerson 系统中已经有的人
	 * @param user           操作用户
	 */
	public Map <String,Object> importPerson(int num, Sheet sheet,Map<String, String> map, EhrUser user) throws Exception {
		int totalRows = sheet.getLastRowNum();
		Row row;
		
		//构建证件类型Map(Name:Code)
		Map<String,String> certTypeMap = new HashMap<String,String>();
		List<EhrCommonData> certTypeList = commonDataMapper.listByCommonDataType(CommonDataConstants.COMMON_DATA_CERT_TYPE);
		if (certTypeList != null && !certTypeList.isEmpty()) {
			for(EhrCommonData cd : certTypeList){
				certTypeMap.put(cd.getCommonDataName(), cd.getCommonDataCode());
			}
		}
		
		String sheetName = sheet.getSheetName();
		log.error("导入的sheet: " + sheetName + " 总行数:" + totalRows);
		log.error("map-size 111:"+map.size());
		
		HashMap <String,Object> chuckList = new HashMap <String,Object>();
		
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {continue;}
			EhrPerson person = new EhrPerson();
			
			// 1. 工号校验
			String jobNumber = getColumnString(row,"工号");
			
			if("".equals(jobNumber)){//第一行的第一个必填项为空直接break
				 //throw new JabavaServiceException(sheetName,i,"工号信息数据错误",jobNumber);
				 break;
			}
			
			person.setJobNumber(jobNumber);

			// 2. 姓名校验
			String name = getColumnString(row,"姓名");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"姓名数据错误",name);
			}
			person.setEmployeeName(name);
			
			// 性别数据处理
			String sex = getColumnString(row,"性别");
			List<EhrBaseData> list = null;			
			
			if(!"".equals(sex)){
				if(sex.equals("男")){
					person.setSex(1);
				}else if(sex.equals("女")){
					person.setSex(2);
				}
			}
			
			// 是否外籍人员
			String nationality = getColumnString(row,"是否外籍");
			if(!"".equals(nationality)){
				if(nationality.equals("是")){
					person.setIsForeign(1);
				}else if(nationality.equals("否")){
					person.setIsForeign(0);
				}
			}
			
			
			
			// 参加工作时间数据
			Date date = null;
			String firstJobDate = getColumnString(row,"参加工作时间");
			
			if(!"".equals(firstJobDate)) {
				try{
					date = formatDate.parse(firstJobDate);
					person.setFirstjobDate(date);
				}catch(Exception e){
					//throw new JabavaServiceException(sheetName,i,"参加工作时间数据错误",firstJobDate);
				}
			}
			
			
			// 证件类型数据
			String certType = getColumnString(row,"证件类型");
			Integer byteCertType = null;
			
			if("".equals(certType.trim())){
				throw new JabavaServiceException(sheetName,i,"证件类型错误",certType);
			}else {
				if (!certTypeMap.containsKey(certType)) {
					throw new JabavaServiceException(sheetName,i,"基础数据中证件类型数据错误",certType);
				}else{
					byteCertType = Integer.parseInt(certTypeMap.get(certType));
				}
				
			}
			
			person.setCertType(byteCertType);
			
			// 证件号码校验,只有在证件类型是身份证的时候出才校验身份证的格式 
			String certId = getColumnString(row,"证件号").toUpperCase();
			if("".equals(certId.trim())){
				throw new JabavaServiceException(sheetName,i,"证件号错误",certId);
			}else{
				if(byteCertType!=null && CommonDataConstants.COMMON_DATA_CERT_TYPE_CERTID.equals(byteCertType.toString())){
					if(!IDCard.IDCardValidate(certId.toUpperCase())){
						throw new JabavaServiceException(sheetName,i,"身份证号码数据错误",certId);
					}
				}
			}
			
			person.setCertId(certId);// 证件号码
			
			// 开户行
			String bankName = getColumnString(row,"开户行");
//			if("".equals(bankName)){
//				//throw new JabavaServiceException(sheetName,i,"开户行数据错误",bankName);
//			}
			person.setBankName(bankName);
			
			// 开户支行
			String subBank = getColumnString(row,"开户支行");
//			if("".equals(subBank)){
//				//throw new JabavaServiceException(sheetName,i,"开户支行数据错误",subBank);
//			}
			person.setSubbank(subBank);
			
			// 工资卡号
			// 工资卡号未做格式校验
			String salaryCart = getColumnString(row,"工资卡号");
//			if("".equals(salaryCart)){
//				//throw new JabavaServiceException(sheetName,i,"工资卡号数据错误",salaryCart);
//			}
			person.setSalaryCard(salaryCart);
			
			// 最高学历 不填可以，填错了要报错
			String degree = getColumnString(row,"最高学历");
			
			if(!"".equals(degree)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_DEGREE, degree);
				if (list != null&&!list.isEmpty()) {
				 
					EhrBaseData data = list.get(0);
					person.setDegree(data.getBaseDataCode());
					
				} 
			}
			
			
			
			// 最高学位，不填可以，填错了要报错
			String education = getColumnString(row,"最高学位");
			
			
			if(!"".equals(education)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EDUCATION, education);
				if (list != null && !list.isEmpty()) {
					 
					EhrBaseData data = list.get(0);
					person.setEducation(data.getBaseDataCode());
				} 
			}
			
			//是否海外留学
			String studyAbroat= getColumnString(row,"海外留学");
			if(!studyAbroat.equals("")){
				if(studyAbroat.equals("是")){
					person.setStudyAbroad(new Integer(1).byteValue());
				}else{
					person.setStudyAbroad(new Integer(0).byteValue());
				}
			}
			
			
			
			 
			String registerPlace = getColumnString(row,"户口所在地");
//			if("".equals(registerPlace)){
//			}
			person.setRegisterLocation(registerPlace);
			
			// 户籍类型-户口类型
			String registerType = getColumnString(row,"户籍类型");
			if(!"".equals(registerType)){
				
				if(registerType.equals("本地城镇")){
					
					person.setRegisterType(1);
				}else if(registerType.equals("本地农村")){
					
					person.setRegisterType(2);
				}else if(registerType.equals("外地城镇")){
					
					person.setRegisterType(3);
				}else if(registerType.equals("外地农村")){
					
					person.setRegisterType(4);
				}
			}
			

			
			// 籍贯数据校验
			String originPlace = getColumnString(row,"籍贯");
//			if("".equals(originPlace)){
//			//	throw new JabavaServiceException(sheetName,i,"籍贯数据错误",originPlace);
//			}
			person.setOriginPlace(originPlace);
			
			// 民族数据校验
			String ethicGroup = getColumnString(row,"民族");
			if(!"".equals(ethicGroup)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_ETHIC_GROUP, ethicGroup);
				if (list != null&&!list.isEmpty()) {
					 
					person.setNationality(ethicGroup);
					
				} 
			}
			
			//手机数据
			String mobilePhone = getColumnString(row,"手机");
			Pattern p = Pattern.compile("^(?:13\\d|14\\d|15\\d|17\\d|18\\d)-?\\d{5}(\\d{3}|\\*{3})$");
			
			if("".equals(mobilePhone.trim())){
				throw new JabavaServiceException(sheetName,i,"手机号码数据错误",mobilePhone);
			}
			
			Matcher m = p.matcher(mobilePhone);
			if(!m.matches()){
				throw new JabavaServiceException(sheetName,i,"手机号码数据错误",mobilePhone);
			}
			person.setMobile(mobilePhone);
			
			// 联系电话数据
			String telephone = getColumnString(row,"联系电话");
//			if("".equals(telephone)){
//				throw new JabavaServiceException(sheetName,i,"电话数据错误",telephone);
//			}
			person.setPhone(telephone);
			
			// 电子邮件数据
			String email = getColumnString(row,"电子邮件");
			if("".equals(email.trim())){
				// 不填也可以
			}else{
				Pattern pattern =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
				m = pattern.matcher(email);
				if(!m.matches()){
					throw new JabavaServiceException(sheetName,i,"电子邮件数据错误",email);
				}
				person.setEmail(email);
			}
			
			// 居住地信息
			String contactAddress = getColumnString(row,"现居住地");
			if("".equals(contactAddress)){
				//throw new JabavaServiceException(sheetName,i,"现居住地信息数据错误",contactAddress);
			}
			person.setContactAddress(contactAddress);
			
			// 家庭住址信息
			String familyAddress = getColumnString(row,"家庭住址");
			if("".equals(familyAddress)){
				//throw new JabavaServiceException(sheetName,i,"家庭住址信息",familyAddress);
			}
			person.setFamilyAddress(familyAddress);
			// 邮编数据错误
			String postCode = getColumnString(row,"邮编");
			if("".equals(postCode)){
				//throw new JabavaServiceException(sheetName,i,"邮编数据错误",postCode);
			}
			person.setPostCode(postCode);
			
			// 档案存放地数据错误
			String fileLocation = getColumnString(row,"档案存放地");
			//if("".equals(fileLocation)){
				//throw new JabavaServiceException(sheetName,i,"档案存放地数据错误",fileLocation);
			//}
			person.setFileLocation(fileLocation);
			
			// 出生日期
			Date birthDate = null;
			String strBirthDate = getColumnString(row,"出生日期");
			
			if(!"".equals(strBirthDate)) {
				try{
					birthDate = formatDate.parse(strBirthDate);
					person.setBirthDate(birthDate);
				}catch(Exception e){
					//throw new JabavaServiceException(sheetName,i,"出生日期数据错误",strBirthDate);
				}
			}
			
			
			// 婚姻状况
			String marrageStatus = getColumnString(row,"婚姻状况");
			if(!"".equals(marrageStatus)){
				
				if(marrageStatus.equals("已婚")){
					person.setMarital(2);
				}else if(marrageStatus.equals("未婚")){
					person.setMarital(1);
				}
			}
			
			
			// 电子简历
			String resume = getColumnString(row,"电子简历");
			if("".equals(resume)){
				//throw new JabavaServiceException(sheetName,i,"电子简历数据错误",resume);
			}else{
				person.setResume(resume);
			}
			
			// 入职时间
			Date entryDate = null;
			String strEntryDate = getColumnString(row,"入职时间");
			
			if(!"".equals(strEntryDate)) {
				try{
					log.error("入职时间:" + strEntryDate);
					entryDate = formatDate.parse(strEntryDate);
					person.setEntryDate(entryDate);
				}catch(Exception e){
					throw new JabavaServiceException(sheetName,i,"入职时间数据错误",strEntryDate);
				}
			}
			

			
			// 转正时间
			Date formalDate = null;
			String strFormalDate = getColumnString(row,"转正时间");
			if(!"".equals(strFormalDate)) {
				try{
					log.error("转正时间:" + formalDate);
					formalDate = formatDate.parse(strFormalDate);
					person.setPositiveDate(formalDate);
				}catch(Exception e){
					throw new JabavaServiceException(sheetName,i,"转正时间数据错误",strFormalDate);
				}
			}
			
			
			// 工作地
			String workingCity = getColumnString(row,"工作地");
			if(!"".equals(workingCity)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CITY, workingCity);
				if (list != null&&!list.isEmpty()) {
					person.setWorkLocation(list.get(0).getBaseDataCode());
				}
			}
			
			
			// 发薪地
			String payrollLocation = getColumnString(row,"发薪地");
			if(!"".equals(payrollLocation)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CITY, payrollLocation);
				if (list != null&&!list.isEmpty()) {
					person.setPayrollLocation(list.get(0).getBaseDataCode());
				}
			}
			
			
			// 社保缴纳地
			String securityLocation = getColumnString(row,"社保缴纳地");
			if(!"".equals(securityLocation)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CITY, securityLocation);
				if (list != null&&!list.isEmpty()){
					person.setSocialSecurityLocation(list.get(0).getBaseDataCode());
				}
			}
			
			
			// 停发标志
			String payrollFlag = getColumnString(row,"停发标志");
			if(!"".equals(payrollFlag)){
				
				if(payrollFlag.equals("停发")){
					person.setIsPayrollFlag((byte) 1);
					
				}else if(payrollFlag.equals("未停发")){
					person.setIsPayrollFlag((byte) 0);
				}
			}
				
				
			
			
			// 员工状态
			String employeeStatus = getColumnString(row,"员工状态");
			
			if(!"".equals(employeeStatus)){
				
				if(employeeStatus.equals("在职")){
					
					person.setStatus(1);
				}else if(employeeStatus.equals("离职")){
					
					person.setStatus(2);
				}else if(employeeStatus.equals("停职")){
					
					person.setStatus(3);
				}else if(employeeStatus.equals("退休")){
					
					person.setStatus(4);
				}
				else if(employeeStatus.equals("再入职")){
					
					person.setStatus(5);
				}
			}
				
				
			
			// 团队
			String team = getColumnString(row,"团队");
			if(!"".equals(team)){
				list = baseDataMapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_TEAM, team);
				if (list != null&&!list.isEmpty()){
					person.setTeam(list.get(0).getBaseDataId());
				}
			}
				
			
			
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());
			person.setCompanyId(user.getCompanyId());
			person.setCreateDate(new Date());
			person.setCreateUserId(user.getUserId());
			person.setCreateUserName(user.getUserName());
			
			// 用身份证区别，有则更新无则创建  顺便判断工号是否在表中唯一
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
			
			chuckList.put(certId,person);
		}
		
		return chuckList;
	}
	
	public List<Map<String,Object>> exportPerson(List<EhrPerson> persons, EhrUser user)throws Exception {
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
			if(person.getSex() != null){
				if (person.getSex() == 1) {// 性别
					data.put("sex", "男");
				} else {
					data.put("sex", "女");
				}
			}else{
				data.put("sex", JabavaUtil.BLANK);
			}
			if(person.getCertType() == null || !certTypeMap.containsKey(person.getCertType().toString())){
				data.put("certType", "其它");
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
			if (person.getIsForeign() != null) {
				if (person.getIsForeign()==0) {// 是否外籍
					data.put("isForeign", "否");
				} else {
					data.put("isForeign", "是");
				}
			}else {
				data.put("isForeign", "是");
			}
			if (person.getFirstjobDate() != null) {
				data.put("firstjobDate", formatDate.format(person.getFirstjobDate()));
			}else{
				data.put("firstjobDate", person.getFirstjobDate());
			}
			if(JabavaUtil.isNumeric(person.getBankName()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getBankName()));
				if(baseData != null){
					data.put("bankName", baseData.getBaseDataName());
				}else{
					data.put("bankName", person.getBankName());
				}
			}else{
				data.put("bankName", person.getBankName());
			}
			if(JabavaUtil.isNumeric(person.getSubbank()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getSubbank()));
				if(baseData != null){
					data.put("subbank", baseData.getBaseDataName());
				}else{
					data.put("subbank", person.getSubbank());
				}
			}else{
				data.put("subbank", person.getSubbank());
			}
			data.put("salaryCard", person.getSalaryCard());
			data.put("degree", person.getDegree());
			data.put("education", person.getEducation());
			if (person.getStudyAbroad()!=null) { 
				if (person.getStudyAbroad()==0) { 
					data.put("studyAbroad", "城镇");
				} else {
					data.put("studyAbroad", "是");
				}
			} else {
				data.put("studyAbroad", JabavaUtil.BLANK);
			}
			data.put("registerLocation", person.getRegisterLocation());
			if (person.getStudyAbroad()!=null) { 
				if (person.getStudyAbroad()==0) { 
					data.put("registerType", "城镇");
				} else {
					data.put("registerType", "农业");
				}
			} else {
				data.put("registerType", JabavaUtil.BLANK);
			}
			data.put("originPlace", person.getOriginPlace());
			data.put("nationality", person.getNationality());
			data.put("mobile", person.getMobile());
			data.put("phone", person.getPhone());
			data.put("email", person.getEmail());
			data.put("contactAddress", person.getContactAddress());
			data.put("familyAddress", person.getFamilyAddress());
			data.put("postCode", person.getPostCode());
			data.put("fileLocation", person.getFileLocation());
			if (person.getBirthDate() != null) {
				data.put("birthDate", formatDate.format(person.getBirthDate()));
			}else{
				data.put("birthDate", person.getBirthDate());
			}
			if (person.getMarital()!=null) { 
				if (person.getMarital()==1) { 
					data.put("marital", "已婚");
				} else if (person.getMarital()==2) { 
					data.put("marital", "离异");
				} else{
					data.put("marital", "未婚");
				}
			} else{
				data.put("marital", "未婚");
			}
			datas.add(data);
		}
		return datas;
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
