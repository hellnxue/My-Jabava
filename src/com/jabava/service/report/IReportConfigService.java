package com.jabava.service.report;

import java.util.List;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReportConfig;

public interface IReportConfigService {
	List<EhrReportConfig> listByReportId(Long reportId);
	
	int deleteById(Long companyId, Long reportConfigId);
	
	int saveOrUpdate(EhrUser user, EhrReportConfig reportConfig);
	
	EhrReportConfig loadReportConfig(Long companyId, Long reportConfigId);
}
