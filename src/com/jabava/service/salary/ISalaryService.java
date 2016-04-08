package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalary;
import com.jabava.pojo.salary.EhrSalaryDetail;

public interface ISalaryService {
	List<EhrSalary> listSalaryPage(Map<String,Object> params);
	
	EhrSalary selectByPersonAndUsage(Long companyId, Long personId, Integer usageFlag);
	
	int saveOrUpdate(EhrSalary salary, EhrUser user);
	
	int updateDetailList(List<EhrSalary> salaryList);
	
	int deleteById(Long companyId, Long salaryId);
	
	EhrSalary selectById(Long companyId, Long salaryId);
	
	List<EhrSalaryDetail> loadDetailList(Long salaryId);
	
	/**
	 * 以salaryItemName为键将工资明细组成Map
	 * @param salaryId
	 * @return
	 */
	Map<String,EhrSalaryDetail> getDetailMap(Long salaryId);
	
	int updateSalaryDetail(String name, String value, String pk);
}
