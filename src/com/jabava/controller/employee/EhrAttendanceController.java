package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.service.employee.EhrAttendanceService;

@Controller
@RequestMapping("employee")
public class EhrAttendanceController {
	@Resource
	private EhrAttendanceService attendanceService;
	
	/**
	 * 考勤记录
	 * @param personId
	 * @return
	 */
	@RequestMapping("/attendanceInfo")
	@ResponseBody
	public Map<String, Object> getAttendance(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", attendanceService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
