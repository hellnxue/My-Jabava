package com.jabava.controller.salary;

import java.io.OutputStream;
import java.util.ArrayList;
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
import com.jabava.pojo.salary.EhrSalaryItem;
import com.jabava.pojo.salary.EhrSalaryTemplate;
import com.jabava.pojo.salary.EhrSalaryTemplateDetail;
import com.jabava.service.salary.ISalaryItemService;
import com.jabava.service.salary.ISalaryTemplateService;
import com.jabava.service.salary.ITaxRateService;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.excel.ExcelUtil;

@Controller
@RequestMapping("/salaryTemplate")
public class SalaryTemplateController {
	private static Logger log = Logger.getLogger(SalaryTemplateController.class);
	
	@Autowired
	private ISalaryTemplateService salaryTemplateService;
	
	@Autowired
	private ISalaryItemService salaryItemService;
	
	@Autowired
	private ITaxRateService taxRateService;
	
	@RequestMapping("toListSalaryTemplate")
	public String toListSalaryTemplate(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		request.setAttribute("taxRateList", taxRateService.listTaxRate(user.getCompanyId()));
		return "salary/listTemplate";
	}
	
	@RequestMapping("listSalaryTemplate")
	@ResponseBody
	public Page<EhrSalaryTemplate> listSalaryTemplate(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		Page<EhrSalaryTemplate> page = new Page<EhrSalaryTemplate>(0,Constants.MAX_RECORD_SIZE);
		List<EhrSalaryTemplate> list = salaryTemplateService.listSalaryTemplate(user.getCompanyId());
		page.setData(list);
		return page;
	}
	
	@RequestMapping("listSalaryTemplatePage")
	@ResponseBody
	public Page<EhrSalaryTemplate> listSalaryTemplatePage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		if(!StringUtils.isEmpty(search)){
			params.put("search", search);
		}
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrSalaryTemplate> page = new Page<EhrSalaryTemplate>(start,length);
		params.put("page", page);
		List<EhrSalaryTemplate> list = salaryTemplateService.listSalaryTemplatePage(params);
		
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddSalaryTemplate")
	public String toAddSalaryTemplate(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//如果salaryTemplateId不为空，则读取并添加到request
		String salaryTemplateId = request.getParameter("salaryTemplateId");
		if(!StringUtils.isEmpty(salaryTemplateId)){
			EhrSalaryTemplate salaryTemplate = salaryTemplateService.selectByPrimaryKey(Long.valueOf(salaryTemplateId));
			request.setAttribute("salaryTemplate", salaryTemplate);
		}

		request.setAttribute("taxRateList", taxRateService.listTaxRate(user.getCompanyId()));
		return "salary/addTemplate";
	}
	
	@RequestMapping("editSalaryTemplate")
	@ResponseBody
	public Map<String,Object> editSalaryTemplate(HttpServletRequest request, HttpServletResponse response, EhrSalaryTemplate salaryTemplate){
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrSalaryTemplate exist = salaryTemplateService.selectByName(user.getCompanyId(), salaryTemplate.getTemplateName());
		if(exist != null){
			if(salaryTemplate.getSalaryTemplateId() == null){
				return MessageUtil.errorMessage("名称重复");
			}else if(exist.getSalaryTemplateId() != salaryTemplate.getSalaryTemplateId()){
				return MessageUtil.errorMessage("名称重复");
			}
		}
		
		//String ids = request.getParameter("ids");
		if(salaryTemplateService.saveOrUpdate(salaryTemplate, user, request.getParameterValues("itemId[]")) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("salaryTemplateId", salaryTemplate.getSalaryTemplateId());
			return result;
		}
	}
	
	@RequestMapping("deleteSalaryTemplate")
	@ResponseBody
	public Map<String,Object> deleteSalaryTemplate(HttpServletRequest request, HttpServletResponse response, Long id){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryTemplateService.deleteById(user.getCompanyId(), id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("listSalaryItem")
	@ResponseBody
	public Page<EhrSalaryItem> listSalaryItem(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		String salaryTemplateId = request.getParameter("salaryTemplateId");
		Page<EhrSalaryItem> page = new Page<EhrSalaryItem>(0,Constants.MAX_RECORD_SIZE);
		if(StringUtils.isEmpty(salaryTemplateId)){
			return page;
		}
		
		List<EhrSalaryTemplateDetail> detailList = salaryTemplateService.loadDetailList(Long.valueOf(salaryTemplateId));
		Map<Long,Object> itemMap = new HashMap<Long,Object>();
		for(EhrSalaryTemplateDetail detail : detailList){
			itemMap.put(detail.getSalaryItemId(), null);
		}
		
		List<EhrSalaryItem> list = salaryItemService.listSalaryItem(user.getCompanyId());
		for(EhrSalaryItem item : list){
			if(!StringUtils.isEmpty(item.getChangeTableName())){
				//系统变动表设置名称
				if(SalaryEnum.SystemChangeTable.EhrAttendance.getTableName().equals(item.getChangeTableName())){
					item.setSalaryChangeDefName(SalaryEnum.SystemChangeTable.EhrAttendance.getDisplayName());
				}else if(SalaryEnum.SystemChangeTable.EhrSocialInsurance.getTableName().equals(item.getChangeTableName())){
					item.setSalaryChangeDefName(SalaryEnum.SystemChangeTable.EhrSocialInsurance.getDisplayName());
				}
			}
			
			if(itemMap.containsKey(item.getSalaryItemId())){
				item.setInTemplate(1);
			}else{
				item.setInTemplate(0);
			}
		}
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("exportSalaryTemplate")
	public void exportSalaryTemplate(HttpServletRequest request, HttpServletResponse response, Long salaryTemplateId) {
		EhrUser user = RequestUtil.getLoginUser(request);
		
		EhrSalaryTemplate salaryTemplate = salaryTemplateService.selectByPrimaryKey(salaryTemplateId);
		if(salaryTemplate == null || salaryTemplate.getCompanyId() != user.getCompanyId()){
			log.error("没有权限操作此定义：" + salaryTemplateId);
			return ;
		}
		
		Map<String,Object> datas = new HashMap<String,Object>();
		
		List<Map<String,Object>> templateList = new ArrayList<Map<String,Object>>();
		
		List<EhrSalaryTemplateDetail> detailList = salaryTemplateService.loadDetailList(Long.valueOf(salaryTemplateId));
		Map<Long,Object> itemMap = new HashMap<Long,Object>();
		for(EhrSalaryTemplateDetail detail : detailList){
			itemMap.put(detail.getSalaryItemId(), null);
		}
		
		List<EhrSalaryItem> list = salaryItemService.listSalaryItem(user.getCompanyId());
		for(EhrSalaryItem item : list){
			if(itemMap.containsKey(item.getSalaryItemId())){
				Map<String,Object> template = new HashMap<String,Object>();
				template.put("displayName", item.getSalaryItemName());
				templateList.add(template);
			}
		}
		
		datas.put("templateList", templateList);
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=" + new String("salaryTemplate.xlsx"));
		
		try {
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "salaryTemplate.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
