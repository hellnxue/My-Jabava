package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrTrial;

public interface EhrTrialService {
	List<EhrTrial> getByPersonId(Long personId) throws Exception;
	
	boolean addTrial(EhrTrial trial) throws Exception;
	
	boolean updateTrial(EhrTrial trial) throws Exception;
	
	boolean delTrial(Long trialId) throws Exception ;
}
