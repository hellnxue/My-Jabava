package com.jabava.controller.salary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalary;
import com.jabava.pojo.salary.EhrSalaryDetail;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.service.salary.ISalaryService;
import com.jabava.service.salary.ISalaryTemplateService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.utils.Constants;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/salary")
public class SalaryController {
	private static Logger log = Logger.getLogger(SalaryController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private ISalaryTemplateService templateService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IEhrOrganizationService organizationService;
	
	@RequestMapping("toListSalary")
	public String toListSalary(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		request.setAttribute("templateList", templateService.listSalaryTemplate(user.getCompanyId()));
		EhrOrganization topOrg = organizationService.findTopOrganization(user.getCompanyId());
		request.setAttribute("orgList", organizationService.getChildren(topOrg.getParentId()));
		return "salary/listSalary";
	}
	
	@RequestMapping("listSalaryPage")
	@ResponseBody
	public Page<EhrSalary> listSalaryPage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		String jobNumber = request.getParameter("jobNumber");
		String employeeName = request.getParameter("employeeName");
		String status = request.getParameter("status");
		String orgId = request.getParameter("orgId");
		params.put("search", search);
		params.put("jobNumber", jobNumber);
		params.put("employeeName", employeeName);
		params.put("status", status);
		if(!StringUtils.isEmpty(orgId)){
			params.put("orgList", organizationService.getTreeByParent(Long.valueOf(orgId)));
		}
		
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrSalary> page = new Page<EhrSalary>(start,length);
		params.put("page", page);
		List<EhrSalary> list = salaryService.listSalaryPage(params);
		
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddSalary")
	public String toAddSalary(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//如果salaryId不为空，则读取并添加到request
		String salaryId = request.getParameter("salaryId");
		if(!StringUtils.isEmpty(salaryId)){
			EhrSalary salary = salaryService.selectById(user.getCompanyId(), Long.valueOf(salaryId));
			request.setAttribute("salary", salary);
		}
		
		request.setAttribute("templateList", templateService.listSalaryTemplate(user.getCompanyId()));
		return "salary/addSalary";
	}
	
	@RequestMapping("editSalary")
	@ResponseBody
	public Map<String,Object> editSalary(HttpServletRequest request, HttpServletResponse response, EhrSalary salary){
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrPerson person = employeeService.searchPersonByJobNumber(user.getCompanyId(), salary.getJobNumber());
		if(person == null){
			return MessageUtil.errorMessage("员工不存在");
		}
		EhrSalary exist = salaryService.selectByPersonAndUsage(user.getCompanyId(), person.getPersonId(), salary.getUsageFlag());
		if(exist != null){
			if(salary.getSalaryId() == null){
				return MessageUtil.errorMessage("员工用途组合重复");
			}else if(exist.getSalaryId() != salary.getSalaryId()){
				return MessageUtil.errorMessage("员工用途组合重复");
			}
		}else{
			salary.setStatus(1);	//默认在发
		}

		salary.setPersonId(person.getPersonId());
		if(salaryService.saveOrUpdate(salary, user) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("salaryId", salary.getSalaryId());
			return result;
		}
	}
	
	@RequestMapping("updateStatus")
	@ResponseBody
	public Map<String,Object> updateStatus(HttpServletRequest request, HttpServletResponse response, Long id, Integer status){
		if(id == null || status == null){
			return MessageUtil.errorMessage("数据为空");
		}
		
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrSalary salary = salaryService.selectById(user.getCompanyId(), id);
		if(salary == null){
			return MessageUtil.errorMessage("工资不存在");
		}

		salary.setStatus(status);
		if(salaryService.saveOrUpdate(salary, user) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("salaryId", salary.getSalaryId());
			return result;
		}
	}
	
	@RequestMapping("deleteSalary")
	@ResponseBody
	public Map<String,Object> deleteSalary(HttpServletRequest request, HttpServletResponse response, Long salaryId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryService.deleteById(user.getCompanyId(), salaryId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("listSalaryDetail")
	@ResponseBody
	public Page<EhrSalaryDetail> listSalaryDetail(HttpServletRequest request, HttpServletResponse response){
		//EhrUser user = RequestUtil.getLoginUser(request);
		String salaryId = request.getParameter("salaryId");
		Page<EhrSalaryDetail> page = new Page<EhrSalaryDetail>(0,Constants.MAX_RECORD_SIZE);
		if(!StringUtils.isEmpty(salaryId)){
			List<EhrSalaryDetail> list = salaryService.loadDetailList(Long.valueOf(salaryId));
			page.setData(list);
		}
		
		return page;
	}
	
	@RequestMapping("updateSalaryDetail")
	@ResponseBody
	public Map<String, Object> updateSalaryDetail(String name, String value, String pk) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value) || StringUtils.isEmpty(pk)) {
			return MessageUtil.errorMessage("数据为空");
		}
		if(salaryService.updateSalaryDetail(name, value, pk) == 0){
			return MessageUtil.errorMessage("更新失败");
		}else{
			return MessageUtil.successMessage("更新成功");
		}
	}
	
