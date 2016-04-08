package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrExperience;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrExperienceService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrExperienceController {
	@Resource
	private EhrExperienceService experienceService;
	@Resource
	private IBaseDataService baseDataService;
	
	
	/**
	 * 工作经验页面入口
	 * @return
	 */
	@RequestMapping("experienceInfoPage")
	public String experienceInfoPage(){
		 
		return "employees/work_experience";
	}
	
	/**
	 * 工作经验列表
	 * @param personId
	 * @return
	 */
	@RequestMapping("experienceInfo")
	@ResponseBody
	public Map<String, Object> experienceInfo(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", experienceService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("addExperience")
	@ResponseBody
	public Map<String, Object> addexperience(@RequestBody EhrExperience experience, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = experienceService.addExperience(experience, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加工作经验失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateExperience")
	@ResponseBody
	public Map<String, Object> updateexperience(@RequestBody EhrExperience experience, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = experienceService.updateExperience(experience, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改工作经验失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteExperience")
	@ResponseBody
	public Map<String, Object> deleteexperience(Long experienceId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = experienceService.deleteExperience(experienceId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除工作经验失败！");
			e.printStackTrace();
		}
		return map;
	}
}
