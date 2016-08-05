package com.jabava.service.policygroup.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.policygroup.PolicyProdRatioMapper;
import com.jabava.dao.policygroup.SbGroupDetailMapper;
import com.jabava.dao.policygroup.SbGroupMapper;
import com.jabava.pojo.policygroup.PolicyGroup;
import com.jabava.pojo.policygroup.PolicyProdRatio;
import com.jabava.pojo.policygroup.SbGroup;
import com.jabava.service.policygroup.GroupService;

/**
 * 政策组合包
 *
 * @version $Id: GroupServiceImpl.java, 
 * v 0.1 2016年4月19日 下午1:34:55 
 * <pre>
 * @author steven.chen
 * @date 2016年4月19日 下午1:34:55 
 * </pre>
 */
@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private SbGroupMapper groupMapper;
	@Autowired
	private SbGroupDetailMapper groupDetailMapper;
	@Autowired
	private PolicyProdRatioMapper policyProdRatioMapper;

	@Override
	public List<SbGroup> getGroupByCityAndType(PolicyGroup policyGroup) {
		List<SbGroup> list = groupMapper.getGroupByCityAndType(policyGroup);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("policyGroupType", policyGroup.getPolicyGroupType());
		StringBuffer sb = new StringBuffer();
		BigDecimal oneHundred = new BigDecimal(100);
		BigDecimal r = null;
		for(SbGroup sbg : list){
			map.put("sbGroupId", sbg.getId());
			sb.delete(0, sb.length());
			for(Map<String,Object> groupDetail : groupDetailMapper.selectBySbGroupIdList(map)){
				sb.append(" ").append(groupDetail.get("prodName"));
				if(groupDetail.get("ratioId") == null){
					continue;
				}
				
				PolicyProdRatio ratio = policyProdRatioMapper.selectByPrimaryKey((Long)groupDetail.get("ratioId"));
				
				r = (ratio.getCompanyRatio() != null) ? ratio.getCompanyRatio() : BigDecimal.ZERO;
				sb.append(" ").append(r.multiply(oneHundred).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString());
				
				r = (ratio.getIndividualRatio() != null) ? ratio.getIndividualRatio() : BigDecimal.ZERO;
				sb.append("+").append(r.multiply(oneHundred).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString());
			}
			
			sbg.setSummary(sb.toString().substring(1));
		}
		
		return list;
	}

	@Override
	public List<SbGroup> getGroupByName(String groupName) {
		return groupMapper.getGroupByName(groupName);
	}

	@Override
	public String getGroupNamePlus(List<Long> list) {
		return null;
	}

	@Override
	public List<SbGroup> getGroupListByIdList(List<Long> list) {
		return null;
	}

}
