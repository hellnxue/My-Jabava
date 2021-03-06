package com.jabava.dao.system;

import java.util.*;

import com.jabava.pojo.system.EhrTableDef;

public interface EhrTableDefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    int deleteByPrimaryKey(Long tableDefId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    int insert(EhrTableDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    int insertSelective(EhrTableDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    EhrTableDef selectByPrimaryKey(Long tableDefId);
    
    EhrTableDef selectByCompanyId(Map<String, Object> map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    int updateByPrimaryKeySelective(EhrTableDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_def
     *
     * @mbggenerated Mon Aug 01 11:01:14 CST 2016
     */
    int updateByPrimaryKey(EhrTableDef record);
    /**
     * 根据公司id查询所有数据
     * @param companyId
     * @return
     */
    List<EhrTableDef> selectKeyTable(Long companyId);

}