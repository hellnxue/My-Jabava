package com.jabava.utils.employee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;

public class PersonPost implements IPersonImport, IPersonExport {

	private EhrPersonMapper personMapper;
	private EhrBaseDataMapper baseDataMapper;
	private EhrOrganizationMapper organizationMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonPost(EhrPersonMapper personMapper,
			EhrBaseDataMapper baseDatamapper,
			EhrOrganizationMapper organizationMapper) {
		this.personMapper = personMapper;
		this.baseDataMapper = baseDatamapper;
		this.organizationMapper = organizationMapper;
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
			if (row.getCell(5) != null) {// 部门名称
				List<EhrOrganization> orgs = organizationMapper.selectOrgByName(
						user.getCompanyId(), row.getCell(5).toString());
				if (orgs != null && !orgs.isEmpty()) {
					person.setOrganizationId(orgs.get(0).getOrganizationId());
				}else{
					EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,数据库中检索不到该部门名称:"+row
							.getCell(5).toString());
				}
			} 
			if (row.getCell(6) != null) {// 岗位名称
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 1, row.getCell(6).toString());
				if (list != null && !list.isEmpty()) {
					person.setPostId(list.get(0).getBaseDataId());
				}else{
					EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,基础数据中检索不到该岗位名称:"+row
							.getCell(6).toString());
				}
			} 
			if (row.getCell(7) != null) {// 成本中心
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 2, row.getCell(7).toString());
				if (list != null && !list.isEmpty()) {
					person.setCostId(list.get(0).getBaseDataId());
				}else{
					EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,基础数据中检索不到该成本中心:"+row
							.getCell(7).toString());
				}
			} 
			if (row.getCell(8) != null) {// 用工性质
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 9, row.getCell(8).toString());
				if (list != null && !list.isEmpty()) {
					person.setEmploymentType((list.get(0).getBaseDataId()+""));
				}else{
					EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,基础数据中检索不到该用工性质:"+row
							.getCell(8).toString());
				}
			} 
			if (row.getCell(9) != null) {// 职级
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 3, row.getCell(9).toString());
				if (list != null && !list.isEmpty()) {
					person.setRankId(list.get(0).getBaseDataId());
				} else {
					person.setRankId((long) 733);// 待定职级
				}
			} else {
				person.setRankId((long) 733);// 待定职级
			}
			if (row.getCell(10) == null) {// 是否关键岗位
				person.setKeyPerson((byte) 0);
			} else {
				if (row.getCell(10).toString().contains("是")) {
					person.setKeyPerson((byte) 1);
				} else {
					person.setKeyPerson((byte) 0);
				}
			}
			person.setReportObject(JabavaUtil.getCellStr(row.getCell(11)));// 汇报对象
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());
			Long personId = map.get(row.getCell(4).toString());
			if (personId == null) {// 添加
				person.setStatus((byte) 0);
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
				EhrOrganization org = organizationMapper.selectByPrimaryKey(person.getOrganizationId());
				if(org != null){
					data.put("org", org.getOrganizationName());
				}else{
					data.put("org", JabavaUtil.BLANK);
				}
			}else{
				data.put("org", JabavaUtil.BLANK);
			}
			if(person.getPostId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getPostId());
				if(baseData != null){
					data.put("post", baseData.getBaseDataName());
				}else{
					data.put("post", JabavaUtil.BLANK);
				}
			}else{
				data.put("post", JabavaUtil.BLANK);
			}
			if(person.getCostId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getCostId());
				if(baseData != null){
					data.put("cost", baseData.getBaseDataName());
				}else{
					data.put("cost", JabavaUtil.BLANK);
				}
			}else{
				data.put("cost", JabavaUtil.BLANK);
			}
			if(JabavaUtil.isNumeric(person.getEmploymentType()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getEmploymentType()));
				if(baseData != null){
					data.put("employmentType", baseData.getBaseDataName());
				}else{
					data.put("employmentType", person.getEmploymentType());
				}
			}else{
				data.put("employmentType", person.getEmploymentType());
			}
			if(person.getRankId() != null){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(person.getRankId());
				if(baseData != null){
					data.put("rank", baseData.getBaseDataName());
				}else{
					data.put("rank", JabavaUtil.BLANK);
				}
			}else{
				data.put("rank", JabavaUtil.BLANK);
			}
			if(person.getKeyPerson() != null){
				if (person.getKeyPerson() == 0) { 
					data.put("keyPerson", "否");
				} else {
					data.put("keyPerson", "是");
				}
			}else{
				data.put("keyPerson", JabavaUtil.BLANK);
			}
			data.put("reportObject", person.getReportObject());
			datas.add(data);
		}
		return datas;
	}
}
