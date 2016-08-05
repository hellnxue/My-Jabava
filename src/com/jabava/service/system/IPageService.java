package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrPage;

public interface IPageService {
	public List<EhrPage> queryPageList(Map<String,Object> params);
}
