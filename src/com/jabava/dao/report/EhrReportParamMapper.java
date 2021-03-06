package com.jabava.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.report.EhrReportParam;

public interface EhrReportParamMapper {
	List<EhrReportParam> listByReportId(Long reportId);
	
	int deleteById(@Param("companyId")Long companyId, @Param("reportParamId")Long reportParamId);
	
	EhrReportParam selectByReportAndName(@Param("companyId")Long companyId, @Param("reportId")Long reportId, 
			@Param("englishName")String englishName);
	
	int deleteByReportId(@Param("reportId")Long reportId);
	
	EhrReportParam selectById(@Param("companyId")Long companyId, @Param("reportParamId")Long reportParamId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int insert(EhrReportParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int insertSelective(EhrReportParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    EhrReportParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int updateByPrimaryKeySelective(EhrReportParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_param
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int updateByPrimaryKey(EhrReportParam record);
}