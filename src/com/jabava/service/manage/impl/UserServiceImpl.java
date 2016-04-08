/**
 * 
 */
package com.jabava.service.manage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.manage.EhrButtonMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrMenuMapper;
import com.jabava.dao.manage.EhrRoleMapper;
import com.jabava.dao.manage.EhrSysLogMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.manage.EhrButton;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrSysLog;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.service.system.impl.EhrOrganizationServiceImpl;
import com.jabava.service.system.impl.EhrRoleServiceImpl;
import com.jabava.utils.Constants;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.Tools;

/**
 * @author WangYongqiang
 * 
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Resource
	private EhrUserMapper userMapper;

	@Resource
	private EhrCompanyMapper companyMapper;

	@Resource
	EhrButtonMapper buttonMapper;

	@Resource
	EhrMenuMapper menuMapper;

	@Resource
	EhrSysLogMapper logMapper;
	
	@Resource
	EhrRoleMapper roleMapper;
	
	@Autowired
	private CenterUserClientService centerUserClientService;
	
	@Resource
	private IEhrRoleService ehrRoleService;
	
	@Resource
	private IEhrOrganizationService organizationService;
	
	@Override
	/**
	 * 检查数据并注册公司及管理员；如果是SSO，则需要同步到用户中心
	 */
	public Map<String,Object> register(EhrCompany company, EhrUser user, String password) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		
		String result = this.register(company, user);
		if(!StringUtils.isEmpty(result)){
			throw new JabavaServiceException(result);
		}
		
		if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
			//用户中心注册
			Map<String,Object> rr = centerUserClientService.registerUser(user,company.getCompanyName(),password);
			if("false".equals(rr.get("success"))){
				//userService.delUser(user.getUserId());
				//companyService.delCompany(company.getCompanyId());
				throw new JabavaServiceException(rr.get("msg").toString());
			}else{
				data.put("orgId", rr.get("orgId"));
			}
		}
		
		return data;
	}
	/**
	 * 检查数据并注册公司及管理员
	 * @param company
	 * @param user
	 * @throws Exception
	 */
	public String register(EhrCompany company,EhrUser user) throws Exception{
		//校验(公司、用户、手机、邮箱)
		if(StringUtils.isEmpty(company.getCompanyName())){
			//throw new JabavaServiceException("公司名为空");
			return "公司名为空";
		}
		EhrCompany existCompany = companyMapper.getCompanyIdByName(company.getCompanyName());
		if(existCompany != null){
			//throw new JabavaServiceException("公司名已存在");
			return "公司名已存在";
		}

		if(StringUtils.isEmpty(user.getUserCode())){
			//throw new JabavaServiceException("用户编号为空");
			return "用户编号为空";
		}
		EhrUser existUser = userMapper.searchUserByUserCode(user.getUserCode());
		if(existUser != null){
			//throw new JabavaServiceException("用户编号已存在");
			return "用户编号已存在";
		}

		if(StringUtils.isEmpty(user.getMobile())){
			//throw new JabavaServiceException("手机号为空");
			return "手机号为空";
		}
		EhrUser existOfMobile = userMapper.searchUserByUserMobile(user.getMobile());
		if(existOfMobile != null){
			//throw new JabavaServiceException("手机号已存在");
			return "手机号已存在";
		}

		if(!StringUtils.isEmpty(user.getMailAddress())){
			EhrUser existOfEmail = userMapper.searchUserByUserEmail(user.getMailAddress());
			if(existOfEmail != null){
				//throw new JabavaServiceException("邮箱已存在");
				return "邮箱已存在";
			}
		}
		
		// insert company
		companyMapper.insertSelective(company);
		
		// insert user
		user.setCompanyId(company.getCompanyId());
		userMapper.insertSelective(user);
		
		//添加角色并向管理员角色中添加用户
		ehrRoleService.addAdminPower(user);
		
		//为新公司添加顶层组织架构
		organizationService.inserTop(user,company.getCompanyName());
		
		return null;
	}

	@Override
	public HashMap login(String userCode, String passwd) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
