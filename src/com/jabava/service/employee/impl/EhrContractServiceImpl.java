package com.jabava.service.employee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jabava.dao.employee.EhrContractMapper;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrContractVO;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrContractService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.constants.BaseDataConstants;
/**
 * 劳动合同
 *
 * @version $Id: EhrContractServiceImpl.java, 
 * v 0.1 2016年3月24日 上午10:05:36 
 * <pre>
 * @author steven.chen
 * @date 2016年3月24日 上午10:05:36 
 * </pre>
 */
@Service("contractService")
public class EhrContractServiceImpl implements EhrContractService {
	@Resource
	private EhrContractMapper contractMapper;

	@Override
	public List<EhrContractVO> getByPersonId(Long personId) throws Exception {
		return contractMapper.getByPersonId(personId);
	}

	@Override
	public boolean updateFalg(Long contractId,Integer flag) throws Exception {
		boolean result = false;
		int code;
		try {
			code = contractMapper.updateFalg(contractId, flag);
			result = (1 == code);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean addContract(EhrContract contract) throws Exception {
		boolean result = false;
		try {
			int code = contractMapper.insertSelective(contract);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateContract(EhrContract contract) throws Exception {
		boolean result = false;
		try {
			int code = contractMapper.updateByPrimaryKeySelective(contract);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delContract(Long contractId) throws Exception {
		boolean result = false;
		try {
			int code = contractMapper.deleteByPrimaryKey(contractId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public Map<String, Object> getContractInfoByPersonID(Map<String, Object> map) {
		return contractMapper.getContractInfoByPersonID(map);
	}

	@Override
	public Map<String, Object> getAllContractInfoByPersonID(HttpServletRequest request,Long personID,IBaseDataService baseDataService)  throws Exception {
		
		EhrUser user = RequestUtil.getLoginUser(request); 
		Map<String, Object> map = new HashMap<String, Object>();
		List<EhrContract> contractList=contractMapper.getAllContractInfoByPersonID(personID);
		
		if(contractList!=null&&contractList.size()>0){
			map.put("contractList", contractList );
			map.put("success", true);
			map.put("msg", "合同信息添加成功！");
			return map;
		} 
		 
//		//合同类型
//		List<EhrBaseData> contractTypeList=null;
//		
//		//业务类型 	
//		List<EhrBaseData> contractBussinessTypeList=null;
//				
//		//合同期限类型
//		List<EhrBaseData> contractDeadlineTypeList=null;
//		try {
//			contractTypeList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CONTRACT_TYPE, null);
//			
//			if(contractTypeList!=null&&contractTypeList.size()>0){
//				map.put("contractTypeList", contractTypeList );
//			} 
//			
//			
//			contractBussinessTypeList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CONTRACT_BUSSINESS_TYPE, null);
//			
//			if(contractBussinessTypeList!=null&&contractBussinessTypeList.size()>0){
//				map.put("contractBussinessTypeList", contractBussinessTypeList );
//			} 
//			
//			
//			contractDeadlineTypeList = baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CONTRACT_DEADLINE_TYPE, null);
//			
//			if(contractDeadlineTypeList!=null&&contractDeadlineTypeList.size()>0){
//				map.put("contractDeadlineTypeList", contractDeadlineTypeList );
//			} 
			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		map.put("success", false);
		map.put("msg", "合同信息添加失败！");
		return map;
	}
}
