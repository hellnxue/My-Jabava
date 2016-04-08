package com.jabava.service.system;

import java.util.Date;
import java.util.List;

import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrSysLog;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

/***
 * 
 * @author panfei
 * 
 */
public interface IEhrSysLogSercice {
	/**
	 * 查询日志
	 */
	public List<EhrSysLog> selectSysLog(String userName, Date startDate,
			Date endDate, String operateInfo, Long companyId, String search,
			String order, String according, int isNumeric, Page<EhrSysLog> page) throws Exception;

	/**
	 * 查询用户(日志查询条件)
	 * 
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public List<EhrUser> selectByUser(Long companyId) throws Exception;

	
	public int addSysLog(EhrUser user, String operation) throws Exception;
}
