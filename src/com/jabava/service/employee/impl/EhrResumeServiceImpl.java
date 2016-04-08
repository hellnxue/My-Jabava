package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrResumeMapper;
import com.jabava.pojo.employee.EhrResume;
import com.jabava.service.employee.EhrResumeService;

@Service("resumeService")
public class EhrResumeServiceImpl implements EhrResumeService {
	@Resource
	private EhrResumeMapper resumeMapper;

	@Override
	public List<EhrResume> getByPersonId(Long personId) throws Exception {
		return resumeMapper.getByPersonId(personId);
	}

	@Override
	public boolean addResume(EhrResume resume) throws Exception {
		boolean result = false;
		try {
			int code = resumeMapper.insertSelective(resume);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean updateResume(EhrResume resume) throws Exception {
		boolean result = false;
		try {
			int code = resumeMapper.updateByPrimaryKeySelective(resume);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delResume(Long resumeId) throws Exception {
		boolean result = false;
		try {
			int code = resumeMapper.deleteByPrimaryKey(resumeId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
