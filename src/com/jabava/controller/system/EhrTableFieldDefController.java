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
	public Page<EhrTableFieldDef> selectTableFieldDef(HttpServletRequest request,HttpServletResponse response, Integer start,Integer length ){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", user.getCompanyId());
		Page<EhrTableFieldDef> page = new Page<EhrTableFieldDef>(start, length);
		map.put("page", page);
		List<EhrTableFieldDef> list = tableFieldDefService.selectCoustomFieldByPage(map);
		for(int i = 0;i<list.size();i++){
			EhrTableFieldDef ehrTableFieldDef = list.get(i);
			
			if(ehrTableFieldDef.getRefId() != null){
			int baseDataType = ehrTableFieldDef.getRefId();
			EhrBaseDataType ehrBaseDataType = baseDataService.selectByPrimaryKey(baseDataType);
			
			System.out.println(ehrBaseDataType.getBaseDataTypeName());
			String refName = ehrBaseDataType.getBaseDataTypeName();
			ehrTableFieldDef.setRefName(refName);
			}
		}
		page.setData(list);
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
		 //关联表
		 List<EhrTableDef> linkList = ehrTableDefService.selectKeyTable(user.getCompanyId());
		 map.put("linkList", linkList);
		return map;
		
	}
	@RequestMapping("addOrUpdataFieldDef")
	@ResponseBody
	public Map<String, Object> addOrUpdataFieldDef(HttpServletRequest request,HttpServletResponse response,@RequestBody EhrTableFieldDef tableFieldDef){
		String columnName = UUID.randomUUID().toString();
		System.out.println(columnName);
		tableFieldDef.setColumnName(columnName);
		Map<String, Object> map= tableFieldDefService.insertOrUpdataFiledDef(tableFieldDef);
		return map;
		
	}
	@RequestMapping("selectFieldDef")
	@ResponseBody
	public EhrTableFieldDef selectFieldDef(HttpServletRequest request,HttpServletResponse response ,Long tableFieldDefId){
		EhrTableFieldDef ehrTableFieldDef = tableFieldDefService.selectByPrimaryKey(tableFieldDefId);
		return ehrTableFieldDef;
	}
	@RequestMapping("deleteFieldDef")
	@ResponseBody
	public Map<String, Object> deleteFieldDef(HttpServletRequest request,HttpServletResponse response ,Long tableFieldDefId){
		Map<String, Object> map = tableFieldDefService.deleteByPrimaryKey(tableFieldDefId);
		return map;
	}
}
