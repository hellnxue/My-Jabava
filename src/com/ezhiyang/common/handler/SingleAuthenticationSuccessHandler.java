package com.ezhiyang.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jabava.controller.manage.UserController;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.service.provider.entity.OrgUser;

public class SingleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		UserController uc = wac.getBean(UserController.class);
		//SysUsers user = (SysUsers)arg2.getPrincipal();  
		//uc.doLogin(orgUser.getIdentity(), orgUser.getOrgId(), request);
		
		try {
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				OrgUser user = (OrgUser)authentication.getPrincipal();
				uc.doLogin(user.getIdentity(),user.getOrgId(), request);
			}else{
				UserDetails user = (UserDetails)authentication.getPrincipal();
				uc.doLogin(user.getUsername(), request);
			}
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
