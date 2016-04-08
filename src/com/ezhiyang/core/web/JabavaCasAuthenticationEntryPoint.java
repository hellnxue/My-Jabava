package com.ezhiyang.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

public class JabavaCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint{
	
	@Override
	protected void preCommence(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getHeader("X-Requested-With") != null && 
				"XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())){
            try {
				PrintWriter out = response.getWriter();
				out.print("AjaxAuthFailed");
				out.flush();
				out.close();
				//response.setHeader("Login-Status", "no");
			} catch (IOException e) {
				e.printStackTrace();
			}
            //CasAuthenticationEntryPoint的commence方法先：
            //	final String urlEncodedService = createServiceUrl(servletRequest, response);  
            //	final String redirectUrl = createRedirectUrl(urlEncodedService);
            //	preCommence(servletRequest, response);  
            //	response.sendRedirect(redirectUrl);
            //所以当out以后会报java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
		}else{
			super.preCommence(request, response);
		}
	}
	
}
