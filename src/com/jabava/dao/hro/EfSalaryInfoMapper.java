package com.jabava.dao.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.EfSalaryInfo;

public interface EfSalaryInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    int insert(EfSalaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    int insertSelective(EfSalaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    EfSalaryInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    int updateByPrimaryKeySelective(EfSalaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ef_salary_info
     *
     * @mbggenerated Tue Mar 08 16:27:40 CST 2016
     */
    int updateByPrimaryKey(EfSalaryInfo record);
    
    EfSalaryInfo selectByUniqueCond(EfSalaryInfo si);
    
    List<Map<String,Object>> querySalaryInfoPage(Map<String,Object> params);
}