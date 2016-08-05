package com.jabava.service.announcement.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * HRO 增减变实体类
 *
 * @version $Id: OrderModel.java, 
 * v 0.1 2016年1月12日 下午2:20:26 
 * <pre>
 * @author steven.chen
 * @date 2016年1月12日 下午2:20:26 
 * </pre>
 */
public class OrderModel {
	
	private  String employeeName;//员工姓名
	
	private Integer cardType;//证件类型  必填 1、身份证 2、军人证  3、港澳身份证  4、台胞证  5、护照  9 、其他
	
	private String cardId;//证件ID  必填
	
	private String hireDate;//入职时间(yyyyMMdd)	增员必填
	
	private String hireRemark;//入职备注
	
	private String mobile;//手机
	
	private String phone;//电话
	
	private BigDecimal sbBase;//社保基数 增员、变更必填
	
	private BigDecimal gjjBase;//公积金基数 增员、变更必填
	
	private BigDecimal gjjCompanyRatio;//公积金个人比例  增员、变更必填
	
	private BigDecimal gjjIndividualRatio ;//公积金企业比例 增员、变更必填
	
	private String gjjAccount;//公积金账号
	
	private String sbGroupName;//参保类型（社保组） 增员、变更必填
	
	private String paymentMonth;//服务月(yyyyMM) 必填
	
	private String billMonth;//账单月(yyyyMM) 必填
	
	private String sbMonth;//社保发生月(yyyyMM)  必填
	
	private String dimissionType;//离职原因   可以不填（智阳给默认值）
	
	private String dimissionDate;//离职日期(yyyyMMdd) 减员必填
	
	private String dimissionRemark;//离职备注
	
	private Integer handleType;//办理方类型    增员必填	1、 大户；	2、 单立户
	
	private Integer operateType;//操作类型1 、 增员	2、 变更	3、 减员
	
	private BigDecimal gjjComRatio;
	private BigDecimal gjjIndRatio;
	private String sendRemark;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getHireRemark() {
		return hireRemark;
	}

	public void setHireRemark(String hireRemark) {
		this.hireRemark = hireRemark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getSbBase() {
		return sbBase;
	}

	public void setSbBase(BigDecimal sbBase) {
		this.sbBase = sbBase;
	}

	public BigDecimal getGjjBase() {
		return gjjBase;
	}

	public void setGjjBase(BigDecimal gjjBase) {
		this.gjjBase = gjjBase;
	}

	public BigDecimal getGjjCompanyRatio() {
		return gjjCompanyRatio;
	}

	public void setGjjCompanyRatio(BigDecimal gjjCompanyRatio) {
		this.gjjCompanyRatio = gjjCompanyRatio;
	}

	public BigDecimal getGjjIndividualRatio() {
		return gjjIndividualRatio;
	}

	public void setGjjIndividualRatio(BigDecimal gjjIndividualRatio) {
		this.gjjIndividualRatio = gjjIndividualRatio;
	}

	public String getGjjAccount() {
		return gjjAccount;
	}

	public void setGjjAccount(String gjjAccount) {
		this.gjjAccount = gjjAccount;
	}

	public String getSbGroupName() {
		return sbGroupName;
	}

	public void setSbGroupName(String sbGroupName) {
		this.sbGroupName = sbGroupName;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getSbMonth() {
		return sbMonth;
	}

	public void setSbMonth(String sbMonth) {
		this.sbMonth = sbMonth;
	}

	public String getDimissionType() {
		return dimissionType;
	}

	public void setDimissionType(String dimissionType) {
		this.dimissionType = dimissionType;
	}

	public String getDimissionDate() {
		return dimissionDate;
	}

	public void setDimissionDate(String dimissionDate) {
		this.dimissionDate = dimissionDate;
	}

	public String getDimissionRemark() {
		return dimissionRemark;
	}

	public void setDimissionRemark(String dimissionRemark) {
		this.dimissionRemark = dimissionRemark;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public BigDecimal getGjjComRatio() {
		return gjjComRatio;
	}

	public void setGjjComRatio(BigDecimal gjjComRatio) {
		this.gjjComRatio = gjjComRatio;
	}

	public BigDecimal getGjjIndRatio() {
		return gjjIndRatio;
	}

	public void setGjjIndRatio(BigDecimal gjjIndRatio) {
		this.gjjIndRatio = gjjIndRatio;
	}

	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}

}
