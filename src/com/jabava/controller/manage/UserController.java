/**
 * 
 */
package com.jabava.controller.manage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.announcement.AnnouncementService;
import com.jabava.service.manage.ICompanyService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.security.JabavaCasUtils;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.Tools;
import com.jabava.utils.mobile.MobileValidHelp;

/**
 * @author WangYongqiang
 * 
 */
@Controller
public class UserController {
	private Logger log = Logger.getLogger(UserController.class);

	@Resource
	private IUserService userService;
	@Resource
	private AnnouncementService announcementService;
	@Resource
	private ICompanyService companyService;
	@Resource
	private IEhrSysLogSercice sysLogService;
	@Resource
	private IEhrRoleService ehrRoleService;
	@Resource
	private IEhrOrganizationService organizationService;
	
//	/**
//	 * 测试registerUser接口
//	 * @return
//	 */
//	@RequestMapping("/registerUser")
//	@ResponseBody
//	public String registerUser(){
//		centerUserClientService.registerUser(null, null, null);
//		return null;
//	}
//	
//	/**
//	 * 测试openUser接口
//	 * @return
//	 */
//	@RequestMapping("/openUser")
//	@ResponseBody
//	public String openUser(){
//		centerUserClientService.openUser(null, null);
//		return null;
//	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/login")
	public String login(EhrUser ehrUser, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("currentFunc");
//			if ((ehrUser.getUserCode() == null && ehrUser.getPassword() == null && ehrUser
//					.getCompanyCode() == null)) {
//				if (session.getAttribute(Constants.LOGIN_USER) == null) {
//					return "login";
//				}
//				return "common/index";
//			}
////			EhrUser user = new EhrUser();
////			HashMap map = userService.login(ehrUser.getUserCode(),
////					ehrUser.getPassword());
////			if (((String) map.get("flag")).equals("0")) {
////				request.setAttribute("viewInfo", map.get("viewInfo"));
////				user.setUserId((long) 0);
////				user.setUserCode(ehrUser.getUserCode());
////				user.setUserName(ehrUser.getUserCode());
////				userService.insertSysLog(user, (String) map.get("logInfo"));
////				return "login";
////			} else {
////				request.setAttribute("viewInfo", map.get("viewInfo"));
////				session.setAttribute(Constants.LOGIN_USER, map.get(Constants.LOGIN_USER));
////				session.setAttribute("POWER", map.get("POWER"));
////				session.setAttribute("ROLE", map.get("ROLE"));
////				user = (EhrUser) map.get(Constants.LOGIN_USER);
//////				userService.insertSysLog(user, (String) map.get("logInfo"));
////				sysLogService.addSysLog(user, (String) map.get("logInfo"));
////				EhrInformation ehrInformation = new EhrInformation();
////
////				ehrInformation.setCompanyId(ehrUser.getCompanyId());
////				// 默认类型为公司信息
////				ehrInformation.setInformationType(InformationType.COMPANY
////						.getValue());
////				// 默认范围为全部公司人员
////				ehrInformation
////						.setInformationRange(InformationRange.ALL_COMPANY_USER
////								.getValue());
////				/*
////				 * List<EhrInformation> ehrInformationlist =
////				 * announcementService.findInformationByParams(ehrInformation);
////				 * model.addAttribute("informationList", ehrInformationlist);
////				 * announcementService.findInformationByParams(ehrInformation);
////				 */
////				String menu = userService.menuTreeStr(user, request);
////				session.setAttribute("MENU", menu);
////
////				// Subject sub = SecurityUtils.getSubject();
////				// UsernamePasswordToken token = new
////				// UsernamePasswordToken(user.getUserCode(),
////				// user.getPassword());
////				// token.setRememberMe(true);
////				// sub.login(token);
////			}
//			
//			if(this.doLogin(ehrUser, request, null)){
//				return "common/index";
//			}else{
//				return "login";
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "login";
	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		if(session != null){
//			session.invalidate();
//		}
//		
//		return "to_index";
//	}
	
	//单点
	public boolean doLogin(String userId, Long orgId, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("orgId", orgId);
//		Enumeration e = session.getAttributeNames();
//		while(e.hasMoreElements()){
//			Object key = e.nextElement();
//			System.out.println(key.toString() + "=====" + session.getAttribute(key.toString()));
//		}
		return this.doLogin(userService.selectUserById(Long.valueOf(userId)),request);
	}
	
	//非单点
	public boolean doLogin(String userCode, HttpServletRequest request) throws Exception{
		return this.doLogin(userService.searchUserByUserCode(userCode),request);
	}
	
	private boolean doLogin(EhrUser ehrUser, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		HashMap map = userService.login(ehrUser.getUserCode(),ehrUser.getPassword());
		if (((String) map.get("flag")).equals("0")) {
			request.setAttribute("viewInfo", map.get("viewInfo"));
			EhrUser user = new EhrUser();
			user.setUserId((long) 0);
			user.setUserCode(ehrUser.getUserCode());
			user.setUserName(ehrUser.getUserCode());
			userService.insertSysLog(user, (String) map.get("logInfo"));
			return false;
		} else {
			request.setAttribute("viewInfo", map.get("viewInfo"));
			EhrUser user = (EhrUser) map.get(Constants.LOGIN_USER);
			session.setAttribute(Constants.LOGIN_USER, user);
			session.setAttribute("POWER", map.get("POWER"));
			session.setAttribute("ROLE", map.get("ROLE"));
			
//			EhrInformation ehrInformation = new EhrInformation();
//			ehrInformation.setCompanyId(ehrUser.getCompanyId());
//			// 默认类型为公司信息
//			ehrInformation.setInformationType(InformationType.COMPANY.getValue());
//			// 默认范围为全部公司人员
//			ehrInformation.setInformationRange(InformationRange.ALL_COMPANY_USER.getValue());
			
			String menu = userService.menuTreeStr(user, request);
			session.setAttribute("MENU", menu);
			
			sysLogService.addSysLog(user, (String) map.get("logInfo"));
		}
		
		return true;
	}

	@RequestMapping("/menuTree")
	public String menuTree(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/xml;charset=UTF-8");	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			PrintWriter out = response.getWriter();
			String result = userService.menuTreeStr(user, request);
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/menuTree1")
	public void menuTree1(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/xml;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			String result = (String) request.getSession().getAttribute("MENU");
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/menuTree2")
	@ResponseBody
	public String menuTree2(HttpServletRequest request, HttpServletResponse response) {
		String currentFunc = request.getParameter("currentFunc");
		HttpSession session = request.getSession();
		session.setAttribute("currentFunc", currentFunc);
		return null;
	}

	@RequestMapping("/to_register")
	public String to_register(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping("checkMobile")
	@ResponseBody
	public Map<String, Object> checkMobile(String flag, String mobile,
			HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			if("0".equals(flag)){//注册时验证手机验证码
				Long userId = userService.searchUserByMobile(mobile);
				if (userId != null) {
					data.put("info", "该手机号已经被注册");
				} else {
					String checkCode = JabavaUtil.getMath(4);
					String msgContent = "[Jabava]校验码:" + checkCode;
					MobileValidHelp.sendMsg(mobile, msgContent);
					data.put("info", "");
					data.put("checkCode", checkCode);
				}
			}else if("1".equals(flag)){//忘记密码时验证手机验证码
				String checkCode = JabavaUtil.getMath(4);
				String msgContent = "[Jabava]校验码:" + checkCode;
				MobileValidHelp.sendMsg(mobile, msgContent);
				data.put("checkCode", checkCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> data = null;
		HttpSession session = request.getSession();
	    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		try {
			String companyName = request.getParameter("companyName");
			String companyProv = request.getParameter("companyProv");
			String companyCity = request.getParameter("companyCity");
			String companyDist = request.getParameter("companyDist");
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			
			EhrCompany company = new EhrCompany();
			//company.setCompanyCode(JabavaUtil.genCompanyCode(companyService.getMaxCompanyId() + 1));
			company.setCompanyCode(String.valueOf(System.currentTimeMillis()));
			company.setCompanyName(companyName);
			company.setCompanyProv(companyProv);
			company.setCompanyCity(companyCity);
			company.setCompanyDist(companyDist);
			company.setParentId((long) 0);
			company.setUseEhr(1);
			company.setCreateDate(new Date());
			company.setLastModifyDate(new Date());
			
			EhrUser user = new EhrUser();
			//user.setCompanyId(company.getCompanyId());
			user.setCompanyCode(company.getCompanyCode());
			user.setUserCode(mobile);
			user.setMobile(mobile);
			user.setPassword(Tools.encryptPassword(password));
			user.setUserName("系统管理员");
			user.setIsValid((byte) 1);
			user.setIsLocked((byte) 0);
			user.setLastChangePasswordDate(new Date());
			user.setFailtureTime(0);
			user.setLoginTime(0);
			user.setUserType((byte) 2);
			user.setCreateDate(new Date());
			user.setLastModifyDate(new Date());
			user.setFlag(1);
				
			data = userService.register(company,user,password);
			
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				//用户中心企业Id
				session.setAttribute("orgId", data.get("orgId"));
				
				//this.bindTicketGrantingTicket();
				
				JabavaCasUtils cas = new JabavaCasUtils();
				String beanId = "casEntryPoint";
			    CasAuthenticationEntryPoint casEntryPoint = (CasAuthenticationEntryPoint)wac.getBean(beanId);
			    beanId = "serviceProperties";
			    ServiceProperties serviceProperties = (ServiceProperties)wac.getBean(beanId);
				String location = cas.getLoginUrl(casEntryPoint.getLoginUrl() + "&service=" + serviceProperties.getService() + ";jsessionid=" + session.getId(), mobile, password);
				log.debug(location);
				
				//response.sendRedirect(location.replaceFirst("[?]", ";jsessionid=" + sessionId + "?"));
				//session.setAttribute("URL_AFTER_REGISTER", "/to_perfect");
				//response.sendRedirect(location);
				data.put("url", location);
			}else{
				//注册后自动登录
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserCode(), password);
			    token.setDetails(new WebAuthenticationDetails(request));
			    
			    String beanId = "org.springframework.security.authenticationManager";
			    AuthenticationManager authenticationManager = (AuthenticationManager)wac.getBean(beanId);
			    Authentication authenticatedUser = authenticationManager.authenticate(token);
			    
			    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
			    //response.sendRedirect(request.getContextPath() + "/to_perfect");
			}
			
			data.put("userId", user.getUserId());
			data.put("userCode", user.getUserCode());
			data.put("password", password);
			data.put("companyId", company.getCompanyId());
			data.put("result", true);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			data = new HashMap<String, Object>();
			data.put("result", false);
			data.put("msg", e.getMessage());
		}
		
		return data;
	}
	
//	private void bindTicketGrantingTicket(String loginName, String loginPassword, HttpServletRequest request, HttpServletResponse response){
//        try {
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
//            credentials.setUsername(loginName);
//            credentials.setPassword(loginPassword);
//            String ticketGrantingTicket = centralAuthenticationService.createTicketGrantingTicket(credentials);
//            ticketGrantingTicketCookieGenerator.addCookie(request, response, ticketGrantingTicket);
//        } catch (TicketException te) {
//            log.error("Validate the login name " + loginName + " failure, can't bind the TGT!", te);
//        } catch (Exception e){
//            log.error("bindTicketGrantingTicket has exception.", e);
//        }
//    }

	@RequestMapping("/to_perfect")
	public String to_perfect(HttpServletRequest request, HttpServletResponse response) {	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		//String companyId = request.getParameter("companyId");
		//Long companyId = Long.valueOf(companyId);
		Long companyId = ehrUser.getCompanyId();
		try {
			EhrCompany company = companyService.selectCompanyById(companyId);
			//request.setAttribute("userId", request.getParameter("userId"));
			//request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("company", company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "perfect_informstion";
	}

	@RequestMapping("perfect")
	// @ResponseBody
	public String perfect(EhrCompany company, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("lefile_two") CommonsMultipartFile file1,
			@RequestParam("lefile") CommonsMultipartFile file2)
			throws Exception {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
//		// insert session	
		EhrUser user = RequestUtil.getLoginUser(request);
//		EhrUser user = userService.selectUserById(Long.valueOf(userId));
//		HashMap<String,Object> map = userService.login(user.getUserCode(), password);
//		user = (EhrUser) map.get(Constants.LOGIN_USER);
//		session.setAttribute(Constants.LOGIN_USER, user);
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			boolean result = false;
			Date now = new Date();
			String storageUrl = request.getSession().getServletContext()
					.getRealPath("static/upload");
			File folder = new File(storageUrl);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			// 公司logo
			if (!file1.isEmpty()) {
				String fileName = file1.getOriginalFilename();
				File targetFile = new File(storageUrl, fileName);
				file1.transferTo(targetFile);
				company.setCompanyLogo(fileName);
			}
			// 营业执照
			if (!file2.isEmpty()) {
				String fileName = file2.getOriginalFilename();
				File targetFile = new File(storageUrl, fileName);
				file2.transferTo(targetFile);
				company.setCompanyLicenseUrl(fileName);
			}
			company.setLastModifyUserId(user.getUserId());
			company.setLastModifyDate(now);
			company.setPerfectDate(now);
			result = companyService.updateCompany(company);
			if (result) {
				//更新登录用户的公司信息(如Logo)
				user.setCompany(company);
			}
			data.put("result", result);
		} catch (Exception e) {
			data.put("result", false);
		}
		// return data;
		//return "common/index";
		return "index/company_index";
	}

	@RequestMapping("/to_index")
	public String to_index(HttpServletRequest request,
			HttpServletResponse response,Integer jump) throws Exception {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
//		// insert session
		HttpSession session = request.getSession();
//		EhrUser user = userService.selectUserById(Long.valueOf(userId));
//		HashMap map = userService.login(user.getUserCode(), password);
//		user = (EhrUser) map.get(Constants.LOGIN_USER);
//		session.setAttribute(Constants.LOGIN_USER, user);
		
		//if(user != null && user.getUserType() == 2){
		if(RequestUtil.hasRoleOfName("企业管理员")){
			if(jump == null || jump.intValue() != 1){
				//如果未选择跳过，则判断是否转向完善信息页面	
				EhrUser user = RequestUtil.getLoginUser(request);
				if(user.getCompany().getPerfectDate() == null){
					response.sendRedirect(request.getContextPath() + "/to_perfect");
					return null;
				}
			}
			return "index/company_index";
		}else if(RequestUtil.hasRoleOfName("HR")){
			//return "index/hr_index";
			return "common/index";
		}else{
			return "common/index";
		}
	}
	
	@RequestMapping("/to_forgetPwd")
	public String to_forgetPwd(HttpServletRequest request,
			HttpServletResponse response) {
		return "password";
	}
	
	@RequestMapping("/resetPwd")
	@ResponseBody
	public Map<String, Object> resetPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Long userId = userService.searchUserByMobile(request.getParameter("mobile"));
		if (userId != null) {
			EhrUser user = userService.selectUserById(userId);
			String newPwd = userService.resetPwd(user);
			data.put("newPwd", newPwd);
		}
		return data;
	}
	
	@RequestMapping("/to_login")
	public String to_login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

}
