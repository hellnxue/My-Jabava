package com.jabava.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.enums.SystemEnum.Module;
/**
 * 
 * @author panfei
 *
 */
@Controller
@RequestMapping("/system")
public class BaseDataController {
	
	@Resource
	  public IBaseDataService baseDataService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;
/**
 * 查询基础数据
 * @param request
 * @param baseDataCode
 * @param baseDataName
 * @param baseDataType
 * @param isValid
 * @return
 */

	@RequestMapping("searchBaseData")
	public String SearchBaseData(HttpServletRequest request, String baseDataCode, String baseDataName,
			Integer baseDataType, Long isValid){
		
		return  "system/list_business";
	}
	@RequestMapping("besedataTableSearch")
	@ResponseBody
	public Page<EhrBaseData> dataTableSearch(HttpServletRequest request,String data, Integer start, Integer length){
	
		Map<String,Object> map = (Map<String , Object>) JSONObject.toBean(JSONObject.fromObject(data), Map.class);
		
		Page<EhrBaseData> page = new Page<>(start, length);
 		List<EhrBaseData> list = null;
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序	
		EhrUser user = RequestUtil.getLoginUser(request);
		map.put("companyId", user.getCompanyId());
		try {
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrBaseData.getColumnName(order);
			list = baseDataService.searchBaseData(map, search, order, according,isNumeric,page);
			page.setData(list);
			//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.SystemManagement, "查询基础数据列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
		
	}
	
	
	/***
	 * 添加基础数据
	 * @param request
	 * @param info
	 * @return
	 */
	@RequestMapping("insertBaseData")
	@ResponseBody
	public Map<String, Object> insertBaseData(HttpServletRequest request,HttpServletResponse response,EhrBaseData info){
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			info.setCompanyId(user.getCompanyId());
			info.setCreateDate(new Date());
			info.setCreateUserId(user.getUserId());
			info.setCreateUserName(user.getUserName());
			info.setIsValid(Byte.valueOf("1"));
			info.setLastModifyUserId(user.getUserId());
			info.setLastModifyUserName(user.getUserName());
			info.setLastModifyDate(new Date());
			data = baseDataService.insertBaseData(info);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.SystemManagement,
					"新增基础数据,基础数据名称:" + info.getBaseDataName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 查询对象得到页面数据(修改前查询)
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("selBaseDatabyId")
	@ResponseBody 
	public Map<String, Object> selectByBaseDataKey (Long id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		EhrBaseData dataInfo = null;
		try {
			 dataInfo = baseDataService.selectByBaseDataKey(id);
			 map.put("info", dataInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("selBaseDataType")
	@ResponseBody
	public Map<String, Object> selBaseDataType(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		try {
			List<EhrBaseDataType>  dataList = baseDataService.selectByBaseDataType();
			map.put("dataList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	@RequestMapping("updateBaseData")
	@ResponseBody
	public Map<String, Object> updateBaseData(EhrBaseData info,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			info.setCompanyId(user.getCompanyId());
			info.setLastModifyDate(new Date());
			info.setLastModifyUserId(user.getUserId());
			info.setLastModifyUserName(user.getUserName());
			info.setIsValid(Byte.valueOf("1"));
			data = baseDataService.updateBaseData(info);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.SystemManagement,
					"修改基础数据,基础数据名称:" + info.getBaseDataName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
 	@RequestMapping("deleteBaseData")
	public String deleteBaseData(String id,Long isValid,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			
		boolean result = baseDataService.updateByIsvalid(Long.valueOf(id),isValid);
			if(result){
				data.put("success", result);
		        data.put("msg", "成功");
			}else{
				data.put("success", result);
				data.put("msg", "失败");
			}
			if(isValid == 1){//有效
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.SystemManagement,
						"基础数据管理,有效基础数据ID:" + id);
			}if(isValid == 0){//无效
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.SystemManagement,
						"基础数据管理,无效基础数据ID:" + id);
			}
			response.getWriter().print(JSONArray.fromObject(data).toString());
		} catch ( Exception e) {
			 e.printStackTrace();
		}
		return null;
	} 

 	@RequestMapping("listBaseDataByType")
 	@ResponseBody
 	public List<EhrBaseData> listBaseDataByType(HttpServletRequest request,HttpServletResponse response,Integer baseDataType){
 		EhrUser user = RequestUtil.getLoginUser(request);
 		if(baseDataType == null){
 			return null;
 		}
 		
 		return baseDataService.selectBaseData(user.getCompanyId(), baseDataType);
 	}
 	/**
 	 * 批量导入基础数据
 	 * @param response
 	 * @param request
 	 * @param multipartFile
 	 * @param data
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping("importBaseData")
 	@ResponseBody
 	public Map<String,Object> importBaseData(HttpServletResponse response,HttpServletRequest request,@RequestParam("importFile") CommonsMultipartFile multipartFile) {
 		    Map<String,Object> map=new HashMap<String,Object>();
 			EhrUser user = RequestUtil.getLoginUser(request);
 			try {
				map = baseDataService.resolveBaseData(multipartFile);
				map = baseDataService.importBaseData(map,user);
				map = baseDataService.moreInsert(map);
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, Module.SystemManagement, "批量导入基本信息");
			} catch (Exception e) {
				e.printStackTrace();
				return MessageUtil.errorMessage(e.toString());
			}
 			return map;
 	}
}
