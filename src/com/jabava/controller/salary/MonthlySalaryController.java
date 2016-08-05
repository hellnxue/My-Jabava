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

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrMonthlySalary;
import com.jabava.service.salary.IMonthlySalaryService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/monthlySalary")
public class MonthlySalaryController {
	private static Logger log = Logger.getLogger(MonthlySalaryController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private IMonthlySalaryService monthlySalaryService;
	@Autowired
	private IEhrOrganizationService organizationService;
	@Autowired
	private IBaseDataService baseDataService;
	
	@RequestMapping("toListMonthlySalary")
	public String toListMonthlySalary(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//EhrOrganization topOrg = organizationService.findTopOrganization(user.getCompanyId());
		//List<EhrOrganization> orgList = organizationService.getChildren(topOrg.getOrganizationId());
		List<EhrOrganization> topList = organizationService.findByLevel(user.getCompanyId(), 0);
		List<EhrOrganization> orgList = organizationService.findByLevel(user.getCompanyId(), 1);
		if(topList != null && !topList.isEmpty()){
			orgList.add(0,topList.get(0));
		}
		request.setAttribute("orgList", orgList);
		//查询类型为4-工资用途的基础数据
		request.setAttribute("salaryTypeList", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_SALARY_TYPE));
		return "salary/listMonthlySalary";
	}
	
