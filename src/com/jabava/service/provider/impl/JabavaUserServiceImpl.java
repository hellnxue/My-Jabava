package com.jabava.service.provider.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.manage.IUserService;
import com.jabava.utils.Tools;
import com.service.provider.JabavaUserService;

//@Service
public class JabavaUserServiceImpl implements JabavaUserService{
	private static Logger log = Logger.getLogger(JabavaUserServiceImpl.class);
	
	@Resource
	private IUserService userService;
	
	/**
	 * 用于开通Jabava服务
	 * @param paramMap
	 * {"orgId":"321","orgName":"上海大众","loginId":"9081","name":"刘小楼","username":"9063admin","mobile":13813781580,"email":"123@abc.com"}
	 * @return 
	 * {"resultCode":0,"resultMsg":"同步成功","resultData":{"userId":781580}}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map syncJabavaData(Map paramMap) {
		log.debug(paramMap);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("resultCode", "1");
		
		//数据验证(用户、公司、手机、邮箱)
		if(paramMap == null){
			result.put("resultMsg", "参数为空");
			return result;
		}
		
		Object orgIdObj = paramMap.get("orgId");
		Object orgNameObj = paramMap.get("orgName");
		Object loginIdObj = paramMap.get("loginId");
		Object nameObj = paramMap.get("name");
		Object usernameObj = paramMap.get("username");
		Object mobileObj = paramMap.get("mobile");
		Object emailObj = paramMap.get("email");
		if(orgIdObj == null || "".equals(orgIdObj.toString())){
			result.put("resultMsg", "机构ID为空");
			return result;
		}
		if(orgNameObj == null || "".equals(orgNameObj.toString())){
			result.put("resultMsg", "机构名称为空");
			return result;
		}
		if(loginIdObj == null || "".equals(loginIdObj.toString())){
			result.put("resultMsg", "登录ID为空");
			return result;
		}
		if(nameObj == null || "".equals(nameObj.toString())){
			result.put("resultMsg", "姓名为空");
			return result;
		}
		if(usernameObj == null || "".equals(usernameObj.toString())){
			result.put("resultMsg", "用户名为空");
			return result;
		}
		if(mobileObj == null || "".equals(mobileObj.toString())){
			result.put("resultMsg", "手机号为空");
			return result;
		}
//		if(emailObj == null || "".equals(emailObj.toString())){
//			result.put("resultMsg", "邮箱为空");
//			return result;
//		}
		
		
		//注册公司及管理员
		try {
			Date now = new Date();
			String password = "123456";
			
			EhrCompany company = new EhrCompany();
			company.setCompanyCode(String.valueOf(System.currentTimeMillis()));
			company.setCompanyName(orgNameObj.toString());
			company.setContactor(nameObj.toString());//联系人
			//company.setCompanyProv(companyProv);
			//company.setCompanyCity(companyCity);
			//company.setCompanyDist(companyDist);
			company.setParentId((long) 0);
			company.setUseEhr(1);
			company.setCreateDate(now);
			company.setLastModifyDate(now);
			
			EhrUser user = new EhrUser();
			user.setCompanyCode(company.getCompanyCode());
			user.setUserCode(usernameObj.toString());
			user.setMobile(mobileObj.toString());
			user.setPassword(Tools.encryptPassword(password));
			user.setUserName(nameObj.toString());//联系人
			user.setIsValid((byte) 1);
			user.setIsLocked((byte) 0);
			user.setLastChangePasswordDate(now);
			user.setFailtureTime(0);
			user.setLoginTime(0);
			user.setUserType((byte) 2);
			user.setCreateDate(now);
			user.setLastModifyDate(now);
			user.setFlag(1);
			//user.setMailAddress(emailObj.toString());
			
			//在Jabava中注册
			String msg = userService.register(company, user);
			if(StringUtils.isEmpty(msg)){
				//初始化
				userService.asynchInitCompany(user);
				
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("userId",user.getUserId());
				result.put("resultData",data);
				result.put("resultMsg","同步成功");
				result.put("resultCode","0");
			}else{
				result.put("resultMsg",msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resultMsg","同步失败");
		}
		
		return result;
	}
}
