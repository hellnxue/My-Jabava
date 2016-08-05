package com.jabava.service.salary;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrMonthlySalary;

public interface IMonthlySalaryService {
	List<EhrMonthlySalary> listMonthlySalaryPage(Map<String,Object> params);
	
	Map<String,Object> payoff(EhrUser user, EhrMonthlySalary monthlySalary);
	
	/**
	 * 生成月度工资
	 * @param monthlySalary
	 * @param user
	 * @return
	 * 		错误列表，如果成功则列表为空
	 */
	List<String> generateMonthlySalary(EhrMonthlySalary monthlySalary, EhrUser user) throws Exception;
	
	EhrMonthlySalary selectByPrimaryKey(Long monthlySalaryId);
	
	List<Map<String,Object>> loadMonthlySalaryPersonHeader(Long monthlySalaryId);
	
	List<Map<String,Object>> loadMonthlySalaryPersonPage(Map<String,Object> params);
	
	Map<String,Object> queryPersonByCertIdAndMonth(String cardId, String month);
	
	Map<String,Object> sendSalarySlip(EhrMonthlySalary monthlySalary, String personIds) throws Exception;
}
