package com.jabava.service.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.employee.EhrPersonFile;

public interface EhrPersonFileService {
	String getFileName(HttpServletRequest request, CommonsMultipartFile file, String fileName) throws Exception;
	
	List<EhrPersonFile> searchFile(Long personId );
	
	void batchInsert(List<EhrPersonFile> list) throws Exception;
}
