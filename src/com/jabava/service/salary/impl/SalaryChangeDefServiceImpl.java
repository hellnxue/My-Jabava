package com.jabava.service.salary.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryChangeDefItemMapper;
import com.jabava.dao.salary.EhrSalaryChangeDefMapper;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;
import com.jabava.service.salary.ISalaryChangeDefService;

@Service
public class SalaryChangeDefServiceImpl implements ISalaryChangeDefService{
	@Autowired
	private EhrSalaryChangeDefMapper salaryChangeDefMapper;
	
	@Autowired
	private EhrSalaryChangeDefItemMapper salaryChangeDefItemMapper;

	@Override
	public List<EhrSalaryChangeDef> listSalaryChangeDef(Long companyId) {
		return salaryChangeDefMapper.queryByCompanyId(companyId);
	}

	@Override
	public List<Map<String, Object>> loadSalaryChangeDef(Long salaryChangeDefId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EhrSalaryChangeDef selectByName(Long companyId, String name) {
		return salaryChangeDefMapper.selectByName(companyId, name);
	}

	@Override
	public int saveOrUpdate(EhrSalaryChangeDef changeDef) {
		if(changeDef.getSalaryChangeDefId() == null || changeDef.getSalaryChangeDefId() == 0){
			salaryChangeDefMapper.insertSelective(changeDef);
		}else{
			salaryChangeDefMapper.updateByPrimaryKey(changeDef);
			salaryChangeDefItemMapper.deleteByDefId(changeDef.getSalaryChangeDefId());
		}
		
		for(EhrSalaryChangeDefItem item : changeDef.getItemList()){
			item.setSalaryChangeDefId(changeDef.getSalaryChangeDefId());
			salaryChangeDefItemMapper.insert(item);
		}
		
		return 1;
	}

	@Override
	public int deleteById(Long companyId, Long salaryChangeDefId) {
		return salaryChangeDefMapper.logicDeleteById(companyId, salaryChangeDefId);
	}

	@Override
	public EhrSalaryChangeDef selectByPrimaryKey(Long salaryChangeDefId) {
		return salaryChangeDefMapper.selectByPrimaryKey(salaryChangeDefId);
	}

	@Override
	public List<EhrSalaryChangeDefItem> queryItemList(Long salaryChangeDefId) {
		return salaryChangeDefItemMapper.listByDefId(salaryChangeDefId);
	}
	
}
