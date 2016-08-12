package com.jabava.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EhrFieldDisplayConfig;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.service.system.IEhrFieldDisplayConfigService;
import com.jabava.utils.FieldCol;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/system")
public class FieldDisplayConfigController {
	@Resource
	private EhrTableFieldDefService  ehrTableFieldDefService;
	@Resource
	private IEhrFieldDisplayConfigService fieldDisplayConfigService;
	
	
	@RequestMapping("toListFieldDisplayConfig")
	public String toListFieldDisplayConfig(){
		return "system/listFieldDisplayConfig";
	}
	@RequestMapping("listFieldDisplayConfig")
	@ResponseBody
	public Map<String, Object> displayCol(HttpServletRequest request , HttpServletResponse response,
			String keyTable,String keyField, String function,String module){
		
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser();
		Map<String,Object> selectMap = new HashMap<String, Object>();
		selectMap.put("companyId", user.getCompanyId());
		/*selectMap.put("keyTable",keyTable);
		selectMap.put("keyField", keyField);*/
		selectMap.put("function",FieldCol.getFunction(function));
		selectMap.put("module", FieldCol.getMoudle(module));
		
		//所有动态列
		List<EhrTableFieldDef> dynamicList = ehrTableFieldDefService.selectCustomField(selectMap);
		
		//显示列
		List<EhrFieldDisplayConfig> displayColList= fieldDisplayConfigService.selectDisplayCol(selectMap);
		Map<String,EhrFieldDisplayConfig> displayColMap = new HashMap<String,EhrFieldDisplayConfig>();
		for(EhrFieldDisplayConfig config : displayColList){
			displayColMap.put(config.getColumnName(), config);
		}
		
		List<Map<String, Object>> hiddenList = null;
		List<Map<String, Object>> displayList = null;
		if(displayColList != null && displayColList.size() > 0){
			//所有固定列
			List<Map<String, Object>> listCol = (List<Map<String, Object>>) FieldCol.getField(keyTable,1).get(keyTable);
			for(EhrTableFieldDef def : dynamicList){
				Map<String, Object> mapCol = new HashMap<String, Object>();
				mapCol.put("columnName", def.getColumnName());
				mapCol.put("displayName", def.getDisplayName());
				//加上所有动态列
				listCol.add(mapCol);
			}
			
			hiddenList = new ArrayList<Map<String, Object>>();
			displayList = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> col : listCol){
				if(displayColMap.containsKey(col.get("columnName"))){
					displayList.add(col);
				}else{
					hiddenList.add(col);
				}
			}
		}else{
			//所有固定列
			Map<String, Object> colMap = FieldCol.getField(keyTable,0);
			//隐藏的固定列
			hiddenList = (List<Map<String, Object>>) colMap.get("hiddenCol");
			//显示的固定列
			displayList = (List<Map<String, Object>>) colMap.get("defaultCol"); 
			for(EhrTableFieldDef def : dynamicList){
				Map<String, Object> mapCol = new HashMap<String, Object>();
				mapCol.put("columnName", def.getColumnName());
				mapCol.put("displayName", def.getDisplayName());
				//加上所有动态列
				hiddenList.add(mapCol);
			}
		}
		
		map.put("hiddenCol", hiddenList);
		map.put("displayCol", displayList);
		return map;
	}
	
	
	@RequestMapping("addDisplayCol")
	@ResponseBody
	public Map<String, Object> addDisplayCol(HttpServletRequest request , HttpServletResponse response,String list1,String function,String module ){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrFieldDisplayConfig ehrFieldDisplayConfig = null; 
		Map<String,Object> deleteMap = new HashMap<String, Object>();
		List<String> list = JSON.parseArray(list1, String.class);
		List<EhrFieldDisplayConfig> addList= new ArrayList<EhrFieldDisplayConfig>();
		deleteMap.put("companyId", user.getCompanyId());
		deleteMap.put("function", FieldCol.getFunction(function));
		deleteMap.put("module", FieldCol.getMoudle(module));
		for(int i =0;i<list.size();i++){
			ehrFieldDisplayConfig = new EhrFieldDisplayConfig();
			ehrFieldDisplayConfig.setColumnName(list.get(i));
			ehrFieldDisplayConfig.setCompanyId(user.getCompanyId());
			ehrFieldDisplayConfig.setFunction(FieldCol.getFunction(function));
			ehrFieldDisplayConfig.setModule(FieldCol.getMoudle(module));
			addList.add(ehrFieldDisplayConfig);
		}
		deleteMap.put("addList", addList);
		map = fieldDisplayConfigService.deleteAndinsert(deleteMap);
		return map;
	}
}
