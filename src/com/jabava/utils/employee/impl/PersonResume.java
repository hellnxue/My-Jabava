package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrResumeMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.pojo.employee.EhrResume;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonResume implements IPersonImport {
	private EhrResumeMapper resumeMapper;
	private EhrBaseDataMapper baseDatamapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonResume(EhrResumeMapper resumeMapper,
			EhrBaseDataMapper baseDatamapper) {
		this.resumeMapper = resumeMapper;
		this.baseDatamapper = baseDatamapper;
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
				EhrResume resume = new EhrResume();
				if (row.getCell(5) != null) {// 起始时间
					resume.setStartDate(formatDate.parse(row.getCell(5)
							.toString()));
				}
				if (row.getCell(6) != null) {// 终止时间
					resume.setEndDate(formatDate.parse(row.getCell(6)
							.toString()));
				}
				resume.setCompanyName(JabavaUtil.getCellStr(row.getCell(7)));// 工作单位
				resume.setWorkPost(JabavaUtil.getCellStr(row.getCell(8)));// 工作岗位
				resume.setDescription(JabavaUtil.getCellStr(row.getCell(9)));// 职责描述
				resume.setCertifier(JabavaUtil.getCellStr(row.getCell(10)));// 证明人
				resume.setPersonId(personId);
				resume.setCreateDate(new Date());
				resume.setCreateUserId(user.getUserId());
				resume.setCreateUserName(user.getUserName());
				resume.setLastModifyDate(new Date());
				resume.setLastModifyUserId(user.getUserId());
				resume.setLastModifyUserName(user.getUserName());
				resumeMapper.insertSelective(resume);
			}
		}

		return map;
	}
}
