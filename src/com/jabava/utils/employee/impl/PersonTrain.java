package com.jabava.utils.employee.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrTrainMapper;
import com.jabava.pojo.employee.EhrTrain;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonTrain implements IPersonImport {
	private EhrTrainMapper trainMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonTrain(EhrTrainMapper trainMapper) {
		this.trainMapper = trainMapper;
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
				EhrTrain train = new EhrTrain();
				try { 
					if (row.getCell(5) != null) {// 培训开始时间
						train.setTrainStartDate(formatDate.parse(row.getCell(5)
								.toString()));
					}
					if (row.getCell(6) != null) {// 培训结束时间
						train.setTrainEndDate(formatDate.parse(row.getCell(6)
								.toString()));
					}
					if (row.getCell(7) != null) {// 服务到期时间
						train.setServiceExpireDate(formatDate.parse(row.getCell(7)
								.toString()));
					}
					train.setClassName(JabavaUtil.getCellStr(row.getCell(8)));// 课程名称
					if (row.getCell(9) != null) {
						train.setTrainCost(new BigDecimal(row.getCell(9)
								.toString()));
					}
					train.setTrainOrganization(JabavaUtil.getCellStr(row
							.getCell(10)));// 培训机构
					if (row.getCell(11) != null && JabavaUtil.isNumeric(row.getCell(11)
								.toString()) == 2) {// 培训课时
						train.setTrainHour(Integer.parseInt(row.getCell(11)
								.toString().replace(".0", "")));
					}
					if (row.getCell(12) == null) {// 是否有证书
						train.setIsCertificate((byte) 0);
					} else {
						if (row.getCell(12).toString().contains("是")) {
							train.setIsCertificate((byte) 1);
						} else {
							train.setIsCertificate((byte) 0);
						}
					}
					train.setMemo(JabavaUtil.getCellStr(row.getCell(13)));// 备注
					train.setPersonId(personId);
					train.setCreateDate(new Date());
					train.setCreateUserId(user.getUserId());
					train.setCreateUserName(user.getUserName());
					train.setLastModifyDate(new Date());
					train.setLastModifyUserId(user.getUserId());
					train.setLastModifyUserName(user.getUserName());
					trainMapper.insertSelective(train);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return map;
	}

}
