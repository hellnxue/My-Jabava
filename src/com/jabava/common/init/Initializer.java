package com.jabava.common.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.pojo.manage.EhrPage;
import com.jabava.service.system.IPageService;
import com.sensorsdata.analytics.javasdk.SensorsDataAPI;

public class Initializer {
	private Logger log = Logger.getLogger(Initializer.class);
	
	private Map<String,EhrPage> pageMap = null;
	private boolean initialized = false;
	
	@Autowired
	private IPageService pageService;
	
	public void init(){
		if(!"1".equals(JabavaPropertyCofigurer.getProperty("collect_data_switch"))){
			log.info("Collect data switch is closed");
			return ;
		}
		
		log.info("Initializer initializing ...");
		
		if(pageMap == null){
			pageMap = new ConcurrentHashMap<String,EhrPage>();
		}else{
			pageMap.clear();
		}
		
		List<EhrPage> pageList = pageService.queryPageList(new HashMap<String,Object>());
		for(EhrPage page : pageList){
			if(!StringUtils.isEmpty(page.getPagePath())){
				pageMap.put(page.getPagePath(), page);
			}
		}
		
		if(!this.initialized){
			this.startCollector();
		}
		
		log.info("Initializer initialized.");
	}
	
	public void startCollector(){
		// 从 Sensors Analytics 获取的数据接收的 URL
	    final String SA_SERVER_URL = "http://jabava.cloud.sensorsdata.cn:8006/sa?token=18d9e27b517ad665";
	    // Debug 模式选项：
	    //   SensorsDataAPI.DebugMode.DEBUG_OFF - 关闭 Debug 模式
	    //   SensorsDataAPI.DebugMode.DEBUG_ONLY - 打开DEBUG模式，但该模式下发送的数据仅用于调试，不进行数据导入
	    //   SensorsDataAPI.DebugMode.DEBUG_AND_TRACK - 打开DEBUG模式，并将数据导入到 Sensors Analytics 中
	    final SensorsDataAPI.DebugMode SA_DEBUG_MODE = SensorsDataAPI.DebugMode.DEBUG_AND_TRACK;

	    // 初始化 Sensors Analytics SDK
	    SensorsDataAPI.sharedInstanceWithServerURL(SA_SERVER_URL, SA_DEBUG_MODE);
	    
	    this.initialized = true;
	}
	
	public void stopCollector(){
		// 结束 Sensors Analytics SDK 所有服务
	    SensorsDataAPI.sharedInstance().shutdown();
	}

	public Map<String, EhrPage> getPageMap() {
		if(this.pageMap == null){
			this.init();
		}
		return pageMap;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
