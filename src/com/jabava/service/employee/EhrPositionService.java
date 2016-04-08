package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrPerson;

/**
 * 岗位信息
 *
 * @version $Id: EhrPositionService.java, 
 * v 0.1 2016年2月17日 下午5:43:47 
 * <pre>
 * @author steven.chen
 * @date 2016年2月17日 下午5:43:47 
 * </pre>
 */
public interface EhrPositionService {
	/**
	 * 新增岗位信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月17日 下午5:50:33 
	 * </pre>
	 *
	 * @param ehrPosition
	 * @return
	 */
	public String addPositin(EhrPosition ehrPosition,Long companyId,String reportId);
	/**
	 * 修改岗位信息 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月17日 下午5:55:53 
	 * </pre>
	 *
	 * @param ehrPosition
	 * @return
	 */
	public String updatePosition(EhrPosition ehrPosition);
	/**
	 * 根据用户ID获取岗位信息 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月18日 下午2:37:46 
	 * </pre>
	 *
	 * @param personId
	 * @return
	 */
	EhrPositionVO getEhrPositionByPersonId(Long personId);
	/**
	 * 根据工号  和 公司ID查询该工号是否存在
	 * 如果存在 返回 true 否则返回 false
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月19日 下午2:11:00 
	 * </pre>
	 *
	 * @param personId
	 * @param companyId
	 * @return
	 */
	boolean employeeNumberExist(String jobNumber, Long companyId);
	/**
	 * 根据personId 和 公司ID查询岗位信息	
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月19日 下午2:11:00 
	 * </pre>
	 *
	 * @param personId
	 * @param companyId
	 * @return
	 */
	List<EhrPosition> getPositionByPersonIdAndCompanyId(EhrPerson ehrPerson);
	/**
	 * 判断该员工在不在该公司中 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月19日 下午4:42:18 
	 * </pre>
	 *
	 * @param employeeName
	 * @param companyId
	 * @return
	 */
	boolean reportExist(String employeeName, Long companyId);

}
