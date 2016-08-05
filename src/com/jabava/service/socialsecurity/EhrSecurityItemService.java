package com.jabava.service.socialsecurity;

import java.util.List;

import com.jabava.pojo.socialsecurity.EhrSecurityItem;

public interface EhrSecurityItemService {

	/**
	 * 依据员工代码查询员工社保项目明细
	 * @param profileId
	 * @return
	 */
	public List <EhrSecurityItem> getEhrSecurityItemlInfoByPersonId(Long personId);
	
	/**
	 * 保存社保项目明细列表
	 * @param profileId
	 * @param list
	 * @return
	 */
	public int saveEhrSecurityItems(List<EhrSecurityItem> list);
}
