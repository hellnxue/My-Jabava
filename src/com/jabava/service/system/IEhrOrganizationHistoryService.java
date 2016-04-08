package com.jabava.service.system;

import com.jabava.pojo.manage.EhrOrganizationHistory;
/****
 * 
 * @author panfei
 *
 */
public interface IEhrOrganizationHistoryService {
	/**
	 * 生成基准
	 * @param info
	 * @return
	 * @throws Exception
	 */
	boolean  insertOrganizationHistory(EhrOrganizationHistory history) throws Exception;

}
