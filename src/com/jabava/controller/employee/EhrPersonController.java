package com.jabava.controller.employee;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonFileService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.excel.ExcelUtil;


@Controller
@RequestMapping("employee")
public class EhrPersonController {
	@Resource
	private EhrPersonService ehrPersonService;
	
	@Resource
	private IBaseDataService baseDataService;
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Resource
	private EhrPersonFileService personFileService;
	
	/**
	 * 进入花名册列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("employeeList")
	public String employeeList(HttpServletRequest request,HttpServletResponse response){
		
		return "employees/list_membership_roster";
	}
	
	/**
	 * 进入添加员工页面
	 * @return
	 */
	@RequestMapping("/to_addPerson")
	public String addPerson(){
		return "employees/list_staff_added";
	}
 	
	/**
	 * 进入完善信息页面
	 * @param personId
	 * @param request 
	 * @return
	 */
	@RequestMapping("/employeeInformation")
	public String employeeInformation(Long personId, HttpServletRequest request){
		request.setAttribute("personId", personId);
		return "employees/personal_information";
	}
	
	/**
	 * 花名册列表
	 * @param request
	 * @param data
	 * @param start
	 * @param length
	 * @return
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping("empdataTableSearch")
	@ResponseBody
	public Page<EhrPerson> dataTableSearch(HttpServletRequest request,String data, Integer start, Integer length){
		Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(data),Map.class);
		EhrUser user = RequestUtil.getLoginUser(request);
		Page<EhrPerson> page = new Page<>(start, length);
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		Long companyId = user.getCompanyId();
		map.put("companyId",companyId);
		String expireDateStart=map.get("expireDateStart").toString();
		String expireDateEnd=map.get("expireDateEnd").toString();
		String positiveDateStart=map.get("positiveDateStart").toString();
		String positiveDateEnd=map.get("positiveDateEnd").toString();
		String entryDateStart=map.get("entryDateStart").toString();
		String entryDateEnd=map.get("entryDateEnd").toString();
		List<EhrPerson> list = null;
		try {
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrPerson.getColumnName(order);
			SimpleDateFormat  df = new SimpleDateFormat("yyyy/MM/dd");
			Date entryDateStart1 = (entryDateStart != null && !"".equals(entryDateStart)) ? df.parse(entryDateStart) : null;
			Date entryDateEnd1 = (entryDateEnd != null && !"".equals(entryDateEnd)) ? df.parse(entryDateEnd) : null;
			Date positiveDateStart1 = (positiveDateStart != null && !"".equals(positiveDateStart)) ? df.parse(positiveDateStart) : null;
			Date positiveDateEnd1 = (positiveDateEnd != null && !"".equals(positiveDateEnd)) ? df.parse(positiveDateEnd) : null;
			Date expireDateStart1 = (expireDateStart != null && !"".equals(expireDateStart)) ? df.parse(expireDateStart) : null;
			Date expireDateEnd1 = (expireDateEnd != null && !"".equals(expireDateEnd)) ? df.parse(expireDateEnd) : null;
			map.put("entryDateStart", entryDateStart1);
			map.put("entryDateEnd", entryDateEnd1);
			map.put("positiveDateStart", positiveDateStart1);
			map.put("positiveDateEnd", positiveDateEnd1);
			map.put("expireDateStart", expireDateStart1);
			map.put("expireDateEnd", expireDateEnd1);
			list = ehrPersonService.searchPerson(map, user.getUserId(),start,length, search, order, according,isNumeric,page);
			page.setData(list);
			sysLogSercice.addSysLog(user, "查询花名册列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@RequestMapping("deletePerson")
	@ResponseBody
	public Map<String, Object> deleteByPrimaryKey(Long personId,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		boolean result = false;
		try {
			int count = ehrPersonService.searchSalary(personId);
			if(count == 0){
				result = ehrPersonService.deletePerson(personId);
				sysLogSercice.addSysLog(user, "员工信息,删除员工ID："+personId);
				if(result){
					map.put("success", 1);
					map.put("msg", "删除成功");
				}else{
					map.put("success", 2);
					map.put("msg", "删除失败");
				}
			}else{
				map.put("success", 3);
				map.put("msg", "已有工资记录，删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询工作地，发薪地
	 * @return
	 */
	@RequestMapping("searchBaseData")
	@ResponseBody
	public Map<String, Object> searchBaseData(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		List<EhrBaseData> list = null;
		try {
			list = ehrPersonService.searchBaseData();
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	
	/**
	 * 根据工号查询
	 * @return
	 */
	@RequestMapping("searchByJobNumber")
	@ResponseBody
	public Map<String,Object> searchByJobNumber(HttpServletRequest request, String jobNumber){
		if(StringUtils.isEmpty(jobNumber)){
			return MessageUtil.errorMessage("数据为空");
		}
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrPerson person = ehrPersonService.getByJobNumber(user.getCompanyId(), jobNumber);
		if(person == null){
			return MessageUtil.errorMessage("员工不存在"); 
		}
		
		Map<String,Object> result = MessageUtil.successMessage(null);
		result.put("person", person);
		return result;
	}
	
	@RequestMapping("/addPerson")
	@ResponseBody
	public Map<String, Object> addPerson(EhrPerson person, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = ehrPersonService.addPerson(person, user);
			sysLogSercice.addSysLog(user, "员工信息,新增员工姓名："+person.getEmployeeName());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}
		
		return map;
	}
	

	/**
	 * 个人信息
	 */
	@RequestMapping("/personalInfo")
	@ResponseBody
	public Map<String, Object> personalInfo(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			EhrPerson person = ehrPersonService.getByPersonId(personId);
			person.setCompanyName(user.getCompany().getCompanyName());
			map.put("person", person);
			//银行
 			map.put("bank", baseDataService.selectBaseData(user.getCompanyId(), 6, null));
 			System.out.println(personFileService.searchFile(personId ));
 			List<EhrPersonFile> personFileList=personFileService.searchFile(personId );
 			for(EhrPersonFile personFile:personFileList){
 				 switch(personFile.getFileType()){
 				 
 				 case 1:
 					map.put("shenfen", personFile.getFilePath());//证件类型电子版图片
 					 break;
 				 case 2:
 					map.put("hukou", personFile.getFilePath());//户口薄图片
 					 break;
 				case 3:
 					map.put("xueli",personFile.getFilePath());//学历证明图片
					 break;
				 case 4:
					 map.put("jianli",personFile.getFilePath());//电子简历图片
					 break;
				 case 5:
					 map.put("lizhi", personFile.getFilePath());//离职证明图片
 					 break;
 				 case 6:
 					map.put("jiankang", personFile.getFilePath());//健康证明图片
 					 break;
 				 case 7:
 					map.put("xinzi", personFile.getFilePath());//薪资证明图片
 					 break;
 				 }
 			}
 //     		map.put("shenfen", personFileService.searchFile(personId ));//证件类型电子版图片
//			map.put("hukou", personFileService.searchFile(personId, 2));//户口薄图片
//			map.put("xueli", personFileService.searchFile(personId, 3));//学历证明图片
//			map.put("jianli", personFileService.searchFile(personId, 4));//电子简历图片
//			map.put("lizhi", personFileService.searchFile(personId, 5));//离职证明图片
//			map.put("jiankang", personFileService.searchFile(personId, 6));//健康证明图片
//			map.put("xinzi", personFileService.searchFile(personId, 7));//薪资证明图片
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 基本信息页面入口
	 * @param request
	 * @return
	 */
	@RequestMapping("basicInformationPage")
	public String basicInformationPage(HttpServletRequest request){
		 
		return "employees/basic_information";
	}
	/**
	 * 基本信息
	 * @param personId
	 * @return
	 */
	@RequestMapping("essentialInfo")
	@ResponseBody
	public Map<String, Object> essentialInfo(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			EhrPerson person = ehrPersonService.getByPersonId(personId);
			person.setCompanyName(user.getCompany().getCompanyName());
			map.put("person", person);
			//城市
			map.put("city", baseDataService.selectBaseData(user.getCompanyId(), 5, null));
			//团队
			map.put("team", baseDataService.selectBaseData(user.getCompanyId(), 7, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSON(map));
		return map;
	}
	
	/**
	 * 岗位信息
	 * @param personId
	 * @return
	 */
	@RequestMapping("positionInfo")
	@ResponseBody
	public Map<String, Object> positionInfo(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			EhrPerson person = ehrPersonService.getByPersonId(personId);
			person.setCompanyName(user.getCompany().getCompanyName());
			map.put("person", person);
			//成本中心
			map.put("cost", baseDataService.selectBaseData(user.getCompanyId(), 2, null));
			//用工性质
			map.put("type", baseDataService.selectBaseData(user.getCompanyId(), 9, null));
			//职级
			map.put("rank", baseDataService.selectBaseData(user.getCompanyId(), 3, null));
			//岗位
			map.put("post", baseDataService.selectBaseData(user.getCompanyId(), 1, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 友情提示：转正人数
	 * @param request
	 * @return
	 */
	@RequestMapping("searchPositive")
	@ResponseBody
	public Map<String, Object> searchPositive(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		try {
			int positiveCount = ehrPersonService.searchPositive(10, companyId, distinguish, userId);
			int contractCount = ehrPersonService.searchContract(50, 0, companyId, distinguish, userId);
			int birthCount = ehrPersonService.searchBirth(0,userId, companyId, distinguish);
			int birthTCount = ehrPersonService.searchBirth(1,userId, companyId, distinguish);
			map.put("positiveCount", positiveCount);
			map.put("contractCount", contractCount);
			map.put("birthCount", birthCount);
			map.put("birthTCount", birthTCount);
			request.setAttribute("positiveCount", positiveCount);
			request.setAttribute("contractCount", contractCount);
			request.setAttribute("birthCount", birthCount);
			request.setAttribute("birthTCount", birthTCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("searchBirthList")
	@ResponseBody
	public Map<String,Object> searchBirthList(HttpServletRequest request,Integer day,Integer type){
		Map<String,Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrPerson> birthList;
		try {
			birthList = ehrPersonService.searchBirthList(day, userId, companyId, distinguish);
			map.put("data", birthList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	@RequestMapping("searchPositiveList")
	@ResponseBody
	public Map<String,Object> searchPositiveList(HttpServletRequest request,Integer type){
		Map<String,Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrPerson> positiveList;
		try {
			positiveList = ehrPersonService.searchPositiveList(10, companyId, distinguish, userId);
			map.put("data", positiveList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	@RequestMapping("searchContractList")
	@ResponseBody
	public Map<String,Object> searchContractList(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrContract> contractList;
		try {
			contractList = ehrPersonService.searchContractList(50, 0, companyId, distinguish, userId);
			map.put("data", contractList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	/**
	 * 导出转正人员列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportPositiveList")
	@ResponseBody
	public void  exportPositiveList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> datas = new HashMap<String,Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrPerson> positiveList = null;
		try {
			positiveList = ehrPersonService.searchPositiveList(10, companyId, distinguish, userId);
			datas.put("data",  this.getExcelPData(positiveList));
			sysLogSercice.addSysLog(user, "花名册列表：导出转正人员");
			//ExcelUtil.write(out, configList);
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename=" + new String("positiveList.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "positiveList.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<Map<String,Object>> getExcelPData(List<EhrPerson> positiveList) throws Exception{
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (EhrPerson person : positiveList) {
			map = new HashMap<String,Object>();
			map.put("personId", person.getPersonId());
			map.put("jobNumber", person.getJobNumber());
			map.put("employeeName", person.getEmployeeName());
			map.put("org", person.getOrg());
			map.put("post", person.getPost());
			map.put("mobile", person.getMobile());
			map.put("email", person.getEmail());
			map.put("positiveDate1", person.getPositiveDate1());
			
			datas.add(map);
		}

		return datas;
	}
	
	/**
	 * 导出合同到期人员列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportContractList")
	@ResponseBody
	public void exportContractList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> datas = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrContract> contractList;
		try {
			contractList = ehrPersonService.searchContractList(50, 0, companyId, distinguish, userId);
			
			datas.put("data", this.getExcelCData(contractList));
			sysLogSercice.addSysLog(user, "花名册列表：导出合同到期人员");
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename=" + new String("contractList.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "contractList.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private List<Map<String,Object>> getExcelCData(List<EhrContract> contractList) throws Exception{
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (EhrContract contract : contractList) {
			map = new HashMap<String,Object>();
			map.put("personId", contract.getPersonId());
			map.put("jobNumber", contract.getJobNumber());
			map.put("employeeName", contract.getEmployeeName());
			map.put("org", contract.getOrg());
			map.put("post", contract.getPost());
			map.put("mobile", contract.getMobile());
			map.put("email", contract.getEmail());
			map.put("contractEndDate1", contract.getContractEndDate1());
			
			datas.add(map);
		}

		return datas;
	}
	
	/**
	 * 导出生日列表
	 * @param request
	 * @param response
	 * @param day
	 */
	@RequestMapping("birthdayList")
	@ResponseBody
	public void birthdayList(HttpServletRequest request,HttpServletResponse response,Integer day){
		Map<String,Object> datas = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		String distinguish = user.getUserDistinguish();
		if("A1".equals(distinguish)){
			distinguish="GDSI";
		}else{
			if("A2".equals(distinguish)){
				distinguish="GDSS";
			} else{
				distinguish="";
			}
		}
		Long userId = user.getUserId();
		List<EhrPerson> birthList;
		try {
			birthList = ehrPersonService.searchBirthList(day, userId, companyId, distinguish);
			datas.put("data", this.getExcelBData(birthList));
			if(day == 0){
				sysLogSercice.addSysLog(user, "花名册列表：导出今天生日员工");
			}if(day == 1){
				sysLogSercice.addSysLog(user, "花名册列表：导出明天生日员工");
			}
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename=" + new String("birthdayList.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "birthdayList.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private List<Map<String,Object>> getExcelBData(List<EhrPerson> birthList) throws Exception{
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (EhrPerson person : birthList) {
			map = new HashMap<String,Object>();
			map.put("personId", person.getPersonId());
			map.put("jobNumber", person.getJobNumber());
			map.put("employeeName", person.getEmployeeName());
			map.put("org", person.getOrg());
			map.put("post", person.getPost());
			map.put("mobile", person.getMobile());
			map.put("email", person.getEmail());
			map.put("birthDate1", person.getBirthDate1());
			
			datas.add(map);
		}

		return datas;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping("employeeImport")
	@ResponseBody
	public Map<String, Object> employeeImport(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("importFile") CommonsMultipartFile importFile) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			ehrPersonService.importPerson(importFile, user);  
			data.put("result", true);
			data.put("error", ehrPersonService.errorMsg);
		} catch (Exception e) {
			data.put("result", false);
			e.printStackTrace();
		}
//		return "redirect:employeeList";
		return data;
	}
	
	@RequestMapping("employeeExport")
	@ResponseBody
	public Map<String, Object> employeeExport(HttpServletRequest request,
			HttpServletResponse response) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			List<EhrPerson> persons = ehrPersonService.selectAllPerson(user.getCompanyId());
			ehrPersonService.exportPerson(persons, user, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 个人信息、基本信息、岗位信息
	 * @param person
	 * @return
	 */
	@RequestMapping("updatePerson")
	@ResponseBody
	public Map<String, Object> updatePerson(HttpServletRequest request, HttpServletResponse response,@RequestBody EhrPerson person){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			person.setBirthDate((person.getBirthDate1() == null || "".equals(person.getBirthDate1()) ? null : df.parse(person.getBirthDate1())));
			person.setFirstjobDate((person.getFirstjobDate1() == null || "".equals(person.getFirstjobDate1())) ? null : df.parse(person.getFirstjobDate1()));
			map = ehrPersonService.updatePerson(request, person, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改个人信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 员工信息录入界面---手机版
	 */
	@RequestMapping("/personalInfoEnterPage")
	 
	public String personalInfoH5( HttpServletRequest request,Model modal){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			 
			//银行
 			modal.addAttribute("bank", baseDataService.selectBaseData(user.getCompanyId(), 6, null));
 			modal.addAttribute("personId", 1145);//user.getUserId() personId在此处为登录人ID
 			 
 			List<EhrPersonFile> personFileList=personFileService.searchFile(user.getUserId() );
 			for(EhrPersonFile personFile:personFileList){
 				 switch(personFile.getFileType()){
 				 
 				 case 1:
 					map.put("shenfen", personFile.getFilePath());//证件类型电子版图片
 					 break;
 				 case 2:
 					map.put("hukou", personFile.getFilePath());//户口薄图片
 					 break;
 				case 3:
 					map.put("xueli",personFile.getFilePath());//学历证明图片
					 break;
				 case 4:
					 map.put("jianli",personFile.getFilePath());//电子简历图片
					 break;
				 case 5:
					 map.put("lizhi", personFile.getFilePath());//离职证明图片
 					 break;
 				 case 6:
 					map.put("jiankang", personFile.getFilePath());//健康证明图片
 					 break;
 				 case 7:
 					map.put("xinzi", personFile.getFilePath());//薪资证明图片
 					 break;
 				 }
 			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "employees/yuangongluru";
	}
}
