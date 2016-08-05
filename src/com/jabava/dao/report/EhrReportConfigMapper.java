package com.jabava.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.report.EhrReportConfig;

public interface EhrReportConfigMapper {
	List<EhrReportConfig> listByReportId(Long reportId);
	
	int deleteById(@Param("companyId")Long companyId, @Param("reportConfigId")Long reportConfigId);
	
	int deleteByReportId(@Param("reportId")Long reportId);
	
	EhrReportConfig selectById(@Param("companyId")Long companyId, @Param("reportConfigId")Long reportConfigId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int insert(EhrReportConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int insertSelective(EhrReportConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    EhrReportConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int updateByPrimaryKeySelective(EhrReportConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(EhrReportConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_report_config
     *
     * @mbggenerated Thu Apr 07 11:45:34 CST 2016
     */
    int updateByPrimaryKey(EhrReportConfig record);
}