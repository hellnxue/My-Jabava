package com.jabava.controller.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.accumulationfund.AfPaymentBillService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.salary.IMonthlySalaryService;
import com.jabava.service.socialsecurity.ISsPaymentBillService;
import com.jabava.utils.Constants;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.constants.ApiConstants;
 
/**
 * 提供用户相关的对外的查询接口
 *
 * @version $Id: UserController.java, 
 * v 0.1 2016年4月20日 下午2:44:32 
 * <pre>
 * @author steven.chen
 * @date 2016年4月20日 下午2:44:32 
 * </pre>
 */
@Controller
@RequestMapping("api/employeeInteface")
public class EmployeeIntefaceController {
	
	@Resource
	private EhrPersonService ehrPersonService;
	@Resource
	private IUserService userService;
	@Autowired
	private IMonthlySalaryService monthlySalaryService;
	@Autowired
	private ISsPaymentBillService ssPaymentBillService;
	@Autowired
	private AfPaymentBillService afPaymentBillService;
	
	private static final String URI_HRO_SALARY_QUERY = "api/SalaryQueryByCardIdAndMonth.do";
	private static final String URI_HRO_SOCIAL_INSURANCE = "api/SocialInsuranceController.do";
	
	private HROFetchService hroFetchService;
	
	public EmployeeIntefaceController(){
		this.hroFetchService = new HROFetchService(ApiConstants.AUTHURI_HRO, ApiConstants.URI_HRO_REST);
	}
	
	
	/**
	 * 根据登录的用户名返回人员信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午3:00:34 
	 * </pre>
	 *
	 * @param userName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getEmployeeByUserName")
	public  void getEmployeeByUserName(String userName,HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		
		if(userName !=null && StringUtils.isNotBlank(userName)){
			 byte[] b = userName.getBytes("ISO-8859-1");  
			 String str = new String(b,"UTF-8"); 
			EhrUser user = userService.searchUserByUserCode(str);
			if(user!=null){								
				Map<String,String> map=new HashMap<String, String>();
				map.put("employeeId", user.getUserId().toString());	//登录用户id
				map.put("employeeName", user.getUserName());	//用户姓名
				JSONArray jsonArray=JSONArray.fromObject(map);
				response.getOutputStream().write( jsonArray.toString().getBytes("UTF-8") );
			}else{
				response.getOutputStream().write(("系统查不到当前登录人").getBytes("UTF-8"));
			}
		}else{
			response.getOutputStream().write(("请传递用户名").getBytes("UTF-8"));
		}
	}
	/**
	 * 根据客户id查询该客户下的所有在职员工(带员工姓名的模糊查询 返回:employeeId,employeeName)
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午3:12:39 
	 * </pre>
	 *
	 * @param companyId
	 * @param employeeName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping("getEmployeesByCompanyId")
	public void getEmployeesByCompanyId(Long companyId,String employeeName,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		if(companyId !=null){
			EhrPerson ehrPerson = new EhrPerson();
			if(companyId<0){				
				ehrPerson.setCompanyId(Math.abs(companyId));
			}else{
				ehrPerson.setCompanyId(companyId);
			}
			
			if(StringUtils.isNotBlank(employeeName)){
				 byte[] b = employeeName.getBytes("ISO-8859-1");  
				 String str = new String(b,"UTF-8"); 
				ehrPerson.setEmployeeName(str);
			}			
			List<EhrPerson> personList = ehrPersonService.getEmployeesByCompanyId(ehrPerson);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(personList !=null && personList.size()>0){
				for(EhrPerson person:personList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("companyId", person.getCompanyId());
					map.put("orgId",  person.getCompanyId()-(person.getCompanyId()*2));
					map.put("employeeName", person.getEmployeeName());
					list.add(map);
				}
				JSONArray jsonArray=JSONArray.fromObject(list);
				response.getOutputStream().write( jsonArray.toString().getBytes("UTF-8") );
			}else{
				response.getOutputStream().write(("系统中查不到相关信息").getBytes("UTF-8"));
			}
		}else{
			response.getOutputStream().write(("请传递企业编号").getBytes("UTF-8"));
		}
	}
	/**
	 * 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午4:05:23 
	 * </pre>
	 *  根据员工ID查询员工姓名
	 * @param employeeId
	 * @param request
	 * @param response
	 * @throws Exception	
	 */
	@RequestMapping("getEmployeeNameByEmployeeId")
	public void getEmployeeNameByEmployeeId(Long employeeId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(employeeId !=null && employeeId >0){
			EhrPerson person = ehrPersonService.getByPersonId(employeeId);
			if(person !=null){			
				Map<String,String> map=new HashMap<String, String>();
				map.put("employeeName", person.getEmployeeName());			
				JSONArray jsonArray=JSONArray.fromObject(map);
				response.getOutputStream().write( jsonArray.toString().getBytes("UTF-8") );
			}else{
				response.getOutputStream().write(("系统查不到该员工信息").getBytes("UTF-8"));
			}
		}else{
			response.getOutputStream().write(("请传递员工编号").getBytes("UTF-8"));
		}
	}
	/**
	 * 根据身份证号码查询员工 ID ，员工姓名
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午4:12:47 
	 * </pre>
	 *
	 * @param cardId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getEmployeeInfoByCardId")
	public void getEmployeeInfoByCardId(String cardId,HttpServletRequest request,HttpServletResponse response ) throws Exception{
		
		if(StringUtils.isNotBlank(cardId)){
			EhrPerson person = ehrPersonService.getEmployeeByCardId(cardId);
			if(person !=null){				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("employeeId", person.getPersonId());
				map.put("orgId",  person.getCompanyId()-(person.getCompanyId()*2));
				map.put("employeeName", person.getEmployeeName());
				JSONArray jsonArray=JSONArray.fromObject(map);
				response.getOutputStream().write( jsonArray.toString().getBytes("UTF-8") );
			}else{
				response.getOutputStream().write(("系统中查不到该员工的信息").getBytes("UTF-8"));
			}			
		}else{
			response.getOutputStream().write(("请传递员工身份证号").getBytes("UTF-8"));
		}
	}
	/**
	 * 根据身份证号码获取员工信息，供微信调用
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月27日 下午1:43:43 
	 * </pre>
	 *
	 * @param cardId
	 * @param request
	 * @param response  
	 * @throws Exception  
	 */
	@RequestMapping("getEmployeeByCardId")
	public void getEmployeeByCardId(String cardId,HttpServletRequest request,HttpServletResponse response )throws Exception{
		if(StringUtils.isNotBlank(cardId)){
			System.out.println("身份证号码    "+ cardId);
			Map<String,Object> map = ehrPersonService.getEmployeeInfoByCardId(cardId);
			if(map==null || map.isEmpty()){				
				response.getOutputStream().write(("系统中查不到该员工的信息").getBytes("UTF-8"));
				
			}else{
				JSONObject jsonObject= new JSONObject();
				jsonObject.put("json", map);				
				response.getOutputStream().write( jsonObject.toString().getBytes("UTF-8") );
			}			
		}else{
			response.getOutputStream().write(("请传递员工身份证号").getBytes("UTF-8"));
		}
	}

