package com.jabava.service.employee;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrContractVO;
import com.jabava.service.system.IBaseDataService;

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

   /**
     * 根据合同类型（劳动合同）和业务类型（签订）取最新的合同记录
     * @param map
     * @return
     */
    Map<String,Object> getContractInfoByPersonID(Map<String, Object> map);
    
    /**
     * 根据personID获取所有的合同信息
     * @param personID
     * @return
     * @throws Exception 
     */
    Map<String, Object> getAllContractInfoByPersonID(HttpServletRequest request,Long personID,IBaseDataService baseDataService) throws Exception ;
}
