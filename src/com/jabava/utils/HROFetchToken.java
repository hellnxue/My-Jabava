package com.jabava.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HROFetchToken {
	public String authUrl;
	public String clientId;
	public String clientSecret;

	public HROFetchToken(String authUrl, String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.authUrl = authUrl;
	}

	public Map<String, Object> fetch() throws Exception {
		String auth = clientId + ":" + clientSecret; // 拼接client id 及 client
														// secret
		String encodedAuth = new String(Base64.encodeBase64(auth.getBytes())); // base64转码

		// 构建POST请求
		HttpPost httpost = new HttpPost(authUrl);
		httpost.addHeader("Content-Type", "application/x-www-form-urlencoded");

		// 添加Header 验证信息
		httpost.addHeader("Authorization", "Basic " + encodedAuth);

		// 添加BODY，固定值
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("grant_type",
				"client_credentials"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				"UTF-8");
		httpost.setEntity(entity);

		// 发送请求
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// 打印返回值
		Map<String, Object> map = new HashMap<String, Object>();
		HttpEntity he = response.getEntity();
		String resp = EntityUtils.toString(he, "UTF-8");
		if (StringUtils.isNotEmpty(resp)) {
			ObjectMapper mapper = new ObjectMapper();
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

	public static void main(String[] args) throws Exception {
		HROFetchToken ft = null;
		ft = new HROFetchToken("http://localhost:8080/Jabava/api/authorize","hro","12345678");
		System.out.println(ft.fetch());
		
		ft = new HROFetchToken("http://localhost:8080/Jabava/api/authorize","hr","12345678");
		System.out.println(ft.fetch());
		
		ft = new HROFetchToken("http://localhost:8080/Jabava/api/authorize","hro","123456");
		System.out.println(ft.fetch());
	}

}