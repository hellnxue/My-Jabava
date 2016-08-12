package com.jabava.pojo.system;

import java.io.Serializable;
import java.util.Date;

public class EHrMailConfig implements Serializable {
	
	
	private static final long serialVersionUID = 682287448918060733L;
	
	 private Long mailConfigId;
	 
	 private Long companyId;
	 
	 private String mailType;
	 
	 private String sendTo;
	 
	 private String mailPassword;
	 
	 private String mailServer;
	 
	 private String safeFlag;
	 
	 private String mailPort;
	 
	 private Date createAt;
	 
	 private String createBy;
	 
	 private Date updateAt;
	 
	 private String updateBy;

	public Long getMailConfigId() {
		return mailConfigId;
	}

	public void setMailConfigId(Long mailConfigId) {
		this.mailConfigId = mailConfigId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getSafeFlag() {
		return safeFlag;
	}

	public void setSafeFlag(String safeFlag) {
		this.safeFlag = safeFlag;
	}

	public String getMailPort() {
		return mailPort;
	}

	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	 
	 
}
