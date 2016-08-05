package com.jabava.dao.salary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.salary.EhrSalaryItem;

public interface EhrSalaryItemMapper {
	List<EhrSalaryItem> listSalaryItemPage(Map<String,Object> params);

	List<EhrSalaryItem> listSalaryItem(Long companyId);
	
	EhrSalaryItem selectByName(@Param("companyId")Long companyId, @Param("salaryItemName")String salaryItemName);
	
	int deleteById(@Param("companyId")Long companyId, @Param("salaryItemId")Long salaryItemId);
	
	List<EhrSalaryItem> listTransitionItem(@Param("companyId")Long companyId, @Param("exceptId")Long exceptId);
	
	List<EhrSalaryItem> listByMonthlySalaryId(Long monthlySalaryId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    int deleteByPrimaryKey(Long salaryItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    int insert(EhrSalaryItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    int insertSelective(EhrSalaryItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    EhrSalaryItem selectByPrimaryKey(Long salaryItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    int updateByPrimaryKeySelective(EhrSalaryItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_salary_item
     *
     * @mbggenerated Mon Mar 21 14:16:54 CST 2016
     */
    int updateByPrimaryKey(EhrSalaryItem record);
}