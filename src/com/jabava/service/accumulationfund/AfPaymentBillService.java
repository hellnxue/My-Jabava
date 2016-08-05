package com.jabava.service.accumulationfund;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.accumulationfund.AfPaymentBill;
import com.jabava.pojo.manage.EhrUser;

public interface AfPaymentBillService {
	List<AfPaymentBill> listPaymentBillPage(Map<String, Object> params);
	
	int generatePaymentBill(EhrUser user, Long accumulationFundAccountId, String month) throws Exception;
	
	int changeStatus(EhrUser user, Long id, Integer status) throws Exception;
	
	AfPaymentBill findByPrimaryKey(Long afPaymentBillId);
	
	AfPaymentBill findByMonthAndAfAccount(Long companyId, String month, Long accumulationFundAccountId);
	
	List<Map<String,Object>> loadPaymentBillPersonHeader(AfPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPerson(AfPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonZyHeader(AfPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonZy(AfPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonJyHeader(AfPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonJy(AfPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonTjHeader(AfPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonTj(AfPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonBjHeader(AfPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonBj(AfPaymentBill bill);
	
	Map<String,Object> queryPersonByCertIdAndMonth(String cardId, String month);
	
	List<Map<String,Object>> listPersonDetail(Long afPaymentBillPersonId);
	
}
