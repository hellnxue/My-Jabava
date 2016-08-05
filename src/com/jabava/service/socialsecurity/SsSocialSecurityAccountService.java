package com.jabava.service.socialsecurity;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccount;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccountVO;

public interface SsSocialSecurityAccountService {
	
	/**
	 * 分页查询社保账户列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午8:08:12 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<SsSocialSecurityAccountVO> findSocialSecurityAccountPage(
			Map<String, Object> map);
	

	/**
	 * 根据Id获取社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:59:02 
	 * </pre>
	 *
	 * @param socialSecurityAccountId
	 * @return
	 */
	public SsSocialSecurityAccountVO getSocialSecurityAccountById(SsSocialSecurityAccount ssSocialSecurityAccount);
	/**
	 * 新增社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:46:19 
	 * </pre>
	 *
	 * @param ssSocialSecurityAccount
	 * @return
	 */
	public String addSocialSecurityAccount(SsSocialSecurityAccount ssSocialSecurityAccount);

	/**
	 * 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:54:09 
	 * </pre>
	 *
	 * @param ssSocialSecurityAccount
	 * @return
	 */
	public String updateSocialSecurityAccount(SsSocialSecurityAccount ssSocialSecurityAccount);
	/**
	 * 根据ID删除社保账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午4:04:04 
	 * </pre>
	 *
	 * @param socialSecurityAccountId
	 * @return
	 */
	String  delSocialSecurityAccount(Long socialSecurityAccountId);

/**
 * 根据公司Id查询
 * <pre>
 * @author steven.chen
 * @date 2016年4月19日 下午5:45:10 
 * </pre>
 *
 * @param companyId
 * @return
 */
	List<SsSocialSecurityAccountVO> findSocialSecurityAccountByCompanyId(
			Long companyId);

	/**
	 * 根据账号ID查询人员列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午8:16:36 
	 * </pre>
	 *
	 * @param accountId
	 * @return
	 */
	List<SsSocialSecurityAccountVO> findPersonListPage(Map<String, Object> map);
	
	/**
	 * 根据公司代码查询社保账户列表
	 * @param companyId
	 * @return
	 */
	public List <SsSocialSecurityAccount> getSecurityAccountByCompanyId(Long companyId);
	
	/**
	 * 工具社保账号代码查询相关的社保规则列表
	 * @param accountId
	 * @return
	 */
	public List <SbGroup> getSecurityRulesByAccount(Long accountId);
	
	public List<Map<String,Object>> getSecurityGroupDetail(Long groupId,Long groupType);
}
