package com.jabava.service.hro.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.hro.EfSalaryInfoMapper;
import com.jabava.pojo.hro.EfSalaryInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfSalaryInfoService;
import com.jabava.utils.Page;

@Service
public class EfSalaryInfoServiceImpl implements IEfSalaryInfoService{
	@Autowired
	private EfSalaryInfoMapper salaryInfoMapper;
	
    public int insertOrUpdateList(List<EfSalaryInfo> recordList){
    	for(EfSalaryInfo info : recordList){
    		EfSalaryInfo exist = salaryInfoMapper.selectByUniqueCond(info);
    		if(exist == null){
    			salaryInfoMapper.insertSelective(info);
    		}else{
    			info.setId(exist.getId());
    			salaryInfoMapper.updateByPrimaryKeySelective(info);
    		}
    	}
    	
    	return 1;
    }
    
    public List<Map<String,Object>> querySalaryInfoPage(EhrUser user,Page<Map<String, Object>> page,
    		String ym,String search, String orderBy) throws Exception{
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("companyId", user.getCompanyId());
    	params.put("salaryYm", ym);
    	params.put("search", search);
    	params.put("orderBy", orderBy);
    	params.put("page", page);
    	return salaryInfoMapper.querySalaryInfoPage(params);
    }
}