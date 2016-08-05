package com.jabava.service.policygroup.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.policygroup.PolicyGroupMapper;
import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.service.policygroup.PolicyGroupService;

@Service
public class PolicyGroupServiceImpl implements PolicyGroupService {
	@Autowired
	private PolicyGroupMapper policyGroupMapper;

	@Override
	public List<PolicyGroup> getPolicyGroupByCityAndType(PolicyGroup policyGroup) {
		return policyGroupMapper.getPolicyGroupByCityAndType(policyGroup);
	}

	@Override
	public List<PolicyGroup> getPolicyGroupByName(String policyGroupName) {
		return policyGroupMapper.getPolicyGroupByName(policyGroupName);
	}

}
