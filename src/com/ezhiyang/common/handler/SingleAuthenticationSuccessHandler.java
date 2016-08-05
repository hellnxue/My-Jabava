package com.ezhiyang.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jabava.controller.manage.UserController;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.service.provider.entity.OrgUser;

public class SingleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private Logger log = Logger.getLogger(SingleAuthenticationSuccessHandler.class);
	
	// TODO 抽取都公共变量中
	public static final String LOGIN_CHANNEL_MOBILE = "mobile";
	public static final String LOGIN_CHANNEL_PC = "pc";
	
	// TODO 抽取到公共变量中
	public static final String HOME_PAGE_MOBILE = "employee/mobileInfoCollect";
	public static final String HOME_PAGE_PC = "to_index";
	public static final String HOME_LOGIN = "login";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		UserController uc = wac.getBean(UserController.class);
		
		try {
			boolean ret = false;
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				OrgUser user = (OrgUser)authentication.getPrincipal();
				ret = uc.doLogin(user, request);
			}else{
				UserDetails user = (UserDetails)authentication.getPrincipal();
				ret = uc.doLogin(user.getUsername(), request);
			}
			
			String channel = request.getParameter("channel");
			if(LOGIN_CHANNEL_MOBILE.equals(channel)){
				response.sendRedirect(HOME_PAGE_MOBILE);
			}else{
				if(!ret){
					log.error("权限校验失败，不能登录哦!!!");
					response.sendRedirect(HOME_LOGIN);
				}else{
					response.sendRedirect(HOME_PAGE_PC);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
