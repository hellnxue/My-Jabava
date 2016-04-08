package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrFamily;
import com.jabava.pojo.manage.EhrUser;

public interface EhrFamilyService {

	List<EhrFamily> getByPersonId(Long personId) throws Exception;
	
	Map<String, Object> addFamily(EhrFamily ehrFamily, EhrUser user) throws Exception;
	
	Map<String, Object> updateFamily(EhrFamily ehrFamily, EhrUser user) throws Exception;
	
	Map<String, Object> deleteFamily(Long familyId) throws Exception;
}
