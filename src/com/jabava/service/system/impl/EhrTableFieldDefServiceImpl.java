package com.jabava.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.system.EhrTableDataMapper;
import com.jabava.dao.system.EhrTableFieldDefMapper;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.system.EhrTableData;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.utils.constants.CommonDataConstants;

@Service
public class EhrTableFieldDefServiceImpl implements EhrTableFieldDefService {

	@Resource
	private EhrTableFieldDefMapper tableFieldDefMapper;
	
	@Resource
	private EhrBaseDataMapper dataMapper;
	
	@Resource
	private EhrTableDataMapper tableDataMapper;
	
	@Resource
	EhrCommonDataMapper commonDataMapper;
	
	@Override
	public List<EhrTableFieldDef> selectCustomFieldAndData(Long companyId, Map<String, Object> params) {
		
		List<EhrTableFieldDef> customFieldList=tableFieldDefMapper.selectCustomFieldAndData(params);
		
		Map<Long,EhrTableData> tableDataMap=getCustomFieldData(params);
		
		if(customFieldList!=null&&customFieldList.size()>0){
			Map<String, Object> customMap = null;
			for(EhrTableFieldDef tableFieldDef:customFieldList){
				 customMap = new HashMap<String, Object>();
				 
				 //基础数据处理
				 if(tableFieldDef.getRefId()!=null){
					 int baseDataType=  tableFieldDef.getRefId();//基础数据类型
					 List<EhrBaseData> baseDataList=dataMapper.selectByType(companyId, baseDataType);
//					 customMap.put("baseDataList", baseDataList);//基础数据
					 tableFieldDef.setBaseDataList(baseDataList); 
				 }
				 
				 //自定义字段与数据关联
				 if(tableDataMap!=null){
 					 EhrTableData tableData=tableDataMap.get(tableFieldDef.getTableFieldDefId());
 					 if(tableData!=null){
 						 customMap.put("tableDataId", tableData.getTableDataId());
 	 					 customMap.put("keyValue", tableData.getKeyValue());
 	 					 customMap.put("value", tableData.getValue());
 	 					 tableFieldDef.setCustomMeta(customMap);
 					 }
 					
				 }
				 
			}
			
		}
		
		return customFieldList;
	}
	
	
	public Map<Long,EhrTableData> getCustomFieldData(Map<String,Object> params){
		Map<Long,EhrTableData> map=new HashMap<Long,EhrTableData>();
		List<EhrTableData> tdlist= tableDataMapper.selectCustomFieldData(params);
		for(EhrTableData data : tdlist){
			map.put(data.getTableFieldDefId(), data);
		}
		
		return map;
	}


	@Override
	public List<EhrTableFieldDef> selectCustomField(Map<String, Object> map) {
		List<EhrTableFieldDef> list=tableFieldDefMapper.selectCustomFieldAndData(map);
		if(list!=null&&list.size()>0){
			for(EhrTableFieldDef tableFieldDef:list){
				 //基础数据处理
				 if(tableFieldDef.getRefId()!=null){
					 int baseDataType=  tableFieldDef.getRefId();//基础数据类型
					 List<EhrBaseData> baseDataList=dataMapper.selectByType((Long)map.get("companyId"), baseDataType);
					 tableFieldDef.setBaseDataList(baseDataList); 
				 }
			}
			return list;
		}
		return null;
	}


	@Override
	public List<EhrTableFieldDef> selectCoustomFieldByPage(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<EhrTableFieldDef> list = tableFieldDefMapper.selectByPage(map);
		return list;
	}


	@Override
	public Map<String,Object> insertOrUpdataFiledDef(EhrTableFieldDef ehrTableFieldDef) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		if(ehrTableFieldDef.getTableFieldDefId()==null){
			int result = tableFieldDefMapper.insertSelective(ehrTableFieldDef);
			if(result>0){
				map.put("success", true);
				map.put("msg", "添加成功");
			}else{
				map.put("success", false);
				map.put("msg", "添加失败");
			}
			return map;
		}else{
			int result = tableFieldDefMapper.updateByPrimaryKeySelective(ehrTableFieldDef);
			if(result>0){
				map.put("success", true);
				map.put("msg", "修改成功");
			}else{
				map.put("success", false);
				map.put("msg", "修改失败");
			}
			return map;
		}
	}


	@Override
	public EhrTableFieldDef selectByPrimaryKey(Long tableFieldDefId) {
		// TODO Auto-generated method stub
		EhrTableFieldDef ehrTableFieldDef = tableFieldDefMapper.selectByPrimaryKey(tableFieldDefId);
		return ehrTableFieldDef;
	}


	@Override
	public Map<String,Object> deleteByPrimaryKey(Long tableFieldDefId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		int result =tableFieldDefMapper.deleteByPrimaryKey(tableFieldDefId);
		if(result>0){
			map.put("success", true);
			map.put("msg", "删除成功");
		}else{
			map.put("success", false);
			map.put("msg", "删除失败");
		}
		return map;
	}
}
