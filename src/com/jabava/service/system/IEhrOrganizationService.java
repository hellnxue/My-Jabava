package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrOrganizationHistory;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;

/**
 * 
 * @author panfei
 *
 */
public interface IEhrOrganizationService {
	
	public List<EhrOrganization> loadTree(Long companyId);
	
	public List<EhrOrganization> loadAuthorisedTree(Long companyId);
	
	public int inserTop(EhrUser user, String companyName);
	
	public int insert(EhrOrganization org,EhrOrganization parent);
	
	public int update(EhrOrganization org);
	
	public boolean move(EhrOrganization source,EhrOrganization target,String moveFlag) throws Exception;
	
	public List<EhrOrganization> getChildren(Long parentId);
	
	public String validateDuplicate(EhrOrganization org);
	
	public List<EhrOrganization> getTreeByParent(Long parentId);
	
	public EhrOrganization findTopOrganization(Long companyId);
	
	public List<EhrOrganization> findByLevel(Long companyId, Integer level);
	
	public List<EhrOrganization> getHierarchicalAncestors(Map<Long, EhrOrganization> orgMap, Long organizationId);
	
	/**
	 * 组织机构查询
	 * @param organizationCode
	 * @param totalOrganizationCode
	 * @param organizationName
	 * @param organizationLevel
	 * @param parentId
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	 List<EhrOrganization> searchOrganization(String organizationCode,  String totalOrganizationCode,  String organizationName,
	    		Integer organizationLevel, Long parentId,  Long companyId)throws Exception;

	/**
	    * 组织下是否存在子组织
	    * @param organizationId
	    * @return
	    */
	 boolean hasChildren(Long organizationId)throws Exception;

	/**
	    * 组织下是否存在人员 
	    * @param organizationId
	    * @return
	    */
	 boolean hasPerson(Long organizationId)throws Exception;
	   
	   /**
	    * 删除组织
	    * @param organizationId
	    * @return
	    */
     boolean deleteOrganization(EhrOrganization org)throws Exception;
	  /**
	    * 是否存在子组织 
	    * @param parentId
	    * @return
	    */
	 boolean selectOrganparentId(Long parentId)throws Exception;
	   /**
	    * 组织下是否存在人员 
	    * @param organizationId
	    * @return
	    */
	 boolean selectOrganorganizationId(Long organizationId)throws Exception;
	 
	 public List<EhrPerson> selectPersonByOrganization(Long organizationId);
	   
	   /**
	    * 删除组织
	    * @param organizationId
	    * @return
	    */
     boolean deleteByorganizationId(Long organizationId)throws Exception;
	   
	   
	   /**
	    * 通过organizationId查询
	    * @param organizationId
	    * @return
	    */
	   EhrOrganization selectByorganizationId(Long organizationId);
	   /**
	    * 生成基准
	    * @param historyInfo
	    * @return
	    * @throws Exception
	    */
	boolean insertOrganizationHistory(
			EhrOrganizationHistory history, String organizationCode,
			String totalOrganizationCode, String organizationName,
			Integer organizationLevel, Long parentId, Long companyId);
	/**
	 * 获取公司下所有事业部
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 下午4:59:22 
	 * </pre>
	 *
	 * @param companyId
	 * @return
	 */
	List<EhrOrganization> findOrganizationByCompanyId(Long companyId);
	
	Map<Long, EhrOrganization> findOrganizationMap(Long companyId);
	
	/**
	 * 查询该部门下的人员，不包含子部门
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月31日 下午1:46:06 
	 * </pre>
	 *
	 * @param organizationId
	 * @return
	 */
	List<EhrPerson> findPersonByOrganizationId(Long organizationId);

}
