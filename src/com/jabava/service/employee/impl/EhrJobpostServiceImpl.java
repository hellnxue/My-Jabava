package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.core.EnumConstents.IsDeleted;
import com.jabava.dao.employee.EhrJobpostMapper;
import com.jabava.dao.employee.EhrPositionMapper;
import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.employee.EhrJobpostVO;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.service.employee.EhrJobpostService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.IEmployeeService;

@Service
public class EhrJobpostServiceImpl implements EhrJobpostService {
	@Resource
	private EhrJobpostMapper jobpostMapper;
	@Resource
	private EhrPositionMapper ehrPositionMapper;
	@Resource
	private IEmployeeService personService;
	@Resource
	public EhrPersonService ehrPersonService;

	@Override
	public List<EhrJobpostVO> getByPersonId(Long personId)  {
		return jobpostMapper.getByPersonId(personId);
	}

	@Override
	public boolean addJobpost(EhrJobpost jobpost) throws Exception {
		boolean result = false;	
		//新汇报对象
		EhrPerson person = ehrPersonService.getByPersonId(jobpost.getPersonId());
		List<EhrPerson> personList = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getNewReport());
		List<EhrPerson> repost = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getSourceReport());

		if(personList == null || personList.size()<1){
			return result;
		}else{	
			//新汇报对象
			jobpost.setNewReport(personList.get(0).getPersonId().toString());
		}
		if(repost == null || repost.size()<1){
			return result;
		}else{	
			//原汇报对象
			jobpost.setSourceReport(repost.get(0).getPersonId().toString());
		}
		try {
			EhrPosition ehrPosition = new EhrPosition();
			ehrPosition.setPersonId(jobpost.getPersonId());
			ehrPosition.setCostCenterId(Long.parseLong(jobpost.getNewCost()));
			ehrPosition.setDepartmentId(Long.parseLong(jobpost.getNewDepartment()));
			ehrPosition.setLevelId(Long.parseLong(jobpost.getNewRank()));
			ehrPosition.setReportId(Long.parseLong(jobpost.getNewReport()));
			ehrPositionMapper.updateByPersonId(ehrPosition);
			int code = jobpostMapper.insertSelective(jobpost);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateJobpost(EhrJobpost jobpost) throws Exception{
	boolean result = false;
	try {	
		//新汇报对象
		EhrPerson person = ehrPersonService.getByPersonId(jobpost.getPersonId());
		List<EhrPerson> personList = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getNewReport());
		List<EhrPerson> repost = personService.searchPersonByEmployeeNameAndCompanyId(person.getCompanyId(), jobpost.getSourceReport());

		if(personList == null || personList.size()<1){
			return result;
		}else{	
			//新汇报对象
			jobpost.setNewReport(personList.get(0).getPersonId().toString());
		}
		if(repost == null || repost.size()<1){
			return result;
		}else{	
			//原汇报对象
			jobpost.setSourceReport(repost.get(0).getPersonId().toString());
		}
		
		EhrPosition ehrPosition = new EhrPosition();
		ehrPosition.setPersonId(jobpost.getPersonId());
		ehrPosition.setCostCenterId(Long.parseLong(jobpost.getNewCost()));
		ehrPosition.setDepartmentId(Long.parseLong(jobpost.getNewDepartment()));
		ehrPosition.setLevelId(Long.parseLong(jobpost.getNewRank()));
		ehrPosition.setReportId(Long.parseLong(jobpost.getNewReport()));
		ehrPositionMapper.updateByPersonId(ehrPosition);
		int code = jobpostMapper.updateByPrimaryKeySelective(jobpost);
		result = (1 == code);
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
}
