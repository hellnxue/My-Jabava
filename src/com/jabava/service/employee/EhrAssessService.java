package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrAssess;

public interface EhrAssessService {
	List<EhrAssess> getByPersonId(Long personId) throws Exception;
	
	boolean addAssess(EhrAssess assess) throws Exception;
	
	boolean updateAssess(EhrAssess assess) throws Exception;
	
	boolean delAssess(Long assessId) throws Exception ;
}
