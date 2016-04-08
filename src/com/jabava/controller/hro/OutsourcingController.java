package com.jabava.controller.hro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.core.EnumConstents.IsDeleted;
import com.jabava.core.EnumConstents.ProtocolStatus;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * HRO 客户端-申请开通,查询协议
 *
 * @version $Id: OutsourcingController.java, 
 * v 0.1 2016年1月7日 下午4:34:32 
 * <pre>
 * @author steven.chen
 * @date 2016年1月7日 下午4:34:32 
 * </pre>
 */
@Controller
@RequestMapping("/outsourcing")
public class OutsourcingController {
	
	@Resource
	private OutsourcingService outsourcingService;
	
	/**
	 * 获取公司名称
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午2:00:12 
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getCompanyName")
	@ResponseBody
	public Map getCompanyName(HttpServletRequest request){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		Map<String,String> result= new HashMap<String,String>();
		result.put("companyName", ehrUser.getCompany().getCompanyName().toString());
		return result;
		
	}
	/**
	 * 查询协议列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午2:00:50 
	 * </pre>
	 *
	 * @param request
	 * @param hroPactInfo
	 * @return
	 */
	@RequestMapping("/queryProtocol")
	@ResponseBody
	public Page<HroPactInfo> queryProtocol(HttpServletRequest request,HroPactInfo hroPactInfo,int start,int length){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		String search = request.getParameter("search[value]");// search框的值
		if(StringUtils.isNotBlank(search)){
			hroPactInfo.setPactCode(search);
		}
		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
	//	hroPactInfo.setState(ProtocolStatus.NOT_OPEN.getValue());
		hroPactInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		Page<HroPactInfo> page = new Page<HroPactInfo>(start, length);		
		String order = request.getParameter("order[0][column]");//排序列的下标
		if(order == null || "".equals(order) || "0".equals(order)){
			hroPactInfo.setOrderBy("ID DESC");
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = getColumnName(order);
			hroPactInfo.setOrderBy(order + " " + according);
		}
		 List<HroPactInfo> list =outsourcingService.queryProtocolPage(hroPactInfo,page) ;
	
		 page.setData(list);
		return page;
		
	}
	
	/**
	 * 申请开通
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 上午10:01:18 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	@RequestMapping("/addOutsourcing")
	@ResponseBody
	public Map<String,Object> addOutsourcing(HttpServletRequest request,HroPactInfo hroPactInfo){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
		hroPactInfo.setState(ProtocolStatus.NOT_OPEN.getValue());
		hroPactInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		Map<String,Object> result = new HashMap<String,Object>();
		if("success".equalsIgnoreCase(outsourcingService.addOutsourcing(hroPactInfo,
				ehrUser.getCompany().getCompanyName()))){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		
		return result;
	}
	
	/**
	 * 查看协议内容
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:56:59 
	 * </pre>
	 *
	 * @param pactCode
	 * @return
	 */
	@RequestMapping("/getProtocolById")
	@ResponseBody
	public HroPactInfo getProtocolById(Long  id){		
		
		HroPactInfo hroPactInfo=  outsourcingService.getProtocolById(id);
		return hroPactInfo;
		
	}
	/**
	 * 停用协议
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:59:38 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	@RequestMapping("/updateProtocol")
	@ResponseBody
	public String updateProtocol(HroPactInfo hroPactInfo){
		return outsourcingService.updateProtocol(hroPactInfo);
	}
	public static String getColumnName(String order){
		if(StringUtils.isNotBlank(order)){
			if(order.equals("id")){
				return "ID";
			}else if("pactCode".equals(order)){
				return "PACT_CODE";
			}else if("state".equals(order)){
				return "STATE";
			}else if("contactEmp".equals(order)){
				return "CONTACT_EMP";
			}else if("telephoneNumber".equals(order)){
				return "TELEPHONE_NUMBER";
			}
		}
		return "ID";
	}
		

}
