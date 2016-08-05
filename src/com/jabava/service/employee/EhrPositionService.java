package com.jabava.service.employee;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;

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
	public String addPositin(EhrPosition ehrPosition,Long companyId,String reportEmployeeName);

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
	EhrPositionVO getEhrPositionByPersonId(Long personId) throws JabavaServiceException;
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
	 * 更新员工的岗位信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年5月6日 上午10:50:48 
	 * </pre>
	 *
	 * @param ehrPosition
	 * @return
	 */
	int updatePersonPosition(EhrPosition ehrPosition);

}
