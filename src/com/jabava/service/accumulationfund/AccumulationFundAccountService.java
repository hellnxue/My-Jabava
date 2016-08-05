package com.jabava.service.accumulationfund;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.accumulationfund.AfAccumulationFundAccount;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccountVO;
import com.jabava.pojo.policygroup.SbGroup;

public interface AccumulationFundAccountService {
	

	
	/**
	 * 分页查询公积金账户列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午8:08:12 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<AfAccumulationFundAccountVO> findAccumulationFundAccountPage(
			Map<String, Object> map);
	/**
	 * 根据账户ID分页查询人员列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午3:42:25 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<AfAccumulationFundAccountVO>  findPersonListPage(Map<String, Object> map);
	
	
	/**
	 * 根据Id获取公积金账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:59:02 
	 * </pre>
	 *
	 * @param accumulationFundAccountId
	 * @return
	 */
	public AfAccumulationFundAccountVO getAfAccumulationFundAccountById(Long accumulationFundAccountId);
	/**
	 * 新增公积金账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:46:19 
	 * </pre>
	 *
	 * @param sfAccumulationFundAccount
	 * @return
	 */
	public String addAfAccumulationFundAccount(AfAccumulationFundAccount sfAccumulationFundAccount);

	/**
	 * 更新公积金账户信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月13日 下午3:54:09 
	 * </pre>
	 *
	 * @param sfAccumulationFundAccount
	 * @return
	 */
	public String updateAfAccumulationFundAccount(AfAccumulationFundAccount sfAccumulationFundAccount);
	/**
	 * 删除公积金账户
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午3:56:48 
	 * </pre>
	 *
	 * @param accumulationFundAccountId
	 * @return
	 */
	String delAccumulationFundAccount(Long accumulationFundAccountId);
	
	/**
	 * 根据公司代码查询公积金账户
	 * @param companyId
	 * @return
	 */
	List <AfAccumulationFundAccount> getAccumulationFundProfileByCompanyId(Long companyId);
	/**
	 * 根据公积金账号获取参保规则列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月27日 下午9:23:48 
	 * </pre>
	 *
	 * @param accountId
	 * @return
	 */
	List<SbGroup> getFundAccountRulesByAccount(Long accountId);
}
