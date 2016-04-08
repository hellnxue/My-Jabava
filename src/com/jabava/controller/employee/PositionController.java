package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("position")
public class PositionController {
	
	@Resource
	private EhrPositionService ehrPositionService;
	
	/**
	 * 岗位信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月17日 上午10:45:14 
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("positionInformation")
	public String positionInformation(HttpServletRequest request,Long personId){
		request.setAttribute("personId", personId);
		return "employees/position_information";
	}
	/**
	 * 根据员工ID获取岗位信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月18日 下午2:39:55 
	 * </pre>
	 *
	 * @param personId
	 * @return
	 */
	@RequestMapping("getEhrPositionByPersonId")
	@ResponseBody
	public Map<String, Object> getEhrPositionByPersonId(Long personId){
		Map<String, Object> map = new HashMap<>();
		EhrPositionVO ehrPositionVO =  ehrPositionService.getEhrPositionByPersonId(personId);
		map.put("ehrPositionVO", ehrPositionVO);
		return map;
	}
	/**
	 * 新增岗位信息  如果该已经存在则更新
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月17日 下午5:43:05 
	 * </pre>
	 *
	 * @param ehrPosition
	 * @return
	 */
	@RequestMapping("addPosition")
	@ResponseBody
	public String addPosition(HttpServletRequest request,EhrPosition ehrPosition,Long companyId,String employeeName){
		EhrUser u = RequestUtil.getLoginUser(request);
		Date date  = new Date();
		ehrPosition.setCreateDate(date);
		ehrPosition.setCreateUserId(u.getUserId());
		ehrPosition.setUpdateDate(date);
		ehrPosition.setUpdateUserId(u.getUserId());
		return ehrPositionService.addPositin(ehrPosition,null,employeeName);
	}
	/**
	 * 更新岗位信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月17日 下午6:08:17 
	 * </pre>
	 *
	 * @param ehrPosition
	 * @return
	 */
	@RequestMapping("updatePosition")
	@ResponseBody
	public String updatePosition(EhrPosition ehrPosition){
		return ehrPositionService.updatePosition(ehrPosition);
	}

}
