package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jabava.pojo.employee.EhrAssess;
import com.jabava.pojo.employee.EhrCertificate;
import com.jabava.pojo.manage.EhrUser;

public interface EhrCertificateService {
	List<EhrCertificate> getByPersonId(Long personId) throws Exception;
	
	boolean addCertificate(HttpServletRequest request,EhrCertificate certificate,EhrUser user) throws Exception;
	
	boolean updateCertificate(HttpServletRequest request,EhrCertificate certificate,EhrUser user) throws Exception;
	
	boolean delCertificate(Long technologyId) throws Exception ;
	
	Map uploadFiles(MultipartFile[] myfiles, HttpServletResponse response, HttpServletRequest request)throws Exception;
}
