package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.salary.EhrSalaryChangeData;
import com.jabava.pojo.salary.EhrSalaryChangeDef;

public interface ISalaryChangeDataService {
	List<Map<String,Object>> listSalaryChangeDataPage(Map<String,Object> params);
	
	List<Map<String,Object>> listSalaryChangeData(Map<String,Object> params);
	
	int saveOrUpdateSalaryChangeDataList(Long companyId, String monthly, EhrSalaryChangeDef def, List<EhrSalaryChangeData> salaryChangeDataList);
	
	int deleteById(Long companyId, Long salaryChangeDataId);
	
	int removeSalaryChangeData(Long companyId, String monthly, EhrSalaryChangeDef salaryChangeDef);
	
	int updateSalaryChangeData(String name, String value, String pk);
}
