package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrAttendance;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrAttendanceService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("employee")
public class EhrAttendanceController {
	@Resource
	private IEhrSysLogSercice sysLogSercice;
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

	@RequestMapping("/listAttentancePage")
	@ResponseBody
	public Page<Map<String, Object>> listAttentancePage(HttpServletRequest request, HttpServletResponse response, int start, int length) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("yearMonthRecord", request.getParameter("yearMonthRecord"));
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		params.put("page", page);
		List<Map<String,Object>> attendList = attendanceService.listAttentancePage(params);
		page.setData(attendList);
		return page;
	}

	@RequestMapping("/deleteByYearMonthRecord")
	@ResponseBody
	public Map<String,Object> deleteByYearMonthRecord(HttpServletRequest request, HttpServletResponse response, String yearMonthRecord) throws Exception {
		EhrUser user = RequestUtil.getLoginUser(request);
		if(attendanceService.deleteByYearMonthRecord(user.getCompanyId(), yearMonthRecord) == 0){
			return MessageUtil.errorMessage("清除失败");
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "清空"+yearMonthRecord+"考勤表数据");
			return MessageUtil.successMessage("清除成功");
		}
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String,Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long attendId) throws Exception {
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrAttendance ehrAttendance = attendanceService.selectByPrimaryKey(attendId);
		if(attendanceService.deleteById(user.getCompanyId(), attendId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "删除id为"+attendId+"的"+ehrAttendance.getYearMonthRecord()+"的考勤表数据");
			return MessageUtil.successMessage("删除成功");
		}
	}

	@RequestMapping("/updateAttendance")
	@ResponseBody
	public Map<String,Object> updateAttendance(String name, String value, String pk) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value) || StringUtils.isEmpty(pk)) {
			return MessageUtil.errorMessage("数据为空");
		}
		if(attendanceService.updateAttendance(name, value, pk) == 0){
			return MessageUtil.errorMessage("更新失败");
		}else{
			return MessageUtil.successMessage("更新成功");
		}
	}
}
