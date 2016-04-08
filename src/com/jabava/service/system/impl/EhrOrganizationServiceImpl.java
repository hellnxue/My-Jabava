package com.jabava.service.system.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.core.EnumConstents.NodeMoveFlag;
import com.jabava.dao.manage.EhrOrganizationDetailHistoryMapper;
import com.jabava.dao.manage.EhrOrganizationHistoryMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonHistoryMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrOrganizationDetailHistory;
import com.jabava.pojo.manage.EhrOrganizationHistory;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IEhrOrganizationService;
/**
 * 
 * @author panfei
 *
 */
@Service("organizationService")
public class EhrOrganizationServiceImpl implements IEhrOrganizationService {

	@Resource
	private EhrOrganizationMapper orgMapper;
	@Resource
	private EhrOrganizationHistoryMapper historyMapper;
	@Resource
	private EhrOrganizationDetailHistoryMapper detailMapper;
	@Resource
	private EhrPersonHistoryMapper personHistoryMapper;
	
	@Resource
	private EhrPersonMapper personMapper;
	
	@Override
	public List<EhrOrganization> findOrganizationByCompanyId(Long companyId){
		return orgMapper.selectByCompanyId(companyId);
	}
	
	@Override
	public EhrOrganization findTopOrganization(Long companyId){
		return orgMapper.findTopOrganization(companyId);
	}
	
	@Override
	public List<EhrOrganization> loadTree(Long companyId){
		List<EhrOrganization> orgList = orgMapper.selectByCompanyId(companyId);
		Map<Long,EhrOrganization> orgMap = new HashMap<Long,EhrOrganization>();
		for(EhrOrganization org : orgList){
			orgMap.put(org.getOrganizationId(), org);
		}
		
		//按结构组成树，并找出顶层组织
		EhrOrganization top = null;
		for(EhrOrganization org : orgList){
			if(org.getOrganizationLevel() == null || org.getOrganizationLevel() == 0){
				top = org;
			}else{
				org.setParentOrganization(orgMap.get(org.getParentId()));
			}
		}
		
		//递归去掉引用
		List<EhrOrganization> result = new ArrayList<EhrOrganization>();
		this.trimOrganization(top,result);
		
		return result;
	}
	
	private void trimOrganization(EhrOrganization parent,List<EhrOrganization> orgList){
		orgList.add(parent);
		if(parent.getChildren() != null && !parent.getChildren().isEmpty()){
			for(EhrOrganization org : parent.getChildren()){
				trimOrganization(org,orgList);
				org.setParentOrganization(null);
			}
			parent.clearChildren();
		}
	}

	@Override
	public int inserTop(EhrUser user, String companyName) {
		EhrOrganization org = new EhrOrganization();
		org.setCompanyId(user.getCompanyId());
		org.setOrganizationCode(user.getCompanyCode());
		org.setOrganizationName(companyName);
		org.setParentId(0L);
		org.setOrganizationLevel(0);
		org.setSort(0);
		
		Date now = new Date();
		org.setCreateUserId(user.getUserId());
		org.setCreateUserName(user.getUserName());
		org.setCreateDate(now);
		org.setLastModifyUserId(user.getUserId());
		org.setLastModifyUserName(user.getUserName());
		org.setLastModifyDate(now);
		
		return orgMapper.insert(org);
	}

	@Override
	public int insert(EhrOrganization org,EhrOrganization parent){
		List<EhrOrganization> orgList = orgMapper.selectChildren(org.getParentId());
		org.setOrganizationLevel(parent.getOrganizationLevel() + 1);
		if(orgList == null || orgList.isEmpty()){
			org.setSort(1);
		}else{
			EhrOrganization last = orgList.get(orgList.size() - 1);
			org.setSort(last.getSort() + 1);
		}
		
		int result = orgMapper.insert(org);
		return result;
	}

	@Override
	public int update(EhrOrganization org){
		int result = orgMapper.updateByPrimaryKey(org);
		return result;
	}

