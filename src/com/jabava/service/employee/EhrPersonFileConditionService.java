package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrPersonFileCondition;
import com.jabava.pojo.manage.EhrUser;

public interface EhrPersonFileConditionService {
	
	 Map<String,Object> insertSelective(EhrPersonFileCondition record, EhrUser user) throws Exception;
	
	 Map<String,Object> updateByPrimaryKeySelective(EhrPersonFileCondition record, EhrUser user) throws Exception;
	 
	 List<EhrPersonFileCondition> getFileConditionByPersonId(Long personId) throws Exception;

}
