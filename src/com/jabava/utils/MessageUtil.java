package com.jabava.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作结果信息公用类
 * 
 * @author 郑长山
 * 
 */
public class MessageUtil {
	/**
	 * 删除失败
	 */
	public static final String DEL_ERROR = "del_error";
	/**
	 * 删除成功
	 */
	public static final String DEL_SUCCESS = "del_success";

	/**
	 * 修改失败
	 */
	public static final String UPD_ERROR = "upd_error";
	/**
	 * 修改成功
	 */
	public static final String UPD_SUCCESS = "upd_success";

	/**
	 * 新增失败
	 */
	public static final String INS_ERROR = "ins_error";
	/**
	 * 新增成功
	 */
	public static final String INS_SUCCESS = "ins_success";

	/**
	 * 参数是空的
	 */
	public static final String IS_NULL = "is_null";

	/**
	 * 数字转换异常
	 */
	public static final String EXC_NUMBER = "exc_number";

	/**
	 * 成功
	 */
	public static final String SUCCESS = "success";
	/**
	 * 失败
	 */
	public static final String ERROR = "error";

	/**
	 * 操作类返回值处理
	 * 
	 * @param value
	 *            错误信息
	 * @return
	 */
	public static Map<String, Object> message(String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", value);
		return map;
	}
	
	
	public static Map<String, Object> successMessage(String msg){
		return message(true, msg);
	}
	
	public static Map<String, Object> errorMessage(String msg){
		return message(false, msg);
	}
	
	/**
	 * 生成结果Map(包含success及msg两个键)
	 * @param success
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> message(boolean success, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("msg", msg);
		return map;
	}
	
	
	public static Map<String, Object> successProviderMessage(String returnMessage){
		return providerMessage("0", returnMessage);
	}
	
	public static Map<String, Object> errorProviderMessage(String returnMessage){
		return providerMessage("1", returnMessage);
	}
	
	/**
	 * 生成结果Map(包含returnCode及returnMessage两个键)
	 * @param returnCode
	 * @param returnMessage
	 * @return
	 */
	public static Map<String, Object> providerMessage(String returnCode, String returnMessage){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("returnCode", returnCode);
		map.put("returnMessage", returnMessage);
		return map;
	}
}
