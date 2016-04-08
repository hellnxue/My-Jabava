package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrContractService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.utils.RequestUtil;
/**
 * 劳动合同
 *
 * @version $Id: EhrWorkContractController.java, 
 * v 0.1 2016年3月23日 下午5:04:54 
 * <pre>
 * @author steven.chen
 * @date 2016年3月23日 下午5:04:54 
 * </pre>
 */
@Controller
@RequestMapping("employee")
public class EhrWorkContractController { 
	@Resource
	private EhrContractService contractService;
	
	@Resource
	private IBaseDataService baseDataService;
	@Resource
	private IEhrOrganizationService  iEhrOrganizationService;

	/**
	 * 显示劳动合同
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 下午4:49:37 
	 * </pre>
	 *
	 * @param personId
	 * @param request
	 * @return
	 */
	@RequestMapping("/contractInfo")
	@ResponseBody
	public Map<String, Object> getContract(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map.put("subjectList", iEhrOrganizationService.findOrganizationByCompanyId(user.getCompanyId()));
			map.put("list", contractService.getByPersonId(personId));
			map.put("type", baseDataService.selectBaseData(user.getCompanyId(), 10, null));//合同类别
			map.put("subject", baseDataService.selectBaseData(user.getCompanyId(), 8, null));//合同主体
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return map;
	}
	/***
	 * 获取当前所在公司的主体
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 下午5:11:04 
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findOrganizationByCompanyId")
	@ResponseBody
	public List<EhrOrganization> findOrganizationByCompanyId(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		return iEhrOrganizationService.findOrganizationByCompanyId(user.getCompanyId());
	}
	/**
	 * 修改合同falg(意见征询-完成按钮)
	 * @param contractId
	 * @return
	 */
	@RequestMapping("/updateFalg")
	@ResponseBody
	public Map<String, Object> updateFalg(Long contractId){
		 Map<String, Object> map = new HashMap<>();
		 boolean result = false;
		 try {
			 result = contractService.updateFalg(contractId,1);
			 if(result){
					map.put("success", result);
					map.put("msg", "成功");
				}else{
					map.put("success", result);
					map.put("msg", "失败");
				}
		} catch (Exception e) {
		} 
		return map;
	}
	/**
	 * 添加劳动合同
	 * @param contract
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addContract")
	@ResponseBody
	public Map<String, Object> addContract(EhrContract contract, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			contract.setCreateDate(new Date());
			contract.setCreateUserId(u.getUserId());
			contract.setCreateUserName(u.getUserName());
			contract.setLastModifyDate(new Date());
			contract.setLastModifyUserId(u.getUserId());
			contract.setLastModifyUserName(u.getUserName());
			boolean result = contractService.addContract(contract);
			if(result){
				data.put("success", result);
		        data.put("msg", "添加成功");
			}else{
				data.put("success", result);
				data.put("msg", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	
	}
	
	/***
	 * 修改劳动合同
	 * @param contract
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateContract")
	@ResponseBody
	public Map<String, Object> updateContract(EhrContract contract, HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			contract.setLastModifyDate(new Date());
			contract.setLastModifyUserId(u.getUserId());
			contract.setLastModifyUserName(u.getUserName());
			boolean result = contractService.updateContract(contract);
			if(result){
				data.put("success", result);
		        data.put("msg", "修改成功");
			}else{
				data.put("success", result);
				data.put("msg", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	
	}
	/**
	 * 删除劳动合同
	 * @param contractId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delContract")
	@ResponseBody
	public Map<String, Object> delContract(Long contractId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =contractService.delContract(contractId);
			if(result){
				data.put("success", result);
		        data.put("msg", "删除成功");
			}else{
				data.put("success", result);
				data.put("msg", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	 }
}
