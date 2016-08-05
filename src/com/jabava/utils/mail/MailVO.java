package com.jabava.utils.mail;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class MailVO implements Serializable {
	
	private static final long serialVersionUID = -9039536846551912885L;
	
	private String subject;
	private String content;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private List<File> attachList;
	
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
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getCc() {
		return cc;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public String getBcc() {
		return bcc;
	}
	
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	public List<File> getAttachList() {
		return attachList;
	}
	
	public void setAttachList(List<File> attachList) {
		this.attachList = attachList;
	}
	
}
