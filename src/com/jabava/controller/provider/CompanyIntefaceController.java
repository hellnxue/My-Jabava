package com.jabava.controller.provider;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jabava.pojo.manage.EhrCompany;
import com.jabava.service.manage.ICompanyService;
 
/**
 * 提供用户相关的对外的查询接口
 *
 * @version $Id: UserController.java, 
 * v 0.1 2016年4月20日 下午2:44:32 
 * <pre>
 * @author steven.chen
 * @date 2016年4月20日 下午2:44:32 
 * </pre>
 */
@Controller
@RequestMapping("api/companyInteface")
public class CompanyIntefaceController {
	
	@Resource
	private ICompanyService companyService; 
	
	/**
	 * 根据登录名返回公司信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午8:02:47 
	 * </pre>
	 *
	 * @param userName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getCompanysByUserName")
	public  void getCompanyByUserName(String userName,HttpServletRequest request, HttpServletResponse response) 
										throws Exception{
		
		if (userName != null && StringUtils.isNotBlank(userName)) {
			 byte[] b = userName.getBytes("ISO-8859-1");  
			 String str = new String(b,"UTF-8"); 
			EhrCompany company = companyService.getCompanysByUserName(str);
			Map<String, Object> map = new HashMap<String, Object>();
			if (company != null) {

				map.put("companyId", company.getCompanyId()); // 公司id
				map.put("orgId",  company.getCompanyId()-(company.getCompanyId()*2));
				map.put("companyName", company.getCompanyName());
			}else{
				response.getOutputStream().write(("没有找到该公司信息").getBytes("UTF-8"));
			}

			JSONArray jsonArray = JSONArray.fromObject(map);
			response.getOutputStream().write(
					jsonArray.toString().getBytes("UTF-8"));
		} else {
			response.getOutputStream().write(("请传递用户名").getBytes("UTF-8"));
		}
	}
	
	
}
