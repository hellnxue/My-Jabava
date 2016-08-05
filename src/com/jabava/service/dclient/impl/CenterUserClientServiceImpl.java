package com.jabava.service.dclient.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.enums.JabavaEnum;
import com.service.provider.CenterUserService;
import com.service.provider.entity.CenterSysUser;
import com.service.provider.entity.CenterUserInfo;
import com.service.provider.entity.OrgUser;
import com.service.provider.entity.ReturnS;

@Service
public class CenterUserClientServiceImpl implements CenterUserClientService{
	private static final Logger log = LoggerFactory.getLogger(CenterUserClientServiceImpl.class);
	/** 企业用户 */
	private static final Integer USER_TYPE_E = 1;
	/** 个人用户 */
	private static final Integer USER_TYPE_P = 2;
	private static final String OTHER_LOGIN_CHANNEL = "3";
	
	//@Autowired
	private CenterUserService centerUserService;
	
	public CenterUserService getCenterUserClientService(){
		if(this.centerUserService == null){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			this.centerUserService = (CenterUserService)wac.getBean("dubbo.centerUserService");
		}
		
		return this.centerUserService;
	}
	
	/**
	 * 注册企业及管理员用户
	 */
	@Override
	public Map<String,Object> registerUser(EhrUser user, String companyName,String password){
		Map<String,Object> result = new HashMap<String,Object>();
		
	    //PasswordEncoder passwordEncoder = (PasswordEncoder)wac.getBean("hr.passwordEncoder");
		
		try {
			CenterUserInfo centerUser = this.getCenterUserInfo(user, companyName, password);
			
			ReturnS r = getCenterUserClientService().registerUser(centerUser);
			if(!r.getReturnCode().equals("0")){
				log.error(centerUser.getLoginName() + "注册失败: " + r.getMsg());
				return MessageUtil.errorMessage(r.getMsg());
			}
			
			//需要保存用户中心返回的orgId及loginId
			Long loginId = (Long)r.getResult();
			log.info(centerUser.getLoginName() + "注册返回LoginId: " + loginId);
			
			//根据loginId获取用户中心网站用户
			r = getCenterUserClientService().getCenterUser(loginId, Long.valueOf(1));
			if(!r.getReturnCode().equals("0")){
				log.error(centerUser.getLoginName() + "根据loginId获取用户中心网站用户失败: " + r.getMsg());
				return MessageUtil.errorMessage(r.getMsg());
			}
			
			CenterSysUser centerSysUser = (CenterSysUser) r.getResult();
			OrgUser orgUser = centerSysUser.getOrgUser();
			//开通Jabava服务
			JSONObject paramJson = new JSONObject();
			//标识：用户中心开通服务时不需要调用Jabava接口
			paramJson.put("identity", centerUser.getIdentity());
			result = openService(loginId, Long.valueOf(JabavaPropertyCofigurer.getSystemId()), paramJson.toString());
			result.put("orgId", orgUser.getOrgId());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 注册个人用户
	 */
	@Override
	public Map<String, Object> registerPersonalUser(EhrUser user, EhrPerson person, String password) {
		//如果不需要生成用户，则接口参数可以去掉user
		//可以在前台通过Ajax判断是否存在该手机号的个人账号
		//String getLoginNameByMobile(String mobile, Integer userType)
		//没查到则注册(可能已存在)
		
		//ReturnS returnS = centerUserService.isExistUser(user.getUserCode(), null);
		//if (returnS != null && returnS.getSuccess()) {
		//	// 如果用户中心已经存在该用户,更新用户的密码
		//	//return this.resetPassword(user.getUserCode(), password);
		//	return MessageUtil.errorMessage("用户名已经存在");
		//} else {
			// 如果不存在就注册个人用户
		log.info("注册个人用户-orgId：" + user.getCompany().getUcOrgId());
			CenterUserInfo centerUser = this.getCenterUserInfo(user, user.getCompany().getUcOrgId(), password);
		log.info("注册个人用户-orgId：" + centerUser.getOrgId());
//			if(JabavaEnum.UserTypeEnum.COMMON.getValue().equals(user.getUserType())){
//				JSONObject paramJson = new JSONObject();
//				paramJson.put("identity", centerUser.getIdentity());
////				if(person != null && !StringUtils.isEmpty(person.getCertId())){
////					paramJson.put("IDENTITY_CARD", person.getCertId());
////					log.info("普通用户开通时员工身份证：" + person.getCertId());
////				}else if(person == null){
////					log.error("普通用户开通时员工为空");
////				}else{
////					log.error("普通用户开通时身份证为空");
////				}
//				centerUser.setParamData(paramJson.toString());
//			}
			ReturnS r = this.getCenterUserClientService().registerUser(centerUser);
			if(!r.getReturnCode().equals("0")){
				return MessageUtil.errorMessage(r.getMsg());
			}
			
//			//普通用户更新身份证
//			if(JabavaEnum.UserTypeEnum.COMMON.getValue().equals(user.getUserType())){
//				r = updateUserInfo(person, loginId.toString());
//				if(!r.getReturnCode().equals("0")){
//					log.error(centerUser.getLoginName() + "用户信息更新失败: " + r.getMsg());
//					return MessageUtil.errorMessage(r.getMsg());
//				}
//			}
			
//			//普通用户预激活
//			if(JabavaEnum.UserTypeEnum.COMMON.getValue().equals(user.getUserType())){
//				r = syncUserInfo(person);
//				if(!r.getReturnCode().equals("0")){
//					log.error(centerUser.getLoginName() + "用户预激活失败: " + r.getMsg());
//					return MessageUtil.errorMessage(r.getMsg());
//				}
//			}
		//}
		log.info("用户中心注册个人用户成功: " + user.getUserCode());
		return MessageUtil.successMessage("用户中心注册个人用户成功");
	}

	/**
	 * 开通企业用户(同时开通Jabava服务)
	 */
	@Override
	public Map<String,Object> openUser(EhrUser user,String password){
		//开通用户
		HttpServletRequest request = ((ServletRequestAttributes)   
                RequestContextHolder.currentRequestAttributes()).getRequest();
		Long orgId = (Long)request.getSession().getAttribute("orgId");
		log.info(user.getUserName() + "用户开通的OrgId: " + orgId);
		
		CenterUserInfo centerUser = this.getCenterUserInfo(user, orgId, password);

		JSONObject paramJson = new JSONObject();
		paramJson.put("identity", centerUser.getIdentity());
		centerUser.setParamData(paramJson.toString());
		
		try {
			ReturnS r = getCenterUserClientService().openUser(centerUser);
			if(!r.getReturnCode().equals("0")){
				log.error(centerUser.getLoginName() + "用户开通失败: " + r.getMsg());
				return MessageUtil.errorMessage(r.getMsg());
			}
			
			//需要保存用户中心返回的loginId
			Object loginId = r.getResult();
			log.info("新开通用户LoginId: " + loginId);
			
			return MessageUtil.successMessage("用户开通成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	private CenterUserInfo getCenterUserInfo(EhrUser user, String companyName, String password){
		return this.getCenterUserInfo(user, companyName, null, password);
	}
	
	private CenterUserInfo getCenterUserInfo(EhrUser user, Long orgId, String password){
		return this.getCenterUserInfo(user, null, orgId, password);
	}
	
	private CenterUserInfo getCenterUserInfo(EhrUser user, String companyName, Long orgId, String password){
		CenterUserInfo centerUser = new CenterUserInfo();
		if(!StringUtils.isEmpty(companyName)){
			centerUser.setOrgName(companyName);		//企业名称
		}
		if(orgId != null){
			centerUser.setOrgId(orgId);				//企业ID
		}
		centerUser.setName(user.getUserName());		//用户名称
		centerUser.setLoginName(user.getUserCode());//登录名称
		centerUser.setPassword(password);			//原文
		//centerUser.setEmail(user.getMailAddress());
		if(!StringUtils.isEmpty(user.getMailAddress())){
			centerUser.setEmail(user.getMailAddress());
		}
		centerUser.setMobile(user.getMobile());
		centerUser.setChannel("Jabava");
		if(JabavaEnum.UserTypeEnum.COMMON.getValue().equals(user.getUserType())){
			centerUser.setUserType(USER_TYPE_P);
		}else{
			centerUser.setUserType(USER_TYPE_E);
		}

		//用户在当前系统的唯一标识(对应Jabava系统的userId)
		centerUser.setIdentity(user.getUserId().toString());
		
		centerUser.setSystemId(Long.valueOf(JabavaPropertyCofigurer.getSystemId()));
		return centerUser;
	}
	
	private ReturnS updateUserInfo(EhrPerson person, String loginId){
		Map<String, String> data = new HashMap<String, String>();
		data.put("loginId", loginId);
//		data.put("MOBILE", person.getMobile());
		data.put("cardNum", person.getCertId());
		if(person.getCertType() != null && !StringUtils.isEmpty(person.getCertType().toString())){
			data.put("cardType", person.getCertType().toString());
		}else{
			data.put("cardType", JabavaEnum.HroCardType.IDENTITY_CARD.getValue());
		}
//		if(!StringUtils.isEmpty(person.getEmail())){
//			data.put("EMAIL", person.getEmail());
//		}
		
		log.info("loginId: " + loginId + ", cardNum: " + person.getCertId());
		
		return getCenterUserClientService().updateUserInfo(data);
	}
	
	private ReturnS syncUserInfo(EhrPerson person){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ORG_ID", person.getCompanyId());	//此处应该上hroOrgId
		data.put("MOBILE", person.getMobile());
		data.put("IDENTITY_CARD", person.getCertId());
		if(!StringUtils.isEmpty(person.getEmail())){
			data.put("EMAIL", person.getEmail());
		}
		return getCenterUserClientService().syncUserInfoByChannel(person.getPersonId(), 
				OTHER_LOGIN_CHANNEL, USER_TYPE_P, person.getMobile(), null, data);
	}

	@Override
	public Map<String,Object> openService(Long loginId, Long systemId, String paramData){
	    //PasswordEncoder passwordEncoder = (PasswordEncoder)wac.getBean("hr.passwordEncoder");
		
		try {
			ReturnS r = getCenterUserClientService().openService(loginId, systemId, paramData);
			if(!r.getReturnCode().equals("0")){
				log.error(loginId + "用户开通服务失败: " + r.getMsg());
				return MessageUtil.errorMessage(r.getMsg());
			}else{
				//Object o = r.getResult();
				//log.info("LoginId: " + o);
				return MessageUtil.successMessage("用户开通服务成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
//	@Override
//	public Map<String,Object> personLoginAuth(String login, String password){
//		ReturnS r = this.getCenterUserClientService().loginAuth(null, USER_TYPE_P, login, password);
//		if(!r.getReturnCode().equals("0")){
//			log.error(login + "用户登录失败: " + r.getMsg());
//			return MessageUtil.errorMessage(r.getMsg());
//		}
//		
//		//根据loginId获取用户中心网站用户
//		Long loginId = ((CenterUserInfo)r.getResult()).getLoginId();
//		r = getCenterUserClientService().getCenterUser(loginId, Long.valueOf(1));
//		if(!r.getReturnCode().equals("0")){
//			log.error(login + "根据loginId获取用户中心网站用户失败: " + r.getMsg());
//			return MessageUtil.errorMessage(r.getMsg());
//		}
//		
//		CenterSysUser centerSysUser = (CenterSysUser) r.getResult();
//		OrgUser orgUser = centerSysUser.getOrgUser();
//		Map<String,Object> result = MessageUtil.successMessage("用户登录成功");
//		result.put("mobile", orgUser.getMobile());
//		result.put("email", orgUser.getEmail());
//		return result;
//	}

	@Override
	public Map<String,Object> updateUser(EhrUser user){
	    try {
	    	ReturnS r = getCenterUserClientService().updateMobile(user.getUserCode(), user.getMobile());
			if(!r.getReturnCode().equals("0")){
				log.error(user.getUserName() + "用户更新手机失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
		
			r = getCenterUserClientService().updateEmailInfo(user.getUserCode(), user.getMailAddress());
			if(!r.getReturnCode().equals("0")){
				log.error(user.getUserName() + "用户更新邮箱失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
			
			return MessageUtil.successMessage("更新用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 重置密码
	 */
	@Override
	public Map<String,Object> resetPassword(EhrUser user, String password){
		return this.resetPassword(user.getUserCode(), password);
	}
	
	private Map<String,Object> resetPassword(String userName, String password){
		try {
			ReturnS r = getCenterUserClientService().updatePassword(userName, password);
			if(!r.getReturnCode().equals("0")){
				log.error(userName + "用户重置密码失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
			return MessageUtil.successMessage("重置密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public Map<String,Object> updatePassword(EhrUser user, String oldPassword, String newPassword){
		try {
			ReturnS r;
			//获取用户中心用户
			r = getCenterUserClientService().getCenterUser(user.getUserCode(), Long.valueOf(JabavaPropertyCofigurer.getSystemId()));
			if(!r.getReturnCode().equals("0")){
				log.error(user.getUserName() + "获取用户中心用户失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
			//验证旧密码是否正确
			CenterSysUser centerSysUser = (CenterSysUser) r.getResult();
			r = getCenterUserClientService().isMatchPassword(centerSysUser.getLoginUser().getLoginId(), oldPassword);
			if(!r.getReturnCode().equals("0")){
				log.error(user.getUserName() + "验证密码失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
			//修改新密码
			r = getCenterUserClientService().updatePassword(user.getUserCode(), newPassword);
			if(!r.getReturnCode().equals("0")){
				log.error(user.getUserName() + "更新密码失败: " + r.getMsg());
				log.info("Exception: " + r.getException());
				return MessageUtil.errorMessage(r.getMsg());
			}
			
			return MessageUtil.successMessage("更新密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 获取用户中心用户
	 */
	@Override
	public int hasOpenService(EhrUser user, Long systemId){
		//获取用户中心用户
		ReturnS r = getCenterUserClientService().getCenterUser(user.getUserCode(), systemId);
		if(!r.getReturnCode().equals("0")){
			log.error(user.getUserName() + "获取用户中心用户失败: " + r.getMsg());
			log.info("Exception: " + r.getException());
			return 0;
		}else{
			return 1;
		}
	}
}
