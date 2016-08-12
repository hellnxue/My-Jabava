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
    
    /**
     * 获取花名册的自定义字段显示项列表 
     * @return
     */
    Map<String, Object> handlerDisplayCol(EhrTableFieldDefService  ehrTableFieldDefService,Map<String, Object> params);
    
    public Map<String,Object> deleteAndinsert(Map<String, Object> map);
    
    public int deleteColumnName(Map<String, Object> map);
}
