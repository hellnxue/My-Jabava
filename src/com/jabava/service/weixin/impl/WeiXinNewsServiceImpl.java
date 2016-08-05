package com.jabava.service.weixin.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.dao.weixin.WeiXinNewsMapper;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.weixin.WeiXinNews;
import com.jabava.pojo.weixin.WeiXinNewsVO;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.weixin.WeiXinNewsService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HrHelperFileUploader;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.Sftp;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;
import com.service.provider.entity.ReturnS;

/**
 * 新闻模板ServiceImpl
 *
 * @version $Id: WeiXinNewsServiceImpl.java, 
 * v 0.1 2016年5月23日 上午10:54:58 
 * <pre>
 * @author steven.chen 
 * @date 2016年5月23日 上午10:54:58 
 * </pre>
 */
@Service
public class WeiXinNewsServiceImpl implements WeiXinNewsService {

	@Resource
	private WeiXinNewsMapper  weiXinNewsMapper;	
	@Autowired
	private EhrPersonService ehrPersonService;
	@Resource
	public IEhrOrganizationService ehrOrganizationService;
	@Autowired
	private com.service.provider.WeiXinNewsService weiXinNewsService;//微信员工帮手提供的接口
	
	private static final Logger logger = Logger.getLogger(WeiXinNewsServiceImpl.class);
	//上传到sftp的微信的路径 
	private static final String sftpWeixinPath = JabavaPropertyCofigurer.getProperty("sftp_weixin_path");	
	private static final String sftpWeixinUrl ="http://static.ezhiyang.com/www/weixin";
	
	@Override
	public List<WeiXinNewsVO> findNewsPage(Map<String, Object> map) {
		return weiXinNewsMapper.findNewsPage(map);
	}
	@Override
	public List<EhrOrganization> loadTree(Long companyId){
		List<EhrOrganization>  treeList = new ArrayList<EhrOrganization>();
		List<EhrOrganization>  orgList = ehrOrganizationService.loadTree(companyId);
		if (orgList != null && orgList.size() > 0) {
			for (EhrOrganization org : orgList) {
				org.setOrganizationId(org.getOrganizationId());
				treeList.add(org);
				List<EhrPerson> personList = ehrOrganizationService
						.findPersonByOrganizationId(org.getOrganizationId());
				if (personList != null && personList.size() > 0) {
					for (EhrPerson person : personList) {
						EhrOrganization organization = new EhrOrganization();
						organization.setParentId(org.getOrganizationId());						
						organization.setPersonId(person.getPersonId());
						organization.setPersonName(person.getEmployeeName());
						treeList.add(organization);
					}
				}

			}
		}
		
		
		return treeList;
	}
	
	@Override
	public int deleteByPrimaryKey(WeiXinNews record) {
		String fileName= getFileName(record.getNewsId());//文件名
		record.setNewsId(record.getNewsId());
		String url= createHtml("该资讯已经删除",record.getCompanyId().toString(),fileName);
		if(url!=null){
			record.setImageUrl(url);
			record.setIsDeleted(IsDeleted.DELETED.getValue());
			return weiXinNewsMapper.updateByPrimaryKeySelective(record);
		}else{
			return 0;
		}
	
	}

	@Override
	public int insertSelective(WeiXinNews record) {
	
		//生成文件链接
		String url= createHtml(record.getNewsBody(),record.getCompanyId().toString(),null);
		if(url!=null){
			record.setImageUrl(url);
			//插入表信息
			return weiXinNewsMapper.insertSelective(record);
		}else{
			return 0;
		}
		
		
	}

