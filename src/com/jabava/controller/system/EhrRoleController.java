package com.jabava.controller.system;

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

import com.jabava.pojo.manage.EhrRole;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("acl")
public class EhrRoleController {

	@Resource
	public IEhrRoleService ehrRoleService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;

	/***
	 * 查询
	 * 
	 * @param request
	 * @param roleName
	 * @param roleId
	 * @return
	 */
	@RequestMapping("ehrRoleSearch")
	public String SearchRole(HttpServletRequest request) {
		
		return "acl/role";
	}
	
	@RequestMapping("dataTableSearch")
	@ResponseBody
	public Page<EhrRole> dataTableSearch(HttpServletRequest request, Integer length, Integer start){
		Page<EhrRole> page = new Page<>(start, length);	
		EhrUser user = RequestUtil.getLoginUser(request);
	    Long companyId = user.getCompanyId();
		List<EhrRole> list = null;
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		try {
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrRole.getColumnName(order);
			list = ehrRoleService.searchEhrRole(companyId, search, order, according, isNumeric, page);
			page.setData(list);
			sysLogSercice.addSysLog(user, "查询角色管理列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/***
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param role
	 */
	@RequestMapping("insertRole")
	public void insertRole(HttpServletRequest request,
			HttpServletResponse response, EhrRole role) {
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			role.setCompanyId(user.getCompanyId());
			role.setCreateDate(new Date());
			role.setCreateUserId(user.getUserId());
			role.setCreateUserName(user.getUserName());
			role.setLastModifyDate(new Date());
			role.setLastModifyUserId(user.getUserId());
			role.setLastModifyUserName(user.getUserName());
			boolean result = ehrRoleService.insertRole(role);
			sysLogSercice.addSysLog(user, "角色管理  新增角色名称"+role.getRoleName());
			if (result) {
				map.put("success", result);
				map.put("msg", "添加角色成功");
			} else {
				map.put("success", result);
				map.put("msg", "添加角色失败");
			}
			response.getWriter().print(JSONArray.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 修改前查询（查询条件）
	 * 
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selRoleId")
	@ResponseBody
	public String selectByPrimaryKey(String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		EhrRole roleInfo = null;
		try {
			roleInfo = ehrRoleService.selectByPrimaryKey(Long.valueOf(roleId));
			// response.getWriter().print(JSONObject.fromObject(roleInfo).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(roleInfo).toString();
	}

	/***
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param role
	 */
	@RequestMapping("updatetRole")
	public void updateRole(HttpServletRequest request,
			HttpServletResponse response, EhrRole role) {
		Map<String, Object> info = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			role.setCompanyId(user.getCompanyId());
			role.setCreateDate(new Date());
			role.setCreateUserId(user.getUserId());
			role.setCreateUserName(user.getUserName());
			role.setLastModifyDate(new Date());
			role.setLastModifyUserId(user.getUserId());
			role.setLastModifyUserName(user.getUserName());
			boolean result = ehrRoleService.updateRole(role);
			sysLogSercice.addSysLog(user, "角色管理  修改角色名称"+role.getRoleName());
			if (result) {
				info.put("success", result);
				info.put("msg", "修改角色成功");
			} else {
				info.put("success", result);
				info.put("msg", "修改角色失败");
			}
			response.getWriter().print(JSONArray.fromObject(info).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("deleteRole")
	public void deleteRole(HttpServletRequest request,
			HttpServletResponse response, Long roleId) {
		Map<String, Object> info = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			ehrRoleService.deleteRoleUser(roleId);
			ehrRoleService.deleteRolePower(roleId);
			boolean result = ehrRoleService.deleteRole(roleId);
			sysLogSercice.addSysLog(user, "角色管理  删除角色ID："+roleId);
			if (result) {
				info.put("flag", result);
				info.put("msg", "删除成功");
			} else {
				info.put("flag", result);
				info.put("msg", "删除失败");
			}
				
			response.getWriter().print(JSONArray.fromObject(info).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/memberAllotEnter")
	@ResponseBody
	public Map<String, Object> roleUserEnter(Long roleId, HttpServletResponse reponse, HttpServletRequest request){	
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> map = null;
		try {
			map = ehrRoleService.memberAllotEnter(user,roleId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/memberAllot")
	public void MemberAllot(Long roleId, String userIds, HttpServletRequest request,HttpServletResponse response){	
		EhrUser user = RequestUtil.getLoginUser(request);
		String[] userList = null;
		if(userIds != null && !userIds.equals(""))
			userList = userIds.split(",");
		else
			userList = new String[0];
		try {
			String result = ehrRoleService.memberAllot(new Long(roleId),userList);
			sysLogSercice.addSysLog(user, "角色管理  分配角色ID："+roleId);
			response.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/rolePowerEnter")
	@ResponseBody
	public Map<String, Object> rolePowerEnter(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ehrRoleService.rolePower(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/rolePowerSave")
	@ResponseBody
	public Map<String, Object> rolePowerSave(Long roleId, String buttons, String menus){
		Map<String, Object> map = new HashMap<String, Object>();
		String button[] = (buttons == null || "".equals(buttons)) ? null : buttons.split(",");
		String menu[] = (menus == null || "".equals(menus)) ? null : menus.split(",");
		try {
			ehrRoleService.rolePowerSave(roleId, button, menu);
			map.put("msg", "设置成功！");
			map.put("success", true);
		} catch (Exception e) {
			map.put("msg", "设置失败！");
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}
}