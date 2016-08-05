package com.jabava.service.employee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.pojo.manage.EhrAttendance;
import com.jabava.service.employee.EhrAttendanceService;

@Service("attendanceService")
public class EhrAttendanceServiceImpl implements EhrAttendanceService {
	@Resource
	private EhrAttendanceMapper attendanceMapper;

	@Override
	public List<EhrAttendance> getByPersonId(Long personId) throws Exception {
		return attendanceMapper.getByPersonId(personId);
	}

	@Override
	public int insertAttendanceList(Long companyId,
			List<EhrAttendance> attendanceList) {
		
		for(EhrAttendance data : attendanceList){
			attendanceMapper.insertSelective(data);
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> listAttentancePage(Map<String, Object> params) {
		return attendanceMapper.listAttentancePage(params);
	}

	@Override
	public List<Map<String, Object>> listAttentance(Map<String, Object> params) {
		return attendanceMapper.listAttentance(params);
	}

	@Override
	public int deleteByYearMonthRecord(Long companyId, String yearMonthRecord) {
		return attendanceMapper.deleteByYearMonthRecord(companyId, yearMonthRecord);
	}

	@Override
	public int deleteById(Long companyId, Long attendId) {
		attendanceMapper.selectByPrimaryKey(attendId);
		return attendanceMapper.deleteById(companyId, attendId);
	}

	@Override
	public int updateAttendance(String name, String value, String pk) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("value", value);
		params.put("pk", pk);
		return attendanceMapper.updateByParam(params);
	}

	@Override
	public EhrAttendance selectByPrimaryKey(Long attendId) {
		// TODO Auto-generated method stub
		
		return attendanceMapper.selectByPrimaryKey(attendId);
	}
}