//		map.put("flag", "0");
//		
//		EhrUser user = userMapper.validateUser(userCode);
//		if (user == null) {
//			map.put("logInfo", "用户编号为 " + userCode + " 的用户登录失败,不存在该用户编号");
//			map.put("viewInfo", "用户编号或密码错误,登录失败");
//		} else if (user.getIsLocked() != null && user.getIsLocked().intValue() == 1) {	// 用户已经disabled
//			map.put("logInfo", "用户编号为 " + userCode + " 的用户登录失败,该帐户已经被冻结");
//			map.put("viewInfo", "该帐户已经被冻结,登录失败");
//		} else {
//			int failtureTime = 5;
//			EhrCompany comp = companyMapper.selectByPrimaryKey(user.getCompanyId());
//			user.setCompanyCode(comp.getCompanyCode());
//			user.setCompany(comp);
//			//if (!user.getPassword().equals(Tools.encryptPassword(passwd)))// 密码不对
//			if (!user.getPassword().equals(passwd))	{	// 密码不对
//				map.put("logInfo", "用户编号为 " + userCode + " 的用户登录第 "
//						+ (user.getFailtureTime().intValue() + 1) + " 次失败,密码错误");
//				map.put("viewInfo", "公司号码、用户编号或密码错误,登录失败");
//				boolean isLock = (user.getFailtureTime().intValue() >= failtureTime - 1);
//				userMapper.lockUser(user.getUserId(), isLock);
//			} else {	// 密码正确
//				if (user.getFailtureTime().intValue() >= failtureTime) {	// 登录错误次数已经达到或超过5次
//					map.put("logInfo", "用户编号为 " + userCode + " 的用户密码正确,但之前已经连续 "
//							+ (user.getFailtureTime().intValue()) + " 次密码错误,所以登录失败");
//					map.put("viewInfo", "已经超过" + failtureTime
//							+ "次密码错误了,帐户已经被关闭,请找系统管理员开启帐户");
//				} else {
//					map.put("flag", "1");
//					map.put("logInfo", "用户编号为 " + userCode + " 的用户登录成功");
//					userMapper.updateUserloginTime(user.getUserId());
//					user.setButtonMap(getButtonMap(user.getUserId()));
//					map.put(Constants.LOGIN_USER, user);
//					map.put("POWER", getUrlsList(user));
//					map.put("ROLE", roleMapper.selectByUser(user.getUserId()));
//				}
//			}
//		}
		
		EhrUser user = userMapper.validateUser(userCode);
		EhrCompany comp = companyMapper.selectByPrimaryKey(user.getCompanyId());
		user.setCompanyCode(comp.getCompanyCode());
		user.setCompany(comp);
		user.setButtonMap(getButtonMap(user.getUserId()));
		
		userMapper.updateUserloginTime(user.getUserId());
		
		map.put("flag", "1");
		map.put(Constants.LOGIN_USER, user);
		map.put("POWER", getUrlsList(user));
		map.put("ROLE", roleMapper.selectByUser(user.getUserId()));
		map.put("logInfo", "用户编号为 " + userCode + " 的用户登录成功");
		
		return map;
	}

	public List<String> getUrlsList(EhrUser user) throws Exception {
		List<String> urls = new ArrayList<String>();
		List<EhrMenu> menus = getLeafMenuList(user);
		for (EhrMenu menu : menus) {
			urls.add(menu.getMenuUrl().trim());
		}
		List<EhrButton> buttons = getButtonList(user.getUserId());
		for (EhrButton button : buttons) {
			urls.add(button.getButtonUrl().trim());
		}
		return urls;
	}

	public List<EhrMenu> getLeafMenuList(EhrUser user) throws Exception {
		return menuMapper.getLeafMenu(user.getUserType(), user.getUserId());
	}

	public Map<String, EhrButton> getButtonMap(Long userId) throws Exception {
		Map<String, EhrButton> buttonMap = new HashMap<String, EhrButton>();
		List<EhrButton> list = getButtonList(userId);
		for (EhrButton button : list) {
			buttonMap.put(button.getButtonCode(), button);
		}
		return buttonMap;
	}

	public List<EhrButton> getButtonList(Long userId) throws Exception {
		return buttonMapper.queryButtonByUserId(userId);
	}

	@Override
	public int insertSysLog(EhrUser user, String logInfo) throws Exception {
		EhrSysLog log = new EhrSysLog();
		log.setUserId(user.getUserId());
		log.setUserName(user.getUserName());
		if (user.getCompany() != null) {
			log.setCompanyId(user.getCompany().getCompanyId());
			log.setCompanyCode(user.getCompany().getCompanyCode());
			log.setCompanyName(user.getCompany().getCompanyName());
		} else {
			log.setCompanyId(null);
			log.setCompanyCode(null);
			log.setCompanyName(null);
		}
		log.setOperateInfo(logInfo);
		log.setOperateDate(new Date());
		return logMapper.insertSelective(log);
	}

	@Override
	public Map<EhrMenu, List<EhrMenu>> menuTreeMap(EhrUser user)
			throws Exception {
		List<EhrMenu> menus = getLeafMenuList(user);
		Map<String, List<EhrMenu>> map = new HashMap<String, List<EhrMenu>>();
		String rootId = "";
		while (menus.size() > 0) {
			EhrMenu mdl = menus.remove(0);
			EhrMenu parentMdl = menuMapper
					.selectByPrimaryKey(mdl.getParentId());
			if (parentMdl != null) {
				List<EhrMenu> v = map.get(parentMdl.getMenuId().toString());
				if (v == null) {
					v = new ArrayList<EhrMenu>();
					v.add(mdl);
					map.put(parentMdl.getMenuId().toString(), v);
					menus.add(0, parentMdl);
				} else {
					v.add(mdl);
				}
			} else {
				rootId = mdl.getMenuId().toString();
			}
		}
		HashMap<EhrMenu, List<EhrMenu>> returnMap = new HashMap<EhrMenu, List<EhrMenu>>();
		if(StringUtils.isEmpty(rootId)){
			return returnMap;
		}
		
		EhrMenu root = menuMapper.selectByPrimaryKey(new Long(rootId));
		Stack<EhrMenu> stack = new Stack<EhrMenu>();
		stack.push(root);
		while (!stack.isEmpty()) {
			EhrMenu mdl = stack.pop();
			if (map.get(mdl.getMenuId().toString()) != null) {
				returnMap.put(mdl, map.get(mdl.getMenuId().toString()));
				List<EhrMenu> v = map.get(mdl.getMenuId().toString());
				if (v != null && v.size() != 0) {
					for (int i = 0; i < v.size(); i++) {
						stack.push(v.get(i));
					}
				}
			}
		}
		return returnMap;
	}

	@Override
	public String menuTreeStr(EhrUser user, HttpServletRequest request)
			throws Exception {
		Map<EhrMenu, List<EhrMenu>> map = menuTreeMap(user);
		Iterator<EhrMenu> it = map.keySet().iterator();
		EhrMenu root = null;
		while (it.hasNext()) {
			EhrMenu mdl = it.next();
			if (mdl.getParentId().longValue() == 0) {
				root = mdl;
				break;
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\r\n");
		sb.append("<tree id=\"0\">");
		if(root != null){
			long menuId = root.getMenuId();
			if (request.getParameter("menuId") != null) {
				menuId = new Long(request.getParameter("menuId")).longValue();
			}
			printMenuTree(sb, root, map, request.getContextPath(), menuId);
		}
		sb.append("</tree>");
		return sb.toString();
	}

	@Override
	public void printMenuTree(StringBuffer sb, EhrMenu mdl,
			Map<EhrMenu, List<EhrMenu>> map, String context, long menuId)
			throws Exception {
		sb.append("<item text=\"" + mdl.getMenuName() + "\" id=\""
				+ mdl.getMenuId() + "\" "
				+ (mdl.getMenuId().longValue() == menuId ? "open=\"1\"" : ""));
		List<EhrMenu> v = map.get(mdl);
		if (mdl.getMenuType().intValue() % 10 == 1) {
			if (v == null || v.size() == 0) {
				sb.append(" im0=\"folderOpen.gif\"");
			}
		}
		sb.append(">\r\n");
		if (mdl.getMenuType().intValue() % 10 == 0) {
			sb.append("<userdata name=\"url\">" + context + mdl.getMenuUrl()
					+ "</userdata>");
		}
		if (v != null && v.size() > 0) {
			for (int i = 0; i < v.size(); i++) {
				printMenuTree(sb, v.get(i), map, context, menuId);
			}
		}
		sb.append("</item>\r\n");
	}

	@Override
	public EhrUser searchUserByUserCode(String userCode) throws Exception {
		return userMapper.searchUserByUserCode(userCode);
	}

	@Override
	public Long searchUserByMobile(String mobile) throws Exception {
		Long userId = null;
		userId = userMapper.searchUserByMobile(mobile);
		return userId;
	}

	@Override
	public boolean addUser(EhrUser user) throws Exception {
		boolean result = false;
		try {
			int code = userMapper.insertSelective(user);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("新增用户失败.", e);
		}
		return result;
	}
	
	@Override
	public boolean delUser(Long id) throws Exception {
		boolean result = false;
		try {
			int code = userMapper.deleteByPrimaryKey(id);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("删除用户失败.", e);
		}
		return result;
	}

	@Override
	public EhrUser selectUserById(Long id) throws Exception {
		try {
			return userMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new Exception("查询用户实体失败.", e);
		}
	}

	@Override
	public String resetPwd(EhrUser user) throws Exception {
		String password = Tools.getInitialPassword();
		user.setPassword(Tools.encryptPassword(password)); 
		userMapper.resetPassword(user);
		return password;
	}

}
