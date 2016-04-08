package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;

public interface ISalaryChangeDefService {
	List<EhrSalaryChangeDef> listSalaryChangeDef(Long companyId);
	
	List<Map<String,Object>> loadSalaryChangeDef(Long salaryChangeDefId);
	
	EhrSalaryChangeDef selectByName(Long companyId, String name);
	
	int saveOrUpdate(EhrSalaryChangeDef changeDef);
	
	int deleteById(Long companyId, Long salaryChangeDefId);
	
	EhrSalaryChangeDef selectByPrimaryKey(Long salaryChangeDefId);
	
	List<EhrSalaryChangeDefItem> queryItemList(Long salaryChangeDefId);
}
