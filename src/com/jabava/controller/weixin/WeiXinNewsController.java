package com.jabava.controller.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.weixin.WeiXinNews;
import com.jabava.pojo.weixin.WeiXinNewsVO;
import com.jabava.service.weixin.WeiXinNewsService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * 新闻模板 
 *
 * @version $Id: WeixinNewsController.java, 
 * v 0.1 2016年5月23日 上午10:58:01 
 * <pre>
 * @author steven.chen
 * @date 2016年5月23日 上午10:58:01 
 * </pre>
 */
@Controller
@RequestMapping("/weixinnews")
public class WeiXinNewsController {
	
	@Autowired
	private WeiXinNewsService weiXinNewsService;
	
	
	@RequestMapping("weixinnewsMain")
	public String weixinnewsMain(){
		return "/newsManagement/newsManagement";
	}
	@RequestMapping("/findNewsPage")
	@ResponseBody
	public Page<WeiXinNewsVO> findNewsPage(HttpServletRequest request,String date,String type,int start,int length){
		
		Page<WeiXinNewsVO> page = new Page<WeiXinNewsVO>(start, length);
		Map<String, Object> map =  new HashMap<String, Object>();		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		map.put("companyId", ehrUser.getCompanyId());	
		map.put("page", page);
		List<WeiXinNewsVO> newsList =weiXinNewsService.findNewsPage(map);		
		page.setData(newsList);
		return page;
	}
	
	@RequestMapping("/loadTree")
	@ResponseBody
	public Map<String,List<EhrOrganization	>> loadTree(HttpServletRequest request, HttpServletResponse response){	
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,List<EhrOrganization>> result = new HashMap<String,List<EhrOrganization>>();
		result.put("data", weiXinNewsService.loadTree(user.getCompanyId()));
		return result;
	}
	@RequestMapping("/sendNew")
	@ResponseBody
	public String sendNew(HttpServletRequest request,WeiXinNewsVO weiXinNewsVO){
		
		return weiXinNewsService.sendNews(weiXinNewsVO);
		
	}		
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam CommonsMultipartFile myfiles,HttpServletResponse response,HttpServletRequest request) throws Exception{	
		return weiXinNewsService.uploadFile(myfiles, request);
	}
	@RequestMapping("/addNews")
	@ResponseBody
	public String addNews(HttpServletRequest request,WeiXinNews weiXinNews){
		EhrUser user = RequestUtil.getLoginUser(request);				
		weiXinNews.setCreateDate(new Date());
		weiXinNews.setCreateUserId(user.getUserId());	
		weiXinNews.setCompanyId(user.getCompanyId());
		if(weiXinNewsService.insertSelective(weiXinNews)==1){
			return "success";
		}else{
			return "新增失败!";
		}
		
	}
	@RequestMapping("toWeiXinnewsUpdate")
	public String toWeixinnewsUpdate(Model model,Long newsId){
		model.addAttribute("newsId", newsId);
		return "/newsManagement/newDetail";
	}
	@RequestMapping("/newsDetail")
	@ResponseBody
	public WeiXinNews newsDetail(Long newsId){
		return weiXinNewsService.selectByPrimaryKey(newsId);
	}
	
	@RequestMapping("/updateNews")
	@ResponseBody
	public String updateNews(HttpServletRequest request,WeiXinNews weiXinNews){
		EhrUser user = RequestUtil.getLoginUser(request);				
		weiXinNews.setUpdateDate(new Date());
		weiXinNews.setUpdateUserId(user.getUserId());	
		weiXinNews.setCompanyId(user.getCompanyId());
		if(weiXinNewsService.updateByPrimaryKeySelective(weiXinNews)==1){
			return "success";
		}else{
			return "更新失败!";
		}
	}
	@RequestMapping("/delNews")
	@ResponseBody
	public String delNews(WeiXinNews record,HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		record.setUpdateDate(new Date());
		record.setUpdateUserId(user.getUserId());
		record.setCompanyId(user.getCompanyId());
		if(weiXinNewsService.deleteByPrimaryKey(record)==1){
			return "success";
		}else{
			return "删除失败!";
		}
	}
	
}
