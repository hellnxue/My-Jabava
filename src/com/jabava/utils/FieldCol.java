package com.jabava.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrTableFieldDef;
import com.jabava.utils.enums.SystemEnum;

public class FieldCol {
	/**
	 * 跟关联表相关的固定字段
	 * @param keyTable 关联表名
	 * @param i 判断表中是否有显示字段 1有0没有
	 * @return
	 */
	public static Map<String,Object> getField(String keyTable,int i ){
		Map<String, Object> map= new HashMap<String, Object>();
		List<Map<String, Object>> list= null;
		if(keyTable.equals("ehr_person")&& i==1){
			 list = new ArrayList<Map<String, Object>>();
			list.add(getFieldMap("hsex", "性别"));
			list.add(getFieldMap("family_address","家庭住址"));
			list.add(getFieldMap("birth_date","出生日期"));
			list.add(getFieldMap("contact_address","现居地址"));
			list.add(getFieldMap("post_code","邮编"));
			list.add(getFieldMap("email","Email"));
			list.add(getFieldMap("degree","最高学历"));
			list.add(getFieldMap("cert_type","证件类型"));
			list.add(getFieldMap("cert_id","证件号"));
			list.add(getFieldMap("registerType","户口类型"));
			list.add(getFieldMap("register_location","户口所在地"));
			list.add(getFieldMap("nationality","民族"));
			list.add(getFieldMap("education","最高学位"));
			list.add(getFieldMap("bank_name","开户行"));
			list.add(getFieldMap("subbank","开户支行"));
			list.add(getFieldMap("salary_card","工资卡号"));
			list.add(getFieldMap("politics_status","政治面貌"));
			list.add(getFieldMap("origin_place","籍贯"));
			list.add(getFieldMap("hmarital","婚姻状况"));
			list.add(getFieldMap("firstjob_date","参加工作时间"));
			list.add(getFieldMap("file_location","档案存放处"));
			list.add(getFieldMap("isForeign","是否外籍"));
			list.add(getFieldMap("studyAbroad","是否海外留学"));
			list.add(getFieldMap("company_id","单位名称"));
			list.add(getFieldMap("isPayrollFlag","停发标志"));
			list.add(getFieldMap("entry_date","入职时间"));
			list.add(getFieldMap("positive_date","转正时间"));
			list.add(getFieldMap("hstatus","员工状态"));
			list.add(getFieldMap("work_location","工作地"));
			list.add(getFieldMap("team","团队"));
			list.add(getFieldMap("payroll_location","发薪地"));
			list.add(getFieldMap("recruit","招聘渠道"));
			list.add(getFieldMap("employment_type","用工性质"));
			list.add(getFieldMap("post","岗位名称"));
			list.add(getFieldMap("key_person","是否关键岗位"));
			list.add(getFieldMap("reportObject","汇报对象"));
			list.add(getFieldMap("referrer","推荐人"));
			list.add(getFieldMap("job_number","工号"));
			list.add(getFieldMap("employee_name", "姓名"));
			list.add(getFieldMap("org","部门名称"));
			list.add(getFieldMap("rankName","职级"));
			list.add(getFieldMap("phone","联系电话"));
			map.put(keyTable, list);
		}else if(keyTable.equals("ehr_person")&& i==0){
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			list1.add(getFieldMap("job_number","工号"));
			list1.add(getFieldMap("employee_name", "姓名"));
			list1.add(getFieldMap("org","部门名称"));
			list1.add(getFieldMap("rankName","职级"));
			list1.add(getFieldMap("phone","联系电话"));
			list1.add(getFieldMap("email","Email"));
			list = new ArrayList<Map<String, Object>>();
			list.add(getFieldMap("hsex", "性别"));
			list.add(getFieldMap("family_address","家庭住址"));
			list.add(getFieldMap("birth_date","出生日期"));
			list.add(getFieldMap("contact_address","现居地址"));
			list.add(getFieldMap("post_code","邮编"));
			list.add(getFieldMap("degree","最高学历"));
			list.add(getFieldMap("cert_type","证件类型"));
			list.add(getFieldMap("cert_id","证件号"));
			list.add(getFieldMap("registerType","户口类型"));
			list.add(getFieldMap("register_location","户口所在地"));
			list.add(getFieldMap("nationality","民族"));
			list.add(getFieldMap("education","最高学位"));
			list.add(getFieldMap("bank_name","开户行"));
			list.add(getFieldMap("subbank","开户支行"));
			list.add(getFieldMap("salary_card","工资卡号"));
			list.add(getFieldMap("politics_status","政治面貌"));
			list.add(getFieldMap("origin_place","籍贯"));
			list.add(getFieldMap("hmarital","婚姻状况"));
			list.add(getFieldMap("firstjob_date","参加工作时间"));
			list.add(getFieldMap("file_location","档案存放处"));
			list.add(getFieldMap("isForeign","是否外籍"));
			list.add(getFieldMap("studyAbroad","是否海外留学"));
			list.add(getFieldMap("company_id","单位名称"));
			list.add(getFieldMap("isPayrollFlag","停发标志"));
			list.add(getFieldMap("entry_date","入职时间"));
			list.add(getFieldMap("positive_date","转正时间"));
			list.add(getFieldMap("hstatus","员工状态"));
			list.add(getFieldMap("work_location","工作地"));
			list.add(getFieldMap("team","团队"));
			list.add(getFieldMap("payroll_location","发薪地"));
			list.add(getFieldMap("recruit","招聘渠道"));
			list.add(getFieldMap("employment_type","用工性质"));
			list.add(getFieldMap("post","岗位名称"));
			list.add(getFieldMap("key_person","是否关键岗位"));
			list.add(getFieldMap("reportObject","汇报对象"));
			list.add(getFieldMap("referrer","推荐人"));
			map.put("defaultCol", list1);
			map.put("hiddenCol", list);
			return map;
		}
		return map;
	}
	
