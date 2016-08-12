/**  
 * @Title: AppStoreServiceImpl.java
 * @Package com.jabava.service.appStore.impl
 * @Description: TODO
 * @author meng.meng
 * @date 2016年8月9日
 */
package com.jabava.service.appStore.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.appStore.EhrAppStoreMapper;
import com.jabava.pojo.appStore.EhrAppStore;
import com.jabava.service.appStore.IAppStoreService;

/**
 * ClassName: AppStoreServiceImpl
 * @Description: 应用中心业务实现类
 * @author meng.meng
 * @date 2016年8月9日
 */
@Service("appStoreService")
public class AppStoreServiceImpl implements IAppStoreService {
	
	@Resource
	private EhrAppStoreMapper ehrAppStoreMapper;

	/* (non-Javadoc)
	 * @see com.jabava.service.appStore.IAppStoreService#listAppInStore(java.util.Map)
	 */
	@Override
	public List<EhrAppStore> listAppInStore(Map<String, Object> paramMap) {
		Map<String, Object> map = new HashMap();
		map.put("parentId", 0);
		map.put("isDeleted", 0);
		return ehrAppStoreMapper.selectAppInStore(map);
	}

}
