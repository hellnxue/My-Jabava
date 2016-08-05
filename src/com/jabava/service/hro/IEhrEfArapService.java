package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.EhrEfArap;

/**
 * 账单Service接口
 * 
 */
public interface IEhrEfArapService {
	
	List<Map<String,Object>> syncAndSearchBill(Long companyId,String year) throws Exception;
	
	Map<String,Object> operate(EhrEfArap ehrBill, Integer type) throws Exception;
	
	EhrEfArap findByPrimaryKey(Long billId);
	
	EhrEfArap findByBillCode(String billCode);
	
	List<Map<String,Object>> searchBillDetailHeader(EhrEfArap bill);
	
	List<Map<String,Object>> searchBillDetailData(EhrEfArap bill);
	
	Map<String,Object> checkPayoffStatus(EhrEfArap bill) throws Exception;
	
	Map<String,Object> hroExport(String billIds) throws Exception;

}
