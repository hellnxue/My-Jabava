package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrTrialMapper;
import com.jabava.pojo.employee.EhrTrial;
import com.jabava.service.employee.EhrTrialService;
/**
 * 试用情况
 *
 * @version $Id: EhrTrialServiceImpl.java, 
 * v 0.1 2016年3月24日 下午1:30:31 
 * <pre>
 * @author steven.chen
 * @date 2016年3月24日 下午1:30:31 
 * </pre>
 */
@Service("trialService")
public class EhrTrialServiceImpl implements EhrTrialService {
	@Resource
	private EhrTrialMapper trialMapper;

	@Override
	public List<EhrTrial> getByPersonId(Long personId) throws Exception {
		return trialMapper.getByPersonId(personId);
	}

	@Override
	public boolean addTrial(EhrTrial trial) throws Exception {
		boolean result = false;
		try {
			int code = trialMapper.insertSelective(trial);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateTrial(EhrTrial trial) throws Exception {
		boolean result = false;
		try {
			int code = trialMapper.updateByPrimaryKeySelective(trial);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delTrial(Long trialId) throws Exception {
		boolean result = false;
		try {
			int code = trialMapper.updateTrialByPrimaryKey(trialId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
