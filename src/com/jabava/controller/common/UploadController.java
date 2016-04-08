package com.jabava.controller.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.security.MD5;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	/**
	 * 上传文件
	 * @param uploadFile
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public Map<String,Object> uploadFile(HttpServletResponse response,HttpServletRequest request,
			@RequestParam MultipartFile[] uploadFile) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		//原文件名
		String fileName = uploadFile[0].getOriginalFilename();
		//目标文件名
		String newFileName = MD5.getMD5Code(ehrUser.getUserCode() + System.currentTimeMillis() + fileName);
		//相对目录
		String filePath = new StringBuffer(File.separator).append("temp").toString();
		//绝对目录
		String fullPath = JabavaPropertyCofigurer.getProperty("UPLOAD_PATH") + filePath;
		File file  = new File(fullPath);
		if (!file.exists()) {	//不存在则创建该目录
			file.mkdirs();
		}
		
		FileUtils.copyInputStreamToFile(uploadFile[0].getInputStream(), new File(fullPath, newFileName));
		
		Map<String,Object> map = MessageUtil.successMessage("上传成功");
		//目标文件相对全名
		map.put("filePath", new StringBuffer(filePath).append(File.separator).append(newFileName).toString());
		map.put("originalFilename", fileName);
		
		return map;
	}
	
	public static void main(String[] args){
		System.out.println(System.currentTimeMillis());
	}
}
