package com.jabava.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.system.EhrFieldDisplayConfigMapper;
import com.jabava.pojo.system.EhrFieldDisplayConfig;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.service.system.IEhrFieldDisplayConfigService;
import com.jabava.utils.FieldCol;
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
		if(ehrFieldDispiay != null && ehrFieldDispiay.size()>0){
			for(int i=0;i<ehrFieldDispiay.size();i++){
				EhrFieldDisplayConfig ehrFieldDisplayConfig = ehrFieldDispiay.get(i);
				result= ehrFieldDisplayConfigMapper.insertSelective(ehrFieldDisplayConfig);
			}
			if(result>0){
				map.put("success",true);
				map.put("msg", "保存成功");
			}else{
				map.put("success",false);
				map.put("msg", "保存失败");
			}
		}else{
			map.put("success",true);
			map.put("msg", "保存成功");
		}
		return map;
	}

	
	@Override
	public List<EhrFieldDisplayConfig> selectDisplayCol(Map<String, Object> map) {
		return ehrFieldDisplayConfigMapper.selectByFunction(map);
	}

	@Override
	public Map<String, Object> handlerDisplayCol(EhrTableFieldDefService  ehrTableFieldDefService,Map<String, Object> params) {
		Map<String, Object> data = new HashMap<>();
		//所有的自定列-动态列
		List<EhrTableFieldDef> customFieldList = ehrTableFieldDefService.selectCustomField(params);
		
		params.put("function",FieldCol.getFunction("EmployeeList"));
		params.put("module", FieldCol.getMoudle("Organization"));
		
		//配置的显示列 原始数据
		List<EhrFieldDisplayConfig> displayColList=  selectDisplayCol(params);
		
		//所有固定列
		List<Map<String, Object>> listCol = (List<Map<String, Object>>) FieldCol.getField("ehr_person",1).get("ehr_person");
		
		//Map<String, Object> customFieldMap = new HashMap<String, Object>();
		
		//将动态列的字段加入固定列中
		if(customFieldList != null && !customFieldList.isEmpty()){
			for(int i = 0; i<customFieldList.size();i++){
				Map<String, Object> mapCol=new HashMap<String, Object>();
				mapCol.put("columnName", customFieldList.get(i).getColumnName());
				mapCol.put("displayName", customFieldList.get(i).getDisplayName());
				listCol.add(mapCol);
				
				//customFieldMap.put(customFieldList.get(i).getColumnName(), customFieldList.get(i));
			}
		}
		
		Map<String, Object> allFiledsMap=new HashMap<String,Object>();
		
		for(Map<String, Object> colmap:listCol){
			allFiledsMap.put((String)colmap.get("columnName"), colmap.get("displayName"));
		}
		
		//含有displayName与columnName的显示列
		List<Map<String, Object>> displayList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> xs=null;
		
		//遍历显示列  获取displayName
		if(displayColList != null && !displayColList.isEmpty() ){
			for(EhrFieldDisplayConfig fieldDisplayConfig:displayColList){
				
				String columnName=fieldDisplayConfig.getColumnName();
				String displayName=(String) allFiledsMap.get(columnName);
				if(displayName!=null){
					xs=new HashMap<String,Object>();
					xs.put("columnName", columnName);
					xs.put("displayName", displayName);
	 				displayList.add(xs);
				}
				
				
			}
			data.put("Fileds", displayList);
		}else{
			//没有显示列的情况下，显示默认字段
			List<Map<String, Object>> defaultCol = (List<Map<String, Object>>) FieldCol.getField("ehr_person",0).get("defaultCol") ;
			data.put("Fileds", defaultCol);
		}
		
		data.put("linkFiled", "employee_name");//设置超链接字段
		return data;
	}

	@Override
	public Map<String, Object> deleteAndinsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		deleteMap.put("companyId", map.get("companyId"));
		deleteMap.put("function", map.get("function"));
		deleteMap.put("module", map.get("module"));
		deleteByFunction(deleteMap);
		List<EhrFieldDisplayConfig> ehrFieldDispiay = (List<EhrFieldDisplayConfig>) map.get("addList");
		resultMap = insertAutomateCol(ehrFieldDispiay);
		return resultMap;
	}

	@Override
	public int deleteColumnName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = ehrFieldDisplayConfigMapper.deleteByColumnName(map);
		return result;
	}
}
