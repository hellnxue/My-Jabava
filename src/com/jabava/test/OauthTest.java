package com.jabava.test;

import java.util.HashMap;
import java.util.Map;

import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;

public class OauthTest {
	public static void main(String[] args) throws Exception{
		String server =  "http://localhost:8080/Jabava";
		HROFetchToken fetchToken = null;
		HROFetchService requestService = null;
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> result = null;
		
		fetchToken = new HROFetchToken(server + "/api/authorize", "hro", "12345678");
		requestService = new HROFetchService(server	+ "/api/protocol/testToken", fetchToken);
		result = requestService.invoke("abc", params);
		System.out.println(result);
	}
}
