package com.jabava.utils.constants;

// 配置数据类型
public class ConfigConstants {
	/** 注册成功邮件模板 */
	public static final String TPL_REGISTER_SUCCESS = "tpl_register_success.html";
	/** 密码重置邮件模板 */
	public static final String TPL_RESET_PASSWORD = "tpl_reset_password.html";
	/** 密码修改邮件模板 */
	public static final String TPL_CHANGE_PASSWORD = "tpl_change_password.html";
	/** 发送工资条邮件模板 */
	public static final String TPL_SALARY_SLIP = "tpl_salary_slip.html";

	/** 注册成功邮件主题 */
	public static final String SUBJECT_REGISTER_SUCCESS = "注册成功通知";
	/** 密码重置邮件主题 */
	public static final String SUBJECT_RESET_PASSWORD = "密码重置成功通知";
	/** 密码修改邮件主题 */
	public static final String SUBJECT_CHANGE_PASSWORD = "密码修改成功通知";
	/** 发送工资条邮件主题 */
	public static final String SUBJECT_SALARY_SLIP = "${employeeName}-${month}工资条 ";

}
