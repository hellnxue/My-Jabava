package com.jabava.controller.socialsecurity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccount;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccountVO;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.pojo.tools.EhrProvince;
import com.jabava.service.policygroup.GroupService;
import com.jabava.service.socialsecurity.SsSocialSecurityAccountService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.service.tools.EhrCityService;
import com.jabava.service.tools.EhrProvinceService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

/**
 * 社保账户管理
 *
 * @version $Id: SsSocialSecurityAccountController.java, 
 * v 0.1 2016年4月13日 下午3:09:55 
 * <pre>
 * @author steven.chen
 * @date 2016年4月13日 下午3:09:55 
 * </pre>
 */
@Controller
@RequestMapping("/socialsecurityaccount")
public class SsSocialSecurityAccountController {
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Autowired
	private SsSocialSecurityAccountService socialSecurityAccountService;
	@Autowired
	private EhrProvinceService  ehrProvinceService;	
	@Autowired
	private EhrCityService  ehrCityService;
	@Autowired
	private GroupService  groupService;
	
	@RequestMapping("socialSecurityAccountMain")
	public String socialSecurityAccountMain(){
		return "/socialsecurity/listAccounts";
	}
	
	/**
	 * 社保账户列表
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
	@RequestMapping("/findSocialSecurityAccountPage")
	@ResponseBody
	public Page<SsSocialSecurityAccountVO> findSocialSecurityAccountPage(HttpServletRequest request,String date,String type,int start,int length){
		
		Page<SsSocialSecurityAccountVO> page = new Page<SsSocialSecurityAccountVO>(start, length);
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
			List<SbGroup>  groupList= groupService.getGroupByName(search);
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
		List<SsSocialSecurityAccountVO> socialSecurityAccount =  socialSecurityAccountService.findSocialSecurityAccountPage(map);
		
			//sysLogSercice.addSysLog(ehrUser, SystemEnum.LogOperateType.Select, SystemEnum.Module.Ssaf, "查询社保信息");
	
		
		page.setData(socialSecurityAccount);
		return page;
	}
	
	@RequestMapping("listSocialSecurityAccount")
	@ResponseBody
	public List<SsSocialSecurityAccountVO> listSocialSecurityAccount(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		return socialSecurityAccountService.findSocialSecurityAccountByCompanyId(user.getCompanyId());
	}
	
	@RequestMapping("/findPersonListPage")
	@ResponseBody
	public Page<SsSocialSecurityAccountVO> findPersonListPage(HttpServletRequest request,Long accountId,String date,String type,int start,int length){
		
		Page<SsSocialSecurityAccountVO> page = new Page<SsSocialSecurityAccountVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();	
		map.put("page", page);
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());	
		map.put("accountId", accountId);
		List<SsSocialSecurityAccountVO> socialSecurityAccount = socialSecurityAccountService.findPersonListPage(map);
		page.setData(socialSecurityAccount);
		return page;
	}
	
	/**
	 * 新增社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:48:52 
	 * </pre>
	 *
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addSocialSecurityAccount")
	@ResponseBody
	public String addSocialSecurityAccount( HttpServletRequest request,SsSocialSecurityAccount ssSocialSecurityAccount) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);				
		ssSocialSecurityAccount.setCreateDate(new Date());
		ssSocialSecurityAccount.setCreateUserId(user.getUserId());	
		ssSocialSecurityAccount.setCompanyId(user.getCompanyId());
		String result = socialSecurityAccountService.addSocialSecurityAccount(ssSocialSecurityAccount);
		if(result.equals("success")){
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Ssaf,"添加一个名为"+ssSocialSecurityAccount.getSocialSecurityAccountName()+"的社保账户");
		}
	
		
		return result;

	}
	/**
	 * 根据ID获取社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午4:00:05 
	 * </pre>
	 *
	 * @param socialSecurityAccountId
	 * @return
	 */
	@RequestMapping("getSsSocialSecurityAccountById")
	@ResponseBody
	public SsSocialSecurityAccountVO getSsSocialSecurityAccountById( HttpServletRequest request,SsSocialSecurityAccount ssSocialSecurityAccount){
		EhrUser user = RequestUtil.getLoginUser(request);	
		ssSocialSecurityAccount.setCompanyId(user.getCompanyId());		
		return socialSecurityAccountService.getSocialSecurityAccountById(ssSocialSecurityAccount);
	}
	/**
	 * 更新社保账户信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午4:00:22 
	 * </pre>
	 *
	 * @param ssSocialSecurityAccount
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateSocialSecurityAccount")
	@ResponseBody
	public String updateSocialSecurityAccount( HttpServletRequest request,SsSocialSecurityAccount ssSocialSecurityAccount) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);				
		ssSocialSecurityAccount.setUpdateDate(new Date());
		ssSocialSecurityAccount.setUpdateUserId(user.getUserId());	
		ssSocialSecurityAccount.setCompanyId(user.getCompanyId());
		String result = socialSecurityAccountService.updateSocialSecurityAccount(ssSocialSecurityAccount);
		if(result.equals("success")){
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "修改id为"+ssSocialSecurityAccount.getSocialSecurityAccountId()+"社保信息");
		}
		return result ;
		
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
		return "ss_social_security_account.update_date";
	}
	/**
	 * 删除社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午4:07:18 
	 * </pre>
	 *
	 * @param socialSecurityAccountId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("delSocialSecurityAccount")
	@ResponseBody
	public String delSocialSecurityAccount(Long socialSecurityAccountId) throws Exception{
		String result = socialSecurityAccountService.delSocialSecurityAccount(socialSecurityAccountId);
		if(result.equals("success")){
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), 
					SystemEnum.LogOperateType.Delete, SystemEnum.Module.Ssaf, "删除了 id为"+socialSecurityAccountId+"的社保账户");
		}
		
		return result;
	}

}
