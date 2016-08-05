package com.jabava.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HrHelperFileUploader {
	// public static final String serverUrl = "http://neice.ezhiyang.com";
	public static final String serverUrl = "http://hro.ezhiyang.com";

	public static final String fileUploaderUrl = "/dorado/uploader/fileupload";

	public static String upload(String filePath) {
		return upload(filePath, null, null);
	}
	
	public static String upload(String filePath, Map<String,Object> rtnMsgMap) {
		return upload(filePath, null, rtnMsgMap);
	}
	
//	public static String upload(String filePath, String filename) {
//		return upload(filePath, filename, null);
//	}

	public static String upload(String filePath, String filename, Map<String,Object> rtnMsgMap) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(serverUrl + fileUploaderUrl);

			File file = new File(filePath);

			if (StringUtils.isEmpty(filename)) {
				filename = file.getName();
			}

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("fileName", URLEncoder.encode(filename, "UTF-8"));
			builder.addBinaryBody("file", file);

			builder.addTextBody("modulFrom", "OPEN API");
			builder.addTextBody("fileClass", "100");	//最大20M
			builder.addTextBody("_fileResolver", "uploadFileProcessor#processAnonymous");

			builder.setCharset(Charset.forName("UTF-8"));
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse result = httpClient.execute(httpPost);

			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};
			Map<String, Object> jsonResult = mapper.readValue(json, typeRef);
			if(rtnMsgMap != null){
				rtnMsgMap.putAll(jsonResult);
			}
			System.out.println(jsonResult);
			String fileUrl = (String) jsonResult.get("realUrl");
			return fileUrl;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(upload("E:\\files\\1453108949896.jpeg"));
	}
}
