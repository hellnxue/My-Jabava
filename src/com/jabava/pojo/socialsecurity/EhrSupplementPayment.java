package com.jabava.pojo.socialsecurity;

import java.math.BigDecimal;

public class EhrSupplementPayment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.payment_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Integer paymentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.person_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Integer personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.security_type_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Long securityTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.security_item_code
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Long securityItemCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.security_type_category
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Integer securityTypeCategory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.operate_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private String operateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.start_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private String startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.end_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private String endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.org_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Long orgFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.personal_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Long personalFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.org_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private BigDecimal orgLateFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.personal_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private BigDecimal personalLateFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.description
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.create_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_supplement_payment.is_deleted
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    private Integer isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.payment_id
     *
     * @return the value of ehr_supplement_payment.payment_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.payment_id
     *
     * @param paymentId the value for ehr_supplement_payment.payment_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.person_id
     *
     * @return the value of ehr_supplement_payment.person_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.person_id
     *
     * @param personId the value for ehr_supplement_payment.person_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.security_type_id
     *
     * @return the value of ehr_supplement_payment.security_type_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Long getSecurityTypeId() {
        return securityTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.security_type_id
     *
     * @param securityTypeId the value for ehr_supplement_payment.security_type_id
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setSecurityTypeId(Long securityTypeId) {
        this.securityTypeId = securityTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.security_item_code
     *
     * @return the value of ehr_supplement_payment.security_item_code
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Long getSecurityItemCode() {
        return securityItemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.security_item_code
     *
     * @param securityItemCode the value for ehr_supplement_payment.security_item_code
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setSecurityItemCode(Long securityItemCode) {
        this.securityItemCode = securityItemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.security_type_category
     *
     * @return the value of ehr_supplement_payment.security_type_category
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Integer getSecurityTypeCategory() {
        return securityTypeCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.security_type_category
     *
     * @param securityTypeCategory the value for ehr_supplement_payment.security_type_category
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setSecurityTypeCategory(Integer securityTypeCategory) {
        this.securityTypeCategory = securityTypeCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.operate_time
     *
     * @return the value of ehr_supplement_payment.operate_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.operate_time
     *
     * @param operateTime the value for ehr_supplement_payment.operate_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.start_time
     *
     * @return the value of ehr_supplement_payment.start_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.start_time
     *
     * @param startTime the value for ehr_supplement_payment.start_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.end_time
     *
     * @return the value of ehr_supplement_payment.end_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.end_time
     *
     * @param endTime the value for ehr_supplement_payment.end_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.org_fee
     *
     * @return the value of ehr_supplement_payment.org_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Long getOrgFee() {
        return orgFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.org_fee
     *
     * @param orgFee the value for ehr_supplement_payment.org_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setOrgFee(Long orgFee) {
        this.orgFee = orgFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.personal_fee
     *
     * @return the value of ehr_supplement_payment.personal_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Long getPersonalFee() {
        return personalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.personal_fee
     *
     * @param personalFee the value for ehr_supplement_payment.personal_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setPersonalFee(Long personalFee) {
        this.personalFee = personalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.org_late_fee
     *
     * @return the value of ehr_supplement_payment.org_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public BigDecimal getOrgLateFee() {
        return orgLateFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.org_late_fee
     *
     * @param orgLateFee the value for ehr_supplement_payment.org_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setOrgLateFee(BigDecimal orgLateFee) {
        this.orgLateFee = orgLateFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.personal_late_fee
     *
     * @return the value of ehr_supplement_payment.personal_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public BigDecimal getPersonalLateFee() {
        return personalLateFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.personal_late_fee
     *
     * @param personalLateFee the value for ehr_supplement_payment.personal_late_fee
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setPersonalLateFee(BigDecimal personalLateFee) {
        this.personalLateFee = personalLateFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.description
     *
     * @return the value of ehr_supplement_payment.description
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.description
     *
     * @param description the value for ehr_supplement_payment.description
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.create_time
     *
     * @return the value of ehr_supplement_payment.create_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.create_time
     *
     * @param createTime the value for ehr_supplement_payment.create_time
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_supplement_payment.is_deleted
     *
     * @return the value of ehr_supplement_payment.is_deleted
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_supplement_payment.is_deleted
     *
     * @param isDeleted the value for ehr_supplement_payment.is_deleted
     *
     * @mbggenerated Fri Apr 29 11:44:57 CST 2016
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}