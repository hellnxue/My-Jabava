package com.jabava.service.socialsecurity;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;

public interface EhrPersonSecurityProfileService {
	
	/**
	 * 分页取得员工社保档案列表
	 * @param map 查询参数在这里
	 * @return
	 */
	public List<Map<String,Object>> searchSecurityProfilePage(Map <String,Object> map);
	
	/**
	 * 依据ID查询员工社保档案
	 * @param profileId
	 * @return
	 */
	public EhrPersonSecurityProfile getSecurityProfileById(Long profileId);
	
	/**
	 * 设置或者保存对象
	 * @param record
	 * @return
	 */
	public EhrPersonSecurityProfile saveOrUpdate(EhrPersonSecurityProfile record);
	
	/**
	 * 更新数据对象
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(EhrPersonSecurityProfile record);
	
	/**
	 * 检测是否有社保档案
	 * @param personId
	 * @return
	 */
	public boolean exists(Long personId);
	
	/**
	 * 依据当前日期将符合条件的社保档案转变更为在缴和停缴状态
	 * @return
	 */
	public int updateRecordStatus();
	
	public List <Map <String,Object>> getAllProductsByPersonId(Long personId);
	/**
	 * 查询社保或者公积金账户为在缴状态的社保档案
	 * securityOrgAccount 可以为空
	 * gongjijinOrgAccount  可以为空
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月29日 上午11:09:35 
	 * </pre>
	 *
	 * @param securityOrgAccount
	 * @param gongjijinOrgAccount
	 * @return
	 */
	public List<EhrPersonSecurityProfile> getSecurityProfileByOrgAccount(Long securityOrgAccount,Long gongjijinOrgAccount);
	
	/**
	 * 查询社保档案总数
	 * @return
	 */
	public Long selectProfileTotalCount(Long companyId);
	
	public List<Map<String, Object>> loadPaymentBillPersonHeader(Map<String,Object> params, boolean withCount);
	
	public List<Map<String, Object>> listPaymentBillPerson(Map<String,Object> params);
	

	/**
	 *  员工社保档案导入
	 * @param multipartFile上传文件
	 * @param user 登录用户
	 * @param securityOrgAccount 社保账户
	 * @param securityType  	 社保参保类型
	 * @param gongjijinOrgAccount 公积金账户
	 * @param gongjijinType		  公积金参保类型
	 * @return
	 * @throws Exception
	 */
	public String personSecurityProfileImport(CommonsMultipartFile multipartFile, EhrUser user,Long securityOrgAccount,Long securityType,Long gongjijinOrgAccount,int gongjijinType)
			throws Exception;
	   /**
     * 根据companyId查询员工社保档案表里的所有personId
     * @param companyId
     * @return
     */
    public List<Map<String,Object>> getAllPersonIdByCompanyId(Long companyId);
    
    /**
     * 根据personId集合查询修改的profile_id
     * @param list
     * @return
     */
    public List<EhrPersonSecurityProfile> selectprofileIdListByPersonIdList(List<Long> list);
    
    /**
     * 批量插入
     */
    public int  insertBachForSecurityProfile(List<EhrPersonSecurityProfile> list);
    
    /**
     * 批量修改
     */
    public int  updateBachForSecurityProfile(List<EhrPersonSecurityProfile> list);
    
    /**
	 * 依据ID查询员工社保档案信息
	 * @param profileId
	 * @return
	 */
	public Map<String,Object> getSecurityProfileInfoByPersonId(Long personId);
	
	/**
	 * 新增社保档案信息
	 * @param record
	 * @return
	 */
	Map<String,Object> insertSelective(EhrPersonSecurityProfile record);
	/**
	 * 修改社保档案信息
	 * @param record
	 * @return
	 */
	Map<String,Object> updateByPrimaryKeySelective (EhrPersonSecurityProfile record);
}
