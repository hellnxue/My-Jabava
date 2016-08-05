package com.jabava.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.service.common.ICommonDataService;

@Service
public class CommonDataServiceImpl implements ICommonDataService {
	@Autowired
	private EhrCommonDataMapper commonDataMapper;

	@Override
	public List<EhrCommonData> listByCommonDataType(Integer commonDataType) {
		return commonDataMapper.listByCommonDataType(commonDataType);
	}

}