	@RequestMapping("adjustSalary")
	@ResponseBody
	public Map<String,Object> adjustSalary(HttpServletRequest request, HttpServletResponse response, Integer usageFlag){
		EhrUser user = RequestUtil.getLoginUser(request);
		String filePath = request.getParameter("filePath");
		
		List<Map<String,Object>> rowMessageList = new ArrayList<Map<String,Object>>();
		List<EhrSalary> salaryList = new ArrayList<EhrSalary>();
		String msg = this.readExcel(filePath, usageFlag, user, salaryList, rowMessageList);
		if(!StringUtils.isEmpty(msg)){
			Map<String,Object> result = MessageUtil.errorMessage(msg);
			if(!rowMessageList.isEmpty()){
				result.put("rowMessagge", rowMessageList);
			}
			return result;
		}
		
		if(salaryService.updateDetailList(salaryList) == 0){
			return MessageUtil.errorMessage("工资调整失败");
		}else{
			return MessageUtil.successMessage("工资调整成功");
		}
	}
	
	private String readExcel(String filePath, Integer usageFlag, EhrUser user, List<EhrSalary> salaryList, 
			List<Map<String,Object>> rowMessageList){
		XSSFWorkbook workbook = null;
		InputStream is = null;
		try {
			String path = JabavaPropertyCofigurer.getProperty("UPLOAD_PATH") + filePath;
			is = new FileInputStream(path);
			workbook = new XSSFWorkbook(is);
			return readSheet(workbook.getSheetAt(0), usageFlag, user, salaryList, rowMessageList);
		} catch (Exception e) {
			e.printStackTrace();
			return "读取Excel失败";
		}finally{
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String readSheet(XSSFSheet sheet, Integer usageFlag, EhrUser user, List<EhrSalary> salaryList, 
			List<Map<String,Object>> rowMessageList) throws Exception {
		XSSFRow titleRow = sheet.getRow(0);
		Cell jobNumberCell = titleRow.getCell(2);
		if(jobNumberCell == null || !"工号".equals(jobNumberCell.toString())){
			return "格式不正确，请参考模板填写数据";
		}
		
		int i = 1;
		while (true) {	//循环到工号列为空
			XSSFRow row = sheet.getRow(i);
			if(row == null){
				break;
			}
			
			i ++;
			
			jobNumberCell = row.getCell(2);
			if(jobNumberCell == null || "".equals(jobNumberCell.toString())){
				break;
			}
			
			//根据人员和用途查询工资
			EhrPerson person = employeeService.searchPersonByJobNumber(user.getCompanyId(),jobNumberCell.toString());
			if(person == null){
				rowMessageList.add(this.getRowMessage(i, 3, "员工不存在"));
				continue;
			}
			EhrSalary salary = salaryService.selectByPersonAndUsage(user.getCompanyId(), person.getPersonId(), usageFlag);
			if(salary == null){
				rowMessageList.add(this.getRowMessage(i, 3, "对应用途的员工工资不存在"));
				continue;
			}
			
			int j = 3;
			Map<String,EhrSalaryDetail> detailMap = salaryService.getDetailMap(salary.getSalaryId());
			while(true){	//循环到列值为空或生效日期或变更原因
				Cell cell = row.getCell(j);
				Cell titleCell = titleRow.getCell(j);
				j ++;
				
				if(titleCell == null || "".equals(titleCell.toString()) || 
						"生效日期".equals(titleCell.toString()) || "变更原因".equals(titleCell.toString())){
					break;
				}
				
				//根据工资及项目名称查找明细
				EhrSalaryDetail detail = detailMap.get(titleCell.toString());
				if(detail == null){
					rowMessageList.add(this.getRowMessage(i, j, "员工工资项目不存在"));
					continue;
				}
				
				//为对应工资项目设置金额
				if(cell == null || "".equals(cell.toString())){
					detail.setAmount(BigDecimal.ZERO);
				}else{
					detail.setAmount(new BigDecimal(cell.toString()));
				}
				
				//新增明细
				salary.addDetail(detail);
			}
			
			//新增工资
			if(salary.getDetailList() == null || salary.getDetailList().isEmpty()){
				rowMessageList.add(this.getRowMessage(i, 3, "未找到与工资模板对应的工资项目"));
			}else{
				salaryList.add(salary);
			}
		}
		
		if(salaryList.isEmpty()){
			return "没有需要调整的员工工资";
		}
		
		if(!rowMessageList.isEmpty()){
			return "工资调整数据错误";
		}
		
		return null;
	}
	
	private Map<String,Object> getRowMessage(int row, int cell, String msg){
		Map<String,Object> rowMessage = new HashMap<String,Object>();
		rowMessage.put("row", row);
		rowMessage.put("cell", cell);
		rowMessage.put("msg", msg);
		return rowMessage;
	}
}
