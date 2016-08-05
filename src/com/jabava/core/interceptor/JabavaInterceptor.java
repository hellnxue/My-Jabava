package com.jabava.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jabava.core.annotation.Permission;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.RequestUtil;

public class JabavaInterceptor extends HandlerInterceptorAdapter {
	private Logger log = Logger.getLogger(JabavaInterceptor.class);

//	public String[] allowUrls;
//
//	public void setAllowUrls(String[] allowUrls) {
//		this.allowUrls = allowUrls;
//	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if("1".equals(JabavaPropertyCofigurer.getProperty("collect_data_switch"))){
			//收集数据
			RequestUtil.collectData(request);
		}
		
		//菜单权限
		if(RequestUtil.hasUrlPower(request)){
			return super.preHandle(request, response, handler);
		}
		
		//if(handler instanceof HandlerMethod){
		if(!handler.getClass().isAssignableFrom(HandlerMethod.class)){
			//对非Controller方法进行控制
			log.info(request.getServletPath());
			//可以设置白名单，保存仅需要登录不需要权限的(比如每个页面都包含的公共页面，index等)；需要权限的只允许通过Controller方法请求
			//嵌入的页面不应被访问(不单独请求)，如employee_nav.jsp
			if(!request.getServletPath().equals("/index/index") &&
					!request.getServletPath().equals("/common/404") &&
					!request.getServletPath().equals("/common/500") &&
					!request.getServletPath().equals("/common/error") &&
					!request.getServletPath().equals("/common/maintain") &&
					!request.getServletPath().equals("/common/noPermission") &&
					!request.getServletPath().equals("/common/online_frame") &&
					!request.getServletPath().startsWith("/common/direct/")){
				response.sendRedirect(request.getContextPath() + "/common/noPermission");
				return false;
			}
			
			return super.preHandle(request, response, handler);
		}
		
		HandlerMethod hm = (HandlerMethod)handler;
        Permission permission = hm.getMethodAnnotation(Permission.class);
        if(permission == null || permission.buttons() == null || permission.buttons().length == 0){
            return super.preHandle(request, response, handler);
        }
        //权限验证
        for(String powerCode : permission.buttons()){
        	if(RequestUtil.hasPower(request, powerCode)){
        		return super.preHandle(request, response, handler);
        	}
        }
        
        //未通过校验的处理
        log.error(RequestUtil.getLoginUser(request).getUserCode() + "用户没有权限请求方法" + hm.getMethod().getName());
        
        //if(RequestUtil.isAjaxRequest(request)){
        if(hm.getMethodAnnotation(ResponseBody.class) != null){
            try {
				PrintWriter out = response.getWriter();
				out.print("NoPermission");
				out.flush();
				out.close();
				//response.setHeader("Login-Status", "no");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else{
        	response.sendRedirect(request.getContextPath() + "/common/noPermission");
        }
        
        return false;
		
//		String contextPath = request.getContextPath();
//		String url = request.getRequestURL().toString();
//		if (null != allowUrls && allowUrls.length >= 1) {
//			for (String allowurl : allowUrls) {
//				if (url.contains(allowurl)) {
//					return true;
//				}
//			}
//		}
//		EhrUser user = RequestUtil.getLoginUser(request);
//		if (user == null) {
//			Logger logger = Logger.getLogger(JabavaInterceptor.class);
//			logger.info("Please login again");
//			response.sendRedirect(contextPath);
//			return false;
//		}
//		return true;
	}

//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		super.postHandle(request, response, handler, modelAndView);
//	}

}