	@Override
	public boolean move(EhrOrganization source,EhrOrganization target,String moveFlag) throws Exception{
		Integer orginSort = source.getSort();
		Long orginParentId = source.getParentId();
		int result;
		if(NodeMoveFlag.LAST_CHILDREN.getValue().equals(moveFlag)){
			//作为最后一个子节点：获取目标节点的所有子节点
			List<EhrOrganization> children = orgMapper.selectChildren(target.getOrganizationId());
			if(children != null && !children.isEmpty()){
				EhrOrganization last = children.get(children.size() - 1);
				source.setSort(last.getSort() + 1);
			}else{
				source.setSort(1);
			}
			source.setOrganizationLevel(target.getOrganizationLevel() + 1);
			source.setParentId(target.getOrganizationId());
			result = orgMapper.updateByPrimaryKey(source);
			if(result <= 0){
				throw new Exception("移动节点失败");
			}
		}else if(NodeMoveFlag.NEXT_BROTHER.getValue().equals(moveFlag)){
			//作为下一个兄弟节点：获取目标节点父节点的所有子节点
			//修改顺序：target兄弟-->source-->source兄弟
			List<EhrOrganization> brothers = orgMapper.selectChildren(target.getParentId());
			this.updateBrotherLevel(brothers, target.getSort(), true);
			
			source.setOrganizationLevel(target.getOrganizationLevel());
			source.setParentId(target.getParentId());
			source.setSort(target.getSort() + 1);
			result = orgMapper.updateByPrimaryKey(source);
			if(result <= 0){
				throw new Exception("移动节点失败");
			}
		}else if(NodeMoveFlag.PREV_BROTHER.getValue().equals(moveFlag)){
			//作为上一个兄弟节点：获取目标节点父节点的所有子节点
			//修改顺序：target兄弟-->source-->source兄弟
			List<EhrOrganization> brothers = orgMapper.selectChildren(target.getParentId());
			this.updateBrotherLevel(brothers, target.getSort() - 1, true);	//从target开始的所有节点
			
			source.setOrganizationLevel(target.getOrganizationLevel());
			source.setParentId(target.getParentId());
			source.setSort(target.getSort());
			result = orgMapper.updateByPrimaryKey(source);
			if(result <= 0){
				throw new Exception("移动节点失败");
			}
		}else{
			throw new Exception("错误的移动标识");
		}

		//更新source子节点的level
		this.updateTreeParent(source);
		
		//更新source后面兄弟节点的sort
		List<EhrOrganization> sourceBrothers = orgMapper.selectChildren(orginParentId);
		this.updateBrotherLevel(sourceBrothers, orginSort, false);
		
		return true;
	}
	
	/**
	 * source节点后面的节点-1，target节点后面的节点+1
	 */
	private void updateBrotherLevel(List<EhrOrganization> brothers,Integer baseSort,boolean isTarget) throws Exception{
		int result;
		for(EhrOrganization temp : brothers){
			if(temp.getSort() > baseSort){
				temp.setSort(isTarget ? (temp.getSort() + 1) : (temp.getSort() - 1));
				result = orgMapper.updateByPrimaryKey(temp);
				if(result <= 0){
					throw new Exception("移动节点失败");
				}
			}
		}
	}
	
	private void updateTreeParent(EhrOrganization org) throws Exception{
		List<EhrOrganization> children = orgMapper.selectChildren(org.getOrganizationId());
		int result;
		if(children != null && !children.isEmpty()){
			for(EhrOrganization temp : children){
				temp.setOrganizationLevel(org.getOrganizationLevel() + 1);
				result = orgMapper.updateByPrimaryKey(temp);
				if(result <= 0){
					throw new Exception("移动节点失败");
				}
				this.updateTreeParent(temp);
			}
		}
	}
	
