package com.jabava.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jabava.pojo.salary.EhrSalaryChangeDefItem;

public final class SalaryHelper {
	public static Map<String,EhrSalaryChangeDefItem> SALARY_CONSTANT_COMMON = new LinkedHashMap<String,EhrSalaryChangeDefItem>();
	public static Map<String,EhrSalaryChangeDefItem> SALARY_CONSTANT_PERSON = new LinkedHashMap<String,EhrSalaryChangeDefItem>();
	
	static{
		SALARY_CONSTANT_COMMON.put("计薪天数",new EhrSalaryChangeDefItem("计薪天数", "PayDayCount", 2));
		SALARY_CONSTANT_COMMON.put("薪资月度",new EhrSalaryChangeDefItem("薪资月度", "Monthly",1));
		
//		organization_idbigint(20) NULL部门
//		post_idbigint(20) NULL岗位
//		cost_idbigint(20) NULL成本中心
//		job_numbervarchar(20) NULL工号
//		employee_namevarchar(30) NULL员工姓名
//		employee_photovarchar(50) NULL
//		employment_typevarchar(50) NULL用工性质
//		rank_idbigint(20) NULL职级
//		key_persontinyint(4) NULL0为非核心员工，1为核心员工
//		sextinyint(4) NULL0为男性,1为女性
//		report_objectvarchar(20) NULL汇报对象
//		entry_datedate NULL入职日期
//		positive_datedate NULL转正时间
//		in_company_timetinyint(4) NULL司龄
//		firstjob_datedate NULL参加工作时间
//		birth_datedate NULL出生日期
//		ageint(11) NULL年龄
//		cert_typetinyint(4) NULL0为身份证,1为军官证,2为护照,3为其他
//		cert_idvarchar(30) NULL身份证号
//		bank_namevarchar(60) NULL开户银行
//		salary_cardvarchar(30) NULL工资卡号
//		maritaltinyint(4) NULL0为未婚,1为已婚,2为离异
//		degreevarchar(20) NULL最高学历
//		educationvarchar(20) NULL最高学位
//		study_abroadtinyint(4) NULL0代表没有海外留学经历,1代表有海外求学经历
//		work_locationvarchar(30) NULL工作地点
//		payroll_locationvarchar(30) NULL发薪地
//		register_locationvarchar(50) NULL户口所在地
//		social_security_locationvarchar(30) NULL社保缴纳地
//		register_typetinyint(4) NULL0为城镇户口,1为农业户口
//		birth_placevarchar(30) NULL出生地
//		nationalityvarchar(20) NULL民族
//		mobilevarchar(20) NULL手机
//		phonevarchar(20) NULL联系电话
//		Emailvarchar(50) NULL电子邮箱
//		contact_addressvarchar(100) NULL通讯地址
//		family_addressvarchar(100) NULL家庭住址
//		post_codevarchar(10) NULL邮政编码
//		is_payroll_flagtinyint(4) NULL0未停发,1停发
//		file_locationvarchar(100) NULL档案存放处
//		is_foreigntinyint(4) NULL0非外籍,1外籍
//		left_datedate NULL离职时间
//		left_causevarchar(200) NULL离职原因
//		expire_datedate NULL培训服务到期时间
//		memovarchar(250) NULL备注
//		create_user_idbigint(20) NULL
//		create_user_namevarchar(100) NULL
//		create_datedatetime NULL
//		last_modify_user_idbigint(20) NULL
//		last_modify_user_namevarchar(100) NULL
//		last_modify_datedatetime NULL
//		statustinyint(4) NOT NULL0 代表在职，1代表离职，2代表再入职
//		employee_groupvarchar(10) NULL员工组
//		teambigint(20) NULL团队
//		is_workplacetinyint(4) NULL工作地是否是户口所在地,0代表不是，1代表是
//		resumevarchar(100) NULL存放简历
//		subbankvarchar(100) NULL开户支行
//		recruitvarchar(100) NULL招聘渠道
//		person_passwordvarchar(100) NULL
//		origin_placevarchar(100) NULL籍贯
//		is_deletedint(11) NOT NULL删除标志
		//SALARY_CONSTANT_PERSON.put("用工性质",new EhrSalaryChangeDefItem("用工性质", "EmploymentType",1));
		SALARY_CONSTANT_PERSON.put("司龄",new EhrSalaryChangeDefItem("司龄", "InCompanyTime",2));
		SALARY_CONSTANT_PERSON.put("年龄",new EhrSalaryChangeDefItem("年龄", "Age",2));
		//SALARY_CONSTANT_PERSON.put("最高学历",new EhrSalaryChangeDefItem("最高学历", "Degree",1));
		//SALARY_CONSTANT_PERSON.put("最高学位",new EhrSalaryChangeDefItem("最高学位", "Education",1));
		//SALARY_CONSTANT_PERSON.put("工作地点",new EhrSalaryChangeDefItem("工作地点", "WorkLocation",1));
		SALARY_CONSTANT_PERSON.put("民族",new EhrSalaryChangeDefItem("民族", "Nationality",1));
	}
	
