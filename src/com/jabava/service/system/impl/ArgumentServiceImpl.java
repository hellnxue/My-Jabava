package com.jabava.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrArgumentInfoMapper;
import com.jabava.pojo.manage.EhrArgumentInfo;
import com.jabava.service.system.IArgumentService;
import com.jabava.utils.Page;

@Service("argumentService")
public class ArgumentServiceImpl implements IArgumentService {

	@Resource
	private EhrArgumentInfoMapper argumentMapper;

	@Override
	public List<EhrArgumentInfo> queryMyArgument(Long companyId, String search, String order, String according, int isNumeric, Page<EhrArgumentInfo> page)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("companyId", companyId);
		map.put("search", search);
		map.put("order", order);
		map.put("according", according);
		map.put("isNumeric", isNumeric);
		map.put("page", page);
		List<EhrArgumentInfo> arguments = argumentMapper.queryMyArgumentPage(map);
		return arguments;
	}

	@Override
	public boolean addArgument(EhrArgumentInfo argument) throws Exception {
		boolean result = false;
		try {
			int code = argumentMapper.insertSelective(argument);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("新增参数失败.", e);
		}
		return result;
	}

	@Override
	public boolean delArgument(Long id) throws Exception {
		boolean result = false;
		try {
			int code = argumentMapper.deleteByPrimaryKey(id);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("删除参数失败.", e);
		}
		return result;
	}

	@Override
	public boolean updateArgument(EhrArgumentInfo argument) throws Exception {
		boolean result = false;
		try {
			int code = argumentMapper.updateByPrimaryKeySelective(argument);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("修改参数失败.", e);
		}
		return result;
	}

	@Override
	public List<String> selectAllKey(Long companyId) throws Exception {
		// TODO Auto-generated method stub
		List<String> keys = new ArrayList<String>();
		keys = argumentMapper.selectAllKey(companyId);
		return keys;
	}
	
	@Override
	public EhrArgumentInfo selectByKey(Long companyId, String argumentKey){
		return argumentMapper.selectByKey(companyId, argumentKey);
	}
}
