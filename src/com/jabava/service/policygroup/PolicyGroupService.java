package com.jabava.service.policygroup;

import java.util.List;

import com.jabava.pojo.policygroup.PolicyGroup;

public interface PolicyGroupService {
	
	/**
	 * 根据城市和类型获取政策包
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午4:03:15 
	 * </pre>
	 *
	 * @param policyGroup
	 * @return
	 */
	public List<PolicyGroup> getPolicyGroupByCityAndType(PolicyGroup policyGroup);
	/**
	 * 根据名字查询政策包
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月15日 上午10:54:50 
	 * </pre>
	 *
	 * @param policyGroupName
	 * @return
	 */
	public List<PolicyGroup> getPolicyGroupByName(String policyGroupName);

}
