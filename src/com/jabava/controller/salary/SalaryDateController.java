package com.jabava.controller.salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryDate;
import com.jabava.service.salary.ISalaryDateService;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/salaryDate")
public class SalaryDateController {
	private static Logger log = Logger.getLogger(SalaryDateController.class);
	
	@Autowired
	private ISalaryDateService salaryDateService;
	
	@RequestMapping("toListSalaryDate")
	public String toListSalaryDate(HttpServletRequest request, HttpServletResponse response){
		return "salary/listPayDayChange";
	}
	
	@RequestMapping("listSalaryDatePage")
	@ResponseBody
	public Page<EhrSalaryDate> listSalaryDatePage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		if(!StringUtils.isEmpty(search)){
			params.put("search", search);
		}
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrSalaryDate> page = new Page<EhrSalaryDate>(start,length);
		params.put("page", page);
		List<EhrSalaryDate> list = salaryDateService.listSalaryDatePage(params);
		
		page.setData(list);
		return page;
	}
	
	@RequestMapping("editSalaryDate")
	@ResponseBody
	public Map<String,Object> editSalaryDate(HttpServletRequest request, HttpServletResponse response, EhrSalaryDate salaryDate){
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrSalaryDate exist = salaryDateService.selectByChangeDate(user.getCompanyId(), salaryDate.getChangeDate());
		if(exist != null){
			if(salaryDate.getSalaryDateId() == null){
				return MessageUtil.errorMessage("日期重复");
			}else if(exist.getSalaryDateId() != salaryDate.getSalaryDateId()){
				return MessageUtil.errorMessage("日期重复");
			}
		}
		
		if(salaryDateService.saveOrUpdate(salaryDate, user) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			return MessageUtil.successMessage("操作成功");
		}
	}
	
	@RequestMapping("deleteSalaryDate")
	@ResponseBody
	public Map<String,Object> deleteSalaryDate(HttpServletRequest request, HttpServletResponse response, Long id){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryDateService.deleteById(user.getCompanyId(), id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
}
