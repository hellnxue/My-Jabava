package com.jabava.pojo.accumulationfund;

import java.math.BigDecimal;
import java.util.Date;

public class AfPaymentBillPersonDetail {
	public AfPaymentBillPersonDetail(){
		
	}
	
	public AfPaymentBillPersonDetail(Long sbGroupDetailId){
		this.sbGroupDetailId = sbGroupDetailId;
		this.amountE = BigDecimal.ZERO;
		this.amountP = BigDecimal.ZERO;
		this.baseE = BigDecimal.ZERO;
		this.baseP = BigDecimal.ZERO;
		this.bjAmountE = BigDecimal.ZERO;
		this.bjAmountP = BigDecimal.ZERO;
		this.bjBaseE = BigDecimal.ZERO;
		this.bjBaseP = BigDecimal.ZERO;
		this.bjRatioE = BigDecimal.ZERO;
		this.bjRatioP = BigDecimal.ZERO;
		this.ratioE = BigDecimal.ZERO;
		this.ratioP = BigDecimal.ZERO;
	}
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.af_payment_bill_person_detail_id
     *
     * @mbggenerated
     */
    private Long afPaymentBillPersonDetailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.af_payment_bill_person_id
     *
     * @mbggenerated
     */
    private Long afPaymentBillPersonId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.sb_group_detail_id
     *
     * @mbggenerated
     */
    private Long sbGroupDetailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.base_e
     *
     * @mbggenerated
     */
    private BigDecimal baseE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.ratio_e
     *
     * @mbggenerated
     */
    private BigDecimal ratioE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.amount_e
     *
     * @mbggenerated
     */
    private BigDecimal amountE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.base_p
     *
     * @mbggenerated
     */
    private BigDecimal baseP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.ratio_p
     *
     * @mbggenerated
     */
    private BigDecimal ratioP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.amount_p
     *
     * @mbggenerated
     */
    private BigDecimal amountP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_month_start
     *
     * @mbggenerated
     */
    private String bjMonthStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_month_end
     *
     * @mbggenerated
     */
    private String bjMonthEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_month_num
     *
     * @mbggenerated
     */
    private Integer bjMonthNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_base_e
     *
     * @mbggenerated
     */
    private BigDecimal bjBaseE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_ratio_e
     *
     * @mbggenerated
     */
    private BigDecimal bjRatioE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_amount_e
     *
     * @mbggenerated
     */
    private BigDecimal bjAmountE;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_base_p
     *
     * @mbggenerated
     */
    private BigDecimal bjBaseP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_ratio_p
     *
     * @mbggenerated
     */
    private BigDecimal bjRatioP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.bj_amount_p
     *
     * @mbggenerated
     */
    private BigDecimal bjAmountP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.update_user_id
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column af_payment_bill_person_detail.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.af_payment_bill_person_detail_id
     *
     * @return the value of af_payment_bill_person_detail.af_payment_bill_person_detail_id
     *
     * @mbggenerated
     */
    public Long getAfPaymentBillPersonDetailId() {
        return afPaymentBillPersonDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.af_payment_bill_person_detail_id
     *
     * @param afPaymentBillPersonDetailId the value for af_payment_bill_person_detail.af_payment_bill_person_detail_id
     *
     * @mbggenerated
     */
    public void setAfPaymentBillPersonDetailId(Long afPaymentBillPersonDetailId) {
        this.afPaymentBillPersonDetailId = afPaymentBillPersonDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.af_payment_bill_person_id
     *
     * @return the value of af_payment_bill_person_detail.af_payment_bill_person_id
     *
     * @mbggenerated
     */
    public Long getAfPaymentBillPersonId() {
        return afPaymentBillPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.af_payment_bill_person_id
     *
     * @param afPaymentBillPersonId the value for af_payment_bill_person_detail.af_payment_bill_person_id
     *
     * @mbggenerated
     */
    public void setAfPaymentBillPersonId(Long afPaymentBillPersonId) {
        this.afPaymentBillPersonId = afPaymentBillPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.sb_group_detail_id
     *
     * @return the value of af_payment_bill_person_detail.sb_group_detail_id
     *
     * @mbggenerated
     */
    public Long getSbGroupDetailId() {
        return sbGroupDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.sb_group_detail_id
     *
     * @param sbGroupDetailId the value for af_payment_bill_person_detail.sb_group_detail_id
     *
     * @mbggenerated
     */
    public void setSbGroupDetailId(Long sbGroupDetailId) {
        this.sbGroupDetailId = sbGroupDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.base_e
     *
     * @return the value of af_payment_bill_person_detail.base_e
     *
     * @mbggenerated
     */
    public BigDecimal getBaseE() {
        return baseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.base_e
     *
     * @param baseE the value for af_payment_bill_person_detail.base_e
     *
     * @mbggenerated
     */
    public void setBaseE(BigDecimal baseE) {
        this.baseE = baseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.ratio_e
     *
     * @return the value of af_payment_bill_person_detail.ratio_e
     *
     * @mbggenerated
     */
    public BigDecimal getRatioE() {
        return ratioE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.ratio_e
     *
     * @param ratioE the value for af_payment_bill_person_detail.ratio_e
     *
     * @mbggenerated
     */
    public void setRatioE(BigDecimal ratioE) {
        this.ratioE = ratioE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.amount_e
     *
     * @return the value of af_payment_bill_person_detail.amount_e
     *
     * @mbggenerated
     */
    public BigDecimal getAmountE() {
        return amountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.amount_e
     *
     * @param amountE the value for af_payment_bill_person_detail.amount_e
     *
     * @mbggenerated
     */
    public void setAmountE(BigDecimal amountE) {
        this.amountE = amountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.base_p
     *
     * @return the value of af_payment_bill_person_detail.base_p
     *
     * @mbggenerated
     */
    public BigDecimal getBaseP() {
        return baseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.base_p
     *
     * @param baseP the value for af_payment_bill_person_detail.base_p
     *
     * @mbggenerated
     */
    public void setBaseP(BigDecimal baseP) {
        this.baseP = baseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.ratio_p
     *
     * @return the value of af_payment_bill_person_detail.ratio_p
     *
     * @mbggenerated
     */
    public BigDecimal getRatioP() {
        return ratioP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.ratio_p
     *
     * @param ratioP the value for af_payment_bill_person_detail.ratio_p
     *
     * @mbggenerated
     */
    public void setRatioP(BigDecimal ratioP) {
        this.ratioP = ratioP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.amount_p
     *
     * @return the value of af_payment_bill_person_detail.amount_p
     *
     * @mbggenerated
     */
    public BigDecimal getAmountP() {
        return amountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.amount_p
     *
     * @param amountP the value for af_payment_bill_person_detail.amount_p
     *
     * @mbggenerated
     */
    public void setAmountP(BigDecimal amountP) {
        this.amountP = amountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_month_start
     *
     * @return the value of af_payment_bill_person_detail.bj_month_start
     *
     * @mbggenerated
     */
    public String getBjMonthStart() {
        return bjMonthStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_month_start
     *
     * @param bjMonthStart the value for af_payment_bill_person_detail.bj_month_start
     *
     * @mbggenerated
     */
    public void setBjMonthStart(String bjMonthStart) {
        this.bjMonthStart = bjMonthStart == null ? null : bjMonthStart.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_month_end
     *
     * @return the value of af_payment_bill_person_detail.bj_month_end
     *
     * @mbggenerated
     */
    public String getBjMonthEnd() {
        return bjMonthEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_month_end
     *
     * @param bjMonthEnd the value for af_payment_bill_person_detail.bj_month_end
     *
     * @mbggenerated
     */
    public void setBjMonthEnd(String bjMonthEnd) {
        this.bjMonthEnd = bjMonthEnd == null ? null : bjMonthEnd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_month_num
     *
     * @return the value of af_payment_bill_person_detail.bj_month_num
     *
     * @mbggenerated
     */
    public Integer getBjMonthNum() {
        return bjMonthNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_month_num
     *
     * @param bjMonthNum the value for af_payment_bill_person_detail.bj_month_num
     *
     * @mbggenerated
     */
    public void setBjMonthNum(Integer bjMonthNum) {
        this.bjMonthNum = bjMonthNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_base_e
     *
     * @return the value of af_payment_bill_person_detail.bj_base_e
     *
     * @mbggenerated
     */
    public BigDecimal getBjBaseE() {
        return bjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_base_e
     *
     * @param bjBaseE the value for af_payment_bill_person_detail.bj_base_e
     *
     * @mbggenerated
     */
    public void setBjBaseE(BigDecimal bjBaseE) {
        this.bjBaseE = bjBaseE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_ratio_e
     *
     * @return the value of af_payment_bill_person_detail.bj_ratio_e
     *
     * @mbggenerated
     */
    public BigDecimal getBjRatioE() {
        return bjRatioE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_ratio_e
     *
     * @param bjRatioE the value for af_payment_bill_person_detail.bj_ratio_e
     *
     * @mbggenerated
     */
    public void setBjRatioE(BigDecimal bjRatioE) {
        this.bjRatioE = bjRatioE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_amount_e
     *
     * @return the value of af_payment_bill_person_detail.bj_amount_e
     *
     * @mbggenerated
     */
    public BigDecimal getBjAmountE() {
        return bjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_amount_e
     *
     * @param bjAmountE the value for af_payment_bill_person_detail.bj_amount_e
     *
     * @mbggenerated
     */
    public void setBjAmountE(BigDecimal bjAmountE) {
        this.bjAmountE = bjAmountE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_base_p
     *
     * @return the value of af_payment_bill_person_detail.bj_base_p
     *
     * @mbggenerated
     */
    public BigDecimal getBjBaseP() {
        return bjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_base_p
     *
     * @param bjBaseP the value for af_payment_bill_person_detail.bj_base_p
     *
     * @mbggenerated
     */
    public void setBjBaseP(BigDecimal bjBaseP) {
        this.bjBaseP = bjBaseP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_ratio_p
     *
     * @return the value of af_payment_bill_person_detail.bj_ratio_p
     *
     * @mbggenerated
     */
    public BigDecimal getBjRatioP() {
        return bjRatioP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_ratio_p
     *
     * @param bjRatioP the value for af_payment_bill_person_detail.bj_ratio_p
     *
     * @mbggenerated
     */
    public void setBjRatioP(BigDecimal bjRatioP) {
        this.bjRatioP = bjRatioP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.bj_amount_p
     *
     * @return the value of af_payment_bill_person_detail.bj_amount_p
     *
     * @mbggenerated
     */
    public BigDecimal getBjAmountP() {
        return bjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.bj_amount_p
     *
     * @param bjAmountP the value for af_payment_bill_person_detail.bj_amount_p
     *
     * @mbggenerated
     */
    public void setBjAmountP(BigDecimal bjAmountP) {
        this.bjAmountP = bjAmountP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.create_user_id
     *
     * @return the value of af_payment_bill_person_detail.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.create_user_id
     *
     * @param createUserId the value for af_payment_bill_person_detail.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.create_date
     *
     * @return the value of af_payment_bill_person_detail.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.create_date
     *
     * @param createDate the value for af_payment_bill_person_detail.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.update_user_id
     *
     * @return the value of af_payment_bill_person_detail.update_user_id
     *
     * @mbggenerated
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.update_user_id
     *
     * @param updateUserId the value for af_payment_bill_person_detail.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.update_date
     *
     * @return the value of af_payment_bill_person_detail.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.update_date
     *
     * @param updateDate the value for af_payment_bill_person_detail.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column af_payment_bill_person_detail.is_deleted
     *
     * @return the value of af_payment_bill_person_detail.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column af_payment_bill_person_detail.is_deleted
     *
     * @param isDeleted the value for af_payment_bill_person_detail.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}