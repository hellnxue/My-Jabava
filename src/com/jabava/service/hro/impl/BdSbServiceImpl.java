package com.jabava.service.hro.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.hro.BdSbMapper;
import com.jabava.pojo.hro.BdSb;
import com.jabava.service.hro.BdSbService;
import com.jabava.utils.Page;
@Service
public class BdSbServiceImpl implements BdSbService {
	
	@Resource
	private BdSbMapper bdSbMapper;
	
	 
	@Override
	public List<Map<String, Object>> getSheBaoInfo(int policygrouptype,String reportMonth,String likeFiled,Page<Map<String, Object>> page) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("policygrouptype", policygrouptype);
		params.put("reportmonth", reportMonth);
		//params.put("", likeFiled);
		params.put("page", page);
		return bdSbMapper.getSheBaoInfoPage(params);
	}


	@Override
	public int insertSbUpdateList(List<BdSb> list) {
	 
		return bdSbMapper.insertSbUpdateList(list);
	}
}
