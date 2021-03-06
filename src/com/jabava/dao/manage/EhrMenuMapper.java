package com.jabava.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrUser;

public interface EhrMenuMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	int deleteByPrimaryKey(Long menuId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	int insert(EhrMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	int insertSelective(EhrMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	EhrMenu selectByPrimaryKey(Long menuId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	int updateByPrimaryKeySelective(EhrMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table ehr_menu
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	int updateByPrimaryKey(EhrMenu record);

	List<EhrMenu> getLeafMenu(@Param("userType") Byte userType,
			@Param("userId") Long userId);

	List<EhrMenu> searchMenu(@Param("menuName") String menuName,
			@Param("menuType") Byte menuType, @Param("menuUrl") String menuUrl,
			@Param("parentId") Long parentId);
	
	List<EhrMenu> selectAuthorizedChildren(@Param("parentId") Long parentId, @Param("userId") Long userId);
}