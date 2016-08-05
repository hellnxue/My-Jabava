package com.jabava.service.manage;

import java.util.List;

import com.jabava.pojo.manage.EhrCompany;

public interface ICompanyService {

	public boolean addCompany(EhrCompany company) throws Exception;

	public boolean delCompany(Long id) throws Exception;

	public boolean updateCompany(EhrCompany company) throws Exception;

	public int getMaxCompanyId() throws Exception;
	
	EhrCompany selectCompanyById(Long id)throws Exception;
	/**
	 * 根据用户Code  获取公司信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月20日 下午2:51:56 
	 * </pre>
	 *
	 * @param userName
	 * @return
	 */
	public EhrCompany getCompanysByUserName(String userName);
	
	public List<EhrCompany> getAllCompany(boolean withPlatform);
	
}
