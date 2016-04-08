package com.jabava.controller.salary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;
import com.jabava.service.salary.ISalaryChangeDefService;
import com.jabava.utils.Constants;
import com.jabava.utils.FileUtil;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.SalaryHelper;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.excel.ExcelUtil;

@Controller
@RequestMapping("/salaryChangeDef")
public class SalaryChangeDefController {
	private static Logger log = Logger.getLogger(SalaryChangeDefController.class);
	
	@Autowired
	private ISalaryChangeDefService salaryChangeDefService;
	
	@RequestMapping("toListSalaryChangeDef")
	public String toListSalaryChangeDef(HttpServletRequest request, HttpServletResponse response){
		return "salary/listSalaryChangeDef";
	}
	
	@RequestMapping("listSalaryChangeDef")
	@ResponseBody
	public Page<EhrSalaryChangeDef> listSalaryChangeDef(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
				
		List<EhrSalaryChangeDef> list = salaryChangeDefService.listSalaryChangeDef(user.getCompanyId());
		Page<EhrSalaryChangeDef> page = new Page<EhrSalaryChangeDef>(0,Constants.MAX_RECORD_SIZE);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddSalaryChangeDef")
	public String toAddSalaryChangeDef(HttpServletRequest request, HttpServletResponse response){
		//如果salaryChangeDefId不为空，则读取并添加到request
		return "salary/addSalaryChangeDef";
	}
	
	@RequestMapping("addSalaryChangeDef")
	@ResponseBody
	public Map<String,Object> addSalaryChangeDef(HttpServletRequest request, HttpServletResponse response){
		//salaryChangeDefService
		
		return MessageUtil.successMessage("操作成功");
	}
	
	@RequestMapping("toViewSalaryChangeDef")
	public String toViewSalaryChangeDef(HttpServletRequest request, HttpServletResponse response, Long id){
		EhrSalaryChangeDef salaryChangeDef = salaryChangeDefService.selectByPrimaryKey(id);
		request.setAttribute("salaryChangeDef", salaryChangeDef);
		return "salary/viewSalaryChangeDef";
	}
	
	@RequestMapping("loadSalaryChangeDefItem")
	@ResponseBody
	public Page<EhrSalaryChangeDefItem> loadSalaryChangeDefItem(HttpServletRequest request, HttpServletResponse response, Long id){
		List<EhrSalaryChangeDefItem> itemList = null;
		if(SalaryEnum.SystemChangeTable.EhrAttendance.getId() == id){
			itemList = SalaryHelper.getAttendanceDefination();
		}else if(SalaryEnum.SystemChangeTable.EhrSocialInsurance.getId() == id){
			itemList = SalaryHelper.getSocialInsuranceDefination();
		}else{
			itemList = salaryChangeDefService.queryItemList(id);
		}
		Page<EhrSalaryChangeDefItem> page = new Page<EhrSalaryChangeDefItem>(0,Constants.MAX_RECORD_SIZE);
		page.setData(itemList);
		return page;
	}

	@RequestMapping("toUploadSalaryChangeDef")
	public String toUploadSalaryChangeDef(HttpServletRequest request, HttpServletResponse response){
		return "salary/uploadSalaryChangeDef";
	}
	
	@RequestMapping("uploadSalaryChangeDef")
	@ResponseBody
	public Map<String,Object> uploadSalaryChangeDef(HttpServletRequest request, HttpServletResponse response,
			EhrSalaryChangeDef changeDef){
		EhrUser user = RequestUtil.getLoginUser(request);
		String cover = request.getParameter("cover");
		String filePath = request.getParameter("filePath");
		changeDef.setCompanyId(user.getCompanyId());
		changeDef.setIsDeleted(0);
		
		//根据名称获取定义
		EhrSalaryChangeDef exist = salaryChangeDefService.selectByName(user.getCompanyId(), changeDef.getName());
		if(exist != null){
			changeDef.setSalaryChangeDefId(exist.getSalaryChangeDefId());
			
			//如果同名且不允许覆盖，则直接返回错误
			if(!"1".equals(cover)){
				return MessageUtil.errorMessage("名称已存在");
			}
		}
		
		//读取、验证Excel，并校验主键列
		String msg = this.readExcel(filePath, changeDef);
		if(!StringUtils.isEmpty(msg)){
			return MessageUtil.errorMessage(msg); 
		}
		
		//保存定义
		if(salaryChangeDefService.saveOrUpdate(changeDef) == 0){
			return MessageUtil.errorMessage("上传失败");
		}else{
			String path = JabavaPropertyCofigurer.getProperty("UPLOAD_PATH") + filePath;
			FileUtil.deleteFile(path);
			return MessageUtil.successMessage("上传成功");
		}
	}
	
	private String readExcel(String filePath, EhrSalaryChangeDef changeDef){
		XSSFWorkbook workbook = null;
		InputStream is = null;
		try {
			String path = JabavaPropertyCofigurer.getProperty("UPLOAD_PATH") + filePath;
			is = new FileInputStream(path);
			workbook = new XSSFWorkbook(is);
			return readSheet(workbook.getSheetAt(0), changeDef);
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

	private String readSheet(XSSFSheet sheet, EhrSalaryChangeDef changeDef) throws Exception {
		int columns = Integer.parseInt(JabavaPropertyCofigurer.getProperty("salary.change.def.columns"));
		Map<String, Object> map = new HashMap<String, Object>();
		XSSFRow row = sheet.getRow(0);	//只取第一行
		int i = 0;
		for (; i < columns; i++) {
			Cell cell = row.getCell(i);
			//不支持空列
			if(cell == null || "".equals(cell.toString())){
				break;
			}
			
			String value = cell.toString();
			if(map.containsKey(value)){
				return "列名重复：" + value;
			}
			
			//ObjectUtils.setValue(changeDef, "commonInfo" + (i + 1), value);
			
			EhrSalaryChangeDefItem item = new EhrSalaryChangeDefItem();
			item.setDisplayName(value);
			item.setColumnName("common_info" + (i + 1));
			if(value.equals(changeDef.getKeyInfo())){
				//默认主键列为字符类型
				item.setDataType(SalaryEnum.SalaryChangeDefItemType.Character.getValue());
			}else{
				//其它列为数值类型
				item.setDataType(SalaryEnum.SalaryChangeDefItemType.Number.getValue());
			}
			changeDef.addItem(item);
			
			map.put(value, null);
		}
		
		if(map.isEmpty()){
			return "第一列为空";
		}
		
		if(!map.containsKey(changeDef.getKeyInfo())){
			return "主键列名不存在";
		}

		return null;
	}
	
	@RequestMapping("deleteSalaryChangeDef")
	@ResponseBody
	public Map<String,Object> deleteSalaryChangeDef(HttpServletRequest request, HttpServletResponse response, Long id){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(salaryChangeDefService.deleteById(user.getCompanyId(), id) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("exportSalaryChangeDef")
	public void exportSalaryChangeDef(HttpServletRequest request, HttpServletResponse response, Long id) {
		EhrUser user = RequestUtil.getLoginUser(request);
		
		EhrSalaryChangeDef changeDef = salaryChangeDefService.selectByPrimaryKey(id);
		if(changeDef == null || changeDef.getCompanyId() != user.getCompanyId()){
			log.error("没有权限操作此定义：" + id);
			return ;
		}
		
		Map<String,Object> datas = new HashMap<String,Object>();
		
		Map<String,Object> def = new HashMap<String,Object>();
		for(EhrSalaryChangeDefItem item : salaryChangeDefService.queryItemList(id)){
			def.put(item.getColumnName(), item.getDisplayName());
		}
		datas.put("def", def);
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=" + new String("salaryChangeDef.xlsx"));
		
		try {
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "salaryChangeDef.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}