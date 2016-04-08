package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.manage.EhrAttendance;

public interface EhrAttendanceService {
	List<EhrAttendance> getByPersonId(Long personId) throws Exception;
}
