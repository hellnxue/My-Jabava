package com.jabava.service.system;

import java.util.List;

import com.jabava.pojo.manage.EhrMenu;

public interface IMenuService {
	
	public List<EhrMenu> searchMenu(EhrMenu menu) throws Exception;

}
