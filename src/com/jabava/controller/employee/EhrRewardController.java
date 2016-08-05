package com.jabava.controller.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.jabava.pojo.employee.EhrRewards;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrRewardService;
import com.jabava.utils.RequestUtil;
/**
 * 惩奖记录
 *
 * @version $Id: EhrRewardController.java, 
 * v 0.1 2016年3月24日 下午3:16:15 
 * <pre>
 * @author steven.chen
 * @date 2016年3月24日 下午3:16:15 
 * </pre>
 */
@Controller
@RequestMapping("employee")
public class EhrRewardController {
	@Resource
	private EhrRewardService rewardService;
	
	/**
	 * 奖惩记录
	 * @param personId
	 * @return
	 */
	@RequestMapping("/getReward")
	@ResponseBody
	public Map<String, Object> getReward(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", rewardService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加奖惩记录
	 * @param rewards
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addRewards")
	@ResponseBody
	public Map<String, Object> addRewards(EhrRewards rewards, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			rewards.setCreateDate(new Date());
			rewards.setCreateUserId(u.getUserId());
			rewards.setCreateUserName(u.getUserName());
			rewards.setLastModifyDate(new Date());
			rewards.setLastModifyUserId(u.getUserId());
			rewards.setLastModifyUserName(u.getUserName());
			boolean result = rewardService.addRewards(rewards);
			if(result){
				data.put("success", result);
		        data.put("msg", "添加成功");
			}else{
				data.put("success", result);
				data.put("msg", "添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	/**
	 * 修改奖惩记录
	 * @param rewards
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateRewards")
	@ResponseBody
	public Map<String, Object> updateRewards(EhrRewards rewards, HttpServletRequest request, HttpServletResponse response){
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			rewards.setLastModifyDate(new Date());
			rewards.setLastModifyUserId(u.getUserId());
			rewards.setLastModifyUserName(u.getUserName());
			boolean result = rewardService.updateRewards(rewards);
			if(result){
				data.put("success", result);
		        data.put("msg", "修改成功");
			}else{
				data.put("success", result);
				data.put("msg", "修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	/**
	 * 删除奖惩记录
	 * @param rewardId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delRewards")
	@ResponseBody
	public Map<String, Object> delRewards(Long rewardId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result = rewardService.delRewards(rewardId);
			if(result){
				data.put("success", result);
		        data.put("msg", "删除成功");
			}else{
				data.put("success", result);
				data.put("msg", "删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
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
