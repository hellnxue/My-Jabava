package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrFamily;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrFamilyService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrFamilyController {
	@Resource
	private EhrFamilyService familyService;
	@Resource
	private IBaseDataService baseDataService;
	
	
	/**
	 * 家庭成员页面入口
	 * @return
	 */
	@RequestMapping("familyInfoPage")
	public String experienceInfoPage(){
		 
		return "employees/famliys";
	}
	
	
	/**
	 * 家庭成员列表
	 * @param personId
	 * @return
	 */
	@RequestMapping("familyInfo")
	@ResponseBody
	public Map<String, Object> familyInfo(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", familyService.getByPersonId(personId));
			map.put("success", true);
			map.put("msg", "社会关系添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
		}
		return map;
	}
	
	@RequestMapping("addFamily")
	@ResponseBody
	public Map<String, Object> addFamily(@RequestBody EhrFamily family, HttpServletRequest request){
		Map<String, Object> map = null;
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = familyService.addFamily(family, user);
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateFamily")
	@ResponseBody
	public Map<String, Object> updateFamily(@RequestBody EhrFamily family, HttpServletRequest request){
		Map<String, Object> map = null;
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = familyService.updateFamily(family, user);
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteFamily")
	@ResponseBody
	public Map<String, Object> deleteFamily(Long familyId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = familyService.deleteFamily(familyId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除家庭成员失败！");
			e.printStackTrace();
		}
		return map;
	}
}





