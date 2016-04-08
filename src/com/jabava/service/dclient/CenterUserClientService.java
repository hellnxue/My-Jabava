package com.jabava.service.dclient;

import java.util.Map;

import com.jabava.pojo.manage.EhrUser;

public interface CenterUserClientService {
	
	public Map<String,Object> registerUser(EhrUser user, String companyName,String password);
	
	public Map<String,Object> openUser(EhrUser user,String password);
	
	public Map<String,Object> openService(Long loginId, Long systemId, String paramData);
	
	public Map<String,Object> updateUser(EhrUser user);
}
