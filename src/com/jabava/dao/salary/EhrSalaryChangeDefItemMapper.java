package com.jabava.dao.salary;

import java.util.List;

import com.jabava.pojo.salary.EhrSalaryChangeDefItem;

public interface EhrSalaryChangeDefItemMapper {
	
	List<EhrSalaryChangeDefItem> listByDefId(Long salaryChangeDefId);
	
    int deleteByDefId(Long salaryChangeDefId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    int deleteByPrimaryKey(Long salaryChangeDefItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    int insert(EhrSalaryChangeDefItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    int insertSelective(EhrSalaryChangeDefItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    EhrSalaryChangeDefItem selectByPrimaryKey(Long salaryChangeDefItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    int updateByPrimaryKeySelective(EhrSalaryChangeDefItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_change_def_item
     *
     * @mbggenerated Sun Mar 20 12:57:07 CST 2016
     */
    int updateByPrimaryKey(EhrSalaryChangeDefItem record);
}