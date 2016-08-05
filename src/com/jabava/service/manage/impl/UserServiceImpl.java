/**
 * 
 */
package com.jabava.service.manage.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.common.EhrCommonDataMapper;
import com.jabava.dao.manage.EhrButtonMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrMenuMapper;
import com.jabava.dao.manage.EhrRoleMapper;
import com.jabava.dao.manage.EhrSysLogMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.dao.manage.EhrUserResetPasswordMapper;
import com.jabava.pojo.manage.EhrButton;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrSysLog;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserResetPassword;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.task.CompanyInitializer;
import com.jabava.utils.Constants;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaStringUtils;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Tools;
import com.jabava.utils.constants.ConfigConstants;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.security.MD5;
import com.service.provider.MobileService;

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
	private EhrButtonMapper buttonMapper;
	@Resource
	private EhrMenuMapper menuMapper;
	@Resource
	private EhrSysLogMapper logMapper;
	@Resource
	private EhrRoleMapper roleMapper;
	@Autowired
	private CenterUserClientService centerUserClientService;
	@Autowired
	private MobileService mobileService;	
	@Resource
	private IEhrRoleService ehrRoleService;
	@Resource
	private IEhrOrganizationService organizationService;
	@Autowired
	private EhrCommonDataMapper commonDataMapper;
	@Autowired
	private EhrUserResetPasswordMapper userResetPasswordMapper;
	@Autowired
	private CompanyInitializer companyInitializer;
	
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
			if("false".equals(rr.get("success").toString())){
				//userService.delUser(user.getUserId());
				//companyService.delCompany(company.getCompanyId());
				throw new JabavaServiceException(rr.get("msg").toString());
			}else{
				data.put("orgId", rr.get("orgId"));
				company.setUcOrgId((Long)rr.get("orgId"));
				companyMapper.updateByPrimaryKeySelective(company);
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
			return "用户名为空";
		}
		EhrUser existUser = userMapper.searchUserByUserCode(user.getUserCode());
		if(existUser != null){
			//throw new JabavaServiceException("用户编号已存在");
			return "用户名已存在";
		}

		if(StringUtils.isEmpty(user.getMobile())){
			//throw new JabavaServiceException("手机号为空");
			return "手机号为空";
		}
		
		//新公司不需判断重复
