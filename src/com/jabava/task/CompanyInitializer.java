package com.jabava.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;

@Service
public class CompanyInitializer {
	private Logger log = Logger.getLogger(CompanyInitializer.class);
	
	@Autowired
	private EhrBaseDataMapper baseDataMapper;
	
	public boolean execute(EhrUser user){
		//初始化基础数据
		this.initBaseData(user);

		return true;
	}
	
	private boolean initBaseData(EhrUser user){
		int count = baseDataMapper.countByCompany(user.getCompanyId());
		if(count > 0){
			log.warn("公司初始化数据已经导入：" + user.getCompanyId());
			return true;
		}
		List<Map<String,Object>> bdList = baseDataMapper.selectFromTemplate();
		EhrBaseData baseData = null;
		Date now = new Date();
		for(Map<String,Object> bd : bdList){
			baseData = new EhrBaseData();
			baseData.setBaseDataType(Integer.valueOf(bd.get("base_data_type").toString()));
			baseData.setBaseDataCode((String)bd.get("base_data_code"));
			baseData.setBaseDataName((String)bd.get("base_data_name"));
			baseData.setMemo((String)bd.get("memo"));
			
			baseData.setIsValid(Byte.valueOf("1"));
			baseData.setCompanyId(user.getCompanyId());
			baseData.setCreateDate(now);
			baseData.setCreateUserId(user.getUserId());
			baseData.setCreateUserName(user.getUserName());
			baseData.setLastModifyDate(now);
			baseData.setLastModifyUserId(user.getUserId());
			baseData.setLastModifyUserName(user.getUserName());
			
			baseDataMapper.insert(baseData);
		}
		
		return true;
	}
}
