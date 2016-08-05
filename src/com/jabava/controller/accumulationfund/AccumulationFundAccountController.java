package com.jabava.controller.accumulationfund;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.accumulationfund.AfAccumulationFundAccount;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccountVO;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.pojo.tools.EhrProvince;
import com.jabava.service.accumulationfund.AccumulationFundAccountService;
import com.jabava.service.policygroup.GroupService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.service.tools.EhrCityService;
import com.jabava.service.tools.EhrProvinceService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;
/**
 * 公积金账户管理
 *
 * @version $Id: AccumulationFundAccountController.java, 
 * v 0.1 2016年4月13日 下午3:07:52 
 * <pre>
 * @author steven.chen
 * @date 2016年4月13日 下午3:07:52 
 * </pre>
 */
@Controller
@RequestMapping("/accumulationfundaccount")
public class AccumulationFundAccountController {
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Autowired
	private AccumulationFundAccountService accumulationFundAccountService;
	@Autowired
	private EhrProvinceService  ehrProvinceService;	
	@Autowired
	private EhrCityService  ehrCityService;
	@Autowired
	private GroupService  groupService;
	
	@RequestMapping("accumulationfundaccountMain")
	public String accumulationfundaccountMain(){
		return "/fundAccounts/listFundAccounts";
	}
	
	/**
	 * 公积金账户列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午8:11:28 
	 * </pre>
	 *
	 * @param request
	 * @param date
	 * @param type
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("findAccumulationFundAccountPage")
	@ResponseBody
	public Page<AfAccumulationFundAccountVO> findAccumulationFundAccountPage(HttpServletRequest request,String date,String type,int start,int length){
		
		Page<AfAccumulationFundAccountVO> page = new Page<AfAccumulationFundAccountVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());	
		String search = request.getParameter("search[value]");// search框的值		
		String order = request.getParameter("order[0][column]");//排序列的下标
		if(StringUtils.isNotBlank(order)){
			map.put("orderBy", "send_time DESC");			
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			if(StringUtils.isBlank(according)){
				according="DESC";
			}
			order = getOrderListColumnName(order);
			map.put("orderBy", order + " " + according);
			
		}
		//搜索条件
		if(StringUtils.isNotBlank(search)){
			map.put("search", search);
			List<SbGroup>  groupList= groupService.getGroupByName(search.trim());
			List<String> ruleList = new ArrayList<String>();
			if(groupList!=null && groupList.size()>0){
				for(SbGroup group:groupList){
					ruleList.add(group.getId().toString());
				}
				//参保规则
				map.put("ruleList", ruleList);
			}
			List<EhrCity> cityList = ehrCityService.getCitiByCityName(search);
			List<Long> cityIdList = new ArrayList<Long>();
			if(cityList!=null && cityList.size()>0){
				for(EhrCity ehrCity:cityList){
					cityIdList.add(ehrCity.getCityId());
				}
				//参保地
				map.put("cityIdList", cityIdList);
			}
		}
		
		map.put("page", page);
		List<AfAccumulationFundAccountVO> accumulationFundAccountVO =  accumulationFundAccountService.findAccumulationFundAccountPage(map);
		
		page.setData(accumulationFundAccountVO);
		
		return page;
		
	}
	
	@RequestMapping("listAccumulationFundAccount")
	@ResponseBody
	public List<AfAccumulationFundAccount> listAccumulationFundAccount(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		//查询所有公积金账户
		return accumulationFundAccountService.getAccumulationFundProfileByCompanyId(user.getCompanyId());
	}
	
	/**
	 * 根据账户ID获取人员账户列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午3:30:02 
	 * </pre>
	 *
	 * @param request
	 * @param date
	 * @param type
	 * @param start
	 * @param length
	 * @param accumulationFundAccountId
	 * @return
	 */
	@RequestMapping("/findPersonListPage")
	@ResponseBody
	public Page<AfAccumulationFundAccountVO>  findPersonListPage(HttpServletRequest request,String date,String type,int start,int length,Long accountId){
		Page<AfAccumulationFundAccountVO> page = new Page<AfAccumulationFundAccountVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());	
		map.put("page", page);
		map.put("accountId", accountId);
		List<AfAccumulationFundAccountVO> accumulationFundAccountVO =  accumulationFundAccountService.findPersonListPage(map);
		
