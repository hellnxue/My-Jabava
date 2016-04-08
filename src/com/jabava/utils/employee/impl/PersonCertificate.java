package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrCertificateMapper;
import com.jabava.pojo.employee.EhrCertificate;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonCertificate implements IPersonImport {
	private EhrCertificateMapper certificateMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonCertificate(EhrCertificateMapper certificateMapper) {
		this.certificateMapper = certificateMapper;
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
				EhrCertificate certificate = new EhrCertificate();
				certificate.setCertificateName(JabavaUtil.getCellStr(row
						.getCell(5)));// 证书名称
				if (row.getCell(6) != null) {// 取得证书时间
					certificate.setIssueDate(formatDate.parse(row.getCell(6)
							.toString()));
				}
				if (JabavaUtil.isNumeric(row.getCell(7).toString()) == 2) {// 证书有效期
					certificate.setValidityYear((byte) (Integer.parseInt(row
							.getCell(7).toString())));
				}
				certificate.setMemo(JabavaUtil.getCellStr(row.getCell(8)));// 备注
				certificate.setPersonId(personId);
				certificate.setCreateDate(new Date());
				certificate.setCreateUserId(user.getUserId());
				certificate.setCreateUserName(user.getUserName());
				certificate.setLastModifyDate(new Date());
				certificate.setLastModifyUserId(user.getUserId());
				certificate.setLastModifyUserName(user.getUserName());
				certificateMapper.insertSelective(certificate);
			}
		}
		return map;
	}

}
