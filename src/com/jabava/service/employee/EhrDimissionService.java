package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrUser;

public interface EhrDimissionService {
	List<EhrDimission> getByPersonId(Long personId) throws Exception;
	
	Map<String, Object> addDimission(EhrDimission dimission, EhrUser user) throws Exception;
	
	Map<String, Object> updateDimission(EhrDimission dimission, EhrUser user) throws Exception;
	
	Map<String, Object> deleteDimission(Long dimissionId) throws Exception;
}
