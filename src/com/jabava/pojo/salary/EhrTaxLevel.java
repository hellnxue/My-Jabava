package com.jabava.pojo.salary;

import java.math.BigDecimal;

public class EhrTaxLevel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.tax_level_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private Long taxLevelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.tax_rate_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private Long taxRateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.level_lower
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private BigDecimal levelLower;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.level_limit
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private BigDecimal levelLimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.tax_rate
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private BigDecimal taxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_tax_level.fast_calculation_amount
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    private BigDecimal fastCalculationAmount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.tax_level_id
     *
     * @return the value of ehr_tax_level.tax_level_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public Long getTaxLevelId() {
        return taxLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.tax_level_id
     *
     * @param taxLevelId the value for ehr_tax_level.tax_level_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setTaxLevelId(Long taxLevelId) {
        this.taxLevelId = taxLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.tax_rate_id
     *
     * @return the value of ehr_tax_level.tax_rate_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public Long getTaxRateId() {
        return taxRateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.tax_rate_id
     *
     * @param taxRateId the value for ehr_tax_level.tax_rate_id
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.level_lower
     *
     * @return the value of ehr_tax_level.level_lower
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public BigDecimal getLevelLower() {
        return levelLower;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.level_lower
     *
     * @param levelLower the value for ehr_tax_level.level_lower
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setLevelLower(BigDecimal levelLower) {
        this.levelLower = levelLower;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.level_limit
     *
     * @return the value of ehr_tax_level.level_limit
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public BigDecimal getLevelLimit() {
        return levelLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.level_limit
     *
     * @param levelLimit the value for ehr_tax_level.level_limit
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setLevelLimit(BigDecimal levelLimit) {
        this.levelLimit = levelLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.tax_rate
     *
     * @return the value of ehr_tax_level.tax_rate
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.tax_rate
     *
     * @param taxRate the value for ehr_tax_level.tax_rate
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_tax_level.fast_calculation_amount
     *
     * @return the value of ehr_tax_level.fast_calculation_amount
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public BigDecimal getFastCalculationAmount() {
        return fastCalculationAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_tax_level.fast_calculation_amount
     *
     * @param fastCalculationAmount the value for ehr_tax_level.fast_calculation_amount
     *
     * @mbggenerated Tue Mar 22 15:39:29 CST 2016
     */
    public void setFastCalculationAmount(BigDecimal fastCalculationAmount) {
        this.fastCalculationAmount = fastCalculationAmount;
    }
}