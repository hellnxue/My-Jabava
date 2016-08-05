package com.jabava.service.employee.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrJobpostMapper;
import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.employee.EhrJobpostVO;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;
import com.jabava.utils.enums.PersonEnum;

@Service
public class EhrJobpostServiceImpl implements EhrJobpostService {
	@Resource
	private EhrJobpostMapper jobpostMapper;	
	@Resource
	private IEmployeeService personService;
	@Resource
	public EhrPersonService ehrPersonService;
	@Resource
	public EhrPositionService positionService;

	@Override
	public List<EhrJobpostVO> getByPersonId(Long personId)  {
		return jobpostMapper.getByPersonId(personId);
	}

	@Override
	public boolean addJobpost(EhrJobpost jobpost,EhrUser u) throws Exception {
		boolean result = false;	
		//新汇报对象
		EhrPerson person = ehrPersonService.getByPersonId(jobpost.getPersonId());
		List<EhrPerson> personList = null;
		if(StringUtils.isNotBlank(jobpost.getNewReport())){//新的汇报对象
			personList = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getNewReport());
		}		
		List<EhrPerson> repost =null;
		if(StringUtils.isNotBlank(jobpost.getSourceReport())){
			 repost = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getSourceReport());
		}

		if(personList != null && personList.size()>0){
			//新汇报对象
			jobpost.setNewReport(personList.get(0).getPersonId().toString());			
		}
		if(repost != null && repost.size()>0){
			//原汇报对象
			jobpost.setSourceReport(repost.get(0).getPersonId().toString());			
		}
		jobpost.setRecordStartDate(jobpost.getNewDate());//任职记录开始时间
		jobpost.setChangeType(String.valueOf(PersonEnum.PostChangeType.TransferPost.getValue()));//变动类型 
		
		//原部门  原岗位 原职级 原汇报对象 原工作地 原成本中心
		//根据personId查询人员信息
		EhrPerson personSource=null;
		try {
			personSource = ehrPersonService.getByPersonId(jobpost.getPersonId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(personSource!=null){
			if(personSource.getOrganizationId()!=null){
				jobpost.setSourceDepartment(personSource.getOrganizationId().toString() );//原部门
			}
			if(personSource.getPostId()!=null){
				jobpost.setSourcePost(personSource.getPostId().toString());//原岗位				
			}
			if(personSource.getRankId()!=null){
				jobpost.setSourceRank(personSource.getRankId().toString());//原职级
			}
			if(personSource.getReportObject()!=null){
				jobpost.setSourceReport(personSource.getReportObject());//原汇报对象
			}
			if(personSource.getWorkLocation()!=null){
				jobpost.setSourceLocation(personSource.getWorkLocation());//原工作地
			}
			if(personSource.getCostId()!=null){
				jobpost.setSourceCost(personSource.getCostId().toString());//原成本中心
			}
			
		}
		
		try {
			//处理任职记录-岗位调动
			boolean flag=HandleRecordByPersonId( jobpost.getPersonId(), u,jobpost.getNewDate(), "",null);
			
			int code = jobpostMapper.insertSelective(jobpost);
			result = (1 == code);
			EhrPosition ehrPosition = new EhrPosition();
			ehrPosition.setPersonId(jobpost.getPersonId());
			if(StringUtils.isNotBlank(jobpost.getNewCost())){//新成本中心
				ehrPosition.setCostCenterId(Long.parseLong(jobpost.getNewCost()));
			}
			if(StringUtils.isNotBlank(jobpost.getNewDepartment())){//新部门
				ehrPosition.setDepartmentId(Long.parseLong(jobpost.getNewDepartment()));
			}
			if(StringUtils.isNotBlank(jobpost.getNewRank())){//新职级
				ehrPosition.setLevelId(Long.parseLong(jobpost.getNewRank()));
			}
			if(StringUtils.isNotBlank(jobpost.getNewReport())){//新汇报对象
				ehrPosition.setReportId(Long.parseLong(jobpost.getNewReport()));
			}
			if(StringUtils.isNotBlank(jobpost.getNewPost())){//新岗位
				ehrPosition.setPositionName(Long.parseLong(jobpost.getNewPost()));
			}	
			
			
			positionService.updatePersonPosition(ehrPosition);//更新person中的岗位信息
			
		
			
			if(!flag){
				Log.info("任职记录处理失败...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date); 
		System.out.println(time);
		
		
//		Calendar c = Calendar.getInstance();
//		Date date=null;
//		try {
//		date = new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-01");
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
//		c.setTime(date);
//		int day=c.get(Calendar.DATE);
//		c.set(Calendar.DATE,day-1);
//
//		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	  String day=JabavaDateUtils.getSpecifiedDayBefore("2016-07-01"); 
		
	  Date d=JabavaDateUtils.StrToDate(day);
	  
		System.out.println(d );
	}

	@Override
	public boolean updateJobpost(EhrJobpost jobpost) throws Exception{
	boolean result = false;
	try {	
		//新汇报对象
		EhrPerson person = ehrPersonService.getByPersonId(jobpost.getPersonId());
		List<EhrPerson> personList = null;
		if(StringUtils.isNotBlank(jobpost.getNewReport())){//新的汇报对象
			personList = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getNewReport());
		}
		List<EhrPerson> repost =null;
		if(StringUtils.isNotBlank(jobpost.getSourceReport())){
			 repost = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getSourceReport());
		}
		if(personList != null &&  personList.size()>0){
			//新汇报对象
			jobpost.setNewReport(personList.get(0).getPersonId().toString());
		}
		if(repost != null &&  repost.size()>0){
			//原汇报对象
			jobpost.setSourceReport(repost.get(0).getPersonId().toString());
		}

		int code = jobpostMapper.updateByPrimaryKeySelective(jobpost);
		result = (1 == code);
		EhrPosition ehrPosition = new EhrPosition();
		ehrPosition.setPersonId(jobpost.getPersonId());
		if(StringUtils.isNotBlank(jobpost.getNewCost())){//新成本中心
			ehrPosition.setCostCenterId(Long.parseLong(jobpost.getNewCost()));
		}
		if(StringUtils.isNotBlank(jobpost.getNewDepartment())){//新部门
			ehrPosition.setDepartmentId(Long.parseLong(jobpost.getNewDepartment()));
		}
		if(StringUtils.isNotBlank(jobpost.getNewRank())){//新职级
			ehrPosition.setLevelId(Long.parseLong(jobpost.getNewRank()));
		}
		if(StringUtils.isNotBlank(jobpost.getNewReport())){//新汇报对象
			ehrPosition.setReportId(Long.parseLong(jobpost.getNewReport()));
		}
		if(StringUtils.isNotBlank(jobpost.getNewPost())){//新岗位
			ehrPosition.setPositionName(Long.parseLong(jobpost.getNewPost()));
		}
		positionService.updatePersonPosition(ehrPosition);//更新person中的岗位信息
	} catch (Exception e) {
		e.printStackTrace();
		result = false;
	}
		return result;
	}
	
	@Override
	public boolean delJobpost(Long postId) throws Exception {
		boolean result = false;
		try {
			EhrJobpost jobpost = new EhrJobpost();
			jobpost.setPostId(postId);
			jobpost.setIsDeleted(IsDeleted.DELETED.getValue());
			int code = jobpostMapper.updateByPrimaryKeySelective(jobpost);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public  EhrJobpost  getPreviousRecordByPersonId(Long personId) {
		EhrJobpost jp=null;
		List<EhrJobpost> list=jobpostMapper.getPreviousRecordByPersonId(personId);
		if(list!=null&&list.size()>0){
			  jp=list.get(0);
		}
		return jp;
	}
	
	/**
	 * 处理任职记录  修改上一条任职记录的结束时间并且新增一条任职记录
	 * @param personId 岗位调动和人员离职的参数
	 * @param u 
	 * @param date 下一条任职记录的开始时间-旧记录的结束时间
	 * @param importPerson 花名册导入的有岗位信息的员工，处理入职部分
	 * @return
	 */
	public boolean HandleRecordByPersonId(Long personId,EhrUser u,Date date,String changeType,EhrPerson importPerson){
		boolean flag=false;
		//获取上一条任职记录
		EhrJobpost previousRecord=getPreviousRecordByPersonId(personId);
		
		//查询员工的上一条任职记录，修改结束时间
		if(previousRecord!=null){
			
			previousRecord.setLastModifyDate(new Date());
			previousRecord.setLastModifyUserId(u.getUserId());
			previousRecord.setLastModifyUserName(u.getUserName());
			if(date!=null){
				previousRecord.setRecordEndDate(JabavaDateUtils.StrToDate(JabavaDateUtils.getSpecifiedDayBefore(JabavaDateUtils.DateToStr(date) )));//任职记录结束时间
				flag=jobpostMapper.updateByPrimaryKeySelective(previousRecord)>0?true:false;
			}
			
		} 
		//非岗位调动时，人员离职和入职的任职记录有不同的处理方式
		if(!changeType.equals("")){
			
			
			
			//新增任职记录
			EhrJobpost record=new EhrJobpost();
			record.setPersonId(personId);
			
			//离职时任职记录只包含以下部分
			if(changeType.equals(String.valueOf(PersonEnum.PostChangeType.Dimission.getValue()))){
				//根据personId查询人员信息
				EhrPerson person=null;
				try {
					person = ehrPersonService.getByPersonId(personId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(person!=null){
					if(person.getOrganizationId()!=null){
						record.setSourceDepartment(person.getOrganizationId().toString() );//原部门
					}
					if(person.getPostId()!=null){
						record.setSourcePost(person.getPostId().toString());//原岗位				
					}
					if(person.getRankId()!=null){
						record.setSourceRank(person.getRankId().toString());//原职级
					}
					if(person.getReportObject()!=null){
						record.setSourceReport(person.getReportObject());//原汇报对象
					}
					if(person.getWorkLocation()!=null){
						record.setSourceLocation(person.getWorkLocation());//原工作地
					}
					if(person.getCostId()!=null){
						record.setSourceCost(person.getCostId().toString());//原成本中心
					}
				}
				
				
				
			}
			
			//入职时任职记录只包含以下部分
			if(changeType.equals(String.valueOf(PersonEnum.PostChangeType.Entry.getValue()))&&importPerson!=null){
				
				if(importPerson.getOrganizationId()!=null){
					record.setNewDepartment(importPerson.getOrganizationId().toString() );//新部门
				}
				
				if(importPerson.getPostId()!=null){
					record.setNewPost(importPerson.getPostId().toString());//新岗位
				}
				
				if(importPerson.getRankId()!=null){
					record.setNewRank(importPerson.getRankId().toString());//新职级
				}
				
				if(importPerson.getReportObject()!=null&&!importPerson.getReportObject().equals("")){
					record.setNewReport(importPerson.getReportObject());//新汇报对象
				}
				
				if(importPerson.getWorkLocation()!=null&&!importPerson.getWorkLocation().equals("")){
					record.setNewLocation(importPerson.getWorkLocation());//新工作地
				}
				
				if(importPerson.getCostId()!=null){
					record.setNewCost(importPerson.getCostId().toString());//新成本中心
				}
				
			}
			
			record.setRecordStartDate(date);//任职记录开始时间
			record.setChangeType(changeType);//变动类型 
			
			
			record.setLastModifyUserName(u.getUserName());
			record.setLastModifyDate(new Date());
			record.setLastModifyUserId(u.getUserId());
			record.setCreateDate(new Date());
			record.setCreateUserId(u.getUserId());
			record.setCreateUserName(u.getUserName());
			flag=jobpostMapper.insertSelective(record)>0?true:flag;
			
		}

		return flag;
	}

	@Override
	public  List<Map<String,Object>> getAllRecordsByPersonId(Long personId) {
		return jobpostMapper.getAllRecordsByPersonId(personId);
	}

	@Override
	public EhrJobpost getEntryRecordByPersonId(Map<String,Object> map) {
		EhrJobpost jp=null;
		List<EhrJobpost>  list=jobpostMapper.getEntryRecordByPersonId(map);
		if(list!=null&&list.size()>0){
			
			jp=list.get(0);
		}
		
		return jp;
	}

	@Override
	public boolean HandleEntryRecordByPersonId(Long personId, EhrUser u, Date entryDate) {
		boolean flag=false;
		Map<String, Object> params = new HashMap<>();
		params.put("personId", personId);
		params.put("changeType", String.valueOf(PersonEnum.PostChangeType.Entry.getValue()));//变动类型-入职
		EhrJobpost entryRecord=getEntryRecordByPersonId(params);
		if(entryRecord!=null){

			
			if(entryDate!=null){
				entryRecord.setLastModifyDate(new Date());
				entryRecord.setLastModifyUserId(u.getUserId());
				entryRecord.setLastModifyUserName(u.getUserName());
				entryRecord.setRecordStartDate(entryDate);;//任职记录开始时间
				flag=jobpostMapper.updateByPrimaryKeySelective(entryRecord)>0?true:flag;
			}
		}else{
			EhrPerson person=null;
			try {
				person = ehrPersonService.getByPersonId(personId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//新增任职记录
			EhrJobpost record=new EhrJobpost();
			record.setPersonId(personId);
			if(person!=null){
				if(person.getOrganizationId()!=null){
					record.setNewDepartment(person.getOrganizationId().toString() );//新部门
				}
				
				if(person.getPostId()!=null){
					record.setNewPost(person.getPostId().toString());//新岗位
				}
				
				if(person.getRankId()!=null){
					record.setNewRank(person.getRankId().toString());//新职级
				}
				
				if(person.getReportObject()!=null&&!person.getReportObject().equals("")){
					record.setNewReport(person.getReportObject());//新汇报对象
				}
				
				if(person.getWorkLocation()!=null&&!person.getWorkLocation().equals("")){
					record.setNewLocation(person.getWorkLocation());//新工作地
				}
				
				if(person.getCostId()!=null){
					record.setNewCost(person.getCostId().toString());//新成本中心
				}
			}
			
			record.setLastModifyDate(new Date());
			record.setLastModifyUserId(u.getUserId());
			record.setLastModifyUserName(u.getUserName());
			record.setRecordStartDate(entryDate);//任职记录开始时间
			record.setChangeType(String.valueOf(PersonEnum.PostChangeType.Entry.getValue()));//变动类型-入职
			record.setCreateDate(new Date());
			record.setCreateUserId(u.getUserId());
			record.setCreateUserName(u.getUserName());
			flag=jobpostMapper.insertSelective(record)>0?true:flag;
		}
		
		return flag;
	}

	@Override
	public boolean HandleEntryRecord(EhrPerson person,EhrUser user,boolean isOld) {
		EhrJobpost record=new EhrJobpost();
		
		return false;
	}

}
