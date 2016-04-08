package com.jabava.service.hro.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.hro.BdSbMapper;
import com.jabava.dao.hro.BdSbReportDetailMapper;
import com.jabava.service.hro.BdSbReportDetailService;
import com.jabava.utils.Page;
@Service
public class BdSbReportDetailServiceImpl implements BdSbReportDetailService {
	@Resource
	private BdSbReportDetailMapper bdSbReportDetailMapper;

	@Override
	public List<Map<String, Object>> getSheBaoGongJiJinInfoPage(Long companyId,String search,
			int policygrouptype, String reportMonth, String orderBy,
			Page<Map<String, Object>> page) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("policygrouptype", policygrouptype);
		params.put("reportmonth", reportMonth);
		params.put("search", search);
		params.put("orderBy", orderBy);
		params.put("companyId", companyId);
		params.put("page", page);
		 
		return bdSbReportDetailMapper.getSheBaoGongJiJinInfoPage(params);
	}



	@Override
	public List<Map<String, Object>> getSheBaoGongJiJinInfoDetailPage(Long companyId,String reportMonth,
			Long employeeId, int policyGroupType, int payType,
			Integer backpayType, String orderBy, Page<Map<String, Object>> page) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employeeId);
		params.put("reportMonth", reportMonth);
		params.put("policyGroupType", policyGroupType);
		params.put("payType", payType);
		params.put("backpayType", backpayType);
		params.put("orderBy", orderBy);
		params.put("companyId", companyId);
		params.put("page", page);
		 
		return bdSbReportDetailMapper.getSheBaoGongJiJinInfoDetailPage(params);
	}



	@Override
	public List<Map<String, Object>> getSbGjjTotalByReportMonthPage(int policyGroupType,String reportMonth,Long companyId,Page<Map<String, Object>> page) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("policyGroupType", policyGroupType);
		params.put("reportMonth", reportMonth);
		params.put("companyId", companyId);
		params.put("page", page);
		return bdSbReportDetailMapper.getSbGjjTotalByReportMonth(params);
	}
}
