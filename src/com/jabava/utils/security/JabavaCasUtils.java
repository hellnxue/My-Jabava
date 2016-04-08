package com.jabava.utils.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.jabava.utils.ssl.HttpClientConnectionManager;

/**
 * 
 * @ClassName: JabavaCasUtils
 * @Description: 模拟CAS单点登录
 * @author hsx
 * @date 2016年2月24日 下午3:55:18
 */
public class JabavaCasUtils {
	private Logger log = Logger.getLogger(JabavaCasUtils.class);
	
	private final int BUFFER_SIZE = 1024 * 1024;
	
	/**
	 * 执行cas登录，获取CASTGC cookie
	 * @param server
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public String getLoginUrl(final String server,
			final String username, final String password) throws IOException {
	
		// 接受任何证书的浏览器客户端
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);

		String content = doCasLogin(httpclient, server);
		if(StringUtils.isEmpty(content)){
			return null;
		}
		
		String location = null;
		for(int i=0; i<10; i++){
			HashMap<String, String> result = parse(content);
			location = doLogin(httpclient, server, result, username, password);
			if(!StringUtils.isEmpty(location)){
				return location;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param httpclient
	 * @param url
	 * @return
	 */
	private String doCasLogin(DefaultHttpClient httpclient, String url) {
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			InputStream stream = null;
			stream = response.getEntity().getContent();
			return inputStreamTOString(stream, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用提供server，cas参数(lt，execution)，用户名和密码，实行post操作，模拟CAS登录，返回Location值
	 * @param client
	 * @param server
	 * @param param
	 * @param username
	 * @param password
	 * @return
	 */
	private String doLogin(DefaultHttpClient client, String server, HashMap<String, String> param, String username, String password){
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("lt", param.get("lt")));
		nvps.add(new BasicNameValuePair("execution", param.get("execution")));
		nvps.add(new BasicNameValuePair("_eventId", "submit"));
		
		try {
			HttpPost post = new HttpPost(server);
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(post);
			
			Header locationHeader = response.getFirstHeader("Location");
			if(locationHeader != null){
				log.debug("==========" + locationHeader.getName() + ":" + locationHeader.getValue());
				return locationHeader.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 分析文本，获取lt和execution值
	 * @param content
	 * @return
	 */
	private HashMap<String, String> parse(String content){
		String s = "<input type=\"hidden\" name=\"lt\" value=\"";
		String q = "<input type=\"hidden\" name=\"execution\" value=\"";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		try{
			int index = content.indexOf(s);
			String s1 = content.substring(index + s.length());
			int index1 = s1.indexOf("\"");
			if (index1 != -1)
				map.put("lt", s1.substring(0, index1));
			
			int indexQ = content.indexOf(q);
			String s1Q = content.substring(indexQ + q.length());
			int indexQ1 = s1Q.indexOf("\"");
			if (indexQ1 != -1)
				map.put("execution", s1Q.substring(0, indexQ1));
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 流转换字符串
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	private String inputStreamTOString(InputStream in, String encoding)
			throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		String temp = new String(outStream.toByteArray(), encoding);
		outStream.close();
		return temp;
	}
	
	public static void main(String []args){
		try {
			JabavaCasUtils cas = new JabavaCasUtils();
			String url = "http://sso.ezhiyang.com/cas-server/login?type=jabava&service=http://172.16.1.8:8080/Jabava/j_spring_cas_security_check";
			System.out.println("-----" + cas.getLoginUrl(url, "13817083093", "111111"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
