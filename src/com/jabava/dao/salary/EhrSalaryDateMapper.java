package com.jabava.dao.salary;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.salary.EhrSalaryDate;

public interface EhrSalaryDateMapper {
	List<EhrSalaryDate> listSalaryDatePage(Map<String, Object> params);
	
	int deleteById(@Param("companyId")Long companyId, @Param("salaryDateId")Long salaryDateId);
	
	EhrSalaryDate selectByChangeDate(@Param("companyId")Long companyId, @Param("changeDate")Date changeDate);
    
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    int deleteByPrimaryKey(Long salaryDateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    int insert(EhrSalaryDate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    int insertSelective(EhrSalaryDate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    EhrSalaryDate selectByPrimaryKey(Long salaryDateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    int updateByPrimaryKeySelective(EhrSalaryDate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_date
     *
     * @mbggenerated Sun Mar 27 19:16:14 CST 2016
     */
    int updateByPrimaryKey(EhrSalaryDate record);
}