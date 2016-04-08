
package com.jabava.controller.hro;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody; 

import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfSalaryInfoService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * 工资
 * </pre>
 */
@Controller
@RequestMapping("/salaryInfo")
public class EfSalaryInfoController {
	
	@Autowired
	private IEfSalaryInfoService salaryInfoService;

	/**
	 * 转向工资页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toSalaryInfo")
	public String toSalaryInfo(HttpServletRequest request, HttpServletResponse response) {
		return "hrobilling/pay";
	}
	
	/**
	 * 工资查询列表
	 * @param model
	 * @param request
	 * @param response
	 * @param ehrInformation
	 * @return
	 * @throws UnsupportedEncodingException 
	 */	
	@RequestMapping("/salaryInfoList")
	@ResponseBody
	public Page<Map<String, Object>> salaryInfoList(HttpServletRequest request, HttpServletResponse response,
			int start, int length) throws Exception {	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);

		String ym = request.getParameter("ym");
//		if(StringUtils.isEmpty(ym)){
//			ym = JabavaUtil.parseDate(new Date(), "yyyyMM");
//		}
		String search = request.getParameter("search[value]");// search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		String orderBy = null;
		if(order == null || "".equals(order)){
			orderBy = "grant_date ASC";
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			orderBy = order + " " + according;
		}
		
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		List<Map<String, Object>> list =  salaryInfoService
				.querySalaryInfoPage(ehrUser, page, ym, search, orderBy);
		page.setData(list);
		return page;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
