package com.jabava.controller.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jabava.pojo.tools.EhrCity;
import com.jabava.pojo.tools.EhrProvince;
import com.jabava.service.tools.*;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.ZimuSort;

/**
 * 社保计算器
 * @author xueping.liu
 *
 */
@Controller
public class CalculatorController {
	
	@Autowired
	private EhrProvinceService  ehrProvinceService;
	
	@Autowired
	private EhrCityService  ehrCityService;
	
	/**
	 * 社保计算器页面入口
	 */
	@RequestMapping("tools/calculator")
	public String toCalculator(){
		
		return "tools/calculator";
	}
	
	
	/**
	 * 获取省份
	 */
	@RequestMapping("tools/getProvinceInfo")
	@ResponseBody
	public Map getProvinceAndCity(){
		String  results= "{\"state\":\"获取省份失败！\"}";
		List<HashMap<String, Object>> list=null;
		
	    EhrProvince ehrProvince=new EhrProvince();
	    
	    list=ehrProvinceService.getAllProvince( ehrProvince);
	    
	    HashMap<String, Object> rs=null;
		if(list!=null&&list.size()>0){
			
			ZimuSort zimuSort = new ZimuSort();
			rs=zimuSort.sort12(list,"PROVINCE_NAME");
			
			return rs;   	
		}
		rs.put("state", "获取省份失败！");
		return rs;
	}
	
	/**
	 * 根据省份获取城市列表
	 */
	@RequestMapping("tools/getCityInfo")
	@ResponseBody
	public Map getCityByProvinceId( Long provinceId){
		
		String  results= "获取城市失败！";
		List<HashMap<String, Object>> list=null;
		HashMap<String, Object> rs=null;
	    EhrCity ehrCity=new EhrCity();
	    ehrCity.setProvinceId(provinceId);
	    
	    list=ehrCityService.getCityByProvinceId( ehrCity);
	    
		if(list!=null&&list.size()>0){
			
			ZimuSort zimuSort = new ZimuSort();
			rs=zimuSort.sort13(list,"PINYIN_CODE","CITY_NAME");
			return rs;   	
		}
		rs.put("state", "获取城市失败！");
		return rs;
	}
	
	/**
	 * 根据城市查询政策包信息
	 */
	@RequestMapping("tools/getPolicyInfo")
	@ResponseBody
	public Map<String, Object> getPolicyByCityId( Long cityId){
		
		String  results= "获取政策信息失败！";
//		Map<String, Object> data = new HashMap<String, Object>();
//		Map<String, Object> result=null;
//		data.put("cityId", "");
		
		/*try {
			result=HROFetchService.callHRO("0f9efee4-303e-11e5-8800-f39b0ce86986", data, "querySbGroupByCityId");
		} catch (Exception e) {
			 
			e.printStackTrace();
		}*/
		

		//String server ="http://172.16.0.12:8087";
		String server="http://hro.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(server + "/open/authorize", "dayhr","0f9efee4-303e-11e5-8800-f39b0ce86986");
		
		HROFetchService requestService = new HROFetchService(server + "/open/rest", fetchToken);
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("cityId", cityId);
		 
		System.out.println("data"+data);
		Map<String, Object> result=null;
		
		try {
			result = requestService.invoke("querySbGroupByCityId", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		// results=JSON.toJSONString(result);
		 
		//System.out.println("hello world"+results);
		
	 
		
		return result;
	}
	
	 
	@RequestMapping("tools/hh")
	 
	public String hh(HttpServletRequest request){
		
		String  results= "ddd！";
		 
		
		
	   System.out.println("results="+request.getParameter("xtid"));
		
		return "tools/calculator";
	}
}
