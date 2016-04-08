package com.jabava.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.RequestUtil;

/**
 * @author WangYongqiang
 * 
 */
public class JabavaInterceptor extends HandlerInterceptorAdapter {

	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		String url = request.getRequestURL().toString();
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String allowurl : allowUrls) {
				if (url.contains(allowurl)) {
					return true;
				}
			}
		}
		EhrUser user = RequestUtil.getLoginUser(request);
		if (user == null) {
			Logger logger = Logger.getLogger(JabavaInterceptor.class);
			logger.info("Please login again");
			response.sendRedirect(contextPath);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

}
