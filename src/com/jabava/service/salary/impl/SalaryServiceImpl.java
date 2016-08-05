package com.jabava.service.salary.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrMonthlySalaryPersonMapper;
import com.jabava.dao.salary.EhrSalaryDetailMapper;
import com.jabava.dao.salary.EhrSalaryMapper;
import com.jabava.dao.salary.EhrSalaryTemplateDetailMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalary;
import com.jabava.pojo.salary.EhrSalaryDetail;
import com.jabava.pojo.salary.EhrSalaryTemplateDetail;
import com.jabava.service.salary.ISalaryService;

@Service
public class SalaryServiceImpl implements ISalaryService{
	@Autowired
	private EhrSalaryMapper salaryMapper;
	@Autowired
	private EhrSalaryDetailMapper salaryDetailMapper;
	@Autowired
	private EhrSalaryTemplateDetailMapper templateDetailMapper;
	@Autowired
	private EhrMonthlySalaryPersonMapper monthlySalaryPersonMapper;
	
	@Override
	public List<EhrSalary> listSalaryPage(Map<String, Object> params) {
		return salaryMapper.listSalaryPage(params);
	}

	@Override
	public EhrSalary selectByPersonAndUsage(Long companyId, Long personId, Integer usageFlag) {
		return salaryMapper.selectByPersonAndUsage(companyId, personId, usageFlag);
	}

	@Override
	public int saveOrUpdate(EhrSalary salary, EhrUser user) {
		List<EhrSalaryTemplateDetail> stdList = templateDetailMapper.loadDetailList(salary.getSalaryTemplateId());
		if(salary.getSalaryId() == null){
			salaryMapper.insertSelective(salary);
			//自动添加模板下面的所有工资项目
			for(EhrSalaryTemplateDetail std : stdList){
				EhrSalaryDetail detail = new EhrSalaryDetail(salary.getSalaryId(),std.getSalaryItemId(),std.getSalaryItemName());
				detail.setAmount(new BigDecimal(0));
				detail.setPayRadio(new BigDecimal(1));
				detail.setBonusBase(new BigDecimal(0));
				salaryDetailMapper.insertSelective(detail);
			}
			
		}else{
			salaryMapper.updateByPrimaryKey(salary);
			//将新模板的所有明细放到Map中
			Map<Long,EhrSalaryTemplateDetail> stdMap = new HashMap<Long,EhrSalaryTemplateDetail>();
			for(EhrSalaryTemplateDetail std : stdList){
				stdMap.put(std.getSalaryItemId(),std);
			}
			
			for(EhrSalaryDetail sd : salaryDetailMapper.loadDetailList(salary.getSalaryId())){
				if(stdMap.containsKey(sd.getSalaryItemId())){
					//新模板里有的则保留
					stdMap.remove(sd.getSalaryItemId());
				}else{
					//新模板里没有的则删除
					salaryDetailMapper.deleteByPrimaryKey(sd.getSalaryDetailId());
				}
			}
			
			//新模板里多出的则添加
			for(EhrSalaryTemplateDetail std : stdMap.values()){
				EhrSalaryDetail detail = new EhrSalaryDetail(salary.getSalaryId(),std.getSalaryItemId(),std.getSalaryItemName());
				detail.setAmount(new BigDecimal(0));
				detail.setPayRadio(new BigDecimal(1));
				detail.setBonusBase(new BigDecimal(0));
				salaryDetailMapper.insertSelective(detail);
			}
			
		}
		return 1;
	}
	
	@Override
	public int updateDetailList(List<EhrSalary> salaryList){
		for(EhrSalary salary : salaryList){
			if(salary.getDetailList() == null || salary.getDetailList().isEmpty()){
				continue;
			}
			
			for(EhrSalaryDetail detail : salary.getDetailList()){
				//只更新金额
				salaryDetailMapper.updateByPrimaryKeySelective(detail);
			}
		}
		
		return 1;
	}

	@Override
	public int deleteById(Long companyId, Long salaryId) {
		salaryDetailMapper.deleteBySalaryId(salaryId);
		return salaryMapper.deleteById(companyId, salaryId);
	}

	@Override
	public EhrSalary selectById(Long companyId, Long salaryId) {
		return salaryMapper.selectById(companyId, salaryId);
	}

	@Override
	public List<EhrSalaryDetail> loadDetailList(Long salaryId) {
		return salaryDetailMapper.loadDetailList(salaryId);
	}

	@Override
	public Map<String, EhrSalaryDetail> getDetailMap(Long salaryId) {
		List<EhrSalaryDetail> detailList = loadDetailList(salaryId);
		Map<String,EhrSalaryDetail> detailMap = new HashMap<String,EhrSalaryDetail>();
		for(EhrSalaryDetail detail : detailList){
			detailMap.put(detail.getSalaryItemName(), detail);
		}
		return detailMap;
	}

	@Override
	public int updateSalaryDetail(String name, String value, String pk) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("value", value);
		params.put("pk", pk);
		return salaryDetailMapper.updateByParam(params);
	}

	@Override
	public boolean hasReferencedItem(Long salaryItemId) {
		return salaryDetailMapper.countBySalaryItem(salaryItemId) > 0;
	}

	@Override
	public boolean hasReferencedTemplate(Long salaryTemplateId) {
		return salaryMapper.countBySalaryTemplate(salaryTemplateId) > 0;
	}

	@Override
	public boolean hasReferencedPerson(Long personId) {
		return salaryMapper.countByPerson(personId) > 0;
	}

	@Override
	public List<Map<String, Object>> listPayoffHistoryPage(
			Map<String, Object> params) {
		return monthlySalaryPersonMapper.listPayoffHistoryPage(params);
	}
	
}
