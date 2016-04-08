package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryTemplate;
import com.jabava.pojo.salary.EhrSalaryTemplateDetail;

public interface ISalaryTemplateService {
	List<EhrSalaryTemplate> listSalaryTemplatePage(Map<String,Object> params);
	
	List<EhrSalaryTemplate> listSalaryTemplate(Long companyId);
	
	EhrSalaryTemplate selectByName(Long companyId, String templateName);
	
	int saveOrUpdate(EhrSalaryTemplate salaryTemplate, EhrUser user, String[] ids);
	
	int deleteById(Long companyId, Long salaryTemplateId);
	
	EhrSalaryTemplate selectByPrimaryKey(Long salaryTemplateId);
	
	List<EhrSalaryTemplateDetail> loadDetailList(Long salaryTemplateId);
	
}
