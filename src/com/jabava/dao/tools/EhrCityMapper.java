package com.jabava.dao.tools;

import java.util.HashMap;
import java.util.List;

import com.jabava.pojo.tools.EhrCity;


public interface EhrCityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long cityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    int insert(EhrCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    int insertSelective(EhrCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    EhrCity selectByPrimaryKey(Long cityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EhrCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_city
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EhrCity record);
    /**
     *根据省份id查询对应城市列表
     */
    List<HashMap<String,Object>> getCityByProvinceId(EhrCity record);
}