/**
 * 
 */
package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrFunctionPoint;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.utils.Page;

/**
 * @author pengyr
 * 
 */
public interface EhrUserInfoService {
	List<EhrUser> searchUser(Map<String, Object> map, String search, String order, String according, Page<EhrUser> page) throws Exception;
	
	Map<String, Object> addOrUpdateUser(EhrUser user) throws Exception;
	Map<String, Object> addOrUpdateHR(EhrUser user) throws Exception;
	
	Map<String, Object> pwdReset(EhrUser user, int entry) throws Exception;
	
	EhrUser getUserById(Long userId) throws Exception;
	
	String companyTree(Long companyId) throws Exception;
	
	public List<EhrPersonField> getPersonFieldList() throws Exception;
	
	public  EhrPersonField  getPersonFieldId(Integer fieldId) throws Exception;

	public List<EhrUserBusinessPower> getUserPowerList (Long userId) throws Exception;
	
	public List<EhrFunctionPoint> getFunctionPoint() throws Exception;
	
	public List<String[]> getSelectValueInfo(Integer fieldId,EhrUser user) throws Exception;
	
	public int deleteUserPersonPowerValue(Long userId);
	
	public int deleteUserBusinessPower(Long userId);
	
	public void insertBusinessPower(Long userId, String field) throws Exception;

	Map<String, Object> isDeleteUser(Long userId, Integer isDelete);

	Map<String, Object> deleteUser(Long userId, Integer isValid)
			throws Exception;
	
}