	@Override
	public String validateDuplicate(EhrOrganization org) {
		//根据名称编号查找
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", org.getCompanyId());
		params.put("organizationCode", org.getOrganizationCode());
		List<EhrOrganization> orgList = orgMapper.searchOrganization(params);
		if(orgList != null && !orgList.isEmpty()){
			if(org.getOrganizationId() == null || 
					!orgList.get(0).getOrganizationId().equals(org.getOrganizationId())){
				return "部门编号重复";
			}
		}
		
		//根据名称及父ID查找
		params = new HashMap<String,Object>();
		params.put("companyId", org.getCompanyId());
		params.put("organizationName", org.getOrganizationName());
		params.put("parentId", org.getParentId());
		orgList = orgMapper.searchOrganization(params);
		if(orgList != null && !orgList.isEmpty()){
			if(org.getOrganizationId() == null || 
					!orgList.get(0).getOrganizationId().equals(org.getOrganizationId())){
				return "部门名称重复";
			}
		}
		
		return null;
	}

	@Override
	public List<EhrOrganization> getChildren(Long parentId) {
		return orgMapper.selectChildren(parentId);
	}

	/**
	 * 组织下是否存在子组织 
	 */
	@Override
	public boolean hasChildren(Long organizationId) {
		List<EhrOrganization> orgs = 
			orgMapper.selectChildren(organizationId);
		return orgs != null && !orgs.isEmpty();
	}

	/**
	 * 组织下是否存在人员 
	 */
	@Override
	public boolean hasPerson(Long organizationId) {
		List<Long> list = new ArrayList<Long>();
		list.add(organizationId);
		List<EhrPerson> persons = 
			personMapper.selectByOrganizationId(organizationId);
		return persons != null && !persons.isEmpty();
	}
	
	/**
	 * 删除组织机构
	 */
	@Override
	public boolean deleteOrganization(EhrOrganization org) throws Exception {
		int result;
		
		//将排在后面的兄弟节点序号-1
		List<EhrOrganization> brothers = orgMapper.selectChildren(org.getParentId());
		if(brothers != null && !brothers.isEmpty()){
			for(EhrOrganization temp : brothers){
				if(temp.getSort() > org.getSort()){
					temp.setSort(temp.getSort() - 1);
					result = orgMapper.updateByPrimaryKey(temp);
					if(result <= 0){
						throw new Exception("删除组织失败");
					}
				}
			}
		}
		
		//递归获取所有需要删除的节点并删除
		List<EhrOrganization> orgList = new ArrayList<EhrOrganization>();
		this.getTreeByParent(org, orgList);
		for(int i = orgList.size() - 1; i >= 0; i --){
			result = orgMapper.deleteByPrimaryKey(orgList.get(i).getOrganizationId());
			if(result <= 0){
				throw new Exception("删除组织失败");
			}
		}
		
		return true;
	}
	
	public List<EhrOrganization> getTreeByParent(Long parentId){
		List<EhrOrganization> result = new ArrayList<EhrOrganization>();
		EhrOrganization org = orgMapper.selectByPrimaryKey(parentId);
		this.getTreeByParent(org, result);
		return result;
	}
	
	private void getTreeByParent(EhrOrganization parent,List<EhrOrganization> orgList){
		orgList.add(parent);
		List<EhrOrganization> tempList = orgMapper.selectChildren(parent.getOrganizationId());
		if(tempList != null && !tempList.isEmpty()){
			for(EhrOrganization org : tempList){
				this.getTreeByParent(org, orgList);
			}
		}
	}
	
	/**
	 * 组织机构查询
	 */
	@Override
	public List<EhrOrganization> searchOrganization(String organizationCode,  String totalOrganizationCode,  String organizationName,
    		Integer organizationLevel, Long parentId,  Long companyId) throws Exception {
		// TODO Auto-generated method stub
		List<EhrOrganization> list = orgMapper.selectOrgani(organizationCode, totalOrganizationCode, organizationName, organizationLevel, parentId, companyId);
		for(EhrOrganization info : list){
			//查询父组织
			EhrOrganization	parent = orgMapper.selectOrganParentName(info.getParentId());
			info.setParentOrganization(parent);
		}
		return list;
	}
	/**
	 * 是否存在子组织
	 */
	@Override
	public boolean selectOrganparentId(Long parentId) {
		// TODO Auto-generated method stub
		boolean result = false;
		List<EhrOrganization> orgs =	
			orgMapper.selectOrganparentId(parentId);
		result = (orgs.isEmpty());
		return result;
	}
	/**
	 * 组织下是否存在人员 
	 */
	@Override
	public  boolean selectOrganorganizationId(Long organizationId) {
		// TODO Auto-generated method stub
		boolean result = false;
		List<EhrPerson> persons = 
			orgMapper.selectOrganorganizationId(organizationId);
		result = (persons.isEmpty());
		return result;
	}
	
