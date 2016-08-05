package com.jabava.utils.constants;

// 通用数据类型
public class CommonDataConstants {
	/** 报表类型 */
	public static final int COMMON_DATA_REPORT_TYPE		= 1;
	/** 数据框类型 */
	public static final int COMMON_DATA_DATABOX_TYPE	= 2;
	/** 证件类型 */
	public static final int COMMON_DATA_CERT_TYPE		= 3;
	/** 短信模板 */
	public static final int COMMON_DATA_MSG_TPL			= 4;
	/** 自定义字段信息项类型 */
	public static final int COMMON_DATA_CUSTOM_DATA_TYPE	= 5;

	
	/** 证件类型-身份证编码 */
	public static final String COMMON_DATA_CERT_TYPE_CERTID  = "1";
	/** 证件类型-其他 */
	public static final String COMMON_DATA_CERT_TYPE_OTHER  = "9";
	
	
	/** 短信模板-注册成功 */
	public static final String COMMON_DATA_MSG_TPL_REGISTER_SUCCESS = "1";
	/** 短信模板-密码重置 */
	public static final String COMMON_DATA_MSG_TPL_RESET_PASSWORD = "2";
	/** 短信模板-密码修改 */
	public static final String COMMON_DATA_MSG_TPL_CHANGE_PASSWORD = "3";
}
