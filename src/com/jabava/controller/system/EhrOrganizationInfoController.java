package com.jabava.controller.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrOrganizationHistory;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

/**
 * 
 * @author panfei
 * 
 */
@Controller
@RequestMapping("/system")
public class EhrOrganizationInfoController {
	@Resource
	public IEhrOrganizationService ehrOrganizationService;
	
	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Resource
	public EhrPersonService ehrPersonService;
	
	@RequestMapping("/searchOrganization")
	public String searchOrganization(){
		return "organizational/list_structure";
	}
	
	@RequestMapping("listFirstLevel")
	@ResponseBody
	public List<EhrOrganization> listFirstLevel(HttpServletRequest request, HttpServletResponse response){
		EhrUser user = RequestUtil.getLoginUser(request);
		String withTop = request.getParameter("withTop");
		//EhrOrganization topOrg = ehrOrganizationService.findTopOrganization(user.getCompanyId());
		//List<EhrOrganization> orgList = ehrOrganizationService.getChildren(topOrg.getOrganizationId());
		List<EhrOrganization> topList = ehrOrganizationService.findByLevel(user.getCompanyId(), 0);
		List<EhrOrganization> orgList = ehrOrganizationService.findByLevel(user.getCompanyId(), 1);
		if(!StringUtils.isEmpty(withTop)){
			if(topList != null && !topList.isEmpty()){
				orgList.add(0,topList.get(0));
			}
		}
		return orgList;
	}

	@RequestMapping("/loadTree")
	@ResponseBody
	public Map<String,List<EhrOrganization>> loadTree(HttpServletRequest request, HttpServletResponse response){	
		EhrUser user = RequestUtil.getLoginUser(request);
		//按Level,Order排序的列表
//		BeanUtils.cloneBean(bean)
		Map<String,List<EhrOrganization>> result = new HashMap<String,List<EhrOrganization>>();
		result.put("data", ehrOrganizationService.loadAuthorisedTree(user.getCompanyId()));
		return result;
	}
	/**
	 * 获取公司的组织架构(用于选择部门)
	 * @param personId
	 * @return
	 */
	@RequestMapping("/loadPersonTree")
	@ResponseBody
	public Map<String,List<EhrOrganization>> loadPersonTree(Long personId){
		try {
			EhrPerson ehrPerson = null;
			ehrPerson = ehrPersonService.getByPersonId(personId);
			Map<String,List<EhrOrganization>> result = new HashMap<String,List<EhrOrganization>>();
			result.put("data", ehrOrganizationService.loadAuthorisedTree(ehrPerson.getCompanyId()));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String,List<EhrOrganization>>();
		}
	}
	@RequestMapping("/renameOrg")
	@ResponseBody
	public Map<String,Object> rename(Long organizationId,String organizationName,String organizationCode,
			String memo,HttpServletRequest request, HttpServletResponse response) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrOrganization org = ehrOrganizationService.selectByorganizationId(organizationId);
		if(org == null || !org.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("组织不存在或无权操作");
		}
		
		org.setOrganizationName(organizationName);
		org.setOrganizationCode(organizationCode);
		org.setMemo(memo);
		org.setLastModifyDate(new Date());
		org.setLastModifyUserId(user.getUserId());
		org.setLastModifyUserName(user.getUserName());
		
		Map<String,Object> data = null;
		String vResult = ehrOrganizationService.validateDuplicate(org);
		if(vResult != null){
			data = new HashMap<String,Object>();
			data.put("msg", vResult);
			return data;
		}
		
