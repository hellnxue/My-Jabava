package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrRelationMapper;
import com.jabava.pojo.employee.EhrRelation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonRelation implements IPersonImport {
	private EhrRelationMapper relationMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonRelation(EhrRelationMapper relationMapper) {
		this.relationMapper = relationMapper;
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
				EhrRelation relation = new EhrRelation();
				relation.setRelationName(JabavaUtil.getCellStr(row.getCell(5)));// 成员姓名
				relation.setRelation(JabavaUtil.getCellStr(row.getCell(6)));// 与本人关系
				relation.setLiveCity(JabavaUtil.getCellStr(row.getCell(7)));// 居住城市
				if (row.getCell(8) != null) {// 出生日期
					relation.setBirth(formatDate.parse(row.getCell(8)
							.toString()));
				}
				relation.setCertId(JabavaUtil.getCellStr(row.getCell(9)));// 身份证号
				relation.setCompanyName(JabavaUtil.getCellStr(row.getCell(10)));// 工作单位及职位
				relation.setPhone(JabavaUtil.getCellStr(row.getCell(11)));// 成员联系电话
				relation.setPersonId(personId);
				relation.setCreateDate(new Date());
				relation.setCreateUserId(user.getUserId());
				relation.setCreateUserName(user.getUserName());
				relation.setLastModifyDate(new Date());
				relation.setLastModifyUserId(user.getUserId());
				relation.setLastModifyUserName(user.getUserName());
				relationMapper.insertSelective(relation);
			}
		}
		return map;
	}

}
