package com.jabava.service.system;

import java.util.List;

import com.jabava.pojo.system.EhrTableDef;

public interface EhrTableDefService {
	List<EhrTableDef> selectKeyTable(Long companyId);
}
