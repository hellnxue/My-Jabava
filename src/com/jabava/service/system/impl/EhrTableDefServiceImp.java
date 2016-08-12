package com.jabava.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.system.EhrTableDefMapper;
import com.jabava.pojo.system.EhrTableDef;
import com.jabava.service.system.EhrTableDefService;

@Service
public class EhrTableDefServiceImp implements EhrTableDefService{
	@Resource
	private EhrTableDefMapper ehrTableDefMapper;
	
	@Override
	public List<EhrTableDef> selectKeyTable(Long companyId) {
		// TODO Auto-generated method stub
		List<EhrTableDef> list = ehrTableDefMapper.selectKeyTable(companyId);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public EhrTableDef selectByCompanyId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		EhrTableDef ehrTableDef = ehrTableDefMapper.selectByCompanyId(map);
		return ehrTableDef;
	}

	@Override
	public int insert(EhrTableDef ehrTableDef) {
		// TODO Auto-generated method stub
		int tableDefId = ehrTableDefMapper.insert(ehrTableDef);
		return tableDefId;
	}

}
