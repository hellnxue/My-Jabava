package com.jabava.controller.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrAssess;
import com.jabava.pojo.employee.EhrCertificate;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrCertificateService;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("employee")
public class EhrCertificateController {
	@Resource
	private EhrCertificateService certificateService;
	
	/**
	 * 获得证书页面入口
	 * @return
	 */
	@RequestMapping("certificateInfoPage")
	public String certificateInfoPage(){
		 
		return "employees/obtain_certificate";
	}
	/**
	 * 获得证书
	 * @param personId
	 * @return
	 */
	@RequestMapping("/certificateInfo")
	@ResponseBody
	public Map<String, Object> getCertificate(Long personId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("list", certificateService.getByPersonId(personId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
     * 电子版证书上传
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/certificateUploadFile")
	@ResponseBody
	public Map uploadFile(@RequestParam MultipartFile[] myfiles,HttpServletResponse response,HttpServletRequest request) throws Exception{
    	
    	Map result=certificateService.uploadFiles(myfiles, response, request);
     
		return result;
	}
	/**
	 * 添加证书
	 * @param certificate
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addCertificate")
	@ResponseBody
	public Map<String, Object> addCertificate(HttpServletRequest request, HttpServletResponse response,@RequestBody EhrCertificate certificate ){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			
			boolean result = certificateService.addCertificate(request,certificate,u);
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
	 * 修改证书
	 * @param certificate
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateCertificate")
	@ResponseBody
	public Map<String, Object> updateCertificate(@RequestBody EhrCertificate certificate,HttpServletRequest request, HttpServletResponse response){	
		EhrUser u = RequestUtil.getLoginUser(request);
		Map<String, Object> data = new HashMap<>();
		try {
			certificate.setLastModifyDate(new Date());
			certificate.setLastModifyUserId(u.getUserId());
			certificate.setLastModifyUserName(u.getUserName());
			boolean result = certificateService.updateCertificate(request,certificate,u);
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
	 * 删除证书
	 * @param certificateId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delCertificate")
	@ResponseBody
	public Map<String, Object> delCertificate(Long technologyId, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			boolean result =certificateService.delCertificate(technologyId);
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
}
