package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrTrialMapper;
import com.jabava.pojo.employee.EhrTrial;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonTrial implements IPersonImport {
	private EhrTrialMapper trialMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonTrial(EhrTrialMapper trialMapper) {
		this.trialMapper = trialMapper;
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
				EhrTrial trial = new EhrTrial();
				if (row.getCell(5) != null) {// 试用开始时间
					trial.setTrialStartDate(formatDate.parse(row.getCell(5)
							.toString()));
				}
				if (row.getCell(6) != null) {// 转正时间
					trial.setPositiveDate(formatDate.parse(row.getCell(6)
							.toString()));
				}
				trial.setPositiveType(Integer.valueOf(JabavaUtil.getCellStr(row.getCell(7))));// 转正性质
				trial.setEvaluationResult(JabavaUtil.getCellStr(row.getCell(8)));// 考评结果
				trial.setMemo(JabavaUtil.getCellStr(row.getCell(8)));// 试用备注
				trial.setPersonId(personId);
				trial.setCreateDate(new Date());
				trial.setCreateUserId(user.getUserId());
				trial.setCreateUserName(user.getUserName());
				trial.setLastModifyDate(new Date());
				trial.setLastModifyUserId(user.getUserId());
				trial.setLastModifyUserName(user.getUserName());
				trialMapper.insertSelective(trial);
			}
		}
		return map;
	}

}
