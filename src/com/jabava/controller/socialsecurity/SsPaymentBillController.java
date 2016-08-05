package com.jabava.controller.socialsecurity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.socialsecurity.SsPaymentBill;
import com.jabava.service.socialsecurity.ISsPaymentBillService;
import com.jabava.service.socialsecurity.SsSocialSecurityAccountService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/ssPaymentBill")
public class SsPaymentBillController {
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private ISsPaymentBillService ssPaymentBillService;
	@Autowired
	private SsSocialSecurityAccountService ssSocialSecurityAccountService;
	
	@RequestMapping("toListPaymentBill")
	public String toListPaymentBill(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//查询所有社保账户
		request.setAttribute("ssAccountList", ssSocialSecurityAccountService.findSocialSecurityAccountByCompanyId(user.getCompanyId()));
		
		return "socialpayment/listSocialPay";
	}
	
	@RequestMapping("listPaymentBillPage")
	@ResponseBody
	public Page<SsPaymentBill> listPaymentBillPage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		String socialSecurityAccountId = request.getParameter("socialSecurityAccountId");
		String month = request.getParameter("month");
		String status = request.getParameter("status");
		
		Page<SsPaymentBill> page = new Page<SsPaymentBill>(start, length);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("month", month);
		if(!StringUtils.isEmpty(status)){
			params.put("status", Integer.valueOf(status));
		}
		if(!StringUtils.isEmpty(socialSecurityAccountId)){
			params.put("socialSecurityAccountId", Long.valueOf(socialSecurityAccountId));
		}
		params.put("page", page);
		List<SsPaymentBill> list = ssPaymentBillService.listPaymentBillPage(params);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("generatePaymentBill")
	@ResponseBody
	public Map<String, Object> generatePaymentBill(HttpServletRequest request, HttpServletResponse response,
			Long socialSecurityAccountId, String month){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(socialSecurityAccountId == null || StringUtils.isEmpty(month)){
			return MessageUtil.errorMessage("数据为空");
		}
		
		try {
			if(ssPaymentBillService.generatePaymentBill(user, socialSecurityAccountId, month) == 0){
				return MessageUtil.errorMessage("生成失败");
			}else{
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Ssaf, "生成"+socialSecurityAccountId+"---"+month+"社保汇缴单");
				return MessageUtil.successMessage("生成成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}

	@RequestMapping("changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request, HttpServletResponse response,
			Long id, Integer operType){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(id == null || operType == null){
			return MessageUtil.errorMessage("数据为空");
		}
		
		try {
			if(ssPaymentBillService.changeStatus(user, id, operType) == 0){
				return MessageUtil.errorMessage("修改失败");
			}else{
				if(operType==1){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "锁定id为"+id+"的社保汇缴清单");
				}else if(operType==2){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "解锁id为"+id+"的社保汇缴清单");
				}else if(operType==3){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "报废id为"+id+"的社保汇缴清单");
				}
				return MessageUtil.successMessage("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("toListPaymentBillDetail")
	public String toListPaymentBillDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		String ssPaymentBillId = request.getParameter("ssPaymentBillId");
		if(StringUtils.isEmpty(ssPaymentBillId)){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(Long.valueOf(ssPaymentBillId));
		if(bill == null){
			throw new Exception("社保清单不存在");
		}
		if(!bill.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		request.setAttribute("ssPaymentBill", bill);
		return "socialpayment/listDetail";
	}
	
	@RequestMapping("listPaymentBillDetail")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillDetail(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId){
		EhrUser user = RequestUtil.getLoginUser(request);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillDetail(user.getCompanyId(), ssPaymentBillId);
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("checkPaymentBill")
	@ResponseBody
	public Map<String,Object> checkPaymentBill(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		String month = request.getParameter("month");
		String socialSecurityAccountId = request.getParameter("socialSecurityAccountId");
		if(StringUtils.isEmpty(month) || StringUtils.isEmpty(socialSecurityAccountId)){
			return MessageUtil.errorMessage("请求数据为空");
		}
		
		SsPaymentBill bill = ssPaymentBillService.findByMonthAndSsAccount(user.getCompanyId(), month, Long.valueOf(socialSecurityAccountId));
		if(bill == null){
			return MessageUtil.errorMessage("社保清单不存在");
		}
		
		Map<String, Object> result = MessageUtil.successMessage("检查成功");
		result.put("ssPaymentBillId", bill.getId());
		return result;
	}
	
	@RequestMapping("toListPaymentBillPerson")
	public String toListPaymentBillPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		String ssPaymentBillId = request.getParameter("ssPaymentBillId");
		if(StringUtils.isEmpty(ssPaymentBillId)){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(Long.valueOf(ssPaymentBillId));
		if(bill == null){
			throw new Exception("社保清单不存在");
		}
		if(!bill.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		request.setAttribute("ssPaymentBill", bill);
		request.setAttribute("from", request.getParameter("from"));
		
		String t = request.getParameter("t");
		if("ZY".equalsIgnoreCase(t)){			//增员
			return "socialpayment/listAdd";
		}else if("JY".equalsIgnoreCase(t)){		//减员
			return "socialpayment/listMinus";
		}else if("BG".equalsIgnoreCase(t)){		//变更
			return "socialpayment/listChange";
		}else{									//全部
			return "socialpayment/listFull";
		}
	}
	
	@RequestMapping("loadPaymentBillPersonHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonHeader(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return ssPaymentBillService.loadPaymentBillPersonHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPerson")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPerson(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillPerson(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonZyHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonZyHeader(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return ssPaymentBillService.loadPaymentBillPersonZyHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonZy")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonZy(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillPersonZy(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonJyHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonJyHeader(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return ssPaymentBillService.loadPaymentBillPersonJyHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonJy")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonJy(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillPersonJy(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonTjHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonTjHeader(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return ssPaymentBillService.loadPaymentBillPersonTjHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonTj")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonTj(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillPersonTj(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonBjHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonBjHeader(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return ssPaymentBillService.loadPaymentBillPersonBjHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonBj")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonBj(HttpServletRequest request, HttpServletResponse response,
			Long ssPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(ssPaymentBillId == null){
			throw new Exception("数据为空");
		}
		SsPaymentBill bill = ssPaymentBillService.findByPrimaryKey(ssPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = ssPaymentBillService.listPaymentBillPersonBj(bill);
		page.setData(list);
		
		return page;
	}
}
