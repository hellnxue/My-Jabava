package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrEducation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrEducationService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrEducationController {
	@Resource
	private EhrEducationService educationService;
	
	
	/**
	 * 教育背景页面入口
	 * @return
	 */
	@RequestMapping("educationInfoPage")
	public String educationInfoPage(){
		 
		return "employees/educational_background";
	}
	/**
	 * 教育背景列表
	 * @param personId
	 * @return
	 */
	@RequestMapping("educationInfo")
	@ResponseBody
	public Map<String, Object> educationInfo(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", educationService.getByPersonId(personId));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("addEducation")
	@ResponseBody
	public Map<String, Object> addEducation(@RequestBody EhrEducation education, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = educationService.addEducation(education, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加教育背景失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateEducation")
	@ResponseBody
	public Map<String, Object> updateEducation(@RequestBody EhrEducation education, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = educationService.updateEducation(education, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改教育背景失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteEducation")
	@ResponseBody
	public Map<String, Object> deleteEducation(Long educationId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = educationService.deleteEducation(educationId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除教育背景失败！");
			e.printStackTrace();
		}
		return map;
	}
}
