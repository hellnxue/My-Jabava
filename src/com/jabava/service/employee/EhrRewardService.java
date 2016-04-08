package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrRewards;

public interface EhrRewardService {
	List<EhrRewards> getByPersonId(Long personId) throws Exception;
	
	boolean addRewards(EhrRewards rewards) throws Exception;
	
	boolean updateRewards(EhrRewards rewards) throws Exception;
	
	boolean delRewards(Long rewardId) throws Exception ;
}
