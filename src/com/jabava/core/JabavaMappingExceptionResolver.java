package com.jabava.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.jabava.utils.RequestUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class JabavaMappingExceptionResolver extends	SimpleMappingExceptionResolver {
	private Logger log = Logger.getLogger(JabavaMappingExceptionResolver.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
		
		log.error("请求的资源出错：" + request.getRequestURL());
		ex.printStackTrace();
		
        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(ex, request);
        if (viewName != null) {	// JSON格式返回
            if (request.getHeader("accept").indexOf("application/json") > -1 || RequestUtil.isAjaxRequest(request)) {
                try {
                    PrintWriter writer = response.getWriter();
                    if(ex.getCause() instanceof MySQLIntegrityConstraintViolationException){
                    	writer.write("OperationException" + "操作失败：外键约束");
                    }else{
                    	writer.write("OperationException" + ex.getMessage());
                    }
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            } else {			// JSP格式返回
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            }
        } else {
            return null;
        }
    }
	
}
