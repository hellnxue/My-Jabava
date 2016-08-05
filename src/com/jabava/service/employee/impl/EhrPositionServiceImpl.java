package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.employee.EhrPositionMapper;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonVO;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.constants.BaseDataConstants;
/**
 * 岗位信息
 *
 * @version $Id: EhrPositionServiceImpl.java, 
 * v 0.1 2016年2月17日 下午5:48:29 
 * <pre>
 * @author steven.chen
 * @date 2016年2月17日 下午5:48:29 
 * </pre>
 */
@Service
public class EhrPositionServiceImpl implements EhrPositionService {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(EhrPositionServiceImpl.class);
	
	@Resource
	private EhrPositionMapper positionMapper;
	@Resource
	private  IBaseDataService baseDataService;
	@Resource
	public EhrPersonService ehrPersonService;
	@Resource
	private IEmployeeService personService;

	@Override
	public String addPositin(EhrPosition ehrPosition,Long companyId,String reportEmployeeName) {
		EhrPerson ehrPerson  =null;
		
		try {
			 ehrPerson  = ehrPersonService.getByPersonId(ehrPosition.getPersonId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EhrPerson person  = new EhrPerson();
		person.setCompanyId(ehrPerson.getCompanyId());
		if(StringUtils.isNotBlank(ehrPosition.getEmployeeNumber())){
			person.setJobNumber(ehrPosition.getEmployeeNumber());
		}
		
		List<EhrPerson> numberList= positionMapper.getPositionByEmployeeNumberAndCompanyId(person);
		// employeeName 汇报对象
//		if(!org.apache.commons.lang.StringUtils.isEmpty(reportEmployeeName)){
//			List<EhrPerson> personList = personService.searchPersonByEmployeeNameAndCompanyId(ehrPerson.getCompanyId(), reportEmployeeName);
//			if(personList!= null &&  personList.size()>0){
//				ehrPosition.setReportId(personList.get(0).getPersonId());
//			}else{
//				return "汇报对象填写不正确";
//			}
//		}		
	
		if(numberList !=null && numberList.size() > 0){
			for(EhrPerson tempPerson : numberList){
				if(!tempPerson.getPersonId().equals(ehrPerson.getPersonId())){
					return "该工号已经存在";
				}
			}
		}
		
		if( updatePersonPosition(ehrPosition)==1){//更新person数据					
			return "success";
		}else{
			return "error";
		}
	}
	
	//更新person数据
	@Override
	public int updatePersonPosition(EhrPosition ehrPosition){
		EhrPerson po = new EhrPerson();
		po.setPersonId(ehrPosition.getPersonId());
		if(ehrPosition.getCostCenterId()!=null){
			po.setCostId(ehrPosition.getCostCenterId());//成本中心
		}
		if(ehrPosition.getPositionName()!=null){
			po.setPostId(ehrPosition.getPositionName());//岗位
		}
		if(StringUtils.isNotBlank(ehrPosition.getEmployeeNumber())){
			po.setJobNumber(ehrPosition.getEmployeeNumber());//工号
		}
		if(ehrPosition.getNatureOfLabor()!=null){
			po.setEmploymentType(ehrPosition.getNatureOfLabor().toString());//用工性质
		}
		if(ehrPosition.getLevelId()!=null){
			po.setRankId(ehrPosition.getLevelId());//职级
		}
		if(ehrPosition.getKeyPoint()!=null){
			po.setKeyPerson( ehrPosition.getKeyPoint() );//是否核心员工
		}
		if(ehrPosition.getReportId()!=null){
			po.setReportObject(ehrPosition.getReportId().toString());//汇报对象
		}
		if(ehrPosition.getDepartmentId()!=null){
			po.setOrganizationId(ehrPosition.getDepartmentId());//部门
		}
		po.setLastModifyDate(ehrPosition.getUpdateDate());
		po.setLastModifyUserId(ehrPosition.getUpdateUserId());
		return ehrPersonService.updatePerson(po);
	} 
	@Override
	public boolean employeeNumberExist(String jobNumber,Long companyId){
		EhrPerson ehrPerson  = new EhrPerson();		
		ehrPerson.setCompanyId(companyId);
		ehrPerson.setJobNumber(jobNumber);
		List<EhrPerson> ehrPositionList= positionMapper.getPositionByEmployeeNumberAndCompanyId(ehrPerson);
		if(ehrPositionList!=null && ehrPositionList.size()>0){
			return true;
		}else{
			return false;
		}		
	}

	@Override
	public  EhrPositionVO getEhrPositionByPersonId(Long personId)  throws JabavaServiceException {
			EhrPositionVO ehrPositionVO = null;
			EhrPersonVO personVO  = positionMapper.selectPositionByPersonId(personId);	
			if(ehrPositionVO==null){
				ehrPositionVO = new EhrPositionVO();
			}
			 
			
			try {
				EhrPerson ehrPerson = ehrPersonService.getByPersonId(personId);
				List<EhrBaseData> post =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), BaseDataConstants.BASE_DATA_POSITION, null);//岗位
				List<EhrBaseData> labor =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), BaseDataConstants.BASE_DATA_EMPLOYEE_TYPE, null);//用工性质
				List<EhrBaseData> rank =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), BaseDataConstants.BASE_DATA_LEVEL, null);//职级
				List<EhrBaseData> cost  = baseDataService.selectBaseData(ehrPerson.getCompanyId(), BaseDataConstants.BASE_DATA_COST_CENTER, null);//成本中心
				
				//基础数据
				ehrPositionVO.setPosiName(post); 
				ehrPositionVO.setLabor(labor);
				ehrPositionVO.setLevel(rank);
				ehrPositionVO.setCostCenter(cost);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JabavaServiceException(e.toString());
			}
		
			
		    
		    if(personVO!=null){
		    	//工号
		    	if(StringUtils.isNotBlank(personVO.getJobNumber())){
		    		ehrPositionVO.setEmployeeNumber(personVO.getJobNumber());
		    	}
		    	
		    	//部门名称
		    	if(personVO.getOrganizationId()!=null){
		    		ehrPositionVO.setDepartmentId(personVO.getOrganizationId());
		    	}
		    	if(personVO.getOrganization()!=null){
		    		ehrPositionVO.setDepartment(personVO.getOrganization());
		    	}
		    	//用工性质
		    	if(StringUtils.isNotBlank(personVO.getEmploymentType())){
		    		ehrPositionVO.setNatureOfLabor(Long.valueOf(personVO.getEmploymentType()));
		    	}
		    	//是否关键岗位
		    	if(personVO.getKeyPerson()!=null){
		    		ehrPositionVO.setKeyPoint(personVO.getKeyPerson());
		    	}
		    	//岗位名称
		    	if(personVO.getPostId()!=null){
		    		ehrPositionVO.setPositionName(personVO.getPostId());
		    		// 给一个数据表示岗位信息有数据
			    	ehrPositionVO.setPositionId(personVO.getPostId());
		    	}
		    	//汇报对象
		    	if(StringUtils.isNotBlank(personVO.getReportObject())){
		    		 
		    		ehrPositionVO.setReportId(Long.valueOf(personVO.getReportObject()));
		    	}
		    	if(personVO.getPerson()!=null){
		    		ehrPositionVO.setReportPerson(personVO.getPerson());
		    	}
		    	//职级
		    	if(personVO.getRankId()!=null){
		    		ehrPositionVO.setLevelId(personVO.getRankId());
		    	}
		    	
		    	//成本中心
		    	if(personVO.getCostId()!=null){
		    		ehrPositionVO.setCostCenterId(personVO.getCostId());
		    	}
		    	
		    	//工作地
		    	if(personVO.getWorkLocation()!=null){
		    		ehrPositionVO.setWorkLocation(personVO.getWorkLocation());
		    	}
		    	ehrPositionVO.setPersonId(personVO.getPersonId());
		    	
		    }
		 
		return ehrPositionVO;
	}

}