	/**
	 * 查找组织下的所有人员(包含子组织)
	 */
	@Override
	public List<EhrPerson> selectPersonByOrganization(EhrOrganization org){
		List<Long> idList = new ArrayList<Long>();
		
		//如果要减少查询，可以按照loadTree的方式：
		//先加载所有节点，并生成树，然后根据Map主键取出，递归获取所有子节点
		this.getTreeId(org, idList);
		
		List<EhrPerson> result = personMapper.selectByOrgIdList(idList);
		Collections.sort(result, new Comparator<EhrPerson>() {
			Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
			@Override
			public int compare(EhrPerson o1, EhrPerson o2) {
				return cmp.compare(o1.getEmployeeName(),o2.getEmployeeName());
			}
			
		});
		
		return result;
	}

	private void getTreeId(EhrOrganization org,List<Long> idList){
		idList.add(org.getOrganizationId());
		List<EhrOrganization> children = orgMapper.selectChildren(org.getOrganizationId());
		if(children != null && !children.isEmpty()){
			for(EhrOrganization child : children){
				this.getTreeId(child, idList);
			}
		}
	}
	
	/**
	 * 删除组织机构
	 */
	@Override
	public boolean deleteByorganizationId(Long organizationId) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int code = orgMapper.deleteByorganizationId(organizationId);
			result = (1 == code);
		} catch (Exception e) {
			try {
				throw new Exception("删除组织失败.", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 通过organizationId查询
	 */
	@Override
	public EhrOrganization selectByorganizationId(Long organizationId) {
		// TODO Auto-generated method stub
		EhrOrganization org = orgMapper.selectByPrimaryKey(organizationId);
		return org;
	}
	/**
	 * 生成基准
	 */
	@Override
	public boolean insertOrganizationHistory(
			EhrOrganizationHistory history,String organizationCode,  String totalOrganizationCode,  String organizationName,
    		Integer organizationLevel, Long parentId,  Long companyId){
		boolean result = false;
			try {
				List<EhrOrganization> list = null;
				int code = historyMapper.insertSelective(history);
				result = (1 == code);
				list = orgMapper.selectOrgani(organizationCode, totalOrganizationCode, organizationName, organizationLevel, parentId, companyId);
				for(EhrOrganization info : list){
					EhrOrganizationDetailHistory historyDetail = new EhrOrganizationDetailHistory();
					historyDetail.setOrganizationHistoryId(history.getOrganizationHistoryId());
					historyDetail.setOrganizationId(info.getOrganizationId());
					historyDetail.setOrganizationCode(info.getOrganizationCode());
					historyDetail.setTotalOrganizationCode(info.getTotalOrganizationCode());
					historyDetail.setOrganizationName(info.getOrganizationName());
					historyDetail.setOrganizationLevel(info.getOrganizationLevel());
					historyDetail.setParentId(info.getParentId());
					historyDetail.setMemo(info.getMemo());
		    		historyDetail.setCreateUserId(info.getCreateUserId());
		    		historyDetail.setCreateUserName(info.getCreateUserName());
		    		historyDetail.setCreateDate(info.getCreateDate());
		    		historyDetail.setLastModifyUserId(info.getLastModifyUserId());
		    		historyDetail.setLastModifyUserName(info.getLastModifyUserName());
		    		historyDetail.setLastModifyDate(info.getLastModifyDate());
		    		detailMapper.insert(historyDetail);
		    		personHistoryMapper.insertPersonHistory(historyDetail.getOrganizationDetailHistoryId(), historyDetail.getOrganizationId());
					}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;  

	}
}