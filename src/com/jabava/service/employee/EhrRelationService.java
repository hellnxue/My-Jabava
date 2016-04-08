package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrRelation;

public interface EhrRelationService {
	List<EhrRelation> getByPersonId(Long personId) throws Exception;
	
	boolean addRelation(EhrRelation relation) throws Exception;
	
	boolean updateRelation(EhrRelation relation) throws Exception;
	
	boolean delRelation(Long relationId) throws Exception ;
}
