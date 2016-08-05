package com.jabava.service.salary.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryChangeDefItemMapper;
import com.jabava.dao.salary.EhrSalaryChangeDefMapper;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;
import com.jabava.service.salary.ISalaryChangeDefService;
import com.jabava.utils.SalaryHelper;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.enums.SalaryEnum.SystemChangeTable;

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
	public EhrSalaryChangeDef selectById(Long companyId, Long salaryChangeDefId, boolean withItem) {
		EhrSalaryChangeDef result = null;
		SystemChangeTable ct = null;
		if(salaryChangeDefId.equals(SalaryEnum.SystemChangeTable.Attendance.getId())){
			ct = SalaryEnum.SystemChangeTable.Attendance;
			result = new EhrSalaryChangeDef();
			result.setSalaryChangeDefId(salaryChangeDefId);
			result.setCompanyId(companyId);
			result.setName(ct.getDisplayName());
			result.setKeyInfo("工号");
			result.setKeyType(SalaryEnum.SalaryChangeDefKeyType.JobNumber.getValue());
			if(withItem && result != null){
				List<EhrSalaryChangeDefItem> itemList = new ArrayList<EhrSalaryChangeDefItem>();
				itemList.addAll(SalaryHelper.getAttendanceDefination());
				itemList.add(0, new EhrSalaryChangeDefItem("工号","job_number",1));
				result.setItemList(itemList);
			}
		}else{
			result = salaryChangeDefMapper.selectById(companyId, salaryChangeDefId);
			if(withItem && result != null){
				result.setItemList(salaryChangeDefItemMapper.listByDefId(salaryChangeDefId));
			}
		}
		return result;
	}

	@Override
	public EhrSalaryChangeDef selectByName(Long companyId, String name) {
		return salaryChangeDefMapper.selectByName(companyId, name);
	}

	@Override
	public int saveOrUpdate(EhrSalaryChangeDef changeDef) {
		if(changeDef.getSalaryChangeDefId() == null || changeDef.getSalaryChangeDefId().longValue() == 0){
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
