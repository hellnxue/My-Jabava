package com.jabava.service.salary.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryDateMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryDate;
import com.jabava.service.salary.ISalaryDateService;

@Service
public class SalaryDateServiceImpl implements ISalaryDateService {
	@Autowired
	private EhrSalaryDateMapper salaryDateMapper;

	@Override
	public List<EhrSalaryDate> listSalaryDatePage(Map<String, Object> params) {
		return salaryDateMapper.listSalaryDatePage(params);
	}

	@Override
	public int saveOrUpdate(EhrSalaryDate salaryDate, EhrUser user) {
		salaryDate.setCompanyId(user.getCompanyId());
		if(salaryDate.getSalaryDateId() == null){
			return salaryDateMapper.insert(salaryDate);
		}else{
			return salaryDateMapper.updateByPrimaryKey(salaryDate);
		}
	}

	@Override
	public int deleteById(Long companyId, Long salaryDateId) {
		return salaryDateMapper.deleteById(companyId, salaryDateId);
	}

	@Override
	public EhrSalaryDate selectByChangeDate(Long companyId, Date changeDate) {
		return salaryDateMapper.selectByChangeDate(companyId, changeDate);
	}

}
