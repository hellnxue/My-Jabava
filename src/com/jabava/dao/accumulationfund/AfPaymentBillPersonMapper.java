package com.jabava.dao.accumulationfund;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.accumulationfund.AfPaymentBillPerson;

public interface AfPaymentBillPersonMapper {
	List<AfPaymentBillPerson> selectByBillId(Long afPaymentBillId);
	
	Map<String,Object> selectByPersonAndMonth(@Param("month")String month, @Param("personId")Long personId);
	
	List<Map<String,Object>> selectBillPersonByPerson(Map<String,Object> params);
	
	Map<String, Object> queryByCertIdAndMonth(@Param("cardId")String cardId, @Param("month")String month);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long afPaymentBillPersonId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    int insert(AfPaymentBillPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    int insertSelective(AfPaymentBillPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    AfPaymentBillPerson selectByPrimaryKey(Long afPaymentBillPersonId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AfPaymentBillPerson record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table af_payment_bill_person
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AfPaymentBillPerson record);
}