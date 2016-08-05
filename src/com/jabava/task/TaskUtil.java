package com.jabava.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;

public class TaskUtil {
	
//	/**
//	 * 根据HRO证件类型匹配Jabava证件类型
//	 * @param hroCardType
//	 * @return
//	 */
//	public static int transformCardTypeFromHro(String hroCardType){
//		if(StringUtils.isEmpty(hroCardType)){
//			return JabavaEnum.CardType.OTHER.getValue();
//		}
//		
//		//证件类型
//		if("1".equals(hroCardType)){		//身份证
//			return JabavaEnum.CardType.OTHER.getValue();
//		}else if("2".equals(hroCardType)){	//军人证
//			return JabavaEnum.CardType.MILITARY_CARD.getValue();
//		}else if("5".equals(hroCardType)){	//护照
//			return JabavaEnum.CardType.PASSPORT.getValue();
//		}
//		
//		//其它
//		return JabavaEnum.CardType.OTHER.getValue();
//	}
	 
	/**
	 * 根据返回结果拿到json串
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月3日 下午2:27:18 
	 * </pre>
	 *
	 * @param result
	 * @return
	 */
	public  static JSONArray getJSONArray(Map<String, Object> result){
		JSONArray jsonList = null;
		JSONObject resultData = null;
		if(!result.isEmpty()
				&& "0".equals(result.get("resultCode").toString())){
			JSONObject jsonObj = JSONObject.fromObject(result
					.get("resultData"));
			if(jsonObj!=null &&jsonObj.size()>0){
				 resultData = JSONObject.fromObject(result
						.get("resultData"));
				if(resultData!=null &&jsonObj.size()>0){
					jsonList = com.alibaba.fastjson.JSONArray.parseArray(jsonObj.get("entities").toString());
					
					if(jsonList!=null && jsonList.size()>0){
						return jsonList;
					}
				}				 

			}
		}
		return jsonList;
	} 
	/**
	 * 获取定时任务跨度(月份数)
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMonthList(String key) throws Exception{
		String value = JabavaPropertyCofigurer.getProperty(key);
		int num = Integer.parseInt(value);
		List<String> billMonthList = new ArrayList<>();
		Date now = new Date();
		for(int i = num - 1; i > 0; i --){
			Date theMonth = JabavaUtil.addDate(now, Calendar.MONTH, -i);
			billMonthList.add(JabavaUtil.parseDate(theMonth, "yyyyMM"));
		}
		billMonthList.add(JabavaUtil.parseDate(now, "yyyyMM"));
		
		return billMonthList;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(TaskUtil.getMonthList(""));
	}
}
