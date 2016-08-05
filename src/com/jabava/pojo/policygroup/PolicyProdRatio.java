package com.jabava.pojo.policygroup;

import java.math.BigDecimal;
import java.util.Date;

public class PolicyProdRatio {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.ratio_name
     *
     * @mbggenerated
     */
    private String ratioName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.policy_group_id
     *
     * @mbggenerated
     */
    private Long policyGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.city_id
     *
     * @mbggenerated
     */
    private Long cityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.city_name
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.prod_id
     *
     * @mbggenerated
     */
    private Long prodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.prod_name
     *
     * @mbggenerated
     */
    private String prodName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.house_hold_type
     *
     * @mbggenerated
     */
    private Integer houseHoldType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.company_ratio_id
     *
     * @mbggenerated
     */
    private Long companyRatioId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.company_ratio
     *
     * @mbggenerated
     */
    private BigDecimal companyRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.company_append
     *
     * @mbggenerated
     */
    private BigDecimal companyAppend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.individual_ratio_id
     *
     * @mbggenerated
     */
    private Long individualRatioId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.individual_ratio
     *
     * @mbggenerated
     */
    private BigDecimal individualRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.individual_append
     *
     * @mbggenerated
     */
    private BigDecimal individualAppend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.company_precise
     *
     * @mbggenerated
     */
    private String companyPrecise;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.individual_precise
     *
     * @mbggenerated
     */
    private String individualPrecise;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.company_calculate_type
     *
     * @mbggenerated
     */
    private String companyCalculateType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.individual_calculate_type
     *
     * @mbggenerated
     */
    private String individualCalculateType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.pay_type
     *
     * @mbggenerated
     */
    private Integer payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.pay_month
     *
     * @mbggenerated
     */
    private String payMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.month_company_amount
     *
     * @mbggenerated
     */
    private BigDecimal monthCompanyAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.month_individual_amount
     *
     * @mbggenerated
     */
    private BigDecimal monthIndividualAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.effective_date
     *
     * @mbggenerated
     */
    private String effectiveDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.expiry_date
     *
     * @mbggenerated
     */
    private String expiryDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.create_by
     *
     * @mbggenerated
     */
    private Long createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.create_dt
     *
     * @mbggenerated
     */
    private Date createDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.update_by
     *
     * @mbggenerated
     */
    private Long updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.update_dt
     *
     * @mbggenerated
     */
    private Date updateDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.mimic_by
     *
     * @mbggenerated
     */
    private Long mimicBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.proxy_by
     *
     * @mbggenerated
     */
    private Long proxyBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy_prod_ratio.relative_ratio_id
     *
     * @mbggenerated
     */
    private Long relativeRatioId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.id
     *
     * @return the value of policy_prod_ratio.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.id
     *
     * @param id the value for policy_prod_ratio.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.ratio_name
     *
     * @return the value of policy_prod_ratio.ratio_name
     *
     * @mbggenerated
     */
    public String getRatioName() {
        return ratioName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.ratio_name
     *
     * @param ratioName the value for policy_prod_ratio.ratio_name
     *
     * @mbggenerated
     */
    public void setRatioName(String ratioName) {
        this.ratioName = ratioName == null ? null : ratioName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.policy_group_id
     *
     * @return the value of policy_prod_ratio.policy_group_id
     *
     * @mbggenerated
     */
    public Long getPolicyGroupId() {
        return policyGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.policy_group_id
     *
     * @param policyGroupId the value for policy_prod_ratio.policy_group_id
     *
     * @mbggenerated
     */
    public void setPolicyGroupId(Long policyGroupId) {
        this.policyGroupId = policyGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.city_id
     *
     * @return the value of policy_prod_ratio.city_id
     *
     * @mbggenerated
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.city_id
     *
     * @param cityId the value for policy_prod_ratio.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.city_name
     *
     * @return the value of policy_prod_ratio.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.city_name
     *
     * @param cityName the value for policy_prod_ratio.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.prod_id
     *
     * @return the value of policy_prod_ratio.prod_id
     *
     * @mbggenerated
     */
    public Long getProdId() {
        return prodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.prod_id
     *
     * @param prodId the value for policy_prod_ratio.prod_id
     *
     * @mbggenerated
     */
    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.prod_name
     *
     * @return the value of policy_prod_ratio.prod_name
     *
     * @mbggenerated
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.prod_name
     *
     * @param prodName the value for policy_prod_ratio.prod_name
     *
     * @mbggenerated
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.house_hold_type
     *
     * @return the value of policy_prod_ratio.house_hold_type
     *
     * @mbggenerated
     */
    public Integer getHouseHoldType() {
        return houseHoldType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.house_hold_type
     *
     * @param houseHoldType the value for policy_prod_ratio.house_hold_type
     *
     * @mbggenerated
     */
    public void setHouseHoldType(Integer houseHoldType) {
        this.houseHoldType = houseHoldType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.company_ratio_id
     *
     * @return the value of policy_prod_ratio.company_ratio_id
     *
     * @mbggenerated
     */
    public Long getCompanyRatioId() {
        return companyRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.company_ratio_id
     *
     * @param companyRatioId the value for policy_prod_ratio.company_ratio_id
     *
     * @mbggenerated
     */
    public void setCompanyRatioId(Long companyRatioId) {
        this.companyRatioId = companyRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.company_ratio
     *
     * @return the value of policy_prod_ratio.company_ratio
     *
     * @mbggenerated
     */
    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.company_ratio
     *
     * @param companyRatio the value for policy_prod_ratio.company_ratio
     *
     * @mbggenerated
     */
    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.company_append
     *
     * @return the value of policy_prod_ratio.company_append
     *
     * @mbggenerated
     */
    public BigDecimal getCompanyAppend() {
        return companyAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.company_append
     *
     * @param companyAppend the value for policy_prod_ratio.company_append
     *
     * @mbggenerated
     */
    public void setCompanyAppend(BigDecimal companyAppend) {
        this.companyAppend = companyAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.individual_ratio_id
     *
     * @return the value of policy_prod_ratio.individual_ratio_id
     *
     * @mbggenerated
     */
    public Long getIndividualRatioId() {
        return individualRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.individual_ratio_id
     *
     * @param individualRatioId the value for policy_prod_ratio.individual_ratio_id
     *
     * @mbggenerated
     */
    public void setIndividualRatioId(Long individualRatioId) {
        this.individualRatioId = individualRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.individual_ratio
     *
     * @return the value of policy_prod_ratio.individual_ratio
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualRatio() {
        return individualRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.individual_ratio
     *
     * @param individualRatio the value for policy_prod_ratio.individual_ratio
     *
     * @mbggenerated
     */
    public void setIndividualRatio(BigDecimal individualRatio) {
        this.individualRatio = individualRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.individual_append
     *
     * @return the value of policy_prod_ratio.individual_append
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualAppend() {
        return individualAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.individual_append
     *
     * @param individualAppend the value for policy_prod_ratio.individual_append
     *
     * @mbggenerated
     */
    public void setIndividualAppend(BigDecimal individualAppend) {
        this.individualAppend = individualAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.company_precise
     *
     * @return the value of policy_prod_ratio.company_precise
     *
     * @mbggenerated
     */
    public String getCompanyPrecise() {
        return companyPrecise;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.company_precise
     *
     * @param companyPrecise the value for policy_prod_ratio.company_precise
     *
     * @mbggenerated
     */
    public void setCompanyPrecise(String companyPrecise) {
        this.companyPrecise = companyPrecise == null ? null : companyPrecise.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.individual_precise
     *
     * @return the value of policy_prod_ratio.individual_precise
     *
     * @mbggenerated
     */
    public String getIndividualPrecise() {
        return individualPrecise;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.individual_precise
     *
     * @param individualPrecise the value for policy_prod_ratio.individual_precise
     *
     * @mbggenerated
     */
    public void setIndividualPrecise(String individualPrecise) {
        this.individualPrecise = individualPrecise == null ? null : individualPrecise.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.company_calculate_type
     *
     * @return the value of policy_prod_ratio.company_calculate_type
     *
     * @mbggenerated
     */
    public String getCompanyCalculateType() {
        return companyCalculateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.company_calculate_type
     *
     * @param companyCalculateType the value for policy_prod_ratio.company_calculate_type
     *
     * @mbggenerated
     */
    public void setCompanyCalculateType(String companyCalculateType) {
        this.companyCalculateType = companyCalculateType == null ? null : companyCalculateType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.individual_calculate_type
     *
     * @return the value of policy_prod_ratio.individual_calculate_type
     *
     * @mbggenerated
     */
    public String getIndividualCalculateType() {
        return individualCalculateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.individual_calculate_type
     *
     * @param individualCalculateType the value for policy_prod_ratio.individual_calculate_type
     *
     * @mbggenerated
     */
    public void setIndividualCalculateType(String individualCalculateType) {
        this.individualCalculateType = individualCalculateType == null ? null : individualCalculateType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.pay_type
     *
     * @return the value of policy_prod_ratio.pay_type
     *
     * @mbggenerated
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.pay_type
     *
     * @param payType the value for policy_prod_ratio.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.pay_month
     *
     * @return the value of policy_prod_ratio.pay_month
     *
     * @mbggenerated
     */
    public String getPayMonth() {
        return payMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.pay_month
     *
     * @param payMonth the value for policy_prod_ratio.pay_month
     *
     * @mbggenerated
     */
    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth == null ? null : payMonth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.month_company_amount
     *
     * @return the value of policy_prod_ratio.month_company_amount
     *
     * @mbggenerated
     */
    public BigDecimal getMonthCompanyAmount() {
        return monthCompanyAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.month_company_amount
     *
     * @param monthCompanyAmount the value for policy_prod_ratio.month_company_amount
     *
     * @mbggenerated
     */
    public void setMonthCompanyAmount(BigDecimal monthCompanyAmount) {
        this.monthCompanyAmount = monthCompanyAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.month_individual_amount
     *
     * @return the value of policy_prod_ratio.month_individual_amount
     *
     * @mbggenerated
     */
    public BigDecimal getMonthIndividualAmount() {
        return monthIndividualAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.month_individual_amount
     *
     * @param monthIndividualAmount the value for policy_prod_ratio.month_individual_amount
     *
     * @mbggenerated
     */
    public void setMonthIndividualAmount(BigDecimal monthIndividualAmount) {
        this.monthIndividualAmount = monthIndividualAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.effective_date
     *
     * @return the value of policy_prod_ratio.effective_date
     *
     * @mbggenerated
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.effective_date
     *
     * @param effectiveDate the value for policy_prod_ratio.effective_date
     *
     * @mbggenerated
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.expiry_date
     *
     * @return the value of policy_prod_ratio.expiry_date
     *
     * @mbggenerated
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.expiry_date
     *
     * @param expiryDate the value for policy_prod_ratio.expiry_date
     *
     * @mbggenerated
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate == null ? null : expiryDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.is_deleted
     *
     * @return the value of policy_prod_ratio.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.is_deleted
     *
     * @param isDeleted the value for policy_prod_ratio.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.create_by
     *
     * @return the value of policy_prod_ratio.create_by
     *
     * @mbggenerated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.create_by
     *
     * @param createBy the value for policy_prod_ratio.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.create_dt
     *
     * @return the value of policy_prod_ratio.create_dt
     *
     * @mbggenerated
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.create_dt
     *
     * @param createDt the value for policy_prod_ratio.create_dt
     *
     * @mbggenerated
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.update_by
     *
     * @return the value of policy_prod_ratio.update_by
     *
     * @mbggenerated
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.update_by
     *
     * @param updateBy the value for policy_prod_ratio.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.update_dt
     *
     * @return the value of policy_prod_ratio.update_dt
     *
     * @mbggenerated
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.update_dt
     *
     * @param updateDt the value for policy_prod_ratio.update_dt
     *
     * @mbggenerated
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.mimic_by
     *
     * @return the value of policy_prod_ratio.mimic_by
     *
     * @mbggenerated
     */
    public Long getMimicBy() {
        return mimicBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.mimic_by
     *
     * @param mimicBy the value for policy_prod_ratio.mimic_by
     *
     * @mbggenerated
     */
    public void setMimicBy(Long mimicBy) {
        this.mimicBy = mimicBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.proxy_by
     *
     * @return the value of policy_prod_ratio.proxy_by
     *
     * @mbggenerated
     */
    public Long getProxyBy() {
        return proxyBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.proxy_by
     *
     * @param proxyBy the value for policy_prod_ratio.proxy_by
     *
     * @mbggenerated
     */
    public void setProxyBy(Long proxyBy) {
        this.proxyBy = proxyBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy_prod_ratio.relative_ratio_id
     *
     * @return the value of policy_prod_ratio.relative_ratio_id
     *
     * @mbggenerated
     */
    public Long getRelativeRatioId() {
        return relativeRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy_prod_ratio.relative_ratio_id
     *
     * @param relativeRatioId the value for policy_prod_ratio.relative_ratio_id
     *
     * @mbggenerated
     */
    public void setRelativeRatioId(Long relativeRatioId) {
        this.relativeRatioId = relativeRatioId;
    }
}