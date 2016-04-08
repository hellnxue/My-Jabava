package com.jabava.pojo.hro;

import java.math.BigDecimal;
import java.util.Date;

public class EfSalaryInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.emp_id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private Long empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.salary_ym
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private String salaryYm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.city_name
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.tax_amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private BigDecimal taxAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.grant_date
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private Date grantDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ef_salary_info.protocol_code
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    private String protocolCode;
    
    private Long companyId;
    
    private String employeeName;
    
    private String cardId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.id
     *
     * @return the value of ef_salary_info.id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.id
     *
     * @param id the value for ef_salary_info.id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.emp_id
     *
     * @return the value of ef_salary_info.emp_id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.emp_id
     *
     * @param empId the value for ef_salary_info.emp_id
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.salary_ym
     *
     * @return the value of ef_salary_info.salary_ym
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public String getSalaryYm() {
        return salaryYm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.salary_ym
     *
     * @param salaryYm the value for ef_salary_info.salary_ym
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setSalaryYm(String salaryYm) {
        this.salaryYm = salaryYm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.city_name
     *
     * @return the value of ef_salary_info.city_name
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.city_name
     *
     * @param cityName the value for ef_salary_info.city_name
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.tax_amount
     *
     * @return the value of ef_salary_info.tax_amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.tax_amount
     *
     * @param taxAmount the value for ef_salary_info.tax_amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.amount
     *
     * @return the value of ef_salary_info.amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.amount
     *
     * @param amount the value for ef_salary_info.amount
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.grant_date
     *
     * @return the value of ef_salary_info.grant_date
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public Date getGrantDate() {
        return grantDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.grant_date
     *
     * @param grantDate the value for ef_salary_info.grant_date
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ef_salary_info.protocol_code
     *
     * @return the value of ef_salary_info.protocol_code
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public String getProtocolCode() {
        return protocolCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ef_salary_info.protocol_code
     *
     * @param protocolCode the value for ef_salary_info.protocol_code
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    public void setProtocolCode(String protocolCode) {
        this.protocolCode = protocolCode;
    }

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}