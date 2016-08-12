package com.jabava.dao.system;

import com.jabava.pojo.system.EHrMailConfig;

public interface EHrMailConfigMapper {
	/**
	 * 保存企业邮箱配置信息
	 * @param mailConfig
	 * @return
	 */
	int saveMailConfig(EHrMailConfig mailConfig);
	/**
	 * 根据公司ID获取企业邮箱配置信息
	 * @param companyId
	 * @return
	 */
	EHrMailConfig findByCompanyId(Long companyId);
}
