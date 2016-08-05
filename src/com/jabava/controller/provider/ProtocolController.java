package com.jabava.controller.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.oauth.OauthToken;
import com.jabava.service.oauth.OAuthService;
import com.jabava.service.provider.ProtocolService;
import com.jabava.utils.MessageUtil;

/**
 * 协议接收Controller
 * 
 */
@Controller
@RequestMapping("api/protocol")
public class ProtocolController {
	@Autowired
	private ProtocolService protocolService;
	
	@Autowired
	private OAuthService oAuthService;

	@RequestMapping("syncProtocol")
	@ResponseBody
	public Map<String, Object> syncProtocol(@RequestBody JSONObject paramJson) {
		Map<String, Object> result = null;
		try {
			result = protocolService.syncProtocol(paramJson);
		} catch (Exception e) {
			e.printStackTrace();
			result = MessageUtil.errorProviderMessage(e.getMessage());
		}
		
		return result;
	}

	@RequestMapping("queryProtocol")
	@ResponseBody
	public Map<String, Object> queryProtocol(@RequestBody JSONObject paramJson) {
		Map<String, Object> result = null;
		try {
			result = protocolService.queryProtocol(paramJson);
		} catch (Exception e) {
			e.printStackTrace();
			result = MessageUtil.errorProviderMessage(e.getMessage());
		}
		
		return result;
	}

	@RequestMapping("testToken")
	@ResponseBody
	public Map<String, Object> testToken(HttpServletRequest request, HttpServletResponse response,
			@RequestBody JSONObject paramJson) {
//		String authorization = request.getHeader("Authorization");
//		if(StringUtils.isEmpty(authorization)){
//			System.out.println("authorization is null");
//			return null;
//		}
//		
//		if(!authorization.toLowerCase().startsWith("bearer")){
//			System.out.println("authorization has no bearer");
//			return null;
//		}
//		
//		String token = authorization.substring(6).trim();
//		if("".equals(token)){
//			System.out.println("token is empty");
//			return null;
//		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		OauthToken token = null;
		try {
			token = oAuthService.validRequest(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(token == null){
			result.put("resultCode", "1");
			result.put("resultMsg", "Token验证失败");
		}else{
			result.put("resultCode", "0");
			result.put("resultMsg", "操作成功");
		}
		
		return result;
	}
}
