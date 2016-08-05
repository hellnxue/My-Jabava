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
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryItem;
import com.jabava.service.salary.ISalaryChangeDefService;
import com.jabava.service.salary.ISalaryItemService;
import com.jabava.service.salary.ISalaryService;
import com.jabava.service.salary.ISalaryTemplateService;
import com.jabava.service.salary.ITaxRateService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.SalaryHelper;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/salaryItem")
public class SalaryItemController {
	private static Logger log = Logger.getLogger(SalaryItemController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private ISalaryItemService salaryItemService;
	@Autowired
	private ISalaryChangeDefService salaryChangeDefService;
	@Autowired
	private ITaxRateService taxRateService;
	@Autowired
	private ISalaryService salaryService;
	@Autowired
	private ISalaryTemplateService salaryTemplateService;
	
	@RequestMapping("toListSalaryItem")
	public String toListSalaryItem(HttpServletRequest request, HttpServletResponse response){
		return "salary/listSalaryItem";
	}
	
	@RequestMapping("listSalaryItemPage")
	@ResponseBody
	public Page<EhrSalaryItem> listSalaryItemPage(HttpServletRequest request, HttpServletResponse response, int start, int length) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		if(!StringUtils.isEmpty(search)){
			params.put("search", search);
		}
		String salaryItemName = request.getParameter("salaryItemName");
		if(!StringUtils.isEmpty(salaryItemName)){
			params.put("salaryItemName", salaryItemName);
		}
		String calculateRule = request.getParameter("calculateRule");
		if(!StringUtils.isEmpty(calculateRule)){
			params.put("calculateRule", calculateRule);
		}
		String itemType = request.getParameter("itemType");
		if(!StringUtils.isEmpty(itemType)){
			params.put("itemType", itemType);
		}
		String isTransition = request.getParameter("isTransition");
		if(!StringUtils.isEmpty(isTransition)){
			params.put("isTransition", isTransition);
		}
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrSalaryItem> page = new Page<EhrSalaryItem>(start,length);
		params.put("page", page);
		List<EhrSalaryItem> list = salaryItemService.listSalaryItemPage(params);
		//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.Salary, "查询了公司id为"+user.getCompanyId()+"的薪酬项目设置");
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddSalaryItem")
	public String toAddSalaryItem(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//如果salaryItemId不为空，则读取并添加到request
		String salaryItemId = request.getParameter("salaryItemId");
		List<EhrSalaryItem> transitionItemList = null;
		if(!StringUtils.isEmpty(salaryItemId)){
			EhrSalaryItem salaryItem = salaryItemService.selectByPrimaryKey(Long.valueOf(salaryItemId));
			//设置虚拟ID
			if(SalaryEnum.SystemChangeTable.Attendance.getTableName().equals(salaryItem.getChangeTableName())){
				salaryItem.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.Attendance.getId());
			}else if(SalaryEnum.SystemChangeTable.SocialSecurity.getTableName().equals(salaryItem.getChangeTableName())){
				salaryItem.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.SocialSecurity.getId());
			}else if(SalaryEnum.SystemChangeTable.AccumulationFund.getTableName().equals(salaryItem.getChangeTableName())){
				salaryItem.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.AccumulationFund.getId());
			}
			request.setAttribute("salaryItem", salaryItem);
			transitionItemList = salaryItemService.listTransitionItem(user.getCompanyId(), Long.valueOf(salaryItemId));
		}else{
			transitionItemList = salaryItemService.listTransitionItem(user.getCompanyId(), null);
		}
		
		StringBuffer sb = new StringBuffer();
		for(EhrSalaryItem item : transitionItemList){
			sb.append("、").append(item.getSalaryItemName());
		}
		
		//中间项
		request.setAttribute("transitionItem", sb.length() == 0 ? "" : sb.toString().substring(1));
		//通用项
		request.setAttribute("commonItem", SalaryHelper.salaryConstantCommonToString());
		//员工项
		request.setAttribute("personItem", SalaryHelper.salaryConstantPersonToString());
		
		//系统变动表项
		EhrSalaryChangeDef attendanceDef = new EhrSalaryChangeDef();
		attendanceDef.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.Attendance.getId());
		attendanceDef.setName(SalaryEnum.SystemChangeTable.Attendance.getDisplayName());
		
		EhrSalaryChangeDef socialSecurityDef = new EhrSalaryChangeDef();
		socialSecurityDef.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.SocialSecurity.getId());
		socialSecurityDef.setName(SalaryEnum.SystemChangeTable.SocialSecurity.getDisplayName());
		
		EhrSalaryChangeDef accumulationFund = new EhrSalaryChangeDef();
		accumulationFund.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.AccumulationFund.getId());
		accumulationFund.setName(SalaryEnum.SystemChangeTable.AccumulationFund.getDisplayName());
		
		//自定义变动表项
		List<EhrSalaryChangeDef> changeDefList = salaryChangeDefService.listSalaryChangeDef(user.getCompanyId());
		changeDefList.add(0, attendanceDef);
		changeDefList.add(1, socialSecurityDef);
		changeDefList.add(2, accumulationFund);
		request.setAttribute("changeDefList", changeDefList);
		
		//税率
		request.setAttribute("taxRateList", taxRateService.listTaxRate(user.getCompanyId()));
		
		return "salary/addSalaryItem";
	}
	
	@RequestMapping("editSalaryItem")
	@ResponseBody
	public Map<String,Object> editSalaryItem(HttpServletRequest request, HttpServletResponse response, EhrSalaryItem salaryItem) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrSalaryItem exist = salaryItemService.selectByName(user.getCompanyId(), salaryItem.getSalaryItemName());
		if(exist != null){
			if(salaryItem.getSalaryItemId() == null){
				return MessageUtil.errorMessage("名称重复");
			}else if(!exist.getSalaryItemId().equals(salaryItem.getSalaryItemId())){
				return MessageUtil.errorMessage("名称重复");
			}
		}
		boolean flag = salaryItem.getSalaryItemId() == null;
		if(salaryItemService.saveOrUpdate(salaryItem, user) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			if(flag){
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Salary, "添加一条名为"+salaryItem.getSalaryItemName()+"工资项目");
			}else{
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Salary, "修改一条id为"+salaryItem.getSalaryItemId()+"工资项目");
			}
			return MessageUtil.successMessage("操作成功");
		}
	}
	
	@RequestMapping("deleteSalaryItem")
	@ResponseBody
	public Map<String,Object> deleteSalaryItem(HttpServletRequest request, HttpServletResponse response, 
			Long id) throws Exception {
		EhrUser user = RequestUtil.getLoginUser(request);
		//ehr_salary_detail
		if(salaryService.hasReferencedItem(id)){
			return MessageUtil.errorMessage("员工工资明细引用了该项目");
		}
		
		//ehr_salary_item_defination
		
		//ehr_salary_template_detail
		if(salaryTemplateService.hasReferencedItem(id)){
			return MessageUtil.errorMessage("工资模板明细引用了该项目");
		}
		EhrSalaryItem ehrSalaryItem = salaryItemService.selectByPrimaryKey(id);
		if(salaryItemService.deleteById(user.getCompanyId(), id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "删除了一条id为"+id+"项目名称为"+ehrSalaryItem.getSalaryItemName()+"工资项目");
			return MessageUtil.successMessage("删除成功");
		}
	}
}
