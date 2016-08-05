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

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrEducation;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrEducationService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("employee")
public class EhrEducationController {
	@Resource
	private EhrEducationService educationService;
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Resource
	private IBaseDataService baseDataService;
	
	/**
	 * 教育背景页面入口
	 * @return
	 */
	@RequestMapping("educationInfoPage")
	public String educationInfoPage(){
		 
		return "employees/educational_background";
	}
	/**
	 * 教育背景列表
	 * @param personId
	 * @return
	 */
	@RequestMapping("educationInfo")
	@ResponseBody
	public Map<String, Object> educationInfo(Long personId, HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request); 
		Map<String, Object> map = new HashMap<String, Object>();
		//学位
		List<EhrBaseData> degreeList=null;
		
		//学历 	
		List<EhrBaseData> educationList=null;
		try {
			List<EhrEducation> resultList=educationService.getByPersonId(personId);
			map.put("list", resultList);
			 
			
			degreeList = baseDataService.selectBaseData(user.getCompanyId(),BaseDataConstants.BASE_DATA_EDUCATION , null);
			
			if(degreeList!=null&&degreeList.size()>0){
				map.put("degreeList", degreeList );
			} 
			
			educationList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_DEGREE, null);
			
			if(educationList!=null&&educationList.size()>0){
				map.put("educationList", educationList );
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("addEducation")
	@ResponseBody
	public Map<String, Object> addEducation(@RequestBody EhrEducation education, HttpServletRequest request){
		Map<String, Object> map = null;	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = educationService.addEducation(education, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Organization, "给id为"+education.getPersonId()+"的员工添加学历信息");
		} catch (Exception e) {
			map = new HashMap<String, Object>();	
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("updateEducation")
	@ResponseBody
	public Map<String, Object> updateEducation(@RequestBody EhrEducation education, HttpServletRequest request){
		Map<String, Object> map = null;	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			map = educationService.updateEducation(education, user);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "修改id为"+education.getEducationId()+"的学历信息");

		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "错误信息："+e.toString());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("deleteEducation")
	@ResponseBody
	public Map<String, Object> deleteEducation(Long educationId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = educationService.deleteEducation(educationId);
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Delete, SystemEnum.Module.Organization, "删除id为"+educationId+"的学历信息");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除教育背景失败！");
			e.printStackTrace();
		}
		return map;
	}
}
