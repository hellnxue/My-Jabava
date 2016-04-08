package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrProject;

public interface EhrProjectService {
	List<EhrProject> getByPersonId(Long personId) throws Exception;
	
	boolean addProject(EhrProject project) throws Exception;
	
	boolean updateProject(EhrProject project) throws Exception;
	
	boolean delProject(Long projectId) throws Exception ;
}
