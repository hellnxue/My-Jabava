package com.jabava.service.manage;

import com.jabava.pojo.manage.EhrCompany;

public interface ICompanyService {

	public boolean addCompany(EhrCompany company) throws Exception;

	public boolean delCompany(Long id) throws Exception;

	public boolean updateCompany(EhrCompany company) throws Exception;

	public int getMaxCompanyId() throws Exception;
	
	EhrCompany selectCompanyById(Long id)throws Exception;
	
}
