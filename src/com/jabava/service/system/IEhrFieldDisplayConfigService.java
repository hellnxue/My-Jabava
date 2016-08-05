package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrFieldDisplayConfig;

public interface IEhrFieldDisplayConfigService {
	/**
     * 根据方法编号删除动态字段
     * @param function
     * @return
     */
    public int deleteByFunction(Map<String, Object> map);
    
    public Map<String,Object> insertAutomateCol(List<EhrFieldDisplayConfig> ehrFieldDispiay);
    
    public List<EhrFieldDisplayConfig> selectDisplayCol(Map<String, Object> map);
}