		int result = ehrOrganizationService.update(org);
		if(result > 0){
			data = MessageUtil.message(MessageUtil.UPD_SUCCESS);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Update, SystemEnum.Module.Organization, "修改了一个名为"+organizationName+"的部门");
		}else{
			data = MessageUtil.message(MessageUtil.UPD_ERROR);
		}
		
		//日志
		
		return data;
	}
	
	@RequestMapping("/addOrg")
	@ResponseBody
	public Map<String,Object> add(Long parentId,String organizationCode,String organizationName,
			String memo,HttpServletRequest request, HttpServletResponse response) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrOrganization parent = ehrOrganizationService.selectByorganizationId(parentId);
		if(parent == null || !parent.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("组织不存在或无权操作");
		}
		
		EhrOrganization org = new EhrOrganization();
		org.setCompanyId(user.getCompanyId());
		org.setOrganizationCode(organizationCode);
		org.setOrganizationName(organizationName);
		org.setMemo(memo);
		org.setParentId(parentId);
		Date createDate = new Date();
		org.setCreateDate(createDate);
		org.setCreateUserId(user.getUserId());
		org.setCreateUserName(user.getUserName());
		org.setLastModifyDate(createDate);
		org.setLastModifyUserId(user.getUserId());
		org.setLastModifyUserName(user.getUserName());
		
		Map<String,Object> data = null;
		String vResult = ehrOrganizationService.validateDuplicate(org);
		if(vResult != null){
			data = new HashMap<String,Object>();
			data.put("msg", vResult);
			return data;
		}
		
		int result = ehrOrganizationService.insert(org,parent);
		if(result > 0){
			data = MessageUtil.message(MessageUtil.INS_SUCCESS);
			data.put("org", org);
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add, SystemEnum.Module.Organization, "添加了一个名为"+organizationName+"的部门");

		}else{
			data = MessageUtil.message(MessageUtil.INS_ERROR);
		}
		
		//日志
		
		return data;
	}
	
	@RequestMapping("/moveOrg")
	@ResponseBody
	public Map<String,Object> move(Long sourceId,Long targetId,String moveFlag,
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrOrganization source = ehrOrganizationService.selectByorganizationId(sourceId);
		EhrOrganization target = ehrOrganizationService.selectByorganizationId(targetId);
		if(source == null || !source.getCompanyId().equals(user.getCompanyId()) ||
				target == null || !target.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("组织不存在或无权操作");
		}
		if(source.getOrganizationLevel() == null || source.getOrganizationLevel() == 0){
			throw new Exception("顶层组织不能移动");
		}
		if(target.getOrganizationLevel() == null || target.getOrganizationLevel() == 0){
			throw new Exception("不能移动到顶层组织");
		}
		//不能移到自己的下级
		
		Map<String,Object> data = null;
		if(ehrOrganizationService.move(source, target, moveFlag)){
			data = MessageUtil.message(MessageUtil.UPD_SUCCESS);
		}else{
			data = MessageUtil.message(MessageUtil.UPD_ERROR);
		}
		
		return data;
	}
	/**
	 * 该部门下的员工
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月30日 下午9:54:07 
	 * </pre>
	 *
	 * @param organizationId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadPerson")
	@ResponseBody
	public Page<EhrPerson> loadPerson(Long organizationId,HttpServletRequest request, 
			HttpServletResponse response) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		Page<EhrPerson> page = new Page<EhrPerson>(null,null);
		if(organizationId == null){
			page.setData(new ArrayList<EhrPerson>());
			return page;
		}
		EhrOrganization org = ehrOrganizationService.selectByorganizationId(organizationId);
		if(org == null || !org.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("组织不存在或无权操作");
		}
		
		page.setData(ehrOrganizationService.selectPersonByOrganization(organizationId));
		return page;
	}

	@RequestMapping("deleteOrganization")
	@ResponseBody
	public Map<String,Object> deleteByorganizationId(Long organizationId,
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		EhrUser user = RequestUtil.getLoginUser(request);
		EhrOrganization org = ehrOrganizationService.selectByorganizationId(organizationId);
		if(org == null || !org.getCompanyId().equals(user.getCompanyId())){
			throw new Exception("组织不存在或无权操作");
		}
		if(org.getOrganizationLevel() == null || org.getOrganizationLevel() == 0){
			throw new Exception("顶层组织不能删除");
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		if (!ehrOrganizationService.hasPerson(organizationId)) {
			if (!ehrOrganizationService.hasChildren(organizationId)) {
				boolean result = ehrOrganizationService.deleteOrganization(org);
				if (result) {
					data.put("success", result);
					data.put("msg", "删除成功");
					sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Delete, SystemEnum.Module.Organization, "删除了一个名为"+org.getOrganizationName()+"的部门");
					//日志
					
				} else {
					data.put("success", result);
					data.put("msg", "删除失败");
				}
			} else {
				data.put("success", false);
				data.put("msg", "存在子组织,不能删除");
			}
		} else {
			data.put("success", false);
			data.put("msg", "组织下存在人员,不能删除");
		}
		
		return data;
	}

//	/**
//	 * 组织机构查询
//	 * 
//	 * @param organizationCode
//	 * @param totalOrganizationCode
//	 * @param organizationName
//	 * @param organizationLevel
//	 * @param parentId
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/searchOrganization")
//	public String searchOrganization(String organizationCode,
//			String totalOrganizationCode, String organizationName,
//			Integer organizationLevel, Long parentId,
//			HttpServletRequest request, HttpServletResponse response) {	
//		EhrUser user = RequestUtil.getLoginUser(request);
//		Long companyId = user.getCompanyId();
//		List<EhrOrganization> list = null;
//		try {
//			list = ehrOrganizationService.searchOrganization(organizationCode,
//					totalOrganizationCode, organizationName, organizationLevel,
//					parentId, companyId);
//			request.setAttribute("list", list);
//			request.setAttribute("organizationCode", organizationCode);
//			request.setAttribute("totalOrganizationCode", totalOrganizationCode);
//			request.setAttribute("organizationName", organizationName);
//			request.setAttribute("organizationLevel", organizationLevel);
//			request.setAttribute("parentId", parentId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "organizational/list_structure";
//
//	}

//	@RequestMapping("deleteOrganization")
//	public void deleteByorganizationId(Long organizationId,
//			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> data = new HashMap<String, Object>();
//		try {
//
//			EhrOrganization info = null;
//
//			boolean result = ehrOrganizationService
//					.selectOrganorganizationId(organizationId);
//			if (result) {
////				info = ehrOrganizationService
////						.selectByorganizationId(organizationId);
//				result = ehrOrganizationService.selectOrganparentId(organizationId);
//				if (result) {
//					result = ehrOrganizationService
//							.deleteByorganizationId(organizationId);
//					if (result) {
//						data.put("success", result);
//						data.put("msg", "删除成功");
//					} else {
//						data.put("success", result);
//						data.put("msg", "删除失败");
//					}
//				} else {
//					data.put("success", result);
//					data.put("msg", "存在子组织,不能删除");
//				}
//			} else {
//				data.put("success", result);
//				data.put("msg", "组织下存在人员,不能删除");
//			}
//			response.getWriter().print(JSONArray.fromObject(data).toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@RequestMapping("insertOrganizationHistory")
	public void insertOrganizationHistory(HttpServletRequest request,
			HttpServletResponse response,
			EhrOrganizationHistory history, String organizationCode,
			String totalOrganizationCode, String organizationName,
			Integer organizationLevel, Long parentId, Long companyId) {
		Map<String, Object> data = new HashMap<String, Object>();	
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			history.setCreateUserId(user.getUserId());
			history.setCreateUserName(user.getUserName());
			history.setCompanyId(user.getCompanyId());
			history.setBaselineDate(new Date());
			boolean result = ehrOrganizationService
					.insertOrganizationHistory(history,
							organizationCode, totalOrganizationCode,
							organizationName, organizationLevel, parentId,
							user.getCompanyId());
			if (result) {
				data.put("success", result);
				data.put("msg", "生成基准成功");
			} else {
				data.put("success", result);
				data.put("msg", "生成基准失败");
			}

			response.getWriter().print(JSONArray.fromObject(data).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
