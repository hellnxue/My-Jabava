package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrExperienceMapper;
import com.jabava.pojo.employee.EhrExperience;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrExperienceService;

@Service("experienceService")
public class EhrExperienceServiceImpl implements EhrExperienceService {
	@Resource
	private EhrExperienceMapper experienceMapper;
	
	@Override
	public List<EhrExperience> getByPersonId(Long personId) throws Exception {
		 
		return experienceMapper.getByPersonId(personId);
	}

	@Override
	public Map<String, Object> addExperience(EhrExperience experience,EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		experience.setCreateDate(new Date());
		experience.setCreateUserId(user.getUserId());
		experience.setCreateUserName(user.getUserName());
		experience.setLastModifyDate(new Date());
		experience.setLastModifyUserId(user.getUserId());
		experience.setLastModifyUserName(user.getUserName());
		experienceMapper.insertSelective(experience);
		map.put("success", true);
		map.put("msg", "添加工作经验成功！");
		return map;
	}

	@Override
	public Map<String, Object> updateExperience(EhrExperience experience,
			EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		experience.setLastModifyDate(new Date());
		experience.setLastModifyUserId(user.getUserId());
		experience.setLastModifyUserName(user.getUserName());
		experienceMapper.updateByPrimaryKeySelective(experience);
		map.put("success", true);
		map.put("msg", "修改工作经验成功！");
		return map;
	}

	@Override
	public Map<String, Object> deleteExperience(Long experienceId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		experienceMapper.deleteByPrimaryKey(experienceId);
		map.put("success", true);
		map.put("msg", "删除工作经验成功！");
		return map;
	}

}
