package com.jabava.dao.socialsecurity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.socialsecurity.SsPaymentBillPersonDetail;

public interface SsPaymentBillPersonDetailMapper {
	List<Map<String,Object>> listGroupDetail(Long ssPaymentBillId);
	
	List<Map<String,Object>> selectByBillPersonId(Long ssPaymentBillPersonId);
	
	List<Map<String,Object>> listGroupDetailZy(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> selectZyByBillId(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> listGroupDetailJy(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> selectJyByBillId(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> listGroupDetailTj(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> selectTjByBillId(@Param("ssPaymentBillId")Long ssPaymentBillId,
			@Param("lastSsPaymentBillId")Long lastSsPaymentBillId);
	
	List<Map<String,Object>> listGroupDetailBj(@Param("ssPaymentBillId")Long ssPaymentBillId);
	
	List<Map<String,Object>> selectBjByBillId(@Param("ssPaymentBillId")Long ssPaymentBillId);

	List<Map<String,Object>> listGroupDetailByPerson(Map<String,Object> params);
	
	List<Map<String, Object>> listPersonDetail(Long ssPaymentBillPersonId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    int insert(SsPaymentBillPersonDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    int insertSelective(SsPaymentBillPersonDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    SsPaymentBillPersonDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    int updateByPrimaryKeySelective(SsPaymentBillPersonDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_payment_bill_person_detail
     *
     * @mbggenerated Tue Apr 19 19:22:20 CST 2016
     */
    int updateByPrimaryKey(SsPaymentBillPersonDetail record);
}