	public static String salaryConstantCommonToString(){
		StringBuffer sb = new StringBuffer();
		for(String key : SALARY_CONSTANT_COMMON.keySet()){
			sb.append("、").append(key);
		}
		
		return sb.length() == 0 ? "" : sb.toString().substring(1);
	}
	
	public static String salaryConstantPersonToString(){
		StringBuffer sb = new StringBuffer();
		for(String key : SALARY_CONSTANT_PERSON.keySet()){
			sb.append("、").append(key);
		}
		
		return sb.length() == 0 ? "" : sb.toString().substring(1);
	}
	
	public static List<EhrSalaryChangeDefItem> getAttendanceDefination() {
		List<EhrSalaryChangeDefItem> list = new ArrayList<EhrSalaryChangeDefItem>();
		list.add(new EhrSalaryChangeDefItem("迟到次数","late_times",2));
		list.add(new EhrSalaryChangeDefItem("早退次数","leave_early_times",2));
		list.add(new EhrSalaryChangeDefItem("旷工次数","absent_time",2));
		list.add(new EhrSalaryChangeDefItem("事假天数","all_leave",2));
		list.add(new EhrSalaryChangeDefItem("病假天数","sick_leave",2));
		list.add(new EhrSalaryChangeDefItem("出差天数","business",2));
		list.add(new EhrSalaryChangeDefItem("年假天数","annual_leave",2));
		list.add(new EhrSalaryChangeDefItem("平日加班","work_overtime",2));
		list.add(new EhrSalaryChangeDefItem("周末加班","week_overtime",2));
		list.add(new EhrSalaryChangeDefItem("节假日加班","holidays_overtime",2));
		list.add(new EhrSalaryChangeDefItem("调休天数","adjust_day",2));
		list.add(new EhrSalaryChangeDefItem("夜班天数","night_shift",2));
		list.add(new EhrSalaryChangeDefItem("应休年假天数","total_annual",2));
		list.add(new EhrSalaryChangeDefItem("剩余年假","surplus_annual",2));
		list.add(new EhrSalaryChangeDefItem("婚假","marriage_leave",2));
		list.add(new EhrSalaryChangeDefItem("产假","maternity_leave",2));
		list.add(new EhrSalaryChangeDefItem("陪产假","paternity_leave",2));
		list.add(new EhrSalaryChangeDefItem("丧假","funeral_leave",2));
		//list.add(new EhrSalaryChangeDefItem("备注","memo",1));
		return list;
	}
	
	public static List<EhrSalaryChangeDefItem> getSocialSecurityDefination(){
		List<EhrSalaryChangeDefItem> list = new ArrayList<EhrSalaryChangeDefItem>();
		list.add(new EhrSalaryChangeDefItem("社保企业基数","hj_base_e",2));
		list.add(new EhrSalaryChangeDefItem("社保个人基数","hj_base_p",2));
		
		list.add(new EhrSalaryChangeDefItem("社保企业汇缴总额","hj_amount_e",2));
		list.add(new EhrSalaryChangeDefItem("社保个人汇缴总额","hj_amount_p",2));
		list.add(new EhrSalaryChangeDefItem("社保汇缴总额","hj_amount",2));
		
		list.add(new EhrSalaryChangeDefItem("社保企业补缴总额","bj_amount_e",2));
		list.add(new EhrSalaryChangeDefItem("社保个人补缴总额","bj_amount_p",2));
		list.add(new EhrSalaryChangeDefItem("社保补缴总额","bj_amount",2));

		list.add(new EhrSalaryChangeDefItem("社保企业缴费总额","amount_e",2));
		list.add(new EhrSalaryChangeDefItem("社保个人缴费总额","amount_p",2));
		list.add(new EhrSalaryChangeDefItem("社保缴费总额","amount",2));
		
		return list;
	}
	
