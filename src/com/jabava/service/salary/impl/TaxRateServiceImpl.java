package com.jabava.service.salary.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrTaxLevelMapper;
import com.jabava.dao.salary.EhrTaxRateMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrTaxLevel;
import com.jabava.pojo.salary.EhrTaxRate;
import com.jabava.service.salary.ITaxRateService;

@Service
public class TaxRateServiceImpl implements ITaxRateService{
	@Autowired
	private EhrTaxRateMapper taxRateMapper;
	
	@Autowired
	private EhrTaxLevelMapper taxLevelMapper;
	
	@Override
	public List<EhrTaxRate> listTaxRatePage(Map<String, Object> params) {
		return taxRateMapper.listTaxRatePage(params);
	}

	@Override
	public List<EhrTaxRate> listTaxRate(Long companyId) {
		return taxRateMapper.listTaxRate(companyId);
	}

	@Override
	public EhrTaxRate selectByName(Long companyId, String taxRateName) {
		return taxRateMapper.selectByName(companyId, taxRateName);
	}

	@Override
	public int saveOrUpdate(EhrTaxRate taxRate, EhrUser user) {
		taxRate.setCompanyId(user.getCompanyId());
		if(taxRate.getTaxRateId() == null){
			return taxRateMapper.insertSelective(taxRate);
		}else{
			return taxRateMapper.updateByPrimaryKey(taxRate);
		}
	}

	@Override
	public int deleteById(Long companyId, Long taxRateId) {
		taxLevelMapper.deleteByTaxRateId(taxRateId);
		taxRateMapper.deleteById(companyId, taxRateId);
		
		return 1;
	}

	@Override
	public EhrTaxRate selectByPrimaryKey(Long taxRateId) {
		return taxRateMapper.selectByPrimaryKey(taxRateId);
	}

	@Override
	public List<EhrTaxLevel> listTaxLevel(Long taxRateId) {
		return taxLevelMapper.queryByTaxRateId(taxRateId);
	}

	@Override
	public EhrTaxLevel loadTaxLevel(Long taxLevelId) {
		return taxLevelMapper.selectByPrimaryKey(taxLevelId);
	}

	@Override
	public int saveOrUpdateLevel(EhrTaxLevel taxLevel) {
		if(taxLevel.getTaxLevelId() == null){
			return taxLevelMapper.insertSelective(taxLevel);
		}else{
			return taxLevelMapper.updateByPrimaryKey(taxLevel);
		}
	}

	@Override
	public int deleteTaxLevel(Long taxLevelId) {
		return taxLevelMapper.deleteByPrimaryKey(taxLevelId);
	}
	
}
