package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrResume;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrResumeService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrResumeController {
	@Resource
	private EhrResumeService resumeService;
	
	/**
	 * 工作经验
	 * @param personId
	 * @return
	 */
	@RequestMapping("/resumeInfo")
	@ResponseBody
	public Map<String, Object> getResume(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", resumeService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加工作经验
	 * @param resume
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addResume")
	@ResponseBody
	public Map<String, Object> addResume(EhrResume resume, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			resume.setCreateDate(new Date());
			resume.setCreateUserId(u.getUserId());
			resume.setCreateUserName(u.getUserName());
			resume.setLastModifyDate(new Date());
			resume.setLastModifyUserId(u.getUserId());
			resume.setLastModifyUserName(u.getUserName());
			boolean result = resumeService.addResume(resume);
			if(result){
				data.put("success", result);
		        data.put("msg", "添加成功");
			}else{
				data.put("success", result);
				data.put("msg", "添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	
	/**
	 * 修改工作经验
	 * @param resume
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateResume")
	@ResponseBody
	public Map<String, Object> updateResume(EhrResume resume, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			resume.setLastModifyDate(new Date());
			resume.setLastModifyUserId(u.getUserId());
			resume.setLastModifyUserName(u.getUserName());
			boolean result = resumeService.updateResume(resume);
			if(result){
				data.put("success", result);
		        data.put("msg", "修改成功");
			}else{
				data.put("success", result);
				data.put("msg", "修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	
	/**
	 * 删除工作经验
	 * @param resumeId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delResume")
	@ResponseBody
	public Map<String, Object> delResume(Long resumeId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result = resumeService.delResume(resumeId);
			if(result){
				data.put("success", result);
		        data.put("msg", "删除成功");
			}else{
				data.put("success", result);
				data.put("msg", "删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		 
		 
		 
	 }
	
}
