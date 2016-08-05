package com.jabava.utils.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.pojo.manage.EhrUser;

public interface IPersonImport {
	public Map <String,Object> importPerson(int num, Sheet sheet,Map<String, String> map, EhrUser user)throws Exception;
	public HashMap <String,Integer> duplicatedCertId();
	Map<String, Object> importPersonSecurityProfile(int num, Sheet sheet, Map<String, Long> map, EhrUser user,List<Map<String,Object>> currentSecurityConfig,List<Map<String,Object>> currentGongjijinProducts) throws Exception;
}
