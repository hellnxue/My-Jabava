package com.jabava.service.hro.order;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdDetailVO;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdVO;

/**
 * 订单 
 *
 * @version $Id: OrderService.java, 
 * v 0.1 2016年1月23日 下午4:35:23 
 * <pre>
 * @author steven.chen
 * @date 2016年1月23日 下午4:35:23 
 * </pre>
 */
public interface OrderService {
	/**
	 * 订单列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:34:18 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> findOrderListPage(Map<String, Object> map);
	/**
	 * 订单详情
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:34:27 
	 * </pre>
	 *
	 * @param hsEmpOrd
	 * @return
	 */
	HsEmpOrdVO findOrderDetail(HsEmpOrd hsEmpOrd);
	/**
	 * 订单详情 - 社保公积金
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:34:43 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	List<HsEmpOrdSbDetailRec> findOrderSB(HsEmpOrdRec hsEmpOrdRec);
	/**
	 * 订单详情 -非社保公积金
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月23日 下午4:35:01 
	 * </pre>
	 *
	 * @param orderId
	 * @return
	 */
	List<HsEmpOrdNsbDetailRec> findOrderNSB(HsEmpOrdRec hsEmpOrdRec);
	/**
	 * 根据订单ID 账单月或者服务月查询订单费用信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月26日 上午10:19:25 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdDetailVO> findOrderCostPage(Map<String, Object> map);
	/**
	 * 查询订单列表 (不分页)
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月28日 下午2:15:33 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> findOrderList(Map<String, Object> map);
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
	 * 订单查询服务人数
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月16日 下午10:14:20 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> findServiceCountPage(Map<String, Object> map);
	/**
	 * 新版的订单列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月17日 上午10:09:51 
	 * </pre>
	 *
	 * @param map
	 * @return
	 */
	List<HsEmpOrdVO> newFindOrderListPage(Map<String, Object> map);

}
