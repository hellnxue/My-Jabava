package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrExperience;
import com.jabava.pojo.manage.EhrUser;

public interface EhrExperienceService {
	
	List<EhrExperience> getByPersonId(Long personId) throws Exception;
	
	Map<String, Object> addExperience(EhrExperience experience, EhrUser user) throws Exception;
	
	Map<String, Object> updateExperience(EhrExperience experience, EhrUser user) throws Exception;
	
	Map<String, Object> deleteExperience(Long experienceId) throws Exception;
}
