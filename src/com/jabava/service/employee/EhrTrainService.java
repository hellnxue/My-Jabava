package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrTrain;
import com.jabava.pojo.manage.EhrUser;

public interface EhrTrainService {
	List<EhrTrain> getByPersonId(Long personId) throws Exception;
	
	Map<String, Object> addTrain(EhrTrain train, EhrUser user) throws Exception;
	
	Map<String, Object> updateTrain(EhrTrain train, EhrUser user) throws Exception;
	
	Map<String, Object> deleteTrain(Long trainId) throws Exception;
}
