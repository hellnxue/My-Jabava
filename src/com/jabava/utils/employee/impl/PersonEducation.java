package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrEducationMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.pojo.employee.EhrEducation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonEducation implements IPersonImport {

	private EhrEducationMapper educationMapper;
	private EhrBaseDataMapper baseDatamapper; 
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonEducation(EhrEducationMapper educationMapper,
			EhrBaseDataMapper baseDatamapper ) {
		this.educationMapper = educationMapper;
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
				EhrEducation education = new EhrEducation();
				education.setEducation(JabavaUtil.getCellStr(row.getCell(5)));//学历
				education.setLearnType(JabavaUtil.getCellStr(row.getCell(6)));//学习形式
				if (row.getCell(7) != null) {// 入学时间
					education.setEntranceDate(formatDate.parse(row.getCell(7)
							.toString()));
				}
				if (row.getCell(8) != null) {// 毕业时间
					education.setGraduateDate(formatDate.parse(row.getCell(8)
							.toString()));
				}
				education.setMajor(JabavaUtil.getCellStr(row.getCell(9)));
				education.setGraduateSchool(JabavaUtil.getCellStr(row.getCell(10)));
				if(row.getCell(11) != null){
					education.setSchoolTime(Long.valueOf(row.getCell(11).toString()));
				}
				education.setEducationCertificate(JabavaUtil.getCellStr(row.getCell(12)));
				education.setPersonId(personId);
				education.setCreateDate(new Date());
				education.setCreateUserId(user.getUserId());
				education.setCreateUserName(user.getUserName());
				education.setLastModifyDate(new Date());
				education.setLastModifyUserId(user.getUserId());
				education.setLastModifyUserName(user.getUserName());
				educationMapper.insertSelective(education);
			}
		}
		return map;
	}

}
