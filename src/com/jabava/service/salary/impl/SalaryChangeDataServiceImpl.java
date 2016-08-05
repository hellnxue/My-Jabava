package com.jabava.service.salary.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryChangeDataMapper;
import com.jabava.pojo.salary.EhrSalaryChangeData;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.service.salary.ISalaryChangeDataService;

@Service
public class SalaryChangeDataServiceImpl implements ISalaryChangeDataService {
	@Autowired
	private EhrSalaryChangeDataMapper salaryChangeDataMapper;
	
	@Override
	public List<Map<String, Object>> listSalaryChangeDataPage(
			Map<String, Object> params) {
		return salaryChangeDataMapper.listSalaryChangeDataPage(params);
	}
	
	@Override
	public List<Map<String, Object>> listSalaryChangeData(
			Map<String, Object> params) {
		return salaryChangeDataMapper.listSalaryChangeData(params);
	}

	@Override
	public int saveOrUpdateSalaryChangeDataList(Long companyId, String monthly, EhrSalaryChangeDef def, 
			List<EhrSalaryChangeData> salaryChangeDataList) {
		//salaryChangeDataMapper.deleteByMonthAndDef(companyId, monthly, salaryChangeDefId);
		for(EhrSalaryChangeData data : salaryChangeDataList){
			if(def.getIsMonthly() == 1){
				salaryChangeDataMapper.insertSelective(data);
			}else{
				EhrSalaryChangeData existData = salaryChangeDataMapper.selectByDefAndPerson(data.getSalaryChangeDefId(),data.getPersonId());
				if(existData == null){
					int result = salaryChangeDataMapper.insertSelective(data);
					if(result>0){
						
					}
				}else{
					data.setSalaryChangeDataId(existData.getSalaryChangeDataId());
					salaryChangeDataMapper.updateByPrimaryKeySelective(data);
				}
			}
		}
		return 1;
	}

	@Override
	public int deleteById(Long companyId, Long salaryChangeDataId) {
		
		return salaryChangeDataMapper.deleteById(companyId, salaryChangeDataId);
	}

	@Override
	public int removeSalaryChangeData(Long companyId, String monthly, EhrSalaryChangeDef def) {
		if(def.getIsMonthly() == 1){
			return salaryChangeDataMapper.deleteByMonthAndDef(companyId, monthly, def.getSalaryChangeDefId());
		}else{
			return salaryChangeDataMapper.deleteByDef(companyId, def.getSalaryChangeDefId());
		}
	}

	@Override
	public int updateSalaryChangeData(String name, String value, String pk) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("value", value);
		params.put("pk", pk);
		return salaryChangeDataMapper.updateByParam(params);
	}

}
