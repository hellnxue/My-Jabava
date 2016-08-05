package com.jabava.service.socialsecurity.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.policygroup.SbGroupMapper;
import com.jabava.dao.socialsecurity.SsSocialSecurityAccountMapper;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccount;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccountVO;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.service.socialsecurity.EhrPersonSecurityProfileService;
import com.jabava.service.socialsecurity.SsSocialSecurityAccountService;
import com.jabava.service.tools.EhrCityService;
import com.jabava.service.tools.EhrProvinceService;
/**
 * 社保账户管理
 *
 * @version $Id: SsSocialSecurityAccountImpl.java, 
 * v 0.1 2016年4月13日 下午3:06:17 
 * <pre>
 * @author steven.chen
 * @date 2016年4月13日 下午3:06:17 
 * </pre>
 */
@Service
public class SsSocialSecurityAccountServiceImpl implements SsSocialSecurityAccountService {
	public static Logger log = Logger.getLogger(SsSocialSecurityAccountServiceImpl.class);

	@Autowired
	private SsSocialSecurityAccountMapper socialSecurityAccountMapper;	
	@Autowired
	private  SbGroupMapper groupMapper;
	@Autowired
	private EhrProvinceService ehrProvinceService;
	@Autowired
	private EhrCityService ehrCityService;
	@Autowired
	private EhrPersonSecurityProfileService personSecurityProfileService;	
	
