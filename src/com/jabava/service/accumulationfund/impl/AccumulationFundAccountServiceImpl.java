package com.jabava.service.accumulationfund.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.accumulationfund.AfAccumulationFundAccountMapper;
import com.jabava.dao.policygroup.SbGroupMapper;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccount;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccountVO;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.service.accumulationfund.AccumulationFundAccountService;
import com.jabava.service.socialsecurity.EhrPersonSecurityProfileService;
import com.jabava.service.tools.EhrCityService;
import com.jabava.service.tools.EhrProvinceService;

/**
 * 公积金
 *
 * @version $Id: AccumulationFundAccountServiceImpl.java, 
 * v 0.1 2016年4月13日 下午3:02:58 
 * <pre>
 * @author steven.chen
 * @date 2016年4月13日 下午3:02:58 
 * </pre>
 */
@Service
public class AccumulationFundAccountServiceImpl implements AccumulationFundAccountService {
	
	@Autowired
	private AfAccumulationFundAccountMapper accumulationFundAccountMapper;
	@Autowired
	private  SbGroupMapper groupMapper;
	@Autowired
	private EhrProvinceService ehrProvinceService;
	@Autowired
	private EhrCityService ehrCityService;
	@Autowired
	private EhrPersonSecurityProfileService personSecurityProfileService;	
	@Override
	public List<AfAccumulationFundAccountVO> findAccumulationFundAccountPage(
			Map<String, Object> map) {

		List<AfAccumulationFundAccountVO> accumulationFundAccountList= accumulationFundAccountMapper.findAccumulationFundAccountPage(map);
				
		if(accumulationFundAccountList!=null && accumulationFundAccountList.size()>0){
			for(AfAccumulationFundAccountVO accumulationFundAccountVO:accumulationFundAccountList){
				if(accumulationFundAccountVO!=null && StringUtils.isNotBlank(accumulationFundAccountVO.getAccumulationFundRule())){
					String str = accumulationFundAccountVO.getAccumulationFundRule();
					String[] s = str.split(",");
					List<Long> list = new ArrayList<Long>();
					//获取参保类型
					for(int i=0;i<s.length;i++){
						list.add(Long.valueOf(s[i]));						
					}
					String groupNamePlus = groupMapper.getGroupNamePlus(list);
					if(StringUtils.isNotBlank(groupNamePlus)){
						accumulationFundAccountVO.setAccumulationFundRuleShow(groupNamePlus);
					}
					list.clear();
				}
			}
			
		}
		
		return accumulationFundAccountList;
	
	}

	@Override
	public AfAccumulationFundAccountVO getAfAccumulationFundAccountById(
			Long accumulationFundAccountId) {
		AfAccumulationFundAccountVO accumulationFundAccountVO= accumulationFundAccountMapper.selectByPrimaryKey(accumulationFundAccountId);
		if(accumulationFundAccountVO!=null ){			
				if(accumulationFundAccountVO!=null && StringUtils.isNotBlank(accumulationFundAccountVO.getAccumulationFundRule())){
					String str = accumulationFundAccountVO.getAccumulationFundRule();
					String[] s = str.split(",");
					List<Long> list = new ArrayList<Long>();
					//获取参保类型
					for(int i=0;i<s.length;i++){
						list.add(Long.valueOf(s[i]));						
					}
					if(list!=null && list.size()>0){
						List<SbGroup> groupList = groupMapper.getGroupListByIdList(list);
						if(groupList!=null && groupList.size()>0){
							accumulationFundAccountVO.setGroupList(groupList);						
						}
						String groupNamePlus = groupMapper.getGroupNamePlus(list);
						if(StringUtils.isNotBlank(groupNamePlus)){
							accumulationFundAccountVO.setAccumulationFundRuleShow(groupNamePlus);
						}
						list.clear();
					}
					
				}
			EhrCity city = ehrCityService.getCitiByCityId(accumulationFundAccountVO.getLocationCityId());
			//省份ID
			if(city!=null &&  city.getProvinceId()!=null){
				accumulationFundAccountVO.setCity(city);
				accumulationFundAccountVO.setProvinceId(city.getProvinceId());
				List<EhrCity> cityList = ehrCityService.getCityListByProvinceId(city.getProvinceId());
				//该省份的城市列表
				if(cityList!=null && cityList.size()>0){
					accumulationFundAccountVO.setCityList(cityList);
					
				}
			}
			
		}
		
		return accumulationFundAccountVO;
	
	}

