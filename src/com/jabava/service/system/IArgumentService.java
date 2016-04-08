package com.jabava.service.system;

import java.util.List;

import com.jabava.pojo.manage.EhrArgumentInfo;
import com.jabava.utils.Page;

public interface IArgumentService {
	public List<EhrArgumentInfo> queryMyArgument(Long companyId, String search, String order, String according, int isNumeric, Page<EhrArgumentInfo> page) throws Exception;
	
	public boolean addArgument(EhrArgumentInfo argument) throws Exception;
	
	public boolean delArgument(Long id) throws Exception;
	
	public boolean updateArgument(EhrArgumentInfo argument) throws Exception;

	public List<String> selectAllKey(Long companyId) throws Exception;
}
