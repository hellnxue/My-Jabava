package com.jabava.service.tools;

import java.util.HashMap;
import java.util.List;

import com.jabava.pojo.tools.EhrCity;

public interface EhrCityService {
	 /**
     *根据省份id查询对应城市列表
     */
    List<HashMap<String,Object>> getCityByProvinceId(EhrCity record);
}
