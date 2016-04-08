package com.jabava.service.manage.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.service.manage.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Resource
	private EhrCompanyMapper companyMapper;

	@Override
	public boolean addCompany(EhrCompany company) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int code = companyMapper.insertSelective(company);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("新增公司失败.", e);
		}
		return result;
	}

	@Override
	public boolean delCompany(Long id) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int code = companyMapper.deleteByPrimaryKey(id);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("删除公司失败.", e);
		}
		return result;
	}

	@Override
	public boolean updateCompany(EhrCompany company) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int code = companyMapper.updateByPrimaryKeySelective(company);
			result = (code == 1);
		} catch (Exception e) {
			result = false;
			throw new Exception("修改公司失败.", e);
		}
		return result;
	}

	@Override
	public int getMaxCompanyId() throws Exception {
		Integer max = companyMapper.getMaxCompanyId();
		return max == null ? 0 : max;
	}

	@Override
	public EhrCompany selectCompanyById(Long id) throws Exception {
		try {
			return companyMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new Exception("查询公司实体失败.", e);
		}
	}

}
