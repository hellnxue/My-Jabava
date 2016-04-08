package com.jabava.controller.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.service.employee.EhrPersonFileService;

@RequestMapping("employee")
@Controller
public class EhrPersonFileController {
	@Resource
	private EhrPersonFileService personFileService;
	
	@RequestMapping("exportPerson")
	@ResponseBody
	public Map<String, Object> exportPerson(HttpServletRequest request, HttpServletResponse response, @RequestParam("personId")Long personId, 
			@RequestParam("certTypeFile")CommonsMultipartFile certTypeFile, @RequestParam("resumeFile")CommonsMultipartFile resumeFile, 
			@RequestParam("educationFile")CommonsMultipartFile educationFile, @RequestParam("residenceFile")CommonsMultipartFile residenceFile,
			@RequestParam("jobpostFile")CommonsMultipartFile jobpostFile, @RequestParam("healthFile")CommonsMultipartFile healthFile,
			@RequestParam("salaryFile")CommonsMultipartFile salaryFile){
		Map<String, Object> map = new HashMap<>();
		EhrPerson person = new EhrPerson();
		person.setPersonId(personId);
		List<EhrPersonFile> list = new ArrayList<>();
		try {
			//身份类型
			if(!certTypeFile.isEmpty()){
				String filename = personFileService.getFileName(request, certTypeFile, "shenfenUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setFileType(1);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
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
			//户口薄
			if(!residenceFile.isEmpty()){
				String filename = personFileService.getFileName(request, residenceFile, "huokouUrl_"+personId);
				EhrPersonFile file = new EhrPersonFile();
				file.setPersonId(personId);
				file.setPersonId(personId);
				file.setFileType(2);
				file.setFilePath(filename);
				file.setCreateDate(new Date());
				list.add(file);
			}
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
}
