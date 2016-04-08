package com.jabava.utils.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;

public interface IPersonExport {
	
	public List<Map<String,Object>> exportPerson(List<EhrPerson> persons, EhrUser user)throws Exception;
	
}
