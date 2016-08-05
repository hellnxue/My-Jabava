package com.jabava.controller.employee;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EhrFieldDisplayConfig;
import com.jabava.pojo.system.EhrTableData;
import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.service.common.ICommonDataService;
import com.jabava.service.employee.EhrContractService;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonFileService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.salary.ISalaryService;
import com.jabava.service.system.EhrTableDataService;
import com.jabava.service.system.EhrTableFieldDefService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrFieldDisplayConfigService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.FieldCol;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.excel.ExcelUtil;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
@RequestMapping("employee")
public class EhrPersonController {
	public static Logger log = Logger.getLogger(EhrPersonController.class);
	
	@Resource
	private EhrPersonService ehrPersonService;
	@Resource
	private IBaseDataService baseDataService;
	@Autowired
	private ICommonDataService commonDataService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Resource
	private EhrPersonFileService personFileService;
	@Resource
	private IUserService userService;
	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private EhrContractService ehrContractService;
	
	@Resource
	private EhrPositionService ehrPositionService;
	
	@Resource
	private EhrJobpostService jobpostService;
	
	@Resource
	private EhrTableDataService tableDataService;
	
	@Resource
	private EhrTableFieldDefService tableFieldDefService;
	
	@Resource
	private EhrTableFieldDefService  ehrTableFieldDefService;
	
	@Resource
	private IEhrFieldDisplayConfigService fieldDisplayConfigService;
	
	@RequestMapping("mobile_login")
	@ResponseBody
	public Map<String,Object> mobileLogin(HttpServletRequest request,@RequestBody Map<String,Object> data) throws Exception{
		//EhrUser user = RequestUtil.getLoginUser(request);  
		HttpSession session = request.getSession();
		
		String pwd = (String)data.get("pwd");
		String usr = (String)data.get("user");
		HashMap <String,Object> map = new HashMap <String,Object>();
		
		boolean result = userService.loginWidthPassword(usr, pwd);
		if(result){
			EhrUser user = userService.validateUser(usr);
			session.setAttribute(Constants.LOGIN_USER, user);
			Long personID = ehrPersonService.getPersonId(user.getUserId());
			
			map.put("personId", personID);
			map.put("status", 0);
		}else{
			log.error("==== 匹配账户密码失败");
			map.put("status", 1);
		}
				
		return map;
	}
	
	@RequestMapping(value="mobileInfoCollect/{personId}",method = RequestMethod.GET)
	public String mobileInfoCollector(HttpServletRequest request,@PathVariable Long personId){
		EhrUser user = RequestUtil.getLoginUser(request);
		
		if(user == null){
			return "employees/denglu";
		}else{
			request.setAttribute("userName", user.getUserCode());
			request.setAttribute("personId", personId);
				
			return "employees/yuangongluru";
		}
	}
	
	/**
	 * 移动端登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login_mobile")
	public String mobileLogin(HttpServletRequest request,HttpServletResponse response){
		return "employees/denglu";
	}
	
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
	 * 获取自定义字段与显示与否的配置信息
	 * @param  request  [description]
	 * @param  response [description]
	 * @return          [description]
	 */
	@RequestMapping("getCustomFieldAndDisplayConfig")
	@ResponseBody
	public Map<String, Object> getCustomFieldAndFieldDisplay(HttpServletRequest request,HttpServletResponse response) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			
			//取得所有的显示列，拼displayName
			
			Map<String, Object> params = new HashMap<>();
			params.put("companyId", user.getCompanyId());
			
			//所有的自定列-动态列
			List<EhrTableFieldDef> customFieldList = ehrTableFieldDefService.selectCustomField(params);
			
			params.put("function",FieldCol.getFunction("EmployeeList"));
			params.put("module", FieldCol.getMoudle("Organization"));
			
			//配置的显示列 原始数据
			List<EhrFieldDisplayConfig> displayColList= fieldDisplayConfigService.selectDisplayCol(params);
			
			//所有固定列
			List<Map<String, Object>> listCol = (List<Map<String, Object>>) FieldCol.getField("ehr_person",1).get("ehr_person");
			
			//Map<String, Object> customFieldMap = new HashMap<String, Object>();
			
