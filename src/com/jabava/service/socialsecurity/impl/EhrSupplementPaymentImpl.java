package com.jabava.service.socialsecurity.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.jabava.dao.socialsecurity.EhrSupplementPaymentMapper;
import com.jabava.pojo.socialsecurity.EhrSupplementPayment;
import com.jabava.service.socialsecurity.EhrSupplementPaymentService;

@Service
public class EhrSupplementPaymentImpl implements EhrSupplementPaymentService{
	public static Logger log = Logger.getLogger(EhrSupplementPaymentImpl.class);
	
	@Resource
	private EhrSupplementPaymentMapper ehrSupplementPaymentMapper;
	public int addBatchSupplementPaymentInfo(List<EhrSupplementPayment> list) {
		EhrSupplementPayment first = list.get(0);
		if(first != null){
			String operateMonth = first.getOperateTime();
			Integer personId = first.getPersonId();
			
			log.error("==== removing the data now");
			
			ehrSupplementPaymentMapper.deleteSupplementPaymentInfo(personId.longValue(),operateMonth);
		}
		return ehrSupplementPaymentMapper.addBatchSupplementPaymentInfo(list);
	}
	
	public List <EhrSupplementPayment> getSupplementPaymentInfo(Long personId,String month){
		return ehrSupplementPaymentMapper.getSupplementPaymentByMonth(personId, month);
	}
}
