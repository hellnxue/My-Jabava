package com.jabava.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrMenuMapper;
import com.jabava.pojo.manage.EhrMenu;
import com.jabava.service.system.IMenuService;
import com.jabava.utils.JabavaUtil;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Resource
	private EhrMenuMapper menuMapper;

	@Override
	public List<EhrMenu> searchMenu(EhrMenu menu) throws Exception {
		// TODO Auto-generated method stub
		menu.setMenuName("邮箱参数配置");
		List<EhrMenu> menus = menuMapper.searchMenu(JabavaUtil.formatLikeWildcard(menu.getMenuName()),
				menu.getMenuType(), menu.getMenuUrl(), menu.getParentId());
		return menus;
	}

}
