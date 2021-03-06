package com.jabava.pojo.socialsecurity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SsPaymentBillPerson {
	public SsPaymentBillPerson(){
		
	}
	
	public SsPaymentBillPerson(Long psProfileId, Long personId, String securityAccount){
		this.psProfileId = psProfileId;
		this.personId = personId;
		this.securityAccount = securityAccount;
		
		this.bjBaseE = BigDecimal.ZERO;
		this.bjBaseP = BigDecimal.ZERO;
		this.bjAmount = BigDecimal.ZERO;
		this.bjAmountE = BigDecimal.ZERO;
		this.bjAmountP = BigDecimal.ZERO;
		this.hjBaseE = BigDecimal.ZERO;
		this.hjBaseP = BigDecimal.ZERO;
		this.hjAmount = BigDecimal.ZERO;
		this.hjAmountE = BigDecimal.ZERO;
		this.hjAmountP = BigDecimal.ZERO;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.ss_payment_bill_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private Long ssPaymentBillId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.ps_profile_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private Long psProfileId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.person_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private Long personId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.security_account
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private String securityAccount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.hj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal hjBaseE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.hj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal hjBaseP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.hj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal hjAmount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.hj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal hjAmountE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.hj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal hjAmountP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.bj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal bjAmount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.bj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal bjAmountE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.bj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal bjAmountP;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.bj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal bjBaseE;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ss_payment_bill_person.bj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	private BigDecimal bjBaseP;
	
	private String personName;
	
    private List<SsPaymentBillPersonDetail> personDetailList = new ArrayList<SsPaymentBillPersonDetail>();

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.id
	 * @return  the value of ss_payment_bill_person.id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.id
	 * @param id  the value for ss_payment_bill_person.id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.ss_payment_bill_id
	 * @return  the value of ss_payment_bill_person.ss_payment_bill_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public Long getSsPaymentBillId() {
		return ssPaymentBillId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.ss_payment_bill_id
	 * @param ssPaymentBillId  the value for ss_payment_bill_person.ss_payment_bill_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setSsPaymentBillId(Long ssPaymentBillId) {
		this.ssPaymentBillId = ssPaymentBillId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.ps_profile_id
	 * @return  the value of ss_payment_bill_person.ps_profile_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public Long getPsProfileId() {
		return psProfileId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.ps_profile_id
	 * @param psProfileId  the value for ss_payment_bill_person.ps_profile_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setPsProfileId(Long psProfileId) {
		this.psProfileId = psProfileId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.person_id
	 * @return  the value of ss_payment_bill_person.person_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.person_id
	 * @param personId  the value for ss_payment_bill_person.person_id
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.security_account
	 * @return  the value of ss_payment_bill_person.security_account
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public String getSecurityAccount() {
		return securityAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.security_account
	 * @param securityAccount  the value for ss_payment_bill_person.security_account
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setSecurityAccount(String securityAccount) {
		this.securityAccount = securityAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.hj_base_e
	 * @return  the value of ss_payment_bill_person.hj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getHjBaseE() {
		return hjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.hj_base_e
	 * @param hjBaseE  the value for ss_payment_bill_person.hj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setHjBaseE(BigDecimal hjBaseE) {
		this.hjBaseE = hjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.hj_base_p
	 * @return  the value of ss_payment_bill_person.hj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getHjBaseP() {
		return hjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.hj_base_p
	 * @param hjBaseP  the value for ss_payment_bill_person.hj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setHjBaseP(BigDecimal hjBaseP) {
		this.hjBaseP = hjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.hj_amount
	 * @return  the value of ss_payment_bill_person.hj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getHjAmount() {
		return hjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.hj_amount
	 * @param hjAmount  the value for ss_payment_bill_person.hj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setHjAmount(BigDecimal hjAmount) {
		this.hjAmount = hjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.hj_amount_e
	 * @return  the value of ss_payment_bill_person.hj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getHjAmountE() {
		return hjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.hj_amount_e
	 * @param hjAmountE  the value for ss_payment_bill_person.hj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setHjAmountE(BigDecimal hjAmountE) {
		this.hjAmountE = hjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.hj_amount_p
	 * @return  the value of ss_payment_bill_person.hj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getHjAmountP() {
		return hjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.hj_amount_p
	 * @param hjAmountP  the value for ss_payment_bill_person.hj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setHjAmountP(BigDecimal hjAmountP) {
		this.hjAmountP = hjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.bj_amount
	 * @return  the value of ss_payment_bill_person.bj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getBjAmount() {
		return bjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.bj_amount
	 * @param bjAmount  the value for ss_payment_bill_person.bj_amount
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setBjAmount(BigDecimal bjAmount) {
		this.bjAmount = bjAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.bj_amount_e
	 * @return  the value of ss_payment_bill_person.bj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getBjAmountE() {
		return bjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.bj_amount_e
	 * @param bjAmountE  the value for ss_payment_bill_person.bj_amount_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setBjAmountE(BigDecimal bjAmountE) {
		this.bjAmountE = bjAmountE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.bj_amount_p
	 * @return  the value of ss_payment_bill_person.bj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getBjAmountP() {
		return bjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.bj_amount_p
	 * @param bjAmountP  the value for ss_payment_bill_person.bj_amount_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setBjAmountP(BigDecimal bjAmountP) {
		this.bjAmountP = bjAmountP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.bj_base_e
	 * @return  the value of ss_payment_bill_person.bj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getBjBaseE() {
		return bjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.bj_base_e
	 * @param bjBaseE  the value for ss_payment_bill_person.bj_base_e
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setBjBaseE(BigDecimal bjBaseE) {
		this.bjBaseE = bjBaseE;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ss_payment_bill_person.bj_base_p
	 * @return  the value of ss_payment_bill_person.bj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public BigDecimal getBjBaseP() {
		return bjBaseP;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ss_payment_bill_person.bj_base_p
	 * @param bjBaseP  the value for ss_payment_bill_person.bj_base_p
	 * @mbggenerated  Wed Apr 20 20:01:30 CST 2016
	 */
	public void setBjBaseP(BigDecimal bjBaseP) {
		this.bjBaseP = bjBaseP;
	}

    public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void addPersonDetail(SsPaymentBillPersonDetail pd){
    	this.personDetailList.add(pd);
    }

	public List<SsPaymentBillPersonDetail> getPersonDetailList() {
		return personDetailList;
	}

	public void setPersonDetailList(List<SsPaymentBillPersonDetail> personDetailList) {
		this.personDetailList = personDetailList;
	}
}