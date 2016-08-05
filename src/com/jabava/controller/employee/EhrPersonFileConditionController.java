package com.jabava.controller.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrPersonFileCondition;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonFileConditionService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("employee")
public class EhrPersonFileConditionController {
	@Resource
	private EhrPersonFileConditionService fileConditionService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Resource
	private IBaseDataService baseDataService;
	
	/**
	 * 资料情况页面入口
	 * @return
	 */
	@RequestMapping("ehrPersonFileConditionPage")
	public String ehrPersonFileConditionPage(){
		 
		return "employees/portfolio";
	}
	
	
	
	/**
	 * 资料情况列表
	 * @param personId
	 * @return
	 */
	@RequestMapping("fileConditionInfo")
	@ResponseBody
	public Map<String, Object> educationInfo(Long personId, HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request); 
		Map<String, Object> map = new HashMap<String, Object>();
		List<EhrBaseData> fileStatusList=null;
		try {
			//资料状态
			fileStatusList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_FILE_CONDITION, null);
			
			if(fileStatusList!=null&&fileStatusList.size()>0){
				map.put("fileStatusList", fileStatusList );
			} 
			
			List<EhrPersonFileCondition> fileConditionList=fileConditionService.getFileConditionByPersonId(personId);
			map.put("fileConditionList", fileConditionList );
			map.put("success", true);
			
			
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "错误信息："+e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("addFileCondition")
	@ResponseBody
	public Map<String, Object> addFileCondition(@RequestBody EhrPersonFileCondition fileCondition, HttpServletRequest request){
		Map<String, Object> map = null;	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = fileConditionService.insertSelective(fileCondition, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Organization, "给id为"+fileCondition.getPersonId()+"的员工添加资料情况");
		} catch (Exception e) {
			map = new HashMap<String, Object>();	
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
	
	
	@RequestMapping("updateFileCondition")
	@ResponseBody
	public Map<String, Object> updateEducation(@RequestBody EhrPersonFileCondition fileCondition, HttpServletRequest request){
		Map<String, Object> map = null;	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = fileConditionService.updateByPrimaryKeySelective(fileCondition, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "修改id为"+fileCondition.getFileConditionId()+"的资料情况");

		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
}
