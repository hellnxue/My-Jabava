package com.jabava.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.system.EhrFieldDisplayConfigMapper;
import com.jabava.pojo.system.EhrFieldDisplayConfig;
import com.jabava.service.system.IEhrFieldDisplayConfigService;
@Service
public class EhrFieldDisplayConfigServiceImpl implements IEhrFieldDisplayConfigService{
	@Resource
	private EhrFieldDisplayConfigMapper ehrFieldDisplayConfigMapper;
	@Override
	public int deleteByFunction(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ehrFieldDisplayConfigMapper.deleteByFunction(map);
	}

	@Override
	public Map<String,Object> insertAutomateCol(List<EhrFieldDisplayConfig> ehrFieldDispiay) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		int result=0;
		for(int i=0;i<ehrFieldDispiay.size();i++){
			EhrFieldDisplayConfig ehrFieldDisplayConfig = ehrFieldDispiay.get(i);
			result= ehrFieldDisplayConfigMapper.insertSelective(ehrFieldDisplayConfig);
		}
		if(result>0){
			map.put("success",true);
			map.put("msg", "添加成功");
		}else{
			map.put("success",false);
			map.put("msg", "添加失败");
		}
		
		return map;
	}

	@Override
	public List<EhrFieldDisplayConfig> selectDisplayCol(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ehrFieldDisplayConfigMapper.selectByFunction(map);
	}
}
