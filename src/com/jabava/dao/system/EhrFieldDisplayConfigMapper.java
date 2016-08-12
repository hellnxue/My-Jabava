package com.jabava.dao.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrFieldDisplayConfig;

public interface EhrFieldDisplayConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    int insert(EhrFieldDisplayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    int insertSelective(EhrFieldDisplayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    EhrFieldDisplayConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    int updateByPrimaryKeySelective(EhrFieldDisplayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_field_display_config
     *
     * @mbggenerated Mon Aug 01 11:39:16 CST 2016
     */
    int updateByPrimaryKey(EhrFieldDisplayConfig record);
    /**
     * 删除数据
     * @param function
     * @return
     */
    int deleteByFunction(Map<String, Object> map);
    /**
     * 查询显示列名
     * @param map
     * @return
     */
    List<EhrFieldDisplayConfig> selectByFunction(Map<String, Object> map);
    /**
     * 根据列名删除数据
     * @param map
     * @return
     */
    int deleteByColumnName(Map<String, Object> map);
}