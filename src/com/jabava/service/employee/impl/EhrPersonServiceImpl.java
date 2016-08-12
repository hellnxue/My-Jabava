package com.jabava.service.employee.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.jabava.common.exception.JabavaServiceException;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.dao.employee.EhrAssessMapper;
import com.jabava.dao.employee.EhrCertificateMapper;
import com.jabava.dao.employee.EhrContractMapper;
import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.dao.employee.EhrEducationMapper;
import com.jabava.dao.employee.EhrJobpostMapper;
import com.jabava.dao.employee.EhrProjectMapper;
import com.jabava.dao.employee.EhrRelationMapper;
import com.jabava.dao.employee.EhrResumeMapper;
import com.jabava.dao.employee.EhrRewardsMapper;
import com.jabava.dao.employee.EhrTrainMapper;
import com.jabava.dao.employee.EhrTrialMapper;
import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.pojo.manage.EhrUserPersonPowerValue;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.common.ICommonDataService;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.JabavaStringUtils;
import com.jabava.utils.Page;
import com.jabava.utils.Tools;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;
import com.jabava.utils.employee.impl.PersonBase;
import com.jabava.utils.employee.impl.PersonEach;
import com.jabava.utils.employee.impl.PersonLeft;
import com.jabava.utils.employee.impl.PersonPost;
import com.jabava.utils.enums.PersonEnum;
import com.jabava.utils.excel.ExcelUtil;
import com.jabava.utils.mobile.MobileValidHelp;
import com.service.provider.MobileService;

@Service("ehrPersonService")
public class EhrPersonServiceImpl implements EhrPersonService {
	private static final Logger logger = LoggerFactory.getLogger(EhrPersonServiceImpl.class);	
	
	@Resource
	private EhrPersonMapper personMapper;
	@Resource
	private EhrOrganizationMapper orgMapper;
	@Resource
	private EhrBaseDataMapper baseDataMapper;
	@Autowired
	private EhrCommonDataMapper commonDataMapper;
	@Resource
	private EhrContractMapper contractMapper;
	@Resource
	private EhrUserMapper userMapper;
	@Resource
	private EhrEducationMapper educationMapper;
	@Resource
	private EhrResumeMapper resumeMapper;
	@Resource
	private EhrProjectMapper projectMapper;
	@Resource
	private EhrCertificateMapper certificateMapper;
	@Resource
	private EhrTrainMapper trainMapper;
	@Resource
	private EhrRelationMapper relationMapper;
	@Resource
	private EhrTrialMapper trialMapper;
	@Resource
	private EhrAssessMapper assessMapper;
	@Resource
	private EhrRewardsMapper rewardsMapper;
	@Resource
	private EhrAttendanceMapper attendanceMapper;
	@Resource
	private EhrDimissionMapper leftMapper;
	@Resource
	private EhrJobpostMapper jobPostMapper;
	@Resource
	private EhrOrganizationMapper organizationMapper;
	@Resource
	private EhrCompanyMapper companyMapper;
	
	@Autowired
	private CenterUserClientService centerUserClientService;
	@Autowired
	private MobileService mobileService;//usercenter提供的发送短信的接口
	@Resource
	private IEhrRoleService roleService;
	
	@Resource
	private EhrJobpostService jobpostService;
	
	@Resource
	private EhrTableFieldDefService tableFieldDefService;
	
	@Resource
	private EhrBaseDataMapper dataMapper;

