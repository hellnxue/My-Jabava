package com.jabava.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jabava.dao.system.EHrMailConfigMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EHrMailConfig;
import com.jabava.pojo.system.MailProperties;
import com.jabava.service.system.IEhrMailConfigService;
import com.jabava.utils.security.MailSendUtils;
import com.jabava.utils.security.ThreeDesUtils;
@Service
public class EhrMailConfigServiceImpl implements IEhrMailConfigService {

	@Resource
	private EHrMailConfigMapper mailConfigMapper;
	
	@Override
	public boolean saveMailConfig(EhrUser user,EHrMailConfig mailConfig) throws Exception {
		if(null!=user.getCompanyId()){
			mailConfig.setCompanyId(user.getCompanyId());
		}
		if(StringUtils.isNotBlank(user.getUserName())){
			mailConfig.setCreateBy(user.getUserName());
		}
		mailConfig.setMailType("SMTP");
		if(!sendMail(mailConfig)){
			return false;
		}
		if(StringUtils.isNotBlank(mailConfig.getMailPassword())){
			mailConfig.setMailPassword(ThreeDesUtils.encryptMode(mailConfig.getMailPassword().getBytes()));
		}
		boolean result = false;
		try {
			int code = mailConfigMapper.saveMailConfig(mailConfig);
			if(code==1 || code==2){
				result = true;
			}
		} catch (Exception e) {
			result = false;
			throw new Exception("保存邮件配置信息失败.", e);
		}
		return result;
	}

	@Override
	public EHrMailConfig findByCompanyId(Long companyId) {
		EHrMailConfig mailConfig=mailConfigMapper.findByCompanyId(companyId);
		if(mailConfig!=null && StringUtils.isNotBlank(mailConfig.getMailPassword())){
			mailConfig.setMailPassword(ThreeDesUtils.decryptMode(mailConfig.getMailPassword()));
		}
		return mailConfig;
	}
	
	private boolean sendMail(EHrMailConfig mailConfig){
		MailProperties properties=new MailProperties();
		List<String> recipientList=new ArrayList<String>();
		if(StringUtils.isNotBlank(mailConfig.getMailServer())){
			properties.setHost(mailConfig.getMailServer());
		}
		if(StringUtils.isNotBlank(mailConfig.getSendTo())){
			properties.setUserName(mailConfig.getSendTo());
			recipientList.add(mailConfig.getSendTo());
		}
		if(StringUtils.isNotBlank(mailConfig.getMailPassword())){
			properties.setPassword(mailConfig.getMailPassword());
		}
		if(StringUtils.isNotBlank(mailConfig.getMailPort())){
			properties.setPort(mailConfig.getMailPort());
		}
		if(StringUtils.isNotBlank(mailConfig.getMailType())){
			properties.setType(StringUtils.lowerCase(mailConfig.getMailType()));
		}
		if(StringUtils.isNotBlank(mailConfig.getSafeFlag())){
			if(StringUtils.equals(mailConfig.getSafeFlag(), "1")){
				properties.setUseSSL(true);
			}else{
				properties.setUseSSL(false);
			}
		}
		properties.setContent("这是测试企业邮箱配置的测试邮件!");
		properties.setSubject("企业邮箱配置测试");
		
		if(recipientList.size()>0){
			properties.setSendTo(recipientList);
		}
		return MailSendUtils.sendMail(properties);
	}
}