			//将动态列的字段加入固定列中
			for(int i = 0; i<customFieldList.size();i++){
				Map<String, Object> mapCol=new HashMap<String, Object>();
				mapCol.put("columnName", customFieldList.get(i).getColumnName());
				mapCol.put("displayName", customFieldList.get(i).getDisplayName());
				listCol.add(mapCol);
				
				//customFieldMap.put(customFieldList.get(i).getColumnName(), customFieldList.get(i));
			}
			
			Map<String, Object> allFiledsMap=new HashMap<String,Object>();
			
			for(Map<String, Object> colmap:listCol){
				allFiledsMap.put((String)colmap.get("columnName"), colmap.get("displayName"));
			}
			
			//含有displayName与columnName的显示列
			List<Map<String, Object>> displayList = new ArrayList<Map<String, Object>>();
			
			Map<String, Object> xs=null;
			
			//遍历显示列  获取displayName
			if(displayColList!=null&&displayColList.size()>0 ){
				for(EhrFieldDisplayConfig fieldDisplayConfig:displayColList){
					
					String columnName=fieldDisplayConfig.getColumnName();
					String displayName=(String) allFiledsMap.get(columnName);
					 xs=new HashMap<String,Object>();
					xs.put("columnName", columnName);
					xs.put("displayName", displayName);
	 				displayList.add(xs);
					
				}
				data.put("Fileds", displayList);
			}else{
				//没有显示列的情况下，显示默认字段
				List<Map<String, Object>> defaultCol = (List<Map<String, Object>>) FieldCol.getField("ehr_person",0).get("ehr_person");
				data.put("Fileds", displayList);
			}
			
