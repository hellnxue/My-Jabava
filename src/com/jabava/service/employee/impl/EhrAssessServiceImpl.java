package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrAssessMapper;
import com.jabava.pojo.employee.EhrAssess;
import com.jabava.service.employee.EhrAssessService;

@Service("assessService")
public class EhrAssessServiceImpl implements EhrAssessService {
	@Resource
	private EhrAssessMapper assessMapper;

	@Override
	public List<EhrAssess> getByPersonId(Long personId) throws Exception {
		return assessMapper.getByPersonId(personId);
	}

	@Override
	public boolean addAssess(EhrAssess assess) throws Exception {
		boolean result = false;
		try {
			int code = assessMapper.insertSelective(assess);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateAssess(EhrAssess assess) throws Exception {
		boolean result = false;
		try {
			int code = assessMapper.updateByPrimaryKeySelective(assess);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delAssess(Long assessId) throws Exception {
		boolean result = false;
		try {
			int code = assessMapper.deleteByPrimaryKey(assessId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
