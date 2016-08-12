package com.jabava.service.common;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.common.EhrCommonData;

public interface ICommonDataService {
	List<EhrCommonData> listByCommonDataType(Integer commonDataType);
	
	Map<String,Object> getByCommonDataTypeMap(Integer commonDataType);
}
