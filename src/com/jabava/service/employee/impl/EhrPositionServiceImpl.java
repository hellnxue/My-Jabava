package com.jabava.service.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jabava.dao.employee.EhrPositionMapper;
import com.jabava.pojo.employee.EhrPosition;
import com.jabava.pojo.employee.EhrPositionVO;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.employee.EhrPositionService;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.service.system.IBaseDataService;
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
	public String addPositin(EhrPosition ehrPosition,Long companyId,String employeeName) {
		EhrPerson ehrPerson  =null;
		try {
			 ehrPerson  = ehrPersonService.getByPersonId(ehrPosition.getPersonId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EhrPerson person  = new EhrPerson();		
		person.setCompanyId(ehrPerson.getCompanyId());
		person.setJobNumber(ehrPosition.getEmployeeNumber());
		List<EhrPosition> ehrPositionList= positionMapper.getPositionByEmployeeNumberAndCompanyId(person);
		//如果该工号已经存在,返回提示信息
		/*if(employeeNumberExist(ehrPosition.getEmployeeNumber(),ehrPerson.getCompanyId())){
			return "该工号已经存在";
		}*/
		if(employeeName!=null){
			List<EhrPerson> personList = personService.searchPersonByEmployeeNameAndCompanyId(ehrPerson.getCompanyId(), employeeName);
			if(personList == null || personList.size()<1){
				
				return "汇报对象不存在";
			}else{
				ehrPosition.setReportId(personList.get(0).getPersonId());
			}
		}		
		
		//修改
		if(ehrPosition.getPositionId()!=null){
			if(ehrPositionList!=null && ehrPositionList.size()>0){
				//如果只有一个
				if(ehrPositionList.size()==1 && ehrPositionList.get(0).getEmployeeNumber().equals(ehrPosition.getEmployeeNumber())){
					if( positionMapper.updateByPrimaryKeySelective(ehrPosition)==1){
						return "success";
					}else{
						return "error";
					}
				}else{
					return "该工号已经存在";
				}
			}else{
				if( positionMapper.updateByPrimaryKeySelective(ehrPosition)==1){
					return "success";
				}else{
					return "error";
				}
			}
			
		}else{
			if(ehrPositionList!=null && ehrPositionList.size()>0){
				return "该工号已经存在";
			}
			//新增
			if( positionMapper.insertSelective(ehrPosition)==1){
				return "success";
			}else{
				return "error";
			}
			
		}		
		
	}
	
	@Override
	public boolean employeeNumberExist(String jobNumber,Long companyId){
		EhrPerson ehrPerson  = new EhrPerson();		
		ehrPerson.setCompanyId(companyId);
		ehrPerson.setJobNumber(jobNumber);
		List<EhrPosition> ehrPositionList= positionMapper.getPositionByEmployeeNumberAndCompanyId(ehrPerson);
		if(ehrPositionList == null || ehrPositionList.size()>0){
			return true;
		}else{
			return false;
		}		
	}
	@Override
	public boolean reportExist(String employeeName,Long companyId){
		List<EhrPerson> personList = personService.searchPersonByEmployeeNameAndCompanyId(companyId, employeeName);
		if(personList == null || personList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<EhrPosition> getPositionByPersonIdAndCompanyId(EhrPerson ehrPerson){
		return positionMapper.getPositionByPersonIdAndCompanyId(ehrPerson);
	}
	@Override
	public  EhrPositionVO getEhrPositionByPersonId(Long personId){
		EhrPositionVO ehrPositionVO = null;
		ehrPositionVO = positionMapper.selectByPersonId(personId);	
		if(ehrPositionVO==null){
			ehrPositionVO = new EhrPositionVO();
		}
		try {
			EhrPerson ehrPerson  = ehrPersonService.getByPersonId(personId);
			List<EhrBaseData> post =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), 1, null);//岗位
			List<EhrBaseData> labor =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), 9, null);//用工性质
		    List<EhrBaseData> rank =  baseDataService.selectBaseData(ehrPerson.getCompanyId(), 3, null);//职级
		    List<EhrBaseData> cost  = baseDataService.selectBaseData(ehrPerson.getCompanyId(), 2, null);//成本中心
		    ehrPositionVO.setPosiName(post); 
		    ehrPositionVO.setLabor(labor);
		    ehrPositionVO.setLevel(rank);
		    ehrPositionVO.setCostCenter(cost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ehrPositionVO;
	}
	@Override
	public String updatePosition(EhrPosition ehrPosition) {
		
		if( positionMapper.updateByPrimaryKeySelective(ehrPosition)==1){
			return "success";
		}else{
			return "error";
		}
	}
	

}
