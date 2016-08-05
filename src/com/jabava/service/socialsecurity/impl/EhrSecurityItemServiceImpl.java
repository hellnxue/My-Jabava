package com.jabava.service.socialsecurity.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.socialsecurity.EhrSecurityItemMapper;
import com.jabava.pojo.socialsecurity.EhrSecurityItem;
import com.jabava.service.socialsecurity.EhrSecurityItemService;

@Service
public class EhrSecurityItemServiceImpl implements EhrSecurityItemService{
	@Resource
	private EhrSecurityItemMapper ehrSecurityItemMapper;

	public List<EhrSecurityItem> getEhrSecurityItemlInfoByPersonId(Long personId) {
		return ehrSecurityItemMapper.selectByPersonId(personId);
	}

	public int saveEhrSecurityItems(List<EhrSecurityItem> list) {
		//ehrSecurityItemMapper.selectByPersonId(personId);
		Long personId = -1L;
		if(list.size() > 0){
			EhrSecurityItem item = list.get(0);
			personId = item.getPersonId();
		}
		
		// 先删除
		ehrSecurityItemMapper.removeSecurityItemByPersonId(personId);
		
		int ret = ehrSecurityItemMapper.addBachSecurityItem(list);
		
		// 再插入
		
		return ret;
	}

}
