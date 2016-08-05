/**
 * 
 */
package com.jabava.service.manage;

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

	public Map<String,Object> login(String userCode,String passwd ) throws Exception;

	public int insertSysLog(EhrUser user, String logInfo) throws Exception;
	
	public Map<EhrMenu,List<EhrMenu>> menuTreeMap(EhrUser user) throws Exception;
	
	public String menuTreeStr(EhrUser user, HttpServletRequest request) throws Exception;
	
	public List<EhrMenu> selectAuthorizedChildren(Long parentId, Long userId);
	
	public void printMenuTree(StringBuffer sb,EhrMenu mdl, Map<EhrMenu,List<EhrMenu>> map,String context,long menuId) throws Exception;

	public  EhrUser searchUserByUserCode(String userCode) throws Exception;
	
	public Long searchEUserByMobile(String mobile) throws Exception;
	
	public boolean addUser(EhrUser user) throws Exception;
	
	public boolean delUser(Long id) throws Exception;
	
	EhrUser selectUserById(Long id)throws Exception;
	
	public Map<String,Object> newPassword(EhrUser user, String password) throws Exception;
	
	public Map<String,Object> changePassword(EhrUser user, String oldPassword, String newPassword) throws Exception;
	
	public String resetPwd(EhrUser user, EhrUser affectUser) throws Exception;
	
	public String validateResetPasswordLink(Long userId, String code);
	
	public Map<String,Object> setPassword(EhrUser user, String code, String password) throws Exception;
	
	public boolean loginWidthPassword(String userCode,String password);
	
	public void asynchInitCompany(final EhrUser user);
	
	public int hasOpenService(EhrUser user, Long systemId);
	
	public Map<String,Object> openService(Long orgLoginId, Long systemId);
	
	public EhrUser validateUser(String userCode);
}