	/**
	 * 根据身份证号及月份获取社保信息
	 */
	@RequestMapping("getSocialInsurance")
	@ResponseBody
	public Map<String, Object> getSocialInsurance(HttpServletResponse response, String cardId, String month)throws Exception{
		if(StringUtils.isBlank(cardId)){
			return MessageUtil.errorProviderMessage("身份证为空");
		}
		if(StringUtils.isBlank(month)){
			return MessageUtil.errorProviderMessage("月份为空");
		}
		
		Map<String,Object> result = null;
		List<Map<String,Object>> detailList = null;

		//先从Jabava查
		Map<String,Object> map = ssPaymentBillService.queryPersonByCertIdAndMonth(cardId, month);
		if(map == null || map.isEmpty()){
			map = afPaymentBillService.queryPersonByCertIdAndMonth(cardId, month);
			if(map != null && !map.isEmpty()){
				//数据放入result
				result = MessageUtil.successProviderMessage("查询成功");
				result.put("cardId", cardId);
				result.put("employeeName", map.get("employeeName"));
				result.put("month", month);
				
				//查找明细数据
				detailList = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> list = afPaymentBillService.listPersonDetail(Long.valueOf(map.get("afPaymentBillPersonId").toString()));
				this.transferSocialInsuranceDetail(detailList, list);
			}
		}else{
			//数据放入result
			result = MessageUtil.successProviderMessage("查询成功");
			result.put("cardId", cardId);
			result.put("employeeName", map.get("employeeName"));
			result.put("month", month);
			
			//查找明细数据
			detailList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = ssPaymentBillService.listPersonDetail(Long.valueOf(map.get("ssPaymentBillPersonId").toString()));
			this.transferSocialInsuranceDetail(detailList, list);
			
			map = afPaymentBillService.queryPersonByCertIdAndMonth(cardId, month);
			if(map != null && !map.isEmpty()){
				//查找明细数据
				list = afPaymentBillService.listPersonDetail(Long.valueOf(map.get("afPaymentBillPersonId").toString()));
				this.transferSocialInsuranceDetail(detailList, list);
			}
		}
		
		if(result != null){
			result.put("listProd", detailList);
			return result;
		}
		
		//如果不存在，再从HRO查
		//{"errorMessage":"该用户不存在！","cardId":"12022319850911165"}
		//{"cardId":"120223198509111651","employeeName":"对帐信息测试13","month":"201610","sbEmpAccount":"","errorMessage":"所查信息不存在"}
		//{"cardId":"120223198509111651","employeeName":"对帐信息测试13","month":"201606","sbEmpAccount":"","errorMessage":"","listProd":[{"policyGroupId":1,"handlePartyId":14,"reportMonth":"201606","orgId":2,"prodName":"养老保险","companyBase":4000,"individualBase":4000,"companyRatio":0.2,"individualRatio":0.08,"companyAppend":0,"individualAppend":0,"companySum":800,"individualSum":320,"compensationSum":null},{"policyGroupId":1,"handlePartyId":14,"reportMonth":"201606","orgId":2,"prodName":"失业保险","companyBase":4000,"individualBase":4000,"companyRatio":0.01,"individualRatio":0.002,"companyAppend":0,"individualAppend":0,"companySum":40,"individualSum":8,"compensationSum":null},{"policyGroupId":1,"handlePartyId":14,"reportMonth":"201606","orgId":2,"prodName":"工伤保险","companyBase":4000,"individualBase":4000,"companyRatio":0.005,"individualRatio":0,"companyAppend":0,"individualAppend":0,"companySum":20,"individualSum":0,"compensationSum":null},{"policyGroupId":1,"handlePartyId":14,"reportMonth":"201606","orgId":2,"prodName":"生育保险","companyBase":4000,"individualBase":4000,"companyRatio":0.008,"individualRatio":0,"companyAppend":0,"individualAppend":0,"companySum":32,"individualSum":0,"compensationSum":null}]}
		String serverUrl = JabavaPropertyCofigurer.getProperty("SERVER_URL");
		String url = serverUrl + (serverUrl.endsWith("/") ? "" : "/") + URI_HRO_SOCIAL_INSURANCE;
		Map<String,Object> hroResult = HROFetchService.invoke(url + "?cardId=" + cardId + "&month=" + month);
		if(hroResult == null || hroResult.isEmpty()){
			return MessageUtil.errorProviderMessage("调用HRO接口失败");
		}
		
		if(hroResult.get("errorMessage") != null && StringUtils.isNotBlank(hroResult.get("errorMessage").toString())){
			return MessageUtil.errorProviderMessage(hroResult.get("errorMessage").toString());
		}
		
		result = MessageUtil.successProviderMessage("查询成功");
		result.putAll(hroResult);
		return result;
	}
	
