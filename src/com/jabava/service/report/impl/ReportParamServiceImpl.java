package com.jabava.service.report.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.report.EhrReportParamMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReportParam;
import com.jabava.service.report.IReportParamService;

@Service
public class ReportParamServiceImpl implements IReportParamService {
	@Autowired
	private EhrReportParamMapper reportParamMapper;

	@Override
	public List<EhrReportParam> listByReportId(Long reportId) {
		return reportParamMapper.listByReportId(reportId);
	}

	@Override
	public int deleteById(Long companyId, Long reportParamId) {
		return reportParamMapper.deleteById(companyId, reportParamId);
	}

	@Override
	public EhrReportParam selectByReportAndName(Long companyId, Long reportId,
			String englishName) {
		return reportParamMapper.selectByReportAndName(companyId, reportId, englishName);
	}

	@Override
	public int saveOrUpdate(EhrUser user, EhrReportParam reportParam) {
		Date now = new Date();
		reportParam.setLastModifyDate(now);
		reportParam.setLastModifyUserId(user.getUserId());
		reportParam.setLastModifyUserName(user.getUserName());
		if(reportParam.getId() == null){
			reportParam.setCreateDate(now);
			reportParam.setCreateUserId(user.getUserId());
			reportParam.setCreateUserName(user.getUserName());
			return reportParamMapper.insertSelective(reportParam);
		}else{
			return reportParamMapper.updateByPrimaryKey(reportParam);
		}
	}

	@Override
	public EhrReportParam loadReportParam(Long companyId, Long reportParamId) {
		return reportParamMapper.selectById(companyId, reportParamId);
	}

}
