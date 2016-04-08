package com.jabava.utils.employee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;

public class PersonEach implements IPersonImport, IPersonExport {

	private EhrPersonMapper personMapper;
	private EhrBaseDataMapper baseDataMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonEach(EhrPersonMapper personMapper, EhrBaseDataMapper baseDataMapper) {
		this.personMapper = personMapper;
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
			if ("男".equals(row.getCell(3).toString())) {// 性别
				person.setSex((byte) 0);
			} else if ("女".equals(row.getCell(3).toString())) {
				person.setSex((byte) 1);
			}
			if (row.getCell(4) == null) {
				person.setIsForeign((byte) 0);
			} else {
				if ("否".equals(row.getCell(4).toString())
						|| "非外籍".equals(row.getCell(4).toString())) {// 是否外籍
					person.setIsForeign((byte) 0);
				} else if ("是".equals(row.getCell(4).toString())
						|| "外籍".equals(row.getCell(4).toString())) {
					person.setIsForeign((byte) 1);
				}
			}
			if (row.getCell(5) != null) {// 参加工作时间
				person.setFirstjobDate(formatDate.parse(row.getCell(5)
						.toString()));
			}
			if ("身份证".equals(row.getCell(6).toString())) {// 证件类型
				person.setCertType((byte) 0);
			} else if ("军官证".equals(row.getCell(6).toString())) {
				person.setCertType((byte) 1);
			} else if ("护照".equals(row.getCell(6).toString())) {
				person.setCertType((byte) 2);
			} else {
				person.setCertType((byte) 3);
			}
			person.setCertId(JabavaUtil.getCellStr(row.getCell(7)));// 证号
			
			if (row.getCell(8) != null) {// 开户行
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 6, row.getCell(8).toString());
				if (list != null && !list.isEmpty()) {
					person.setBankName(list.get(0).getBaseDataId()+"");
				}else{
					EhrPersonService.errorMsg.add("[个人信息]第"+i+"行,基础数据中检索不到该开户行名称:"+row
							.getCell(8).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[个人信息]第"+i+"行,请输入开户行");
			}
			if (row.getCell(9) != null) {// 开户支行
				List<EhrBaseData> list = baseDataMapper.selectBaseData(
						user.getCompanyId(), 6, row.getCell(9).toString());
				if (list != null && !list.isEmpty()) {
					person.setSubbank(list.get(0).getBaseDataId()+"");
				}else{
					EhrPersonService.errorMsg.add("[个人信息]第"+i+"行,基础数据中检索不到该开户支行名称:"+row
							.getCell(9).toString());
				}
			}else{
				EhrPersonService.errorMsg.add("[个人信息]第"+i+"行,请输入开户支行");
			}
			person.setSalaryCard(JabavaUtil.getCellStr(row.getCell(10)));// 工资卡号
			person.setDegree(JabavaUtil.getCellStr(row.getCell(11)));// 最高学历
			person.setEducation(JabavaUtil.getCellStr(row.getCell(12)));// 最高学位
			if (row.getCell(13) == null) {
				person.setStudyAbroad((byte) 0);
			} else {
				if ("是".equals(row.getCell(13).toString())) {// 是否海外留学
					person.setStudyAbroad((byte) 1);
				} else {
					person.setStudyAbroad((byte) 0);
				}
			}
			person.setRegisterLocation(JabavaUtil.getCellStr(row.getCell(14)));// 户口所在地
			if (row.getCell(15) != null) {
				if (row.getCell(15).toString().contains("城镇")) {// 户口类型
					person.setRegisterType((byte) 0);
				} else if (row.getCell(15).toString().contains("农业")
						|| row.getCell(15).toString().contains("农村")) {
					person.setRegisterType((byte) 1);
				}
			}
			person.setOriginPlace(JabavaUtil.getCellStr(row.getCell(16)));// 籍贯
			person.setNationality(JabavaUtil.getCellStr(row.getCell(17)));// 民族
			person.setMobile(JabavaUtil.getCellStr(row.getCell(18)));// 手机
			person.setPhone(JabavaUtil.getCellStr(row.getCell(19)));// 联系电话
			person.setEmail(JabavaUtil.getCellStr(row.getCell(20)));// 邮件
			person.setContactAddress(JabavaUtil.getCellStr(row.getCell(21)));// 现居住地
			person.setFamilyAddress(JabavaUtil.getCellStr(row.getCell(22)));// 家庭住址
			person.setPostCode(JabavaUtil.getCellStr(row.getCell(23)));// 邮编
			person.setFileLocation(JabavaUtil.getCellStr(row.getCell(24)));// 档案存放处
			if (row.getCell(25) != null) {
				if (!"".equals(row.getCell(25).toString())) {// 出生日期
					person.setBirthDate(formatDate.parse(row.getCell(25)
							.toString()));
				}
			}
			if (row.getCell(26) == null) {
				person.setMarital((byte) 0);
			} else {
				if ("已婚".equals(row.getCell(26).toString())) {// 婚姻状况
					person.setMarital((byte) 1);
				} else if ("离异".equals(row.getCell(26).toString())) {// 婚姻状况
					person.setMarital((byte) 2);
				} else {
					person.setMarital((byte) 0);
				}
			}
			person.setResume(JabavaUtil.getCellStr(row.getCell(27)));// 电子简历
			person.setLastModifyDate(new Date());
			person.setLastModifyUserId(user.getUserId());
			person.setLastModifyUserName(user.getUserName());
			Long personId = map.get(row.getCell(7).toString());
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
	public List<Map<String,Object>> exportPerson(List<EhrPerson> persons, EhrUser user)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> data = null;
		int index = 1;
		for (EhrPerson person : persons) {
			data = new HashMap<String,Object>();
			data.put("index", index++);
			data.put("jobNumber", person.getJobNumber());
			data.put("employeeName", person.getEmployeeName());
			if(person.getSex() != null){
				if (person.getSex() == 0) {// 性别
					data.put("sex", "男");
				} else {
					data.put("sex", "女");
				}
			}else{
				data.put("sex", JabavaUtil.BLANK);
			}
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
			if (person.getIsForeign() != null) {
				if (person.getIsForeign()==0) {// 是否外籍
					data.put("isForeign", "否");
				} else {
					data.put("isForeign", "是");
				}
			}else {
				data.put("isForeign", "是");
			}
			if (person.getFirstjobDate() != null) {
				data.put("firstjobDate", formatDate.format(person.getFirstjobDate()));
			}else{
				data.put("firstjobDate", person.getFirstjobDate());
			}
			if(JabavaUtil.isNumeric(person.getBankName()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getBankName()));
				if(baseData != null){
					data.put("bankName", baseData.getBaseDataName());
				}else{
					data.put("bankName", person.getBankName());
				}
			}else{
				data.put("bankName", person.getBankName());
			}
			if(JabavaUtil.isNumeric(person.getSubbank()) == 2){
				EhrBaseData baseData = baseDataMapper.selectByPrimaryKey(Long.valueOf(person.getSubbank()));
				if(baseData != null){
					data.put("subbank", baseData.getBaseDataName());
				}else{
					data.put("subbank", person.getSubbank());
				}
			}else{
				data.put("subbank", person.getSubbank());
			}
			data.put("salaryCard", person.getSalaryCard());
			data.put("degree", person.getDegree());
			data.put("education", person.getEducation());
			if (person.getStudyAbroad()!=null) { 
				if (person.getStudyAbroad()==0) { 
					data.put("studyAbroad", "城镇");
				} else {
					data.put("studyAbroad", "是");
				}
			} else {
				data.put("studyAbroad", JabavaUtil.BLANK);
			}
			data.put("registerLocation", person.getRegisterLocation());
			if (person.getStudyAbroad()!=null) { 
				if (person.getStudyAbroad()==0) { 
					data.put("registerType", "城镇");
				} else {
					data.put("registerType", "农业");
				}
			} else {
				data.put("registerType", JabavaUtil.BLANK);
			}
			data.put("originPlace", person.getOriginPlace());
			data.put("nationality", person.getNationality());
			data.put("mobile", person.getMobile());
			data.put("phone", person.getPhone());
			data.put("email", person.getEmail());
			data.put("contactAddress", person.getContactAddress());
			data.put("familyAddress", person.getFamilyAddress());
			data.put("postCode", person.getPostCode());
			data.put("fileLocation", person.getFileLocation());
			if (person.getBirthDate() != null) {
				data.put("birthDate", formatDate.format(person.getBirthDate()));
			}else{
				data.put("birthDate", person.getBirthDate());
			}
			if (person.getMarital()!=null) { 
				if (person.getMarital()==1) { 
					data.put("marital", "已婚");
				} else if (person.getMarital()==2) { 
					data.put("marital", "离异");
				} else{
					data.put("marital", "未婚");
				}
			} else{
				data.put("marital", "未婚");
			}
			datas.add(data);
		}
		return datas;
	}

}
