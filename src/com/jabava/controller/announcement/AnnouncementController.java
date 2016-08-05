
package com.jabava.controller.announcement;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.utils.enums.JabavaEnum.HasRead;
import com.jabava.utils.enums.JabavaEnum.InformationRange;
import com.jabava.utils.enums.JabavaEnum.InformationType;
import com.jabava.utils.enums.SystemEnum;
import com.jabava.utils.enums.SystemEnum.Module;
import com.jabava.pojo.announcement.EhrInformation;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.announcement.AnnouncementService;
import com.jabava.service.manage.IUserService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * 公告
 *
 * @version $Id: AnnouncementController.java, 
 * v 0.1 2015年12月25日 下午2:33:10 
 * <pre>
 * @author steven.chen
 * @date 2015年12月25日 下午2:33:10 
 * </pre>
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private IUserService userService;

	/**
	 * 打开消息公告页面
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月25日 下午3:08:24 
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/announcementMain")
	public String announcementMain(
			HttpServletRequest request, HttpServletResponse response,EhrInformation ehrInformation) {
		
		return "announcement/announcement_list";

	}
	/**
	 * 信息公告查询列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月7日 下午4:28:40 
	 * </pre>
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @param ehrInformation
	 * @return
	 * @throws UnsupportedEncodingException 
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/announcementList")
	@ResponseBody
	public Page<Map<String, Object>> announcementList(Model model, String data,
			HttpServletRequest request, HttpServletResponse response,
			int start, int length) throws UnsupportedEncodingException {

		QeuryEhrInformation ehrInformation2 =  new QeuryEhrInformation();
					
		Map<String, Object> map = (Map) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject
                .fromObject(data), Map.class);
		if(map!=null && map.get("startDate")!=null && StringUtils.isNotBlank(map.get("startDate").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = dateFormat.parse(map.get("startDate").toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ehrInformation2.setStartDate(date);
		}
		if(map!=null && map.get("finishDate")!=null && StringUtils.isNotBlank(map.get("finishDate").toString())){
			Date date =new  Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = dateFormat.parse(map.get("finishDate").toString()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ehrInformation2.setFinishDate(date);
		}
		if(map!=null && map.get("informationTitle")!=null && StringUtils.isNotBlank(map.get("informationTitle").toString())){			
			ehrInformation2.setInformationTitle(map.get("informationTitle").toString());
		}				
		request.setCharacterEncoding("UTF-8");		
		EhrUser ehrUser = RequestUtil.getLoginUser(request);

		ehrInformation2.setCompanyId(ehrUser.getCompanyId());
		// 默认类型为公司信息
		ehrInformation2.setInformationType(InformationType.COMPANY.getValue());
		// 默认范围为全部公司人员
		ehrInformation2.setInformationRange(InformationRange.ALL_COMPANY_USER
				.getValue());
		ehrInformation2.setStart(start);
		ehrInformation2.setLength(length);
		/*List<EhrInformation> ehrInformationlist = announcementService
				.findInformationByParams(ehrInformation2);
		ehrInformation2.setLength(null);
		ehrInformation2.setStart(null);*/
		String search = request.getParameter("search[value]");// search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		if(order == null || "".equals(order) || "0".equals(order)){
			ehrInformation2.setOrderBy("last_modify_date DESC, is_priority DESC");
		}else{
			order = request.getParameter("columns["+order+"][data]"); //排序列的名称
			String according = request.getParameter("order[0][dir]");//升序或倒序
			order = EhrInformation.getColumnName(order);
			ehrInformation2.setOrderBy(order + " " + according);
		}
		
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start, length);
		List<Map<String, Object>> list =  announcementService
				.findInformationByParamsPage(ehrInformation2,page,search);
		page.setData(list);
		return page;
		/*System.out.println(JSONObject.toJSON(ehrInformationlist));

		Map<String, Object> map = new HashMap<String, Object>();
		if (ehrInformationlist2 != null && ehrInformationlist2.size() > 0) {
			map.put("recordsTotal", ehrInformationlist2.size());
			map.put("recordsFiltered", ehrInformationlist2.size());
		} else {
			map.put("recordsTotal", 0);
			map.put("recordsFiltered", 0);
		}

		map.put("data", ehrInformationlist);
		return map;*/

	}
	/**
	 * 右上角显示的五条最近的信息公告 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月7日 下午4:28:55 
	 * </pre>
	 *
	 * @param request
	 * @param ehrInformation
	 * @return
	 */
	@RequestMapping("/informationList")
	@ResponseBody
	public List<EhrInformation> informationList(
			HttpServletRequest request,QeuryEhrInformation ehrInformation) {	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		ehrInformation.setCompanyId(ehrUser.getCompanyId());
		//默认类型为公司信息
		ehrInformation.setInformationType(InformationType.COMPANY.getValue());
		//默认范围为全部公司人员
		ehrInformation.setInformationRange(InformationRange.ALL_COMPANY_USER.getValue());
		ehrInformation.setHasRead(HasRead.UNREAD.getValue());
		ehrInformation.setStart(0);
		ehrInformation.setLength(5);
		ehrInformation.setSearchDate(new Date());
		List<EhrInformation>	ehrInformationlist = announcementService.findInformationByParams(ehrInformation);
//		List<EhrInformation>	resultlist = new ArrayList<EhrInformation>();
//		//显示五条记录
//		if(ehrInformationlist!=null  && ehrInformationlist.size()>0){
//			if(ehrInformationlist.size()>5){
//				for(int i=0;i<5;i++){
//					resultlist.add(ehrInformationlist.get(i));
//				}
//			}else{
//				for(EhrInformation ehrInformation2 : ehrInformationlist){
//					resultlist.add(ehrInformation2);
//				}
//			}
//			
//			
//		}		
		return ehrInformationlist;

	}
	@RequestMapping("/getListSize")
	@ResponseBody
	public int getListSize(
			HttpServletRequest request,QeuryEhrInformation ehrInformation) {	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		ehrInformation.setCompanyId(ehrUser.getCompanyId());
		//默认类型为公司信息
		ehrInformation.setInformationType(InformationType.COMPANY.getValue());
		//默认范围为全部公司人员
		ehrInformation.setInformationRange(InformationRange.ALL_COMPANY_USER.getValue());
		ehrInformation.setHasRead(HasRead.UNREAD.getValue());
		ehrInformation.setStart(null);
		ehrInformation.setLength(null);
		ehrInformation.setSearchDate(new Date());
		List<EhrInformation>	ehrInformationlist = announcementService.findInformationByParams(ehrInformation);
			
		return ehrInformationlist.size();

	}
	/**
	 * 新增信息公告
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:27:11 
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param ehrInformation
	 * @return
	 */	
	@RequestMapping("/addAnnouncement")
	@ResponseBody
	public Map<String,Object> addAnnouncement(HttpServletRequest request, HttpServletResponse response,
			EhrInformation ehrInformation) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		ehrInformation.setCreateUserId(ehrUser.getUserId());
		ehrInformation.setCreateUserName(ehrUser.getUserName());
		ehrInformation.setLastModifyUserId(ehrUser.getUserId());
		ehrInformation.setLastModifyUserName(ehrUser.getUserName());
		ehrInformation.setLastModifyDate(new Date());
		ehrInformation.setCompanyId(ehrUser.getCompanyId());		
		ehrInformation.setHasRead(HasRead.UNREAD.getValue());
		String msg = null;
		if("success".equals(announcementService.addAnnouncement(ehrInformation))){
			sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Add, Module.InformationBulletin, "发布标题为"+ehrInformation.getInformationTitle()+"的公告");
			msg = MessageUtil.INS_SUCCESS;
			
		}else{
			msg = MessageUtil.INS_ERROR;
		}
		
		return MessageUtil.message(msg);
		
	}
	/**
	 * 显示修改页面
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月31日 上午11:55:44 
	 * </pre>
	 *
	 * @param informationId
	 * @return
	 */
	@RequestMapping("/showUpdate")
	@ResponseBody
	public EhrInformation showUpdate(Long informationId){
		
		return announcementService.selectByInformationId(informationId);
	}
	/**
	 * 修改信息公告
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:27:29 
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param ehrInformation
	 * @return
	 */
	@RequestMapping("/updateAnnouncement")
	@ResponseBody
	public Map<String,Object> updateAnnouncement(HttpServletRequest request, HttpServletResponse response,
			EhrInformation ehrInformation) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		
		ehrInformation.setLastModifyUserId(ehrUser.getUserId());
		ehrInformation.setLastModifyUserName(ehrUser.getUserName());
		ehrInformation.setLastModifyDate(new Date());
		String msg = null;
		if("success".equals(announcementService.updateAnnouncement(ehrInformation))){
				sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Update, SystemEnum.Module.InformationBulletin, "修改id为："+ehrInformation.getInformationId()+"的公告");
				msg = MessageUtil.UPD_SUCCESS;
		}else{
			msg = MessageUtil.UPD_ERROR;
		}
		
		return MessageUtil.message(msg);
	}
	/**
	 * 删除信息公告 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:27:52 
	 * </pre>
	 *
	 * @param informationId
	 * @return
	 */
	@RequestMapping("/deleteAnnouncement")
	@ResponseBody
	public Map<String,Object> deleteAnnouncement(HttpServletRequest request,Long informationId) throws Exception{	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		String msg = null;
		if("success".equals(announcementService.deleteByInformationId(informationId))){
				sysLogSercice.addSysLog(RequestUtil.getLoginUser(), SystemEnum.LogOperateType.Delete, SystemEnum.Module.InformationBulletin, "删除id为"+informationId+"的公告");
				msg = MessageUtil.DEL_SUCCESS;
		}else{
			msg = MessageUtil.DEL_ERROR;
		}
		
		return MessageUtil.message(msg);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