	public static List<EhrSalaryChangeDefItem> getAccumulationFundDefination(){
		List<EhrSalaryChangeDefItem> list = new ArrayList<EhrSalaryChangeDefItem>();
		list.add(new EhrSalaryChangeDefItem("公积金企业基数","hj_base_e",2));
		list.add(new EhrSalaryChangeDefItem("公积金个人基数","hj_base_p",2));
		
		list.add(new EhrSalaryChangeDefItem("公积金企业汇缴总额","hj_amount_e",2));
		list.add(new EhrSalaryChangeDefItem("公积金个人汇缴总额","hj_amount_p",2));
		list.add(new EhrSalaryChangeDefItem("公积金汇缴总额","hj_amount",2));
		
		list.add(new EhrSalaryChangeDefItem("公积金企业补缴总额","bj_amount_e",2));
		list.add(new EhrSalaryChangeDefItem("公积金个人补缴总额","bj_amount_p",2));
		list.add(new EhrSalaryChangeDefItem("公积金补缴总额","bj_amount",2));

		list.add(new EhrSalaryChangeDefItem("公积金企业缴费总额","amount_e",2));
		list.add(new EhrSalaryChangeDefItem("公积金个人缴费总额","amount_p",2));
		list.add(new EhrSalaryChangeDefItem("公积金缴费总额","amount",2));
		
		return list;
	}
	
//	public static List<EhrSalaryChangeDefItem> getSocialInsuranceDefination() {
//		List<EhrSalaryChangeDefItem> list = new ArrayList<EhrSalaryChangeDefItem>();
//		list.add(new EhrSalaryChangeDefItem("社保总计金额","total_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保企业合计","total_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("劳务费","laowu_fee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保管理费","fee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保合计","total_insurance_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保公司支付","insurance_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保个人支付","insurance_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("养老保险基数","yanglao_base",2));
//		list.add(new EhrSalaryChangeDefItem("养老企业缴纳","yanglao_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("养老个人缴纳","yanglao_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("失业保险基数","shiye_base",2));
//		list.add(new EhrSalaryChangeDefItem("失业企业缴纳","shiye_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("失业个人缴纳","shiye_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("工伤保险基数","gongshang_base",2));
//		list.add(new EhrSalaryChangeDefItem("工伤企业缴纳","gongshang_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("工伤个人缴纳","gongshang_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("生育保险基数","shengyu_base",2));
//		list.add(new EhrSalaryChangeDefItem("生育企业缴纳","shengyu_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("生育个人缴纳","shengyu_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("医疗基数","yiliao_base",2));
//		list.add(new EhrSalaryChangeDefItem("医疗企业缴纳","yiliao_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("医疗个人缴纳","yiliao_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("综合保险","zhonghe_amount",2));
//		list.add(new EhrSalaryChangeDefItem("重大疾病企业","dabing_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("重大疾病个人","dabing_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("残保金企业","canbao_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("五险合计企业","sum_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("五险合计个人","sum_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("五险小计","sum_amount",2));
//		list.add(new EhrSalaryChangeDefItem("住房公积金基数","gongjijin_base",2));
//		list.add(new EhrSalaryChangeDefItem("住房公积金企业缴纳","gongjijin_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("住房公积金个人缴纳","gongjijin_employee_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保统筹基数","tongchou_base",2));
//		list.add(new EhrSalaryChangeDefItem("社保统筹企业","tongchou_company_amount",2));
//		list.add(new EhrSalaryChangeDefItem("社保统筹个人","tongchou_employee_amount",2));
//		//list.add(new EhrSalaryChangeDefItem("社保备注","memo",1));
//		return list;
//	}
	
	public static void main(String args[]){
		System.out.println(SalaryHelper.salaryConstantCommonToString());
		System.out.println(SalaryHelper.salaryConstantPersonToString());
	}
}
