package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrPersonFileConditionMapper;
import com.jabava.pojo.employee.EhrPersonFileCondition;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonFileConditionService;

@Service
public class EhrPersonFileConditionServiceImpl implements EhrPersonFileConditionService {
	@Resource
	private EhrPersonFileConditionMapper ehrPersonFileConditionMapper;

	@Override
	public Map<String, Object> insertSelective(EhrPersonFileCondition fileCondition, EhrUser user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		fileCondition.setCreateDate(new Date());
		fileCondition.setCreateUserId(user.getUserId());
		fileCondition.setCreateUserName(user.getUserName());
		fileCondition.setLastModifyDate(new Date());
		fileCondition.setLastModifyUserId(user.getUserId());
		fileCondition.setLastModifyUserName(user.getUserName());
		int flag=ehrPersonFileConditionMapper.insertSelective(fileCondition);
		if(flag>0){
			map.put("success", true);
			map.put("msg", "添加资料情况成功！");
		}else{
			map.put("success", false);
			map.put("msg", "添加资料情况失败！");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> updateByPrimaryKeySelective( EhrPersonFileCondition fileCondition, EhrUser user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		fileCondition.setLastModifyDate(new Date());
		fileCondition.setLastModifyUserId(user.getUserId());
		fileCondition.setLastModifyUserName(user.getUserName());
		int flag=ehrPersonFileConditionMapper.updateByPrimaryKeySelective(fileCondition);
		
		if(flag>0){
			map.put("success", true);
			map.put("msg", "添加资料情况成功！");
		}else{
			map.put("success", false);
			map.put("msg", "添加资料情况失败！");
		}
		
		return map;
	}

	@Override
	public List<EhrPersonFileCondition> getFileConditionByPersonId(Long personId) throws Exception{
		 
		return ehrPersonFileConditionMapper.getFileConditionByPersonId(personId);
	}
}
