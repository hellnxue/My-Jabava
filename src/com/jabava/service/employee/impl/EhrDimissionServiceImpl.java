package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrDimissionService;
/**
 * 离职管理
 *
 * @version $Id: EhrLeftServiceImpl.java, 
 * v 0.1 2016年3月25日 上午10:49:28 
 * <pre>
 * @author steven.chen
 * @date 2016年3月25日 上午10:49:28 
 * </pre>
 */
@Service
public class EhrDimissionServiceImpl implements EhrDimissionService {
	@Autowired
	private EhrDimissionMapper ehrDimissionMapper;

	@Override
	public List<EhrDimission> getByPersonId(Long personId) throws Exception {
		return ehrDimissionMapper.getByPersonId(personId);
	}

	@Override
	public Map<String, Object> addLeft(EhrDimission left, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		left.setCreateDate(new Date());
		left.setCreateUserId(user.getUserId());
		left.setCreateUserName(user.getUserName());
		left.setLastModifyDate(new Date());
		left.setLastModifyUserId(user.getUserId());
		left.setLastModifyUserName(user.getUserName());
		ehrDimissionMapper.insertSelective(left);
		map.put("success", true);
		map.put("msg", "添加离职信息成功！");
		return map;
	}

	@Override
	public Map<String, Object> updateLeft(EhrDimission left, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		left.setLastModifyDate(new Date());
		left.setLastModifyUserId(user.getUserId());
		left.setLastModifyUserName(user.getUserName());
		ehrDimissionMapper.updateByPrimaryKeySelective(left);
		map.put("success", true);
		map.put("msg", "修改离职信息成功！");
		return map;
	}

	@Override
	public Map<String, Object> deleteLeft(Long leftId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ehrDimissionMapper.deleteByPrimaryKey(leftId);
		map.put("success", true);
		map.put("msg", "删除离职信息成功！");
		return map;
	}
}
