package com.jabava.dao.hro.order;

import java.util.List;

import com.jabava.pojo.hro.order.HsEmpOrdNsbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdRec;

public interface HsEmpOrdNsbDetailRecMapper {
	/**
	 *  批量新增或者更新，如果 ordNsbRecId 存在就更新，如果ordNsbRecId 不存在就新增
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月21日 下午6:54:22 
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertOrUpdateList(List<HsEmpOrdNsbDetailRec> list);
	/**
	 * 查询非社保公积金列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:49:52 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	public List<HsEmpOrdNsbDetailRec> findOrderNSB(HsEmpOrdRec hsEmpOrdRec);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    int insert(HsEmpOrdNsbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    int insertSelective(HsEmpOrdNsbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    HsEmpOrdNsbDetailRec selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HsEmpOrdNsbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_nsb_detail_rec
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HsEmpOrdNsbDetailRec record);
}