package com.jabava.pojo.socialsecurity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jabava.utils.enums.SsAfEnum;

public class SsPaymentBill {
	public SsPaymentBill(){
		
	}
	
	public SsPaymentBill(Long companyId, Long socialSecurityAccountId, String month){
		this.companyId = companyId;
		this.socialSecurityAccountId = socialSecurityAccountId;
		this.month = month;
		
		this.status = SsAfEnum.PaymentBillStatus.Created.getValue();
		
		this.amountE = BigDecimal.ZERO;
		this.amountP = BigDecimal.ZERO;
		this.hjNum = 0;
		this.hjBaseE = BigDecimal.ZERO;
		this.hjBaseP = BigDecimal.ZERO;
		this.hjAmountE = BigDecimal.ZERO;
		this.hjAmountP = BigDecimal.ZERO;
		this.hjAmount = BigDecimal.ZERO;
		this.bjNum = 0;
		this.bjBaseE = BigDecimal.ZERO;
		this.bjBaseP = BigDecimal.ZERO;
		this.bjAmountE = BigDecimal.ZERO;
		this.bjAmountP = BigDecimal.ZERO;
		this.bjAmount = BigDecimal.ZERO;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.social_security_account_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Long socialSecurityAccountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.social_security_account_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private String socialSecurityAccountName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.company_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Long companyId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.month
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private String month;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.status
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal amountE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal amountP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Integer hjNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal hjBaseE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal hjBaseP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal hjAmountE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal hjAmountP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.hj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal hjAmount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Integer bjNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal bjBaseE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal bjBaseP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal bjAmountE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal bjAmountP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.bj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private BigDecimal bjAmount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.create_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Long createUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.create_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private String createUserName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.create_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.last_modify_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Long lastModifyUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.last_modify_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private String lastModifyUserName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.last_modify_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Date lastModifyDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill.is_deleted
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	private Integer isDeleted;
	
	private List<SsPaymentBillDetail> detailList = new ArrayList<SsPaymentBillDetail>();
	private List<SsPaymentBillPerson> personList = new ArrayList<SsPaymentBillPerson>();

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.id
	 * @return  the value of ss_payment_bill.id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.id
	 * @param id  the value for ss_payment_bill.id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.social_security_account_id
	 * @return  the value of ss_payment_bill.social_security_account_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Long getSocialSecurityAccountId() {
		return socialSecurityAccountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.social_security_account_id
	 * @param socialSecurityAccountId  the value for ss_payment_bill.social_security_account_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setSocialSecurityAccountId(Long socialSecurityAccountId) {
		this.socialSecurityAccountId = socialSecurityAccountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.social_security_account_name
	 * @return  the value of ss_payment_bill.social_security_account_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public String getSocialSecurityAccountName() {
		return socialSecurityAccountName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.social_security_account_name
	 * @param socialSecurityAccountName  the value for ss_payment_bill.social_security_account_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setSocialSecurityAccountName(String socialSecurityAccountName) {
		this.socialSecurityAccountName = socialSecurityAccountName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.company_id
	 * @return  the value of ss_payment_bill.company_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.company_id
	 * @param companyId  the value for ss_payment_bill.company_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.month
	 * @return  the value of ss_payment_bill.month
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.month
	 * @param month  the value for ss_payment_bill.month
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.status
	 * @return  the value of ss_payment_bill.status
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.status
	 * @param status  the value for ss_payment_bill.status
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.amount_e
	 * @return  the value of ss_payment_bill.amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getAmountE() {
		return amountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.amount_e
	 * @param amountE  the value for ss_payment_bill.amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setAmountE(BigDecimal amountE) {
		this.amountE = amountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.amount_p
	 * @return  the value of ss_payment_bill.amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getAmountP() {
		return amountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.amount_p
	 * @param amountP  the value for ss_payment_bill.amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setAmountP(BigDecimal amountP) {
		this.amountP = amountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_num
	 * @return  the value of ss_payment_bill.hj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Integer getHjNum() {
		return hjNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_num
	 * @param hjNum  the value for ss_payment_bill.hj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjNum(Integer hjNum) {
		this.hjNum = hjNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_base_e
	 * @return  the value of ss_payment_bill.hj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getHjBaseE() {
		return hjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_base_e
	 * @param hjBaseE  the value for ss_payment_bill.hj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjBaseE(BigDecimal hjBaseE) {
		this.hjBaseE = hjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_base_p
	 * @return  the value of ss_payment_bill.hj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getHjBaseP() {
		return hjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_base_p
	 * @param hjBaseP  the value for ss_payment_bill.hj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjBaseP(BigDecimal hjBaseP) {
		this.hjBaseP = hjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_amount_e
	 * @return  the value of ss_payment_bill.hj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getHjAmountE() {
		return hjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_amount_e
	 * @param hjAmountE  the value for ss_payment_bill.hj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjAmountE(BigDecimal hjAmountE) {
		this.hjAmountE = hjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_amount_p
	 * @return  the value of ss_payment_bill.hj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getHjAmountP() {
		return hjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_amount_p
	 * @param hjAmountP  the value for ss_payment_bill.hj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjAmountP(BigDecimal hjAmountP) {
		this.hjAmountP = hjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.hj_amount
	 * @return  the value of ss_payment_bill.hj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getHjAmount() {
		return hjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.hj_amount
	 * @param hjAmount  the value for ss_payment_bill.hj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setHjAmount(BigDecimal hjAmount) {
		this.hjAmount = hjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_num
	 * @return  the value of ss_payment_bill.bj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Integer getBjNum() {
		return bjNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_num
	 * @param bjNum  the value for ss_payment_bill.bj_num
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjNum(Integer bjNum) {
		this.bjNum = bjNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_base_e
	 * @return  the value of ss_payment_bill.bj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getBjBaseE() {
		return bjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_base_e
	 * @param bjBaseE  the value for ss_payment_bill.bj_base_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjBaseE(BigDecimal bjBaseE) {
		this.bjBaseE = bjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_base_p
	 * @return  the value of ss_payment_bill.bj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getBjBaseP() {
		return bjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_base_p
	 * @param bjBaseP  the value for ss_payment_bill.bj_base_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjBaseP(BigDecimal bjBaseP) {
		this.bjBaseP = bjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_amount_e
	 * @return  the value of ss_payment_bill.bj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getBjAmountE() {
		return bjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_amount_e
	 * @param bjAmountE  the value for ss_payment_bill.bj_amount_e
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjAmountE(BigDecimal bjAmountE) {
		this.bjAmountE = bjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_amount_p
	 * @return  the value of ss_payment_bill.bj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getBjAmountP() {
		return bjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_amount_p
	 * @param bjAmountP  the value for ss_payment_bill.bj_amount_p
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjAmountP(BigDecimal bjAmountP) {
		this.bjAmountP = bjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.bj_amount
	 * @return  the value of ss_payment_bill.bj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public BigDecimal getBjAmount() {
		return bjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.bj_amount
	 * @param bjAmount  the value for ss_payment_bill.bj_amount
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setBjAmount(BigDecimal bjAmount) {
		this.bjAmount = bjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.create_user_id
	 * @return  the value of ss_payment_bill.create_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.create_user_id
	 * @param createUserId  the value for ss_payment_bill.create_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.create_user_name
	 * @return  the value of ss_payment_bill.create_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.create_user_name
	 * @param createUserName  the value for ss_payment_bill.create_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.create_date
	 * @return  the value of ss_payment_bill.create_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.create_date
	 * @param createDate  the value for ss_payment_bill.create_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.last_modify_user_id
	 * @return  the value of ss_payment_bill.last_modify_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Long getLastModifyUserId() {
		return lastModifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.last_modify_user_id
	 * @param lastModifyUserId  the value for ss_payment_bill.last_modify_user_id
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setLastModifyUserId(Long lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.last_modify_user_name
	 * @return  the value of ss_payment_bill.last_modify_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.last_modify_user_name
	 * @param lastModifyUserName  the value for ss_payment_bill.last_modify_user_name
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.last_modify_date
	 * @return  the value of ss_payment_bill.last_modify_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.last_modify_date
	 * @param lastModifyDate  the value for ss_payment_bill.last_modify_date
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill.is_deleted
	 * @return  the value of ss_payment_bill.is_deleted
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill.is_deleted
	 * @param isDeleted  the value for ss_payment_bill.is_deleted
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public void addDetail(SsPaymentBillDetail detail){
		this.detailList.add(detail);
	}

	public List<SsPaymentBillDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SsPaymentBillDetail> detailList) {
		this.detailList = detailList;
	}
	
	public void addPerson(SsPaymentBillPerson person){
		this.personList.add(person);
	}

	public List<SsPaymentBillPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<SsPaymentBillPerson> personList) {
		this.personList = personList;
	}
}