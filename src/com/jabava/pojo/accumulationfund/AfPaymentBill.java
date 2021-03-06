package com.jabava.pojo.accumulationfund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jabava.utils.enums.SsAfEnum;

public class AfPaymentBill {
	public AfPaymentBill(){
		
	}
	
	public AfPaymentBill(Long companyId, Long accumulationFundAccountId, String month){
		this.companyId = companyId;
		this.accumulationFundAccountId = accumulationFundAccountId;
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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.af_payment_bill_id
     *
     * @mbggenerated
     */
    private Long afPaymentBillId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.accumulation_fund_account_id
     *
     * @mbggenerated
     */
    private Long accumulationFundAccountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.accumulation_fund_account_name
     *
     * @mbggenerated
     */
    private String accumulationFundAccountName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.company_id
     *
     * @mbggenerated
     */
    private Long companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.month
     *
     * @mbggenerated
     */
    private String month;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.amount_e
     *
     * @mbggenerated
     */
    private BigDecimal amountE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.amount_p
     *
     * @mbggenerated
     */
    private BigDecimal amountP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_num
     *
     * @mbggenerated
     */
    private Integer hjNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_base_e
     *
     * @mbggenerated
     */
    private BigDecimal hjBaseE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_base_p
     *
     * @mbggenerated
     */
    private BigDecimal hjBaseP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_amount_e
     *
     * @mbggenerated
     */
    private BigDecimal hjAmountE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_amount_p
     *
     * @mbggenerated
     */
    private BigDecimal hjAmountP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.hj_amount
     *
     * @mbggenerated
     */
    private BigDecimal hjAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_num
     *
     * @mbggenerated
     */
    private Integer bjNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_base_e
     *
     * @mbggenerated
     */
    private BigDecimal bjBaseE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_base_p
     *
     * @mbggenerated
     */
    private BigDecimal bjBaseP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_amount_e
     *
     * @mbggenerated
     */
    private BigDecimal bjAmountE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_amount_p
     *
     * @mbggenerated
     */
    private BigDecimal bjAmountP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.bj_amount
     *
     * @mbggenerated
     */
    private BigDecimal bjAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.update_user_id
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;
    
    private List<AfPaymentBillPerson> personList = new ArrayList<AfPaymentBillPerson>();

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.af_payment_bill_id
     *
     * @return the value of af_payment_bill.af_payment_bill_id
     *
     * @mbggenerated
     */
    public Long getAfPaymentBillId() {
        return afPaymentBillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.af_payment_bill_id
     *
     * @param afPaymentBillId the value for af_payment_bill.af_payment_bill_id
     *
     * @mbggenerated
     */
    public void setAfPaymentBillId(Long afPaymentBillId) {
        this.afPaymentBillId = afPaymentBillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.accumulation_fund_account_id
     *
     * @return the value of af_payment_bill.accumulation_fund_account_id
     *
     * @mbggenerated
     */
    public Long getAccumulationFundAccountId() {
        return accumulationFundAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.accumulation_fund_account_id
     *
     * @param accumulationFundAccountId the value for af_payment_bill.accumulation_fund_account_id
     *
     * @mbggenerated
     */
    public void setAccumulationFundAccountId(Long accumulationFundAccountId) {
        this.accumulationFundAccountId = accumulationFundAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.accumulation_fund_account_name
     *
     * @return the value of af_payment_bill.accumulation_fund_account_name
     *
     * @mbggenerated
     */
    public String getAccumulationFundAccountName() {
        return accumulationFundAccountName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.accumulation_fund_account_name
     *
     * @param accumulationFundAccountName the value for af_payment_bill.accumulation_fund_account_name
     *
     * @mbggenerated
     */
    public void setAccumulationFundAccountName(String accumulationFundAccountName) {
        this.accumulationFundAccountName = accumulationFundAccountName == null ? null : accumulationFundAccountName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.company_id
     *
     * @return the value of af_payment_bill.company_id
     *
     * @mbggenerated
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.company_id
     *
     * @param companyId the value for af_payment_bill.company_id
     *
     * @mbggenerated
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.month
     *
     * @return the value of af_payment_bill.month
     *
     * @mbggenerated
     */
    public String getMonth() {
        return month;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.month
     *
     * @param month the value for af_payment_bill.month
     *
     * @mbggenerated
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.status
     *
     * @return the value of af_payment_bill.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.status
     *
     * @param status the value for af_payment_bill.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.amount_e
     *
     * @return the value of af_payment_bill.amount_e
     *
     * @mbggenerated
     */
    public BigDecimal getAmountE() {
        return amountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.amount_e
     *
     * @param amountE the value for af_payment_bill.amount_e
     *
     * @mbggenerated
     */
    public void setAmountE(BigDecimal amountE) {
        this.amountE = amountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.amount_p
     *
     * @return the value of af_payment_bill.amount_p
     *
     * @mbggenerated
     */
    public BigDecimal getAmountP() {
        return amountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.amount_p
     *
     * @param amountP the value for af_payment_bill.amount_p
     *
     * @mbggenerated
     */
    public void setAmountP(BigDecimal amountP) {
        this.amountP = amountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_num
     *
     * @return the value of af_payment_bill.hj_num
     *
     * @mbggenerated
     */
    public Integer getHjNum() {
        return hjNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_num
     *
     * @param hjNum the value for af_payment_bill.hj_num
     *
     * @mbggenerated
     */
    public void setHjNum(Integer hjNum) {
        this.hjNum = hjNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_base_e
     *
     * @return the value of af_payment_bill.hj_base_e
     *
     * @mbggenerated
     */
    public BigDecimal getHjBaseE() {
        return hjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_base_e
     *
     * @param hjBaseE the value for af_payment_bill.hj_base_e
     *
     * @mbggenerated
     */
    public void setHjBaseE(BigDecimal hjBaseE) {
        this.hjBaseE = hjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_base_p
     *
     * @return the value of af_payment_bill.hj_base_p
     *
     * @mbggenerated
     */
    public BigDecimal getHjBaseP() {
        return hjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_base_p
     *
     * @param hjBaseP the value for af_payment_bill.hj_base_p
     *
     * @mbggenerated
     */
    public void setHjBaseP(BigDecimal hjBaseP) {
        this.hjBaseP = hjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_amount_e
     *
     * @return the value of af_payment_bill.hj_amount_e
     *
     * @mbggenerated
     */
    public BigDecimal getHjAmountE() {
        return hjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_amount_e
     *
     * @param hjAmountE the value for af_payment_bill.hj_amount_e
     *
     * @mbggenerated
     */
    public void setHjAmountE(BigDecimal hjAmountE) {
        this.hjAmountE = hjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_amount_p
     *
     * @return the value of af_payment_bill.hj_amount_p
     *
     * @mbggenerated
     */
    public BigDecimal getHjAmountP() {
        return hjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_amount_p
     *
     * @param hjAmountP the value for af_payment_bill.hj_amount_p
     *
     * @mbggenerated
     */
    public void setHjAmountP(BigDecimal hjAmountP) {
        this.hjAmountP = hjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.hj_amount
     *
     * @return the value of af_payment_bill.hj_amount
     *
     * @mbggenerated
     */
    public BigDecimal getHjAmount() {
        return hjAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.hj_amount
     *
     * @param hjAmount the value for af_payment_bill.hj_amount
     *
     * @mbggenerated
     */
    public void setHjAmount(BigDecimal hjAmount) {
        this.hjAmount = hjAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_num
     *
     * @return the value of af_payment_bill.bj_num
     *
     * @mbggenerated
     */
    public Integer getBjNum() {
        return bjNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_num
     *
     * @param bjNum the value for af_payment_bill.bj_num
     *
     * @mbggenerated
     */
    public void setBjNum(Integer bjNum) {
        this.bjNum = bjNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_base_e
     *
     * @return the value of af_payment_bill.bj_base_e
     *
     * @mbggenerated
     */
    public BigDecimal getBjBaseE() {
        return bjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_base_e
     *
     * @param bjBaseE the value for af_payment_bill.bj_base_e
     *
     * @mbggenerated
     */
    public void setBjBaseE(BigDecimal bjBaseE) {
        this.bjBaseE = bjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_base_p
     *
     * @return the value of af_payment_bill.bj_base_p
     *
     * @mbggenerated
     */
    public BigDecimal getBjBaseP() {
        return bjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_base_p
     *
     * @param bjBaseP the value for af_payment_bill.bj_base_p
     *
     * @mbggenerated
     */
    public void setBjBaseP(BigDecimal bjBaseP) {
        this.bjBaseP = bjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_amount_e
     *
     * @return the value of af_payment_bill.bj_amount_e
     *
     * @mbggenerated
     */
    public BigDecimal getBjAmountE() {
        return bjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_amount_e
     *
     * @param bjAmountE the value for af_payment_bill.bj_amount_e
     *
     * @mbggenerated
     */
    public void setBjAmountE(BigDecimal bjAmountE) {
        this.bjAmountE = bjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_amount_p
     *
     * @return the value of af_payment_bill.bj_amount_p
     *
     * @mbggenerated
     */
    public BigDecimal getBjAmountP() {
        return bjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_amount_p
     *
     * @param bjAmountP the value for af_payment_bill.bj_amount_p
     *
     * @mbggenerated
     */
    public void setBjAmountP(BigDecimal bjAmountP) {
        this.bjAmountP = bjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.bj_amount
     *
     * @return the value of af_payment_bill.bj_amount
     *
     * @mbggenerated
     */
    public BigDecimal getBjAmount() {
        return bjAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.bj_amount
     *
     * @param bjAmount the value for af_payment_bill.bj_amount
     *
     * @mbggenerated
     */
    public void setBjAmount(BigDecimal bjAmount) {
        this.bjAmount = bjAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.create_user_id
     *
     * @return the value of af_payment_bill.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.create_user_id
     *
     * @param createUserId the value for af_payment_bill.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.create_date
     *
     * @return the value of af_payment_bill.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.create_date
     *
     * @param createDate the value for af_payment_bill.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.update_user_id
     *
     * @return the value of af_payment_bill.update_user_id
     *
     * @mbggenerated
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.update_user_id
     *
     * @param updateUserId the value for af_payment_bill.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.update_date
     *
     * @return the value of af_payment_bill.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.update_date
     *
     * @param updateDate the value for af_payment_bill.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill.is_deleted
     *
     * @return the value of af_payment_bill.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill.is_deleted
     *
     * @param isDeleted the value for af_payment_bill.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
	
	public void addPerson(AfPaymentBillPerson person){
		this.personList.add(person);
	}

	public List<AfPaymentBillPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<AfPaymentBillPerson> personList) {
		this.personList = personList;
	}
}