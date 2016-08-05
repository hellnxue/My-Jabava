package com.jabava.service.tools.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.tools.EhrCityMapper;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.service.tools.EhrCityService;
@Service
public class EhrCityServiceImpl implements EhrCityService {

	@Resource
	private EhrCityMapper  ehrCitydao;
	
	@Override
	public List<HashMap<String, Object>> getCityByProvinceId(EhrCity record) {
		 
		return ehrCitydao.getCityByProvinceId(record);
	}

	@Override
	public List<EhrCity> getCityListByProvinceId(Long provinceId) {
		return ehrCitydao.getCityListByProvinceId(provinceId);
	}

	@Override
	public EhrCity getCitiByCityId(Long cityId) {
		return ehrCitydao.selectByPrimaryKey(cityId);
	}
	@Override
	public List<EhrCity> getCitiByCityName(String cityName) {
		return ehrCitydao.getCitiByCityName(cityName);
	}

}
