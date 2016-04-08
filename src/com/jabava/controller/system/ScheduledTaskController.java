package com.jabava.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jabava.utils.Page;

@Controller
@RequestMapping("/system")
public class ScheduledTaskController {
	
	@RequestMapping("/toScheduledJob")
	public String toScheduledJob(){
		return "system/list_scheduledJob";
	}
	
	@RequestMapping("/loadScheduledJob")
	@ResponseBody
	public Page<Map<String,Object>> loadScheduledJob(HttpServletRequest request, HttpServletResponse response){
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		Scheduler scheduler = (Scheduler)context.getBean("scheduler");
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			CronTriggerBean trigger = null;
			Map<String,Object> record = null;
			for(String triggerName : scheduler.getTriggerNames(Scheduler.DEFAULT_GROUP)){
				trigger = (CronTriggerBean)scheduler.getTrigger(triggerName, Scheduler.DEFAULT_GROUP);
				record = new HashMap<String,Object>();
				record.put("triggerKey", trigger.getName());
				record.put("cronExpression", trigger.getCronExpression());
				result.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(0,99);
		page.setData(result);
		return page;
	}
	
	@RequestMapping("/modifyCronExpression")
	@ResponseBody
	public Map<String,Object> modifyCronExpression(HttpServletRequest request, HttpServletResponse response,
			String triggerKey,String cronExpression){
		Map<String,Object> result = new HashMap<String,Object>();
		
		try {
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
			Scheduler scheduler = (Scheduler)context.getBean("scheduler");
			//scheduler.triggerJob(arg0, arg1);
			//scheduler.pauseTrigger(arg0, arg1);
			//scheduler.resumeJob(arg0, arg1);
			
			CronTriggerBean trigger = (CronTriggerBean)scheduler.getTrigger(triggerKey,Scheduler.DEFAULT_GROUP);
			
			String originConExpression = trigger.getCronExpression();
			//有效性验证
			
			// 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob  
			if(!originConExpression.equalsIgnoreCase(cronExpression)){  
			    trigger.setCronExpression(cronExpression);  
			    scheduler.rescheduleJob(triggerKey, Scheduler.DEFAULT_GROUP, trigger);  
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/triggerJob")
	@ResponseBody
	public Map<String,Object> triggerJob(HttpServletRequest request, HttpServletResponse response, String triggerKey){
		Map<String,Object> result = new HashMap<String,Object>();
		
		try {
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
			Scheduler scheduler = (Scheduler)context.getBean("scheduler");
//			for(String jobGroup : scheduler.getJobGroupNames()){
//				System.out.println("jobGroup: " + jobGroup);
//			}
//			for(String jobName : scheduler.getJobNames(Scheduler.DEFAULT_GROUP)){
//				System.out.println("jobName: " + jobName);
//			}
			
			CronTriggerBean trigger = (CronTriggerBean)scheduler.getTrigger(triggerKey,Scheduler.DEFAULT_GROUP);
			int state = scheduler.getTriggerState(triggerKey, Scheduler.DEFAULT_GROUP);
			System.out.println("Trigger state: " + state);
			
			//scheduler.triggerJob("importSalaryInfoJobFactory", Scheduler.DEFAULT_GROUP);
			scheduler.triggerJob(trigger.getJobName(), Scheduler.DEFAULT_GROUP);
			state = scheduler.getTriggerState(triggerKey, Scheduler.DEFAULT_GROUP);
			System.out.println("Trigger state: " + state);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
}
