package com.jabava.controller.hro;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfReceiptOwnedService;
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
@RequestMapping("/efReceiptOwned")
public class EfReceiptOwnedController {
	
//	@Autowired
//	private IEfArapService efArapService;
	
	@Autowired
	private IEfReceiptOwnedService receiptOwnedService;
	
	@RequestMapping("/toReceiptOwned")
	public String toReceiptOwned(HttpServletRequest request,HttpServletResponse response,
			String ym,String amount){
		request.setAttribute("ym", ym);
		request.setAttribute("amount", amount);
		return "hrobilling/shifu_bill";
	}
	
	@RequestMapping("/receiptOwnedListPage")
	@ResponseBody
	public Page<Map<String,Object>> receiptOwnedListPage(HttpServletRequest request,HttpServletResponse response,
			String ym,String amount) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(null,null);
		List<Map<String,Object>> list = receiptOwnedService.queryReceiptOwnedList(user, ym, amount);
		page.setData(list);
		return page; 
	}
    
}
