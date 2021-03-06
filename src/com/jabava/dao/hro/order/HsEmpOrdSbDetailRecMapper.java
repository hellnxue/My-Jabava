package com.jabava.dao.hro.order;

import java.util.List;

import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetailRec;

public interface HsEmpOrdSbDetailRecMapper {
	/**
	 * 批量新增或者更新，如果 ordSbRecId 存在就更新，如果ordNsbRecId 不存在就新增
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月21日 下午7:08:11 
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertOrUpdateList(List<HsEmpOrdSbDetailRec> list);
	/**
	 * 查询社保公积金列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:50:18 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	public List<HsEmpOrdSbDetailRec> findOrderSB(HsEmpOrdRec hsEmpOrdRec);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    int insert(HsEmpOrdSbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    int insertSelective(HsEmpOrdSbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    HsEmpOrdSbDetailRec selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HsEmpOrdSbDetailRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord_sb_detail_rec
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HsEmpOrdSbDetailRec record);
}