package com.jabava.controller.hro;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.pojo.accumulationfund.AfPaymentBill;
import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReport;
import com.jabava.pojo.report.EhrReportParam;
import com.jabava.pojo.socialsecurity.SsPaymentBill;
import com.jabava.service.accumulationfund.AccumulationFundAccountService;
import com.jabava.service.accumulationfund.AfPaymentBillService;
import com.jabava.service.common.IFileService;
import com.jabava.service.hro.ChangeService;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.service.report.IReportParamService;
import com.jabava.service.report.IReportService;
import com.jabava.service.socialsecurity.ISsPaymentBillService;
import com.jabava.service.socialsecurity.SsSocialSecurityAccountService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.FileUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;
import com.jabava.utils.enums.JabavaEnum.ProtocolStatus;
import com.jabava.utils.enums.SystemEnum;

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
	
	private Logger log = Logger.getLogger(ChangeController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private ChangeService changeService; 
	@Autowired
	private OutsourcingService   outsourcingService;
	@Autowired
	private IReportService reportService;
	@Autowired
	private IReportParamService reportParamService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private SsSocialSecurityAccountService socialSecurityAccountService;
	@Autowired
	private AccumulationFundAccountService accumulationFundAccountService;
	@Autowired
	private ISsPaymentBillService ssPaymentBillService;
	@Autowired
	private AfPaymentBillService afPaymentBillService;
	
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
	
	@RequestMapping("toUploadChange")
	public String toUploadChange(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		request.setAttribute("ssAccountList", socialSecurityAccountService.findSocialSecurityAccountByCompanyId(user.getCompanyId()));
		request.setAttribute("afAccountList", accumulationFundAccountService.getAccumulationFundProfileByCompanyId(user.getCompanyId()));
		String ssPaymentBillId = request.getParameter("ssPaymentBillId");
		String afPaymentBillId = request.getParameter("afPaymentBillId");
		if(!StringUtils.isEmpty(ssPaymentBillId)){
			SsPaymentBill sspb = ssPaymentBillService.findByPrimaryKey(Long.parseLong(ssPaymentBillId));
			request.setAttribute("socialSecurityAccountId", sspb.getSocialSecurityAccountId());
			request.setAttribute("month", sspb.getMonth());
		}
		if(!StringUtils.isEmpty(afPaymentBillId)){
			AfPaymentBill afpb = afPaymentBillService.findByPrimaryKey(Long.valueOf(afPaymentBillId));
			request.setAttribute("accumulationFundAccountId", afpb.getAccumulationFundAccountId());
			request.setAttribute("month", afpb.getMonth());
		}
		return "client/upload_change";
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
		String search = request.getParameter("search[value]");// search框的值
		hroOrderSend.setSearchValue(search);
		//if(StringUtils.isNotBlank(search)){
		//	hroOrderSend.setHroOrderSendId(Long.parseLong(search));
		//}
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
	
	@RequestMapping("listDetail/{id}/{type}")
	public String listDetail(HttpServletRequest request, @PathVariable Long id, @PathVariable String type) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		HroOrderSend hos = changeService.selectOrderSendById(id);
		if(hos == null || !user.getCompanyId().equals(hos.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		request.setAttribute("hroOrderSend", hos);
		if("1".equals(type)){
			return "client/list_zjb_add";
		}else if("2".equals(type)){
			return "client/list_zjb_change";
		}else if("3".equals(type)){
			return "client/list_zjb_subtract";
		}
		
		return "client/list_zjb_add";
	}
	
	@RequestMapping("loadDetail")
	@ResponseBody
	public List<Map<String,Object>> loadDetail(HttpServletRequest request,
			Long hroOrderSendId, String type) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(hroOrderSendId == null || type == null){
			throw new Exception("请求数据为空");
		}
		HroOrderSend hos = changeService.selectOrderSendById(hroOrderSendId);
		if(hos == null || !user.getCompanyId().equals(hos.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		if(StringUtils.isBlank(hos.getBatchCode())){
			return new ArrayList<Map<String,Object>>();
		}else{
			return changeService.loadDetail(hos,type);
		}
	}
	
	@RequestMapping("loadDetailPage")
	@ResponseBody
	public Page<Map<String,Object>> loadDetailPage(HttpServletRequest request,
			Long hroOrderSendId, String type) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		if(hroOrderSendId == null || type == null){
			throw new Exception("请求数据为空");
		}
		HroOrderSend hos = changeService.selectOrderSendById(hroOrderSendId);
		if(hos == null || !user.getCompanyId().equals(hos.getCompanyId())){
			throw new Exception("没有数据操作权限");
		}
		
		List<Map<String,Object>> list = null;
		if(StringUtils.isBlank(hos.getBatchCode())){
			list = new ArrayList<Map<String,Object>>();
		}else{
			list = changeService.loadDetail(hos,type);
		}
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0, Constants.MAX_RECORD_SIZE);
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
				.append(ehrUser.getCompanyId().toString()).append(File.separator)
				.append(hroOrderSend.getAttachment()).toString();
		hroOrderSend.setAttachmentUrl(FILE_PATH);
		hroOrderSend.setCreateUserId(ehrUser.getUserId());
		hroOrderSend.setCreateDate(new Date());
		hroOrderSend.setUpdateDate(new Date());
		hroOrderSend.setUpdateUserId(ehrUser.getUserId());
		hroOrderSend.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		hroOrderSend.setCompanyId(ehrUser.getCompany().getCompanyId());
		hroOrderSend.setSendTime(new Date());
		
		String result =  changeService.addChange(hroOrderSend,ehrUser.getCompanyId());
		if(request.equals("success")){
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Upload, SystemEnum.Module.Supplier, "上传增减变表");
		}
		res.put("message", result);
		return res;
	}
	
	/**
	 * 生成并新增增减变
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("generateAddChange")
	@ResponseBody
	public Map<String,Object> generateAddChange(HttpServletRequest request,HttpServletResponse response, Long reportId) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		//1.验证并生成文件
		if(reportId == null){
			return MessageUtil.errorMessage("请求数据为空");
		}
		EhrReport report = reportService.selectByReportId(reportId);

		if(report == null){
			return MessageUtil.errorMessage("报表不存在");
		}
		if(report.getCompanyId() != 0 && !report.getCompanyId().equals(user.getCompanyId())){
			//只能操作本公司及平台级报表
			return MessageUtil.errorMessage("没有数据操作权限");
		}
		
		List<EhrReportParam> paramList = reportParamService.listByReportId(reportId);
		Map<String,Object> params = new HashMap<String,Object>();
		for(EhrReportParam rp : paramList){
			//根据报表参数获取前台数据
			params.put(rp.getEnglishName(),request.getParameter(rp.getEnglishName()));
		}
		
		//返回生成的报表文件信息
		Map<String,Object> grResult = reportService.generateReport(user,report,params);
		Long fileId = (Long)grResult.get("fileId");
		if(grResult.get("fileId") == null){
			return MessageUtil.errorMessage("生成增减变文件失败");
		}
		
		log.info("生成增减变文件：" + fileId);
		
		//拷贝文件，便于前台展示
		//FileUtils.copyInputStreamToFile(myfiles[0].getInputStream(), new File(FILE_PATH, fileName));
		
		//2.利用生成的文件上传增减变
		EhrFile file = fileService.getFileById(user.getCompanyId(), fileId);
		//String fullPath = file.getFilePath();
		HroOrderSend hroOrderSend = new HroOrderSend();
		//hroOrderSend.setAttachment(fullPath.substring(fullPath.lastIndexOf("/") + 1));
		//hroOrderSend.setAttachmentUrl(fullPath.substring(0,fullPath.lastIndexOf("/")));
		hroOrderSend.setAttachment(report.getReportName() + ".xlsx");
		hroOrderSend.setAttachmentUrl(file.getFilePath());
		hroOrderSend.setCreateUserId(user.getUserId());
		hroOrderSend.setCreateDate(new Date());
		hroOrderSend.setUpdateDate(new Date());
		hroOrderSend.setUpdateUserId(user.getUserId());
		hroOrderSend.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		hroOrderSend.setCompanyId(user.getCompanyId());
		hroOrderSend.setSendTime(new Date());
		String result = changeService.addChange(hroOrderSend,user.getCompanyId());
		
		if (!"success".equals(result)){
			return MessageUtil.errorMessage(result);
		}else{
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Upload, SystemEnum.Module.Supplier, "一键外包"+report.getCompanyId());
			return MessageUtil.successMessage("操作成功");
		}
	}
	
	@RequestMapping("downloadFile")
	@ResponseBody
	public String downloadFile(HttpServletResponse response,Long hroOrderSendId){
		HroOrderSend hroOrderSend = changeService.selectOrderSendById(hroOrderSendId);
		try {
			//if(FileUtil.downLoadFile(hroOrderSend.getAttachmentUrl()+File.separator+hroOrderSend.getAttachment(), response, hroOrderSend.getAttachment(), "xlsx")){
			if(FileUtil.downLoadFile(hroOrderSend.getAttachmentUrl(), response, hroOrderSend.getAttachment(), "xlsx")){
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
