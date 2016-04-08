package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrContractVO;

public interface EhrContractService {
	/**
	 * 根据personId 获取劳动合同列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月24日 上午10:06:12 
	 * </pre>
	 *
	 * @param personId
	 * @return
	 * @throws Exception
	 */
	List<EhrContractVO> getByPersonId(Long personId) throws Exception;
	
	boolean updateFalg(Long contractId, Integer flag) throws Exception;

	boolean addContract(EhrContract contract) throws Exception;
	
	boolean updateContract(EhrContract contract) throws Exception;
	
	boolean delContract(Long contractId) throws Exception ;

}
