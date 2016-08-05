package com.jabava.service.salary.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryItemMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryItem;
import com.jabava.service.salary.ISalaryItemService;
import com.jabava.utils.enums.SalaryEnum;


@Service
public class SalaryItemServiceImpl implements ISalaryItemService{
	@Autowired
	private EhrSalaryItemMapper salaryItemMapper;
	@Override
	public List<EhrSalaryItem> listSalaryItemPage(Map<String, Object> params) {
		return salaryItemMapper.listSalaryItemPage(params);
	}

	@Override
	public List<EhrSalaryItem> listSalaryItem(Long companyId) {
		return salaryItemMapper.listSalaryItem(companyId);
	}

	@Override
	public EhrSalaryItem selectByName(Long companyId, String salaryItemName) {
		return salaryItemMapper.selectByName(companyId, salaryItemName);
	}

	@Override
	public int saveOrUpdate(EhrSalaryItem salaryItem, EhrUser user) {
		salaryItem.setCompanyId(user.getCompanyId());
		if(salaryItem.getSalaryChangeDefId() != null){
			//如果是系统变动表，则需要作特殊处理
			if(SalaryEnum.SystemChangeTable.Attendance.getId().equals(salaryItem.getSalaryChangeDefId())){
				salaryItem.setChangeTableName(SalaryEnum.SystemChangeTable.Attendance.getTableName());
				salaryItem.setSalaryChangeDefId(null);
			}else if(SalaryEnum.SystemChangeTable.SocialSecurity.getId().equals(salaryItem.getSalaryChangeDefId())){
				salaryItem.setChangeTableName(SalaryEnum.SystemChangeTable.SocialSecurity.getTableName());
				salaryItem.setSalaryChangeDefId(null);
			}else if(SalaryEnum.SystemChangeTable.AccumulationFund.getId().equals(salaryItem.getSalaryChangeDefId())){
				salaryItem.setChangeTableName(SalaryEnum.SystemChangeTable.AccumulationFund.getTableName());
				salaryItem.setSalaryChangeDefId(null);
			}
		}
		salaryItem.setLastModifyDate(new Date());
		salaryItem.setLastModifyUserId(user.getUserId());
		salaryItem.setLastModifyUserName(user.getUserName());
		if(salaryItem.getSalaryItemId() != null){
			return salaryItemMapper.updateByPrimaryKey(salaryItem);
		}else{
			salaryItem.setCreateDate(new Date());
			salaryItem.setCreateUserId(user.getUserId());
			salaryItem.setCreateUserName(user.getUserName());
			return salaryItemMapper.insertSelective(salaryItem);
		}
	}

	@Override
	public int deleteById(Long companyId, Long salaryItemId) {
		return salaryItemMapper.deleteById(companyId, salaryItemId);
	}

	@Override
	public EhrSalaryItem selectByPrimaryKey(Long salaryItemId) {
		return salaryItemMapper.selectByPrimaryKey(salaryItemId);
	}

	@Override
	public List<EhrSalaryItem> listTransitionItem(Long companyId, Long exceptId) {
		return salaryItemMapper.listTransitionItem(companyId, exceptId);
	}
	
}