	@Override
	public List<Map<String, Object>> searchPerson(Map<String, Object> map, EhrUser user,
			Integer start, Integer length, String search, String order,
			String according, int isNumeric, Page<Map<String, Object>> page,IBaseDataService baseDataService,ICommonDataService commonDataService)
			throws Exception {
		String where = " and  " + getPersonPowerSqlStr(user.getUserId(), "", 1) + "";
		map.put("where", where);
		map.put("according", according);
		map.put("search", search);
		map.put("order", order);
		map.put("page", page);
		
		//处理自定义字段&自定义字段的数据
		Map<String, Object> customMap = new HashMap<String, Object>();
		customMap.put("companyId", user.getCompanyId());
		//所有的自定义字段&数据
		List<EhrTableFieldDef> customFieldList=tableFieldDefService.selectCustomFieldAndData(user.getCompanyId(),customMap);
	//	System.out.println(JSON.toJSON(customFieldList));
		//员工列表
		List<Map<String, Object>> personList=personMapper.searchEhrPersonPage(map);
		
		//最高学历
		Map<String, Object> degreeMap=baseDataService.getBaseDataMap(user.getCompanyId(),BaseDataConstants.BASE_DATA_DEGREE, null);
		
		//最高学位
		Map<String, Object> educationMap=baseDataService.getBaseDataMap(user.getCompanyId(),BaseDataConstants.BASE_DATA_EDUCATION, null);
		
		//开户行
		Map<String, Object> levelMap=baseDataService.getBaseDataMap(user.getCompanyId(),BaseDataConstants.BASE_DATA_LEVEL, null);
		
		//政治面貌
		Map<String, Object> politicsStatusMap=baseDataService.getBaseDataMap(user.getCompanyId(),BaseDataConstants.BASE_DATA_POLITICS_STATUS, null);
		
		//证件类型
		 Map<String, Object> certTypeMap=commonDataService.getByCommonDataTypeMap(CommonDataConstants.COMMON_DATA_CERT_TYPE);
		
		
		for(Map<String, Object> pmap:personList){
			Long personId=(Long) pmap.get("person_id");
			
			//处理关联基础数据code的字段值--------------------
			
			String degreeCode=  (String) pmap.get("degree");				//最高学历
			String educationCode=  (String) pmap.get("education");			//最高学位
			String bankNameCode=  (String) pmap.get("bank_name");			//银行
			String politicsStatusCode=  (String) pmap.get("politics_status");//政治面貌
			Integer certTypeCode=  (Integer) pmap.get("cert_type");		 //证件类型
			
			if(degreeCode!=null&&!degreeCode.equals ("")){
				pmap.put("degree", degreeMap.get(degreeCode));
			}
			
			if(educationCode!=null&&!educationCode.equals ("")){
				pmap.put("education", educationMap.get(degreeCode));
			}
			
			if(bankNameCode!=null&&!bankNameCode.equals ("")){
				pmap.put("bank_name", levelMap.get(degreeCode));
			}
			
			if(politicsStatusCode!=null&&!politicsStatusCode.equals ("")){
				pmap.put("politics_status", politicsStatusMap.get(degreeCode));
			}
			
			if(certTypeCode!=null){
				pmap.put("cert_type", certTypeMap.get(certTypeCode.toString()));
			}
			
			
			//将N个自定义字段 添加到每个人的行中
			for(EhrTableFieldDef tableFieldDef:customFieldList){
				//自定义字段的数据
				Map<String, Object> customData=tableFieldDef.getCustomMeta();
				String value="";
				if(customData!=null){
					String customPersonId=(String) customData.get("keyValue");
					if(customPersonId!=null &&customPersonId.equals(personId.toString())){
						value=(String) customData.get("value");
						
						//有参照项的根据code取name
						if(tableFieldDef.getRefId()!=null){
							 List<EhrBaseData> baseDataList=dataMapper.selectByType(user.getCompanyId(), tableFieldDef.getRefId());//参照项的基础数据
							 for(EhrBaseData baseData:baseDataList){
								 if(value.equals(baseData.getBaseDataCode())){
									 value=baseData.getBaseDataName();
								 }
								 
							 }
						}
					}
					 
				}
				pmap.put(tableFieldDef.getColumnName(), value);//拼自定字段列名+列值
			}
			
			
			 
		}
		
		return personList;
	}
	