	@RequestMapping("listMonthlySalaryPage")
	@ResponseBody
	public Page<EhrMonthlySalary> listMonthlySalaryPage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		String monthly = request.getParameter("monthly");
		String usageFlag = request.getParameter("usageFlag");
		String lastFlag = request.getParameter("lastFlag");
		String organizationId = request.getParameter("organizationId");
		params.put("search", search);
		params.put("monthly", monthly);
		params.put("usageFlag", usageFlag);
		//默认只显示有效
		params.put("lastFlag", StringUtils.isEmpty(lastFlag) ? "1" : lastFlag);
		params.put("organizationId", organizationId);

		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrMonthlySalary> page = new Page<EhrMonthlySalary>(start,length);
		params.put("page", page);
		List<EhrMonthlySalary> list = monthlySalaryService.listMonthlySalaryPage(params);
		
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toGenerateMonthlySalary")
	public String toGenerateMonthlySalary(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//EhrOrganization topOrg = organizationService.findTopOrganization(user.getCompanyId());
		//List<EhrOrganization> orgList = organizationService.getChildren(topOrg.getOrganizationId());
		List<EhrOrganization> topList = organizationService.findByLevel(user.getCompanyId(), 0);
		List<EhrOrganization> orgList = organizationService.findByLevel(user.getCompanyId(), 1);
		if(topList != null && !topList.isEmpty()){
			orgList.add(0,topList.get(0));
		}
		request.setAttribute("orgList", orgList);
		//查询类型为4-工资用途的基础数据
		request.setAttribute("salaryTypeList", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_SALARY_TYPE));
		return "salary/generateMonthlySalary";
	}
	
	@RequestMapping("generateMonthlySalary")
	@ResponseBody
	public Map<String,Object> generateMonthlySalary(HttpServletRequest request, HttpServletResponse response,
			EhrMonthlySalary monthlySalary){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalary == null || StringUtils.isEmpty(monthlySalary.getMonthly()) || monthlySalary.getUsageFlag() == null){
			return MessageUtil.errorMessage("数据为空");
		}
		
		//String monthly, Integer usageFlag, Long organizationId
		try {
			List<String> msgList = monthlySalaryService.generateMonthlySalary(monthlySalary, user);
			if(msgList == null || msgList.isEmpty()){
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Salary, "生成一个新的数据");
				return MessageUtil.successMessage("生成成功");
			}else{
				Map<String,Object> result = MessageUtil.errorMessage("生成失败");
				result.put("messageList", msgList);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("payoff")
	@ResponseBody
	public Map<String,Object> payoff(HttpServletRequest request, HttpServletResponse response, Long monthlySalaryId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalaryId == null){
			return MessageUtil.errorMessage("数据为空");
		}
		
		EhrMonthlySalary monthlySalary = monthlySalaryService.selectByPrimaryKey(monthlySalaryId);
		if(monthlySalary == null){
			return MessageUtil.errorMessage("月度薪资不存在为空");
		}
		if(!monthlySalary.getCompanyId().equals(user.getCompanyId())){
			return MessageUtil.errorMessage("没有数据权限操作");
		}
		
		return monthlySalaryService.payoff(user, monthlySalary);
	}
	
	@RequestMapping("viewMonthlySalary")
	public String viewMonthlySalary(HttpServletRequest request, HttpServletResponse response, Long monthlySalaryId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalaryId == null){
			return null;
		}
		
		EhrMonthlySalary monthlySalary = monthlySalaryService.selectByPrimaryKey(monthlySalaryId);
		request.setAttribute("monthlySalary", monthlySalary);
		
		//查询类型为4-工资用途的基础数据
		request.setAttribute("salaryTypeList", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_SALARY_TYPE));
		return "salary/viewMonthlySalary";
	}
	
	@RequestMapping("loadMonthlySalaryPersonHeader")
	@ResponseBody
	public List<Map<String,Object>> loadMonthlySalaryPersonHeader(HttpServletRequest request, HttpServletResponse response, Long monthlySalaryId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalaryId == null){
			return null;
		}
		
		return monthlySalaryService.loadMonthlySalaryPersonHeader(monthlySalaryId);
	}
	
	@RequestMapping("loadMonthlySalaryPersonData")
	@ResponseBody
	public Page<Map<String,Object>> loadMonthlySalaryPersonData(HttpServletRequest request, HttpServletResponse response, 
			Long monthlySalaryId, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalaryId == null){
			return null;
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(start, length);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("page", page);
		params.put("monthlySalaryId", monthlySalaryId);
		List<Map<String,Object>> list = monthlySalaryService.loadMonthlySalaryPersonPage(params);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("exportMonthlySalaryDetail")
	public void exportMonthlySalaryDetail(HttpServletRequest request, HttpServletResponse response, Long salaryTemplateId) {
		EhrUser user = RequestUtil.getLoginUser(request);
		
		//自定义报表功能实现后补充
		
		//MonthlySalaryService-->detailExport
		
//		EhrSalaryTemplate salaryTemplate = salaryTemplateService.selectByPrimaryKey(salaryTemplateId);
//		if(salaryTemplate == null || !salaryTemplate.getCompanyId().equals(user.getCompanyId())){
//			log.error("没有权限操作此定义：" + salaryTemplateId);
//			return ;
//		}
//		
//		Map<String,Object> datas = new HashMap<String,Object>();
//		
//		List<Map<String,Object>> templateList = new ArrayList<Map<String,Object>>();
//		
//		List<EhrSalaryTemplateDetail> detailList = salaryTemplateService.loadDetailList(Long.valueOf(salaryTemplateId));
//		Map<Long,Object> itemMap = new HashMap<Long,Object>();
//		for(EhrSalaryTemplateDetail detail : detailList){
//			itemMap.put(detail.getSalaryItemId(), null);
//		}
//		
//		List<EhrSalaryItem> list = salaryItemService.listSalaryItem(user.getCompanyId());
//		for(EhrSalaryItem item : list){	//中间项、变动项或不在模板中的项目不导出
//			if(item.getIsTransition()){
//				continue;
//			}
//			
////			if((item.getSalaryChangeDefId() != null || !StringUtils.isEmpty(item.getChangeTableName())) 
////					&& !StringUtils.isEmpty(item.getChangeFormula())){
//			if(!StringUtils.isEmpty(item.getChangeFormula())){	//有公式则不导出
//				continue;
//			}
//			
//			if(!itemMap.containsKey(item.getSalaryItemId())){
//				continue;
//			}
//			
//			Map<String,Object> template = new HashMap<String,Object>();
//			template.put("displayName", item.getSalaryItemName());
//			templateList.add(template);
//		}
//		
//		datas.put("templateList", templateList);
//		
//		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
//		response.setHeader("Content-Disposition","attachment; filename=" + new String("salaryTemplate.xlsx"));
//		
//		try {
//			OutputStream out = response.getOutputStream();
//			ExcelUtil.write(datas, "salaryTemplate.xlsx", out);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@RequestMapping("sendSalarySlip")
	@ResponseBody
	public Map<String,Object> sendSalarySlip(HttpServletRequest request, HttpServletResponse response, Long monthlySalaryId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(monthlySalaryId == null){
			return MessageUtil.errorMessage("请求数据为空");
		}
		
		EhrMonthlySalary monthlySalary = monthlySalaryService.selectByPrimaryKey(monthlySalaryId);
		if(!user.getCompanyId().equals(monthlySalary.getCompanyId())){
			return MessageUtil.errorMessage("没有数据操作权限");
		}
		
		String personIds = request.getParameter("personIds");
		
		try {
			return monthlySalaryService.sendSalarySlip(monthlySalary, personIds);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.toString());
		}
	}
}
