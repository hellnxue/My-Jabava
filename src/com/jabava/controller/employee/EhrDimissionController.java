package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrDimissionService;
import com.jabava.utils.RequestUtil;
/**
 * 离职管理
 *
 * @version $Id: EhrTurnoverController.java, 
 * v 0.1 2016年3月25日 上午10:46:54 
 * <pre>
 * @author steven.chen
 * @date 2016年3月25日 上午10:46:54 
 * </pre> 
 */
@Controller
@RequestMapping("employee")
public class EhrDimissionController {
	
	@Autowired
	private EhrDimissionService ehrDimissionService;
	
	@RequestMapping("/turnover")
	public String jumpPage(Long personId,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("personId", personId);
		return "employees/turnover_management";
	}
	
	/**
	 * 离职管理
	 * @param personId
	 * @return
	 */
	@RequestMapping("/dimissionInfo")
	@ResponseBody
	public Map<String, Object> dimissionInfo(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map.put("list", ehrDimissionService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("addLeft")
	@ResponseBody
	public Map<String, Object> addLeft(EhrDimission left, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
		//SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
		//	Date leftDate1 = (left.getLeftDate1() != null && !"".equals(left.getLeftDate1())) ? df.parse(left.getLeftDate1()) : null;
		//	Date salarySettleDate1 = (left.getSalarySettleDate1() != null && !"".equals(left.getSalarySettleDate1())) ? df.parse(left.getSalarySettleDate1()) : null;
		//.setLeftDate(leftDate1);
		//	left.setSalarySettleDate(salarySettleDate1); 
			left.setCreateDate(new Date());
			left.setCreateUserId(user.getUserId());
			left.setCreateUserName(user.getUserName());
			left.setLastModifyUserId(user.getUserId());
			left.setLastModifyUserName(user.getUserName());
			left.setCreateDate(new Date());
			map = ehrDimissionService.addLeft(left, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加离职信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateLeft")
	@ResponseBody
	public Map<String, Object> updateLeft(EhrDimission left, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {

			/*   SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
			Date leftDate1 = (left.getLeftDate1() != null && !"".equals(left.getLeftDate1())) ? df.parse(left.getLeftDate1()) : null;
			Date salarySettleDate1 = (left.getSalarySettleDate1() != null && !"".equals(left.getSalarySettleDate1())) ? df.parse(left.getSalarySettleDate1()) : null;
			left.setLeftDate(leftDate1);
			left.setSalarySettleDate(salarySettleDate1);*/
			left.setLastModifyUserId(user.getUserId());
			left.setLastModifyUserName(user.getUserName());
			left.setCreateDate(new Date());
			map = ehrDimissionService.updateLeft(left, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改离职信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteLeft")
	@ResponseBody
	public Map<String, Object> deleteLeft(Long leftId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ehrDimissionService.deleteLeft(leftId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除离职信息失败！");
			e.printStackTrace();
		}
		return map;
	}
}