	private static Map<String, Object> getFieldMap(String columnName, String displayName){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("columnName", columnName);
		m.put("displayName", displayName);
		return m;
	}
	
	public static int getMoudle(String module){
		int mou=0;
		if(module.equalsIgnoreCase("Organization")){
			mou=SystemEnum.Module.Organization.getValue();
		}else if(module.equalsIgnoreCase("Ssaf")){
			mou=SystemEnum.Module.Ssaf.getValue();
		}else if(module.equalsIgnoreCase("Salary")){
			mou=SystemEnum.Module.Salary.getValue();
		}else if(module.equalsIgnoreCase("InformationBulletin")){
			mou=SystemEnum.Module.InformationBulletin.getValue();
		}else if(module.equalsIgnoreCase("Report")){
			mou=SystemEnum.Module.Report.getValue();
		}else if(module.equalsIgnoreCase("Supplier")){
			mou=SystemEnum.Module.Supplier.getValue();
		}else if(module.equalsIgnoreCase("SystemManagement")){
			mou=SystemEnum.Module.SystemManagement.getValue();
		}else if(module.equalsIgnoreCase("UserManagement")){
			mou=SystemEnum.Module.UserManagement.getValue();
		}
		return mou;
		
	}
	public static int getFunction(String function){
		int fun = 0;
		if(function.equalsIgnoreCase("AccumulationFundAccountMain")){
			fun=SystemEnum.Function.AccumulationFundAccountMain.getValue();
		}else if(function.equalsIgnoreCase("AddPerson")){
			fun=SystemEnum.Function.AddPerson.getValue();
		}else if(function.equalsIgnoreCase("AfListPaymentBill")){
			fun=SystemEnum.Function.AfListPaymentBill.getValue();
		}else if(function.equalsIgnoreCase("BillList")){
			fun=SystemEnum.Function.BillList.getValue();
		}else if(function.equalsIgnoreCase("EmployeeList")){
			fun=SystemEnum.Function.EmployeeList.getValue();
		}else if(function.equalsIgnoreCase("GenerateReport")){
			fun=SystemEnum.Function.GenerateReport.getValue();
		}else if(function.equalsIgnoreCase("ListMonthlySalary")){
			fun=SystemEnum.Function.ListMonthlySalary.getValue();
		}else if(function.equalsIgnoreCase("ListProtocol")){
			fun=SystemEnum.Function.ListProtocol.getValue();
		}else if(function.equalsIgnoreCase("ListQueryChange")){
			fun=SystemEnum.Function.ListQueryChange.getValue();
		}else if(function.equalsIgnoreCase("ListSalary")){
			fun=SystemEnum.Function.ListSalary.getValue();
		}else if(function.equalsIgnoreCase("ListSalaryChangeData")){
			fun=SystemEnum.Function.ListSalaryChangeData.getValue();
		}else if(function.equalsIgnoreCase("ListSalaryChangeDef")){
			fun=SystemEnum.Function.ListSalaryChangeDef.getValue();
		}else if(function.equalsIgnoreCase("OrderMain")){
			fun=SystemEnum.Function.OrderMain.getValue();
		}else if(function.equalsIgnoreCase("SearchOrganization")){
			fun=SystemEnum.Function.SearchOrganization.getValue();
		}else if(function.equalsIgnoreCase("SecurityProfile")){
			fun=SystemEnum.Function.SecurityProfile.getValue();
		}else if(function.equalsIgnoreCase("ServiceOpen")){
			fun=SystemEnum.Function.ServiceOpen.getValue();
		}else if(function.equalsIgnoreCase("SocialSecurityAccountMain")){
			fun=SystemEnum.Function.SocialSecurityAccountMain.getValue();
		}else if(function.equalsIgnoreCase("SsListPaymentBill")){
			fun=SystemEnum.Function.SsListPaymentBill.getValue();
		}else if(function.equalsIgnoreCase("UploadChange")){
			fun=SystemEnum.Function.UploadChange.getValue();
		}
		return fun;
	}
}
