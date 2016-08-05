package com.jabava.controller.common;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.common.IFileService;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.enums.JabavaEnum;
import com.jabava.utils.security.MD5;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HrHelperFileUploader;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.RequestUtil;

@Controller
@RequestMapping("/common")
public class FileController {
	private static Logger log = Logger.getLogger(FileController.class);
	
	@Autowired
	private IFileService fileService;

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
	public Map<String,Object> uploadFile(HttpServletResponse response,HttpServletRequest request,@RequestParam MultipartFile[] uploadFile) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		try {
			//原文件名
			String fileName = uploadFile[0].getOriginalFilename();
			//目标文件名
			String newFileName = FileUtil.generateFileName(ehrUser.getUserCode(), fileName);
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
			
			String targetFile = new StringBuffer(filePath).append(File.separator).append(newFileName).toString();
			String fid = request.getParameter("fid");
			
			//目标文件相对全名
			map.put("filePath", targetFile);
			map.put("originalFilename", fileName);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}

	/**
	 * 上传文件
	 * @param uploadAndSaveFile
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadAndSaveFile")
	@ResponseBody
	public Map<String,Object> uploadAndSaveFile(HttpServletResponse response,HttpServletRequest request,@RequestParam MultipartFile[] uploadFile) throws Exception{
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		String fileClass = request.getParameter("fileClass");
		if(StringUtils.isEmpty(fileClass)){
			fileClass = JabavaEnum.FileClassEnum.NOCLASS.getValue().toString();
		}
		
		try {
			//原文件名
			String fileName = uploadFile[0].getOriginalFilename();
			//目标文件名
			String newFileName = FileUtil.generateFileName(ehrUser.getUserCode(), fileName);
			//绝对目录(UPLOAD_PATH+companyId+fileClass)
			String fullPath = FileUtil.confirmFullPath(ehrUser.getCompanyId().toString(), fileClass);
			
			FileUtils.copyInputStreamToFile(uploadFile[0].getInputStream(), new File(fullPath, newFileName));
			
			String fileId = request.getParameter("fileId");
			EhrFile ehrFile = null;
			if(StringUtils.isEmpty(fileId)){
				ehrFile = new EhrFile();
				ehrFile.setFileClass(Integer.valueOf(fileClass));
			}else{
				ehrFile = fileService.getFileById(ehrUser.getCompanyId(), Long.valueOf(fileId));
			}
			ehrFile.setFileName(fileName);
			ehrFile.setFilePath(new StringBuffer(fullPath).append(File.separator).append(newFileName).toString());
			fileService.saveOrUpdate(ehrUser, ehrFile);
			
			Map<String,Object> map = MessageUtil.successMessage("上传成功");
			map.put("fileId", ehrFile.getFileId());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("downloadFile")
	@ResponseBody
	public Map<String,Object> downloadFile(HttpServletResponse response,HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		String fileId = request.getParameter("fileId");
		if(StringUtils.isEmpty(fileId)){
			return MessageUtil.errorMessage("文件不存在");
		}
		EhrFile file = fileService.getFileById(user.getCompanyId(), Long.valueOf(fileId));
		try {
			String fileName = file.getFileName();
			if(FileUtil.downLoadFile(file.getFilePath(), response, fileName, 
					fileName.lastIndexOf(".") == -1 ? "xlsx" : fileName.substring(0, fileName.lastIndexOf(".")))){
				return MessageUtil.successMessage("文件下载成功");
			}else{
				return MessageUtil.errorMessage("文件下载失败 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageUtil.errorMessage("文件下载出失败");
	}
}
