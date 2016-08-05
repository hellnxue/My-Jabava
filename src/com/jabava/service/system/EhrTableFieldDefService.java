package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrTableFieldDef;

public interface EhrTableFieldDefService {
	  /**
     * 查询扩展字段 &数据
     * @param map
     * @return
     */
    List<EhrTableFieldDef> selectCustomFieldAndData(Long companyId,Map<String,Object> map);
    
    /**
     * 查询表的扩展字段
     * @param map
     * @return
     */
    List<EhrTableFieldDef> selectCustomField( Map<String, Object> map);
    /**
     * 分页查询扩展字段
     * @param map
     * @return
     */
    List<EhrTableFieldDef> selectCoustomFieldByPage( Map<String,Object> map);

    Map<String, Object> insertOrUpdataFiledDef(EhrTableFieldDef ehrTableFieldDef);
    /**
     * 根据主键查数据
     * @param tableFieldDefId
     * @return
     */
    EhrTableFieldDef selectByPrimaryKey(Long tableFieldDefId);
    /**
     * 根据主键删除数据
     * @param tableFieldDefId
     * @return
     */
    Map<String, Object> deleteByPrimaryKey(Long tableFieldDefId);
  
}
