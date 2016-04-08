package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrProjectMapper;
import com.jabava.pojo.employee.EhrProject;
import com.jabava.service.employee.EhrProjectService;

@Service("projectService")
public class EhrProjectServiceImpl implements EhrProjectService {
	@Resource
	private EhrProjectMapper projectMapper;

	@Override
	public List<EhrProject> getByPersonId(Long personId) throws Exception {
		return projectMapper.getByPersonId(personId);
	}

	@Override
	public boolean addProject(EhrProject project) throws Exception {
		boolean result = false;
		try {
			int code = projectMapper.insertSelective(project);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateProject(EhrProject project) throws Exception {
		boolean result = false;
		try {
			int code = projectMapper.updateByPrimaryKeySelective(project);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
			return result;
	}

	@Override
	public boolean delProject(Long projectId) throws Exception {
		boolean result = false;
		try {
			int code = projectMapper.deleteByPrimaryKey(projectId);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
