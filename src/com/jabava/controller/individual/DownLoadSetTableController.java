package com.jabava.controller.individual;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.individual.IdvRosterBatchService;
import com.jabava.utils.RequestUtil;


@Controller
@RequestMapping("individual")
public class DownLoadSetTableController {
	public static Logger log = Logger.getLogger(DownLoadSetTableController.class);
	
	@Resource
	private IdvRosterBatchService rosterBatchService;
	
	/**
	 * 进入各地BP上传界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("todownloadSetTable")
	public String mobileLogin(HttpServletRequest request,HttpServletResponse response){
		return "individual/downloadSetTable";
	}
	
	/**
	 * 进入总部上传界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tolistRosterBatch")
	public String employeeList(HttpServletRequest request,HttpServletResponse response){
		
		return "individual/listRosterBatch";
	}
	
	/**
	 * 获取公司总名单批次信息
	 * @param personId
	 * @param request
	 * @return
	 */
	@RequestMapping("/rosterBatchInfo")
	@ResponseBody
	public Map<String, Object> rosterBatchInfo(HttpServletRequest request,Long type ){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String, Object> data =null;
		Map<String, Object> params = new HashMap<>();
		try{
			
			params.put("companyId",user.getCompanyId());
			params.put("type",type);
			data= rosterBatchService.selectBatchInfoByCompanyIdAndType(params);
		
		} catch (Exception e) { 
			
			data = new HashMap<>();
			e.printStackTrace();
			data.put("errorInfo",e.toString());
	   }
		
		return data;
	}
	
	/**
	 * 各地BP名单上传
	 * @param request
	 * @param response
	 * @param rosterbatchId 总名单批次ID
	 * @param type 批次类型
	 * @param multipartFile 文件
	 * @return
	 */
	@RequestMapping("uploadBPFile")
	@ResponseBody
	public Map<String, Object> uploadSecurityFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam("rosterbatchId")Long rosterbatchId, @RequestParam("type")Integer type, 
			@RequestParam("uploadFiles")CommonsMultipartFile multipartFile ){
		
		Map<String, Object> data = null;
		
		EhrUser user = RequestUtil.getLoginUser(request);
		
		try {
			
			data=rosterBatchService.importBDFiles(multipartFile, rosterbatchId, type, user);
			
		} catch (Exception e) {
			data = new HashMap<>();
			e.printStackTrace();
			data.put("errorInfo",e.toString());
		}
		return data;
	}
	
	
	
	

}
