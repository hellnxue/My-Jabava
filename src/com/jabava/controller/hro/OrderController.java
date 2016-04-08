package com.jabava.controller.hro;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jabava.core.EnumConstents.HireStatus;
import com.jabava.core.EnumConstents.OrderStatus;
import com.jabava.core.EnumConstents.SbStatus;
import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdDetailVO;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdVO;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.order.OrderService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.excel.ExcelUtil;

/**
 * HRO 客户端  - 订单
 *
 * @version $Id: OrderController.java, 
 * v 0.1 2016年1月22日 下午2:41:58 
 * <pre>
 * @author steven.chen
 * @date 2016年1月22日 下午2:41:58 
 * </pre>
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger log = Logger.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/toOrderMain")	
	public String toOrderMain(HttpServletRequest request){	
		request.setAttribute("year", new Date().getYear()+1900);		
		return "/hroorder/order_index";
	}
	
	/**
	 * 新版的订单主页面
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月10日 下午9:57:11 
	 * </pre>
	 *
	 * @param request
	 * @param date
	 * @param type
	 * @param start
	 * @param length
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@RequestMapping("/findOrderListPageNew")
	@ResponseBody
	public Page<HsEmpOrdVO> findOrderListPageNew(HttpServletRequest request,int year,int start,int length){
		Page<HsEmpOrdVO> page = new Page<HsEmpOrdVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());
		Date startDay  =null;//查询的开始时间
		Date endDay  = null;//查询的结束时间
		if(year>0){
			startDay = JabavaDateUtils.firstDayOfMonth(year, 1);
			endDay = JabavaDateUtils.lastDayOfMonth(year, 12);
		}else{
			startDay = JabavaDateUtils.firstDayOfMonth(new Date().getYear()+1900, 1);
			endDay = JabavaDateUtils.lastDayOfMonth(new Date().getYear()+1900, 12);
		}
		
		map.put("orderDateStart",String.valueOf(year)+"01");
	    map.put("orderDateEnd",String.valueOf(year)+"12");
		List<HsEmpOrdVO> orderList =  orderService.findServiceCountPage(map);	
		//log.debug(JSONObject.toJSON(orderList));
		page.setData(orderList);
		return page;
	}
	/**
	 * 跳转到订单列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月11日 上午1:01:43 
	 * </pre>
	 *
	 * @param model
	 * @param request
	 * @param yearmonth
	 * @return
	 */
	@RequestMapping("/toOrderList")	
	public String toOrderList(Model model,HttpServletRequest request,String yearmonth){
		System.out.println(yearmonth+"跳转到订单列表");
		request.setAttribute("yearmonth", yearmonth);
		model.addAttribute("yearmonth", yearmonth);
		
		return "/hroorder/order_list";
	}
	/**
	 * 订单列表查询
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:19:19 
	 * </pre>
	 *
	 * @param request
	 * @param queryOrderParams
	 * @param start
	 * @param length
	 * @return
	 */	
	@RequestMapping("/findOrderListPage")
	@ResponseBody
	public Page<HsEmpOrdVO> findOrderListPage(HttpServletRequest request,String date,String type,int start,int length){
		
		Page<HsEmpOrdVO> page = new Page<HsEmpOrdVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		
		String order = request.getParameter("order[0][column]");//排序列的下标
		if(order == null || "".equals(order)){
			map.put("orderBy", "send_time DESC");			
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = getOrderListColumnName(order);
			map.put("orderBy", order + " " + according);
			
		}
		//订单状态列表
		List<Long> orderStatusList  = new ArrayList<Long>();
		//search 框不是空
		if(StringUtils.isNotBlank(search)){
			if("服务中".equals(search)){
				orderStatusList.add(OrderStatus.ADD_CONFIRM.getValue());
				orderStatusList.add(OrderStatus.CHANGE_CONFIRM.getValue());
				map.put("orderStatusList",orderStatusList);		
			}else if("已停止".equals(search)){
				orderStatusList.add(OrderStatus.CANCEL_REDUCE.getValue());
				orderStatusList.add(OrderStatus.REDUCE_CONFIRM.getValue());
				map.put("orderStatusList",orderStatusList);		
			}else if("未申请".equals(search)){
				map.put("sBGjjStatus",SbStatus.NOT_APPLY.getValue());
			}else if("已申请".equals(search)){
				map.put("sBGjjStatus",SbStatus.APPLY.getValue());
			}else if("已缴纳".equals(search)){
				map.put("sBGjjStatus",SbStatus.PAY.getValue());
			}else if("已停缴".equals(search)){
				map.put("sBGjjStatus",SbStatus.NOT_PAY.getValue());
			}else if("入职中".equals(search)){
				map.put("hireStatus",HireStatus.ENTRY.getValue());
			}else if("在职".equals(search)){
				map.put("hireStatus",HireStatus.JOB.getValue());
			}else if("离职中".equals(search)){
				map.put("hireStatus",HireStatus.LEAVING.getValue());
			}else if("离职".equals(search)){
				map.put("hireStatus",HireStatus.LEAVE.getValue());
			}else{
				map.put("search", search);
			}			
		}
		Date startDay  =null;//查询的开始时间
		Date endDay  = null;//查询的结束时间
		//选择了年月
		if(StringUtils.isNotBlank(date)){
			int firstDay  = Integer.parseInt(date.substring(0, 4));
			int lastDay = 0 ;
			if(date.length()>6){
				 lastDay = Integer.parseInt(date.substring(date.length()-2, date.length()));
			}else {
				lastDay = Integer.parseInt(date.substring(date.length()-1, date.length()));				
			}
			//int lastDay  = Integer.parseInt(date.substring(5, 7));
			startDay = JabavaDateUtils.firstDayOfMonth(firstDay, lastDay);
			endDay = JabavaDateUtils.lastDayOfMonth(firstDay, lastDay);
		}else{
			//上个月月第一天 00：00：00
			 startDay  = JabavaDateUtils.previousMonth();
			//本月最后一天 23：59：59
			 endDay  = JabavaDateUtils.lastDayOfMonth();		
		}	
		//没有选择日期，并且搜索框不为空 ,开始结束日期为空
		if(StringUtils.isBlank(date) && StringUtils.isNotBlank(search)){
			startDay=null;
			endDay= null;
		}		
	   if ("change".equals(type)){// 本月变更			
			map.put("orderStatus",OrderStatus.CHANGE_CONFIRM.getValue());	
			map.put("orderDateStart",startDay);
		    map.put("orderDateEnd",endDay);
		}
	    
	    map.put("billMonth",date);	   
	    map.put("nowMonth","201604");	   
		map.put("type", type);
		//本月新增
	/*	if("add".equals(type)){			
			map.put("orderStatus",OrderStatus.ADD_CONFIRM.getValue());			
		}else if("del".equals(type)){//本月减员			
			orderStatusList.add(OrderStatus.CANCEL_REDUCE.getValue());
			orderStatusList.add(OrderStatus.REDUCE_CONFIRM.getValue());
			map.put("orderStatusList",orderStatusList);		
		}else if("change".equals(type)){// 本月变更			
			map.put("orderStatus",OrderStatus.CHANGE_CONFIRM.getValue());			
		}*/
		map.put("page", page);
		List<HsEmpOrdVO> orderList =  orderService.findOrderListPage(map);	
		log.debug(JSONObject.toJSON(orderList));
		page.setData(orderList);
		return page;
		
	}
	/**
	 * 跳转到订单详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月11日 上午1:02:09 
	 * </pre>
	 *
	 * @param model
	 * @param request
	 * @param yearmonth
	 * @param orderId
	 * @param ordRecId
	 * @return
	 */
	@RequestMapping("/toOrderDetail")	
	public String toOrderDetail(Model model,HttpServletRequest request,String paymentMonth,Long orderId,Long ordRecId){
		System.out.println(orderId+"orderId 跳转到订单详情 ");
		request.setAttribute("paymentMonth", request.getParameter("paymentMonth"));
		request.setAttribute("orderId", orderId);
		request.setAttribute("ordRecId", ordRecId);		
		return "/hroorder/order_information";
	}

	@RequestMapping("/showDetail")
	public String showDetail(Model model,Long orderId,Long ordRecId){
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("ordRecId", ordRecId);
		return "hroorder/order_information";
	}
	/**
	 * 查询订单详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:28:28 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */	
	@RequestMapping("/findOrderDetail")
	@ResponseBody
	public HsEmpOrdVO findOrderDetail(HttpServletRequest request,HsEmpOrd hsEmpOrd){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		hsEmpOrd.setCompanyId(ehrUser.getCompanyId());
		HsEmpOrdVO orderDetail  = orderService.findOrderDetail(hsEmpOrd);
		log.debug(JSONObject.toJSON(orderDetail));
		return orderDetail;
	}
	/**
	 * 订单详情 -社保公积金列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:29:18 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/findOrderSB")
	@ResponseBody
	public Map<String,Object> findOrderSB(HttpServletRequest request,HsEmpOrdRec hsEmpOrdRec){
		Map<String,Object> map = new HashMap<String,Object>();	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		hsEmpOrdRec.setCompanyId(ehrUser.getCompanyId());		
		List<HsEmpOrdSbDetailRec> sbRecList  = orderService.findOrderSB(hsEmpOrdRec);
		log.debug(JSONObject.toJSON(sbRecList));
		map.put("data", sbRecList);
		return map;
		
	}
	/**
	 * 订单详情 - 非社保公积金列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:30:06 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/findOrderNSB")
	@ResponseBody
	public Map<String,Object> findOrderNSB(HttpServletRequest request,HsEmpOrdRec hsEmpOrdRec){
		Map<String,Object> map = new HashMap<String,Object>();	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		hsEmpOrdRec.setCompanyId(ehrUser.getCompanyId());	
		List<HsEmpOrdNsbDetailRec> nsbRecList  = orderService.findOrderNSB(hsEmpOrdRec);
		log.debug(JSONObject.toJSON(nsbRecList));
		map.put("data", nsbRecList);
		return map;
	}
	/**
	 * 订单费用信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月25日 下午3:12:04 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */	
	@RequestMapping("/findOrderCostPage")
	@ResponseBody
	public Page<HsEmpOrdDetailVO> findOrderCostPage(HttpServletRequest request,
			String paymentMonth,Long ordRecId,Long orderId) {
		/*Map<String, Object> params = (Map) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject
                .fromObject(data), Map.class);*/	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		Page<HsEmpOrdDetailVO> page = new Page<HsEmpOrdDetailVO>(0, 1000);
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("companyId", ehrUser.getCompanyId());
		map.put("type","repayment");
		if(StringUtils.isNotBlank(paymentMonth)){
			String year  = paymentMonth.substring(0, 4);
			String month= null;
			if(paymentMonth.length()>6){
				 month= paymentMonth.substring(5, paymentMonth.length());
			}else {
				month= "0"+paymentMonth.substring( paymentMonth.length()-1, paymentMonth.length());
			}
			
			map.put("paymentMonth",year+month); 
		}		
		/*if(params!=null ){
			if(params.get("paymentMonthStart")!=null && StringUtils.isNotBlank(params.get("paymentMonthStart").toString())){				
				map.put("paymentMonthStart",params.get("paymentMonthStart").toString());				
			}
			if(params.get("paymentMonthEnd")!=null && StringUtils.isNotBlank(params.get("paymentMonthEnd").toString())){
				map.put("paymentMonthEnd",params.get("paymentMonthEnd").toString());				
			}
			if(params.get("billMonthStart")!=null && StringUtils.isNotBlank(params.get("billMonthStart").toString())){				
				map.put("billMonthStart",params.get("billMonthStart").toString());		
			}
			if(params.get("billMonthEnd")!=null && StringUtils.isNotBlank(params.get("billMonthEnd").toString())){				
				map.put("billMonthEnd",params.get("billMonthEnd").toString());

			}
			if(params.get("type")!=null && StringUtils.isNotBlank(params.get("type").toString())){				
				map.put("type",params.get("type").toString());
			}
			  if("bill".equals(map.get("type").toString())){//如果是账单月查询
				  map.put("paymentMonthStart",null);
				  map.put("paymentMonthEnd",null);
			  }else{
				  map.put("billMonthStart",null);
				  map.put("billMonthEnd",null);
			  }
		}*/
		map.put("page", page);
		List<HsEmpOrdDetailVO> hsEmpOrdDetailVOList  = orderService.findOrderCostPage(map);
		log.debug(JSONObject.toJSON(hsEmpOrdDetailVOList));
		map.put("page", page);		
		page.setData(hsEmpOrdDetailVOList);
		return page;
		
	}
	/**
	 * 导出订单列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月28日 下午2:20:19 
	 * </pre>
	 * 暂时用不到这个方法
	 * @param request
	 * @param data
	 * @param start
	 * @param length
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/exportOrderList")
	@ResponseBody
	public Page<HsEmpOrdVO> exportOrderList(HttpServletRequest request, HttpServletResponse response,String data){
		Map<String, Object> params = (Map) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject
                .fromObject(data), Map.class);
		Page<HsEmpOrdVO> page = new Page<HsEmpOrdVO>(0, 1000);
		Map<String, Object> map =  new HashMap<String, Object>();	
		if(params!=null ){			
		//	map = getQueryParams(map,params);
		}
		map.put("page", page);
		List<HsEmpOrdVO> orderList =  orderService.findOrderList(map);	
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList = getDataList(orderList,dataList);
		datas.put("PersonEach", dataList);
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("import_order.xlsx"));
		
		try {
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "import_order.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("", e);
		}
		page.setData(orderList);
		return page;
		
	}
	// 暂时不用 
	private List<Map<String, Object>> getDataList(List<HsEmpOrdVO> orderList,List<Map<String, Object>> dataList){
		if(orderList!=null&& orderList.size()>0){
			for(HsEmpOrdVO hsEmpOrdVO:orderList){
				Map<String, Object> map = new HashMap<String, Object>();
				if(hsEmpOrdVO.getEhrPerson()!=null && StringUtils.isNotBlank(hsEmpOrdVO.getEhrPerson().getEmployeeName())){
					map.put("employeeName", hsEmpOrdVO.getEhrPerson().getEmployeeName());
				}
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getPersonId()!=null){
					map.put("personId", hsEmpOrdVO.getEhrPerson().getPersonId());	
				}
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getCertType()!=null){
					map.put("certType", hsEmpOrdVO.getEhrPerson().getCertType());	
				}
				if(hsEmpOrdVO.getEhrPerson()!=null && StringUtils.isNotBlank(hsEmpOrdVO.getEhrPerson().getCertId())){
					map.put("certId", hsEmpOrdVO.getEhrPerson().getCertType());	
				}
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getSex()!=null){
					map.put("sex", hsEmpOrdVO.getEhrPerson().getSex());	
				}
				if(hsEmpOrdVO.getEhrPerson()!=null && StringUtils.isNotBlank(hsEmpOrdVO.getEhrPerson().getWorkLocation())){
					map.put("workLocation", hsEmpOrdVO.getEhrPerson().getWorkLocation());	
				}
				//入职日期
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getEntryDate()!=null){
					map.put("entryDate", hsEmpOrdVO.getEhrPerson().getEntryDate());	
				}
				//离职日期
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getLeftDate()!=null){
					map.put("leftDate", hsEmpOrdVO.getEhrPerson().getLeftDate());	
				}
				//离职原因
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getLeftCause()!=null){
					map.put("leftCause", hsEmpOrdVO.getEhrPerson().getLeftCause());	
				}
				//备注
				if(hsEmpOrdVO.getEhrPerson()!=null && StringUtils.isNotBlank(hsEmpOrdVO.getEhrPerson().getMemo())){
					map.put("memo", hsEmpOrdVO.getEhrPerson().getMemo());	
				}
				//雇佣状态
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getStatus()!=null){
					map.put("status", hsEmpOrdVO.getEhrPerson().getStatus());	
				}
				//员工雇主 //TODO
				if(hsEmpOrdVO.getEhrPerson()!=null && hsEmpOrdVO.getEhrPerson().getStatus()!=null){
					map.put("company", hsEmpOrdVO.getEhrPerson().getStatus());	
				}
				//客服
				if(StringUtils.isNotBlank(hsEmpOrdVO.getCreateUser())){
					map.put("createUser", hsEmpOrdVO.getCreateUser());	
				}
				//订单创建时间
				if(hsEmpOrdVO.getOrderStatus()!=null){
					map.put("orderStatus", hsEmpOrdVO.getOrderStatus());	
				}
				//订单创建时间
				if(hsEmpOrdVO.getCreateDate()!=null){
					map.put("createDate", hsEmpOrdVO.getCreateDate());	
				}
				//报减员
				if(StringUtils.isNotBlank(hsEmpOrdVO.getReducestaff())){
					map.put("reducestaff", hsEmpOrdVO.getReducestaff());	
				}
				//报减时间
				if(hsEmpOrdVO.getReducestaffUpdateDt()!=null){
					map.put("reducestaffUpdateDt", hsEmpOrdVO.getReducestaffUpdateDt());	
				}
				dataList.add(map);
			}
			
		}
		return dataList;
		
	} 
	//查询订单的参数
	@SuppressWarnings("unused")
	private Map<String, Object>  getQueryParams(Map<String, Object> map,Map<String, Object> params){

		if(params.get("employeeName")!=null && StringUtils.isNotBlank(params.get("employeeName").toString())){
			map.put("employeeName",params.get("employeeName").toString());
		}
		if(params.get("sex")!=null && StringUtils.isNotBlank(params.get("sex").toString())){
			map.put("sex",params.get("sex").toString());
		}
		if(params.get("cardType")!=null && StringUtils.isNotBlank(params.get("cardType").toString())){
			map.put("cardType",params.get("cardType").toString());
		}
		if(params.get("cardId")!=null && StringUtils.isNotBlank(params.get("cardId").toString())){
			map.put("cardId",params.get("cardId").toString());
		}
		if(params.get("hireStatus")!=null && StringUtils.isNotBlank(params.get("hireStatus").toString())){
			map.put("hireStatus",params.get("hireStatus").toString());
		}
		if(params.get("orderStatus")!=null && StringUtils.isNotBlank(params.get("orderStatus").toString())){
			map.put("orderStatus",params.get("orderStatus").toString());
		}			
		if(params.get("workCity")!=null && StringUtils.isNotBlank(params.get("workCity").toString())){
			map.put("workCity",params.get("workCity").toString());
		}
		if(params.get("entryDateStart")!=null && StringUtils.isNotBlank(params.get("entryDateStart").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("entryDateStart").toString()+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("entryDateStart",date);
		}
		if(params.get("entryDateEnd")!=null && StringUtils.isNotBlank(params.get("entryDateEnd").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("entryDateEnd").toString()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("entryDateEnd",date);
		}
		if(params.get("leftDateStart")!=null && StringUtils.isNotBlank(params.get("leftDateStart").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("leftDateStart").toString()+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("leftDateStart",date);
		}
		if(params.get("leftDateEnd")!=null && StringUtils.isNotBlank(params.get("leftDateEnd").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("leftDateEnd").toString()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("leftDateEnd",date);
		}
		if(params.get("createOrderDateStart")!=null && StringUtils.isNotBlank(params.get("createOrderDateStart").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("createOrderDateStart").toString()+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("createOrderDateStart",date);
		}
		if(params.get("createOrderDateEnd")!=null && StringUtils.isNotBlank(params.get("createOrderDateEnd").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("createOrderDateEnd").toString()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("createOrderDateEnd",date);
		}
		if(params.get("reducestaffDateStart")!=null && StringUtils.isNotBlank(params.get("reducestaffDateStart").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("reducestaffDateStart").toString()+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("reducestaffDateStart",date);
		}
		if(params.get("reducestaffDateEnd")!=null && StringUtils.isNotBlank(params.get("reducestaffDateEnd").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			try {
				date = dateFormat.parse(params.get("reducestaffDateEnd").toString()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}				
			map.put("reducestaffDateEnd",date);
		}
		return map;
		
	}
	//订单列表查询的排序
	public static String getOrderListColumnName(String order){
		if(StringUtils.isNotBlank(order)){
			if(order.equals("bdEmpBaseInfo.employeeName")){
				return "p.employee_name";
			}else if("cardTypeShow".equals(order)){
				return "p.card_type";
			}else if("bdEmpBaseInfo.cardId".equals(order)){
				return "p.card_id";
			}else if("genderShow".equals(order)){
				return "p.gender";
			}else if("bdEmpBaseInfo.workCity".equals(order)){
				return "p.work_city";
			}else if("bdEmpRecInfo.hireDate".equals(order)){
				return "rec.hire_date";
			}else if("createUser".equals(order)){
				return "hs_emp_ord.create_user";
			}else if("hireStatusShow".equals(order)){
				return "rec.hire_status";
			}else if("orderStatusShow".equals(order)){
				return "hs_emp_ord.order_status";
			}else if("createDate".equals(order)){
				return "hs_emp_ord.create_date";
			}else if("bdEmpRecInfo.dimissionDate".equals(order)){
				return "rec.dimission_date";
			}else if("dimissionTypeShow".equals(order)){
				return "rec.dimission_type";
			}else if("bdEmpRecInfo.dimissionRemark".equals(order)){
				return "rec.dimission_remark";
			}else if("reducestaff".equals(order)){
				return "hs_emp_ord.reducestaff";
			}else if("reducestaffUpdateDt".equals(order)){
				return "hs_emp_ord.reducestaff_update_dt";
			}
		}
		return "hro_order_send_id";
	}
	
}
