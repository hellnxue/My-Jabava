package com.jabava.controller.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.employee.EhrJobpostVO;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.RequestUtil;
/**
 * 岗位调动
 *
 * @version $Id: EhrJobpostController.java, 
 * v 0.1 2016年2月22日 下午4:44:49 
 * <pre>
 * @author steven.chen
 * @date 2016年2月22日 下午4:44:49 
 * </pre>
 */
@Controller
@RequestMapping("employee")
public class EhrJobpostController {
	@Resource
	private EhrJobpostService jobpostService;
	
	@Resource
	private IBaseDataService baseDataService;
	@Resource
	public EhrPersonService ehrPersonService;
	
	@Resource
	private EhrPositionService ehrPositionService;
	
	
	@RequestMapping("jobpost")
	public String jumpPage(Long personId,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("personId", personId);
		return "employees/job_transfer";
	}
	/**
	 * 岗位调动
	 * @param personId
	 * @return
	 */
	@RequestMapping("jobpostInfo")
	@ResponseBody
	public Map<String, Object> getJobpost(Long personId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		EhrPerson user;	
		try {
			user = ehrPersonService.getByPersonId(personId);
			List<EhrJobpostVO> list = jobpostService.getByPersonId(personId);
			List<EhrPositionVO> positionList = new ArrayList<EhrPositionVO>();
			EhrPositionVO  ehrPositionVO= ehrPositionService.getEhrPositionByPersonId(personId);
			if(ehrPositionVO!=null ){
				positionList.add(ehrPositionVO);
				map.put("ehrPositionList", positionList);
			}
			map.put("list", list);
			map.put("post", baseDataService.selectBaseData(user.getCompanyId(), 1, null));//岗位
			map.put("rank", baseDataService.selectBaseData(user.getCompanyId(), 3, null));//职级
			map.put("cost", baseDataService.selectBaseData(user.getCompanyId(), 2, null));//成本中心
			map.put("city", baseDataService.selectBaseData(user.getCompanyId(), 5, null));//城市
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 添加岗位调动
	 * @param post
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addJobpost")
	@ResponseBody
	public Map<String, Object> addJobpost(EhrJobpost post, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			post.setCreateDate(new Date());
			post.setCreateUserId(u.getUserId());
			post.setCreateUserName(u.getUserName());
			post.setLastModifyDate(new Date());
			post.setLastModifyUserId(u.getUserId());
			post.setLastModifyUserName(u.getUserName());
			boolean result = jobpostService.addJobpost(post);
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
	 * 修改岗位调动
	 * @param post
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateJobpost")
	@ResponseBody
	public Map<String, Object> updateJobpost(EhrJobpost post, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			post.setLastModifyDate(new Date());
			post.setLastModifyUserId(u.getUserId());
			post.setLastModifyUserName(u.getUserName());
			boolean result = jobpostService.updateJobpost(post);
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
	
	/***
	 * 删除岗位调动
	 * @param postId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("delJobpost")
	@ResponseBody
	public Map<String, Object> delJobpost(Long postId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =jobpostService.delJobpost(postId);
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
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
