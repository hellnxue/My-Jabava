package com.jabava.dao.hro.order;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdVO;

public interface HsEmpOrdMapper {
	/**
	 * 查询订单服务人数
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月16日 下午10:11:34 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> findServiceCountPage(Map<String, Object> map);
	/**
	 * 订单新页面
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月10日 下午9:55:21 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> findNewOrderListPage(Map<String, Object> map);
	
	/**
	 * 分页查询订单列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月22日 下午3:00:35 
	 * </pre>
	 *
	 * @return
	 */
	List<HsEmpOrdVO> findOrderListPage(Map<String, Object> map);
	/**
	 * 查询订单列表 --不分页
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月22日 下午3:00:35 
	 * </pre>
	 *
	 * @return
	 */
	List<HsEmpOrdVO> findOrderList(Map<String, Object> map);
	/**
	 * 订单详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:40:16 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	HsEmpOrdVO  findOrderDetail(HsEmpOrd hsEmpOrd);
	
	/**
	 * 批量新增或者更新订单，如果 orderId 存在就更新，如果orderId 不存在就新增
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月20日 下午6:22:21 
	 * </pre>
	 *
	 * @param hsEmpOrdList
	 * @return
	 */
	int insertOrUpdateList(List<HsEmpOrd> list);
	/**
	 * 根据协议号获取订单总数
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 上午11:27:04 
	 * </pre>
	 *
	 * @param procotolCode
	 * @return
	 */
	Long getOrderTotalByProcotolCode(String procotolCode);
	/**
	 * 批量更新订单
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月20日 下午6:22:34 
	 * </pre>
	 *
	 * @param hsEmpOrdList
	 * @return
	 */
	int updateOrderList(List<HsEmpOrd> hsEmpOrdList);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    int insert(HsEmpOrd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    int insertSelective(HsEmpOrd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    HsEmpOrd selectByPrimaryKey(Long orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HsEmpOrd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hs_emp_ord
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HsEmpOrd record);
}