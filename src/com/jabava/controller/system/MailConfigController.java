package com.jabava.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.system.EHrMailConfig;
import com.jabava.service.system.IEhrMailConfigService;
import com.jabava.service.system.IEhrSysLogSercice;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.enums.SystemEnum;

@Controller
@RequestMapping("/system")
public class MailConfigController {

	@Resource
	private IEhrSysLogSercice sysLogSercice;
	
	@Resource
	private IEhrMailConfigService mailConfigService;
	
	@RequestMapping("/to_mailConfig")
	public String mailConfig(){	
		return "system/mailServerConfig";
	}
	
	
	@RequestMapping("loadMailConfig")
	@ResponseBody
	public Map<String,Object> loadMailConfig(HttpServletRequest request, HttpServletResponse response) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		EHrMailConfig mailConfig=null;
		if(user!=null){
			mailConfig=mailConfigService.findByCompanyId(user.getCompanyId());
		}
	    sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Select,SystemEnum.Module.SystemManagement,"查询企业邮箱配置信息，查询参数：公司Id:"+user.getCompanyId());
		Map<String,Object> resultMap=MessageUtil.successMessage("");
		resultMap.put("result",mailConfig);
	    return resultMap;
	}
	
	
	@RequestMapping("saveMailConfig")
	@ResponseBody
	public Map<String,Object> saveMailConfig(HttpServletRequest request, HttpServletResponse response,EHrMailConfig mailConfig) throws Exception{
		Map<String, Object> data =MessageUtil.errorMessage("企业邮箱配置失败");
		EhrUser user = RequestUtil.getLoginUser(request);
		try {
			boolean result = mailConfigService.saveMailConfig(user,mailConfig);
			if (result) {
				data=MessageUtil.successMessage("企业邮箱配置成功");
			}
			sysLogSercice.addSysLog(user, SystemEnum.LogOperateType.Add,SystemEnum.Module.SystemManagement,
					"企业邮箱配置参数,发信箱："+mailConfig.getSendTo()+",发信箱服务器："+mailConfig.getMailServer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
