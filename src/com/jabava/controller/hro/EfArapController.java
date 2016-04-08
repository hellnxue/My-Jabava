package com.jabava.controller.hro;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.hro.EfArap;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfArapService;
import com.jabava.utils.Constants;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * HRO 客户端- 查看账单信息
 *
 * @version $Id: EfArapController.java, 
 * v 0.1 2016年1月22日 上午10:33:07 
 * <pre>
 * @author zhenbo.chen
 * @date 2016年1月22日 上午10:33:07 
 * </pre>
 */
@Controller
@RequestMapping("/efArap")
public class EfArapController {
	
	@Autowired
	private IEfArapService efArapService;
	
	@RequestMapping("/toBillList")
	public String toBillList(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("year", JabavaUtil.getDateElement(new Date(), Calendar.YEAR));
		return "hrobilling/zhangdan";
	}
	
	@RequestMapping("/billListPage")
	@ResponseBody
	public Page<EfArap> billListPage(HttpServletRequest request,HttpServletResponse response,Integer start,Integer length){	
		EhrUser user = RequestUtil.getLoginUser(request);
		String search = request.getParameter("search[value]");//search框的值
		String billYm = request.getParameter("billYm");
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		if(StringUtils.isEmpty(according)){
			according = "asc";
		}
		order = this.getColumnName(order);

		Long companyId = user.getCompanyId();
		Page<EfArap> page = null;
		String noPaging = request.getParameter("noPaging");
		if(StringUtils.isEmpty(noPaging) || "0".equals(noPaging)){
			//page = new Page<EfArap>(start,length);
			//List<EfArap> list = efArapService.searchBill(companyId,billYm,page,search,order + " " + according);
			page = new Page<EfArap>(0,Constants.MAX_RECORD_SIZE);
			String year = request.getParameter("year");
			List<EfArap> list = efArapService.searchBill(companyId,year,search,order + " " + according);
			page.setData(list);
		}else{	//从对账单转向过来的，指定了月份，不需要分页
			//page = new Page<EfArap>(null,null);
			page = new Page<EfArap>(0,Constants.MAX_RECORD_SIZE);
			List<EfArap> list = efArapService.searchBill(companyId,billYm,order + " " + according);
			page.setData(list);
		}
		return page; 
	}
    
	private String getColumnName(String order){
		if(order != null){
			if(order.equals("billId")){
				return "bill_id";
			}else if(order.equals("billYm")){
				return "bill_ym";
			}else if(order.equals("billTemplateId")){
				return "bill_template_id";
			}else if(order.equals("billCode")){
				return "bill_code";
			}else if(order.equals("paymentDay")){
				return "payment_day";
			}else if(order.equals("paymentLockDay")){
				return "payment_lock_day";
			}else if(order.equals("dateBillCreateFirst")){
				return "date_bill_create_first";
			}else if(order.equals("dateBillCreate")){
				return "date_bill_create";
			}else if(order.equals("amount")){
				return "amount";
			}else if(order.equals("amountTotal")){
				return "amount_total";
			}else if(order.equals("statusBill")){
				return "status_bill";
			}
		}
			
		return "bill_id";
	}
	
	@RequestMapping("detailList")
	public String detailList(String billId,String billYm,Model model){
		model.addAttribute("billId", billId);
		model.addAttribute("billYm", billYm);
		return "hrobilling/check";
	}
	
	@RequestMapping("/detailHeaderList")
	@ResponseBody
	public List<Map<String,Object>> detailHeaderList(HttpServletRequest request,HttpServletResponse response,
			Long billId) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EfArap arap = efArapService.findByPrimaryKey(billId);
		if(!user.getCompanyId().equals(arap.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return efArapService.searchBillDetailHeader(arap);
	}
	
	@RequestMapping("/detailListPage")
	@ResponseBody
	public Page<Map<String,Object>> detailListPage(HttpServletRequest request,HttpServletResponse response,
			Long billId) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EfArap arap = efArapService.findByPrimaryKey(billId);
		if(!user.getCompanyId().equals(arap.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		String order = request.getParameter("order[0][column]");//排序列的下标
		String according = request.getParameter("order[0][dir]");//升序或倒序
		if(order == null || "".equals(order)){
			order = "jobNumber";
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		}
		if(according == null || "".equals(according)){
			according = "asc";
		}
		
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(null,null);
		List<Map<String, Object>> list =  efArapService.searchBillDetailData(arap,order,according);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("/toBalanceBill")
	public String toBalanceBill(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("year", JabavaUtil.getDateElement(new Date(), Calendar.YEAR));
		return "hrobilling/duizhangdan";
	}
	
	@RequestMapping("/balanceBillPage")
	@ResponseBody
	public Page<Map<String,Object>> detailListPage(HttpServletRequest request,HttpServletResponse response,
			String year) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(null,null);
		List<Map<String, Object>> list =  efArapService.queryBalanceList(user, year);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("/toBillOfYm")
	public String toBillOfYm(HttpServletRequest request,HttpServletResponse response,String ym){
		request.setAttribute("billYm", ym);
		return "hrobilling/bill";
	}
}








