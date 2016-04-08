package com.jabava.service.system;

import java.util.List;

import com.jabava.pojo.system.EhrSysConfig;

/**
 * 系统配置Service
 */
public interface IEhrSysConfigSercice {
	List<EhrSysConfig> loadSysConfigList();
	
	EhrSysConfig loadSysConfig(String code);
	
}
