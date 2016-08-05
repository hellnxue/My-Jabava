package com.jabava.controller.hro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

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
	private IEhrSysLogSercice sysLogService;
	@Resource
	private OutsourcingService outsourcingService;
	
	/** 参保方案 */
	@RequestMapping("toServiceOpen")
	public String toServiceOpen(HttpServletRequest request, HroPactInfo hroPactInfo){
		request.setAttribute("hroPactInfo", hroPactInfo);
		return "client/os_securityplan";
	}
	
	/** 申请开通 */
	@RequestMapping("toApplyOpen")
	public String toApplyOpen(HttpServletRequest request, HroPactInfo hroPactInfo){
		request.setAttribute("hroPactInfo", hroPactInfo);
		
		//可以在此查询企业认证状态
		
		return "client/os_applyopen";
	}
	
	@RequestMapping("toListProtocol")
	public String toListProtocol(){
		return "client/os_bill";
	}
	
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
	@RequestMapping("/getCompanyName")
	@ResponseBody
	public Map<String,String> getCompanyName(HttpServletRequest request){	
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
//	public Page<HroPactInfo> queryProtocol(HttpServletRequest request,HroPactInfo hroPactInfo,int start,int length){
	public Page<HroPactInfo> queryProtocol(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		
//		EhrUser ehrUser = RequestUtil.getLoginUser(request);
//		String search = request.getParameter("search[value]");// search框的值
////		if(StringUtils.isNotBlank(search)){
////			hroPactInfo.setPactCode(search);
////		}
//		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
//	//	hroPactInfo.setState(ProtocolStatus.NOT_OPEN.getValue());
//		hroPactInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
//		Page<HroPactInfo> page = new Page<HroPactInfo>(start, length);		
//		String order = request.getParameter("order[0][column]");//排序列的下标
//		if(order == null || "".equals(order) || "0".equals(order)){
//			order = "ID DESC";
//		}else{
//			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
//			String according = request.getParameter("order[0][dir]");//升序或倒序
//			order = getColumnName(order) + " " + according;
//		}
//		
//		List<HroPactInfo> list = outsourcingService.queryProtocolPage(hroPactInfo, search, order, page) ;
		List<HroPactInfo> list = outsourcingService.queryProtocolByCompany(user.getCompanyId());
		Page<HroPactInfo> page = new Page<HroPactInfo>(0, Constants.MAX_RECORD_SIZE);
		page.setData(list);
		return page;
	}
	
	@RequestMapping("/addOutsourcing")
	@ResponseBody
	public Map<String,Object> addOutsourcing(HttpServletRequest request,HroPactInfo hroPactInfo){
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			Map<String,Object> map = outsourcingService.addOutsourcing(hroPactInfo, user);
			sysLogService.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Supplier, "增加一条"+hroPactInfo.getPactName()+"服务单");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/updateOutsourcing")
	@ResponseBody
	public Map<String,Object> updateOutsourcing(HttpServletRequest request,HroPactInfo hroPactInfo){
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			HroPactInfo exist = outsourcingService.getProtocolById(hroPactInfo.getId());
			if(exist == null){
				return MessageUtil.errorMessage("协议号不存在");
			}
			if(!user.getCompany().getCompanyId().equals(exist.getCompanyId())){
				return MessageUtil.errorMessage("没有数据操作权限");
			}
			Map<String,Object> map = outsourcingService.updateOutsourcing(hroPactInfo, exist, user);
			sysLogService.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Supplier, "修改id为"+hroPactInfo.getId()+"的服务单");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/submitOutsourcing")
	@ResponseBody
	public Map<String,Object> submitOutsourcing(HttpServletRequest request,Long id){
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			HroPactInfo pactInfo = outsourcingService.getProtocolById(id);
			if(pactInfo == null){
				return MessageUtil.errorMessage("协议号不存在");
			}
			if(!user.getCompany().getCompanyId().equals(pactInfo.getCompanyId())){
				return MessageUtil.errorMessage("没有数据操作权限");
			}
			
			Map<String,Object> result = outsourcingService.submitOutsourcing(pactInfo, user);
			//信息是否已经完善
			result.put("isPerfected", user.getCompany().getPerfectDate() == null ? false : true);
			sysLogService.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Supplier, "提交id为"+id+"的服务单");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/addAndSubmitOutsourcing")
	@ResponseBody
	public Map<String,Object> addAndSubmitOutsourcing(HttpServletRequest request,HroPactInfo hroPactInfo){
		EhrUser user = RequestUtil.getLoginUser(request);
		
//		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
//		hroPactInfo.setState(ProtocolStatus.NOT_OPEN.getValue());
//		hroPactInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
//		Map<String,Object> result = new HashMap<String,Object>();
//		if("success".equalsIgnoreCase(outsourcingService.addAndSubmitOutsourcing(hroPactInfo,
//				ehrUser.getCompany().getCompanyName()))){
//			result.put("success", true);
//			//信息是否已经完善
//			result.put("isPerfected", ehrUser.getCompany().getPerfectDate() == null ? false : true);
//		}else{
//			result.put("success", false);
//		}
		
		try {
			Map<String,Object> result = outsourcingService.addAndSubmitOutsourcing(hroPactInfo, user);
			//信息是否已经完善
			result.put("isPerfected", user.getCompany().getPerfectDate() == null ? false : true);
			sysLogService.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Supplier, "保存提交一条id为"+hroPactInfo.getId()+"的服务单");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("/deleteOutsourcing")
	@ResponseBody
	public Map<String,Object> deleteOutsourcing(HttpServletRequest request,Long id){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(id == null){
			return MessageUtil.errorMessage("请求数据为空");
		}
		try {
			HroPactInfo pactInfo = outsourcingService.getProtocolById(id);
			if(pactInfo == null){
				return MessageUtil.errorMessage("协议号不存在");
			}
			if(!user.getCompany().getCompanyId().equals(pactInfo.getCompanyId())){
				return MessageUtil.errorMessage("没有数据操作权限");
			}
			
			Map<String,Object> map = outsourcingService.deleteProtocol(pactInfo);
			sysLogService.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Supplier, "删除id为"+id+"的服务单");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
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
		HroPactInfo hroPactInfo = outsourcingService.getProtocolById(id);
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
