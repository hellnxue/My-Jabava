package com.jabava.utils.employee.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrProjectMapper;
import com.jabava.pojo.employee.EhrProject;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonProject implements IPersonImport {
	private EhrProjectMapper projectMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonProject(EhrProjectMapper projectMapper) {
		this.projectMapper = projectMapper;
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
			Long personId = map.get(row.getCell(4).toString());
			if (personId != null) {
				EhrProject project = new EhrProject();
				project.setProjectName(JabavaUtil.getCellStr(row.getCell(5)));// 项目名称
				if (row.getCell(6) != null) {// 项目起始时间
					project.setProjectStartDate(formatDate.parse(row.getCell(6)
							.toString()));
				}
				if (row.getCell(7) != null) {// 项目终止时间
					project.setProjectEndDate(formatDate.parse(row.getCell(7)
							.toString()));
				}
				project.setProjectDuty(JabavaUtil.getCellStr(row.getCell(8)));// 项目职责
				if (row.getCell(9) != null) {
					project.setProjectCost(new BigDecimal(row.getCell(9)
							.toString()));// 项目金额
				}
				project.setMemo(JabavaUtil.getCellStr(row.getCell(10)));// 项目描述
				project.setPersonId(personId);
				project.setCreateDate(new Date());
				project.setCreateUserId(user.getUserId());
				project.setCreateUserName(user.getUserName());
				project.setLastModifyDate(new Date());
				project.setLastModifyUserId(user.getUserId());
				project.setLastModifyUserName(user.getUserName());
				projectMapper.insertSelective(project);
			}
		}

		return map;
	}

}
