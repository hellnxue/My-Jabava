package com.jabava.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrSysLog;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * 
 * @author panfei
 * 
 */
@Controller
@RequestMapping("/system")
public class SysLogInfoController {

	@Resource
	private IEhrSysLogSercice sysLogService;

	/**
	 * 查询日志
	 * 
	 * @param request
	 * @param companyName
	 * @param startDate
	 * @param endDate
	 * @param operateInfo
	 * @return
	 */
	@RequestMapping("sysLogSearch")
	public String sysLogSearch() {
		return "system/list_log";
	}
	
	@RequestMapping("dataTableSearch")
	@ResponseBody
	public Page<EhrSysLog> dataTableSearch(HttpServletRequest request, String userName, Integer start, Integer length,
			String startDate, String endDate, String operateInfo) {
		Page<EhrSysLog> page = new Page<>(start, length);	
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		List<EhrSysLog> list = null;
		
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		try {
			int isNumeric = JabavaUtil.isNumeric(search);
			order = EhrSysLog.getColumnName(order);
			SimpleDateFormat  df = new SimpleDateFormat("MM/dd/yyyy");
			Date sDate = (startDate != null && !"".equals(startDate)) ? df.parse(startDate) : null;
			Date eDate = (endDate != null && !"".equals(endDate)) ? df.parse(endDate) : null;
			list = sysLogService.selectSysLog(userName, sDate, eDate,
					operateInfo, companyId, search, order, according, isNumeric, page);
			//List<EhrUser> userList = sysLogService.selectByUser(companyId);
			for(EhrSysLog log : list){
				log.setOperateDate1(format.format(log.getOperateDate()));
			}
			page.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page; 
	}

}
