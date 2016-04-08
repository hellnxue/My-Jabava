package com.jabava.utils.employee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;

public class PersonBase implements IPersonImport, IPersonExport {

	private EhrPersonMapper personMapper;
	private EhrCompanyMapper companyMapper;
	private EhrBaseDataMapper baseDataMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonBase(EhrPersonMapper personMapper,
			EhrCompanyMapper companyMapper, EhrBaseDataMapper baseDataMapper) {
		this.personMapper = personMapper;
		this.companyMapper = companyMapper;
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
			EhrPerson person = new EhrPerson();
			person.setJobNumber(JabavaUtil.getCellStr(row.getCell(1)));// 工号
			person.setEmployeeName(JabavaUtil.getCellStr(row.getCell(2)));// 姓名
			if ("身份证".equals(row.getCell(3).toString())) {// 证件类型
				person.setCertType((byte) 0);
			} else if ("军官证".equals(row.getCell(3).toString())) {
				person.setCertType((byte) 1);
			} else if ("护照".equals(row.getCell(3).toString())) {
				person.setCertType((byte) 2);
			} else {
				person.setCertType((byte) 3);
			}
			person.setCertId(JabavaUtil.getCellStr(row.getCell(4)));// 证号
			if (row.getCell(5) != null) {// 单位名称
				EhrCompany company = companyMapper.getCompanyIdByName(row
						.getCell(5).toString());
				if (company != null) {
					person.setCompanyId(company.getCompanyId());
				}else{
					EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,数据库中检索不到该单位名称:"+row
							.getCell(5).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,请输入单位名称");
			}
			if (row.getCell(6) != null) {// 入职时间
				person.setEntryDate(formatDate.parse(row.getCell(6).toString()));
			}
			if (row.getCell(7) != null) {// 转正时间
				person.setPositiveDate(formatDate.parse(row.getCell(7)
						.toString()));
			}
			if (row.getCell(8) != null) {// 工作地
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 5, row.getCell(8).toString());
				if (list != null && !list.isEmpty()) {
					person.setWorkLocation(list.get(0).getBaseDataId()+"");
				}else{
					EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,基础数据中检索不到该工作地:"+row
							.getCell(8).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,请输入工作地");
			}
			if (row.getCell(9) != null) {// 发薪地
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 5, row.getCell(9).toString());
				if (list != null && !list.isEmpty()) {
					person.setPayrollLocation(list.get(0).getBaseDataId()+"");
				}else{
					EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,基础数据中检索不到该发薪地:"+row
							.getCell(9).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,请输入发薪地");
			}
			if (row.getCell(10) != null) {// 社保缴纳地
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 5, row.getCell(10).toString());
				if (list != null && !list.isEmpty()) {
					person.setSocialSecurityLocation(list.get(0).getBaseDataId()+"");
				}else{
					EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,基础数据中检索不到该社保缴纳地:"+row
							.getCell(10).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,请输入社保缴纳地");
			}
			if (row.getCell(11) == null) {// 停发标志
				person.setIsPayrollFlag((byte) 0);
			} else {
				if (row.getCell(11).toString().contains("未")) {
					person.setIsPayrollFlag((byte) 0);
				} else {
					person.setIsForeign((byte) 1);
				}
			}
			if (row.getCell(12) == null) {// 员工状态
				person.setStatus((byte) 0);
			} else {
				if (row.getCell(12).toString().contains("在职")) {
					person.setStatus((byte) 0);
				} else if (row.getCell(12).toString().contains("离职")) {
					person.setStatus((byte) 1);
				} else if (row.getCell(12).toString().contains("入职")) {
					person.setStatus((byte) 2);
				}
			}
			if (row.getCell(13) != null) {// 团队
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 7, row.getCell(13).toString());
				if (list != null && !list.isEmpty()) {
					person.setTeam(list.get(0).getBaseDataId());
				}else{
					EhrPersonService.errorMsg.add("[基本信息]第"+i+"行,基础数据中检索不到该团队名称:"+row
							.getCell(13).toString());
				}
			}
			person.setRecruit(JabavaUtil.getCellStr(row.getCell(14)));// 招聘渠道
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());
			Long personId = map.get(row.getCell(4).toString());
			if (personId == null) {// 添加
				person.setCompanyId(user.getCompanyId());
				person.setCreateDate(new Date());
				person.setCreateUserId(user.getUserId());
				person.setCreateUserName(user.getUserName());
				personMapper.insertSelective(person);
				map.put(person.getCertId(), person.getPersonId());
			} else {// 更新
				person.setPersonId(personId);
				personMapper.updateByPrimaryKeySelective(person);
			}
		}

		return map;
	}

	@Override
	public List<Map<String, Object>> exportPerson(List<EhrPerson> persons,
			EhrUser user) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> data = null;
		int index = 1;
		for (EhrPerson person : persons) {
			data = new HashMap<String,Object>();
			data.put("index", index++);
			data.put("jobNumber", person.getJobNumber());
			data.put("employeeName", person.getEmployeeName());
			if(person.getCertType() == null){
				data.put("certType", "其它");
			}else{
				if (person.getCertType()==0) {// 证件类型
					data.put("certType", "身份证");
				} else if (person.getCertType()==0) {
					data.put("certType", "军官证");
				} else if (person.getCertType()==0) {
					data.put("certType", "护照");
				}
			}
			data.put("certId", person.getCertId());
			if(person.getCompanyId() != null){
				EhrCompany company = companyMapper.selectByPrimaryKey(person.getCompanyId());
				if(company != null){
					data.put("company", company.getCompanyName());
				}else{
					data.put("company", JabavaUtil.BLANK);
				}
			}else{
				data.put("company", JabavaUtil.BLANK);
			}
			if (person.getEntryDate() != null) {
				data.put("entryDate", formatDate.format(person.getEntryDate()));
			}else{
				data.put("entryDate", person.getEntryDate());
			}
			if (person.getPositiveDate() != null) {
				data.put("positiveDate", formatDate.format(person.getPositiveDate()));
			}else{
				data.put("positiveDate", person.getPositiveDate());
			}
			if(JabavaUtil.isNumeric(person.getWorkLocation()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getWorkLocation()));
				if(baseData != null){
					data.put("workLocation", baseData.getBaseDataName());
				}else{
					data.put("workLocation", person.getWorkLocation());
				}
			}else{
				data.put("workLocation", person.getWorkLocation());
			}
			if(JabavaUtil.isNumeric(person.getPayrollLocation()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getPayrollLocation()));
				if(baseData != null){
					data.put("payrollLocation", baseData.getBaseDataName());
				}else{
					data.put("payrollLocation", person.getPayrollLocation());
				}
			}else{
				data.put("payrollLocation", person.getPayrollLocation());
			}
			if(JabavaUtil.isNumeric(person.getSocialSecurityLocation()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getSocialSecurityLocation()));
				if(baseData != null){
					data.put("socialSecurityLocation", baseData.getBaseDataName());
				}else{
					data.put("socialSecurityLocation", person.getSocialSecurityLocation());
				}
			}else{
				data.put("socialSecurityLocation", person.getSocialSecurityLocation());
			}
			if (person.getIsPayrollFlag()!=null) { 
				if (person.getIsPayrollFlag()==0) { 
					data.put("isPayrollFlag", "停发");
				} else {
					data.put("isPayrollFlag", "为停发");
				}
			} else {
				data.put("isPayrollFlag", JabavaUtil.BLANK);
			}
			if (person.getStatus()!=null) { 
				if (person.getStatus()==0) { 
					data.put("status", "在职");
				} else if (person.getStatus()==1) { 
					data.put("status", "离职");
				} else{
					data.put("status", "入职中");
				}
			} else{
				data.put("status", "在职");
			}
			if(person.getTeam() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getTeam()));
				if(baseData != null){
					data.put("team", baseData.getBaseDataName());
				}else{
					data.put("team", person.getTeam());
				}
			}else{
				data.put("team", person.getTeam());
			}
			data.put("recruit", person.getRecruit()); 
			datas.add(data);
		}
		return datas;
	}
 
}
