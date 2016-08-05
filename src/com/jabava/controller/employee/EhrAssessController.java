package com.jabava.controller.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrAssess;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrAssessService;
import com.jabava.utils.RequestUtil;

/**
 * 绩效考核
 *
 * @version $Id: EhrAssessController.java, 
 * v 0.1 2016年3月24日 下午2:53:37 
 * <pre>
 * @author steven.chen
 * @date 2016年3月24日 下午2:53:37 
 * </pre>
 */
@Controller
@RequestMapping("employee")
public class EhrAssessController {
	@Resource
	private EhrAssessService assessService;
	
	/**
	 * 绩效考核
	 * @param personId
	 * @return
	 */
	@RequestMapping("/assessInfo")
	@ResponseBody
	public Map<String, Object> getAssess(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", assessService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 添加绩效考核
	 * @param assess
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addAssess")
	@ResponseBody
	public Map<String, Object> addAssess(EhrAssess assess, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			assess.setCreateDate(new Date());
			assess.setCreateUserId(u.getUserId());
			assess.setCreateUserName(u.getUserName());
			assess.setLastModifyDate(new Date());
			assess.setLastModifyUserId(u.getUserId());
			assess.setLastModifyUserName(u.getUserName());
			boolean result = assessService.addAssess(assess);
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
	 * 修改绩效考核
	 * @param assess
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateAssess")
	@ResponseBody
	public Map<String, Object> updateAssess(EhrAssess assess, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			assess.setLastModifyDate(new Date());
			assess.setLastModifyUserId(u.getUserId());
			assess.setLastModifyUserName(u.getUserName());
			boolean result = assessService.updateAssess(assess);
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
	 * 删除绩效考核
	 * @param assessId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delAssess")
	@ResponseBody
	public Map<String, Object> delAssess(Long assessId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =assessService.delAssess(assessId);
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
	/**
	 * 格式化date 类型
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午5:16:27 
	 * </pre>
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
