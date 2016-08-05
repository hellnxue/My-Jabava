package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrFamilyMapper;
import com.jabava.pojo.employee.EhrFamily;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrFamilyService;

@Service
public class EhrFamilyServiceImpl implements EhrFamilyService {
	@Resource
	private EhrFamilyMapper ehrFamilyMapper;
	
	@Override
	public List<EhrFamily> getByPersonId(Long personId) throws Exception {
		// TODO Auto-generated method stub
		return ehrFamilyMapper.getByPersonId(personId);
	}

	@Override
	public Map<String, Object> addFamily(EhrFamily ehrFamily, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ehrFamily.setCreateDate(new Date());
		ehrFamily.setCreateUserId(user.getUserId());
		ehrFamily.setCreateUserName(user.getUserName());
		ehrFamily.setLastModifyDate(new Date());
		ehrFamily.setLastModifyUserId(user.getUserId());
		ehrFamily.setLastModifyUserName(user.getUserName());
		if(ehrFamily.getPersonId()==null){
			ehrFamily.setPersonId(user.getUserId());
		}
		int flag=ehrFamilyMapper.insertSelective(ehrFamily);
		if(flag>0){
			map.put("success", true);
			map.put("msg", "社会关系添加成功！");
		}else{
			map.put("success", false);
			map.put("msg", "社会关系添加失败！");
		}
		return map;
	}

	@Override
	public Map<String, Object> updateFamily(EhrFamily ehrFamily,
			EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ehrFamily.setLastModifyDate(new Date());
		ehrFamily.setLastModifyUserId(user.getUserId());
		ehrFamily.setLastModifyUserName(user.getUserName());
		int flag=ehrFamilyMapper.updateByPrimaryKeySelective(ehrFamily);
		if(flag>0){
			map.put("success", true);
			map.put("msg", "社会关系修改成功！");
		}else{
			map.put("success", false);
			map.put("msg", "社会关系修改失败！");
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteFamily(Long familyId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ehrFamilyMapper.deleteByPrimaryKey(familyId);
		map.put("success", true);
		map.put("msg", "删除家庭成员成功！");
		return map;
	}

}