	@Override
	public WeiXinNews selectByPrimaryKey(Long newsId) {
		return weiXinNewsMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public int updateByPrimaryKeySelective(WeiXinNews	 record) {
		
        String fileName= getFileName(record.getNewsId());//文件名
		//生成文件链接
		String url= createHtml(record.getNewsBody(),record.getCompanyId().toString(),fileName);
		if(url!=null){
			record.setImageUrl(url);
			return weiXinNewsMapper.updateByPrimaryKeySelective(record);
		}else{
			return 0;
		}
		
		
	}
	private String getFileName(Long newsId){
		WeiXinNews weiXinNews=weiXinNewsMapper.selectByPrimaryKey(newsId);
		 String str[] =weiXinNews.getImageUrl().split("/");
        return  str[str.length-1];
	}
	@Override
	public String sendNews(WeiXinNewsVO weiXinNewsVO) {
		//获取内容
		 WeiXinNews weiXinNews= weiXinNewsMapper.selectByPrimaryKey(weiXinNewsVO.getNewsId());
		List<Long> list = new ArrayList<Long>();
		if(weiXinNewsVO.getOrgId()!=null && weiXinNewsVO.getOrgId().size()>0){
			for(Long orgId :weiXinNewsVO.getOrgId()){
				//部门下的人员
				List<EhrPerson> perList = ehrOrganizationService.selectPersonByOrganization(orgId);
				if(perList!=null && perList.size()>0){
				
						if(weiXinNewsVO.getPersonId()!=null && weiXinNewsVO.getPersonId().size()>0){
							for(EhrPerson person:perList){
								//不在PersonId 里面才添加，避免重复
								if(!weiXinNewsVO.getPersonId().contains(person.getPersonId())){
									list.add(person.getPersonId());
								}
								
							}
							
						}else{
							for(EhrPerson person:perList){
								list.add(person.getPersonId());
							}
						}
					
				}
			}
		}		
		if(weiXinNewsVO.getPersonId()!=null && weiXinNewsVO.getPersonId().size()>0){
			list.addAll(weiXinNewsVO.getPersonId());
		}
		List<EhrPerson> personList = ehrPersonService.findPersonByIdList(list);
	  //发送消息模板  
		for(EhrPerson person:personList){
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("userName", person.getMobile());
			map.put("appType", "weixinPerson");
			map.put("newsTitle", weiXinNews.getNewsTitle());
			map.put("contentType", weiXinNews.getImageUrl());
			ReturnS res= weiXinNewsService.sendWeiXinNews(map);
			System.out.println(res.getMsg());
		}
	
		return "success";
	}
	@Override
	public String uploadFile(@RequestParam CommonsMultipartFile myfiles,HttpServletRequest request) throws IOException{
			EhrUser user = RequestUtil.getLoginUser(request);	
		//	String fileName = myfiles.getOriginalFilename();
			String fileName = new Date().getTime()+"."+FileUtil.getSuffixName(myfiles.getOriginalFilename());
			final String FILE_PATH = new StringBuffer(JabavaPropertyCofigurer.getProperty("UPLOAD_PATH"))
					.append(File.separator).append("webapps").append(File.separator).append("project")
					.append(File.separator).append("files").append(File.separator)
					.append(user.getCompanyId().toString()).toString();

			File file  = new File(FILE_PATH);
			if (!file.exists()) {	//不存在则创建该目录
				file.mkdirs();
			}
			
			FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(FILE_PATH, fileName)); // 上传文件
			String fileUrl = HrHelperFileUploader.upload(FILE_PATH+File.separator+fileName);	
			return fileUrl;
	}
	
	/**
	 * 生成html文件并上产到sftp 服务器，返回 url
	 * <pre>
	 * @author steven.chen
	 * @date 2016年6月20日 上午11:46:05 
	 * </pre>
	 *
	 * @param content
	 * @param path
	 * @return
	 */
	private String createHtml(String content,String  path,String sourceFileName){
		String head="<!DOCTYPE html><html><head>"+"<meta charset='utf-8' /> </head><body>";
		String foot="</body></html>";
		String str = head+content+foot;
		String fileName = new Date().getTime()+".html";	
		String day = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			day = sdf.format(new Date());
		} catch (Exception e1) {
			logger.error("JabavaUtil.formatDate 出错", e1);
			day="5";
			e1.printStackTrace();
		}
		final String FILE_PATH = new StringBuffer(JabavaPropertyCofigurer.getProperty("UPLOAD_PATH"))
				.append(File.separator).append("webapps").append(File.separator).append("project")
				.append(File.separator).append("files").append(File.separator)
				.append(day).toString();
		//源文件
		final String sourceFile = FILE_PATH+File.separator+fileName;
		String destinationFile=null;
		String url = null;
		//目标文件
		if(sourceFileName!=null){
			 destinationFile =sftpWeixinPath+"/"+path+"/"+day+"/"+sourceFileName;
			  url = sftpWeixinUrl+"/"+path+"/"+day+"/"+sourceFileName;
		}else{
			 destinationFile =sftpWeixinPath+"/"+path+"/"+day+"/"+fileName;
			  url = sftpWeixinUrl+"/"+path+"/"+day+"/"+fileName;
		}		
		//如果不存在就创建目录
		File fileTemp  = new File(FILE_PATH);
		if(!fileTemp.exists()){
			fileTemp.mkdir();
		}
		File file  = new File(sourceFile);
		if (!file.exists()) {	//不存在则创建该目录
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("创建文件出错", e);
				e.printStackTrace();
			}
		}
		try {
			writeHtmlFile(str,file);
		} catch (Exception e) {
			logger.error("写入html文件出错", e);
			e.printStackTrace();		
		}
		try {
			//上传到sftp
			Sftp.sshSftp(sourceFile,destinationFile);
		} catch (Exception e) {
			logger.error("html文件上传到sftp服务器失败 文件名： "+sourceFile);		
			e.printStackTrace();
			return null;
		}	
		return url;
	}
	/**
	 * 写入文件
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月23日 下午6:01:42 
	 * </pre>
	 *
	 * @param content
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean writeHtmlFile(String content,File  fileName)throws Exception{  
		  BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
		  boolean flag=false;  
		  try {  
	            output.write(content);  
	            output.flush();
	            output.close();  
		   flag=true;  
		  } catch (Exception e) {  
			  logger.error("writeHtmlFile  出错", e);
		     e.printStackTrace();  
		  }finally{  
			  output.close();  
		  }  
		  return flag;  
		 }
}
