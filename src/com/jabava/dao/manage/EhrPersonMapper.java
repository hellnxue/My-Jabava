package com.jabava.dao.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.pojo.manage.EhrUserPersonPowerValue;

public interface EhrPersonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int deleteByPrimaryKey(Long personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int insert(EhrPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int insertSelective(EhrPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    EhrPerson selectByPrimaryKey(Long personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int updateByPrimaryKeySelective(EhrPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_person
     *
     * @mbggenerated Tue Dec 22 13:34:15 CST 2015
     */
    int updateByPrimaryKey(EhrPerson record);
    /***
     * 通讯录
     * @param CompanyId
     * @return
     */
    List<EhrPerson> selectByCompanyId(Map<String, Object> params);
    
    List<EhrPerson> selectAddressPage(Map<String, Object> params);
    
    List<EhrPerson> searchPerson(EhrPerson person);
    
    List<EhrPerson> selectByOrganizationId(Long organizationId);
    
    List<EhrPerson> selectByOrgIdList(List<Long> list);
    
    EhrPerson searchPersonByJobNumber(@Param("companyId")Long companyId, @Param("jobNumber")String jobNumber);
    
    /***
     * 员工查询
     * @param person
     * @return
     */
    List<EhrPerson> searchEhrPersonPage(Map<String,Object> map);
    
    Integer countPerson(EhrPerson person);
    /**
     * Tools里用
     * @param userId
     * @param functionId
     * @return
     */
    List<EhrUserBusinessPower> userBusinessPower(@Param("userId")Long userId, @Param("functionId")int functionId);
    EhrPersonField getPersonField(@Param("id")Integer id);
    List<EhrUserPersonPowerValue> getPowerValue(@Param("id")Long id);
    int searchSalary(Long personId);
    int deletePerson(Long personId);
	int deleteUser(@Param("personId")Long personId);
    List<EhrBaseData> searchBaseData();
	
	/**
     * 根据employeeName和mobile,查询人员
     * @param person
     * @return
     */
    List<EhrPerson> getPersonByMobile(@Param("mobile") String mobile);
    
    Long getUserId(@Param("personId") Long personId);
    
    int insertUserPerson(@Param("userId")Long userId, @Param("personId")Long personId);
    int insertRoleUser(@Param("userId")Long userId);
    
    int searchPositive(Map<String,Object> map);
    int searchContract(Map<String,Object> map);
    int searchBirth(Map<String,Object> map);
    
    List<EhrPerson> searchBirthList(Map<String, Object> map);
    List<EhrPerson> searchPositiveList(Map<String, Object> map);
    
    List<EhrPerson> getAllPersonCertId(Long companyId);
    
    List<EhrPerson> selectAllPerson(Long companyId);
    int isDeletePerson(@Param("personId")Long personId, @Param("isDeleted")Integer isDeleted);

    EhrPerson getById(Long personId);

	List<EhrPerson> searchPersonByEmployeeNameAndCompanyId(EhrPerson person);
}