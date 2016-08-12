package com.jabava.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EhrTableData;
import com.jabava.pojo.system.EhrTableDef;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.common.ICommonDataService;
import com.jabava.service.system.EhrTableDataService;
import com.jabava.service.system.EhrTableDefService;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.CommonDataConstants;

@Controller
@RequestMapping("/system")
public class EhrTableFieldDefController {
	
	@Resource
	private EhrTableDataService tableDataService;
	@Resource
	private EhrTableFieldDefService tableFieldDefService;
	@Resource
	private EhrTableDefService ehrTableDefService;
	@Resource
	private ICommonDataService commonDataService;
	@Resource
	private IBaseDataService baseDataService;
	@Resource
	private EhrTableDataService ehrTableDataService;
	/**
	 * 进入自定义列表页面
	 * @return
	 */
	@RequestMapping("/toListTableFieldDef")
	public String addPerson(){
		return "system/listTableFieldDef";
	}
	
	@RequestMapping("listTableFieldDef")
	@ResponseBody
	public Page<Map<String, Object>> selectTableFieldDef(HttpServletRequest request,HttpServletResponse response, Integer start,Integer length ){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", user.getCompanyId());
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		map.put("page", page);
		List<Map<String, Object>> list = tableFieldDefService.selectCoustomFieldByPage(map);
		for(int i = 0;i<list.size();i++){
			Map<String, Object> map1 = list.get(i);
			
			if(map1.get("refId") != null){
			int baseDataType = (int) map1.get("refId");
			EhrBaseDataType ehrBaseDataType = baseDataService.selectByPrimaryKey(baseDataType);
			String refName = ehrBaseDataType.getBaseDataTypeName();
			map1.put("refName",refName);
			}
		}
		page.setData(list);;
		return page;
	}
	
	@RequestMapping("addTableFieldDef")
	@ResponseBody
	public Map<String, Object> addTableFieldDef(HttpServletRequest request,HttpServletResponse response ){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser();
		//信息项类型基础数据
		 List<EhrCommonData> commonDataList= commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CUSTOM_DATA_TYPE);//自定义字段信息项类型
		 map.put("commonDataList", commonDataList);
		 //参照项
		 List<EhrBaseDataType> consultList = baseDataService.selectBaseDateType();
		 map.put("consultList", consultList);
		/* //关联表
		 List<EhrTableDef> linkList = ehrTableDefService.selectKeyTable(user.getCompanyId());
		 map.put("linkList", linkList);*/
		return map;
		
	}
	
	@RequestMapping("addOrUpdataFieldDef")
	@ResponseBody
	public Map<String, Object> addOrUpdataFieldDef(HttpServletRequest request,  @RequestBody EhrTableFieldDef tableFieldDef){
		Map<String, Object> tableDefmap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		EhrTableDef ehrTableDef1 = new EhrTableDef();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		if(tableFieldDef.getTableFieldDefId()==null){
			String columnName = UUID.randomUUID().toString();
			tableFieldDef.setColumnName(columnName);
			String keytable = tableFieldDef.getEhrTableDef().getKeyTable();
			tableDefmap.put("companyId",companyId);
			tableDefmap.put("keyTable",keytable);
			//查找关联表所在的数据
			EhrTableDef ehrTableDef = ehrTableDefService.selectByCompanyId(tableDefmap);
			if(ehrTableDef!=null){
				int tableDefId = ehrTableDef.getTableDefId();
				tableFieldDef.setTableDefId(tableDefId);
			}else{
				ehrTableDef1 = tableFieldDef.getEhrTableDef();
				ehrTableDef1.setCompanyId(companyId);
				ehrTableDef1.setIsDeleted(0);
				//新增一个关联表
				int tableDefId =  ehrTableDefService.insert(ehrTableDef1);
				if(tableDefId ==0){
					map.put("success", false);
					map.put("msg", "添加失败");
					return map;
				}
				tableFieldDef.setTableDefId(ehrTableDef1.getTableDefId());
			}
		}
		 map= tableFieldDefService.insertOrUpdataFiledDef(tableFieldDef);
		return map;
		
	}
	
	@RequestMapping("selectFieldDef")
	@ResponseBody
	public Map<String, Object> selectFieldDef(HttpServletRequest request,HttpServletResponse response ,Long tableFieldDefId){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrTableFieldDef ehrTableFieldDef = tableFieldDefService.selectByPrimaryKey(tableFieldDefId);
		 List<EhrCommonData> commonDataList= commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CUSTOM_DATA_TYPE);//自定义字段信息项类型
		//信息项类型基础数据
		 map.put("commonDataList", commonDataList);
		 //参照项
		 List<EhrBaseDataType> consultList = baseDataService.selectBaseDateType();
		 map.put("consultList", consultList);
		 map.put("ehrTableFieldDef", ehrTableFieldDef);
		return map;
	}
	@RequestMapping("deleteFieldDef")
	@ResponseBody
	public Map<String, Object> deleteFieldDef(HttpServletRequest request,Long tableFieldDefId , String columnName){
		Map<String, Object> deleteColumnNameMap=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<EhrTableData> ehrTableDataList = ehrTableDataService.selectByTableFieldDefId(tableFieldDefId);
		if(ehrTableDataList!=null && ehrTableDataList.size()>0 ){
			map.put("success", false);
			map.put("msg", "该列已有数据，禁止删除");
		}else {
			EhrUser user = RequestUtil.getLoginUser(request);
			Long companyId = user.getCompanyId();
			deleteColumnNameMap.put("columnName", columnName);
			deleteColumnNameMap.put("companyId", companyId);
			map = tableFieldDefService.deleteByPrimaryKey(tableFieldDefId,deleteColumnNameMap);
		}
		return map;
	}
}
