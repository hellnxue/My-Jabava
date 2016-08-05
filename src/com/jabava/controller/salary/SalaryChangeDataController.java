package com.jabava.controller.salary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.jabava.pojo.manage.EhrAttendance;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryChangeData;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;
import com.jabava.service.employee.EhrAttendanceService;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.service.salary.ISalaryChangeDataService;
import com.jabava.service.salary.ISalaryChangeDefService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.ObjectUtils;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.excel.ExcelUtil;

@Controller
@RequestMapping("/salaryChangeData")
public class SalaryChangeDataController {
	private static Logger log = Logger.getLogger(SalaryChangeDataController.class);
	
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private ISalaryChangeDataService salaryChangeDataService;
	@Autowired
	private ISalaryChangeDefService salaryChangeDefService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private EhrAttendanceService attendanceService;
	
	@RequestMapping("toListSalaryChangeData")
	public String toListSalaryChangeData(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		List<EhrSalaryChangeDef> defList = salaryChangeDefService.listSalaryChangeDef(user.getCompanyId());
		EhrSalaryChangeDef def = new EhrSalaryChangeDef();
		def.setSalaryChangeDefId(SalaryEnum.SystemChangeTable.Attendance.getId());
		def.setName(SalaryEnum.SystemChangeTable.Attendance.getDisplayName());
		defList.add(0, def);
		request.setAttribute("salaryChangeDefList", defList);
		return "salary/listSalaryChange";
	}
	
