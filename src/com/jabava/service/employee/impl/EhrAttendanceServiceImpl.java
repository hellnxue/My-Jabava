package com.jabava.service.employee.impl;

import java.util.List;

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
}
