package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrTableDef;

public interface EhrTableDefService {
	List<EhrTableDef> selectKeyTable(Long companyId);
	
	EhrTableDef selectByCompanyId(Map<String, Object> map );
	
	int insert(EhrTableDef ehrTableDef);
}
