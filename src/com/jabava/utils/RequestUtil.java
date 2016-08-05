package com.jabava.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jabava.common.init.Initializer;
import com.jabava.pojo.manage.EhrButton;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.pojo.manage.EhrPage;
import com.jabava.pojo.manage.EhrRole;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.manage.IUserService;
import com.jabava.utils.security.MD5;
import com.sensorsdata.analytics.javasdk.SensorsDataAPI;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

public class RequestUtil {
	private static Initializer initializer;
	
	public static EhrUser getLoginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (EhrUser)session.getAttribute(Constants.LOGIN_USER);
	}
	
	public static EhrUser getLoginUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return getLoginUser(request);
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request){
		return request.getHeader("X-Requested-With") != null && 
				request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1;
	}
	
	public static boolean hasRoleOfName(String roleName){
		if(StringUtils.isEmpty(roleName)){
			return false;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		List<EhrRole> roleList = (List<EhrRole>)session.getAttribute("ROLE");
		if(roleList != null && !roleList.isEmpty()){
			for(EhrRole role : roleList){
				if(role.getRoleName().equals(roleName)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean hasUrlPower(HttpServletRequest request){
		EhrUser user = getLoginUser(request);
		if(user == null){
			return false;
		}
		
		String servletPath = request.getServletPath();
		if(servletPath == null){
			return false;
		}
		
		for(EhrMenu menu : user.getMenuList()){
			if(servletPath.startsWith(menu.getMenuUrl())){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasPower(String powerCode) {
		ServletRequestAttributes sras = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		if(sras == null){
			return false;
		}
		
		HttpServletRequest request = sras.getRequest();
		if(request == null){
			return false;
		}
		
		return hasPower(getLoginUser(request), powerCode);
	}
	
	public static boolean hasPower(HttpServletRequest request, String powerCode) {
		return hasPower(getLoginUser(request), powerCode);
	}
	
	public static boolean hasPower(EhrUser user, String powerCode) {
		if(user == null){
			return false;
		}
		
		Map<String,EhrButton> buttonMap = user.getButtonMap();
		if(buttonMap != null && buttonMap.get(powerCode) != null){
			return true;
		}
		
		return false;
	}
	
	public static String generateCheckCode(HttpServletRequest request, String mobile){
		String checkCode = JabavaStringUtils.getRandomNum(4);
		HttpSession session = request.getSession();
		Map<String,Object> info = new HashMap<String,Object>();
		info.put("mobile", mobile);
		info.put("checkCode", checkCode);
		//30分钟内有效
		info.put("invalidDate", JabavaUtil.addDate(new Date(), Calendar.MINUTE, 30));
		session.setAttribute("CHECK_CODE_INFO", info);
		return checkCode;
	}
	
	/**
	 * 校验图形验证码
	 * @param request
	 * @param checkCode
	 * @return
	 */
	public static boolean validateGraphicsCode(HttpServletRequest request, String checkCode){
		HttpSession session = request.getSession();
		String vcode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(StringUtils.isEmpty(checkCode) || !checkCode.equals(vcode)){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 校验码有效性验证
	 * @param request
	 * @param mobile
	 * @param checkCode
	 * @return
	 */
	public static Map<String,Object> validateCheckCode(HttpServletRequest request, String mobile, String checkCode) throws Exception{
		HttpSession session = request.getSession();
		Map<String,Object> info = (Map<String,Object>)session.getAttribute("CHECK_CODE_INFO");
		if(info == null){
			return MessageUtil.errorMessage("非法的手机检验信息");
		}
		String existMobile = (String)info.get("mobile");
		if(!mobile.equals(existMobile)){
			return MessageUtil.errorMessage("手机不匹配");
		}
		String existCheckCode = (String)info.get("checkCode");
		if(!checkCode.equals(existCheckCode)){
			return MessageUtil.errorMessage("手机校验码不匹配");
		}
		Date invalidaDate = (Date)info.get("invalidDate");
		if(new Date().after(invalidaDate)){
			return MessageUtil.errorMessage("手机校验码已过期");
		}
		
		return MessageUtil.successMessage("手机验证码校验通过");
	}
	
	public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

	public static void collectData(HttpServletRequest request){
		//track_signup
//		{
//		    "distinct_id":"12345",
//		    "original_id":"2b0a6f51a3cd6775",
//		    "time": 1434557935000,
//		    "type": "track_signup",
//		    "event": "$Signup",
//		    "properties": {
//		        "$manufacturer":"Apple",
//		        "$model": "iPhone 5",
//		        "$os":"iOS",
//		        "$os_version":"7.0",
//		        "$app_version":"1.3",        
//		        "$wifi":true,
//		        "$ip":"180.79.35.65",
//		        "$province":"湖南",
//		        "$city":"长沙",
//		        "$screen_width":320,
//		        "$screen_height":640
//		    }
//		}
		
		if("/index/index".equals(request.getServletPath())){
			EhrPage page = new EhrPage();
			page.setPagePath("/index/index");
			page.setPageCode("SuperHomePage");
			page.setPageTitle("超级首页");
			page.setModule("超级首页");
			try {
				Map<String,Object> properties = getCollectProperties(request, page);
				SensorsDataAPI.sharedInstance().track(getDistinctId(request), page.getPageCode(), properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ;
		}
		
		if(initializer == null){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			initializer = wac.getBean(Initializer.class);
		}
		Map<String,EhrPage> pageMap = initializer.getPageMap();
		if(!pageMap.containsKey(request.getServletPath())){
			return ;
		}
		
		EhrPage page = pageMap.get(request.getServletPath());
		if(page.getIsCollect() == 0){
			return ;
		}

	    try {
			// 记录用户浏览事件
	    	Map<String,Object> properties = getCollectProperties(request, page);
			SensorsDataAPI.sharedInstance().track(getDistinctId(request), page.getPageCode(), properties);
		} catch (Exception e) {
			e.printStackTrace();
	    }

	}
	
	private static String getDistinctId(HttpServletRequest request){
		EhrUser user = getLoginUser(request);
		if(user == null){	//非登录用户在Session期间采用UUID
			String distinctId = (String)request.getSession().getAttribute("distinctId");
			if(StringUtils.isEmpty(distinctId)){
				distinctId = UUID.randomUUID().toString().replaceAll("-", "");
				request.getSession().setAttribute("distinctId", distinctId);
			}
			return distinctId;
		}else{				//登录用户采用userCode加密
			return MD5.getMD5Code(user.getUserCode());
		}
	}
	
	private static Map<String,Object> getCollectProperties(HttpServletRequest request, EhrPage page) throws Exception{
		Map<String, Object> properties = new HashMap<String, Object>();
		
	    // '$time' 属性是系统预置属性，表示事件发生的时间，如果不填入该属性，则默认使用系统当前时间
	    properties.put("$time", new Date());
	    // '$ip' 属性是系统预置属性，如果服务端中能获取用户 IP 地址，并填入该属性，Sensors Analytics 会自动根据 IP 地址解析用户的省份、城市信息
	    properties.put("$ip", getRealIp(request));
	    // Title
	    properties.put("title", page.getPageTitle());
	    // 页面url
	    properties.put("url", page.getPagePath());
	    // 模块
	    properties.put("module", page.getModule());
	    
	    String userAgent = request.getHeader("User-Agent");
	    if(!StringUtils.isEmpty(userAgent)){	//操作系统及浏览器
	    	properties.put("$os", UserAgentParser.getOS(userAgent));
	    	//properties.put("$os", UserAgentParser.getOS(userAgent));
	    }
	    
	    return properties;
	}
}