	@RequestMapping("loadHeaderList")
	@ResponseBody
	public List<Map<String,Object>> loadHeaderList(HttpServletRequest request, HttpServletResponse response,
			Long salaryChangeDefId, String monthly){
		EhrUser user = RequestUtil.getLoginUser(request);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//if(salaryChangeDefId == null || StringUtils.isEmpty(monthly)){
		if(salaryChangeDefId == null){
			return result;
		}
		
		EhrSalaryChangeDef def = salaryChangeDefService.selectById(user.getCompanyId(), salaryChangeDefId, true);
		if(def == null){
			return result;
		}
		
		//主键列使用固定字段
		result.add(this.getFieldHeader("job_number","工号"));
		result.add(this.getFieldHeader("cert_id","身份证"));
		for(EhrSalaryChangeDefItem item : def.getItemList()){
			if(item.getDisplayName().equals(def.getKeyInfo())){
				continue;
			}
			result.add(this.getFieldHeader(item.getColumnName(), item.getDisplayName()));
		}
		
		return result;
	}
	
	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}
	
	@RequestMapping("listSalaryChangeDataPage")
	@ResponseBody
	public Page<Map<String,Object>> listSalaryChangeDataPage(HttpServletRequest request, HttpServletResponse response, 
			int start, int length) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		String salaryChangeDefId = request.getParameter("salaryChangeDefId");
		String monthly = request.getParameter("monthly");
		if(salaryChangeDefId == null){
			throw new Exception("请求数据为空");
		}
		EhrSalaryChangeDef def = salaryChangeDefService.selectById(user.getCompanyId(), Long.valueOf(salaryChangeDefId), false);
		if(def == null){
			throw new Exception("外部数据定义不存在");
		}
		if(def.getIsMonthly() == 1 && StringUtils.isEmpty(monthly)){
			throw new Exception("月份不能为空");
		}
		
		params.put("search", search);
		params.put("salaryChangeDefId", salaryChangeDefId);
		params.put("monthly", def.getIsMonthly() == 1 ? monthly : null);
		
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(start,length);
		params.put("page", page);
		List<Map<String,Object>> list = salaryChangeDataService.listSalaryChangeDataPage(params);
		//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.Salary, "查看外部数据导入");
		page.setData(list);
		return page;
	}
	
	@RequestMapping("deleteSalaryChangeData")
	@ResponseBody
	public Map<String,Object> deleteSalary(HttpServletRequest request, HttpServletResponse response, Long salaryChangeDataId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryChangeDataService.deleteById(user.getCompanyId(), salaryChangeDataId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "删除变动表里id为"+salaryChangeDataId+"的数据");
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("updateSalaryChangeData")
	@ResponseBody
	public Map<String, Object> updateSalaryDetail(String name, String value, String pk) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value) || StringUtils.isEmpty(pk)) {
			return MessageUtil.errorMessage("数据为空");
		}
		if(salaryChangeDataService.updateSalaryChangeData(name, value, pk) == 0){
			return MessageUtil.errorMessage("更新失败");
		}else{
			//sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Update, SystemEnum.Module.Salary, "修改名字为"+name+"的数据");

			return MessageUtil.successMessage("更新成功");
		}
	}
	
	@RequestMapping("clearSalaryChangeData")
	@ResponseBody
	public Map<String,Object> clearSalaryChangeData(HttpServletRequest request, HttpServletResponse response, 
			Long salaryChangeDefId, String monthly) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryChangeDefId == null){
			return MessageUtil.errorMessage("请求数据为空");
		}
		EhrSalaryChangeDef def = salaryChangeDefService.selectById(user.getCompanyId(), Long.valueOf(salaryChangeDefId), false);
		if(def == null){
			return MessageUtil.errorMessage("外部数据定义不存在");
		}
		if(def.getIsMonthly() == 1 && StringUtils.isEmpty(monthly)){
			return MessageUtil.errorMessage("月份不能为空");
		}
		
		if(salaryChangeDataService.removeSalaryChangeData(user.getCompanyId(), monthly, def) == 0){
			return MessageUtil.errorMessage("清除失败");
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Salary, "清空"+def.getSalaryChangeDefId()+"--"+monthly+"数据");
			return MessageUtil.successMessage("清除成功");
		}
	}
	
	@RequestMapping("uploadSalaryChangeData")
	@ResponseBody
	public Map<String,Object> uploadSalaryChangeData(HttpServletRequest request, HttpServletResponse response, 
			Long salaryChangeDefId, String monthly) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		String filePath = request.getParameter("filePath");
		
		EhrSalaryChangeDef def = null;
		if(salaryChangeDefId != null){
			def = salaryChangeDefService.selectById(user.getCompanyId(), salaryChangeDefId, true);
		}
		if(def == null){
			return MessageUtil.errorMessage("工资变动表不存在");
		}
		if(def.getIsMonthly() == 1 && StringUtils.isEmpty(monthly)){
			return MessageUtil.errorMessage("月份不能为空");
		}
		
		List<Map<String,Object>> rowMessageList = new ArrayList<Map<String,Object>>();
		List<Object> dataList = new ArrayList<Object>();
		String msg = this.readExcel(filePath, def, monthly, user, dataList, rowMessageList);
		if(!StringUtils.isEmpty(msg)){
			Map<String,Object> result = MessageUtil.errorMessage(msg);
			if(!rowMessageList.isEmpty()){
				result.put("rowMessagge", rowMessageList);
			}
			return result;
		}
		
		int flag = 0;
		if(salaryChangeDefId.equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
			List<EhrAttendance> attendanceList = new ArrayList<EhrAttendance>();
			for(Object data : dataList){
				attendanceList.add((EhrAttendance)data);
			}
			flag = attendanceService.insertAttendanceList(user.getCompanyId(), attendanceList);
		}else{
			List<EhrSalaryChangeData> salaryChangeDataList = new ArrayList<EhrSalaryChangeData>();
			for(Object data : dataList){
				salaryChangeDataList.add((EhrSalaryChangeData)data);
			}
			flag = salaryChangeDataService.saveOrUpdateSalaryChangeDataList(user.getCompanyId(), monthly, def, salaryChangeDataList);
		}
		
		if(flag == 0){
			return MessageUtil.errorMessage("上传工资变动失败");
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Upload, SystemEnum.Module.Salary, "导入一个工资变动");
			return MessageUtil.successMessage("上传工资变动成功");
		}
	}
	
	private String readExcel(String filePath, EhrSalaryChangeDef def, String monthly, EhrUser user, 
			List<Object> salaryChangeDataList, List<Map<String,Object>> rowMessageList){
		XSSFWorkbook workbook = null;
		InputStream is = null;
		try {
			String path = JabavaPropertyCofigurer.getProperty("UPLOAD_PATH") + filePath;
			is = new FileInputStream(path);
			workbook = new XSSFWorkbook(is);
			return readSheet(workbook.getSheetAt(0), def, monthly, user, salaryChangeDataList, rowMessageList);
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

	private String readSheet(XSSFSheet sheet, EhrSalaryChangeDef def, String monthly, EhrUser user, 
			List<Object> dataList, List<Map<String,Object>> rowMessageList) throws Exception {
		XSSFRow titleRow = sheet.getRow(0);
		if(titleRow == null){
			return "格式不正确，请参考变动表填写数据";
		}
		
		Map<String,EhrSalaryChangeDefItem> defItemMap = new HashMap<String,EhrSalaryChangeDefItem>();
		for(EhrSalaryChangeDefItem item : def.getItemList()){
			defItemMap.put(item.getDisplayName(), item);
		}

		//检查标题行是否与变动表一致，找到主键列号
		int columns = Integer.parseInt(JabavaPropertyCofigurer.getProperty(Constants.SALARY_CHANGE_DEF_COLUMNS));
		int keyColumnNum = -1;
		Map<Integer,EhrSalaryChangeDefItem> itemMap = new HashMap<Integer,EhrSalaryChangeDefItem>();
		int maxColumns = 0;	//有效最大列数
		for(int i = 0; i < columns; i ++){
			Cell cell = titleRow.getCell(i);
			if(cell == null || "".equals(cell.toString())){
				maxColumns = i;
				break;
			}
			
			if(keyColumnNum == -1){	//找到主键列号
				if(cell.toString().equals(def.getKeyInfo())){
					keyColumnNum = i;
				}
			}
			
			//检查标题行是否与变动表一致
			if(!defItemMap.containsKey(cell.toString())){
				//rowMessageList.add(this.getRowMessage(1, (i + 1), "定义中不存在列" + cell.toString()));
			}else{
				//与Excel中列一致的定义明细
				itemMap.put(i, defItemMap.get(cell.toString()));
			}
		}
		if(keyColumnNum == -1){
			return "主键列不存在，请参考变动表填写数据";
		}
		if(!rowMessageList.isEmpty()){
			return "标题行格式错误";
		}
		
		int i = 1;
		while (true) {	//循环到空行或主键列为空
			XSSFRow row = sheet.getRow(i);
			if(row == null){
				break;
			}
			
			i ++;
			
			//EhrSalaryChangeData data = new EhrSalaryChangeData();
			//data.setMonthly(monthly);
			//data.setSalaryChangeDefId(def.getSalaryChangeDefId());
			Object data = this.getDataObject(def,monthly);
			for(int j = 0; j < maxColumns; j ++){
				Cell cell = row.getCell(j);
				EhrSalaryChangeDefItem defItem = itemMap.get(j);
				if(defItem == null){	//过滤不在定义表中的列
					continue;
				}
				if(j == keyColumnNum){	//校验主键列，为空结束遍历
					if(cell == null || "".equals(cell.toString())){
						break;
					}
					
					EhrPerson person = null;
					if(def.getKeyType().equals(SalaryEnum.SalaryChangeDefKeyType.ID.getValue())){
						person = employeeService.searchPersonByCertId(user.getCompanyId(),cell.toString());
					}else{
						person = employeeService.searchPersonByJobNumber(user.getCompanyId(),cell.toString());
					}
					if(person == null){
						rowMessageList.add(this.getRowMessage(i, (j + 1), "员工不存在"));
						continue;
					}
					
					if(def.getSalaryChangeDefId().equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
						((EhrAttendance)data).setPersonId(person.getPersonId());
					}else{
						ObjectUtils.setValue(data, "personId", person.getPersonId());
					}
				}
				
				//数据格式暂未验证
				
				//设置对应列的数据
				if(cell != null && !StringUtils.isEmpty(cell.toString())){
					if(def.getSalaryChangeDefId().equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
						((EhrAttendance)data).setFieldValue(defItem.getColumnName(), cell.toString());
					}else{
						ObjectUtils.setValue(data, ObjectUtils.formatBeanField(defItem.getColumnName()), cell.toString());
					}
				}
			}
			
			dataList.add(data);
		}
		
		if(dataList.isEmpty()){
			return "没有需要处理的工资变动数据";
		}
		
		if(!rowMessageList.isEmpty()){
			return "工资变动数据错误";
		}
		
		return null;
	}
	
	private Object getDataObject(EhrSalaryChangeDef def, String monthly){
		if(def.getSalaryChangeDefId().equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
			EhrAttendance data = new EhrAttendance();
			data.setYearMonthRecord(monthly.substring(0,4) + "-" + monthly.substring(4));
			return data;
		}else{
			EhrSalaryChangeData data = new EhrSalaryChangeData();
			if(def.getIsMonthly() == 1){
				data.setMonthly(monthly);
			}
			data.setSalaryChangeDefId(def.getSalaryChangeDefId());
			return data;
		}
	}
	
	private Map<String,Object> getRowMessage(int row, int cell, String msg){
		Map<String,Object> rowMessage = new HashMap<String,Object>();
		rowMessage.put("row", row);
		rowMessage.put("cell", cell);
		rowMessage.put("msg", msg);
		return rowMessage;
	}
	
	@RequestMapping("exportSalaryChangeData")
	public void exportSalaryChangeData(HttpServletRequest request, HttpServletResponse response, 
			Long salaryChangeDefId, String monthly) {
		EhrUser user = RequestUtil.getLoginUser(request);
		
		EhrSalaryChangeDef def = null;
		if(salaryChangeDefId != null){
			def = salaryChangeDefService.selectById(user.getCompanyId(), salaryChangeDefId, true);
		}
		if(def == null){
			log.error("没有指定的变动定义：" + salaryChangeDefId);
			return ;
		}
		if(def.getIsMonthly() == 1 && StringUtils.isEmpty(monthly)){
			log.error("没有指定的变动定义：" + salaryChangeDefId);
			return ;
		}
		
		Map<String,Object> datas = new HashMap<String,Object>();
		datas.put("defItemList", def.getItemList());
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		List<Map<String,Object>> changeDataList = null;
		if(salaryChangeDefId.equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
			params.put("yearMonthRecord", monthly.substring(0,4) + "-" + monthly.substring(4));
			changeDataList = attendanceService.listAttentance(params);
		}else{
			params.put("salaryChangeDefId", salaryChangeDefId);
			params.put("monthly", def.getIsMonthly() == 1 ? monthly : null);
			changeDataList = salaryChangeDataService.listSalaryChangeData(params);
		}
		List<List<Object>> salaryChangeDataList = new ArrayList<List<Object>>();
		for(Map<String,Object> data : changeDataList){
			List<Object> dataList = new ArrayList<Object>();
			for(EhrSalaryChangeDefItem item : def.getItemList()){
				dataList.add(data.get(item.getColumnName()));
			}
			salaryChangeDataList.add(dataList);
		}		
		datas.put("salaryChangeDataList", salaryChangeDataList);
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=" + new String("salaryChangeData.xlsx"));
		
		try {
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "salaryChangeData.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
