package com.jabava.service.salary;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryDate;

public interface ISalaryDateService {
	List<EhrSalaryDate> listSalaryDatePage(Map<String,Object> params);
	
	int saveOrUpdate(EhrSalaryDate salaryDate, EhrUser user);
	
	int deleteById(Long companyId, Long salaryDateId);
	
	EhrSalaryDate selectByChangeDate(Long companyId, Date changeDate);
}
