package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrAssessMapper;
import com.jabava.pojo.employee.EhrAssess;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonAssess implements IPersonImport {
	private EhrAssessMapper assessMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonAssess(EhrAssessMapper assessMapper) {
		this.assessMapper = assessMapper;
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
				EhrAssess assess = new EhrAssess();
				assess.setEvaluationCycle(JabavaUtil.getCellStr(row.getCell(5)));// 考核年度
				assess.setEvaluationResult(JabavaUtil.getCellStr(row.getCell(6)));// 考核结果
				assess.setAssesser(JabavaUtil.getCellStr(row.getCell(7)));// 考评人
				assess.setEvaluationLevel(JabavaUtil.getCellStr(row.getCell(8)));// 考核等级
				assess.setDescription(JabavaUtil.getCellStr(row.getCell(9)));// 考评人评价
				assess.setPersonId(personId);
				assess.setCreateDate(new Date());
				assess.setCreateUserId(user.getUserId());
				assess.setCreateUserName(user.getUserName());
				assess.setLastModifyDate(new Date());
				assess.setLastModifyUserId(user.getUserId());
				assess.setLastModifyUserName(user.getUserName());
				assessMapper.insertSelective(assess);
			}
		}
		return map;
	}

}
