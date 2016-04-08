/**
 * 
 */
package com.jabava.controller.system;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrFunctionPoint;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.EhrUserInfoService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * @author pengyr
 *
 */
@Controller
@RequestMapping("/user")
public class UserManageController {
	
	@Resource
	private EhrUserInfoService userInfoService;
	@Resource
	private IUserService userService;
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
//	@Autowired
//	private UserService dubboUserService;
	
	@RequestMapping("/searchUser")
	public String searchUser(String data,
			HttpServletRequest request, HttpServletResponse response){
		return "user/user";
	}
	
	@RequestMapping("userDataTableSearch")
	@ResponseBody
	public Page<EhrUser> dataTableSearch(String data, Integer start, Integer length,
			HttpServletRequest request, HttpServletResponse response){
		Page<EhrUser> page = new Page<>(start, length);
		Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(data), Map.class);	
		EhrUser user = RequestUtil.getLoginUser(request);
		map.put("companyId", user.getCompanyId());
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		order = EhrUser.getColumnName(order);
		try {
			List<EhrUser> list = userInfoService.searchUser(map, search, order, according, page);
			page.setData(list);
			sysLogSercice.addSysLog(user, "查询用户列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	@RequestMapping("/addOrUpdateUser")
	@ResponseBody
	public Map<String, Object> addOrUpdateUser(EhrUser user, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> map = new HashMap<>();
		if("".equals(user.getUserId()) || user.getUserId() == null){
			user.setLastModifyDate(new Date());
			user.setLastModifyUserId(u.getUserId());
			user.setLastModifyUserName(u.getUserName());
			user.setCreateDate(new Date());
			user.setCreateUserId(u.getUserId());
			user.setCreateUserName(u.getUserName());
			user.setLastChangePasswordDate(new Date());
			user.setFailtureTime(0);
			user.setLoginTime(0);
			user.setIsValid((byte) 1);
			user.setIsLocked((byte) 0);
			//user.setPassword("123456");
		}
		try {
			map = userInfoService.addOrUpdateUser(user);
			map.put("msg", "操作成功");
			map.put("success", true);
			sysLogSercice.addSysLog(user, (String) map.get("logInfo"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("/addOrUpdateHR")
	@ResponseBody
	public Map<String, Object> addOrUpdateHR(EhrUser user, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> map = new HashMap<>();
		//user.setUserCode(user.getMobile());
		if("".equals(user.getUserId()) || user.getUserId() == null){
			user.setCompanyId(u.getCompanyId());
			user.setLastModifyDate(new Date());
			user.setLastModifyUserId(u.getUserId());
			user.setLastModifyUserName(u.getUserName());
			user.setCreateDate(new Date());
			user.setCreateUserId(u.getUserId());
			user.setCreateUserName(u.getUserName());
			user.setLastChangePasswordDate(new Date());
			user.setFailtureTime(0);//登陆失败次数
			user.setLoginTime(0);
			user.setIsValid((byte) 1);//有效
			user.setIsLocked((byte) 0);//是否被锁定 
			//user.setPassword("123456");
			user.setUserType((byte)4);//企业用户管理员
			user.setFlag(2);//普通用户
			user.setIsDeleted(0);
		}
		try {
			map = userInfoService.addOrUpdateHR(user);
			sysLogSercice.addSysLog(user, (String) map.get("logInfo"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	@RequestMapping("/updateUserEnter")
	@ResponseBody
	public Map<String, Object> updateUserEnter(EhrUser user, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		try {
			user = userInfoService.getUserById(user.getUserId());
			map.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	 * 改变状态
	 * @param user
	 * @param isValid
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request,Long userId,Integer isValid){
		Map<String, Object> map = new HashMap<>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = userInfoService.deleteUser(userId,isValid);
			if(isValid == 0){//无效
				sysLogSercice.addSysLog(user, "用户管理  改变用户ID:"+userId+"状态为无效");
			}else if(isValid == 1){//有效
				sysLogSercice.addSysLog(user, "用户管理  改变用户ID:"+userId+"状态为有效");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除
	 * @param userId
	 * @param isDelete
	 * @param request
	 * @param response
	 * @return
	 */
 	@RequestMapping("/isDeleteUser")
 	@ResponseBody
	public Map<String, Object> isDeleteUser(Long userId,Integer isDelete,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = userInfoService.isDeleteUser(userId,isDelete);
			sysLogSercice.addSysLog(user, "用户管理 删除用户ID:"+user.getUserId());
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			 
		}
		return map;
	} 
	
	@RequestMapping("/pwdReset")
	@ResponseBody
	public Map<String, Object> pwdReset(EhrUser user){
		Map<String, Object> map = new HashMap<>();
		try {
			user = userInfoService.getUserById(user.getUserId());
			map = userInfoService.pwdReset(user, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//暂不用
	@RequestMapping("/userView")
	public String userView(String data, HttpServletRequest request){
		EhrUser user = (EhrUser) JSONObject.toBean(JSONObject.fromObject(data), EhrUser.class);
		try {
			user = userInfoService.getUserById(user.getUserId());
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/system/user_view";
	}
	
	//暂不用
	@RequestMapping("/companyTree")
	public void companyTree(HttpServletRequest request, HttpServletResponse response, String companyId){
		response.setContentType("text/xml;charset=UTF-8");
		long company = 0;
		if(companyId != null)
		{
			company = Long.parseLong(companyId);
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(userInfoService.companyTree(company));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/userPowerEnter")
	@ResponseBody
	public Map<String, Object> UserPowerEnter (HttpServletRequest request, HttpServletResponse response, EhrUser user){
		Map<String, Object> map = new HashMap<>();
		try {
			List<EhrPersonField> listField = userInfoService.getPersonFieldList();
			 List<EhrUserBusinessPower> listPower  = userInfoService.getUserPowerList(user.getUserId());
			 user =	userInfoService.getUserById(user.getUserId());
			 List<EhrFunctionPoint> listFunctionPoint = userInfoService.getFunctionPoint();
			 List<String[]> list  = userInfoService.getSelectValueInfo(1, user);
			 map.put("listField",listField);
			 map.put("listPower",listPower);
			 map.put("user",user);
			 map.put("listFunctionPoint",listFunctionPoint);
			 map.put("fieldValue", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getFieldValue")
	@ResponseBody
	public String getFieldValue(EhrUserBusinessPower power, HttpServletRequest request){
		String result = null;	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			List<String[]> list  = userInfoService.getSelectValueInfo(power.getFieldId(), user);
			result = JSONArray.fromObject(list).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*暂不用，所以不修改
	@RequestMapping("getFieldType")
	public String getFieldType(HttpServletRequest request, HttpServletResponse response){
		try {
			DataInputStream is=new DataInputStream(request.getInputStream());
			String str=is.readLine();
			String result=String.valueOf(userInfoService.getPersonFieldId(Integer.valueOf(str)).getFieldType());
			PrintWriter out;
			out = response.getWriter();
			out.write(result);
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	//暂不做修改，要和前端商量
	@RequestMapping("/userPowerSave")
	@ResponseBody
	public Map<String, Object> userPowerSave(Long userId, String field){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userInfoService.insertBusinessPower(userId, field);
			map.put("success", true);
			map.put("msg", "权限设置成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "权限设置失败！");
			e.printStackTrace();
		}
		return map;
	}
}
