package com.jabava.dao.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrTableFieldDef;

public interface EhrTableFieldDefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    int deleteByPrimaryKey(Long tableFieldDefId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    int insert(EhrTableFieldDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    int insertSelective(EhrTableFieldDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    EhrTableFieldDef selectByPrimaryKey(Long tableFieldDefId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    int updateByPrimaryKeySelective(EhrTableFieldDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_table_field_def
     *
     * @mbggenerated Mon Aug 01 11:02:21 CST 2016
     */
    int updateByPrimaryKey(EhrTableFieldDef record);
    
    /**
     * 查询指定表的扩展字段 
     * @param map
     * @return
     */
    List<EhrTableFieldDef> selectCustomFieldAndData(Map<String,Object> map);
    /**
     * 分页查询扩展字段
     * @param map
     * @return
     */
    List<EhrTableFieldDef> selectByPage(Map<String,Object> map);
}