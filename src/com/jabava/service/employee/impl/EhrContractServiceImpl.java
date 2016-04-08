package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrContractMapper;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.employee.EhrContractVO;
import com.jabava.service.employee.EhrContractService;
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
}
