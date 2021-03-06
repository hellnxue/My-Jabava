package com.jabava.dao.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.employee.EhrAssess;

public interface EhrAssessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int deleteByPrimaryKey(Long assessId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int insert(EhrAssess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int insertSelective(EhrAssess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    EhrAssess selectByPrimaryKey(Long assessId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int updateByPrimaryKeySelective(EhrAssess record);
    /**
     * 根据AssessId 更新is_deleted =1
     * <pre>
     * @author steven.chen
     * @date 2016年3月24日 下午3:09:38 
     * </pre>
     *
     * @param personId
     * @return
     */
    int  updateAssessByAssessId(Long personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_assess
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int updateByPrimaryKey(EhrAssess record);
    
    List<EhrAssess> getByPersonId(@Param("personId") Long personId);
}