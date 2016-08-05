package com.jabava.controller.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrDimissionService;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.enums.SystemEnum;
/**
 * 离职管理
 *
 * @version $Id: EhrTurnoverController.java, 
 * v 0.1 2016年3月25日 上午10:46:54 
 * <pre>
 * @author steven.chen
 * @date 2016年3月25日 上午10:46:54 
 * </pre> 
 */
@Controller
@RequestMapping("employee")
public class EhrDimissionController {
	
	@Resource
	private EhrDimissionService ehrDimissionService;
	@Resource
	private IBaseDataService baseDataService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Resource
	private EhrPersonService ehrPersonService;
	@Resource
	private EhrJobpostService jobpostService;
	
	@RequestMapping("/turnover")
	public String jumpPage(Long personId,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("personId", personId);
		return "employees/turnover_management";
	}
	
	/**
	 * 离职信息
	 * @param personId
	 * @return
	 */
	@RequestMapping("/dimissionInfo")
	@ResponseBody
	public Map<String, Object> dimissionInfo(Long personId,HttpServletRequest request){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map.put("isDimission", false);
			map.put("dimission", ehrPersonService.getBasicPersonInfoByPersonId(personId));
			
			//岗位
			List<EhrBaseData> positionList =  baseDataService.selectBaseData(u.getCompanyId(),  BaseDataConstants.BASE_DATA_POSITION, null);//岗位基础数据
			
			map.put("positionList", positionList);
			
			//离职原因
			List<EhrBaseData> dimissionCauseList =  baseDataService.selectBaseData(u.getCompanyId(),  BaseDataConstants.BASE_DATA_DIMISSION_REASON, null); 
			
			map.put("dimissionCauseList", dimissionCauseList);
			
			//是否离职
			List<EhrDimission> list=ehrDimissionService.getByPersonId(personId);
			if(list!=null&&list.size()>0){
				map.put("isDimission", true);
			} 
			//任职记录开始时间-用于离职日期的判断
			EhrJobpost jobpost=jobpostService.getPreviousRecordByPersonId(personId);
			if(jobpost!=null){
				map.put("recordStartDate", jobpost.getRecordStartDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "错误信息："+e.getMessage());
		}
		
	
		return map;
	}
	/**
	 * 新增离职信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午5:15:56 
	 * </pre>
	 *
	 * @param dimission
	 * @param request
	 * @return
	 */
	@RequestMapping("addDimission")
	@ResponseBody
	public Map<String, Object> addDimission(@RequestBody  EhrDimission dimission, HttpServletRequest request){
		Map<String, Object> map = null;
		EhrUser user = RequestUtil.getLoginUser(request);
		try {		
			
			map = ehrDimissionService.addDimission(dimission, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Organization, "给id为"+dimission.getPersonId()+"的员工办理离职");
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "错误信息："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 更新离职信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午5:16:08 
	 * </pre>
	 *
	 * @param dimission
	 * @param request
	 * @return
	 */
	@RequestMapping("updateDimission")
	@ResponseBody
	public Map<String, Object> updateDimission(EhrDimission dimission, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		EhrUser user = RequestUtil.getLoginUser(request);
		try {

			dimission.setLastModifyUserId(user.getUserId());
			dimission.setLastModifyUserName(user.getUserName());
			dimission.setCreateDate(new Date());
			map = ehrDimissionService.updateDimission(dimission, user);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改离职信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除离职信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午5:16:18 
	 * </pre>
	 *
	 * @param dimissionId
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteDimission")
	@ResponseBody
	public Map<String, Object> deleteDimission(Long dimissionId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ehrDimissionService.deleteDimission(dimissionId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除离职信息失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 格式化date 类型
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午5:16:27 
	 * </pre>
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
