/**
 * 
 */
package com.jabava.service.hro.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.IHroPactInfoService;

/**
 * @author WangYongqiang
 * 
 */

@Service("pactInfoService")
public class HroPactInfoServiceImpl implements IHroPactInfoService {

	@Resource
	private HroPactInfoMapper pactMapper;

	@Override
	public HroPactInfo selectPactById(Long id) throws Exception {
		// TODO Auto-generated method stub
		HroPactInfo pact = pactMapper.selectByPrimaryKey(id);
		return pact;
	}

	@Override
	public List<HroPactInfo> queryPactInfoList() {
		return pactMapper.queryPactInfoList();
	}
	
}