	public String getPersonPowerSqlStr(Long userId, String prefix,
			int functionId) throws Exception {
		if (!prefix.equals(""))
			prefix += ".";
		String result = "";
		List<EhrUserBusinessPower> list = personMapper.userBusinessPower(
				userId, functionId);
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				EhrUserBusinessPower mdl = list.get(i);
				List<EhrUserPersonPowerValue> items = personMapper
						.getPowerValue(mdl.getUserPersonPowerId());
				if (mdl.getFieldType().intValue() == 1) {
					EhrPersonField personMdl = personMapper.getPersonField(mdl
							.getFieldId());
					if (personMdl.getFieldType().intValue() >= 4) {
						if (!items.isEmpty()) {
							result += " " + prefix + personMdl.getFieldName()
									+ " in(";
							for (int j = 0; j < items.size(); j++) {
								if (j == 0) {
									result += Tools.strToStr(items.get(j)
											.getFieldKey());
								} else {
									result += ","
											+ Tools.strToStr(items.get(j)
													.getFieldKey());
								}
							}
							result += ")";
						}
					} else {
						if (!items.isEmpty()) {
							result += " " + prefix + personMdl.getFieldName();
							if (mdl.getOperateType() == 1) {
								result += "="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 2) {
								result += ">"
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 3) {
								result += "<"
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 4) {
								result += ">="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 5) {
								result += "<="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 6) {
								result += " like "
										+ Tools.strToStr("%"
												+ mdl.getFieldValue());
								;
							} else if (mdl.getOperateType() == 7) {
								result += " like "
										+ Tools.strToStr(mdl.getFieldValue()
												+ "%");
								;
							} else if (mdl.getOperateType() == 8) {
								result += " like "
										+ Tools.strToStr("%"
												+ mdl.getFieldValue() + "%");
								;
							} else if (mdl.getOperateType() == 9) {
								result += " in(" + mdl.getFieldValue() + ")";
							}
						}
					}
				} else if (mdl.getFieldType().intValue() == 2
						&& !items.isEmpty()) {
					result += " and";
				} else if (mdl.getFieldType().intValue() == 3
						&& !items.isEmpty()) {
					result += " or";
				} else if (mdl.getFieldType().intValue() == 4
						&& !items.isEmpty()) {
					result += "(";
				} else if (mdl.getFieldType().intValue() == 5
						&& !items.isEmpty()) {
					result += ")";
				}
			}
		}
		if (result.trim().equals(""))
			return " 1=1";
		return "(" + result + ")";
	}

	@Override
	public List<EhrBaseData> searchBaseData() {
		List<EhrBaseData> list = null;
		list = personMapper.searchBaseData();
		return list;
	}

	@Override
	public boolean deletePerson(Long personId) throws Exception {
		boolean result = false;
		int code = personMapper.isDeletePerson(personId, 1);
		userMapper.deleteUser(personId, 1);
		result = (1 == code);
		return result;
	}

	@Override
	public Map<String, Object> addPerson(EhrPerson person, EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		person.setLastModifyDate(new Date());
		person.setLastModifyUserId(user.getUserId());
		person.setLastModifyUserName(user.getUserName());
		if(person.getCertType() == null || StringUtils.isEmpty(person.getCertType().toString())){
			person.setCertType(Integer.valueOf(CommonDataConstants.COMMON_DATA_CERT_TYPE_CERTID));
		}
		
		String password = Tools.getInitialPassword();
		
		
		if(StringUtils.isEmpty(person.getMobile())){
			throw new JabavaServiceException("手机号为空");
		}
		
		int mobileCount = personMapper.fetchMobileCount(user.getCompany().getCompanyId(), person.getMobile());
		if(mobileCount > 0){
			throw new JabavaServiceException("手机号已存在");
		}
		
		if(!StringUtils.isEmpty(person.getEmail())){
				int emailCount = personMapper.fetchEmailCount(user.getCompany().getCompanyId(), person.getEmail());
			if(emailCount > 0){
				throw new JabavaServiceException("邮箱已存在");
			}
		}
		
		String certId = person.getCertId();
		if(certId == null || "".equals(certId.trim())){
			throw new JabavaServiceException("身份证为空");
		}else{
			int certIdCount = personMapper.fetchCertIdCount(user.getCompany().getCompanyId(), certId);
			if(certIdCount > 0){
				throw new JabavaServiceException("身份证号码已经存在");
			}
		}
		
		person.setStatus(1);
		person.setCompanyId(user.getCompanyId());
		if(person.getOrganizationId() == null){
			EhrOrganization topOrg = organizationMapper.findTopOrganization(user.getCompanyId());
			person.setOrganizationId(topOrg.getOrganizationId());
		}
		person.setCreateUserId(user.getUserId());
		person.setCreateUserName(user.getUserName());
		person.setCreateDate(new Date());
		person.setPersonPassword(Tools.encryptPassword(password));
		
		personMapper.insertSelective(person);
		addUser(person,user,password);
		
		if(person.getIsOpen() == 1){
			int result = sendMsgAndEmail(person, user, password);
			if(result == 3){
				map.put("msg", "密码无法通过邮件或信息发出");
				map.put("success", false);
				return map;
			}
		}
		
		map.put("personId", person.getPersonId());
		map.put("msg", "员工添加成功！");
		map.put("success", true);
		return map;
	}
	
	private Long addUser(EhrPerson person,EhrUser loginUser, String password) throws Exception{
		EhrUser user = new EhrUser();
		user.setUserName(person.getEmployeeName());
		user.setMailAddress(person.getEmail());
		user.setIsValid(Byte.valueOf(person.getIsOpen().toString()));
		user.setLastModifyDate(person.getLastModifyDate());
		user.setLastModifyUserId(person.getLastModifyUserId());
		user.setLastModifyUserName(person.getLastModifyUserName());

		user.setUserCode(person.getMobile());
		user.setMobile(person.getMobile());
		user.setFailtureTime(0);
		user.setLoginTime(0);
		user.setUserType((byte)3);
		user.setSex(1);
		user.setIsLocked((byte)0);
		user.setPassword(person.getPersonPassword());
		user.setLastChangePasswordDate(person.getLastModifyDate());
		user.setCompanyId(person.getCompanyId());
		user.setCreateDate(person.getCreateDate());
		user.setCreateUserId(person.getCreateUserId());
		user.setCreateUserName(person.getCreateUserName());
		userMapper.insertSelective(user);
		//roleService.addCommonRoleUser(user);
		personMapper.insertUserPerson(user.getUserId(), person.getPersonId());

		if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
			//用户中心开通
			//Map<String,Object> rr = centerUserClientService.openUser(user,person,password);
			user.setCompany(loginUser.getCompany());
			//Map<String,Object> rr = centerUserClientService.registerPersonalUser(user,person,password);
			Map<String,Object> rr = centerUserClientService.openUser(user,password);
			if("false".equals(rr.get("success").toString())){
				throw new JabavaServiceException(rr.get("msg").toString());
			}
		}
		
		return user.getUserId();
	}
	
	public int sendMsgAndEmail(EhrPerson person, EhrUser user, String password) throws Exception{
		int returnValue = 0;
		try{
			returnValue = 1;
			
			// 发送提示邮件
			String emailSubject = "您在Jabava的帐号开通啦~";
			String emailTemplate = "{name}, 您好,<br><br>"
					+ "{company}{admin}通过智阳HR Saas（Jabava）系统邀请您填写个人信息资料。<br>"
					+ "您的用户名为:{account},登录密码为:{password},请尽快登录({url})补全资料。<br><br><br>"
					+ "祝您工作愉快!<br><br>"
					+ "Jabava系统邮件组<br><br>"
					+ "******本邮件由系统自动发送，请勿直接回复。******";
			
			emailTemplate = emailTemplate.replace("{name}", person.getEmployeeName());
			emailTemplate = emailTemplate.replace("{company}", user.getCompany().getCompanyName());
			emailTemplate = emailTemplate.replace("{admin}", user.getUserName());
			emailTemplate = emailTemplate.replace("{account}", person.getMobile());
			emailTemplate = emailTemplate.replace("{password}", password);
			emailTemplate = emailTemplate.replace("{url}", JabavaPropertyCofigurer.getProperty("JABAVA_URL_SHORT")+person.getPersonId());
			Tools.mailSend(person.getEmail(), emailSubject, emailTemplate, null);
			
			// 发送提示短信
			String smsTemplate = "您好!{admin}通过Jabava系统邀请您填写个人信息。" +"您的用户名为 {account},密码为：{password}，请尽快登录{url} 补全资料。";
			smsTemplate = smsTemplate.replace("{admin}", user.getUserName());
			smsTemplate = smsTemplate.replace("{account}", person.getMobile());
			smsTemplate = smsTemplate.replace("{password}", password);
			smsTemplate = smsTemplate.replace("{url}", JabavaPropertyCofigurer.getProperty("JABAVA_URL_SHORT")+person.getPersonId());
			MobileValidHelp.sendMsg(person.getMobile(), smsTemplate);
			String res = mobileService.sendMessage(person.getMobile(), smsTemplate);
			if("1".equals(res)){
				returnValue = 2;
			}else{
				returnValue = 3;
			}
			
		} catch (Exception ex) {
			returnValue = 3;
			ex.printStackTrace();
		}
		return returnValue;
	}
	
	@Override
	public String sendMessage(Long personId,EhrUser user) throws Exception{
		int res = 0;
		String password = JabavaStringUtils.getRandomNum(6);	//生成的密码
		
		EhrPerson person = this.getByPersonId(personId);
		if(person == null){
			return "未找到该员工";
		}
		
		EhrUser u = new EhrUser();
		u.setUserCode(person.getMobile());
		u.setMobile(person.getMobile());
		u.setMailAddress(person.getEmail());
		u.setUserName(person.getEmployeeName());
		u.setCompanyId(person.getCompanyId());
		u.setLastModifyDate(new Date());
		u.setLastModifyUserId(user.getUserId());
		u.setLastModifyUserName(user.getUserName());
		u.setCreateDate(new Date());
		u.setCreateUserId(user.getUserId());
		u.setCreateUserName(user.getUserName());
		u.setLastChangePasswordDate(new Date());
		u.setFailtureTime(0);//登陆失败次数
		u.setLoginTime(0);
		u.setIsValid((byte) 1);//有效
		u.setIsLocked((byte) 0);//是否被锁定 
		u.setPassword(Tools.encryptPassword(password));
		u.setUserType((byte)3);//普通用户
		u.setFlag(2);//普通用户
		u.setIsDeleted(0);
		
		//判断该用户存在不存在
	    Long userId = this.getUserId(personId);					
		if(userId == null){//用户不存在就新增	
			u.setMailAddress(person.getEmail());
			u.setMobile(person.getMobile());
			userMapper.insertSelective(u);
			personMapper.insertUserPerson(u.getUserId(),personId);
			
			//开通用户
			//Map<String,Object> rr = centerUserClientService.openUser(u, person, passWord);
			u.setCompany(user.getCompany());
			//Map<String,Object> rr = centerUserClientService.registerPersonalUser(u, person, password);
			Map<String,Object> rr = centerUserClientService.openUser(u, password);
			if("false".equals(rr.get("success").toString())){
				throw new JabavaServiceException(rr.get("msg").toString());
			}
		}else{//存在则更新密码
//			u.setUserId(userId);
//			userMapper.updateByPrimaryKeySelective(u);
//			
//			//更新用户
//			Map<String,Object> rr = centerUserClientService.updateUser(u);
//			if("false".equals(rr.get("success").toString())){
//				throw new JabavaServiceException(rr.get("msg").toString());
//			}
			//更新密码
			Map<String,Object> rr = centerUserClientService.resetPassword(u, password);
			if("false".equals(rr.get("success").toString())){
				throw new JabavaServiceException(rr.get("msg").toString());
			}
		}
			
//						ReturnS returnS = centerUserService.isExistUser(u.getUserCode(), null);
//						if (returnS != null && returnS.getSuccess()) {// 如果用户中心已经存在该用户,更新用户的密码
//							ReturnS ucResult = centerUserService
//									.updatePassword(u.getUserCode(), passWord);
//							if (ucResult.getSuccess()) {
//								logger.info(" 邀请员工完善个人信息,修改用户中心用户密码成功");
//							} else {
//								logger.info(" 邀请员工完善个人信息,修改用户中心用户密码失败 失败原因："+ucResult.getMsg());
//							}
//						} else {// 不存在就注册该用户
//							CenterUserInfo centerUser = new CenterUserInfo();
//							centerUser.setOrgName(user.getCompany()
//									.getCompanyName()); // 企业名称
//							centerUser.setName(u.getUserName()); // 用户名称
//							centerUser.setLoginName(u.getUserCode());// 登录名称
//							centerUser.setPassword(passWord); // 原文
//							// 用户在当前系统的唯一标识(对应Jabava系统的userId)
//							centerUser.setIdentity(u.getUserId().toString());
//							centerUser.setEmail(u.getMailAddress());
//							centerUser.setMobile(u.getMobile());
//
//							centerUser.setChannel("Jabava");
//							centerUser.setUserType(2); // 2 个人用户
//							centerUser.setSystemId(Long
//									.valueOf(JabavaPropertyCofigurer
//											.getSystemId()));
//
//							ReturnS r = centerUserService
//									.registerUser(centerUser);
//							if (r.getSuccess()) {
//								logger.info(" 邀请员工完善个人信息,注册到用户中心成功");
//							} else {
//								logger.info(" 邀请员工完善个人信息,注册到用户中心失败 失败原因："+r.getMsg());
//							}
//
//						}


		if(StringUtils.isBlank(person.getMobile()) && StringUtils.isBlank(person.getEmail())){
			return "没有找到手机号码和Email地址, 无法发送短信和Email";
		}
		
		// 如果邮箱不为空，发送邮件
		if(StringUtils.isNotBlank(person.getEmail())){
			String emailSubject = "请完善您的个人信息~";
			String emailTemplate = "{name}, 您好,<br><br>"
					+ "{company}{admin}通过智阳HR Saas（Jabava）您的用户名为:{account},登录密码为:{password} 系统邀请您登录({url}) 填写个人信息资料。<br>"							
					+ "祝您工作愉快!<br><br>"
					+ "Jabava系统邮件组<br><br>"
					+ "****本邮件由系统自动发送，请勿直接回复。***";
			emailTemplate = emailTemplate.replace("{name}", person.getEmployeeName());
			emailTemplate = emailTemplate.replace("{company}", user.getCompany().getCompanyName());
			emailTemplate = emailTemplate.replace("{admin}", user.getUserName());
			emailTemplate = emailTemplate.replace("{account}", person.getMobile());	
			emailTemplate = emailTemplate.replace("{password}", password);
			emailTemplate = emailTemplate.replace("{url}", JabavaPropertyCofigurer.getProperty("JABAVA_URL_SHORT")+personId);
			try {
				Tools.mailSend(person.getEmail(), emailSubject, emailTemplate, null);
			} catch (Exception e) {
				res = 0;
				logger.error(" 邮件发送失败", e);
			}
			res = 1;
		}
		
		//如果手机号码不为空,发送短信
		if(StringUtils.isNotBlank(person.getMobile())){
    	    int mobileRes = 1;//短信是否发送成功 1 成功
			String smsTemplate = "您好!{admin}通过Jabava系统邀请您填写个人信息。" +"用户名 {account},密码：{password}请登录{url} ";
			smsTemplate = smsTemplate.replace("{admin}", user.getUserName());
			smsTemplate = smsTemplate.replace("{account}", person.getMobile());		
			smsTemplate = smsTemplate.replace("{password}", password);
			smsTemplate = smsTemplate.replace("{url}", JabavaPropertyCofigurer.getProperty("JABAVA_URL_SHORT")+personId);					
			try {
				String mobRes = mobileService.sendMessage(person.getMobile(), smsTemplate);
				if(!"1".equals(mobRes)){
					mobileRes = 0;//短信发送失败
				}
			} catch (Exception e) {
				mobileRes = 0;//短信发送失败
				logger.error("短信发送失败", e);
			}
			if(res == 1 && mobileRes ==1 ){//3 短信发送成功，邮件也发成功
				res = 3;
			}else if(res == 0 && mobileRes == 0){//0 都发送失败
				res = 0;
			}else if(res == 1 && mobileRes == 0){//邮件发送成功，短信发送失败
				res = 1;
			}else if(res == 0 && mobileRes == 1){//短信发送成功，邮件发送失败
				res = 2;
			}else{
				res = 0;
			}
		}
		
		if(res == 1){
			return "Email发送成功";
		}else if(res == 2){
			return "短信发送成功";
		}else if(res == 3){
			return "短信发送成功,Email发送成功";
		}else{
			return "发送失败";
		}
	}
	
	@Override
	public EhrPerson getByPersonId(Long personId) throws Exception {
		return personMapper.getById(personId);
	}

	@Override
	public EhrPerson getByJobNumber(Long companyId, String jobNumber){
		return personMapper.searchPersonByJobNumber(companyId, jobNumber);
	}
	
	@Override
	public List<EhrPerson> getBySearch(Long companyId, String search) {
		return personMapper.searchBySearch(companyId, search);
	}

	@Override
	public int searchPositive(int day, Long companyId, String distinguish,
			Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and  positive_date<"
				+ Tools.dateToStr(Tools.addDay(now, 10))
				+ " and positive_date >" + Tools.dateToStr(now) + " and  "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		map.put("day", day);
		return personMapper.searchPositive(map);
	}

	@Override
	public int searchContract(int day, int flag, Long companyId,
			String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String dateSql = "flag=" + flag + " and  contract_end_date<"
				+ Tools.dateToStr(Tools.addDay(now, day))
				+ " and contract_end_date >" + Tools.dateToStr(now);
		String where = " and  " + getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("day", day);
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("dateSql", dateSql);
		map.put("where", where);
		return personMapper.searchContract(map);
	}

	@Override
	public int searchBirth(int day, Long userId, Long companyId,
			String distinguish) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and DATE_FORMAT(birth_date, '%m-%d')='"
				+ Tools.formatAll(Tools.addDay(now, day), "MM-dd") + "' and "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		return personMapper.searchBirth(map);
	}

	@Override
	public List<EhrPerson> searchBirthList(int day, Long userId,Long companyId, String distinguish) throws Exception {		
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and  ep.birth_date "
				+ Tools.strToLikeStr(Tools.formatAll(Tools.addDay(now, day),
						"MM-dd")) + " and "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		return personMapper.searchBirthList(map);
	}

	@Override
	public List<EhrPerson> searchPositiveList(int day, Long companyId,
			String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		//List<EhrPerson> list = null;
		Date now = new Date();
		String where = "and  ep.positive_date<"
				+ Tools.dateToStr(Tools.addDay(now, 10))
				+ " and ep.positive_date >" + Tools.dateToStr(now) + " and  "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		map.put("day", day);
		return personMapper.searchPositiveList(map);
	}

	@Override
	public List<EhrContract> searchContractList(int day, int flag,
			Long companyId, String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String dateSql = "flag=" + flag + " and  contract_end_date<"
				+ Tools.dateToStr(Tools.addDay(now, day))
				+ " and contract_end_date >" + Tools.dateToStr(now);
		String where = " and  " + getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("day", day);
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("dateSql", dateSql);
		map.put("where", where);
		return contractMapper.searchContractList(map);
	}

	@Override
	public String importPerson(CommonsMultipartFile multipartFile, EhrUser user) throws Exception {
		errorMsg.clear();
		
		Workbook book = null;
		InputStream inputstream = multipartFile.getInputStream();
		if (".xlsx".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new XSSFWorkbook(inputstream);
		} else if (".xls".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new HSSFWorkbook(inputstream);
		} else {
			throw new JabavaServiceException("花名册导入失败，错误：文件格式错误。");
		}
		
		IPersonImport personImporter = null;
		Map<String, String> existingPerson = new HashMap<String, String>();
		List<EhrPerson> list = personMapper.getAllPersonCertId(user.getCompanyId());
		EhrOrganization topOrg = organizationMapper.findTopOrganization(user.getCompanyId());
		
		// 提取系统中所有有身份证的人
		for (EhrPerson person : list) {
			if (!"".equals(person.getCertId()) && person.getCertId() != null) {
				existingPerson.put(person.getCertId(), person.getPersonId().toString());
				existingPerson.put(person.getJobNumber(), person.getPersonId().toString());
			}
		}
		
		// 校验并收集导入数据
		// TODO 如果数据过大，这里可能有内存方面的隐患
		HashMap<Integer,Map<String,Object>> dataChunk = new HashMap<Integer,Map<String,Object>>();
		HashMap<Integer,Map<String,Integer>> dupMap = new HashMap<Integer,Map<String,Integer>>();
		for (int num = 0; num < book.getNumberOfSheets(); num++) {
			Sheet sheet = book.getSheetAt(num);
			if (num == 0) {// 个人信息
				personImporter = new PersonEach(personMapper, baseDataMapper, commonDataMapper);
			} else if (num == 1) {// 岗位信息
				personImporter = new PersonPost(personMapper,baseDataMapper,organizationMapper,commonDataMapper);
			}else if (num == 2) {// 离职管理
				personImporter = new PersonLeft(leftMapper,baseDataMapper);
			} else {
				continue;
			}
			
			dataChunk.put(num, personImporter.importPerson(num, sheet, existingPerson, user));
			dupMap.put(num, personImporter.duplicatedCertId());
		}
		
		Map<String,Object> baseInfo = dataChunk.get(0);//个人信息
		Map<String,Object> posInfo = dataChunk.get(1); //岗位信息
		Map<String,Object> dismInfo = dataChunk.get(2);//离职管理
		
//		//任职记录相关
//		Map<String,Date> recordAdd=new HashMap<String,Date>();
//		List<String> jobNumberList=new ArrayList<String>();
// 		Long companyId=null;
		// 个人基本信息入库，如果岗位信息里有同一个人的数据，只修改个人基本信息
		Set<String> keys = baseInfo.keySet();
		for(String certId : keys){
			EhrPerson person = (EhrPerson) baseInfo.get(certId);
// 			companyId=person.getCompanyId();
			if(person.getPersonId() == null){
				if(posInfo.get(person.getCertId()) != null){
					personMapper.insertSelective(person);
					
					EhrPerson np = (EhrPerson)posInfo.get(person.getCertId());
					np.setPersonId(person.getPersonId());
//					personMapper.updateEmployeeInfoByCardId(np);//problem
					personMapper.updateByPrimaryKeySelective(np);
					posInfo.remove(person.getCertId());
					
					
					
					//处理新员工的任职记录(只给有岗位信息的员工添加任何记录)
//					if(person.getEntryDate()!=null&&"".equals(person.getEntryDate())){
//						jobNumberList.add(person.getJobNumber());//收集工号，用于查询personId
//						recordAdd.put(person.getJobNumber(), person.getEntryDate());//收集入职时间
//					}
					
					if(person.getEntryDate()!=null ){
						jobpostService.HandleRecordByPersonId(np.getPersonId(), user, person.getEntryDate(),String.valueOf(PersonEnum.PostChangeType.Entry.getValue()),np);
 					}
					
					
				}else{	//新增时如果没有部门，则默认顶层部门
					if(person.getOrganizationId() == null){
						person.setOrganizationId(topOrg.getOrganizationId());
					}
					personMapper.insertSelective(person);
				}
				
			
				
				
			}else{
				personMapper.updateByPrimaryKeySelective(person);
				
				if(posInfo.get(person.getCertId()) != null){
					EhrPerson np = (EhrPerson)posInfo.get(person.getCertId());
					personMapper.updateByPrimaryKeySelective(np);
					posInfo.remove(person.getCertId());
					
					//处理任职记录(只给有岗位信息的已存在的员工添加任何记录)
//					if(person.getEntryDate()!=null){
//						//jobpostService.HandleEntryRecordByPersonId(person.getPersonId(), user, person.getEntryDate());
//						jobpostService.HandleRecordByPersonId(np.getPersonId(), user, np.getEntryDate(),String.valueOf(PersonEnum.PostChangeType.Entry.getValue()),np);
//					}
					
				}
				
				
				
				
			}
		}
		
		////批量新增有入职时间的新员工的任职记录
//		if(recordAdd!=null&&recordAdd.size()>0){
//			
//			handleNewEmployeeEntryRecords(recordAdd, jobNumberList, companyId,user);
//			 
//		}
		
		
		// 岗位数据入库
		Set<String> pkeys = posInfo.keySet();
		for(String certId : pkeys){
			EhrPerson person = (EhrPerson) posInfo.get(certId);
			if(person.getPersonId() == null){
				//personMapper.insertSelective(person);
			}else{
				personMapper.updateByPrimaryKeySelective(person);
			}
		}
		
		// 离职数据入库
		Set<String> dkeys = dismInfo.keySet();
		for(String certId : dkeys){
			 EhrDimission person = (EhrDimission) dismInfo.get(certId);
			//根据personId查询dimissionId
			 List<EhrDimission> dimissionList=leftMapper.getDimissionByPersonId(person.getPersonId());
			 
			 //dimissionId有则改，无责加
			 if(dimissionList!=null&&dimissionList.size()>0){
				 person.setDimissionId(dimissionList.get(0).getDimissionId());
				 leftMapper.updateByPrimaryKeySelective(person);
			 }else{
				 leftMapper.insertSelective(person);
			 }
			 
			
			EhrPerson dimiPerson = new EhrPerson();
			dimiPerson.setPersonId(person.getPersonId());
			if(person.getDimissionDate()!=null){
				dimiPerson.setLeftDate(person.getDimissionDate());//离职时间
			}
			if(person.getDimissionCause()!=null){
				dimiPerson.setLeftCause(person.getDimissionCause().toString());//离职原因
			}
			dimiPerson.setStatus(2);//状态为离职状态
			
			//处理任职记录-离职4
			boolean flag=jobpostService.HandleRecordByPersonId( person.getPersonId(), user,person.getDimissionDate(),String.valueOf(PersonEnum.PostChangeType.Dimission.getValue()),null);
			if(!flag){
				Log.info("任职记录处理失败...");
			}
			this.updatePerson(dimiPerson);//更新Person中的 离职 
		}
		
		// 收集重复信息
		Map<String,Integer> baseDup = dupMap.get(0);
		Map<String,Integer> posDup  = dupMap.get(1);
		
		HashMap<String,Integer> overallMap = new HashMap<String,Integer>();
		for(String certId : baseDup.keySet()){
			Integer baseCount = baseDup.get(certId);
			Integer posCount = posDup.get(certId);
			if(baseCount > 1){
				baseCount --;
				if(posCount != null ){
					baseCount += (posCount-1);
				}
				overallMap.put(certId, baseCount);
			}else{
				if(posCount != null ){
					baseCount += (posCount-1);
					overallMap.put(certId, baseCount);
				}
			}
		}
		
		// 提示重复的前五条
		Integer BRIEFE_DUPLICATE_CERTID = 5;
		StringBuffer sb = new StringBuffer();
		if(overallMap.size() > 0){
			int idx = 0;
			for(String certId : overallMap.keySet()){
				idx ++;
				sb.append(certId + " ");
				
				if(idx >= BRIEFE_DUPLICATE_CERTID){
					break;
				}
			}
			
			sb.append(" 等" + idx +"个证件号码出现重复,重复资料已依据编辑顺序覆盖");
		}
		
		return sb.toString();
	}

	@Override
	public List<EhrPerson> selectAllPerson(Long companyId) throws Exception {
		List<EhrPerson> list = new ArrayList<EhrPerson>();
		list = personMapper.selectAllPerson(companyId);
		return list;
	}

	@Override
	public List<EhrPerson> selectPersonByParam(Map<String,Object> params) throws Exception {
		return personMapper.searchPersonByParam(params);
	}

	@Override
	public void exportPerson(List<EhrPerson> persons, EhrUser user,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		try {
			IPersonExport pe = null;
			// 个人信息
			pe = new PersonEach(personMapper, baseDataMapper, commonDataMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonEach", data);
			
			// 基本信息
			//datas.put("PersonBase", new ArrayList<Map<String, Object>>());
			pe = new PersonBase(personMapper, companyMapper, baseDataMapper, commonDataMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonBase", data);
			
			// 岗位信息
			pe = new PersonPost(personMapper, baseDataMapper,organizationMapper, commonDataMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonPost", data);
			
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String("all_persons.xlsx"));
			
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "tpl_person.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, Object> updatePerson(HttpServletRequest request, EhrPerson person, EhrUser user) throws Exception{
		Map<String, Object> map = new HashMap<>();
		person.setLastModifyDate(new Date());
		person.setLastModifyUserId(user.getUserId());
		person.setLastModifyUserName(user.getUserName());
		personMapper.updateByPrimaryKeySelective(person);
		Long userId = personMapper.getUserId(person.getPersonId());
		
		if(userId != null && !"".equals(userId)){
			EhrUser u = new EhrUser();
			u.setSex(person.getSex() == 0 ? 1 : 2);
			u.setTelephone(person.getPhone());
			u.setLastModifyDate(new Date());
			u.setLastModifyUserId(user.getUserId());
			u.setLastModifyUserName(user.getUserName());
			userMapper.updateByPrimaryKeySelective(u);

//			//更新用户
//			Map<String,Object> rr = centerUserClientService.updateUser(u);
//			if("false".equals(rr.get("success").toString())){
//				throw new JabavaServiceException(rr.get("msg").toString());
//			}
		}
		map.put("success", true);
		map.put("msg", "修改成功！");
		return map;
	}
	@Override
	public int updatePerson(EhrPerson person){
		
		return personMapper.updateByPrimaryKeySelective(person);
	}
	public Long getPersonId(Long userId){
		return personMapper.getPersonId(userId);
	}
	public Long getUserId(Long personId){
		return personMapper.getUserId(personId);
	}
	@Override
	public List<EhrPerson> getEmployeesByCompanyId(EhrPerson ehrPerson) {
		return  personMapper.getEmployeesByCompanyId(ehrPerson);//TODO  personMapper;
	}

	@Override
	public EhrPerson getEmployeeByCardId(String cardId) {
		return personMapper.getEmployeeByCardId(cardId);
	}

	@Override
	public List<EhrPerson> getAllPersonForSync() {
		return personMapper.getAllPersonForSync();
	}

	@Override
	public Map<String,Object> getEmployeeInfoByCardId(String cardId) {
		return personMapper.getEmployeeInfoByCardId(cardId);
	}

	@Override
	public Map<String, Object> queryHintInfo(Map<String, Object> params) {
		Long companyId = (Long)params.get("companyId");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("toBecomeMember", personMapper.countToBecomeMember(companyId, (Integer)params.get("daysToBecomeMember")));
		result.put("birthdayToday", personMapper.countBirthdayToday(companyId));
		result.put("entryThisMonth", personMapper.countEntryThisMonth(companyId));
		result.put("leaveThisMonth", personMapper.countLeaveThisMonth(companyId));
		result.put("contractExpiring", personMapper.countContractExpiring(companyId, (Integer)params.get("daysContractExpiring")));
		return result;
	}
	
	@Override
	public List<EhrPerson> selectByAuthorisedOrgIdList(List<Long>orgIdList) {
		return personMapper.selectByAuthorisedOrgIdList(orgIdList);
	}
	@Override
	public List<EhrPerson> findPersonByIdList(List<Long> list) {
		return personMapper.findPersonByIdList(list);
	}
	@Override
	public List<EhrPerson> selectByCompanyIdJobNumberList(Map<String, Object> params) {
		return personMapper.selectByCompanyIdJobNumberList(params);
	}
	/**
	 * 批量新增员工入职任职记录
	 * @param recordAdd
	 * @param jobNumberList
	 * @param companyId
	 * @return
	 */
	public int handleNewEmployeeEntryRecords(Map<String,Date> recordAdd,List<String> jobNumberList,Long companyId,EhrUser user){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", companyId);
		params.put("jobNumberList", jobNumberList);
		
		//根据工号和companyId查询员工信息
		List<EhrPerson> personList=personMapper.selectByCompanyIdJobNumberList(params);
		
		List<EhrJobpost> recordList=new ArrayList<EhrJobpost>();
		
		for(EhrPerson person:personList){
			EhrJobpost record=new EhrJobpost();
			record.setPersonId(person.getPersonId());
			record.setRecordStartDate(recordAdd.get(person.getJobNumber()));//入职时间
			
			record.setCreateUserId(user.getUserId());
			record.setCreateUserName(user.getUserName());
			record.setCreateDate(new Date());
			record.setLastModifyDate(new Date());
			record.setLastModifyUserId(user.getUserId());
			record.setLastModifyUserName(user.getUserName());
			record.setChangeType("1");//变动类型-入职
		
			recordList.add(record);
		}
		//批量新增入职记录
		int flag=jobPostMapper.insertBachForEntryRecords(recordList);
		return flag;
	}

	@Override
	public  Map<String, Object> getBasicPersonInfoByPersonId(Long personId) {
		List<Map<String, Object>> list=personMapper.getBasicPersonInfoByPersonId(personId);
		Map<String, Object> data =null;
		if(list!=null&&list.size()>0){
			data=list.get(0);
		}
		return data;
	}
}