	@Override
	public List<SsSocialSecurityAccountVO> findSocialSecurityAccountPage(Map<String, Object> map){
		List<SsSocialSecurityAccountVO> socialSecurityAccountList= socialSecurityAccountMapper.findSocialSecurityAccountPage(map);
		if(socialSecurityAccountList!=null && socialSecurityAccountList.size()>0){
			for(SsSocialSecurityAccountVO socialSecurityAccountVO:socialSecurityAccountList){
				if(socialSecurityAccountVO!=null && StringUtils.isNotBlank(socialSecurityAccountVO.getSocialSecurityRule())){
					String str = socialSecurityAccountVO.getSocialSecurityRule();
					String[] s = str.split(",");
					List<Long> list = new ArrayList<Long>();
					//获取参保类型
					for(int i=0;i<s.length;i++){
						list.add(Long.valueOf(s[i]));						
					}
					String groupNamePlus = groupMapper.getGroupNamePlus(list);
					if(StringUtils.isNotBlank(groupNamePlus)){
						socialSecurityAccountVO.setSocialSecurityRuleShow(groupNamePlus);
					}
					list.clear();
				}
			}
			
		}
		
		return socialSecurityAccountList;
	}
	@Override
	public List<SsSocialSecurityAccountVO> findPersonListPage(Map<String, Object> map){
		return socialSecurityAccountMapper.findPersonListPage(map);
	}
	@Override
	public List<SsSocialSecurityAccountVO> findSocialSecurityAccountByCompanyId(Long companyId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		List<SsSocialSecurityAccountVO> socialSecurityAccountList= socialSecurityAccountMapper.findSocialSecurityAccountPage(map);
		if(socialSecurityAccountList!=null && socialSecurityAccountList.size()>0){
			for(SsSocialSecurityAccountVO socialSecurityAccountVO:socialSecurityAccountList){
				if(socialSecurityAccountVO!=null && StringUtils.isNotBlank(socialSecurityAccountVO.getSocialSecurityRule())){
					String str = socialSecurityAccountVO.getSocialSecurityRule();
					String[] s = str.split(",");
					List<Long> list = new ArrayList<Long>();
					//获取参保类型
					for(int i=0;i<s.length;i++){
						list.add(Long.valueOf(s[i]));						
					}
					String groupNamePlus = groupMapper.getGroupNamePlus(list);
					if(StringUtils.isNotBlank(groupNamePlus)){
						socialSecurityAccountVO.setSocialSecurityRuleShow(groupNamePlus);
					}
					list.clear();
				}
			}
			
		}
		
		return socialSecurityAccountList;
	}  
	@Override
	public String addSocialSecurityAccount(
			SsSocialSecurityAccount ssSocialSecurityAccount) {
		if(checkAccount(null,ssSocialSecurityAccount.getSocialSecurityCode())){
			return "编码已经存在";
		}
		if( socialSecurityAccountMapper.insertSelective(ssSocialSecurityAccount)==1){
			return "success";
		}else {
			return "error";
		}
	}
	@Override
	public String updateSocialSecurityAccount(
			SsSocialSecurityAccount ssSocialSecurityAccount) {
		if(checkAccount(ssSocialSecurityAccount.getSocialSecurityAccountId(),ssSocialSecurityAccount.getSocialSecurityCode())){
			return "编码已经存在";
		}
		if( socialSecurityAccountMapper.updateByPrimaryKeySelective(ssSocialSecurityAccount)==1){
			return "success";
		}else {
			return "error";
		}
	}
	@Override
	public SsSocialSecurityAccountVO getSocialSecurityAccountById(SsSocialSecurityAccount ssSocialSecurityAccount) {

	    SsSocialSecurityAccountVO socialSecurityAccountVO= socialSecurityAccountMapper.getSocialSecurityAccountById(ssSocialSecurityAccount);
	   	if(socialSecurityAccountVO!=null ){	
			    List<Long> list = new ArrayList<Long>();
				if(socialSecurityAccountVO!=null && StringUtils.isNotBlank(socialSecurityAccountVO.getSocialSecurityRule())){
					String str = socialSecurityAccountVO.getSocialSecurityRule();
					String[] s = str.split(",");
					//获取参保类型
					for(int i=0;i<s.length;i++){
						list.add(Long.valueOf(s[i]));						
					}
					if(list!=null && list.size()>0){
						List<SbGroup> groupList = groupMapper.getGroupListByIdList(list);
						if(groupList!=null && groupList.size()>0){
							socialSecurityAccountVO.setGroupList(groupList);						
						}
						String groupNamePlus = groupMapper.getGroupNamePlus(list);
						if(StringUtils.isNotBlank(groupNamePlus)){
							socialSecurityAccountVO.setSocialSecurityRuleShow(groupNamePlus);
						}
						list.clear();
					}
					
				}
			EhrCity city = ehrCityService.getCitiByCityId(socialSecurityAccountVO.getLocationCityId());
			//省份ID
			if(city!=null &&  city.getProvinceId()!=null){
				socialSecurityAccountVO.setCity(city);
				socialSecurityAccountVO.setProvinceId(city.getProvinceId());
				List<EhrCity> cityList = ehrCityService.getCityListByProvinceId(city.getProvinceId());
				//该省份的城市列表
				if(cityList!=null && cityList.size()>0){
					socialSecurityAccountVO.setCityList(cityList);
					
				}
			}
			
		}
		
		return socialSecurityAccountVO;
	}
	/**
	 * 如果编码已经存在 返回 true
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午4:39:29 
	 * </pre>
	 *
	 * @param ssSocialSecurityAccount
	 * @return
	 */
	private boolean checkAccount(Long socialSecurityAccountId,String code){
		
		List<SsSocialSecurityAccount> accountList = socialSecurityAccountMapper.findSocialSecurityByCode(code);
		if(accountList!=null && accountList.size()>0){
			//如果只有一条，并且 是该公司下的，返回false  (修改的时候回用到这个)
			if(socialSecurityAccountId!=null && accountList.size()==1 && accountList.get(0).getSocialSecurityAccountId()==socialSecurityAccountId){
				return false;
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public String delSocialSecurityAccount(Long socialSecurityAccountId) {
		List<EhrPersonSecurityProfile>  securityProfileList = personSecurityProfileService.getSecurityProfileByOrgAccount(socialSecurityAccountId, null);
		if(securityProfileList==null ||securityProfileList.size()>0){
			return "该社保账户下已经有在缴状态的社保档案";
		}
		if(socialSecurityAccountMapper.deleteById(socialSecurityAccountId)==1){
			return "success";
		}else{
			return "error";
		}
	}
	
	/**
	 * 根据公司代码查询社保账户列表 
	 * @param companyId
	 * @return
	 */
	public List <SsSocialSecurityAccount> getSecurityAccountByCompanyId(Long companyId){
		return socialSecurityAccountMapper.getSecurityAccountByCompanyId(companyId);
	}
	
	/**
	 * 
	 * @param accountId
	 * @return
	 */
	public List<SbGroup> getSecurityRulesByAccount(Long accountId){
		String rules = socialSecurityAccountMapper.getSocialSecurityRules(accountId);
		String[] arr = rules.split(",");
		
		List<SbGroup> list = socialSecurityAccountMapper.getSocialSecurityRulesDetail(arr);
		
		return list;
	}
	
	/**
	 * 取得社保项目明细信息
	 * @param aaa
	 * @param bbb
	 * @return
	 */
	public List<Map<String,Object>> getSecurityGroupDetail(Long groupId,Long groupType){
		List<Map<String,Object>> ret = socialSecurityAccountMapper.getSecurityGroupDetail(groupId, groupType);
		return ret;
	}
}
