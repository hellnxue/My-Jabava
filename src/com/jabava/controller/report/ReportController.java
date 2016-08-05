package com.jabava.controller.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReport;
import com.jabava.pojo.report.EhrReportConfig;
import com.jabava.pojo.report.EhrReportParam;
import com.jabava.service.common.ICommonDataService;
import com.jabava.service.common.IFileService;
import com.jabava.service.manage.ICompanyService;
import com.jabava.service.report.IReportConfigService;
import com.jabava.service.report.IReportParamService;
import com.jabava.service.report.IReportService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.Constants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static Logger log = Logger.getLogger(ReportController.class);
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private IReportService reportService;
	@Autowired
	private IReportConfigService reportConfigService;
	@Autowired
	private IReportParamService reportParamService;
	@Autowired
	private ICommonDataService commonDataService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("toListReport")
	public String toListReport(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//查询报表类型
		request.setAttribute("reportTypeList", commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_REPORT_TYPE));
		
		//公司
		request.setAttribute("companyList", companyService.getAllCompany(false));
		
		return "report/listReport";
	}
	
	@RequestMapping("listReportPage")
	@ResponseBody
	public Page<EhrReport> listReportPage(HttpServletRequest request, HttpServletResponse response, int start, int length){
		EhrUser user = RequestUtil.getLoginUser(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		
		String search = request.getParameter("search[value]");// search框的值
		String reportType = request.getParameter("reportType");
		String reportName = request.getParameter("reportName");
		String scopeType = request.getParameter("scopeType");
		String scopeId = request.getParameter("scopeId");
		params.put("search", search);
		params.put("reportType", reportType);
		params.put("reportName", reportName);
		params.put("scopeType", scopeType);
		params.put("scopeId", scopeId);
		
		//params.put("orderBy", ehrInformation.getOrderBy());
		
		Page<EhrReport> page = new Page<EhrReport>(start,length);
		params.put("page", page);
		List<EhrReport> list = reportService.listReportPage(params);
		
		page.setData(list);
		return page;
	}
	
	@RequestMapping("toAddReport")
	public String toAddReport(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//如果reportId不为空，则读取并添加到request
		String reportId = request.getParameter("reportId");
		if(!StringUtils.isEmpty(reportId)){
			EhrReport report = reportService.loadReport(user.getCompanyId(), Long.valueOf(reportId));
			request.setAttribute("report", report);
		}
		
		//查询报表类型
		request.setAttribute("reportTypeList", commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_REPORT_TYPE));
		
		//公司
		request.setAttribute("companyList", companyService.getAllCompany(false));
		
		return "report/addReport";
	}
	
	@RequestMapping("editReport")
	@ResponseBody
	public Map<String,Object> editReport(HttpServletRequest request, HttpServletResponse response, EhrReport report){
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrReport exist = reportService.selectByTypeAndName(user.getCompanyId(), report.getReportType(), report.getReportName());
		if(exist != null){
			if(report.getReportId() == null){
				return MessageUtil.errorMessage("相同类型名称重复");
			}else if(!exist.getReportId().equals(report.getReportId())){
				return MessageUtil.errorMessage("相同类型名称重复");
			}
		}

		if(reportService.saveOrUpdate(user, report) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("reportId", report.getReportId());
			return result;
		}
	}
	
	@RequestMapping("setReportTemplate")
	@ResponseBody
	public Map<String,Object> setReportTemplate(HttpServletRequest request, HttpServletResponse response, 
			Long reportId, Long fileId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportId == null || fileId == null){
			return MessageUtil.errorMessage("数据为空");
		}
		
		EhrReport report = reportService.loadReport(user.getCompanyId(), reportId);
		if(report == null){
			return MessageUtil.errorMessage("报表不存在");
		}
		
		EhrFile file = fileService.getFileById(user.getCompanyId(), fileId);
		if(file == null){
			return MessageUtil.errorMessage("文件不存在");
		}

		report.setFileId(fileId);
		if(reportService.saveOrUpdate(user, report) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			return MessageUtil.successMessage("操作成功");
		}
	}
	
	@RequestMapping("deleteReport")
	@ResponseBody
	public Map<String,Object> deleteReport(HttpServletRequest request, HttpServletResponse response, Long reportId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportService.deleteById(user.getCompanyId(), reportId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("listReportByType")
	@ResponseBody
	public List<EhrReport> listReportByType(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		String reportType = request.getParameter("reportType");
		if(!StringUtils.isEmpty(reportType)){
			List<EhrReport> list = reportService.listReportByType(user.getCompanyId(), reportType);
			return list;
		}
		
		return null;
	}
	
	@RequestMapping("listReportConfig")
	@ResponseBody
	public Page<EhrReportConfig> listReportConfig(HttpServletRequest request, HttpServletResponse response){
		//EhrUser user = RequestUtil.getLoginUser(request);
		String reportId = request.getParameter("reportId");
		Page<EhrReportConfig> page = new Page<EhrReportConfig>(0,Constants.MAX_RECORD_SIZE);
		if(!StringUtils.isEmpty(reportId)){
			List<EhrReportConfig> list = reportConfigService.listByReportId(Long.valueOf(reportId));
			page.setData(list);
		}
		
		return page;
	}
	
	@RequestMapping("loadReportConfig")
	@ResponseBody
	public EhrReportConfig loadReportConfig(HttpServletRequest request, HttpServletResponse response, Long reportConfigId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportConfigId == null){
			return null;
		}
		
		return reportConfigService.loadReportConfig(user.getCompanyId(), reportConfigId);
	}
	
	@RequestMapping("saveReportConfig")
	@ResponseBody
	public Map<String, Object> saveReportConfig(HttpServletRequest request, HttpServletResponse response, EhrReportConfig reportConfig) {
		EhrUser user = RequestUtil.getLoginUser(request);
//		EhrReportConfig exist = reportConfigService.selectByReportAndDataset(user.getCompanyId(), reportConfig.getReportId(), reportConfig.getReportDataset());
//		if(exist != null){
//			//属于同一父数据集(或父数据集为空)，不能重复
//		}

		if(reportConfigService.saveOrUpdate(user, reportConfig) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("reportConfigId", reportConfig.getId());
			return result;
		}
	}
	
	@RequestMapping("deleteReportConfig")
	@ResponseBody
	public Map<String,Object> deleteReportConfig(HttpServletRequest request, HttpServletResponse response, Long reportConfigId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportConfigService.deleteById(user.getCompanyId(), reportConfigId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("listReportParam")
	@ResponseBody
	public Page<EhrReportParam> listReportParam(HttpServletRequest request, HttpServletResponse response){
		//EhrUser user = RequestUtil.getLoginUser(request);
		String reportId = request.getParameter("reportId");
		Page<EhrReportParam> page = new Page<EhrReportParam>(0,Constants.MAX_RECORD_SIZE);
		if(!StringUtils.isEmpty(reportId)){
			List<EhrReportParam> list = reportParamService.listByReportId(Long.valueOf(reportId));
			page.setData(list);
		}
		
		return page;
	}
	
	@RequestMapping("loadReportParam")
	@ResponseBody
	public EhrReportParam loadReportParam(HttpServletRequest request, HttpServletResponse response, Long reportParamId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportParamId == null){
			return null;
		}
		
		return reportParamService.loadReportParam(user.getCompanyId(), reportParamId);
	}
	
	@RequestMapping("saveReportParam")
	@ResponseBody
	public Map<String, Object> saveReportParam(HttpServletRequest request, HttpServletResponse response, EhrReportParam reportParam) {
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrReportParam exist = reportParamService.selectByReportAndName(user.getCompanyId(), reportParam.getReportId(), reportParam.getEnglishName());
		if(exist != null){
			if(reportParam.getId() == null){
				return MessageUtil.errorMessage("相同类型名称重复");
			}else if(!exist.getId().equals(reportParam.getId())){
				return MessageUtil.errorMessage("相同类型名称重复");
			}
		}

		if(reportParamService.saveOrUpdate(user, reportParam) == 0){
			return MessageUtil.errorMessage("操作失败");
		}else{
			Map<String,Object> result = MessageUtil.successMessage("操作成功");
			result.put("reportParamId", reportParam.getId());
			return result;
		}
	}
	
	@RequestMapping("deleteReportParam")
	@ResponseBody
	public Map<String,Object> deleteReportParam(HttpServletRequest request, HttpServletResponse response, Long reportParamId){
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportParamService.deleteById(user.getCompanyId(), reportParamId) == 0){
			return MessageUtil.errorMessage("删除失败");
		}else{
			return MessageUtil.successMessage("删除成功");
		}
	}
	
	@RequestMapping("toGenerateReport")
	public String toGenerateReport(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		//查询报表类型
		request.setAttribute("reportTypeList", commonDataService.listByCommonDataType(CommonDataConstants.COMMON_DATA_REPORT_TYPE));
		return "report/genReport";
	}
	
	@RequestMapping("generateReport")
	@ResponseBody
	public Map<String,Object> generateReport(HttpServletRequest request, HttpServletResponse response, Long reportId) {
		EhrUser user = RequestUtil.getLoginUser(request);
		if(reportId == null){
			return MessageUtil.errorMessage("请求数据为空");
		}
		//EhrReport report = reportService.loadReport(user.getCompanyId(), reportId);
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
		
		try {
			//返回生成的报表文件信息
			Map<String,Object> map = reportService.generateReport(user,report,params);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select, SystemEnum.Module.Report, "生成id为"+reportId+"报表");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.errorMessage(e.getMessage());
		}
	}
}
