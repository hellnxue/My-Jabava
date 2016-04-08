package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrRole;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

public interface IEhrRoleService {
	/***
	 * 查询
	 * 
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	public List<EhrRole> searchEhrRole(Long companyId, String search,
			String order, String according, int isNumeric, Page<EhrRole> page) throws Exception;

	/***
	 * 新增用户角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean insertRole(EhrRole role) throws Exception;

	/***
	 * 修改前查询
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public EhrRole selectByPrimaryKey(Long roleId) throws Exception;

	/***
	 * 修改
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean updateRole(EhrRole role) throws Exception;

	/***
	 * 删除
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRole(Long roleId) throws Exception;

	public boolean deleteRolePower(Long roleId) throws Exception;

	public boolean deleteRoleUser(Long roleId) throws Exception;

	/**
	 * 分配人员初始
	 */
	public Map<String, Object> memberAllotEnter(EhrUser user, Long roleId)
			throws Exception;

	/**
	 * 分配人员
	 * 
	 * @param roleId
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	public String memberAllot(long roleId, String[] userList) throws Exception;
	
	public int addRoleUser(Long roleId, Long userId) throws Exception;
	
	public Map<String, Object> rolePower(Long roleId) throws Exception; 
	
	public void rolePowerSave(Long roleId, String[] buttons, String[] menus) throws Exception;
	
	public void addAdminPower(EhrUser user);
	
	public void addCommonRoleUser(EhrUser user);
	
	public void addHRRoleUser(EhrUser user);
}
