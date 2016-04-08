package com.jabava.controller.employee;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 员工信息导航链接跳转
 * @author zhiyanguser
 *
 */
@Controller
@RequestMapping("employee")
public class StaffController {
	
	@RequestMapping("/toPageByLinkType")
	public String getProject(HttpServletRequest request,Model model){
		
		String result="";
		String linkType=request.getParameter("linkType");
		String personId=request.getParameter("personId");
		model.addAttribute("personId", personId);
		if(linkType!=null&&!linkType.equals("")){
			
			
			if(linkType.equals("slicks")){
				result="forward:/employee/basicInformationPage";
			}
			
			if(linkType.equals("bill")){
				result="employees/basic_information";
			}
			
			//个人信息
			if(linkType.equals("personal")){
				result="forward:/employee/employeeInformation";
			}
			//基本信息页面
			if(linkType.equals("essential")){
				result="forward:/employee/basicInformationPage";
			}
			
			//岗位信息
			if(linkType.equals("position")){
				result="forward:/position/positionInformation";
			}
			
			//岗位调动
			if(linkType.equals("transfer")){
				result="forward:/employee/jobpost";
			}
			
			//教育背景
			if(linkType.equals("educational")){
				result="forward:/employee/educationInfoPage";
			}
			//工作经验页面
			if(linkType.equals("experience")){
				result="forward:/employee/experienceInfoPage";
			}
			//项目经验
			if(linkType.equals("project")){
				result="forward:/employee/projectInfoPage";
			}
			//获得证书
			if(linkType.equals("obtain")){
				result="forward:/employee/certificateInfoPage";
			}
			//培训经历
			if(linkType.equals("training")){
				result="forward:/employee/trainInfoPage";
			}
			//家庭成员
			if(linkType.equals("member")){
				result="forward:/employee/familyInfoPage";
			}
			//劳动合同
			if(linkType.equals("contract")){							
				result="forward:/employees/work_contract";
			}
			//试用情况
			if(linkType.equals("trial")){
				result="employees/trial";
			}
			//绩效考核
			if(linkType.equals("performance")){
				result="employees/assess";
			}
			//惩奖记录
			if(linkType.equals("reward")){
				result="employees/reward";
			}
			//离职管理
			if(linkType.equals("dimission")){
				result="employees/dimission";
			}
		}
		
		return result;
	}

}
