/**
 * 
 */
package com.jabava.controller.manage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.announcement.AnnouncementService;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.service.manage.ICompanyService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HrHelperFileUploader;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.Tools;
import com.jabava.utils.enums.JabavaEnum;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.enums.SystemEnum.LogOperateType;
import com.jabava.utils.enums.SystemEnum.Module;
import com.jabava.utils.security.JabavaCasUtils;
import com.service.provider.MobileService;
import com.service.provider.UcOrgCertificateService;
import com.service.provider.entity.OrgUser;
import com.service.provider.entity.ReturnS;

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
	@Autowired
	private OutsourcingService outsourcingService;
	@Autowired
	private MobileService mobileService;//usercenter提供的发送短信的接口
	@Autowired
	private UcOrgCertificateService ucOrgCertificateService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		return "common/index";
	}
	
	@RequestMapping("/login")
	public String login(EhrUser ehrUser, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("currentFunc");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "login";
	}
	
	//单点
	public boolean doLogin(OrgUser orgUser, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("orgId", orgUser.getOrgId());
		session.setAttribute("orgLoginId", orgUser.getLoginId());
		
		EhrUser user = userService.selectUserById(Long.valueOf(orgUser.getIdentity()));
		
		return this.doLogin(user,request);
	}
	
	//非单点
	public boolean doLogin(String userCode, HttpServletRequest request) throws Exception{
		EhrUser user = userService.searchUserByUserCode(userCode);
		return this.doLogin(user,request);
	}
	
	@SuppressWarnings("rawtypes")
	private boolean doLogin(EhrUser ehrUser, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();

//		// 拒绝普通用户的登录
//		if(ehrUser.getUserType() == 3){
//			return false;
//		}
		
		Map map = userService.login(ehrUser.getUserCode(),ehrUser.getPassword());

		HroPactInfo hroPactInfo = new HroPactInfo();
		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
		request.setAttribute("viewInfo", map.get("viewInfo"));
		EhrUser user = (EhrUser) map.get(Constants.LOGIN_USER);
		session.setAttribute(Constants.LOGIN_USER, user);
		//session.setAttribute("POWER", map.get("POWER"));
		session.setAttribute("ROLE", map.get("ROLE"));
		
		List<HroPactInfo> pactList = outsourcingService.queryProtocolByParams(hroPactInfo);
		if(pactList!=null && pactList.size()>0){
			session.setAttribute("RECEIVE_ORG_ID", pactList.get(0).getReceiveOrgId());
		}
			
		String menu = userService.menuTreeStr(user, request);
		session.setAttribute("MENU", menu);
		
		//同步orgId(正常情况下注册时已经更新)
		this.syncOrgId(request);
			
		sysLogService.addSysLog(user, (LogOperateType)map.get("operateType"),(Module)map.get("module"), (String) map.get("logInfo"));
		
		return true;
	}
	
	private void syncOrgId(HttpServletRequest request) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrCompany company = user.getCompany();
		if(company == null || company.getUcOrgId() != null){
			return;
		}
		
		Long orgId = (Long)request.getSession().getAttribute("orgId");
		if(orgId == null){
			return;
		}
		
		company.setUcOrgId(orgId);
		companyService.updateCompany(company);
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

	@RequestMapping("/selectAuthorizedChildren")
	@ResponseBody
	public List<EhrMenu> selectAuthorizedChildren(HttpServletRequest request, HttpServletResponse response, Long parentId) {
		EhrUser user = RequestUtil.getLoginUser(request);
		return userService.selectAuthorizedChildren(parentId, user.getUserId());
	}

	@RequestMapping("/to_register")
	public String to_register(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping("checkMobile")
	@ResponseBody
	public Map<String, Object> checkMobile(String flag, String mobile,HttpServletRequest request,
			HttpServletResponse response) {
//		Map<String, Object> data = new HashMap<String, Object>();
//		if(!RequestUtil.validateGraphicsCode(request, gCheckCode)){
//			data.put("result", false);
//			data.put("msg", "图形验证码不匹配");
//		}

		//校验图形验证码
		String gCheckCode = request.getParameter("gCheckCode");
		if(!RequestUtil.validateGraphicsCode(request, gCheckCode)){
			return MessageUtil.errorMessage("图形验证码不匹配");
		}
		
		try {
			//新公司不需判断重复
//			if("0".equals(flag)){//注册时验证手机验证码
//				Long userId = userService.searchEUserByMobile(mobile);
//				if (userId != null) {
//					return MessageUtil.errorMessage("该手机号已经被注册");
//				}
//			}else if("1".equals(flag)){//忘记密码时验证手机验证码
//				;
//			}
			
			String checkCode = RequestUtil.generateCheckCode(request, mobile);
			String msgContent = "[Jabava]校验码:" + checkCode;
			String sendRtn = mobileService.sendMessage(mobile, msgContent);
			//1 成功 2 失败 3  手机号码不合法  4 手机号码或者内容为空
			if("2".equals(sendRtn)){
				return MessageUtil.errorMessage("短信发送失败");
			}else if("3".equals(sendRtn)){
				return MessageUtil.errorMessage("手机号码不合法");
			}else if("4".equals(sendRtn)){
				return MessageUtil.errorMessage("手机号码或者内容为空");
			}
			return MessageUtil.successMessage("获取校验码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}

	@RequestMapping("register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpSession session = request.getSession();
	    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		try {
			String companyName = request.getParameter("companyName");
			String contactor = request.getParameter("contacts");//联系人
			String companyProv = request.getParameter("companyProv");
			String companyCity = request.getParameter("companyCity");
			String companyDist = request.getParameter("companyDist");
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			String gCheckCode = request.getParameter("gCheckCode");
			String mCheckCode = request.getParameter("mCheckCode");

			//校验图形验证码
			if(!RequestUtil.validateGraphicsCode(request, gCheckCode)){
				data.put("result", false);
				data.put("msg", "图形验证码不匹配");
				return data;
			}
			//校验手机验证码
			Map<String,Object> chkResult = RequestUtil.validateCheckCode(request, mobile, mCheckCode);
			if(chkResult.get("success").toString().equals("false")){
				data.put("result", false);
				data.put("msg", chkResult.get("msg"));
				return data;
			}
			
			EhrCompany company = new EhrCompany();
			//company.setCompanyCode(JabavaUtil.genCompanyCode(companyService.getMaxCompanyId() + 1));
			company.setCompanyCode(String.valueOf(System.currentTimeMillis()));
			company.setCompanyName(companyName);
			company.setContactor(contactor);//联系人
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
			user.setUserName(contactor);
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
			    this.doLogin(user.getUserCode(), request);
			    
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserCode(), password);
			    token.setDetails(new WebAuthenticationDetails(request));
			    
			    String beanId = "org.springframework.security.authenticationManager";
			    AuthenticationManager authenticationManager = (AuthenticationManager)wac.getBean(beanId);
			    Authentication authenticatedUser = authenticationManager.authenticate(token);
			    
			    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
			    //response.sendRedirect(request.getContextPath() + "/to_perfect");
			}
			
			//初始化
			userService.asynchInitCompany(user);
			
			data.put("userId", user.getUserId());
			data.put("userCode", user.getUserCode());
			data.put("password", password);
			data.put("companyId", company.getCompanyId());
			data.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			data.put("result", false);
			data.put("msg", e.getMessage());
		}
		
		return data;
	}
	
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
		return "perfect_information";
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
			//String storageUrl = request.getSession().getServletContext().getRealPath("static/upload");
			String storageUrl = FileUtil.confirmFullPath(user.getCompanyId().toString(), 
					JabavaEnum.FileClassEnum.PICTURE.getValue().toString());
			//File folder = new File(storageUrl);
			//if (!folder.exists()) {
			//	folder.mkdirs();
			//}
			// 公司logo
			if (!file1.isEmpty()) {
				String fileName = file1.getOriginalFilename();
				File targetFile = new File(storageUrl, fileName);
				file1.transferTo(targetFile);
				String realPath = HrHelperFileUploader.upload(storageUrl + File.separator + fileName);
				//company.setCompanyLogo(fileName);
				company.setCompanyLogo(realPath);
			}
			// 营业执照
			if (!file2.isEmpty()) {
				String fileName = file2.getOriginalFilename();
				File targetFile = new File(storageUrl, fileName);
				file2.transferTo(targetFile);
				String realPath = HrHelperFileUploader.upload(storageUrl + File.separator + fileName);
				//company.setCompanyLicenseUrl(fileName);
				company.setCompanyLicenseUrl(realPath);
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
		session.removeAttribute("currentFunc");
//		EhrUser user = userService.selectUserById(Long.valueOf(userId));
//		HashMap map = userService.login(user.getUserCode(), password);
//		user = (EhrUser) map.get(Constants.LOGIN_USER);
//		session.setAttribute(Constants.LOGIN_USER, user);
		
		//if(user != null && user.getUserType() == 2){
		if(RequestUtil.hasRoleOfName("企业管理员")){
			//if(jump == null || jump.intValue() != 1){
			//	//如果未选择跳过，则判断是否转向完善信息页面	
			//	EhrUser user = RequestUtil.getLoginUser(request);
			//	if(user.getCompany().getPerfectDate() == null){
			//		response.sendRedirect(request.getContextPath() + "/to_perfect");
			//		return null;
			//	}
			//}
			return "index/company_index";
		}else if(RequestUtil.hasRoleOfName("HR")){
			//return "index/hr_index";
			return "common/index";
		}else{
			return "common/index";
		}
	}
	
	@RequestMapping("/checkGraphics")
	@ResponseBody
	public Map<String, Object> checkGraphics(HttpServletRequest request, HttpServletResponse response) {
		String gCheckCode = request.getParameter("gCheckCode");

		//校验图形验证码
		if(!RequestUtil.validateGraphicsCode(request, gCheckCode)){
			return MessageUtil.errorMessage("图形验证码不匹配");
		}
		
		return MessageUtil.successMessage("校验通过");
	}
	
	@RequestMapping("/to_forgetPwd")
	public String to_forgetPwd(HttpServletRequest request, HttpServletResponse response) {
		return "/user/password";
	}
	
	@RequestMapping("/newPassword")
	@ResponseBody
	public Map<String, Object> newPassword(HttpServletRequest request, HttpServletResponse response) {
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("newPassword");
		String gCheckCode = request.getParameter("gCheckCode");
		String mCheckCode = request.getParameter("mCheckCode");

		try {
			Long userId = userService.searchEUserByMobile(mobile);
			if(userId == null){
				return MessageUtil.errorMessage("用户不存在");
			}
			EhrUser user = userService.selectUserById(userId);
			
			//校验图形验证码
			if(!RequestUtil.validateGraphicsCode(request, gCheckCode)){
				return MessageUtil.errorMessage("图形验证码不匹配");
			}
			//校验手机验证码
			Map<String,Object> chkResult = RequestUtil.validateCheckCode(request, mobile, mCheckCode);
			if(chkResult.get("success").toString().equals("false")){
				return MessageUtil.errorMessage(chkResult.get("msg").toString());
			}
			//更新密码
			return userService.newPassword(user, password);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/toChangePassword")
	public String toChangePassword(HttpServletRequest request, HttpServletResponse response) {
		return "/user/update_password";
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public Map<String, Object> changePassword(HttpServletRequest request, HttpServletResponse response,
			String oldPassword, String newPassword) {
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			return userService.changePassword(user, oldPassword, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/resetPwd/{userId}")
	@ResponseBody
	public Map<String, Object> resetPwd(HttpServletRequest request, HttpServletResponse response, @PathVariable Long userId){
		try {
			EhrUser user = RequestUtil.getLoginUser(request);
			EhrUser affectUser = userService.selectUserById(userId);
			if(affectUser == null || !user.getCompanyId().equals(affectUser.getCompanyId())){
				return MessageUtil.successMessage("没有数据操作权限");
			}
			
			String result = userService.resetPwd(user, affectUser);
			if(StringUtils.isEmpty(result)){
				return MessageUtil.successMessage("重置密码成功");
			}else{
				return MessageUtil.errorMessage(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/toSetPassword/{userId}/{code}")
	public String toSetPassword(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long userId, @PathVariable String code) {
		if(userService.validateResetPasswordLink(userId, code) == null){
			request.setAttribute("userId", userId);
			request.setAttribute("code", code);
			return "user/resetpassword";
		}else{
			return "redirect:/to_index";
		}
	}
	
	@RequestMapping("/setPassword")
	@ResponseBody
	public Map<String,Object> setPassword(HttpServletRequest request, HttpServletResponse response,
			Long userId, String code, String newPassword) {
		try {
			EhrUser user = userService.selectUserById(userId);
			if(user == null){
				return MessageUtil.errorMessage("用户不存在");
			}
			Map<String,Object> result = userService.setPassword(user, code, newPassword);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/to_login")
	public String to_login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping("hasOpenService")
	@ResponseBody
	public String hasOpenService(HttpServletRequest request, HttpServletResponse response, Long systemId) {
		EhrUser user = RequestUtil.getLoginUser(request);
		int result = userService.hasOpenService(user, systemId);
		return String.valueOf(result);
	}

	@RequestMapping("openService")
	@ResponseBody
	public Map<String,Object> openService(HttpServletRequest request, HttpServletResponse response, Long systemId) {
		//EhrUser user = RequestUtil.getLoginUser(request);
		Long orgLoginId = (Long)request.getSession().getAttribute("orgLoginId");
		return userService.openService(orgLoginId, systemId);
	}
	
	@RequestMapping("syncCertificateStatus")
	@ResponseBody
	public Map<String,Object> syncCertificateStatus(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		
		try {
			//查询状态
			Long orgId = (Long)request.getSession().getAttribute("orgId");
			ReturnS r = ucOrgCertificateService.orgAuthenticateResult(orgId);
			Integer status = Integer.valueOf(r.getReturnCode().toString());
			if(!status.equals(user.getCompany().getCertificateStatus())){
				//如果结果不一致则更新
				user.getCompany().setCertificateStatus(status);
				companyService.updateCompany(user.getCompany());
			}
			
			Map<String,Object> result = MessageUtil.successMessage("获取认证状态成功");
			result.put("certificateStatus", user.getCompany().getCertificateStatus());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
}
