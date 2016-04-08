package com.jabava.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrOrganizationHistoryMapper;
import com.jabava.pojo.manage.EhrOrganizationHistory;
import com.jabava.service.system.IEhrOrganizationHistoryService;
/***
 * 
 * @author panfei
 *
 */
@Service("organizationHistoryService")
public class EhrOrganizationHistoryServiceImpl implements
		IEhrOrganizationHistoryService {
	@Resource
	public EhrOrganizationHistoryMapper historyMapper;
	/**
	 * 生成基准
	 */
	@Override
	public boolean insertOrganizationHistory(EhrOrganizationHistory history)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int code = historyMapper.insertSelective(history);
			result = (1 == code);
		} catch (Exception e) {	
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
