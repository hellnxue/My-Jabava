package com.jabava.utils.employee.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrJobpostMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonTransfer implements IPersonImport {

	private EhrJobpostMapper jobPostMapper;
	private EhrBaseDataMapper baseDataMapper;
	private EhrOrganizationMapper organizationMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonTransfer(EhrJobpostMapper jobPostMapper,
			EhrBaseDataMapper baseDataMapper,
			EhrOrganizationMapper organizationMapper) {
		this.jobPostMapper = jobPostMapper;
		this.baseDataMapper = baseDataMapper;
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
			Long personId = map.get(row.getCell(4).toString());
			if (personId != null) {
				EhrJobpost jobPost = new EhrJobpost();
				if (row.getCell(5) != null) {// 原部门名称
					List<EhrOrganization> orgs = organizationMapper.selectOrgByName(
							user.getCompanyId(), row.getCell(5).toString());
					if (orgs != null && !orgs.isEmpty()) {
						jobPost.setSourceDepartment(orgs.get(0).getOrganizationId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,数据库中检索不到原部门名称:"+row
								.getCell(5).toString());
					}
				}
				if (row.getCell(6) != null) {// 原岗位名称
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 1, row.getCell(6).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setSourcePost(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,基础数据中检索不到原岗位名称:"+row
								.getCell(6).toString());
					}
				}
				if (row.getCell(7) != null) {// 原职级
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 3, row.getCell(7).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setSourceRank(list.get(0).getBaseDataId()+"");
					} else {
						jobPost.setSourceRank("733");// 待定职级
					}
				} else {
					jobPost.setSourceRank("733");// 待定职级
				}
				if (row.getCell(8) != null) {// 原岗位任职期限
					jobPost.setSourceLong(row.getCell(8)
							.toString());
				}
				jobPost.setSourceReport(JabavaUtil.getCellStr(row.getCell(9)));// 原汇报对象
				if (row.getCell(10) != null) {// 原工作地
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 5, row.getCell(10).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setSourceLocation(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,基础数据中检索不到原工作地:"+row
								.getCell(10).toString());
					}
				}
				if (row.getCell(11) != null) {// 原成本中心
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 2, row.getCell(11).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setSourceCost(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,基础数据中检索不到原成本中心:"+row
								.getCell(11).toString());
					}
				} 
				
				if (row.getCell(12) != null) {// 现部门名称
					List<EhrOrganization> orgs = organizationMapper.selectOrgByName(
							user.getCompanyId(), row.getCell(12).toString());
					if (orgs != null && !orgs.isEmpty()) {
						jobPost.setNewDepartment(orgs.get(0).getOrganizationId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,数据库中检索不到现部门名称:"+row
								.getCell(12).toString());
					}
				}
				if (row.getCell(13) != null) {// 现岗位名称
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 1, row.getCell(13).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setNewPost(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,基础数据中检索不到现岗位名称:"+row
								.getCell(13).toString());
					}
				}
				if (row.getCell(14) != null) {// 现职级
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 3, row.getCell(14).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setNewRank(list.get(0).getBaseDataId()+"");
					} else {
						jobPost.setNewRank("733");// 待定职级
					}
				} else {
					jobPost.setNewRank("733");// 待定职级
				}
				jobPost.setNewReport(JabavaUtil.getCellStr(row.getCell(15)));// 当前汇报对象
				if (row.getCell(16) != null) {// 现工作地
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 5, row.getCell(16).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setNewLocation(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位调动]第"+i+"行,基础数据中检索不到现工作地:"+row
								.getCell(16).toString());
					}
				}
				if (row.getCell(17) != null) {// 当前成本中心
					List<EhrBaseData> list = baseDataMapper.selectBaseData(
							user.getCompanyId(), 2, row.getCell(17).toString());
					if (list != null && !list.isEmpty()) {
						jobPost.setNewCost(list.get(0).getBaseDataId()+"");
					}else{
						EhrPersonService.errorMsg.add("[岗位信息]第"+i+"行,基础数据中检索不到当前成本中心:"+row
								.getCell(17).toString());
					}
				} 
				jobPost.setCause(JabavaUtil.getCellStr(row.getCell(18)));// 岗位调整原因
				if (row.getCell(19) != null) {// 岗位调整日期
					jobPost.setNewDate(formatDate.parse(row.getCell(19)
							.toString()));
				}
				if (row.getCell(20) == null) {// 是否调薪
					jobPost.setIsChangeSalary((byte) 0);
				} else {
					if (row.getCell(20).toString().contains("是")) {
						jobPost.setIsChangeSalary((byte) 1);
					} else {
						jobPost.setIsChangeSalary((byte) 0);
					}
				}
				jobPost.setPersonId(personId);
				jobPost.setCreateDate(new Date());
				jobPost.setCreateUserId(user.getUserId());
				jobPost.setCreateUserName(user.getUserName());
				jobPost.setLastModifyDate(new Date());
				jobPost.setLastModifyUserId(user.getUserId());
				jobPost.setLastModifyUserName(user.getUserName());
				jobPostMapper.insertSelective(jobPost);
			}
		}
		return map;
	}

}
