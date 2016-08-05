package com.jabava.service.report.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.report.EhrReportConfigMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReportConfig;
import com.jabava.service.report.IReportConfigService;

@Service
public class ReportConfigServiceImpl implements IReportConfigService {
	@Autowired
	private EhrReportConfigMapper reportConfigMapper;

	@Override
	public List<EhrReportConfig> listByReportId(Long reportId) {
		return reportConfigMapper.listByReportId(reportId);
	}

	@Override
	public int deleteById(Long companyId, Long reportConfigId) {
		return reportConfigMapper.deleteById(companyId, reportConfigId);
	}

	@Override
	public int saveOrUpdate(EhrUser user, EhrReportConfig reportConfig) {
		Date now = new Date();
		reportConfig.setLastModifyDate(now);
		reportConfig.setLastModifyUserId(user.getUserId());
		reportConfig.setLastModifyUserName(user.getUserName());
		if(reportConfig.getId() == null){
			reportConfig.setCreateDate(now);
			reportConfig.setCreateUserId(user.getUserId());
			reportConfig.setCreateUserName(user.getUserName());
			return reportConfigMapper.insertSelective(reportConfig);
		}else{
			//return reportConfigMapper.updateByPrimaryKey(reportConfig);
			return reportConfigMapper.updateByPrimaryKeyWithBLOBs(reportConfig);
		}
	}

	@Override
	public EhrReportConfig loadReportConfig(Long companyId, Long reportConfigId) {
		return reportConfigMapper.selectById(companyId, reportConfigId);
	}

}
