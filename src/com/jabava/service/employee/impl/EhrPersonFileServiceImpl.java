package com.jabava.service.employee.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.dao.employee.EhrPersonFileMapper;
import com.jabava.pojo.employee.EhrPersonFile;
import com.jabava.service.employee.EhrPersonFileService;
import com.jabava.utils.HrHelperFileUploader;

@Service("personFileService")
public class EhrPersonFileServiceImpl implements EhrPersonFileService {
	@Resource
	private EhrPersonFileMapper fileMapper;

	@Override
	public List<EhrPersonFile> searchFile(Long personId ) {
		
		 return fileMapper.searchFile(personId );
	}
	
	public void batchInsert(List<EhrPersonFile> list) throws Exception{
 		fileMapper.batchDelete(list);//先逻辑删除，再添加
 		fileMapper.batchInsert(list);
	}
	
	/**
	 * 个人信息7个图片上传
	 */
	@Override
	public String getFileName(HttpServletRequest request, CommonsMultipartFile file, String fileName){
		String path = request.getSession().getServletContext().getRealPath(File.separator+"person");
		String fname = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);//后缀
		File folder = new File(path);
    	if(!folder.exists()) {
    		folder.mkdirs();
    	}
    	path = path + File.separator + fileName + "." + fname;
    	File img = new File(path);
    	if(img.exists()){
    		img.delete();
    	}
    	String realUrl = null;
    	try {
			file.transferTo(img);//生成文件
			realUrl = HrHelperFileUploader.upload(path);
			img.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return realUrl;
	}
}