		page.setData(accumulationFundAccountVO);
		return page;
		
	}
	/**
	 * 新增公积金账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:48:52 
	 * </pre>
	 *
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addAccumulationFundAccount")
	@ResponseBody
	public String addAccumulationFundAccount( HttpServletRequest request,AfAccumulationFundAccount afAccumulationFundAccount) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);				
		afAccumulationFundAccount.setCreateDate(new Date());
		afAccumulationFundAccount.setCreateUserId(user.getUserId());	
		afAccumulationFundAccount.setCompanyId(user.getCompanyId());
		String result = accumulationFundAccountService.addAfAccumulationFundAccount(afAccumulationFundAccount);
		if(result.equals("success")){
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Ssaf, "新增一个名为"+afAccumulationFundAccount.getAccumulationFundAccountName()+"的公积金账户");
		}
		return result;
		

	}
	@RequestMapping("delAccumulationFundAccount")
	@ResponseBody
	
	public String delAccumulationFundAccount(Long accumulationFundAccountId) throws Exception{
		
		String result = accumulationFundAccountService.delAccumulationFundAccount(accumulationFundAccountId);
		if(result.equals("success")){
			
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Delete, SystemEnum.Module.Ssaf, "删除一个id为"+accumulationFundAccountId+"的公积金账户");
		}
		return result;
	}
	/**
	 * 根据ID获取公积金账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午4:00:05 
	 * </pre>
	 *
	 * @param socialSecurityAccountId
	 * @return
	 */
	@RequestMapping("getAccumulationFundAccountById")
	@ResponseBody
	public AfAccumulationFundAccountVO getAccumulationFundAccountById(Long accumulationFundAccountId){
		return accumulationFundAccountService.getAfAccumulationFundAccountById(accumulationFundAccountId);
	}

	/**
	 * 更新社保账户信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午4:00:22 
	 * </pre>
	 *
	 * @param afAccumulationFundAccount
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateSocialSecurityAccount")
	@ResponseBody
	public String updateAccumulationFundAccount( HttpServletRequest request,AfAccumulationFundAccount afAccumulationFundAccount) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);				
		afAccumulationFundAccount.setUpdateDate(new Date());
		afAccumulationFundAccount.setUpdateUserId(user.getUserId());	
		afAccumulationFundAccount.setCompanyId(user.getCompanyId());
		String result = accumulationFundAccountService.updateAfAccumulationFundAccount(afAccumulationFundAccount);
		if(result.equals("success")){
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "修改id为"+afAccumulationFundAccount.getAccumulationFundAccountId()+"的公积金账户");
		}
		return result;
		
	}
	/**
	 * 获取省份
	 */
	@RequestMapping("getProvinceInfo")
	@ResponseBody
	public List<EhrProvince> getProvinceInfo(){
		
	    return ehrProvinceService.getProvinceList();
	   
	}
	
	/**
	 * 根据省份获取城市列表
	 */
	@RequestMapping("getCityByProvinceId")
	@ResponseBody
	public List<EhrCity> getCityByProvinceId( Long provinceId){
	    
	    return ehrCityService.getCityListByProvinceId(provinceId);
		
	}
	
	/**
	 * 根据城市和类型查询政策包列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午4:11:11 
	 * </pre>
	 *
	 * @param policyGroup
	 * @return
	 */
	@RequestMapping("getGroupByCityAndType")
	@ResponseBody
	public List<SbGroup> getGroupByCityAndType(PolicyGroup policyGroup){
		return groupService.getGroupByCityAndType(policyGroup);
	}
	//列表查询的排序
	public static String getOrderListColumnName(String order){
		if(StringUtils.isNotBlank(order)){
			if(order.equals("socialSecurityAccountName")){
				return "social_security_account_name";
				
			}else if("socialSecurityCode".equals(order)){
				return "social_security_code";
				
			}else if("city.cityName".equals(order)){
				return "location_city_id";
				
			}else if("socialSecurityRuleShow".equals(order)){
				return "social_security_rule";
			}
		}
		return "update_date";
	}



}
