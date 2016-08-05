package com.jabava.utils.privilege;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.manage.EhrUserOrganizationMapper;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserOrganization;
import com.jabava.utils.RequestUtil;

public class AuthorisedPersonUtil {
	//不自动注入
	private static EhrUserOrganizationMapper userOrganizationMapper;
	private static EhrPersonMapper personMapper;
	
	private static EhrUserOrganizationMapper getUserOrganizationMapper(){
		if(userOrganizationMapper == null){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			userOrganizationMapper = wac.getBean(EhrUserOrganizationMapper.class);
		}
		
		return userOrganizationMapper;
	}
	
	private static EhrPersonMapper getPersonMapper(){
		if(personMapper == null){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			personMapper = wac.getBean(EhrPersonMapper.class);
		}
		
		return personMapper;
	}

	/**
	 * 返回管理员可用的用户列表(默认所有)
	 * @return
	 */
	public static String getUserAuthorisedPersonList(){
		return getPersonString(true);
	}

	/**
	 * 返回管理员可用的用户列表(默认空值)
	 * @param field
	 * @return
	 */
	public static String getUserAuthorisedPersonList(String field){
		//" and " + field + " in (" + list + ")"
		String personString = getPersonString(false);
		if(StringUtils.isEmpty(personString)){
			return "";
		}

		return " and " + field + " in (" + personString + ")";
	}
	
	private static String getPersonString(boolean defaultAll){
		HttpServletRequest request = ((ServletRequestAttributes)   
                RequestContextHolder.currentRequestAttributes()).getRequest();
		
		EhrUser user = RequestUtil.getLoginUser(request);
		List<Long> orgIdList = new ArrayList<Long>();
		for(EhrUserOrganization uo : getUserOrganizationMapper().listByUserId(user.getUserId())){
			orgIdList.add(uo.getOrganizationId());
		}
		
		if(orgIdList.isEmpty()){
			if(defaultAll){	//如果默认所有，则为空时返回所有
				List<EhrPerson> personList = getPersonMapper().selectAllPerson(user.getCompanyId());
				return getPersonString(personList);
			}else{
				return "";
			}
		}else{
			List<EhrPerson> personList = getPersonMapper().selectByAuthorisedOrgIdList(orgIdList);
			return getPersonString(personList);
		}
	}
	
	private static String getPersonString(List<EhrPerson> personList){
		if(personList == null || personList.isEmpty()){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for(EhrPerson person : personList){
			sb.append(",").append(person.getPersonId());
		}
		
		return sb.toString().substring(1);
	}

	/**
	 * 返回管理员可用的组织列表(默认空值)
	 * @param field
	 * @return
	 */
	public static String getUserAuthorisedOrgList(String field){
		//" and " + field + " in (" + list + ")"
		HttpServletRequest request = ((ServletRequestAttributes)   
                RequestContextHolder.currentRequestAttributes()).getRequest();
		
		EhrUser user = RequestUtil.getLoginUser(request);
		List<EhrUserOrganization> uoList = getUserOrganizationMapper().listByUserId(user.getUserId());
		if(uoList.isEmpty()){
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for(EhrUserOrganization uo : uoList){
			sb.append(",").append(uo.getOrganizationId());
		}
		
		return " and " + field + " in (" + sb.toString().substring(1) + ")";
	}
	
//	/**
//	 * 返回组织可用的用户列表
//	 * @return
//	 */
//	public static String getOrgAuthorisedPersonList(){
//		return "";
//	}
}
