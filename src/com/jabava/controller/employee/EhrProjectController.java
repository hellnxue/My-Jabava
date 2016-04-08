package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrProject;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrProjectService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrProjectController {
	@Resource
	private EhrProjectService projectService;
	
	/**
	 * 项目经验页面入口
	 * @return
	 */
	@RequestMapping("projectInfoPage")
	public String projectInfoPage(){
		 
		return "employees/project_experience";
	}
	
	/**
	 * 项目经验
	 * @param personId
	 * @return
	 */
	@RequestMapping("/projectInfo")
	@ResponseBody
	public Map<String, Object> getProject(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", projectService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加项目经验
	 * @param project
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addProject")
	@ResponseBody
	public Map<String, Object> addProject(@RequestBody EhrProject project, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			project.setCreateDate(new Date());
			project.setCreateUserId(u.getUserId());
			project.setCreateUserName(u.getUserName());
			project.setLastModifyDate(new Date());
			project.setLastModifyUserId(u.getUserId());
			project.setLastModifyUserName(u.getUserName());
			boolean result = projectService.addProject(project);
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
	 * 修改项目经验
	 * @param project
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateProject")
	@ResponseBody
	public Map<String, Object> updateProject(@RequestBody EhrProject project, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			project.setLastModifyDate(new Date());
			project.setLastModifyUserId(u.getUserId());
			project.setLastModifyUserName(u.getUserName());
			boolean result = projectService.updateProject(project);
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
	 * 删除项目经验
	 * @param projectId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delProject")
	@ResponseBody
	public Map<String, Object> delProject(Long projectId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result = projectService.delProject(projectId);
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
