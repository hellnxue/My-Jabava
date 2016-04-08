package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrResume;

public interface EhrResumeService {
	List<EhrResume> getByPersonId(Long personId) throws Exception;
	
	boolean addResume(EhrResume resume) throws Exception;
	
	boolean updateResume(EhrResume resume) throws Exception;
	
	boolean delResume(Long resumeId) throws Exception ;
}
