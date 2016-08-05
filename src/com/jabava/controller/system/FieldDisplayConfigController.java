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
	public Map<String, Object> displayCol(HttpServletRequest request , HttpServletResponse response ,String keyTable,String keyField,
			String function,String module){
		
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser();
		Map<String,Object> selectMap = new HashMap<String, Object>();
		selectMap.put("companyId", user.getCompanyId());
		selectMap.put("keyTable",keyTable);
		selectMap.put("keyField", keyField);
		selectMap.put("function",FieldCol.getFunction(function));
		selectMap.put("module", FieldCol.getMoudle(module));
		//所有动态列
		List<EhrTableFieldDef> list = ehrTableFieldDefService.selectCustomField(selectMap);
		//显示列
		List<EhrFieldDisplayConfig> displayColList= fieldDisplayConfigService.selectDisplayCol(selectMap);
		
		if(displayColList !=null&&displayColList.size()>0){
			
			//所有固定列
			List<Map<String, Object>> listCol = (List<Map<String, Object>>) FieldCol.getField(keyTable,1).get(keyTable);
			
			//将动态列的字段加入固定列中
			for(int i = 0; i<list.size();i++){
				Map<String, Object> mapCol=new HashMap<String, Object>();
				mapCol.put("columnName", list.get(i).getColumnName());
				mapCol.put("displayName", list.get(i).getDisplayName());
				listCol.add(mapCol);
			}
			
			//显示的列
			List<Map<String, Object>> displayList = new ArrayList<Map<String, Object>>();
			
			for(int j = 0;j<displayColList.size();j++){
				int z=0;
				for(int i = 0;i<listCol.size();i++){
					if(displayColList.get(j).getColumnName().equals(listCol.get(i).get("columnName"))){
						displayList.add(listCol.get(i));
						listCol.remove(i);
						}
					
				}
			}
			map.put("hiddenCol", listCol);
			map.put("displayCol", displayList);
			return map;
		}else{
			//所有固定列
			Map<String, Object> listCol = FieldCol.getField(keyTable,0);
			List<Map<String, Object>> hiddenCol= (List<Map<String, Object>>) listCol.get("hiddenCol"); 
			List<Map<String, Object>> displayName= (List<Map<String, Object>>) listCol.get("defaultCol"); 
			for(int i = 0; i<list.size();i++){
				Map<String, Object> mapCol=new HashMap<String, Object>();
				mapCol.put("columnName", list.get(i).getColumnName());
				mapCol.put("displayName", list.get(i).getDisplayName());
				hiddenCol.add(mapCol);
			}
			map.put("hiddenCol", hiddenCol);
			map.put("displayCol", displayName);
			return map;
		}
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
		fieldDisplayConfigService.deleteByFunction(deleteMap);
		for(int i =0;i<list.size();i++){
			ehrFieldDisplayConfig = new EhrFieldDisplayConfig();
			ehrFieldDisplayConfig.setColumnName(list.get(i));
			ehrFieldDisplayConfig.setCompanyId(user.getCompanyId());
			ehrFieldDisplayConfig.setFunction(FieldCol.getFunction(function));
			ehrFieldDisplayConfig.setModule(FieldCol.getMoudle(module));
			addList.add(ehrFieldDisplayConfig);
		}
		map = fieldDisplayConfigService.insertAutomateCol(addList);
		return map;
	}
}
