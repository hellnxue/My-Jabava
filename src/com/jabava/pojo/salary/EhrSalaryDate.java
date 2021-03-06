package com.jabava.pojo.salary;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EhrSalaryDate {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_salary_date.salary_date_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    private Long salaryDateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_salary_date.change_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date changeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_salary_date.change_type
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    private Integer changeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_salary_date.company_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    private Long companyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_salary_date.salary_date_id
     *
     * @return the value of ehr_salary_date.salary_date_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public Long getSalaryDateId() {
        return salaryDateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_salary_date.salary_date_id
     *
     * @param salaryDateId the value for ehr_salary_date.salary_date_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public void setSalaryDateId(Long salaryDateId) {
        this.salaryDateId = salaryDateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_salary_date.change_date
     *
     * @return the value of ehr_salary_date.change_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_salary_date.change_date
     *
     * @param changeDate the value for ehr_salary_date.change_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_salary_date.change_type
     *
     * @return the value of ehr_salary_date.change_type
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_salary_date.change_type
     *
     * @param changeType the value for ehr_salary_date.change_type
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_salary_date.company_id
     *
     * @return the value of ehr_salary_date.company_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_salary_date.company_id
     *
     * @param companyId the value for ehr_salary_date.company_id
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}