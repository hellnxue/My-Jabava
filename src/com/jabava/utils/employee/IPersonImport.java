package com.jabava.utils.employee;

import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.pojo.manage.EhrUser;

public interface IPersonImport {

	public Map<String, Long> importPerson(int num, Sheet sheet,
			Map<String, Long> map, EhrUser user)throws Exception;

}
