package com.ezhiyang.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

import com.jabava.utils.RequestUtil;

public class JabavaCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint{
	
	/**
	 * 处理认证异常(如Session过期)
	 */
	@Override
	protected void preCommence(HttpServletRequest request, HttpServletResponse response) {
		if(RequestUtil.isAjaxRequest(request)){
			//Ajax请求处理
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
			//普通请求处理
			super.preCommence(request, response);
		}
	}
	
}
