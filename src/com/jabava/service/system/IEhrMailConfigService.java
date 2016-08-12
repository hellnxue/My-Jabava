package com.jabava.service.system;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EHrMailConfig;

/**
 * 
 * @author leiwei
 * 
 */
public interface IEhrMailConfigService {

	/**   
	 * 根据companyId插入或者更新企业邮件配置
	 */

	boolean saveMailConfig(EhrUser user,EHrMailConfig mailConfig) throws Exception;
	
	/**
	 * 根据公司Id获取企业邮箱配置信息
	 * @param companyId 公司ID
	 * @return  EHrMailConfig
	 */
	EHrMailConfig findByCompanyId(Long companyId);

}
