package com.jabava.service.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

public interface EhrPersonService {

	public static List<String> errorMsg = new ArrayList<String>();

	public List<Map<String, Object>> searchPerson(Map<String, Object> map,EhrUser user,
			Integer start, Integer length, String search, String order,
			String according, int isNumeric, Page<Map<String, Object>> page)
			throws Exception;
	/**
	 *  获取所有在职的，手机号码不是空的职员工信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月26日 下午1:39:16 
	 * </pre>
	 *
	 * @return
	 */
	public List<EhrPerson> getAllPersonForSync();

	public List<EhrBaseData> searchBaseData() throws Exception;

	Map<String, Object> addPerson(EhrPerson person, EhrUser user)
			throws Exception;

	public int searchPositive(int day, Long companyId, String distinguish,
			Long userId) throws Exception;

	public int searchContract(int day, int flag, Long companyId,
			String distinguish, Long userId) throws Exception;

	public int searchBirth(int day, Long userId, Long companyId,
			String distinguish) throws Exception;

	public List<EhrPerson> searchBirthList(int day, Long userId,
			Long companyId, String distinguish) throws Exception;

	public List<EhrPerson> searchPositiveList(int day, Long companyId,
			String distinguish, Long userId) throws Exception;

	public List<EhrContract> searchContractList(int day, int flag,
			Long companyId, String distinguish, Long userId) throws Exception;

	EhrPerson getByPersonId(Long personId) throws Exception;
	
	EhrPerson getByJobNumber(Long companyId, String jobNumber);
	
	List<EhrPerson> getBySearch(Long companyId, String search);

	public String importPerson(CommonsMultipartFile multipartFile, EhrUser user)
			throws Exception;

	public List<EhrPerson> selectAllPerson(Long companyId) throws Exception;

	public List<EhrPerson> selectPersonByParam(Map<String,Object> params) throws Exception;

	public void exportPerson(List<EhrPerson> persons,
			EhrUser user, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	boolean deletePerson(Long personId) throws Exception;
	
	Map<String, Object> updatePerson(HttpServletRequest request, EhrPerson person, EhrUser user) throws Exception;
	
	public Long getPersonId(Long userId);
	/**
	 * 根据company_id查询该公司下的所有在职员工(带员工姓名的模糊查询 )
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午3:21:22 
	 * </pre>
	 *
	 * @param ehrPerson
	 * @return
	 */
	public List<EhrPerson>  getEmployeesByCompanyId(EhrPerson ehrPerson);
	/**
	 * 根据身份证号码查询员工信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午4:39:26 
	 * </pre>
	 *
	 * @param cardId
	 * @return
	 */
	public EhrPerson getEmployeeByCardId(String cardId);
	/**
	 * 根据身份证号码查询员工信息 ，供微信使用
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月27日 下午1:58:55 
	 * </pre>
	 *
	 * @param cardId
	 * @return
	 */
	Map<String, Object> getEmployeeInfoByCardId(String cardId);
	/**
	 * 更新员工信息数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月6日 上午10:05:41 
	 * </pre>
	 *
	 * @param person
	 * @return
	 */
	int updatePerson(EhrPerson person);
	
	public Map<String,Object> queryHintInfo(Map<String,Object> params);
	/***
	 * 给手机和邮箱发送信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月8日 下午3:24:05 
	 * </pre>
	 *
	 * @param personId
	 * @param user
	 * @return
	 * @throws Exception
	 */
	String sendMessage(Long personId, EhrUser user) throws Exception;
	
	List<EhrPerson> selectByAuthorisedOrgIdList(List<Long> orgIdList);
	/**
	 * 根据personIdList获取员工列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月24日 上午11:13:54 
	 * </pre>
	 *
	 * @param idList
	 * @return
	 */
	public List<EhrPerson> findPersonByIdList(List<Long> idList);
	
	/**
	 * 根据N个工号和companyId查询人员列表
	 * @param params
	 * @return
	 */
	public List<EhrPerson> selectByCompanyIdJobNumberList(Map<String, Object> params);
	
	 /**
     * 根据personId获取姓名，手机，岗位，部门信息
     * @param personId
     * @return
     */
     Map<String, Object>  getBasicPersonInfoByPersonId(Long personId);
}
