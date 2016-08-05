package com.jabava.service.salary.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.salary.EhrSalaryDateMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrSalaryDate;
import com.jabava.service.salary.ISalaryDateService;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Tools;

@Service
public class SalaryDateServiceImpl implements ISalaryDateService {
	@Autowired
	private EhrSalaryDateMapper salaryDateMapper;
	
	@Override
	public List<EhrSalaryDate> listSalaryDatePage(Map<String, Object> params) {
		return salaryDateMapper.listSalaryDatePage(params);
	}

	@Override
	public int saveOrUpdate(EhrSalaryDate salaryDate, EhrUser user) {
		salaryDate.setCompanyId(user.getCompanyId());
		if(salaryDate.getSalaryDateId() == null){
			return salaryDateMapper.insert(salaryDate);
		}else{
			return salaryDateMapper.updateByPrimaryKey(salaryDate);
		}
	}

	@Override
	public int deleteById(Long companyId, Long salaryDateId) {
		return salaryDateMapper.deleteById(companyId, salaryDateId);
	}

	@Override
	public EhrSalaryDate selectByChangeDate(Long companyId, Date changeDate) {
		return salaryDateMapper.selectByChangeDate(companyId, changeDate);
	}

	@Override
	public int getPayDayCount(String monthly,Long companyId) throws Exception{
		java.util.Date ed = JabavaUtil.addDate(JabavaUtil.formatDate(monthly + "01", "yyyyMMdd"), Calendar.MONTH, 1);
		return this.getPayDayCountBefore(monthly,companyId,ed);
	}

	@Override
	public int getPayDayCountBefore(String monthly,Long companyId,Date bd) throws Exception {
		java.util.Date d = JabavaUtil.formatDate(monthly + "01", "yyyyMMdd");
		java.util.Date ed = JabavaUtil.formatDate(JabavaUtil.parseDate(bd, "yyyyMMdd"), "yyyyMMdd");
		java.util.Date sed = JabavaUtil.addDate(JabavaUtil.formatDate(monthly + "01", "yyyyMMdd"), Calendar.MONTH, 1);
		if(ed == null || ed.compareTo(sed) > 0){
			ed = sed;
		}
		
		List<EhrSalaryDate> salaryDateList = salaryDateMapper.selectBefore(companyId, d, ed);
		Map<String,Integer> changeTypeMap = new HashMap<String,Integer>();
		//构建日期与变化类型的Map
		for(EhrSalaryDate salaryDate : salaryDateList){
			changeTypeMap.put(JabavaUtil.parseDate(salaryDate.getChangeDate(), "yyyyMMdd"), salaryDate.getChangeType());
		}

		int r = 0;
		while(d.compareTo(ed) < 0){
			Integer t = changeTypeMap.get(JabavaUtil.parseDate(d, "yyyyMMdd"));
			if(t == null || t == 0){
				String w = JabavaUtil.parseDate(d, "E");
				if(w.equals("星期六") || w.equalsIgnoreCase("saturday") || 
						w.equals("星期日") || w.equalsIgnoreCase("sunday")){
					;
				} else {
					 r++;
				 }
			} else if (t.equals("1")){
				 r++;
			} else if (t.equals("2")){
				 ;
			}
			
			d = JabavaUtil.addDate(d, Calendar.DAY_OF_MONTH, 1);
		}
		
		return r;
	}
}
