package com.jabava.dao.manage;

import com.jabava.pojo.manage.EhrRoleUser;

public interface EhrRoleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    int deleteByPrimaryKey(Long roleUserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    int insert(EhrRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    int insertSelective(EhrRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    EhrRoleUser selectByPrimaryKey(Long roleUserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    int updateByPrimaryKeySelective(EhrRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_role_user
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    int updateByPrimaryKey(EhrRoleUser record);
}