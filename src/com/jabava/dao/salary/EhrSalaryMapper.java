package com.jabava.dao.salary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.salary.EhrSalary;

public interface EhrSalaryMapper {
	List<EhrSalary> listSalaryPage(Map<String, Object> params);
	
	EhrSalary selectByPersonAndUsage(@Param("companyId")Long companyId, @Param("personId")Long personId, 
			@Param("usageFlag")Integer usageFlag);

    int deleteById(@Param("companyId")Long companyId, @Param("salaryId")Long salaryId);

    EhrSalary selectById(@Param("companyId")Long companyId, @Param("salaryId")Long salaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    int deleteByPrimaryKey(Long salaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    int insert(EhrSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    int insertSelective(EhrSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    EhrSalary selectByPrimaryKey(Long salaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    int updateByPrimaryKeySelective(EhrSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary
     *
     * @mbggenerated Thu Mar 24 09:48:32 CST 2016
     */
    int updateByPrimaryKey(EhrSalary record);
}