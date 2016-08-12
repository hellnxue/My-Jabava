package com.jabava.controller.appStore;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.appStore.EhrAppStore;
import com.jabava.service.appStore.IAppStoreService;

/**
 * ClassName: AppStoreController
 * @Description: 应用中心控制器
 * @author meng.meng
 * @date 2016年8月9日
 */
@Controller
@RequestMapping("appStore")
public class AppStoreController {
	
	@Resource
	private IAppStoreService appStoreService;
	
	/**
	 * @Description: 进入应用中心页面
	 * @return String  页面路径（视图）
	 * @author meng.meng
	 * @date 2016年8月9日
	 */
	@RequestMapping("to_appStore")
	public String to_appStore() {
		return "appStore/appStore";
	}
	
	/**
	 * @Description: 获取商店中各项应用信息
	 * @return List<EhrAppStore>  返回值（json）
	 * @throws
	 * @author meng.meng
	 * @date 2016年8月9日
	 */
	@RequestMapping("getAppInfo")
	@ResponseBody
	public List<EhrAppStore> getAppInfo() throws Exception{
		return appStoreService.listAppInStore(null);
	}
}
