package com.jabava.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/common")
public class CommonLinkController {
	private static Logger log = Logger.getLogger(CommonLinkController.class);
	

	/**
	 * 通用外部连接
	 */
	@RequestMapping(value = "link/{type}", method = RequestMethod.GET)
	public String link(HttpServletResponse response,HttpServletRequest request,@PathVariable String type) throws Exception{	
		if(type == null){
			throw new Exception("请求数据为空");
		}
		request.setAttribute("type", type);
		return "common/common_link";
	}
	/**
	 * 通用外部功能申请
	 */
	@RequestMapping(value = "plan/{type}", method = RequestMethod.GET)
	public String plan(HttpServletResponse response,HttpServletRequest request,@PathVariable String type) throws Exception{	
		if(type == null){
			throw new Exception("请求数据为空");
		}
		request.setAttribute("type", type);
		return "common/" + type + "_plan";
	}

}
