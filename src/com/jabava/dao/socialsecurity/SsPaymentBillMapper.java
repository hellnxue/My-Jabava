package com.jabava.dao.socialsecurity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.socialsecurity.SsPaymentBill;

public interface SsPaymentBillMapper {
	List<SsPaymentBill> listPaymentBillPage(Map<String,Object> params);
	
	SsPaymentBill getValidPaymentBill(@Param("companyId")Long companyId, @Param("socialSecurityAccountId")Long socialSecurityAccountId, 
			@Param("month")String month);
	
	SsPaymentBill selectById(@Param("companyId")Long companyId, @Param("id")Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	int insert(SsPaymentBill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	int insertSelective(SsPaymentBill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	SsPaymentBill selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	int updateByPrimaryKeySelective(SsPaymentBill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ss_payment_bill
	 * @mbggenerated  Tue Apr 19 18:19:13 CST 2016
	 */
	int updateByPrimaryKey(SsPaymentBill record);
}