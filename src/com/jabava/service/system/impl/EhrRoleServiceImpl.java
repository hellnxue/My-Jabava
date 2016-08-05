package com.jabava.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;
import javax.swing.ButtonModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrButtonMapper;
import com.jabava.dao.manage.EhrRoleMapper;
import com.jabava.dao.manage.EhrRolePowerMapper;
import com.jabava.dao.manage.EhrRoleUserMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.manage.EhrButton;
import com.jabava.pojo.manage.EhrRole;
import com.jabava.pojo.manage.EhrRolePower;
import com.jabava.pojo.manage.EhrRoleUser;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.RolePower;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.Constants;
import com.jabava.utils.Page;

@Service("roleService")
public class EhrRoleServiceImpl implements IEhrRoleService {

	@Resource
	public EhrRoleMapper ehrRoleMapper;
	@Resource
	public EhrUserMapper userMapper;
	@Resource
	public EhrRoleUserMapper roleUserMapper;
	@Resource
	public EhrRolePowerMapper rolePowerMapper;
	@Autowired
	public EhrButtonMapper buttonMapper;

	@Override
	public List<EhrRole> searchEhrRole(Long companyId, String search,
			String order, String according, int isNumeric, Page<EhrRole> page)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("companyId", companyId);
		map.put("search", search);
		map.put("order", order);
		map.put("according", according);
		map.put("isNumeric", isNumeric);
		map.put("page", page);
		List<EhrRole> roles = ehrRoleMapper.searchEhrRolePage(map);
		return roles;
	}

	@Override
	public boolean insertRole(EhrRole role) throws Exception {
		EhrRole exist = ehrRoleMapper.selectByRoleName(role.getCompanyId(),role.getRoleName());
		if(exist != null){
			throw new Exception("角色名称重复");
		}
		int count = ehrRoleMapper.insertSelective(role);
		return (1 == count);
	}

	@Override
	public EhrRole selectByPrimaryKey(Long roleId) throws Exception {
		EhrRole roleInfo = null;
		try {
			if (roleId != null) {
				roleInfo = ehrRoleMapper.selectByPrimaryKey(roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleInfo;
	}

	@Override
	public boolean updateRole(EhrRole role) throws Exception {
		EhrRole exist = ehrRoleMapper.selectByRoleName(role.getCompanyId(),role.getRoleName());
		if(exist != null && !role.getRoleId().equals(exist.getRoleId())){
			throw new Exception("角色名称重复");
		}
		int count=ehrRoleMapper.updateByPrimaryKeySelective(role);
		return (1 == count);
	}

	@Override
	public boolean deleteRole(Long roleId) throws Exception {
		boolean result = false;
		try {
			int count=ehrRoleMapper.deleteRole(roleId);
			result = (1 == count);
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	@Override
	public boolean deleteRolePower(Long roleId) throws Exception {
		boolean result = false;
		try {
			int count=ehrRoleMapper.deleteRolePower(roleId);
			result = (1 == count);
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	@Override
	public boolean deleteRoleUser(Long roleId) throws Exception {
		boolean result = false;
		try {
			int count=ehrRoleMapper.deleteRoleUser(roleId);
			result = (1 == count);
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	@Override
	public Map<String, Object> memberAllotEnter(EhrUser user, Long roleId)
			throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userSelected", userMapper.userSelected(user.getCompanyId(), roleId));
		map.put("userNotSelected", userMapper.userNotSelected(user.getCompanyId(), roleId));
		map.put("roleInfo", ehrRoleMapper.selectByPrimaryKey(roleId));
		return map;
	}

	@Override
	public String memberAllot(long roleId, String[] userList) throws Exception {
		ehrRoleMapper.deleteRoleUser(roleId);
		EhrRole role = ehrRoleMapper.selectByPrimaryKey(roleId);
		String logInfo = "将角色 "+role.getRoleName()+" 的成员更改为 ";
		for(int i = 0; i < userList.length; i++){
			if(userList[i] != null && !userList[i].equals("")){
				logInfo += userMapper.selectByPrimaryKey(Long.valueOf(userList[i])).getUserName()+" ";
				EhrRoleUser mdl=new EhrRoleUser();
				mdl.setUserId(Long.valueOf(userList[i]));
				mdl.setRoleId(roleId);
				roleUserMapper.insertSelective(mdl);
			}
		}
		return logInfo;
	}

	@Override
	public int addRoleUser(Long roleId, Long userId) throws Exception {
		EhrRoleUser mdl=new EhrRoleUser();
		mdl.setUserId(userId);
		mdl.setRoleId(roleId);
		return roleUserMapper.insertSelective(mdl);
	}
	
	/**
	 * 为新公司添加角色，并为管理员赋角色
	 */
	public void addAdminPower(EhrUser user) {
		Date now = new Date();
		this.addRolePower("企业管理员","ADMIN_POWER",user,now);
		this.addRolePower("普通用户","COMMON_POWER",user,now);
		this.addRolePower("HR","HR_POWER",user,now);
		
		
		this.addRoleUser("企业管理员", user);
	}
	
	public void addAdminRoleUser(EhrUser user){
		this.addRoleUser("企业管理员", user);
	}
	
	public void addCommonRoleUser(EhrUser user){
		this.addRoleUser("普通用户", user);
	}
	
	public void addHRRoleUser(EhrUser user){
		this.addRoleUser("HR", user);
	}
	
	private void addRolePower(String roleName,String powerKey,EhrUser user,Date now){
		EhrRole role = new EhrRole();
		role.setCompanyId(user.getCompanyId());
		role.setRoleName(roleName);
		role.setCreateUserId(user.getUserId());
		role.setCreateUserName(user.getUserName());
		role.setCreateDate(now);
		role.setLastModifyUserId(user.getUserId());
		role.setLastModifyUserName(user.getUserName());
		role.setLastModifyDate(now);
		ehrRoleMapper.insertSelective(role);
		
		String powerString = JabavaPropertyCofigurer.getProperty(powerKey);
		if(!StringUtils.isEmpty(powerString)){
			for(String power : powerString.split(",")){
				EhrRolePower rp = new EhrRolePower();
				rp.setRoleId(role.getRoleId());
				rp.setPowerId(Long.valueOf(power));
				rp.setType(1);
				rolePowerMapper.insertSelective(rp);
				
				this.addButtonPower(role, Long.parseLong(power));
			}
			
			if("企业管理员".equals(roleName) || "HR".equals(roleName)){
				this.addButtonPower(role, Constants.TOP_MENU_ID);
			}
		}
	}
	
	private void addButtonPower(EhrRole role, Long menuId){
		//添加按钮权限
		for(EhrButton btn : buttonMapper.queryButtonByMenuId(menuId)){
			EhrRolePower rButton = new EhrRolePower();
			rButton.setRoleId(role.getRoleId());
			rButton.setPowerId(btn.getButtonId());
			rButton.setType(2);
			rolePowerMapper.insertSelective(rButton);
		}
	}
	
	private void addRoleUser(String roleName,EhrUser user){
		EhrRole paramRole = new EhrRole();
		paramRole.setCompanyId(user.getCompanyId());
		paramRole.setRoleName(roleName);
		List<EhrRole> roleList = ehrRoleMapper.selectByRole(paramRole);
		if(roleList != null && !roleList.isEmpty()){
			EhrRoleUser ru = new EhrRoleUser();
			ru.setUserId(user.getUserId());
			ru.setRoleId(roleList.get(0).getRoleId());
			roleUserMapper.insertSelective(ru);
		}
	}

	//--------------------------------权限设置开始------------------------------
	@Override
	public Map<String, Object> rolePower(Long roleId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RolePower button = roleButtonPower(roleId);
		RolePower menu = menuPowerTree(roleId);
		EhrRole role = ehrRoleMapper.selectByPrimaryKey(roleId);
		map.put("role", role);
		map.put("button", button);
		map.put("menu", menu);
		return map;
	}
	
	//--------------------------------按钮权限开始------------------------------
	public RolePower roleButtonPower(Long roleId) throws Exception{
		Map<RolePower, List<RolePower>> menuMap = getMenuTreeMap();
		Map<String, List<RolePower>> pageMap = getMenuPagesMap();
		Map<String, List<RolePower>> buttonMap = getPageButtonsMap();
		Iterator<RolePower> it = menuMap.keySet().iterator();
		RolePower root=null;
		while(it.hasNext()){
			RolePower mdl = it.next();
			if(mdl.getpId().longValue()==0){
				root=mdl;
				break;
			}
		}
		long[] buttonPower = getPowerListByRole(roleId,2);
		buttonPowerTree(menuMap, pageMap, buttonMap, root, buttonPower);
		return root;
	}
	
	public Map<RolePower, List<RolePower>> getMenuTreeMap() throws Exception{
		Map<RolePower, List<RolePower>> map = new HashMap<RolePower, List<RolePower>>();
		List<RolePower> tree = ehrRoleMapper.getMenu();
		RolePower root = ehrRoleMapper.getZeroMenu();
    	Stack<RolePower> stack = new Stack<RolePower>();
    	stack.push(root);
    	while(!stack.isEmpty()){
    		RolePower mdl = stack.pop();
    		if(mdl.getMenuType().intValue()%10==1){
        		List<RolePower> children = this.getChildrenByParent(mdl.getId(), tree);
        		map.put(mdl, children);
            	for(int i=0;i<children.size();i++){
            		stack.push(children.get(i));
            	}
    		}
    		
    	}
    	return map;
    }
	
	public List<RolePower> getChildrenByParent(long parentId, List<RolePower> tree){
		List<RolePower> v = new ArrayList<RolePower>();
    	for(int i = 0; i < tree.size(); i++){
    		RolePower mdl = tree.get(i);
    		if(mdl.getpId().intValue()==parentId){
    			v.add(mdl);
    			tree.remove(i);
    			i--;
    		}
    	}
    	return v;
    }
	
	public Map<String, List<RolePower>> getMenuPagesMap() throws Exception{
    	List<RolePower> v = ehrRoleMapper.getPages();
    	Map<String,List<RolePower>> map = new HashMap<String,List<RolePower>>();
    	for(int i = 0;i < v.size();i++){
    		RolePower mdl = v.get(i);
    		if(map.get(mdl.getpId().toString()) == null){
    			List<RolePower> pages = new ArrayList<RolePower>();
    			pages.add(mdl);
    			map.put(mdl.getpId().toString(), pages);
    		}else{
    			List<RolePower> pages = map.get(mdl.getpId().toString());
    			pages.add(mdl);
    		}
    	}
    	return map;
    }
	
	public Map<String, List<RolePower>> getPageButtonsMap() throws Exception{
		List<RolePower> v = ehrRoleMapper.getPageButton();
		Map<String, List<RolePower>> map = new HashMap<String, List<RolePower>>();
    	for(int i = 0; i < v.size(); i++){
    		RolePower mdl = v.get(i);
    		if(map.get(mdl.getpId().toString()) == null){
    			List<RolePower> buttons = new ArrayList<RolePower>();
    			buttons.add(mdl);
    			map.put(mdl.getpId().toString(), buttons);
    		}else{
    			List<RolePower> buttons = map.get(mdl.getpId().toString());
    			buttons.add(mdl);
    		}
    	}
    	return map; 
    }
	
	public long[] getPowerListByRole(Long roleId, int type) throws Exception{
    	List<EhrRolePower> v = ehrRoleMapper.getPowerListByRole(roleId, type);
    	long[] l=new long[v.size()];
    	for(int i=0;i<v.size();i++){
    		EhrRolePower mdl = v.get(i);
    		l[i] = mdl.getPowerId().longValue();
    	}
    	return l;
    }
	
	public void buttonPowerTree(Map<RolePower, List<RolePower>> menuMap, Map<String, List<RolePower>> pageMap, Map<String, List<RolePower>> buttonMap, RolePower mdl,long[] buttonPower) throws Exception{
		if(mdl.getMenuType().intValue() == 31 || mdl.getMenuType().intValue() == 30 || mdl.getMenuType().intValue() == 1){
			int type = 0;	//非叶节点
			if(mdl.getMenuType().intValue() == 30){
				type=1;		//叶节点
			}
			
			if(type == 1 || mdl.getMenuType().intValue() == 1){	//只有叶节点和根节点才有page
				List<RolePower> pages = pageMap.get(mdl.getId().toString());
				if(pages!=null){
					for(int i=0;i<pages.size();i++){
						RolePower page = pages.get(i);
						List<RolePower> buttons = buttonMap.get(page.getId().toString());
						if(buttons != null){
							for(long power : buttonPower){
								for(RolePower rolePower : buttons){
									if(power == rolePower.getId()){
										rolePower.setChecked(true);
										break;
									}
								}
							}
							page.setNodes(buttons);
						}
					}
					//mdl.setNodes(pages);
					mdl.addNodes(pages);
				}
			}

			List<RolePower> v = menuMap.get(mdl);
			if(v != null && v.size() > 0){
				List<RolePower> list = new ArrayList<RolePower>();
				for(int i = 0; i < v.size(); i++){
					if(v.get(i).getMenuType().intValue() == 31 || v.get(i).getMenuType().intValue() == 30 || v.get(i).getMenuType().intValue() == 1){
						list.add(v.get(i));
					}
					buttonPowerTree(menuMap, pageMap, buttonMap, v.get(i), buttonPower);
				}
				//mdl.setNodes(list);
				mdl.addNodes(list);
			}
		}	
	}
	//--------------------------------按钮权限结束------------------------------
	
	//--------------------------------菜单权限开始------------------------------
	public RolePower menuPowerTree(Long roleId) throws Exception{
		Map<RolePower, List<RolePower>> map = getMenuTreeMap();
		long[] menuPower = getPowerListByRole(new Long(roleId),1);
		Iterator<RolePower> it = map.keySet().iterator();
		RolePower root=null;
		while(it.hasNext()){
			RolePower mdl = it.next();
			if(mdl.getpId().longValue()==0){
				root=mdl;
				break;
			}
		}
		printMenuPowerTree(root, map, menuPower);
		return root;
	}
	
	public void printMenuPowerTree(RolePower mdl, Map<RolePower, List<RolePower>> menuMap,long[] menuPower) throws Exception{
		if(mdl.getMenuType().intValue()==31 || mdl.getMenuType().intValue()==30 ||mdl.getMenuType().intValue()==1){
			if(mdl.getMenuType().intValue()==30){
				mdl.setType("menu_30");
			}
			if(this.isContains(menuPower, mdl.getId().longValue())){
				mdl.setChecked(true);
			}
			List<RolePower> v = menuMap.get(mdl);
			if(v != null && v.size() > 0){
				List<RolePower> list = new ArrayList<RolePower>();
				for(int i=0;i<v.size();i++){
					if(v.get(i).getMenuType().intValue() == 31 || v.get(i).getMenuType().intValue() == 30 || v.get(i).getMenuType().intValue() == 1){
						list.add(v.get(i));
					}
					printMenuPowerTree(v.get(i),menuMap,menuPower);
				}
				mdl.setNodes(list);
			}
		}		
	}
	
	public boolean isContains(long[] l,long id){
		if(l == null)
			return false;
		else{
			for(int i = 0; i < l.length; i++){
				if(l[i] == id)
					return true;
			}
			return false;
		}
	}
	//--------------------------------菜单权限结束------------------------------

	@Override
	public void rolePowerSave(Long roleId, String[] buttons, String[] menus)
			throws Exception {
		ehrRoleMapper.delRolePower(roleId);
		//按钮权限
		if(buttons != null && buttons.length > 0){
	    	for(int i = 0; i < buttons.length; i++){
	    		EhrRolePower mdl = new EhrRolePower();
	    		mdl.setRoleId(new Long(roleId));
	    		mdl.setPowerId(new Long(buttons[i]));
	    		mdl.setType(new Integer(2));
	    		rolePowerMapper.insertSelective(mdl);
	    	}
		}
    	//菜单权限
		if(menus != null && menus.length > 0){
	    	for(int i = 0;i < menus.length;i++){
	    		EhrRolePower mdl = new EhrRolePower();
	    		mdl.setRoleId(new Long(roleId));
	    		mdl.setPowerId(new Long(menus[i]));
	    		mdl.setType(new Integer(1));
	    		rolePowerMapper.insertSelective(mdl);
	    	}
		}
	}
	//--------------------------------权限设置结束------------------------------
}
