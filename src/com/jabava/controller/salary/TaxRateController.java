package com.jabava.controller.salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrTaxLevel;
import com.jabava.pojo.salary.EhrTaxRate;
import com.jabava.service.salary.ISalaryTemplateService;
import com.jabava.service.salary.ITaxRateService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/taxRate")
public class TaxRateController {
	private static Logger log = Logger.getLogger(TaxRateController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Autowired
	private ITaxRateService taxRateService;
	@Autowired
	private ISalaryTemplateService salaryTemplateService;
	
	@RequestMapping("toListTaxRate")
	public String toListTaxRate(HttpServletRequest request, HttpServletResponse response){
		return "salary/listTaxRate";
	}
	
	@RequestMapping("listTaxRatePage")
	@ResponseBody
	public Page<EhrTaxRate> listTaxRatePage(HttpServletRequest request, HttpServletResponse response, int start, int length) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		if(!StringUtils.isEmpty(search)){
			params.put("search", search);
		}
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrTaxRate> page = new Page<EhrTaxRate>(start,length);
		params.put("page", page);
		List<EhrTaxRate> list = taxRateService.listTaxRatePage(params);
		//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.Salary, "查看薪酬税率设置");
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddTaxRate")
	public String toAddTaxRate(HttpServletRequest request, HttpServletResponse response){
		//EhrUser user = RequestUtil.getLoginUser(request);
		//如果taxRateId不为空，则读取并添加到request
		String taxRateId = request.getParameter("taxRateId");
		if(!StringUtils.isEmpty(taxRateId)){
			EhrTaxRate taxRate = taxRateService.selectByPrimaryKey(Long.valueOf(taxRateId));
			request.setAttribute("taxRate", taxRate);
		}

		return "salary/addTaxRate";
	}
	
	@RequestMapping("editTaxRate")
	@ResponseBody
	public Map<String,Object> editTaxRate(HttpServletRequest request, HttpServletResponse response, EhrTaxRate taxRate) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrTaxRate exist = taxRateService.selectByName(user.getCompanyId(), taxRate.getTaxRateName());
		if(exist != null){
			if(taxRate.getTaxRateId() == null){
				return MessageUtil.errorMessage("名称重复");
			}else if(!exist.getTaxRateId().equals(taxRate.getTaxRateId())){
				return MessageUtil.errorMessage("名称重复");
			}
		}
		boolean flag = taxRate.getTaxRateId()==null;
		if(taxRateService.saveOrUpdate(taxRate, user) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			if(flag){
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Salary, "添加了一个名称为"+taxRate.getTaxRateName()+"薪酬税率设置");
			}else{
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Salary, "修改了一个名称为"+taxRate.getTaxRateName()+"薪酬税率设置");
			}
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("taxRateId", taxRate.getTaxRateId());
			return result;
		}
	}
	
	@RequestMapping("deleteTaxRate")
	@ResponseBody
	public Map<String,Object> deleteTaxRate(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		//ehr_salary_detail
		if(salaryTemplateService.hasReferencedTaxRate(id)){
			return MessageUtil.errorMessage("工资模板引用了该税率");
		}
		
		EhrTaxRate ehrTaxRate = taxRateService.selectByPrimaryKey(id);
		if(taxRateService.deleteById(user.getCompanyId(), id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			Map<String,Object> map = MessageUtil.successMessage("删除成功");
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "删除了一条id为"+id+"税率名为"+ehrTaxRate.getTaxRateName()+"薪酬税率");
			return map;
		}
	}
	
	@RequestMapping("listTaxLevel")
	@ResponseBody
	public Page<EhrTaxLevel> listTaxLevel(HttpServletRequest request, HttpServletResponse response){
		//EhrUser user = RequestUtil.getLoginUser(request);
		String taxRateId = request.getParameter("taxRateId");
		Page<EhrTaxLevel> page = new Page<EhrTaxLevel>(0,Constants.MAX_RECORD_SIZE);
		if(!StringUtils.isEmpty(taxRateId)){
			List<EhrTaxLevel> list = taxRateService.listTaxLevel(Long.valueOf(taxRateId));
			page.setData(list);
		}
		
		return page;
	}
	
	@RequestMapping("saveTaxLevel")
	@ResponseBody
	public Map<String,Object> saveTaxLevel(HttpServletRequest request, HttpServletResponse response, EhrTaxLevel taxLevel) throws Exception{
		if(taxRateService.saveOrUpdateLevel(taxLevel) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Update, SystemEnum.Module.Salary, "修改了一条id为"+taxLevel.getTaxLevelId()+"薪酬税率");

			return MessageUtil.successMessage("操作成功");
		}
	}
	

	@RequestMapping("loadTaxLevel")
	@ResponseBody
	public EhrTaxLevel loadTaxLevel(HttpServletRequest request, HttpServletResponse response, Long id){
		if(id != null){
			return taxRateService.loadTaxLevel(id);
		}else{
			return null;
		}
	}
	
	@RequestMapping("deleteTaxLevel")
	@ResponseBody
	public Map<String,Object> deleteTaxLevel(HttpServletRequest request, HttpServletResponse response, Long id){
		if(taxRateService.deleteTaxLevel(id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
}
