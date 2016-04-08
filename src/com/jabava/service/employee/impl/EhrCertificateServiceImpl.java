package com.jabava.service.employee.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.dao.employee.EhrCertificateMapper;
import com.jabava.pojo.employee.EhrCertificate;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrCertificateService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HrHelperFileUploader;
import com.service.provider.entity.CenterSysUser;

@Service("certificateService")
public class EhrCertificateServiceImpl implements EhrCertificateService {
	@Resource
	private EhrCertificateMapper certificateMapper;

	@Override
	public List<EhrCertificate> getByPersonId(Long personId) throws Exception {
		return certificateMapper.getByPersonId(personId);
	}

	@Override
	public boolean addCertificate(HttpServletRequest request,EhrCertificate certificate,EhrUser user) throws Exception {
		boolean result = false;
		try {
			certificate.setCreateDate(new Date());
			certificate.setCreateUserId(user.getUserId());
			certificate.setCreateUserName(user.getUserName());
			certificate.setLastModifyDate(new Date());
			certificate.setLastModifyUserId(user.getUserId());
			certificate.setLastModifyUserName(user.getUserName());
			int code = certificateMapper.insertSelective(certificate);
//			if(certificate.getFile() != null){
//				certificate.setCertificationCopy(getFilePath(request, certificate.getFile(), "certification_"+certificate.getTechnologyId()));
//			}
			certificate.getTechnologyId();
			certificateMapper.updateByPrimaryKeySelective(certificate);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateCertificate(HttpServletRequest request,EhrCertificate certificate,EhrUser user)
			throws Exception {
		boolean result = false;
		try {
			certificate.setLastModifyDate(new Date());
			certificate.setLastModifyUserId(user.getUserId());
			certificate.setLastModifyUserName(user.getUserName());
//			if(certificate.getCertificationCopy() != null && !"".equals(certificate.getCertificationCopy())){
//				certificate.setCertificationCopy(getFilePath(request, certificate.getFile(), "certification_"+certificate.getTechnologyId()));
//			}
//			System.out.println("llllll"+getFilePath(request, certificate.getFile(), "certification_"+certificate.getTechnologyId()));
			int code = certificateMapper.updateByPrimaryKeySelective(certificate);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delCertificate(Long technologyId) throws Exception {
		boolean result = false;
		try {
			int code = certificateMapper.deleteByPrimaryKey(technologyId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public String getFilePath(HttpServletRequest request, CommonsMultipartFile file, String filename){
		String path = request.getSession().getServletContext().getRealPath(File.separator+"person");
		String fname = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);//后缀
		File folder = new File(path);
    	if(!folder.exists()) {
    		folder.mkdirs();
    	}
    	path = path + File.separator + filename + "." + fname;
    	File img = new File(path);
    	if(img.exists()){
    		img.delete();
    	}
    	try {
			file.transferTo(img);//生成文件
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return filename + "." + fname;
	}
	
	 /**
     * 电子版证书上传
     * @param myfiles
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    public  Map uploadFiles(MultipartFile[] myfiles, HttpServletResponse response, HttpServletRequest request)throws Exception{
    	
    	 
    	String fileName="";//处理后的文件名称
		 
		String realPath=request.getSession().getServletContext().getRealPath("uploadFile")+"//";//文件上传目录
		
  		Map<String,Object> map = new HashMap<String,Object>();
		//int i=0;
		String realUrl="";
		for(MultipartFile myfile:myfiles){
			//i++;
			if (!myfile.isEmpty()) {
    			
    		    fileName = new Date().getTime()+"."+FileUtil.getSuffixName(myfile.getOriginalFilename());//处理后的文件名称
    			
    			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, fileName)); // 上传文件到项目根目录下的uploadFile
    			
    			realUrl=HrHelperFileUploader.upload(realPath+fileName);//上传文件到服务器
    			
    			if(realUrl!=null&&!realUrl.equals("")){
    				map.put("realUrl", realUrl);
    				map.put("success", "上传成功！");
    			}else{
    				map.put("error", "上传失败！");
    			}
            	 
            }else{
            	map.put("realUrl", "");
            }
			
		}
  		 
		return map;
    }
}
