package com.jabava.utils.employee.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.pojo.manage.EhrAttendance;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonAttendance implements IPersonImport {
	private EhrAttendanceMapper attendanceMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonAttendance(EhrAttendanceMapper attendanceMapper) {
		this.attendanceMapper = attendanceMapper;
	}

	@Override
	public Map<String, Long> importPerson(int num, Sheet sheet,
			Map<String, Long> map, EhrUser user) throws Exception {
		// TODO Auto-generated method stub
		int totalRows = sheet.getLastRowNum();
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			try {
				Long personId = map.get(row.getCell(4).toString());
				if (personId != null) {
					EhrAttendance attendance = new EhrAttendance();
					if(row.getCell(5) != null){// 考勤年月
						attendance.setYearMonthRecord(row.getCell(5).toString().replace(".0", ""));
					}
					if(row.getCell(6) != null){//迟到次数
						attendance.setLateTimes((byte)Integer.parseInt(row.getCell(6).toString().replace(".0", "")));
					}else{
						attendance.setLateTimes((byte)0);
					}
					if(row.getCell(7) != null){//早退次数
						attendance.setLeaveEarlyTimes((byte)Integer.parseInt(row.getCell(7).toString().replace(".0", "")));
					}else{
						attendance.setLeaveEarlyTimes((byte)0);
					}
					if(row.getCell(8) != null){//事假天数
						attendance.setAllLeave(new BigDecimal(row.getCell(8).toString()));
					}else{
						attendance.setAllLeave(new BigDecimal(0));
					}
					if(row.getCell(9) != null){//病假天数
						attendance.setSickLeave(new BigDecimal(row.getCell(9).toString()));
					}else{
						attendance.setSickLeave(new BigDecimal(0));
					}
					if(row.getCell(10) != null){//出差天数
						attendance.setBusiness(new BigDecimal(row.getCell(10).toString()));
					}else{
						attendance.setBusiness(new BigDecimal(0));
					}
					if(row.getCell(11) != null){//平日加班
						attendance.setWorkOvertime(new BigDecimal(row.getCell(11).toString()));
					}else{
						attendance.setWorkOvertime(new BigDecimal(0));
					}
					if(row.getCell(12) != null){//周末加班
						attendance.setWeekOvertime(new BigDecimal(row.getCell(12).toString()));
					}else{
						attendance.setWeekOvertime(new BigDecimal(0));
					}
					if(row.getCell(13) != null){//节假日加班
						attendance.setHolidaysOvertime(new BigDecimal(row.getCell(13).toString()));
					}else{
						attendance.setHolidaysOvertime(new BigDecimal(0));
					}
					if(row.getCell(14) != null){//调休天数
						attendance.setAdjustDay(new BigDecimal(row.getCell(14).toString()));
					}else{
						attendance.setAdjustDay(new BigDecimal(0));
					}
					if(row.getCell(15) != null){//夜班天数
						attendance.setNightShift(new BigDecimal(row.getCell(15).toString()));
					}else{
						attendance.setNightShift(new BigDecimal(0));
					}
					attendance.setPersonId(personId);
					attendance.setCreateDate(new Date());
					attendance.setCreateUserId(user.getUserId());
					attendance.setCreateUserName(user.getUserName());
					attendance.setLastModifyDate(new Date());
					attendance.setLastModifyUserId(user.getUserId());
					attendance.setLastModifyUserName(user.getUserName());
					attendanceMapper.insertSelective(attendance);
				}
			} catch (Exception e) {
				continue;
			}
		}

		return map;
	}

}