	//将Jabava返回的结果转换为与HRO一到的结构
	private void transferSocialInsuranceDetail(List<Map<String,Object>> targetList, List<Map<String,Object>> sourceList){
		for(Map<String,Object> detail : sourceList){
			//"prodName":"养老保险","companyBase":4000,"individualBase":4000,"companyRatio":0.2,"individualRatio":0.08,"companySum":800,"individualSum":320
			Map<String,Object> d = new HashMap<String,Object>();
			d.put("prodName", detail.get("prodName"));
			d.put("companyBase", detail.get("companyBase"));
			d.put("individualBase", detail.get("individualBase"));
			d.put("companyRatio", detail.get("companyRatio"));
			d.put("individualRatio", detail.get("individualRatio"));
			d.put("companySum", detail.get("individualRatio"));
			d.put("individualSum", detail.get("individualSum"));
			targetList.add(d);
		}
	}

	/**
	 * 根据身份证号及月份获取薪资信息
	 */
	@RequestMapping("getSalaryInfo")
	@ResponseBody
	public Map<String, Object> getSalaryInfo(HttpServletResponse response, String cardId, String month)throws Exception{
		if(StringUtils.isBlank(cardId)){
			return MessageUtil.errorProviderMessage("身份证为空");
		}
		if(StringUtils.isBlank(month)){
			return MessageUtil.errorProviderMessage("月份为空");
		}
		
		Map<String,Object> result = null;

		//先从Jabava查
		Map<String,Object> map = monthlySalaryService.queryPersonByCertIdAndMonth(cardId, month);
		if(map != null && !map.isEmpty()){
			result = MessageUtil.successProviderMessage("查询成功");
			result.put("cardId", cardId);
			result.put("employeeName", map.get("employeeName"));
			result.put("salaryYm", month);
			result.put("baseSalary", map.get("taxableIncome"));
			//result.put("amountSalary", map.get("afterTaxIncome"));
			result.put("amountSalary", map.get("taxableIncome"));	//税前工资
			result.put("amountTax", map.get("withholdingTax"));
			//result.put("amountSalaried", map.get("takeHomeIncome"));
			result.put("amountSalaried", map.get("afterTaxIncome"));//税后工资
			return result;
		}

		//如果不存在，再从HRO查
		//{"errorMessage":"该用户不存在！","cardId":"45345999"}
		//{"employeeId":132700,"employeeName":"king0004","salaryYm":"201611","errorMessage":"所查信息不存在"}
		//{"employeeId":132700,"employeeName":"king0004","salaryYm":"201607","amountSb":0,"amountSbCompany":0,"fields":[],"baseSalary":70000,"amountSalary":70000,"amountTax":17770,"amountSalaried":366218.8}
		String serverUrl = JabavaPropertyCofigurer.getProperty("SERVER_URL");
		String url = serverUrl + (serverUrl.endsWith("/") ? "" : "/") + URI_HRO_SALARY_QUERY;
		Map<String,Object> hroResult = HROFetchService.invoke(url + "?cardId=" + cardId + "&month=" + month);
		if(hroResult == null || hroResult.isEmpty()){
			return MessageUtil.errorProviderMessage("调用HRO接口失败");
		}
		
		if(hroResult.get("errorMessage") != null && StringUtils.isNotBlank(hroResult.get("errorMessage").toString())){
			return MessageUtil.errorProviderMessage(hroResult.get("errorMessage").toString());
		}
		
		result = MessageUtil.successProviderMessage("查询成功");
		result.putAll(hroResult);
		return result;
	}
	
