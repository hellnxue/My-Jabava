package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrEducationMapper;
import com.jabava.pojo.employee.EhrEducation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrEducationService;

@Service("educationService")
public class EhrEducationServiceImpl implements EhrEducationService {
	@Resource
	private EhrEducationMapper educationMapper;

	@Override
	public List<EhrEducation> getByPersonId(Long personId) throws Exception {
		return educationMapper.getByPersonId(personId);
	}

	@Override
	public Map<String, Object> addEducation(EhrEducation education, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		education.setCreateDate(new Date());
		education.setCreateUserId(user.getUserId());
		education.setCreateUserName(user.getUserName());
		education.setLastModifyDate(new Date());
		education.setLastModifyUserId(user.getUserId());
		education.setLastModifyUserName(user.getUserName());
//		if(education.getPersonId()==null){
//			education.setPersonId(user.getUserId());
//		}
		int flag=educationMapper.insertSelective(education);
		if(flag>0){
			map.put("success", true);
			map.put("msg", "添加教育背景成功！");
		}else{
			map.put("success", false);
			map.put("msg", "添加教育背景失败！");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> updateEducation(EhrEducation education,
			EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		education.setLastModifyDate(new Date());
		education.setLastModifyUserId(user.getUserId());
		education.setLastModifyUserName(user.getUserName());
		int flag=educationMapper.updateByPrimaryKeySelective(education);
		if(flag>0){
			map.put("success", true);
			map.put("msg", "添加教育背景成功！");
		}else{
			map.put("success", false);
			map.put("msg", "添加教育背景失败！");
		}
		
		
		return map;
	}

	@Override
	public Map<String, Object> deleteEducation(Long educationId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		educationMapper.deleteByPrimaryKey(educationId);
		map.put("success", true);
		map.put("msg", "删除教育背景成功！");
		return map;
	}
}
