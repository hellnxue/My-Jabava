package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonLeft implements IPersonImport {
	private EhrDimissionMapper leftMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonLeft(EhrDimissionMapper leftMapper) {
		this.leftMapper = leftMapper;
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
					EhrDimission left = new EhrDimission();
					if (row.getCell(5) != null) {// 离职时间
						left.setDimissionDate(formatDate.parse(row.getCell(5)
								.toString()));
					}
					if (row.getCell(6) != null) {// 薪资结算日
						left.setSalarySettleDate(formatDate.parse(row.getCell(6)
								.toString()));
					}
					left.setDimissionCause(Integer.valueOf(JabavaUtil.getCellStr(row.getCell(7))));// 离职原因
					left.setMemo(JabavaUtil.getCellStr(row.getCell(8)));// 备注
					left.setPersonId(personId);
					left.setCreateDate(new Date());
					left.setCreateUserId(user.getUserId());
					left.setCreateUserName(user.getUserName());
					left.setLastModifyDate(new Date());
					left.setLastModifyUserId(user.getUserId());
					left.setLastModifyUserName(user.getUserName());
					leftMapper.insertSelective(left);
				}
			} catch (Exception e) {
				continue;
			}
		
		}
		return map;
	}

}
