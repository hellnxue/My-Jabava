package com.jabava.service.report;

import java.util.List;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReportParam;

public interface IReportParamService {
	List<EhrReportParam> listByReportId(Long reportId);
	
	int deleteById(Long companyId, Long reportParamId);
	
	EhrReportParam selectByReportAndName(Long companyId, Long reportId, String englishName);
	
	int saveOrUpdate(EhrUser user, EhrReportParam reportParam);
	
	EhrReportParam loadReportParam(Long companyId, Long reportParamId);
}