	@Override
	public String addAfAccumulationFundAccount(
			AfAccumulationFundAccount sfAccumulationFundAccount) {

		if(checkAccount(null,sfAccumulationFundAccount.getAccumulationFundCode())){
			return "编码已经存在";
		}
		if( accumulationFundAccountMapper.insertSelective(sfAccumulationFundAccount)==1){
			return "success";
		}else {
			return "error";
		}
	
	}

	@Override
	public String updateAfAccumulationFundAccount(
			AfAccumulationFundAccount sfAccumulationFundAccount) {
		if(checkAccount(sfAccumulationFundAccount.getAccumulationFundAccountId(),sfAccumulationFundAccount.getAccumulationFundCode())){
			return "编码已经存在";
		}
		if( accumulationFundAccountMapper.updateByPrimaryKeySelective(sfAccumulationFundAccount)==1){
			return "success";
		}else {
			return "error";
		}
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
	private boolean checkAccount(Long accumulationFundAccountId,String code){
	
		List<AfAccumulationFundAccount> accountList = accumulationFundAccountMapper.findAccumulationFundByCode(code);		
		if(accountList!=null && accountList.size()>0){
			//如果只有一条，并且 是该公司下的，返回false  (修改的时候回用到这个)
			if(accumulationFundAccountId!=null && accountList.size()==1 && accountList.get(0).getAccumulationFundAccountId()==accumulationFundAccountId){
				return false;
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<AfAccumulationFundAccountVO> findPersonListPage(
			Map<String, Object> map) {
		return accumulationFundAccountMapper.findPersonListPage(map);

	}

	@Override
	public String delAccumulationFundAccount(Long accumulationFundAccountId) {
		List<EhrPersonSecurityProfile>  securityProfileList = personSecurityProfileService.getSecurityProfileByOrgAccount(null, accumulationFundAccountId);
		if(securityProfileList==null ||securityProfileList.size()>0){
			return "该公积金账户下已经有在缴状态的社保档案";
		}
		if(accumulationFundAccountMapper.deleteById(accumulationFundAccountId)==1){
			return "success";
		}else{
			return "error";
		}
	}
	@Override
	public List<SbGroup> getFundAccountRulesByAccount(Long accountId){
	
		AfAccumulationFundAccountVO accumulationFundAccountVO= accumulationFundAccountMapper.selectByPrimaryKey(accountId);
		if(accumulationFundAccountVO!=null){			
			if(accumulationFundAccountVO!=null && StringUtils.isNotBlank(accumulationFundAccountVO.getAccumulationFundRule())){
				String str = accumulationFundAccountVO.getAccumulationFundRule();
				String[] s = str.split(",");
				List<Long> list = new ArrayList<Long>();
				//获取参保类型
				for(int i=0;i<s.length;i++){
					list.add(Long.valueOf(s[i]));						
				}
				if(list!=null && list.size()>0){
					List<SbGroup> groupList = groupMapper.getGroupListByIdList(list);
					if(groupList!=null && groupList.size()>0){
								return 		groupList;
					}
					
				}
				
			}
	
		}
		return null;
		
	}
	/**
	 * 根据公司代码查询公积金账户
	 */
	public List <AfAccumulationFundAccount> getAccumulationFundProfileByCompanyId(Long companyId){
		return accumulationFundAccountMapper.getAccumulationFundProfileByCompanyId(companyId);
	}

}
