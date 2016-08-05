package com.jabava.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrSysLogMapper;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrSysLog;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Page;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.enums.SystemEnum.LogOperateType;
import com.jabava.utils.enums.SystemEnum.Module;

/***
 * 
 * @author panfei
 * 
 */
@Service("sysLogService")
public class EhrSysLogSerciceImpl implements IEhrSysLogSercice {

	@Resource
	private EhrSysLogMapper logMapper;
	
	@Resource
	private EhrCompanyMapper companyMapper;

	/**
	 * 查询日志
	 */
	@Override
	public List<EhrSysLog> selectSysLog(String userName, Date startDate,
			Date endDate, String operateInfo, Long companyId, String search,
			String order, String according, int isNumeric, Page<EhrSysLog> page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("userName", userName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("operateInfo", operateInfo);
		map.put("companyId", companyId);
		map.put("search", search);
		map.put("order", order);
		map.put("according", according);
		map.put("isNumeric", isNumeric);
		return logMapper.selectSysLogPage(map);
	}

	/**
	 * 查询用户(日志查询条件)
	 * 
	 * @param companyId
	 * @return
	 */
	public List<EhrUser> selectByUser(Long companyId) {
		return logMapper.selectByUser(companyId);

	}
	
//	public int addSysLog(int module, String operation, boolean result) throws Exception {
//		HttpServletRequest request = ((ServletRequestAttributes)   
//                RequestContextHolder.currentRequestAttributes()).getRequest();
//		
//	}

	@Override
	public int addSysLog(EhrUser user, String operation) throws Exception {
		// TODO Auto-generated method stub
		EhrSysLog log = new EhrSysLog();
		log.setUserId(user.getUserId());
		log.setUserName(user.getUserName());
		if (user.getCompany() != null) {
			log.setCompanyId(user.getCompany().getCompanyId());
			log.setCompanyCode(user.getCompany().getCompanyCode());
			log.setCompanyName(user.getCompany().getCompanyName());
		} else {
			EhrCompany company = companyMapper.selectByPrimaryKey(user.getCompanyId());
			log.setCompanyId(company.getCompanyId());
			log.setCompanyCode(company.getCompanyCode());
			log.setCompanyName(company.getCompanyName());
		}
		log.setOperateInfo(operation);
		log.setOperateDate(new Date());
		return logMapper.insertSelective(log);
	}

@Override
public int addSysLog(EhrUser user, LogOperateType operation, Module module ,String operationInfo)
		throws Exception {
	// TODO Auto-generated method stub
	EhrSysLog log = new EhrSysLog();
	log.setUserId(user.getUserId());
	log.setUserName(user.getUserName());
	log.setOperateType(operation.getValue());
	log.setModule(module.getModuleName());
	
	if (user.getCompany() != null) {
		log.setCompanyId(user.getCompany().getCompanyId());
		log.setCompanyCode(user.getCompany().getCompanyCode());
		log.setCompanyName(user.getCompany().getCompanyName());
	} else {
		EhrCompany company = companyMapper.selectByPrimaryKey(user.getCompanyId());
		log.setCompanyId(company.getCompanyId());
		log.setCompanyCode(company.getCompanyCode());
		log.setCompanyName(company.getCompanyName());
	}
	log.setOperateInfo(operationInfo);
	log.setOperateDate(new Date());
	return logMapper.insertSelective(log);

}

}
