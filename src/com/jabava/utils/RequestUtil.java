package com.jabava.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.pojo.manage.EhrRole;
import com.jabava.pojo.manage.EhrUser;

public class RequestUtil {
	public static EhrUser getLoginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (EhrUser)session.getAttribute(Constants.LOGIN_USER);
	}
	
	public static boolean hasRoleOfName(String roleName){
		if(StringUtils.isEmpty(roleName)){
			return false;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		List<EhrRole> roleList = (List<EhrRole>)session.getAttribute("ROLE");
		if(roleList != null && !roleList.isEmpty()){
			for(EhrRole role : roleList){
				if(role.getRoleName().equals(roleName)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean hasRight(Integer menuId){
		if(menuId == null){
			return false;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
//		List<EhrRole> roleList = (List<EhrRole>)session.getAttribute("");
//		if(roleList != null && !roleList.isEmpty()){
//			for(EhrRole role : roleList){
//				if(role.getRoleName().equals(roleName)){
//					return true;
//				}
//			}
//		}
		
		return false;
	}
}
