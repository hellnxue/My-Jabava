package com.jabava.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.manage.EhrUserOrganization;

public interface EhrUserOrganizationMapper {
	List<EhrUserOrganization> listByUserId(Long userId);
	
	int deleteByOrganization(Long organizationId);
	
	int deleteByUser(Long userId);
	
	List<EhrUserOrganization> selectByOrganization(Long organizationId);
	
	EhrUserOrganization selectByUserAndOrganization(@Param("userId")Long userId , @Param("organizationId")Long organizationId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    int insert(EhrUserOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    int insertSelective(EhrUserOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    EhrUserOrganization selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    int updateByPrimaryKeySelective(EhrUserOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_user_organization
     *
     * @mbggenerated Wed May 25 21:20:09 CST 2016
     */
    int updateByPrimaryKey(EhrUserOrganization record);
}