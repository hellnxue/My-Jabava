package com.jabava.service.employee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.pojo.manage.EhrUserPersonPowerValue;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.utils.Page;
import com.jabava.utils.Tools;

@Service("personService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Resource
	private EhrPersonMapper personMapper;
	@Resource
	private EhrBaseDataMapper baseDataMapper;
	@Resource
	private EhrOrganizationMapper orgMapper;
	
	@Override
	public List<EhrPerson> searchAddress(Long CompanyId,String search,Page<EhrPerson> page,String orderBy,String billYm) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", CompanyId);
		params.put("search", search);
		params.put("orderBy", orderBy);
		params.put("page", page);
		List<EhrPerson> list = personMapper.selectAddressPage(params);
		return list;
	}
	
	@Override
	public List<EhrPerson> searchAllAddress(Long CompanyId,String search) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", CompanyId);
		params.put("search", search);
		List<EhrPerson> list = personMapper.selectByCompanyId(params);
		return list;
	}
	@Override
	public List<EhrPerson> searchPersonByEmployeeNameAndCompanyId(Long companyId,String employeeName){
		EhrPerson person = new EhrPerson();
		person.setCompanyId(companyId);
		person.setEmployeeName(employeeName);
		return personMapper.searchPersonByEmployeeNameAndCompanyId(person);
	}
	@Override
	public List<EhrPerson> searchByOrganizationId(Long organizationId) {
		//Map<String,Object> params = new HashMap<String,Object>();
		//List<Long> list = new ArrayList<Long>();
		//list.add(organizationId);
		return personMapper.selectByOrganizationId(organizationId);
	}

	@Override
	public List<EhrPerson> searchPerson(EhrPerson person, Long userId,
			long companyid, String distinguish) {
		if(person.getOrganizationId() != null && !person.getOrganizationId().equals("") && person.getOrganizationId() != 0){
			String totalCode = orgMapper.selectByPrimaryKey(person.getOrganizationId()).getTotalOrganizationCode();
			person.setTotalCode(totalCode);
		}
		String where = " and  "+getPersonPowerSqlStr(userId, "", 1)+"";
		person.setWhere(where);
		person.setCompanyId(companyid);
		person.setDistinguish(distinguish);
		List<EhrPerson> list = personMapper.searchPerson(person);
		return list;
	}
	
	@Override
	public EhrPerson searchPersonByJobNumber(Long companyId, String jobNumber) {
		return personMapper.searchPersonByJobNumber(companyId, jobNumber);
	}

	public String getPersonPowerSqlStr(Long userId, String prefix, int functionId) {
		if (!prefix.equals(""))
			prefix += ".";
		String result = "";
		List<EhrUserBusinessPower> list = personMapper.userBusinessPower(userId, functionId);
		if(list != null && list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				EhrUserBusinessPower mdl = list.get(i);
				List<EhrUserPersonPowerValue> items = personMapper.getPowerValue(mdl.getUserPersonPowerId());
				if (mdl.getFieldType().intValue() == 1) {
					EhrPersonField personMdl = personMapper.getPersonField(mdl.getFieldId());
					if (personMdl.getFieldType().intValue() >= 4) {
						if(!items.isEmpty()){
							result += " " + prefix + personMdl.getFieldName() + " in(";
							for (int j = 0; j < items.size(); j++) {
								if (j == 0) {
									result += Tools
											.strToStr(items.get(j).getFieldKey());
								} else {
									result += ","
											+ Tools.strToStr(items.get(j).getFieldKey());
								}
							}
							result += ")"; 
						}
					} else {
						if(!items.isEmpty()){
							result += " " + prefix + personMdl.getFieldName();
							if (mdl.getOperateType() == 1) {
								result += "=" + Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 2) {
								result += ">" + Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 3) {
								result += "<" + Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 4) {
								result += ">=" + Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 5) {
								result += "<=" + Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 6) {
								result += " like "
										+ Tools.strToStr("%" + mdl.getFieldValue());
								;
							} else if (mdl.getOperateType() == 7) {
								result += " like "
										+ Tools.strToStr(mdl.getFieldValue() + "%");
								;
							} else if (mdl.getOperateType() == 8) {
								result += " like "
										+ Tools.strToStr("%" + mdl.getFieldValue()
												+ "%");
								;
							} else if (mdl.getOperateType() == 9) {
								result += " in(" + mdl.getFieldValue() + ")";
							}
						}
					}
				} else if (mdl.getFieldType().intValue() == 2 && !items.isEmpty()) {
					result += " and";
				} else if (mdl.getFieldType().intValue() == 3 && !items.isEmpty()) {
					result += " or";
				} else if (mdl.getFieldType().intValue() == 4 && !items.isEmpty()) {
					result += "(";
				} else if (mdl.getFieldType().intValue() == 5 && !items.isEmpty()) {
					result += ")";
				}
			}
		}
		if (result.trim().equals(""))
			return " 1=1";
		return "(" + result + ")";
	}
}
