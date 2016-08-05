package com.jabava.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jabava.core.config.JabavaPropertyCofigurer;

public class HROFetchService {

	private String uri;
	private HROFetchToken fetchToken;

	public HROFetchService(String uri, HROFetchToken fetchToken) {
		this.uri = uri;
		this.fetchToken = fetchToken;
	}
	
	public HROFetchService(String relativeAuthorizeUri, String relativeRequestUri){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(server + relativeAuthorizeUri, 
				JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		this.uri = server + relativeRequestUri;
		this.fetchToken = fetchToken;
	}

	public Map<String, Object> invoke(String type, Object entity)
			throws Exception {

		// 构建POST请求
		HttpPost httpost = new HttpPost(uri);
		httpost.addHeader("Content-Type", "application/json");

		Map<String, Object> tokenInfo = fetchToken.fetch();

		// 添加Header 验证信息
		httpost.addHeader("Authorization",
				"Bearer " + tokenInfo.get("access_token"));

		// 添加BODY，固定值
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", type);
		body.put("data", entity);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(body);
		HttpEntity httpEntity = new StringEntity(json, "UTF-8");
		httpost.setEntity(httpEntity);

		// 发送请求
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// 打印返回值
		Map<String, Object> map = new HashMap<String, Object>();
		HttpEntity he = response.getEntity();
		String resp = EntityUtils.toString(he, "UTF-8");
		if (StringUtils.isNotEmpty(resp)) {
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};
			map = mapper.readValue(resp, typeRef);
		}
		httpost.reset();
		EntityUtils.consume(he);
		response.close();
		httpclient.close();
		return map;
	}

	public static Map<String, Object> invoke(String url) throws Exception {

		// 构建POST请求
		HttpGet httpost = new HttpGet(url);
		httpost.addHeader("Content-Type", "application/json");

		// 发送请求
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// 打印返回值
		Map<String, Object> map = new HashMap<String, Object>();
		HttpEntity he = response.getEntity();
		String resp = EntityUtils.toString(he, "UTF-8");
		if (StringUtils.isNotEmpty(resp)) {
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};
			map = new ObjectMapper().readValue(resp, typeRef);
		}
		httpost.reset();
		EntityUtils.consume(he);
		response.close();
		httpclient.close();
		return map;
	}

	public static Map<String, Object> callHRO(String token,
			Map<String, Object> entity, String protocolType) throws Exception {
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"), 
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		HROFetchService requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
		Map<String, Object> result = requestService.invoke(protocolType, entity);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("clientName", "test客户");
//		map.put("contractName", "test合同");
//		map.put("telephoneNumber", "444555");
//		map.put("contactEmp", "托尔斯泰");
//		map.put("remark", "啊啊啊");
//		map.put("dataSource ", "EHR");
//		Map<String, Object> result = HROFetchService.callHRO(null, map,
//				"applyContract");
		/*map.put("protocolCode", "ZY-EHR-20160106-0002");
		Map<String, Object> result = HROFetchService.callHRO(null, map,
				"getProtocolCodeState");
		System.out.println(result);*/
//		String server="http://neice.ezhiyang.com";
//		HROFetchToken fetchToken = new HROFetchToken(server + "/open/authorize", "dayhr","0f9efee4-303e-11e5-8800-f39b0ce86986");
//		
//		HROFetchService requestService = new HROFetchService(server + "/open/rest", fetchToken);
//		
//		Map<String, Object> parameter = new HashMap<String, Object>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		Map<String, Object> data1 = new HashMap<String, Object>();
//
//		Map<String, Object> data2 = new HashMap<String, Object>();
//
//		parameter.put("PROTOCOL_CODE", "ZY-DH-20150721-0001");
//		parameter.put("BILL_YM", "201405");
//		parameter.put("STATUS_BILL", "v");
//
//		parameter.put("code", "billquery");
//
//		data.put("parameter", parameter);
//		data1.put("data", data);		
//		data2.put("data",data);
//		data2.put("type", "SqlQuery");
//		
//		System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(data2));
//		System.out.println("data"+data2);
//		Map<String, Object> result=null;
		
		try {
			//result = requestService.invoke("SqlQuery", data);
			System.out.println(invoke("http://king.ezhiyang.com/api/SalaryQueryByCardIdAndMonth.do?cardId=110101201607058814&month=" + 201605));
			System.out.println(invoke("http://king.ezhiyang.com/api/SocialInsuranceController.do?cardId=110101201607058814&month=" + 201609));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
