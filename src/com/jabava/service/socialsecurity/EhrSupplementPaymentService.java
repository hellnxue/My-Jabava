package com.jabava.service.socialsecurity;

import java.util.List;

import com.jabava.pojo.socialsecurity.EhrSupplementPayment;

public interface EhrSupplementPaymentService {

	public int addBatchSupplementPaymentInfo(List<EhrSupplementPayment> list);
	
	public List <EhrSupplementPayment> getSupplementPaymentInfo(Long personId,String month);
}
