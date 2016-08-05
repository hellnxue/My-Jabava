package com.jabava.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrPageMapper;
import com.jabava.pojo.manage.EhrPage;
import com.jabava.service.system.IPageService;

@Service
public class PageServiceImpl implements IPageService {
	@Autowired
	private EhrPageMapper pageMapper;

	@Override
	public List<EhrPage> queryPageList(Map<String,Object> params) {
		return pageMapper.queryPageList(params);
	}

}
