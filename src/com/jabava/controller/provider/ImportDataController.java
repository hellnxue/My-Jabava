package com.jabava.controller.provider;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.service.oauth.OAuthService;
import com.jabava.utils.MessageUtil;

/**
 * 接收数据
 * 
 */
@Controller
@RequestMapping("api/importData")
public class ImportDataController {
	private Logger log = Logger.getLogger(ImportDataController.class);
	
	@Autowired
	private OAuthService oAuthService;

	@RequestMapping("importFormData")
	@ResponseBody
	public Map<String, Object> importFormData(@RequestBody JSONObject paramJson) {
		Map<String, Object> result = null;
		log.info(paramJson.toString());
		
		return MessageUtil.successProviderMessage("导入成功");
	}

	@RequestMapping("importFormStream")
	@ResponseBody
	public Map<String, Object> importFormData(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    InputStream in = request.getInputStream();
	    String data = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
	    log.info(data);
	    
	    Map dataMap = com.alibaba.fastjson.JSON.parseObject(data,Map.class);
	    

		return MessageUtil.successProviderMessage("导入成功");
	}
}
