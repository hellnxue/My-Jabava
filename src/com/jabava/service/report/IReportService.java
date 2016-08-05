package com.jabava.service.report;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReport;

public interface IReportService {
	List<EhrReport> listReportPage(Map<String,Object> params);
	
	EhrReport selectByTypeAndName(Long companyId, String reportType, String reportName);
	
	int saveOrUpdate(EhrUser user, EhrReport report);
	
	int deleteById(Long companyId, Long reportId);
	
	EhrReport loadReport(Long companyId, Long reportId);
	
	EhrReport selectByReportId(Long reportId);
	
	List<EhrReport> listReportByType(Long companyId, String reportType);
	
	Map<String,Object> generateReport(EhrUser user, EhrReport report, Map<String,Object> params) throws Exception;
}