//		EhrUser existOfMobile = userMapper.searchEUserByUserMobile(user.getMobile());
//		if(existOfMobile != null){
//			//throw new JabavaServiceException("手机号已存在");
//			return "手机号已存在";
//		}
//
//		if(!StringUtils.isEmpty(user.getMailAddress())){
//			EhrUser existOfEmail = userMapper.searchEUserByUserEmail(user.getMailAddress());
//			if(existOfEmail != null){
//				//throw new JabavaServiceException("邮箱已存在");
//				return "邮箱已存在";
//			}
//		}
		
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

	public Map<String,Object> login(String userCode, String passwd) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
	
		EhrUser user = userMapper.validateUser(userCode);
		EhrCompany comp = companyMapper.selectByPrimaryKey(user.getCompanyId());
		user.setCompanyCode(comp.getCompanyCode());
		user.setCompany(comp);
		user.setButtonMap(getButtonMap(user.getUserId()));
		user.setMenuList(this.getLeafMenuList(user));
		
		userMapper.updateUserloginTime(user.getUserId());
		
		map.put("flag", "1");
		map.put(Constants.LOGIN_USER, user);
		//map.put("POWER", getUrlsList(user));
		map.put("ROLE", roleMapper.selectByUser(user.getUserId()));
		map.put("operateType", SystemEnum.LogOperateType.Login);
		map.put("module", SystemEnum.Module.Login);
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
	public List<EhrMenu> selectAuthorizedChildren(Long parentId, Long userId) {
		return menuMapper.selectAuthorizedChildren(parentId, userId);
	}
	@Override
	public EhrUser searchUserByUserCode(String userCode) throws Exception {
		return userMapper.searchUserByUserCode(userCode);
	}

	@Override
	public Long searchEUserByMobile(String mobile) throws Exception {
		Long userId = null;
		userId = userMapper.searchEUserByMobile(mobile);
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
	public Map<String,Object> newPassword(EhrUser user, String password) throws Exception {
		//设置新密码
		user.setPassword(Tools.encryptPassword(password));
		userMapper.resetPassword(user);
		Map<String,Object> result = centerUserClientService.resetPassword(user, password);
		if("false".equals(result.get("success").toString())){
			//return result;
			throw new JabavaServiceException(result.get("msg").toString());
		}
		return result;
	}

	@Override
	public Map<String, Object> changePassword(EhrUser user, String oldPassword,
			String newPassword) throws Exception {
		user.setPassword(Tools.encryptPassword(newPassword));
		userMapper.resetPassword(user);
		Map<String,Object> result = centerUserClientService.updatePassword(user, oldPassword, newPassword);
		if("false".equals(result.get("success").toString())){
			//return resetResult;
			throw new JabavaServiceException(result.get("msg").toString());
		}
		return result;
	}
	@Override
	public String resetPwd(EhrUser user, EhrUser affectUser) throws Exception {
//		String password = Tools.getInitialPassword();
//		user.setPassword(Tools.encryptPassword(password)); 
//		if(userMapper.resetPassword(user) == 0){
//			throw new JabavaServiceException("Jabava重置密码失败");
//		}
//		
//		if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
//			//用户中心重置密码
//			Map<String,Object> rr = centerUserClientService.resetPassword(user, password);
//			if("false".equals(rr.get("success"))){
//				throw new JabavaServiceException(rr.get("msg").toString());
//			}
//		}
//		
//		//上下文
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("userName", user.getUserName());
//		params.put("password", password);
//		
//		//发送短信及邮件
//		try {
//			Tools.mailSend(user.getMailAddress(), ConfigConstants.SUBJECT_RESET_PASSWORD, params, 
//					ConfigConstants.TPL_RESET_PASSWORD, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "邮件发送失败";
//		}
//		try {
//			String mobRes = this.sendMessage(user.getMobile(), CommonDataConstants.COMMON_DATA_MSG_TPL_RESET_PASSWORD, params);
//			if(!"1".equals(mobRes)){
//				return "邮件发送成功，短信发送失败";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "邮件发送成功，短信发送失败";
//		}
		
		Date now = new Date();
		//生成链接
		String salt = JabavaStringUtils.getRandomNum(4);
		String code = MD5.getMD5Code(salt + now.getTime() + affectUser.getUserCode());
		
		EhrUserResetPassword urp = new EhrUserResetPassword();
		urp.setUserId(affectUser.getUserId());
		urp.setSalt(salt);
		urp.setCode(code);
		urp.setInvalidDate(JabavaUtil.addDate(now, Calendar.HOUR, 24));
		urp.setCreateDate(now);
		urp.setCreateUserId(user.getUserId());
		urp.setCreateUserName(user.getUserName());
		userResetPasswordMapper.insert(urp);
		
		String link = JabavaPropertyCofigurer.getProperty("JABAVA_URL") + "/toSetPassword/" + affectUser.getUserId() + "/" + code;
		
		//上下文
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", user.getUserName());
		params.put("url", link);
		params.put("createDate", JabavaDateUtils.formatDate(now, "yyyy-MM-dd HH:mm:ss"));
		
		//发送邮件
		try {
			Tools.mailSend(affectUser.getMailAddress(), ConfigConstants.SUBJECT_RESET_PASSWORD, params, 
					ConfigConstants.TPL_RESET_PASSWORD, null);
		} catch (Exception e) {
			e.printStackTrace();
			return "邮件发送失败";
		}
		
		return null;
	}
	
//	private String sendMessage(String mobile, String tplCode, Map<String,Object> params) throws Exception{
//		EhrCommonData cd = commonDataMapper.selectByTypeAndCode(CommonDataConstants.COMMON_DATA_MSG_TPL,tplCode);
//		if(cd == null){
//			throw new JabavaServiceException("短信模板不存在");
//		}
//		
//		String content = cd.getMemo();
//		content = JabavaStringUtils.formatString(content, params);
//		return mobileService.sendMessage(mobile, content);
//	}
	
	@Override
	public String validateResetPasswordLink(Long userId, String code){
		//是否存在一致的有效信息
		List<EhrUserResetPassword> urpList = userResetPasswordMapper.queryValidByUser(userId);
		if(urpList == null || urpList.isEmpty() ||
				!urpList.get(0).getCode().equals(code)){
			return "无效的链接信息";
		}
		return null;
	}

	@Override
	public Map<String,Object> setPassword(EhrUser user, String code, String password) throws Exception {
		//是否存在一致的有效信息
		List<EhrUserResetPassword> urpList = userResetPasswordMapper.queryValidByUser(user.getUserId());
		if(urpList == null || urpList.isEmpty() ||
				!urpList.get(0).getCode().equals(code)){
			return MessageUtil.errorMessage("无效的链接信息");
		}
		
		//设置新密码
		user.setPassword(Tools.encryptPassword(password));
		userMapper.resetPassword(user);
		Map<String,Object> resetResult = centerUserClientService.resetPassword(user, password);
		if("false".equals(resetResult.get("success").toString())){
			//return resetResult;
			throw new JabavaServiceException(resetResult.get("msg").toString());
		}
		
		//更新重置密码信息
		EhrUserResetPassword urp = urpList.get(0);
		urp.setActionDate(new Date());
		userResetPasswordMapper.updateByPrimaryKey(urp);
		
		return MessageUtil.successMessage("更新密码成功");
	}
	
	/**
	 * 通过密码登录, 默认情况下的系统登录被spring security 托管，这里用在需要人工认证的情况下
	 */
	public boolean loginWidthPassword(String userCode,String password){
		try{
			EhrUser user = userMapper.validateUser(userCode);
			if(user == null){
				log.error("用户错误");
				return false;
			}
			
			log.error("user: " + userCode);
			log.error("pwd:" + password);
			log.error("companyId:" + user.getCompanyId());
			
			String enp = Tools.encryptPassword(password);
			int ret = userMapper.checkUserPassword(user.getCompanyId(), userCode, enp);
			
			log.error("user pwd: " + ret);
			
			return (ret > 0);
		}catch(Exception e){
			log.error("====异常");
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public int hasOpenService(EhrUser user, Long systemId) {
		return centerUserClientService.hasOpenService(user, systemId);
	}
	
	@Override
	public Map<String,Object> openService(Long orgLoginId, Long systemId) {
		return centerUserClientService.openService(orgLoginId, systemId, new JSONObject().toString());
	}
	
	@Override
	public void asynchInitCompany(final EhrUser user) {
		//启动新线程同步
		ExecutorService pool = Executors.newFixedThreadPool(1);
		pool.execute(new Runnable() {
			@Override
			public void run() {
				//初始化(基础数据、报表等)
				companyInitializer.execute(user);
			}
		});
		log.info("初始化线程已启动：" + user.getUserCode());
	}
	
	@Override
	public EhrUser validateUser(String userCode) {
		return userMapper.validateUser(userCode);
	}
}
