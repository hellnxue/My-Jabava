package com.jabava.controller.socialsecurity;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccount;
import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.socialsecurity.EhrDesignatedHospital;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.socialsecurity.EhrSecurityItem;
import com.jabava.pojo.socialsecurity.EhrSupplementPayment;
import com.jabava.pojo.socialsecurity.SsPaymentBill;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccount;
import com.jabava.service.accumulationfund.AccumulationFundAccountService;
import com.jabava.service.employee.EhrPersonFileService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.socialsecurity.EhrDesignatedHospitalService;
import com.jabava.service.socialsecurity.EhrPersonSecurityProfileService;
import com.jabava.service.socialsecurity.EhrSecurityItemService;
import com.jabava.service.socialsecurity.EhrSupplementPaymentService;
import com.jabava.service.socialsecurity.SsSocialSecurityAccountService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.Constants;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.enums.SystemEnum.Module;
import com.jabava.utils.excel.ExcelUtil;

@Controller
@RequestMapping("/employee")
public class EhrPersonSecurityProfileController {
	public static Logger log = Logger.getLogger(EhrPersonSecurityProfileController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Resource
	private EhrPersonSecurityProfileService ehrPersonSecurityProfileService;
	
	@Resource
	private SsSocialSecurityAccountService ssSocialSecurityAccountService;
	
	@Resource
	private AccumulationFundAccountService gongjijinAccountService;
	
	@Resource
	private EhrDesignatedHospitalService ehrDesignatedHospitalService;
	
	@Resource
	private EhrSecurityItemService ehrSecurityItemService;
	
	@Resource
	private EhrSupplementPaymentService ehrSupplementPaymentService;
	
	@Resource
	private IBaseDataService baseDataService;
	
	@Autowired
	private EhrPersonService personService;
	
	@Resource
	private EhrPersonFileService personFileService;
	
	@RequestMapping("securityProfile")
	public String securityProfile(HttpServletRequest request){
		return "insuranceDocs/listDocs";
	}
	
	/**
	 * 依据企业代码查询企业下所有在缴状态的员工社保档案
	 * @param companyId
	 * @return
	 */
	@RequestMapping("getSecurityProfileByCompanyId")
	@ResponseBody
	public Map<String,Object> getSecurityProfileByCompanyId(String companyId){
		HashMap <String,Object> map = new HashMap<String,Object>();
		return map;
	}
	
	/**
	 * 根据公司代码查询对应的社保账号
	 * @return
	 */
	@RequestMapping(value="getSecurityAccount", method=RequestMethod.GET)  
	@ResponseBody
	public Map <String,Object> getSecurityAccount(HttpServletRequest request){
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		Long companyId = ehrUser.getCompanyId();
		
		List <SsSocialSecurityAccount> securityList = ssSocialSecurityAccountService.getSecurityAccountByCompanyId(companyId);
		List<AfAccumulationFundAccount> gongjijinList = gongjijinAccountService.getAccumulationFundProfileByCompanyId(companyId);
		
		HashMap <String,Object> payload =  new HashMap<String,Object>();
		payload.put("security", JSONObject.toJSON(securityList));
		payload.put("gongjijin", JSONObject.toJSON(gongjijinList));
		
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",payload);
		
		return map;
	}
	
	/**
	 * 根据社保账号代码查询可用参保类型
	 * @param accountId 社保账号代码
	 * @return
	 */
	@RequestMapping(value="getSecurityRulesByAccount/{accountId}", method=RequestMethod.GET)
	@ResponseBody
	public Map <String,Object> getSecurityRulesByAccount(@PathVariable Long accountId){
		List <SbGroup> rulesList = ssSocialSecurityAccountService.getSecurityRulesByAccount(accountId);
		
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",rulesList);
		
		return map;
	}
	/**
	 * 根据公积金账号代码查询可用参保类型
	 * @param accountId 社保账号代码
	 * @return
	 */
	@RequestMapping(value="getFundAccountRulesByAccount/{accountId}", method=RequestMethod.GET)
	@ResponseBody
	public Map <String,Object> getFundAccountRulesByAccount(@PathVariable Long accountId){		
		List <SbGroup> rulesList = gongjijinAccountService.getFundAccountRulesByAccount(accountId);
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",rulesList);
		return map;
	}
	/**
	 * 获取社保规则下的规则明细
	 * @param groupId    规则代码
	 * @param groupType  规则类型 1 社保规则  2 公积金规则
	 * @return
	 */
	@RequestMapping(value="getSecurityGroupDetail/{groupId}/{groupType}",method=RequestMethod.GET)
	@ResponseBody
	public Map <String,Object> getSecurityGroupDetail(@PathVariable Long groupId,@PathVariable Long groupType){
		List<Map<String,Object>> rulesList = ssSocialSecurityAccountService.getSecurityGroupDetail(groupId,groupType);
		
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",rulesList);
		
		return map;
	}
	
	/**
	 * 依据员工代码查询社保档案
	 * @param personId
	 * @return
	 */
	@RequestMapping(value="securityProfile/{personId}",method=RequestMethod.GET)
	@ResponseBody
	public Map <String,Object> getSecurityProfileById(@PathVariable Long personId){
		EhrPersonSecurityProfile profile = ehrPersonSecurityProfileService.getSecurityProfileById(personId);
		
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",profile);
		
		return map;
	}
	
	/**
	 * 保存或更新社保档案
	 * @param securityProfile 社保档案信息
	 * @return
	 */
	@RequestMapping(value="securityProfile",method=RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Map <String,Object> saveSecurityProfile(@RequestBody EhrPersonSecurityProfile securityProfile){
		EhrPersonSecurityProfile profile = ehrPersonSecurityProfileService.saveOrUpdate(securityProfile);
		
		log.error("=================== running now ===============");
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data", profile);
		
		log.error(profile.getProfileCode());
		
		return map;
	}

	/**
	 * 取得社保档案信息
	 * @return
	 */
	@RequestMapping("searchSecurityProfile")
	@ResponseBody
	public Page<Map <String,Object>> searchSecurityProfilePage(HttpServletRequest request,int start,int length){
		HashMap <String,Object> queryParam = new HashMap<String,Object>();
		
		// 查询参数
		String search = request.getParameter("search[value]");
		if(StringUtils.isNotBlank(search)){
			queryParam.put("search", search);
		}
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		queryParam.put("companyId", ehrUser.getCompanyId());
		
		// 分页参数
		Page<Map <String,Object>> page = new Page<Map <String,Object>>(start,length);
		queryParam.put("page",page);
		
		//ehrPersonSecurityProfileService.updateRecordStatus();
		
		List <Map<String,Object>> list = ehrPersonSecurityProfileService.searchSecurityProfilePage(queryParam);
		page.setData(list);
		
		return page;
	}
	/**
	 * 查询定点医院
	 * @param personId
	 * @return
	 */
	@RequestMapping(value="designatedHospital/{personId}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getDesignatedHospitalInfoByProfileId(@PathVariable Long personId){
		List<EhrDesignatedHospital> list = ehrDesignatedHospitalService.getDesignatedHospitalInfoByProfileId(personId);
		
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data", list);
		
		return map;
	}
	
	/**
	 * 保存定点医院
	 * @param list
	 * @return
	 */
	@RequestMapping(value="designatedHospital",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveHospitalList(@RequestBody List<EhrDesignatedHospital> list){
		for(EhrDesignatedHospital hos : list){
			StringBuffer sb = new StringBuffer();
			sb.append("id=" + hos.getProfileId());
			sb.append(" name=" + hos.getHospitalName());
		}
		
		int ret = ehrDesignatedHospitalService.savetDesignatedHospitalInfo(list);
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data", ret);
		
		return map;
	}
	
	/**
	 * 保存社保项目列表
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="saveSecurityItems",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveSecurityItems(@RequestBody List<EhrSecurityItem> list) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String createTime = sdf.format(new Date());
		
		for(EhrSecurityItem item: list){
			item.setCreateTime(createTime);
			item.setIsDeleted(0);
		}
		
		int ret = ehrSecurityItemService.saveEhrSecurityItems(list);
			
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",ret);
		sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Update, Module.Ssaf, "修改员工id为"+list.get(0).getPersonId()+"的社保档案");
		return map;
	}
	
	/**
	 * 根据员工代码取得员工相关的社保明细信息
	 * @param personId 员工代码
	 * @return
	 */
	@RequestMapping(value="saveSecurityItems/{personId}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getSecurityItemsByPersonId(@PathVariable Long personId){
		List <EhrSecurityItem> list = ehrSecurityItemService.getEhrSecurityItemlInfoByPersonId(personId);
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("text", "OK");
		map.put("data",list);
		
		return map;
	}
	
	/**
	 * 保存补缴信息
	 * @param list
	 * @return
	 */
	@RequestMapping(value="supplementPayment",method = RequestMethod.POST)
	@ResponseBody
	public HashMap <String,Object> saveSupplementPayment(@RequestBody List <EhrSupplementPayment> list){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS");
		String createTime = sdf.format(new Date());
		
		for(EhrSupplementPayment esp : list){
			esp.setCreateTime(createTime);
			esp.setIsDeleted(0);
		}
		
		// TODO　需要新增一个插入服务
		int ret = ehrSupplementPaymentService.addBatchSupplementPaymentInfo(list);
		HashMap <String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		result.put("text", "OK");
		result.put("data", ret);
		
		return result;
	}
	
	@RequestMapping(value="supplementPayment/{personId}/{month}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getEhrSupplementPaymentList(@PathVariable Long personId,@PathVariable String month){
		List<EhrSupplementPayment> ret = ehrSupplementPaymentService.getSupplementPaymentInfo(personId, month);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		result.put("text", "OK");
		result.put("data", ret);
		
		return result;
	}
	
	@RequestMapping(value="getAllProductsByPersonId/{personId}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAllProductsByPersonId(HttpServletRequest request,@PathVariable Long personId) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		
		List <Map<String,Object>> list = ehrPersonSecurityProfileService.getAllProductsByPersonId(personId);
		
		EhrPersonSecurityProfile profile = ehrPersonSecurityProfileService.getSecurityProfileById(personId);
		List<EhrBaseData> hispitalList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_HOSPITAL, null);
		
		result.put("hospital", hispitalList);
		
		result.put("status", 0);
		result.put("text", "OK");
		result.put("data", list);
		result.put("profile", profile);
		
		return result;
	}
	
	@RequestMapping(value="exportSecurityProfile",method = RequestMethod.GET)
	@ResponseBody
	public void exportSecurityProfile(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> datas = new HashMap<String,Object>();
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		Long totalCount = ehrPersonSecurityProfileService.selectProfileTotalCount(ehrUser.getCompanyId());
		log.error("有效社保档案总数:" + totalCount);
		
		Page<Map <String,Object>> page = new Page<Map <String,Object>>(0,totalCount.intValue());
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("page",page);
		
		map.put("companyId", ehrUser.getCompanyId());
		
		List <Map<String,Object>> list = ehrPersonSecurityProfileService.searchSecurityProfilePage(map);
		
		HashMap <Integer,String> statusMapper = new HashMap <Integer,String>();
		statusMapper.put(0, "草稿");
		statusMapper.put(1, "在缴");
		statusMapper.put(2, "停缴");
		
		for(Map<String,Object> tup : list){
			Object st = tup.get("gongjijinStatus");
			String status = statusMapper.get(st);
			tup.put("gongjijinStatus",status);
			
			st = tup.get("securityStatus");
			status = statusMapper.get(st);
			tup.put("securityStatus",status);
		}
		
		try {
			datas.put("data", list);
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename=" + new String("insurance_profile.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "security_profile.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("loadPaymentBillPersonHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonHeader(HttpServletRequest request, HttpServletResponse response,
			Long personId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(personId == null){
			throw new Exception("数据为空");
		}
		EhrPerson person = personService.getByPersonId(personId);
		if(person == null || !user.getCompanyId().equals(person.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personId", personId);
		params.put("startMonth", request.getParameter("startMonth"));
		params.put("endMonth", request.getParameter("endMonth"));
		return ehrPersonSecurityProfileService.loadPaymentBillPersonHeader(params,true);
	}
	
	@RequestMapping("listPaymentBillPerson")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPerson(HttpServletRequest request, HttpServletResponse response,
			Long personId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(personId == null){
			throw new Exception("数据为空");
		}
		EhrPerson person = personService.getByPersonId(personId);
		if(person == null || !user.getCompanyId().equals(person.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personId", personId);
		params.put("startMonth", request.getParameter("startMonth"));
		params.put("endMonth", request.getParameter("endMonth"));
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ehrPersonSecurityProfileService.listPaymentBillPerson(params);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("exportEmployeePayment")
	public void exportEmployeePayment(HttpServletRequest request, HttpServletResponse response, Long personId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(personId == null){
			throw new Exception("数据为空");
		}
		EhrPerson person = personService.getByPersonId(personId);
		if(person == null || !user.getCompanyId().equals(person.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personId", personId);
		params.put("startMonth", request.getParameter("startMonth"));
		params.put("endMonth", request.getParameter("endMonth"));
		
		Map<String,Object> datas = new HashMap<String,Object>();
		datas.put("headerList", ehrPersonSecurityProfileService.loadPaymentBillPersonHeader(params,false));
		datas.put("personList", ehrPersonSecurityProfileService.listPaymentBillPerson(params));
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=" + new String("payment_employee.xlsx"));
		
		OutputStream out = response.getOutputStream();
		ExcelUtil.write(datas, "tpl_payment_employee.xlsx", out);
	}
	
	/**
	 * 导入员工社保档案
	 * @param  request    [description]
	 * @param  response   [description]
	 * @param  importFile [description]
	 * @return            [description]
	 */
	@RequestMapping("personSecurityProfileImport")
	@ResponseBody
	public Map<String, Object> personSecurityProfileImport(HttpServletRequest request,HttpServletResponse response,@RequestParam("importFile") CommonsMultipartFile importFile) {
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		String securityOrgAccount=request.getParameter("securityOrgAccount");//社保账号
		String securityType= request.getParameter("securityType");//社保参保类型
		
		String gongjijinOrgAccount=request.getParameter("gongjijinOrgAccount");//公积金账号
		String gongjijinType= request.getParameter("gongjijinType") ;//公积金参保类型
		
		if(!StringUtils.isEmpty(securityOrgAccount)&&!StringUtils.isEmpty(securityType)&&!StringUtils.isEmpty(gongjijinOrgAccount)&&!StringUtils.isEmpty(gongjijinType)){
			try {
				String msg = ehrPersonSecurityProfileService.personSecurityProfileImport(importFile,user,  Long.parseLong(securityOrgAccount),   Long.parseLong(securityType),Long.parseLong(gongjijinOrgAccount)  , Integer.parseInt(gongjijinType) );  
				data.put("error", msg);
				data.put("result", StringUtils.isEmpty(msg) ? false : true);
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Upload, SystemEnum.Module.Ssaf, "批量导入员工社保档案");
			} catch (Exception e) {
				data.put("result", false);
				data.put("error", e.getMessage());
				e.printStackTrace();
			}   
		}
		 
		return data;
	}
	
	/**
	 * 依据员工代码查询社保档案信息-花名册卡片部分
	 * @param personId
	 * @return
	 */
	@RequestMapping("getSecurityProfileInfoById")
	@ResponseBody
	public Map <String,Object> getSecurityProfileInfoById( HttpServletRequest request,@RequestParam Long personId){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map <String,Object>  map = null;
		try {

			//员工社保公积金档案信息
			 map= ehrPersonSecurityProfileService.getSecurityProfileInfoByPersonId(personId);
			 
			//城市基础数据
			List<EhrBaseData> cityList =  baseDataService.selectBaseData(user.getCompanyId(),  BaseDataConstants.BASE_DATA_CITY, null);
			map.put("cityList", cityList);
			
			//定点医院
			List<EhrBaseData> hispitalList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_HOSPITAL, null);
			
			map.put("hospital", hispitalList);
			
			//上传的文件
			List<EhrPersonFile> personFileList=personFileService.searchFile(personId );
			for(EhrPersonFile personFile:personFileList){
				switch(personFile.getFileType()){
					case 1:
						map.put("shenfenPhoto", personFile.getFilePath());//身份证正反面
						break;
					case 2:
						map.put("hukouIndexPhoto", personFile.getFilePath());//户口薄首页图片
						break;
					case 8:
						map.put("bankPhoto",personFile.getFilePath());//银行卡
						break;
					case 9:
						map.put("personalPhoto",personFile.getFilePath());//免冠照
						break;
					case 10:
						map.put("hukouSelfPhoto", personFile.getFilePath());//户口本本人页
						break;
				 }
			}
			
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("status", 0);
			map.put("data",e.getMessage());
			e.printStackTrace();
		} 
		
		
		return map;
	}
	
	
	/**
	 * 添加员工社保公积金信息--花名册卡片页面
	 * @param personId
	 * @return
	 */
	@RequestMapping("addSecurityProfileInfo")
	@ResponseBody
	public Map <String,Object> addSecurityProfileInfoById( HttpServletRequest request,@RequestBody EhrPersonSecurityProfile profile ){
		Map <String,Object> map = null;
		try {		
			
			map = ehrPersonSecurityProfileService.insertSelective(profile);
			
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
	
		return map;
	}
	
	/**
	 * 修改员工社保公积金信息--花名册卡片页面
	 * @param personId
	 * @return
	 */
	@RequestMapping("updateSecurityProfileInfo")
	@ResponseBody
	public Map <String,Object> updateSecurityProfileInfoById( HttpServletRequest request,@RequestBody EhrPersonSecurityProfile profile ){
		Map <String,Object> map = null;
		try {		
			
			map = ehrPersonSecurityProfileService.updateByPrimaryKeySelective(profile);
			
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
	
		return map;
	}
	
}
