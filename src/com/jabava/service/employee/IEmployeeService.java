package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.manage.EhrPerson;
import com.jabava.utils.Page;

public interface IEmployeeService {

	public List<EhrPerson> searchAddress(Long CompanyId,String search,Page<EhrPerson> page,String orderBy,String billYm);
	
	public List<EhrPerson> searchPerson(EhrPerson person,Long userId,long companyid,String distinguish);
	
	public List<EhrPerson> searchByOrganizationId(Long organizationId);
	
	public EhrPerson searchPersonByJobNumber(Long companyId, String jobNumber);
	
	public EhrPerson searchPersonByCertId(Long companyId, String certId);
	
	/**
	 * 根据员工姓名和公司ID查询员工信息 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年2月19日 下午6:50:34 
	 * </pre>
	 *
	 * @param companyId
	 * @param employeeName
	 * @return
	 */
	List<EhrPerson> searchPersonByEmployeeNameAndCompanyId(Long companyId,
			String employeeName);
}
