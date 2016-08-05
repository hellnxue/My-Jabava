package com.jabava.service.tools;

import java.util.HashMap;
import java.util.List;

import com.jabava.pojo.tools.EhrCity;

public interface EhrCityService {
	 /**
     *根据省份id查询对应城市列表
     */
    List<HashMap<String,Object>> getCityByProvinceId(EhrCity record);
    /**
     * 根据省份id查询对应城市列表 返回list<EhrCity>
     * <pre>
     * @author steven.chen
     * @date 2016年4月13日 下午4:36:17 
     * </pre>
     *
     * @return
     */
    List<EhrCity> getCityListByProvinceId(Long provinceId); 
    /**
     * 根据ID获取城市信息
     * <pre>
     * @author steven.chen
     * @date 2016年4月15日 下午1:03:46 
     * </pre>
     *
     * @param cityId
     * @return
     */
    EhrCity getCitiByCityId(Long cityId);
    /**
     * 根据城市名称获取城市
     * <pre>
     * @author steven.chen
     * @date 2016年4月15日 下午1:29:54 
     * </pre>
     *
     * @param cityName
     * @return
     */
	List<EhrCity> getCitiByCityName(String cityName);
    
}
