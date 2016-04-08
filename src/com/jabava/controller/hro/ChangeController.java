package com.jabava.controller.hro;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.core.EnumConstents.IsDeleted;
import com.jabava.core.EnumConstents.ProtocolStatus;
import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.ChangeService;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;





/**
 * HRO 客户端-上传增减变,查询增减变
 *
 * @version $Id: ChangeController.java, 
 * v 0.1 2016年1月11日 上午10:33:07 
 * <pre>
 * @author steven.chen
 * @date 2016年1月11日 上午10:33:07 
 * </pre>
 */
@Controller
@RequestMapping("/change")
public class ChangeController {
	
	@Autowired
	private ChangeService changeService; 
	@Autowired
	private OutsourcingService   outsourcingService; 
	
	/**
	 * 获取公司的协议号
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月13日 下午5:29:41 
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("getProtocolCode")
	@ResponseBody
	public String  getProtocolCode(HttpServletRequest request){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		HroPactInfo hroPactInfo = new HroPactInfo();
		hroPactInfo.setCompanyId(ehrUser.getCompanyId());
		hroPactInfo.setState(ProtocolStatus.ENABLE.getValue());
		hroPactInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		List<HroPactInfo> pactList =  outsourcingService.queryProtocol(hroPactInfo);
		if(pactList!=null && pactList.size()>0 ){
			return pactList.get(0).getPactCode();
		}else{
			return "error";
		}
	}
	
	/**
	 * 上传增减变文件
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月11日 下午2:59:25 
	 * </pre>
	 *
	 * @param myfiles
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("uploadFile")
	@ResponseBody
	public Map uploadFile(@RequestParam MultipartFile[] myfiles,HttpServletResponse response,HttpServletRequest request) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);	
	//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		//String fileName = dateFormat.format(new Date())+"."+FileUtil.getSuffixName(myfiles[0].getOriginalFilename());
//		String fileName = new Date().getTime()+"."+FileUtil.getSuffixName(myfiles[0].getOriginalFilename());
		//文件名
		String fileName = myfiles[0].getOriginalFilename();
		
		final String FILE_PATH = new StringBuffer(JabavaPropertyCofigurer.getProperty("UPLOAD_PATH"))
				.append(File.separator).append("webapps").append(File.separator).append("project")
				.append(File.separator).append("files").append(File.separator)
				.append(user.getCompanyId().toString()).toString();

		//String path =request.getSession().getServletContext().getRealPath(File.separator);
		File file  = new File(FILE_PATH);
		if (!file.exists()) {	//不存在则创建该目录
			file.mkdirs();
		}
		
	//	final String FILE_PATH=path+File.separator+"webapps"+File.separator+"project"+File.separator+"files"+File.separator+ehrUser.getCompanyId().toString();
		FileUtils.copyInputStreamToFile(myfiles[0].getInputStream(), new File(FILE_PATH, fileName)); // 上传文件
		Map<String,String> map = new HashMap<String,String>();
		map.put("fileUrl", fileName);
		return map;
	}
	/**
	 * 查询增减变表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月11日 下午4:54:39 
	 * </pre>
	 *
	 * @return
	 */
	@RequestMapping("findChange")
	@ResponseBody
	public Page<HroOrderSend> findChange(HttpServletRequest request,HroOrderSend hroOrderSend,int start,int length){	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		// String path =request.getSession().getServletContext().getRealPath(File.separator);
		String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	//	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println(path);
		String search = request.getParameter("search[value]");// search框的值
		if(StringUtils.isNotBlank(search)){
			hroOrderSend.setHroOrderSendId(Long.parseLong(search));
		}
		String order = request.getParameter("order[0][column]");//排序列的下标
		if(order == null || "".equals(order)||"0".equals(order)){
			hroOrderSend.setOrderBy("send_time DESC");
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = getColumnName(order);
			hroOrderSend.setOrderBy(order + " " + according);
		}
		hroOrderSend.setCompanyId(ehrUser.getCompany().getCompanyId());		
		Page<HroOrderSend> page = new Page<HroOrderSend>(start, length);
		hroOrderSend.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		List<HroOrderSend> list =  changeService
				.findChangePage(page,hroOrderSend);
		page.setData(list);
		return page;
	}
	/**
	 * 新增增减变
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月11日 下午4:37:28 
	 * </pre>
	 *
	 * @param hroOrderSend
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addChange")
	@ResponseBody
	public Map<String,Object> addChange(HttpServletRequest request,HroOrderSend hroOrderSend) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		Map<String,Object> res = new HashMap<String,Object>();
	//	String path =request.getSession().getServletContext().getRealPath(File.separator);
	//	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+File.separator;

		final String FILE_PATH = new StringBuffer(JabavaPropertyCofigurer.getProperty("UPLOAD_PATH"))
				.append(File.separator).append("webapps").append(File.separator).append("project")
				.append(File.separator).append("files").append(File.separator)
				.append(ehrUser.getCompanyId().toString()).toString();
		hroOrderSend.setAttachmentUrl(FILE_PATH);
		hroOrderSend.setCreateUserId(ehrUser.getUserId());
		hroOrderSend.setCreateDate(new Date());
		hroOrderSend.setUpdateDate(new Date());
		hroOrderSend.setUpdateUserId(ehrUser.getUserId());
		hroOrderSend.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		hroOrderSend.setCompanyId(ehrUser.getCompany().getCompanyId());
		hroOrderSend.setSendTime(new Date());
		String result =  changeService.addChange(hroOrderSend,ehrUser.getCompanyId());
		res.put("message", result);
		return res;
	}
	@RequestMapping("downloadFile")
	@ResponseBody
	public String downloadFile(HttpServletResponse response,Long hroOrderSendId){
		HroOrderSend hroOrderSend = changeService.selectOrderSendById(hroOrderSendId);
		try {
			if(FileUtil.downLoadFile(hroOrderSend.getAttachmentUrl()+File.separator+hroOrderSend.getAttachment(), response, hroOrderSend.getAttachment(), "xlsx")){
				return "success";
			}else{
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public static String getColumnName(String order){
		if(StringUtils.isNotBlank(order)){
			if(order.equals("hroOrderSendId")){
				return "hro_order_send_id";
			}else if("sendTime".equals(order)){
				return "send_time";
			}else if("remark".equals(order)){
				return "remark";
			}
		}
		return "hro_order_send_id";
	}
	
	
}
