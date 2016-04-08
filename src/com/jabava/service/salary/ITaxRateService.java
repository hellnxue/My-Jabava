package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrTaxLevel;
import com.jabava.pojo.salary.EhrTaxRate;

public interface ITaxRateService {
	List<EhrTaxRate> listTaxRatePage(Map<String,Object> params);
	
	List<EhrTaxRate> listTaxRate(Long companyId);
	
	EhrTaxRate selectByName(Long companyId, String taxRateName);
	
	int saveOrUpdate(EhrTaxRate taxRate, EhrUser user);
	
	int deleteById(Long companyId, Long taxRateId);
	
	EhrTaxRate selectByPrimaryKey(Long taxRateId);
	
	List<EhrTaxLevel> listTaxLevel(Long taxRateId);
	
	EhrTaxLevel loadTaxLevel(Long taxLevelId);
	
	int saveOrUpdateLevel(EhrTaxLevel taxLevel);
	
	int deleteTaxLevel(Long taxLevelId);
}
