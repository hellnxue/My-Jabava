package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrRelationMapper;
import com.jabava.pojo.employee.EhrRelation;
import com.jabava.service.employee.EhrRelationService;

@Service("relationService")
public class EhrRelationServiceImpl implements EhrRelationService {
	@Resource
	private EhrRelationMapper relationMapper;

	@Override
	public List<EhrRelation> getByPersonId(Long personId) throws Exception {
		return relationMapper.getByPersonId(personId);
	}

	@Override
	public boolean addRelation(EhrRelation relation) throws Exception {
		boolean result = false;
		try {
			int code = relationMapper.insertSelective(relation);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateRelation(EhrRelation relation) throws Exception {
		boolean result = false;
		try {
			int code = relationMapper.updateByPrimaryKeySelective(relation);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delRelation(Long relationId) throws Exception {
		boolean result = false;
		try {
			int code = relationMapper.deleteByPrimaryKey(relationId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
