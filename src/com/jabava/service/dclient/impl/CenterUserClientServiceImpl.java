package com.jabava.service.dclient.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.service.provider.CenterUserService;
import com.service.provider.entity.CenterSysUser;
import com.service.provider.entity.CenterUserInfo;
import com.service.provider.entity.OrgUser;
import com.service.provider.entity.ReturnS;

@Service
public class CenterUserClientServiceImpl implements CenterUserClientService{
	private static final Logger log = LoggerFactory.getLogger(CenterUserClientServiceImpl.class);
	
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
	public Map<String,Object> registerUser(EhrUser user, String companyName,String password){
		Map<String,Object> result = new HashMap<String,Object>();
		
//	    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	    CenterUserService cuService = (CenterUserService)wac.getBean("dubbo.centerUserService");
//	    //PasswordEncoder passwordEncoder = (PasswordEncoder)wac.getBean("hr.passwordEncoder");
	    
		CenterUserInfo centerUser = new CenterUserInfo();
		centerUser.setOrgName(companyName);			//企业名称
		centerUser.setName(user.getUserName());		//用户名称
		centerUser.setLoginName(user.getUserCode());//登录名称
		centerUser.setPassword(password);			//原文
		//用户在当前系统的唯一标识(对应Jabava系统的userId)
		centerUser.setIdentity(user.getUserId().toString());
		centerUser.setEmail(user.getMailAddress());
		centerUser.setMobile(user.getMobile());
		
//		centerUser.setOrgName("orgTest11_Jabava");
//		centerUser.setName("Jabava测试用户");
//		centerUser.setLoginName("loginNameTest11_Jabava");
//		centerUser.setPassword("123456");
//		centerUser.setIdentity("59909111");
//		centerUser.setEmail("aaaTest11@sina.com_Jabava");
//		centerUser.setMobile("13091399351");
		
		centerUser.setChannel("Jabava");
		centerUser.setUserType(1);	//企业用户
		centerUser.setSystemId(Long.valueOf(JabavaPropertyCofigurer.getSystemId()));
		
		try {
			ReturnS r = getCenterUserClientService().registerUser(centerUser);
			if(!r.getReturnCode().equals("0")){
				log.error(centerUser.getLoginName() + "注册失败: " + r.getMsg());
				result.put("success", "false");
				result.put("msg", r.getMsg());
			}else{
				//需要保存用户中心返回的orgId及loginId
				Long loginId = (Long)r.getResult();
				log.info(centerUser.getLoginName() + "注册返回LoginId: " + loginId);
				//根据loginId获取用户中心网站用户
				r = getCenterUserClientService().getCenterUser(loginId, Long.valueOf(1));
				if(!r.getReturnCode().equals("0")){
					log.error(centerUser.getLoginName() + "根据loginId获取用户中心网站用户失败: " + r.getMsg());
					result.put("success", "false");
					result.put("msg", r.getMsg());
				}else{
					CenterSysUser centerSysUser = (CenterSysUser) r.getResult();
					OrgUser orgUser = centerSysUser.getOrgUser();
					//开通Jabava服务
					JSONObject paramJson = new JSONObject();
					//标识：用户中心开通服务时不需要调用Jabava接口
					paramJson.put("identity", centerUser.getIdentity());
					result = openService(loginId, Long.valueOf(JabavaPropertyCofigurer.getSystemId()), paramJson.toString());
					
					result.put("orgId", orgUser.getOrgId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 开通用户(同时开通Jabava服务)
	 */
	public Map<String,Object> openUser(EhrUser user,String password){
		Map<String,Object> result = new HashMap<String,Object>();
		
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	    CenterUserService cuService = (CenterUserService)wac.getBean("dubbo.centerUserService");
//	    //PasswordEncoder passwordEncoder = (PasswordEncoder)wac.getBean("hr.passwordEncoder");
		
		HttpServletRequest request = ((ServletRequestAttributes)   
                RequestContextHolder.currentRequestAttributes()).getRequest();
		Long orgId = (Long)request.getSession().getAttribute("orgId");
		log.info(user.getUserName() + "用户开通的OrgId: " + orgId);
		
		CenterUserInfo centerUser = new CenterUserInfo();
		centerUser.setOrgId(orgId);
		centerUser.setName(user.getUserName());
		centerUser.setLoginName(user.getUserCode());
		centerUser.setPassword(password);
		centerUser.setIdentity(user.getUserId().toString());
		centerUser.setEmail(user.getMailAddress());
		centerUser.setMobile(user.getMobile());
		
//		centerUser.setOrgId(Long.valueOf(399));
//		centerUser.setName("openNameTest4_Jabava");
//		centerUser.setLoginName("openLoginNameTest4_Jabava");
//		centerUser.setPassword("123456");
//		centerUser.setIdentity("1998004");
//		centerUser.setEmail("openEailTest4@sina.com_Jabava");
//		centerUser.setMobile("13481399334");
		
		//centerUser.setUserType(2);		//普通用户
		centerUser.setUserType(1);			//2注册后无法登录
		centerUser.setChannel("Jabava");
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("identity", centerUser.getIdentity());
		centerUser.setParamData(paramJson.toString());
		centerUser.setSystemId(Long.valueOf(JabavaPropertyCofigurer.getSystemId()));
		
		try {
			ReturnS r = getCenterUserClientService().openUser(centerUser);
			if(!r.getReturnCode().equals("0")){
				log.error(centerUser.getLoginName() + "用户开通失败: " + r.getMsg());
				result.put("success", "false");
				result.put("msg", r.getMsg());
			}else{
				//需要保存用户中心返回的loginId
				Object o = r.getResult();
				log.info("LoginId: " + o);
				result.put("success", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	
	public Map<String,Object> openService(Long loginId, Long systemId, String paramData){
		Map<String,Object> result = new HashMap<String,Object>();
		
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	    CenterUserService cuService = (CenterUserService)wac.getBean("dubbo.centerUserService");
//	    //PasswordEncoder passwordEncoder = (PasswordEncoder)wac.getBean("hr.passwordEncoder");
		
		try {
			ReturnS r = getCenterUserClientService().openService(loginId, systemId, paramData);
			if(!r.getReturnCode().equals("0")){
				log.error(loginId + "用户开通服务失败: " + r.getMsg());
				result.put("success", "false");
				result.put("msg", r.getMsg());
			}else{
				//Object o = r.getResult();
				//log.info("LoginId: " + o);
				result.put("success", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	public Map<String,Object> updateUser(EhrUser user){
		Map<String,Object> result = new HashMap<String,Object>();
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	    CenterUserService cuService = (CenterUserService)wac.getBean("dubbo.centerUserService");
	    
	    try {
			//根据Identity查找用户
			ReturnS r = getCenterUserClientService().getCenterUser(user.getUserCode(), 
					Long.valueOf(JabavaPropertyCofigurer.getSystemId()));
			CenterSysUser centerSysUser = (CenterSysUser) r.getResult();
			if (centerSysUser == null) {
				result.put("success", "false");
				result.put("msg", "用户不存在");
				return result;
			}
			
			OrgUser orgUser = centerSysUser.getOrgUser();
			if(!user.getMobile().equals(orgUser.getMobile())){
				r = getCenterUserClientService().updateMobile(user.getUserCode(), user.getMobile());
				if(!r.getReturnCode().equals("0")){
					log.error(user.getUserName() + "用户更新手机失败: " + r.getMsg());
					log.info("Exception: " + r.getException());
					result.put("success", "false");
					result.put("msg", r.getMsg());
					return result;
				}
			}
			if(!user.getMailAddress().equals(orgUser.getEmail())){
				r = getCenterUserClientService().updateEmailInfo(user.getUserCode(), user.getMailAddress());
				if(!r.getReturnCode().equals("0")){
					log.error(user.getUserName() + "用户更新邮箱失败: " + r.getMsg());
					log.info("Exception: " + r.getException());
					result.put("success", "false");
					result.put("msg", r.getMsg());
					return result;
				}
			}
			if(!user.getPassword().equals(orgUser.getPassword())){
				r = getCenterUserClientService().updatePassword(user.getUserCode(), user.getPassword());
				if(!r.getReturnCode().equals("0")){
					log.error(user.getUserName() + "用户更新密码失败: " + r.getMsg());
					log.info("Exception: " + r.getException());
					result.put("success", "false");
					result.put("msg", r.getMsg());
					return result;
				}
			}
			
			result.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", e.getMessage());
		}
	    return result;
	}
}