	@RequestMapping("getEmployeeList")
	@ResponseBody
	public Map<String,Object> getEmployeeList(HttpServletResponse response, String cardId)throws Exception{
		if(StringUtils.isBlank(cardId)){
			return MessageUtil.errorProviderMessage("身份证为空");
		}
		
		//先从Jabava查
		EhrPerson thePerson = ehrPersonService.getEmployeeByCardId(cardId);
		if(thePerson != null){
			Map<String,Object> result = MessageUtil.successProviderMessage("查询成功");
			
			List<Map<String, Object>> personList = new ArrayList<Map<String,Object>>();
			for(EhrPerson person : ehrPersonService.selectAllPerson(thePerson.getCompanyId())){
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("deptId", person.getOrganizationId());
				p.put("cName", person.getEmployeeName());
				p.put("deptName", person.getOrg());
				p.put("userId", null);	//暂未取
				p.put("userName", person.getEmployeeName());
				p.put("mobile", person.getMobile());
				p.put("email", person.getEmail());
				p.put("phone", person.getPhone());
				p.put("birthday", person.getBirthDate() == null ? "" : JabavaDateUtils.formatDate(person.getBirthDate(), "yyyy-MM-dd"));
				p.put("gender", person.getSex() == null ? "" : person.getSex().toString());
				p.put("residenceAddress", person.getFamilyAddress());
				personList.add(p);
			}
			
			Map<String,Object> resultData = new HashMap<String,Object>();
			resultData.put("entities", personList);
			result.put("resultData", resultData);
			return result;
		}
		
		//如果不存在，再从HRO查
		return this.getEmployeeListFromHro(cardId);
	}
	
	private Map<String,Object> getEmployeeListFromHro(String cardId) throws Exception{
		Map<String,Object> entity = new HashMap<String,Object>();
		//{"type":"","data":{"page":{"pageSize":"5"},"parameter":{"code":"empContactList",cardId: xx}}}
		Map<String,Object> page = new HashMap<String,Object>();
		page.put("pageSize", Constants.MAX_RECORD_SIZE);
		entity.put("page", page);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", "empContactList");
		param.put("cardId", cardId);
		entity.put("parameter", param);
		Map<String, Object> hroResult = hroFetchService.invoke("SqlQuery", entity);
//		if (!"0".equals(hroResult.get("resultCode").toString())) {
//			return MessageUtil.errorMessage("调用HRO接口失败: " + hroResult.get("resultMessage"));
//		}
//		
//		//处理返回数据
//		Map<String, Object> data = (Map<String, Object>)hroResult.get("resultData");
//		if(data == null || data.isEmpty()){
//			return MessageUtil.errorMessage("HRO返回数据为空");
//		}
//		
//		Map<String, Object> result = MessageUtil.successMessage("HRO返回成功");
//		result.putAll(data);
		
//		{
//	    "resultCode" : 0,
//	    "resultMessage" : null,
//	    "resultData" : {
//	      "pageSize" : 999999,
//	      "pageNo" : 1,
//	      "firstEntityIndex" : 0,
//	      "lastEntityIndex" : 999999,
//	      "entities" : [ {
//	        "deptId" : 0,
//	        "cName" : "张丽丽",
//	        "deptName" : "",
//	        "userId" : 81,
//	        "userName" : "张丽丽",
//	        "mobile" : "13446436547",
//	        "email" : null,
//	        "phone" : null,
//	        "birthday" : "1977-01-10",
//	        "gender" : "2",
//	        "residenceAddress" : "13601202167"
//	      }],
//	      "entityCount" : 757,
//	      "pageCount" : 1
//	    }
//	  }
		
		return hroResult;
	}
}
