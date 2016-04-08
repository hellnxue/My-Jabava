package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrContractMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonContract implements IPersonImport {

	private EhrContractMapper contractMapper;
	private EhrBaseDataMapper baseDataMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonContract(EhrContractMapper contractMapper,
			EhrBaseDataMapper baseDataMapper) {
		this.contractMapper = contractMapper;
		this.baseDataMapper = baseDataMapper;
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
				EhrContract contract = new EhrContract();
				if (row.getCell(5) != null) {// 合同类型
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 10, row.getCell(5).toString());
					if (list != null && !list.isEmpty()) {
						contract.setContractType(Integer.valueOf(list.get(0).getBaseDataId()+""));
					}else{
						EhrPersonService.errorMsg.add("[劳动合同]第"+i+"行,基础数据中检索不到该合同类型:"+row
								.getCell(5).toString());
					}
				}
				if (row.getCell(6) != null) {// 合同开始时间
					contract.setContractStartDate(formatDate.parse(row.getCell(6).toString()));
				}
				if (row.getCell(7) != null) {// 合同结束时间
					contract.setContractEndDate(formatDate.parse(row.getCell(7)
							.toString()));
				}
				if (JabavaUtil.isNumeric(row.getCell(8).toString()) == 2) {// 试用期
					contract.setTrialMonth((byte)(Integer.parseInt(row
							.getCell(8).toString())));
				}
				contract.setContractProperty(Integer.valueOf(JabavaUtil.getCellStr(row.getCell(9))));// 合同性质
				if (row.getCell(10) != null) {// 合同主体
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 8, row.getCell(10).toString());
					if (list != null && !list.isEmpty()) {
						contract.setContractSubject(list.get(0).getBaseDataId());
					}else{
						EhrPersonService.errorMsg.add("[劳动合同]第"+i+"行,基础数据中检索不到该合同主体:"+row
								.getCell(10).toString());
					}
				}
				contract.setMemo(JabavaUtil.getCellStr(row.getCell(11)));// 备注
				contract.setPersonId(personId);
				contract.setCreateDate(new Date());
				contract.setCreateUserId(user.getUserId());
				contract.setCreateUserName(user.getUserName());
				contract.setLastModifyDate(new Date());
				contract.setLastModifyUserId(user.getUserId());
				contract.setLastModifyUserName(user.getUserName());
				contractMapper.insertSelective(contract);
			}
		}
		return map;
	}

}
