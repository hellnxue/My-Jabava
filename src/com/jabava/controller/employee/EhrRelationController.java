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

import com.jabava.pojo.employee.EhrRelation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrRelationService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrRelationController {
	@Resource
	private EhrRelationService relationService;
	
	/**
	 * 家庭成员
	 * @param personId
	 * @return
	 */
	@RequestMapping("/relationInfo")
	@ResponseBody
	public Map<String, Object> getRelation(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", relationService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/***
	 * 添加家庭成员
	 * @param relation
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addRelation")
	@ResponseBody
	public Map<String, Object> addRelation(EhrRelation relation, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			relation.setCreateDate(new Date());
			relation.setCreateUserId(u.getUserId());
			relation.setCreateUserName(u.getUserName());
			relation.setLastModifyDate(new Date());
			relation.setLastModifyUserId(u.getUserId());
			relation.setLastModifyUserName(u.getUserName());
			boolean result = relationService.addRelation(relation);
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
	 * 修改家庭成员
	 * @param relation
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateRelation")
	@ResponseBody
	public Map<String, Object> updateRelation(EhrRelation relation, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			relation.setLastModifyDate(new Date());
			relation.setLastModifyUserId(u.getUserId());
			relation.setLastModifyUserName(u.getUserName());
			boolean result = relationService.updateRelation(relation);
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
	 * 删除家庭成员
	 * @param relationId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delRelation")
	@ResponseBody
	public Map<String, Object> delRelation(Long relationId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =relationService.delRelation(relationId);
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
