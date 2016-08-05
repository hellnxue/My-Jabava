package com.jabava.service.socialsecurity;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.socialsecurity.SsPaymentBill;

public interface ISsPaymentBillService {
	List<SsPaymentBill> listPaymentBillPage(Map<String, Object> params);
	
	int generatePaymentBill(EhrUser user, Long socialSecurityAccountId, String month) throws Exception;
	
	int changeStatus(EhrUser user, Long id, Integer status) throws Exception;
	
	List<Map<String,Object>> listPaymentBillDetail(Long companyId, Long ssPaymentBillId);
	
	SsPaymentBill findByPrimaryKey(Long ssPaymentBillId);
	
	SsPaymentBill findByMonthAndSsAccount(Long companyId, String month, Long socialSecurityAccountId);
	
	List<Map<String,Object>> loadPaymentBillPersonHeader(SsPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPerson(SsPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonZyHeader(SsPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonZy(SsPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonJyHeader(SsPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonJy(SsPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonTjHeader(SsPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonTj(SsPaymentBill bill);
	
	List<Map<String,Object>> loadPaymentBillPersonBjHeader(SsPaymentBill bill);
	
	List<Map<String,Object>> listPaymentBillPersonBj(SsPaymentBill bill);
	
	Map<String,Object> queryPersonByCertIdAndMonth(String cardId, String month);
	
	List<Map<String,Object>> listPersonDetail(Long ssPaymentBillPersonId);
}
