package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryItem;

public interface ISalaryItemService {
	List<EhrSalaryItem> listSalaryItemPage(Map<String,Object> params);
	
	List<EhrSalaryItem> listSalaryItem(Long companyId);
	
	EhrSalaryItem selectByName(Long companyId, String salaryItemName);
	
	int saveOrUpdate(EhrSalaryItem salaryItem, EhrUser user);
	
	int deleteById(Long companyId, Long salaryItemId);
	
	EhrSalaryItem selectByPrimaryKey(Long salaryItemId);
	
	List<EhrSalaryItem> listTransitionItem(Long companyId, Long exceptId);
}
