package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrTrainMapper;
import com.jabava.pojo.employee.EhrTrain;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrTrainService;

@Service("trainService")
public class EhrTrainServiceImpl implements EhrTrainService {
	@Resource
	private EhrTrainMapper trainMapper;

	@Override
	public List<EhrTrain> getByPersonId(Long personId) throws Exception {
		return trainMapper.getByPersonId(personId);
	}

	@Override
	public Map<String, Object> addTrain(EhrTrain train, EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		train.setCreateDate(new Date());
		train.setCreateUserId(user.getUserId());
		train.setCreateUserName(user.getUserName());
		train.setLastModifyDate(new Date());
		train.setLastModifyUserId(user.getUserId());
		train.setLastModifyUserName(user.getUserName());
		trainMapper.insertSelective(train);
		map.put("success", true);
		map.put("msg", "添加培训经历成功！");
		return map;
	}

	@Override
	public Map<String, Object> updateTrain(EhrTrain train, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		train.setLastModifyDate(new Date());
		train.setLastModifyUserId(user.getUserId());
		train.setLastModifyUserName(user.getUserName());
		trainMapper.updateByPrimaryKeySelective(train);
		map.put("success", true);
		map.put("msg", "修改培训经历成功！");
		return map;
	}

	@Override
	public Map<String, Object> deleteTrain(Long trainId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		trainMapper.deleteByPrimaryKey(trainId);
		map.put("success", true);
		map.put("msg", "删除培训经历成功！");
		return map;
	}
}
