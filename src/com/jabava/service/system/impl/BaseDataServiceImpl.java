package com.jabava.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.Page;
/***
 * 
 * @author panfei
 *
 */
@Service("baseDataService")
public class BaseDataServiceImpl implements IBaseDataService {
	@Resource
	public EhrBaseDataMapper dataMapper;
	/**
	 * 修改前查询
	 */
	@Override
	public EhrBaseData selectByBaseDataKey(Long baseDataId) throws Exception {
		EhrBaseData data = null;
	try {
		if(baseDataId != null){
			data =  dataMapper.selectByPrimaryKey(baseDataId);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return data;
	}
	/**
	 * 修改基础数据
	 */
	@Override
	public Map<String, Object> updateBaseData(EhrBaseData info){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = dataMapper.checkRepeat(info);
		if(count > 0){
			map.put("success", false);
			map.put("msg", "数据重复！");
			return map;
		} 
		try {
			dataMapper.updateByPrimaryKeySelective(info);
			map.put("success", true);
			map.put("msg", "修改基本数据成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改基本数据失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询基础数据列表
	 */
	@Override
	public List<EhrBaseData> searchBaseData(Map<String,Object> map, String search,
			String order, String according, int isNumeric,Page<EhrBaseData> page) throws Exception {
		map.put("according", according);
		map.put("order", order);
		map.put("search", search);
		map.put("page", page);
		return  dataMapper.searchBaseDataPage(map);
	}

	/**
	 * 查询数据类型
	 */
	@Override
	public List<EhrBaseDataType> selectByBaseDataType(){
		
		return dataMapper.selectByBaseDataType();
		
	}
	/**
	 * 新增
	 */
	@Override
	public Map<String, Object> insertBaseData(EhrBaseData data)  {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = dataMapper.checkRepeat(data);
		if(count > 0){
			map.put("success", false);
			map.put("msg", "数据重复！");
			return map;
		}
		try {
			dataMapper.insert(data);
			map.put("success", true);
			map.put("msg", "添加基础数据成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加基础数据失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除
	 */
	@Override
	public boolean updateByIsvalid(Long baseDataId,Long isValid){
		boolean result = false;
		try {
			int code = dataMapper.updateByIsvalid(baseDataId,isValid);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	@Override
	public List<EhrBaseData> getByCompanyId(Long companyId) throws Exception {
		return dataMapper.getByCompanyId(companyId);
	}
	@Override
	public List<EhrBaseData> selectBaseData(Long companyId, int baseDataType,
			String baseDataName) throws Exception {
		return dataMapper.selectBaseData(companyId, baseDataType, baseDataName);
	}

}
