package com.jabava.service.salary.impl;

import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryTemplateDetailMapper;
import com.jabava.dao.salary.EhrSalaryTemplateMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryTemplate;
import com.jabava.pojo.salary.EhrSalaryTemplateDetail;
import com.jabava.service.salary.ISalaryTemplateService;



@Service
public class SalaryTemplateServiceImpl implements ISalaryTemplateService{
	@Autowired
	private EhrSalaryTemplateMapper salaryTemplateMapper;
	@Autowired
	private EhrSalaryTemplateDetailMapper salaryTemplateDetailMapper;

	@Override
	public List<EhrSalaryTemplate> listSalaryTemplatePage(Map<String, Object> params) {
		return salaryTemplateMapper.listSalaryTemplatePage(params);
	}

	@Override
	public List<EhrSalaryTemplate> listSalaryTemplate(Long companyId) {
		return salaryTemplateMapper.listSalaryTemplate(companyId);
	}

	@Override
	public EhrSalaryTemplate selectByName(Long companyId, String templateName) {
		return salaryTemplateMapper.selectByName(companyId, templateName);
	}

	@Override
	public int saveOrUpdate(EhrSalaryTemplate salaryTemplate, EhrUser user, String[] ids) {
		salaryTemplate.setCompanyId(user.getCompanyId());
		if(salaryTemplate.getSalaryTemplateId() == null){
			salaryTemplateMapper.insertSelective(salaryTemplate);
		
		}else{
			salaryTemplateMapper.updateByPrimaryKey(salaryTemplate);
			
			//删除明细
			salaryTemplateDetailMapper.deleteByTemplateId(salaryTemplate.getSalaryTemplateId());

			//新增明细
			//if(!StringUtils.isEmpty(ids)){
			//	for(String id : ids.split(",")){
			if(ids != null && ids.length > 0){
				for(String id : ids){
					if(StringUtils.isEmpty(id)){
						continue;
					}
					
					EhrSalaryTemplateDetail detail = new EhrSalaryTemplateDetail();
					detail.setSalaryItemId(Long.valueOf(id));
					detail.setSalaryTemplateId(salaryTemplate.getSalaryTemplateId());
					salaryTemplateDetailMapper.insert(detail);
				}
			}
		}
		
		return 1;
	}

	@Override
	public int deleteById(Long companyId, Long salaryTemplateId) {
		salaryTemplateDetailMapper.deleteByTemplateId(salaryTemplateId);
		return salaryTemplateMapper.deleteById(companyId, salaryTemplateId);
	}

	@Override
	public EhrSalaryTemplate selectByPrimaryKey(Long salaryTemplateId) {
		return salaryTemplateMapper.selectByPrimaryKey(salaryTemplateId);
	}

	@Override
	public List<EhrSalaryTemplateDetail> loadDetailList(Long salaryTemplateId) {
		return salaryTemplateDetailMapper.loadDetailList(salaryTemplateId);
	}

	@Override
	public boolean hasReferencedItem(Long salaryItemId) {
		return salaryTemplateDetailMapper.countBySalaryItem(salaryItemId) > 0;
	}

	@Override
	public boolean hasReferencedTaxRate(Long taxRateId) {
		return salaryTemplateMapper.countByTaxRate(taxRateId) > 0;
	}

}
