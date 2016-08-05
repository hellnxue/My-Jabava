package com.jabava.controller.employee;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonFileService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HrHelperFileUploader;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.JabavaEnum;

@RequestMapping("employee")
@Controller
public class EhrPersonFileController {
	@Resource
	private EhrPersonFileService personFileService;

	/**
	 * 上传文件到HRO
	 * @param uploadToHRO
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadToHRO")
	@ResponseBody
	public Map<String,Object> uploadToHRO(HttpServletResponse response,HttpServletRequest request,@RequestParam MultipartFile[] uploadFile) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		try{
			String storageUrl = FileUtil.confirmFullPath(user.getCompanyId().toString(), 
					JabavaEnum.FileClassEnum.PICTURE.getValue().toString());
			String fileName = uploadFile[0].getOriginalFilename();
			File targetFile = new File(storageUrl, fileName);
			uploadFile[0].transferTo(targetFile);
			//上传到HRO服务器，返回URL
			Map<String,Object> rtnMsgMap = new HashMap<String,Object>();
			String realPath = HrHelperFileUploader.upload(storageUrl + File.separator + fileName,rtnMsgMap);
			if(StringUtils.isEmpty(realPath)){
				if(rtnMsgMap.get("message") != null && !StringUtils.isEmpty(rtnMsgMap.get("message").toString())){
					return MessageUtil.errorMessage(rtnMsgMap.get("message").toString());
				}else{
					return MessageUtil.errorMessage("上传HRO失败");
				}
			}
			Map<String,Object> map = MessageUtil.successMessage("上传成功");
			map.put("fileUrl", realPath);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
	
	@RequestMapping("savePersonFiles")
	@ResponseBody
	public Map <String,Object> savePersonFiles(@RequestBody List <EhrPersonFile> list) throws Exception{
		HashMap <String,Object> map = new HashMap<String,Object>();
		
		if(list != null && list.size() > 0){
			personFileService.batchInsert(list);
		}
		
		map.put("status", 0);
		
		return map;
	}
	
	/**
	 * 员工个人信息-上传电子简历、  离职证明、 健康证明、 学历证明、 薪资证明
	 * @param request
	 * @param response
	 * @param personId
	 * @param resumeFile
	 * @param educationFile
	 * @param jobpostFile
	 * @param healthFile
	 * @param salaryFile
	 * @return
	 */
	@RequestMapping("exportPerson")
	@ResponseBody
	public Map<String, Object> exportPerson(HttpServletRequest request, HttpServletResponse response, @RequestParam("personId")Long personId, 
			  @RequestParam("resumeFile")CommonsMultipartFile resumeFile, 
			@RequestParam("educationFile")CommonsMultipartFile educationFile, 
			@RequestParam("jobpostFile")CommonsMultipartFile jobpostFile, @RequestParam("healthFile")CommonsMultipartFile healthFile,
			@RequestParam("salaryFile")CommonsMultipartFile salaryFile){
		Map<String, Object> map = new HashMap<>();
		EhrPerson person = new EhrPerson();
		person.setPersonId(personId);
		List<EhrPersonFile> list = new ArrayList<>();
		
		
		try {
//			//身份类型
//			if(!certTypeFile.isEmpty()){
//				String filename = personFileService.getFileName(request, certTypeFile, "shenfenUrl_"+personId);
//				EhrPersonFile file = new EhrPersonFile();
//				file.setPersonId(personId);
//				file.setFileType(1);
//				file.setFilePath(filename);
//				file.setCreateDate(new Date());
//				list.add(file);
//			}
			//简历
			if(!resumeFile.isEmpty()){
				String filename = personFileService.getFileName(request, resumeFile, "jianliUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setFileType(4);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
			//学历
			if(!educationFile.isEmpty()){
				String filename = personFileService.getFileName(request, educationFile, "xueliUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setPersonId(personId);
				file.setFileType(3);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
//			//户口薄
//			if(!residenceFile.isEmpty()){
//				String filename = personFileService.getFileName(request, residenceFile, "huokouUrl_"+personId);
//				EhrPersonFile file = new EhrPersonFile();
//				file.setPersonId(personId);
//				file.setPersonId(personId);
//				file.setFileType(2);
//				file.setFilePath(filename);
//				file.setCreateDate(new Date());
//				list.add(file);
//			}
			//离职证明
			if(!jobpostFile.isEmpty()){
				String filename = personFileService.getFileName(request, jobpostFile, "lizhiUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setPersonId(personId);
				file.setFileType(5);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
			//健康证明
			if(!healthFile.isEmpty()){
				String filename = personFileService.getFileName(request, healthFile, "jiankangUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setPersonId(personId);
				file.setFileType(6);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
			//薪资证明
			if(!salaryFile.isEmpty()){
				String filename = personFileService.getFileName(request, salaryFile, "xinziUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setPersonId(personId);
				file.setFileType(7);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
			if(!list.isEmpty()){
				personFileService.batchInsert(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 社保公积金-身份证正反面、  银行卡、 免冠照、 户口本首页、 户口本本人页
	 * @param request
	 * @param response
	 * @param personId
	 * @param resumeFile
	 * @param educationFile
	 * @param jobpostFile
	 * @param healthFile
	 * @param salaryFile
	 * @return
	 */
	@RequestMapping("uploadSecurityFiles")
	@ResponseBody
	public Map<String, Object> uploadSecurityFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam("personId")Long personId, 
			@RequestParam("uploadFiles")CommonsMultipartFile securityFile , @RequestParam("fileType")int fileType){
		Map<String, Object> map = new HashMap<>();
		EhrPerson person = new EhrPerson();
		person.setPersonId(personId);
		List<EhrPersonFile> list = new ArrayList<>();
		
		try {
			 
			if(!securityFile.isEmpty()){
				String filename = personFileService.getFileName(request, securityFile, "shenfenUrl_"+personId);
				map.put("imgUrl", filename);
				map.put("success", true);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setFileType(fileType);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				file.setFrom(1);
				list.add(file);

				if(!list.isEmpty()){
					personFileService.batchInsert(list);
				}
			}
			
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}
}
