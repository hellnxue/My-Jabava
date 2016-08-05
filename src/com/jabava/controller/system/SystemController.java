package com.jabava.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrArgumentInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IArgumentService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.service.system.IMenuService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/system")
public class SystemController {

	@Resource
	private IMenuService menuService;

	@Resource
	private IArgumentService argumentService;

	@Resource
	private IEhrSysLogSercice sysLogSercice;

	@RequestMapping("/to_argument")
	public String to_argument(EhrArgumentInfo argument,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EhrUser user = RequestUtil.getLoginUser(request);
		List<String> keys = argumentService.selectAllKey(user.getCompanyId());
		request.setAttribute("keys", keys);
		return "system/list_parameter";
	}

	@RequestMapping("argument")
	@ResponseBody
	public Page<EhrArgumentInfo> argument(EhrArgumentInfo argument,
			Integer start, Integer length, HttpServletRequest request,
			HttpServletResponse response) {
		Page<EhrArgumentInfo> page = new Page<>(start, length);
		try {	
			EhrUser user = RequestUtil.getLoginUser(request);
			String search = request.getParameter("search[value]");// search框的值
			String order = request.getParameter("order[0][column]");// 排序列的下标
			order = request.getParameter("columns[" + order + "][data]"); // 排序列的名称
			String according = request.getParameter("order[0][dir]");// 升序或倒序
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrArgumentInfo.getColumnName(order);
			List<EhrArgumentInfo> arguments = argumentService.queryMyArgument(
					user.getCompanyId(), search, order, according, isNumeric,
					page);
			page.setData(arguments);
			//sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select,SystemEnum.Module.SystemManagement,"查询参数列表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@RequestMapping("/addArgument")
	@ResponseBody
	public Map<String, Object> addArgument(EhrArgumentInfo argument,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			boolean result = argumentService.addArgument(argument);
			String msg = "新增失败";
			if (result) {
				msg = "新增成功";
			}
			data.put("msg", msg);
			data.put("result", result);
			request.setAttribute("data", data);
			sysLogSercice.addSysLog(user,SystemEnum.LogOperateType.Add, SystemEnum.Module.SystemManagement,
					"新增参数,KEY:" + argument.getArgumentKey() + ",VALUE:"
							+ argument.getArgumentValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("/delArgument")
	@ResponseBody
	public Map<String, Object> delArgument(Long id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			boolean result = argumentService.delArgument(id);
			String msg = "删除失败";
			if (result) {
				msg = "删除成功";
			}
			data.put("msg", msg);
			data.put("result", result);
			request.setAttribute("data", data);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete,SystemEnum.Module.SystemManagement, "删除参数设置 参数ID:" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("/updateArgument")
	@ResponseBody
	public Map<String, Object> updateArgument(EhrArgumentInfo argument,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			boolean result = argumentService.updateArgument(argument);
			String msg = "修改失败";
			if (result) {
				msg = "修改成功";
			}
			StringBuffer sb = new StringBuffer();
			sb.append("修改参数");
			if (argument.getArgumentKey() != null
					&& !"".equals(argument.getArgumentKey())) {
				sb.append(",KEY:" + argument.getArgumentKey());
			}
			if (argument.getArgumentValue() != null
					&& !"".equals(argument.getArgumentValue())) {
				sb.append(",VALUE:" + argument.getArgumentValue());
			}
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update,SystemEnum.Module.SystemManagement,"修改参数"+sb.toString());
			data.put("msg", msg);
			data.put("result", result);
			request.setAttribute("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
