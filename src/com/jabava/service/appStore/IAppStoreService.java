/**  
 * @Title: IAppStoreService.java
 * @Package com.jabava.service.appStore
 * @Description: TODO
 * @author meng.meng
 * @date 2016年8月9日
 */
package com.jabava.service.appStore;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.appStore.EhrAppStore;

/**
 * ClassName: IAppStoreService
 * @Description: 应用中心服务类
 * @author meng.meng
 * @date 2016年8月9日
 */
public interface IAppStoreService {
	
	/**
	 * @Description: 获取所有有效在店应用
	 * @param @param paramMap 请求参数
	 * @return List<EhrAppStore>  应用信息
	 * @author meng.meng
	 * @date 2016年8月9日
	 */
	List<EhrAppStore> listAppInStore(Map<String, Object> paramMap);
}
