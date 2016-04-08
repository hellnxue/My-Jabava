package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrRewardsMapper;
import com.jabava.pojo.employee.EhrRewards;
import com.jabava.service.employee.EhrRewardService;

@Service("rewardService")
public class EhrRewardServiceImpl implements EhrRewardService {
	@Resource
	private EhrRewardsMapper rewardsMapper;

	@Override
	public List<EhrRewards> getByPersonId(Long personId) throws Exception {
		return rewardsMapper.getByPersonId(personId);
	}

	@Override
	public boolean addRewards(EhrRewards rewards) throws Exception {
		boolean result = false;
		try {
			int code = rewardsMapper.insertSelective(rewards);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateRewards(EhrRewards rewards) throws Exception {
		boolean result = false;
		try {
			int code = rewardsMapper.updateByPrimaryKeySelective(rewards);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delRewards(Long rewardId) throws Exception {
		boolean result = false;
		try {
			int code = rewardsMapper.updateByrewardId(rewardId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
