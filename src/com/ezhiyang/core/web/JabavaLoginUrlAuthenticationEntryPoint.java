package com.ezhiyang.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class JabavaLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint{
	public JabavaLoginUrlAuthenticationEntryPoint(String loginFormUrl){
		super(loginFormUrl);
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {
		if(request.getHeader("X-Requested-With") != null && 
				"XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())){
            PrintWriter out = response.getWriter();
            out.print("AjaxAuthFailed");
            out.flush();
            out.close();
			//response.setHeader("Login-Status", "no");
		}else{
			super.commence(request, response, ex);
		}
	}
	
}
