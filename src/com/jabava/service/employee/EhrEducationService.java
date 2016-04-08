package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrEducation;
import com.jabava.pojo.manage.EhrUser;

public interface EhrEducationService {
	List<EhrEducation> getByPersonId(Long personId) throws Exception;
	
	Map<String, Object> addEducation(EhrEducation education, EhrUser user) throws Exception;
	
	Map<String, Object> updateEducation(EhrEducation education, EhrUser user) throws Exception;
	
	Map<String, Object> deleteEducation(Long educationId) throws Exception;
}
