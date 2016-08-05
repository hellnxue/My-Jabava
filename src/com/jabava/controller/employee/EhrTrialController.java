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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrTrial;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrTrialService;
import com.jabava.utils.RequestUtil;
/**
 * 试用情况
 *
 * @version $Id: EhrTrialController.java, 
 * v 0.1 2016年3月24日 下午1:24:58 
 * <pre>
 * @author steven.chen
 * @date 2016年3月24日 下午1:24:58 
 * </pre>
 */
@Controller
@RequestMapping("employee")
public class EhrTrialController {
	@Resource
	private EhrTrialService trialService;
	
	/**
	 * 试用情况
	 * @param personId
	 * @return
	 */
	@RequestMapping("/trialInfo")
	@ResponseBody
	public Map<String, Object> getTrial(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", trialService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加试用情况
	 * @param trial
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addTrial")
	@ResponseBody
	public Map<String, Object> addTrial(@RequestBody EhrTrial trial,HttpServletRequest request){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			trial.setCreateDate(new Date());
			trial.setCreateUserId(u.getUserId());
			trial.setCreateUserName(u.getUserName());
			trial.setLastModifyDate(new Date());
			trial.setLastModifyUserId(u.getUserId());
			trial.setLastModifyUserName(u.getUserName());
			boolean result = trialService.addTrial(trial);
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
	 * 修改试用情况
	 * @param trial
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateTrial")
	@ResponseBody
	public Map<String, Object> updateTrial(@RequestBody EhrTrial trial, HttpServletRequest request){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			trial.setLastModifyDate(new Date());
			trial.setLastModifyUserId(u.getUserId());
			trial.setLastModifyUserName(u.getUserName());
			boolean result = trialService.updateTrial(trial);
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
	 * 删除试用情况
	 * @param trialId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delTrial")
	@ResponseBody
	public Map<String, Object> delTrial(Long trialId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =trialService.delTrial(trialId);
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
