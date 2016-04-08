package com.jabava.controller.hro;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.BdSbReportDetailService;
import com.jabava.service.hro.BdSbService;
import com.jabava.task.ImportPolicyPackageTask;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/shebao")

public class BdSbController {
	
	@Resource
	private BdSbService bdSbService;
	@Resource
	private BdSbReportDetailService bdSbReportDetailService;
	
	private HROFetchService requestService;
	JSONArray jsonList = null;
	JSONObject resultData = null;
	
	/**
	 * 社保公积金接口测试
	 * @return
	 */
	@RequestMapping("/sbtest")
	public String test(){
		
		//String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		 String server="http://king.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
		
		Map<String, Object> result =null ;
		Map<String, Object> parameter = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();		
	 
		parameter.put("PROTOCOL_CODE", "ZY-HP-20160224-0002");
		parameter.put("code", "querySbDetailInfo");
		data.put("parameter", parameter);			
		try {
			result = requestService.invoke("SqlQuery", data);
			jsonList = getJSONArray(result);
			System.out.println(jsonList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "";
	}
	
	
	// 根据返回结果拿到json串
	private JSONArray getJSONArray(Map<String, Object> result){
		if(!result.isEmpty()
				&& "0".equals(result.get("resultCode").toString())){
			JSONObject jsonObj = JSONObject.fromObject(result
					.get("resultData"));
			if(jsonObj!=null &&jsonObj.size()>0){
				 resultData = JSONObject.fromObject(result
						.get("resultData"));
				if(resultData!=null &&jsonObj.size()>0){
					jsonList = com.alibaba.fastjson.JSONArray.parseArray(jsonObj.get("entities").toString());
					
					if(jsonList!=null && jsonList.size()>0){
						return jsonList;
					}
				}				 

			}
		}
		return jsonList;
	}
	
	
	/**
	 * 查询社保信息
	 *  
	 * @return
	 */
	@RequestMapping("/initSheBaoInfo")
	@ResponseBody
	public Page<Map<String, Object>> getSheBaoInfo(HttpServletRequest request,int policyGroupType,String billYm, Integer start, Integer length){	
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		
		// 设置分页参数
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		List<Map<String, Object>> list=null;
		String search = request.getParameter("search[value]");// search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		String orderBy="reportMonth DESC";
		if(order != null && !"".equals(order)){
			 
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = getSbGjjListColumnName(order);
			orderBy=order + " " + according;
		}
		try {
			//list=bdSbService.getSheBaoInfo(1, reportMonth, likeFiled,page);
			list=bdSbReportDetailService.getSheBaoGongJiJinInfoPage( companyId,search,policyGroupType, billYm, orderBy, page);
			page.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		System.out.println(JSON.toJSON(page));
		return page;
	}
	
	//社保公积金列表查询的排序
		public static String getSbGjjListColumnName(String order){
			if(StringUtils.isNotBlank(order)){
				  if("reportMonth".equals(order)){
					return "reportMonth";
				}else if("employeeName".equals(order)){
					return "employeeName";
				}else if("cardId".equals(order)){
					return "cardId";
				}else if("hireStatus".equals(order)){
					return "hireStatus";
				}else if("companySum".equals(order)){
					return "companySum";
				}else if("individealSum".equals(order)){
					return "individealSum";
				} 
			}
			return "employeeId";
		}
 
		//社保公积金详情列表查询的排序
		public static String getSbGjjDetailListColumnName(String order){
			if(StringUtils.isNotBlank(order)){
				  if("reportMonth".equals(order)){
					return "reportMonth";
				}else if("policyName".equals(order)){
					return "policyName";
				}else if("prodName".equals(order)){
					return "prodName";
					
				}else if("sbMonth".equals(order)){
					
					return "sbMonth";
				}else if("companyBase".equals(order)){
					
					return "companyBase";
				}else if("individualBase".equals(order)){
					
					return "individualBase";
				}else if("companyRatio".equals(order)){
					return "companyRatio";
				}else if("individualRatio".equals(order)){
					return "individualRatio";
				} else if("companyAppend".equals(order)){
					return "companyAppend";
				}else if("individualAppend".equals(order)){
					return "individualAppend";
				}else if("companySum".equals(order)){
					return "companySum";
				}else if("individualSum".equals(order)){
					return "individualSum";
				} 
			}
			return "reportMonth";
		}
			
	/**
	 * 查询社保公积金详情
	 * @param employeeId 员工ID
	 * @param policyGroupType 社保公积金：1社保  2公积金
	 * @param payType  状态：1.汇缴；2.补缴   
	 * @param backpayType  补缴类型： 1.补缴 2.补差 
	 * @return
	 */
	@RequestMapping("/getSbGJJDetail")
	@ResponseBody
	public Page<Map<String, Object>> getDetail(HttpServletRequest request,Long employeeId,String reportMonth,int policyGroupType,int payType,Integer backpayType, Integer start, Integer length){	
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		request.setAttribute("year", JabavaUtil.getDateElement(new Date(), Calendar.YEAR));
		// 设置分页参数
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		List<Map<String, Object>> list=null;
		
		String search = request.getParameter("search[value]");// search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		String orderBy="reportMonth DESC";
		if(order != null && !"".equals(order)){
			 
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = getSbGjjDetailListColumnName(order);
			orderBy=order + " " + according;
		}	
		
	  // list=bdSbReportDetailService.getSheBaoGongJiJinInfoDetailPage(employeeId, policyGroupType, payType,orderBy, page);
	   list=bdSbReportDetailService.getSheBaoGongJiJinInfoDetailPage(companyId,reportMonth,employeeId, policyGroupType, payType, backpayType, orderBy, page);
	   page.setData(list); 
	   System.out.println(JSON.toJSON(list));
	   return page;
	}
	
	
	/**
	 * 查询社保公积金办理人数
	 * @param policyGroupType 社保公积金：1社保  2公积金
	 * @return
	 */
	@RequestMapping("/getSbGjjTotalByReportMonth")
	@ResponseBody
	public Page<Map<String, Object>> getSbGjjTotalByReportMonth(HttpServletRequest request,int policyGroupType,String reportMonth, Integer start, Integer length){	
		EhrUser user = RequestUtil.getLoginUser(request);
		Long companyId = user.getCompanyId();
		// 设置分页参数
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		List<Map<String, Object>> list=null;
	 
	   list=bdSbReportDetailService.getSbGjjTotalByReportMonthPage(policyGroupType,reportMonth,companyId, page);
	   
	   if(list.size()==1&&(list.get(0).get("totalCount").toString()).equals("0")){
		   list.clear();
	   }
	   page.setData(list); 
	   
	   System.out.println(JSON.toJSON(list));
	   return page;
	}
	
	
	
	/**
	 * 社保 index页面
	 * @return
	 */
	@RequestMapping("/shebaoPageIndex")
	public String shebaoPageIndex( HttpServletRequest request){
		request.setAttribute("year", JabavaUtil.getDateElement(new Date(), Calendar.YEAR));
		return "hrobilling/shebaoindex";
	}
	
	/**
	 * 公积金 index页面
	 * @return
	 */
	@RequestMapping("/gongjijinPageIndex")
	public String gongjijinPageIndex(HttpServletRequest request ){
		request.setAttribute("year", JabavaUtil.getDateElement(new Date(), Calendar.YEAR));
		return "hrobilling/gongjijinindex";
	}

	/**
	 * 社保 
	 * @return
	 */
	@RequestMapping("/shebaoPage")
	public String shebaoPage(){
		
		return "hrobilling/shebao";
	}
	
	/**
	 * 公积金 
	 * @return
	 */
	@RequestMapping("/gongjijinPage")
	public String gongjijinPage(){
		
		return "hrobilling/gongjijin";
	}
	

	/**
	 * 社保详情 
	 * @return
	 */
	@RequestMapping("/shebaoDetailPage")
	public String shebaoDetailPage( HttpServletRequest request,Long employeeId,String employeeName,String reportMonth){
		request.setAttribute("employeeId",employeeId );
		request.setAttribute("employeeName",employeeName );
		request.setAttribute("reportMonth",reportMonth );
		return "hrobilling/shebao_xiangqing";
	}
	
	/**
	 * 公积金详情
	 * @return
	 */
	@RequestMapping("/gongjijinDetailPage")
	public String gongjijinDetailPage( HttpServletRequest request,Long employeeId,String employeeName,String reportMonth ){
		request.setAttribute("employeeId",employeeId );
		request.setAttribute("employeeName",employeeName );
		request.setAttribute("reportMonth",reportMonth );
		return "hrobilling/gongjijin_xiangqing";
	}
}