			data.put("linkFiled", "employee_name");//设置超链接字段
			
		} catch (Exception e) {
			e.printStackTrace();
			data.put("error", e.toString());
		}
		return data;
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
	public Page<Map<String, Object>> dataTableSearch(HttpServletRequest request,String data,String searchData, Integer start, Integer length){
		Map<String, Object> map=null;
 		//Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(data),Map.class);
		
		
	    map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(searchData),Map.class);
		
		EhrUser user = RequestUtil.getLoginUser(request);
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		String search = request.getParameter("searchValue");//search框的值
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
		
		Object isPayrollFlag= map.get("isPayrollFlag");
		Object status= map.get("status");
		
		
		
		if(isPayrollFlag!=null&&!StringUtils.isEmpty(isPayrollFlag.toString())){
			
			System.out.println(isPayrollFlag.toString());
			
			int isList=isPayrollFlag.toString().indexOf("]");//-1
			List<String> aList =null;
			if(isList==-1){
				aList=new ArrayList<String>();
				aList.add(isPayrollFlag.toString());
			
			}else{
				aList = (List<String>)isPayrollFlag;
			}
			
			map.put("isPayrollFlag", aList);
			 
		} 
 
		if(status!=null&&!StringUtils.isEmpty(status.toString())){
			
			System.out.println(status.toString());
			
			int isList=status.toString().indexOf("]");//-1
			List<String> aList =null;
			if(isList==-1){
				aList=new ArrayList<String>();
				aList.add(status.toString());
			}else{
				aList = (List<String>)status;
			}
			
			map.put("status", aList);
			 
		} 
		
		
		 
		
		List<Map<String, Object>> list = null;
		try {
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrPerson.getColumnName(order);
			SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
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
			
			
			
			//统计类型(首页提示信息转向)
			//map.put("countType", request.getParameter("countType"));
			//10天转正
			map.put("daysToBecomeMember", 10);
			//30天合同到期
			map.put("daysContractExpiring", 30);
			
			//list = ehrPersonService.searchPerson(map, user,start,length, search, order, according,isNumeric,page);
			list=ehrPersonService.searchPerson(map, user, start, length, search, order, according, isNumeric, page);
			page.setData(list);
			//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.Organization, "查询花名册列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 删除人员信息
	 * @param  personId [description]
	 * @param  response [description]
	 * @param  request  [description]
	 * @return          [description]
	 */
	@RequestMapping("deletePerson")
	@ResponseBody
	public Map<String, Object> deleteByPrimaryKey(Long personId,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		boolean result = false;
		try {
			//int count = ehrPersonService.searchSalary(personId);
			//if(count == 0){
			if(!salaryService.hasReferencedPerson(personId)){
				result = ehrPersonService.deletePerson(personId);
				if(result){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete,SystemEnum.Module.Organization,"员工信息,删除员工ID："+personId);
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
	 * 查询人员基本信息
	 * @param  request [description]
	 * @return         [description]
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
	 * 依据工号查询人员信息
	 * @param  request   [description]
	 * @param  jobNumber [description]
	 * @return           [description]
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

	/**
	 * 根据工号、姓名、身份证模糊查询
	 * @param request
	 * @param search
	 * @return
	 */
	@RequestMapping("searchBySearch")
	@ResponseBody
	public List<EhrPerson> searchBySearch(HttpServletRequest request, String search){
		if(StringUtils.isEmpty(search)){
			return new ArrayList<EhrPerson>();
		}
		EhrUser user = RequestUtil.getLoginUser(request);

		return ehrPersonService.getBySearch(user.getCompanyId(), search);
	}
	
	/**
	 * 添加个人信息
	 * @param  person  [description]
	 * @param  request [description]
	 * @return         [description]
	 */
	@RequestMapping("/addPerson")
	@ResponseBody
	public Map<String, Object> addPerson(EhrPerson person, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		
		try {
			map = ehrPersonService.addPerson(person, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Organization, "员工信息,新增员工姓名："+person.getEmployeeName());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}

		return map;
	}
	

	/**
	 * 取得个人信息
	 * @param  personId [description]
	 * @param  request  [description]  
	 * @return          [description]
	 */
	@RequestMapping("/personalInfo")
	@ResponseBody
	public Map<String, Object> personalInfo(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			EhrPerson person = ehrPersonService.getByPersonId(personId);
			person.setCompanyName(user.getCompany().getCompanyName());
			
			if(person.getCertType() == null){
				person.setCertType(0);
			}
			map.put("person", person);
			
			//基础数据部分
			map.put("bank", baseDataService.selectBaseData(user.getCompanyId(), 6, null));
			map.put("degree", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_DEGREE, null));
			map.put("education", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EDUCATION, null));
			//map.put("certType", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CERT_TYPE, null));
			map.put("certType", commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CERT_TYPE));
			//map.put("registerType", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_REGISTER_TYPE, null));
			map.put("politicsStatusList", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_POLITICS_STATUS, null));//政治面貌
			//上传的文件
			List<EhrPersonFile> personFileList=personFileService.searchFile(personId );
			for(EhrPersonFile personFile:personFileList){
				switch(personFile.getFileType()){
//					case 1:
//						map.put("shenfen", personFile.getFilePath());//证件类型电子版图片
//						break;
//					case 2:
//						map.put("hukou", personFile.getFilePath());//户口薄图片
//						break;
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
			
			//用户类型(根据手机号查找用户)
			EhrUser userByMobile = userService.validateUser(person.getMobile());
			map.put("userType", userByMobile == null ? null : userByMobile.getUserType());
			
			
			//获取员工表指定员工的的自定义字段信息
			Map<String, Object> customMap = new HashMap<String, Object>();
			customMap.put("companyId", user.getCompanyId());
			customMap.put("keyTable", "ehr_person");
			customMap.put("keyField", "person_id");
			customMap.put("keyValue", personId);
			//自定义字段&数据
			List<EhrTableFieldDef> customFieldList=tableFieldDefService.selectCustomFieldAndData(user.getCompanyId(),customMap);
			map.put("customFieldList", customFieldList);
			
			//信息项类型基础数据
			 List<EhrCommonData> commonDataList= commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CUSTOM_DATA_TYPE);//自定义字段信息项类型
			 map.put("commonDataList", commonDataList);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 基本信息页面转发
	 * @param  request [description]
	 * @return         [description]
	 */
	@RequestMapping("basicInformationPage")
	public String basicInformationPage(HttpServletRequest request){
		return "employees/basic_information";
	}

	/**
	 * 查询基本信息
	 * @param  personId [description]
	 * @param  request  [description]
	 * @return          [description]
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
			map.put("city", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CITY, null));
			//团队
			map.put("team", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_TEAM, null));
			// 员工状态
			//map.put("employeeStatus", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EMPLOYEE_STATUS, null));
			
			//岗位信息
			EhrPositionVO ehrPositionVO =  ehrPositionService.getEhrPositionByPersonId(personId);
			
			map.put("ehrPositionVO", ehrPositionVO);
			
			//获取指定员工的自定义字段信息
			Map<String, Object> customMap = new HashMap<String, Object>();
			customMap.put("companyId", user.getCompanyId());
			customMap.put("keyTable", "ehr_position");
			customMap.put("keyField", "post_id");
			customMap.put("keyValue", personId);
			//自定义字段&数据
			List<EhrTableFieldDef> customFieldList=tableFieldDefService.selectCustomFieldAndData( user.getCompanyId(),customMap);
			map.put("customFieldList", customFieldList);
			
			//信息项类型基础数据
			 List<EhrCommonData> commonDataList= commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CUSTOM_DATA_TYPE);//自定义字段信息项类型
			 map.put("commonDataList", commonDataList);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.toString());
		}
		return map;
	}
	
	/**
	 * 查询岗位信息
	 * @param  personId [description]
	 * @param  request  [description]
	 * @return          [description]
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
			map.put("cost", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_COST_CENTER, null));
			//用工性质
			map.put("type", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EMPLOYEE_TYPE, null));
			//职级
			map.put("rank", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_LEVEL, null));
			//岗位
			map.put("post", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_POSITION, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询需要转正的人员人数
	 * @param  request [description]
	 * @return         [description]
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
	
	/**
	 * 查询过生日的人员列表
	 * @param  request [description]
	 * @param  day     [description]
	 * @param  type    [description]
	 * @return         [description]
	 */
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

	/**
	 * 查询转正人员列表
	 * @param  request [description]
	 * @param  type    [description]
	 * @return         [description]
	 */
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

	/**
	 * [searchContractList description]
	 * @param  request [description]
	 * @return         [description]
	 */
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
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Download,SystemEnum.Module.Organization, "花名册列表：导出转正人员");
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
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Download, SystemEnum.Module.Organization, "花名册列表：导出合同到期人员");
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
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Download, SystemEnum.Module.Organization , "花名册列表：导出今天生日员工");
			}if(day == 1){
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Download, SystemEnum.Module.Organization , "花名册列表：导出明天生日员工");
			}
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename=" + new String("birthdayList.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "birthdayList.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * [getExcelBData description]
	 * @param  birthList [description]
	 * @return           [description]
	 * @throws Exception [description]
	 */
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
	
	/**
	 * 导入员工花名册
	 * @param  request    [description]
	 * @param  response   [description]
	 * @param  importFile [description]
	 * @return            [description]
	 */
	@RequestMapping("employeeImport")
	@ResponseBody
	public Map<String, Object> employeeImport(HttpServletRequest request,HttpServletResponse response,@RequestParam("importFile") CommonsMultipartFile importFile) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		
		try {
			String msg = ehrPersonService.importPerson(importFile, user);  
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Upload, SystemEnum.Module.Organization, "批量导入员工花名册");
			data.put("result", true);
			data.put("msg", msg);
			data.put("error", "");
		} catch (Exception e) {
			data.put("result", false);
			data.put("error", e.getMessage());
		
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 导出个人信息
	 * @param  request  [description]
	 * @param  response [description]
	 * @return          [description]
	 */
	@RequestMapping("employeeExport")
	@ResponseBody
	public Map<String, Object> employeeExport(HttpServletRequest request,HttpServletResponse response) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("companyId", user.getCompanyId());
			String countType = request.getParameter("countType");
			if(!StringUtils.isEmpty(countType)){
				params.put("countType", countType);
				//10天转正
				params.put("daysToBecomeMember", 10);
				//30天合同到期
				params.put("daysContractExpiring", 30);
			}
			//List<EhrPerson> persons = ehrPersonService.selectAllPerson(user.getCompanyId());
			List<EhrPerson> persons = ehrPersonService.selectPersonByParam(params);
			ehrPersonService.exportPerson(persons, user, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 修改个人信息
	 * @param  request  [description]
	 * @param  response [description]
	 * @param  person   [description]
	 * @return          [description]  
	 */
	@RequestMapping("updatePerson")
	@ResponseBody
	public Map<String, Object> updatePerson(HttpServletRequest request, HttpServletResponse response,String personData,String customData/*@RequestBody EhrPerson person*/){
		Map<String, Object> map = new HashMap<>();
		EhrUser user = RequestUtil.getLoginUser(request);
		System.out.println(personData);
		EhrPerson person= (EhrPerson) JSONObject.toBean(JSONObject.fromObject(personData),EhrPerson.class);
		JSONObject jo=JSONObject.fromObject(personData);
		//处理日期
		if(jo.get("birthDate")!=null&&!"".equals(jo.get("birthDate"))){
			try {
				Date birthDate=JabavaDateUtils.parseDate(jo.get("birthDate").toString(), "yyyy-MM-dd");
				person.setBirthDate(birthDate);
				
				Date firstjobDate=JabavaDateUtils.parseDate(jo.get("firstjobDate").toString(), "yyyy-MM-dd");
				person.setFirstjobDate(firstjobDate);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
 		//处理自定义字段的数据信息
		List<EhrTableData> customDatalist = JSON.parseArray(customData, EhrTableData.class); 
        if(customDatalist!=null&&customDatalist.size()>0){
        	 tableDataService.handleCustomData(customDatalist);
        }
        
		try {
	//	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//		person.setBirthDate((person.getBirthDate1() == null || "".equals(person.getBirthDate1()) ? null : df.parse(person.getBirthDate1())));
	//		person.setFirstjobDate((person.getFirstjobDate1() == null || "".equals(person.getFirstjobDate1())) ? null : df.parse(person.getFirstjobDate1()));
			if(person.getCertId() != null){
				person.setCertId(person.getCertId().toUpperCase());
			}
			map = ehrPersonService.updatePerson(request, person, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "修改员工"+person.getEmployeeName()+"的个人信息");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改个人信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	
 
	
	/**
	 * 修改基本信息
	 * @param  request  [description]
	 * @param  response [description]
	 * @param  person   [description]
	 * @return          [description]
	 */
	@RequestMapping("updateBasicInfo")
	@ResponseBody
	public Map<String, Object> updateBasicInfo(HttpServletRequest request, HttpServletResponse response,
			String basicData,String positionData,String reportEmployeeName,String customData) {
		EhrUser user = RequestUtil.getLoginUser(request);
		
		//基本信息
		EhrPerson person= (EhrPerson) JSONObject.toBean(JSONObject.fromObject(basicData),EhrPerson.class);
		JSONObject jo=JSONObject.fromObject(basicData);
		//处理时间
		if(jo.get("entryDate")!=null&&!"".equals(jo.get("entryDate"))){
			try {
				Date entryDate=JabavaDateUtils.parseDate(jo.get("entryDate").toString(), "yyyy-MM-dd");
				person.setEntryDate(entryDate);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//处理自定义字段的数据信息
		List<EhrTableData> customDatalist = JSON.parseArray(customData, EhrTableData.class); 
        if(customDatalist!=null&&customDatalist.size()>0){
        	 tableDataService.handleCustomData(customDatalist);
        }
		
		//岗位信息
		EhrPosition ehrPosition=  (EhrPosition) JSONObject.toBean(JSONObject.fromObject(positionData),EhrPosition.class);
		
//		Date date  = new Date();
//		ehrPosition.setCreateDate(date);
//		ehrPosition.setCreateUserId(user.getUserId());
//		ehrPosition.setUpdateDate(date);
//		ehrPosition.setUpdateUserId(user.getUserId());		
		
		String msg = ehrPositionService.addPositin(ehrPosition,null,reportEmployeeName);
		if(!"success".equals(msg)){
			return MessageUtil.errorMessage(msg);
		}
		
		try{
//			//入职时间
//			person.setEntryDate(null);
//			//转正时间
//			person.setPositiveDate(null);
			
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());

			if(ehrPersonService.updatePerson(person) == 0){
				return MessageUtil.errorMessage("修改基本信息失败");
			}else{
				//处理任职记录
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "修改id为"+person.getPersonId()+"的员工工作信息");
				 jobpostService.HandleEntryRecordByPersonId(person.getPersonId(), user, person.getEntryDate());
				return MessageUtil.successMessage("修改基本信息成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
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
			modal.addAttribute("personId", 1146);//user.getUserId() personId在此处为登录人ID
			 
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
	
	@RequestMapping("queryHintInfo")
	@ResponseBody
	public Map<String,Object> queryHintInfo(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		//10天转正
		params.put("daysToBecomeMember", 10);
		//30天合同到期
		params.put("daysContractExpiring", 30);
		return ehrPersonService.queryHintInfo(params);
	}
	
	/**
	 * 加载移动端基础信息编辑页面内容 
	 */
	@RequestMapping(value="mobileBaseData/{personId}")
	@ResponseBody
	public Map<String,Object> queryMobileBaseInfo(HttpServletRequest request,@PathVariable("personId")Long personId) throws Exception{
		HashMap <String,Object> map = new HashMap<String,Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		
		map.put("status", 1);
		
		EhrPerson person = ehrPersonService.getByPersonId(personId);
		//person.setCompanyName(user.getCompany().getCompanyName());
		
		if(person.getCertType() == null){
			person.setCertType(0);
		}
		map.put("person", person);
		
		map.put("bank", baseDataService.selectBaseData(user.getCompanyId(), 6, null));
		map.put("degree", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_DEGREE, null));
		map.put("education", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_EDUCATION, null));
		//map.put("certType", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CERT_TYPE, null));
		map.put("certType", commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_CERT_TYPE));
		//map.put("registerType", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_REGISTER_TYPE, null));
		//map.put("marriage", baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_MARITAL, null));
		
		return map;
	}
	
	
	/**
	 * 根据员工personID获取员工的合同信息--初始化转正弹出页面
	 * @param request
	 * @param personID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getContractInfoByPersonID")
	@ResponseBody
	public Map<String,Object> getContractInfoByPersonID(HttpServletRequest request,@RequestParam Long personId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request); 
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personID",personId);
		params.put("contractType", 1);//合同类型-劳动合同
//		params.put("contractBussinessType", 1);//业务类型-签订
		Map<String,Object> contractInfo= ehrContractService.getContractInfoByPersonID(params);
		params.put("contract", contractInfo);
		
		//岗位
		List<EhrBaseData> positionList =  baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_POSITION, null);//岗位基础数据
		
		params.put("positionList", positionList);
		 
		return params;
	}
	
	/**
	 * 人员转正-回写合同信息与基本信息的实际转正时间
	 * @param request
	 * @param personID
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setContractAndBasicInfo")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request,@RequestBody EhrContract ct) throws Exception{
		Map<String, Object> data = new HashMap<>();
		
		EhrUser user = RequestUtil.getLoginUser(request); 
		 
		ct.setLastModifyDate(new Date());
		ct.setLastModifyUserId(user.getUserId());
		ct.setLastModifyUserName(user.getUserName());
		 
		boolean flag=ehrContractService.updateContract(ct);
		
		EhrPerson person=new EhrPerson();
		person.setPersonId(ct.getPersonId());
		person.setLastModifyDate(new Date());
		person.setLastModifyUserId(user.getUserId());
		person.setLastModifyUserName(user.getUserName());
		person.setPositiveDate(ct.getFactPositiveDate());
		
		int fl=ehrPersonService.updatePerson(person);
		if(flag&&fl>0){
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "把id为"+ct.getPersonId()+"的员工转正");
			data.put("result", true);
		}else{
			data.put("result", false);
		}
		
		return data;
	}
	

	
//	@RequestMapping("getUserTypeByMobile/{mobile}")
//	@ResponseBody
//	public Map<String,Object> getUserTypeByMobile(@PathVariable String mobile){
//		EhrUser user = userService.validateUser(mobile);
//		Map<String,Object> result = MessageUtil.successMessage(null);
//		if(user == null){
//			result.put("userType", null);
//		}else{
//			result.put("userType", user.getUserType());
//		}
//		return result;
//	}
	
	public static void main(String[] args) {
		String a=new String("hello");
		EhrPerson pp=new EhrPerson();
		System.out.println(pp.toString());
	}
}
