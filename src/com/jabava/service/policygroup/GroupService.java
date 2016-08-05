package com.jabava.service.policygroup;

import java.util.List;

import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.pojo.policygroup.SbGroup;

public interface GroupService {
	
	/**
	 * 根据城市和类型获取政策组合包
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午1:34:10 
	 * </pre>
	 *
	 * @param policyGroup
	 * @return
	 */
	public List<SbGroup> getGroupByCityAndType(PolicyGroup policyGroup);
	/**
	 * 根据名字查询政策组合包
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月19日 下午1:38:09 
	 * </pre>
	 *
	 * @param groupName
	 * @return
	 */
	public List<SbGroup> getGroupByName(String groupName);
	/**
	 * 根据政策包组合Id 获取政策包组合名称,
	 * 会将多个名称拼接成一个字符串
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月14日 下午8:04:12 
	 * </pre>List<PolicyGroup>
	 *
	 * @param list
	 * @return
	 */
	String getGroupNamePlus(List<Long> list);
	/**
	 * 根据ID列表获取政策包组合列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月15日 下午2:51:28 
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	List<SbGroup> getGroupListByIdList(List<Long> list);

}
