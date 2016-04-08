package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrTrain;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrTrainService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrTrainController {
	@Resource
	private EhrTrainService trainService;
	
	
	/**
	 *培训经历页面入口
	 * @return
	 */
	@RequestMapping("trainInfoPage")
	public String trainInfoPage(){
		 
		return "employees/training_experience";
	}
	/**
	 * 培训经历
	 * @param personId
	 * @return
	 */
	@RequestMapping("/trainInfo")
	@ResponseBody
	public Map<String, Object> getTrain(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", trainService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("addTrain")
	@ResponseBody
	public Map<String, Object> addTrain(@RequestBody EhrTrain train, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = trainService.addTrain(train, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加培训经历失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateTrain")
	@ResponseBody
	public Map<String, Object> updateTrain(@RequestBody EhrTrain train, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = trainService.updateTrain(train, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改培训经历失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteTrain")
	@ResponseBody
	public Map<String, Object> deleteTrain(Long trainId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = trainService.deleteTrain(trainId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除培训经历失败！");
			e.printStackTrace();
		}
		return map;
	}
}
