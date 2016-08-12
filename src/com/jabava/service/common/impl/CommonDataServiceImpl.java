package com.jabava.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.service.common.ICommonDataService;

@Service
public class CommonDataServiceImpl implements ICommonDataService {
	@Autowired
	private EhrCommonDataMapper commonDataMapper;

	@Override
	public List<EhrCommonData> listByCommonDataType(Integer commonDataType) {
		return commonDataMapper.listByCommonDataType(commonDataType);
	}

	@Override
	public Map<String, Object> getByCommonDataTypeMap(Integer commonDataType) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<EhrCommonData> commonDataList=commonDataMapper.listByCommonDataType(commonDataType);
		
		if(commonDataList!=null&&commonDataList.size()>0){
			for(EhrCommonData baseData:commonDataList){
				dataMap.put(baseData.getCommonDataCode(), baseData.getCommonDataName());
			}
			return dataMap;
		}
		
		return dataMap;
	}

}
