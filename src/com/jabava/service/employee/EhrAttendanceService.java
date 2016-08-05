package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrAttendance;

public interface EhrAttendanceService {
	List<EhrAttendance> getByPersonId(Long personId) throws Exception;
	
	int insertAttendanceList(Long companyId, List<EhrAttendance> attendanceList);
	
	List<Map<String, Object>> listAttentancePage(Map<String, Object> params);
	
	List<Map<String, Object>> listAttentance(Map<String, Object> params);
	
	int deleteByYearMonthRecord(Long companyId, String yearMonthRecord);
	
	int deleteById(Long companyId, Long attendId);
	
	int updateAttendance(String name, String value, String pk);
	
	EhrAttendance selectByPrimaryKey(Long attendId);
}
