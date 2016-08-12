package com.jabava.pojo.system;

import java.io.Serializable;
import java.util.List;

public class MailProperties implements Serializable{

	private static final long serialVersionUID = -6085925813835417739L;
	
	/**
	 * SMTP服务器地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否使用安全协议SSL
	 */
	private boolean useSSL;
	/**
	 * 协议类型
	 */
	private String type;
	/**
	 * 收件人
	 */
	private List<String> sendTo;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String content;
	
	public MailProperties(){}
	
	
	public MailProperties(String host, String port, String userName,
			String password, List<String> sendTo, String subject, String content) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.sendTo = sendTo;
		this.subject = subject;
		this.content = content;
	}


	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isUseSSL() {
		return useSSL;
	}


	public void setUseSSL(boolean useSSL) {
		this.useSSL = useSSL;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<String> getSendTo() {
		return sendTo;
	}
	public void setSendTo(List<String> sendTo) {
		this.sendTo = sendTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
