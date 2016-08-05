package com.jabava.service.employee.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrDimissionService;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.utils.enums.PersonEnum;
/**
 * 离职管理
 *
 * @version $Id: EhrLeftServiceImpl.java, 
 * v 0.1 2016年3月25日 上午10:49:28 
 * <pre>
 * @author steven.chen
 * @date 2016年3月25日 上午10:49:28 
 * </pre>
 */
@Service
public class EhrDimissionServiceImpl implements EhrDimissionService {
	@Resource
	private EhrDimissionMapper ehrDimissionMapper;
	@Resource
	private EhrPersonService  ehrPersonService;
	@Resource
	private EhrJobpostService jobpostService;

	@Override
	public List<EhrDimission> getByPersonId(Long personId) throws Exception {
		List<EhrDimission> list =	ehrDimissionMapper.getDimissionByPersonId(personId);		
		return list;
	}

	@Override
	public Map<String, Object> addDimission(EhrDimission dimission, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		dimission.setCreateDate(new Date());
		dimission.setCreateUserId(user.getUserId());
		dimission.setCreateUserName(user.getUserName());
		dimission.setLastModifyDate(new Date());
		dimission.setLastModifyUserId(user.getUserId());
		dimission.setLastModifyUserName(user.getUserName());
		
		
		int i=ehrDimissionMapper.insertSelective(dimission);
		if(i>0){
			int j=updatePersonDimission(dimission);//更新person的状态
			
			//处理任职记录-离职
			boolean flag=jobpostService.HandleRecordByPersonId( dimission.getPersonId(), user,dimission.getDimissionDate(),String.valueOf(PersonEnum.PostChangeType.Dimission.getValue()),null);
			if(!flag){
				Log.info("任职记录处理失败...");
			}
			
			map.put("success", true);
			map.put("msg", "添加离职信息成功！");
			return map;
		}
		
		map.put("success", false);
		map.put("msg", "添加离职信息失败！");
		
		return map;
	}

	@Override
	public Map<String, Object> updateDimission(EhrDimission left, EhrUser user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		left.setLastModifyDate(new Date());
		left.setLastModifyUserId(user.getUserId());
		left.setLastModifyUserName(user.getUserName());
		ehrDimissionMapper.updateByPrimaryKeySelective(left);
		EhrDimission dimi = ehrDimissionMapper.selectByPrimaryKey(left.getDimissionId());
		left.setPersonId(dimi.getPersonId());
		updatePersonDimission(left);//更新person的状态
		map.put("success", true);
		map.put("msg", "修改离职信息成功！");
		return map;
	}

	@Override
	public Map<String, Object> deleteDimission(Long leftId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(ehrDimissionMapper.updateDeletedByPrimaryKey(leftId)==1){
			map.put("success", true);
			map.put("msg", "删除离职信息成功！");
		}else{
			map.put("error", false);
			map.put("msg", "删除失败！");
		}
		
		return map;
	}
	private  int updatePersonDimission(EhrDimission dimission){
		EhrPerson po = new EhrPerson();
		po.setPersonId(dimission.getPersonId());
		if(dimission.getDimissionDate()!=null){
			po.setLeftDate(dimission.getDimissionDate());//离职时间
		}
		if(dimission.getDimissionCause()!=null){
			po.setLeftCause(dimission.getDimissionCause().toString());//离职原因
		}
		po.setStatus(2);//状态为离职状态
		po.setLastModifyDate(dimission.getLastModifyDate());
		po.setLastModifyUserId(dimission.getLastModifyUserId());
		po.setLastModifyUserName(dimission.getLastModifyUserName());
		return ehrPersonService.updatePerson(po);
	}

}
