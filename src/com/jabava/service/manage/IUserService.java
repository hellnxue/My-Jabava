/**
 * 
 */
package com.jabava.service.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrUser;

/**
 * @author WangYongqiang
 * 
 */
public interface IUserService {
	public Map<String,Object> register(EhrCompany company, EhrUser user, String password) throws Exception;
	
	public String register(EhrCompany company,EhrUser user) throws Exception;

	public HashMap login(String userCode,String passwd ) throws Exception;

	public int insertSysLog(EhrUser user, String logInfo) throws Exception;
	
	public Map<EhrMenu,List<EhrMenu>> menuTreeMap(EhrUser user) throws Exception;
	
	public String menuTreeStr(EhrUser user, HttpServletRequest request) throws Exception;
	
	public void printMenuTree(StringBuffer sb,EhrMenu mdl, Map<EhrMenu,List<EhrMenu>> map,String context,long menuId) throws Exception;

	public  EhrUser searchUserByUserCode(String userCode) throws Exception;
	
	public Long searchUserByMobile(String mobile) throws Exception;
	
	public boolean addUser(EhrUser user) throws Exception;
	
	public boolean delUser(Long id) throws Exception;
	
	EhrUser selectUserById(Long id)throws Exception;
	
	public String resetPwd(EhrUser user) throws Exception;
	
}
