package com.jabava.dao.system;

import com.jabava.pojo.system.EhrSysConfig;

public interface EhrSysConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    int insert(EhrSysConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    int insertSelective(EhrSysConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    EhrSysConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    int updateByPrimaryKeySelective(EhrSysConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_sys_config
     *
     * @mbggenerated Mon Mar 14 17:00:22 CST 2016
     */
    int updateByPrimaryKey(EhrSysConfig record);
}