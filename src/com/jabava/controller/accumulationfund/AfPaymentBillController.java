package com.jabava.controller.accumulationfund;

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

import com.jabava.pojo.accumulationfund.AfPaymentBill;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.accumulationfund.AccumulationFundAccountService;
import com.jabava.service.accumulationfund.AfPaymentBillService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/afPaymentBill")
public class AfPaymentBillController {

	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private AfPaymentBillService afPaymentBillService;
	@Autowired
	private AccumulationFundAccountService accumulationFundAccountService;
	
	@RequestMapping("toListPaymentBill")
	public String toListPaymentBill(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		//查询所有社保账户
		request.setAttribute("afAccountList", accumulationFundAccountService.getAccumulationFundProfileByCompanyId(user.getCompanyId()));
		
		return "billofexchange/listBillExchange";
	}
	
	@RequestMapping("listPaymentBillPage")
	@ResponseBody
	public Page<AfPaymentBill> listPaymentBillPage(HttpServletRequest request,String data, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		String accumulationFundAccountId = request.getParameter("accumulationFundAccountId");
		String month = request.getParameter("month");
		String status = request.getParameter("status");
		
		Page<AfPaymentBill> page = new Page<AfPaymentBill>(start, length);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("month", month);
		if(!StringUtils.isEmpty(status)){
			params.put("status", Integer.valueOf(status));
		}
		if(!StringUtils.isEmpty(accumulationFundAccountId)){
			params.put("accumulationFundAccountId", Long.valueOf(accumulationFundAccountId));
		}
		params.put("page", page);
		List<AfPaymentBill> list = afPaymentBillService.listPaymentBillPage(params);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("generatePaymentBill")
	@ResponseBody
	public Map<String, Object> generatePaymentBill(HttpServletRequest request, HttpServletResponse response,
			Long accumulationFundAccountId, String month){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(accumulationFundAccountId == null || StringUtils.isEmpty(month)){
			return MessageUtil.errorMessage("数据为空");
		}

		try {
			if(afPaymentBillService.generatePaymentBill(user, accumulationFundAccountId, month) == 0){
				return MessageUtil.errorMessage("生成失败");
			}else{
				sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Ssaf, "生成"+accumulationFundAccountId+"---"+month+"公积金汇缴单");
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
			if(afPaymentBillService.changeStatus(user, id, operType) == 0){
				return MessageUtil.errorMessage("修改失败");
			}else{
				if(operType==1){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "锁定id为"+id+"的公积金汇缴清单");
				}else if(operType==2){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "解锁id为"+id+"的公积金汇缴清单");
				}else if(operType==3){
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Ssaf, "报废id为"+id+"的公积金汇缴清单");
				}
				return MessageUtil.successMessage("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("checkPaymentBill")
	@ResponseBody
	public Map<String,Object> checkPaymentBill(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		String month = request.getParameter("month");
		String accumulationFundAccountId = request.getParameter("accumulationFundAccountId");
		if(StringUtils.isEmpty(month) || StringUtils.isEmpty(accumulationFundAccountId)){
			return MessageUtil.errorMessage("请求数据为空");
		}
		
		AfPaymentBill bill = afPaymentBillService.findByMonthAndAfAccount(user.getCompanyId(), month, Long.valueOf(accumulationFundAccountId));
		if(bill == null){
			return MessageUtil.errorMessage("公积金清单不存在");
		}
		
		Map<String, Object> result = MessageUtil.successMessage("检查成功");
		result.put("afPaymentBillId", bill.getAfPaymentBillId());
		return result;
	}
	
	@RequestMapping("toListPaymentBillPerson")
	public String toListPaymentBillPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		String afPaymentBillId = request.getParameter("afPaymentBillId");
		if(StringUtils.isEmpty(afPaymentBillId)){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(Long.valueOf(afPaymentBillId));
		if(bill == null){
			throw new Exception("公积金清单不存在");
		}
		if(!bill.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		request.setAttribute("afPaymentBill", bill);
		request.setAttribute("from", request.getParameter("from"));
		
		String t = request.getParameter("t");
		if("ZY".equalsIgnoreCase(t)){			//增员
			return "billofexchange/listBillAdd";
		}else if("JY".equalsIgnoreCase(t)){		//减员
			return "billofexchange/listBillMinus";
		}else if("BG".equalsIgnoreCase(t)){		//变更
			return "billofexchange/listBillChange";
		}else{									//全部
			return "billofexchange/listBillFull";
		}
	}
	
	@RequestMapping("loadPaymentBillPersonHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonHeader(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return afPaymentBillService.loadPaymentBillPersonHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPerson")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPerson(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = afPaymentBillService.listPaymentBillPerson(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonZyHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonZyHeader(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return afPaymentBillService.loadPaymentBillPersonZyHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonZy")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonZy(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = afPaymentBillService.listPaymentBillPersonZy(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonJyHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonJyHeader(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return afPaymentBillService.loadPaymentBillPersonJyHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonJy")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonJy(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = afPaymentBillService.listPaymentBillPersonJy(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonTjHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonTjHeader(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return afPaymentBillService.loadPaymentBillPersonTjHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonTj")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonTj(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = afPaymentBillService.listPaymentBillPersonTj(bill);
		page.setData(list);
		
		return page;
	}
	
	@RequestMapping("loadPaymentBillPersonBjHeader")
	@ResponseBody
	public List<Map<String,Object>> loadPaymentBillPersonBjHeader(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		return afPaymentBillService.loadPaymentBillPersonBjHeader(bill);
	}
	
	@RequestMapping("listPaymentBillPersonBj")
	@ResponseBody
	public Page<Map<String,Object>> listPaymentBillPersonBj(HttpServletRequest request, HttpServletResponse response,
			Long afPaymentBillId) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(afPaymentBillId == null){
			throw new Exception("数据为空");
		}
		AfPaymentBill bill = afPaymentBillService.findByPrimaryKey(afPaymentBillId);
		if(!user.getCompanyId().equals(bill.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
		List<Map<String,Object>> list = afPaymentBillService.listPaymentBillPersonBj(bill);
		page.setData(list);
		
		return page;
	}
	
}
