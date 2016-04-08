package com.jabava.dao.manage;

import com.jabava.pojo.manage.EhrOrganizationHistory;

public interface EhrOrganizationHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int deleteByPrimaryKey(Long organizationHistoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int insert(EhrOrganizationHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int insertSelective(EhrOrganizationHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    EhrOrganizationHistory selectByPrimaryKey(Long organizationHistoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int updateByPrimaryKeySelective(EhrOrganizationHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_organization_history
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int updateByPrimaryKey(EhrOrganizationHistory record);
}