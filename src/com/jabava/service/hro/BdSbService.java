package com.jabava.service.hro;

import java.util.List;
import java.util.Map;



import com.jabava.pojo.hro.BdSb;
import com.jabava.utils.Page;



public interface BdSbService {
	 /**
	 *  查询社保/公积金信息
	  
	 * @param policygrouptype 政策包类型：1-社保 2-公积金
	 * @param reportMonth 年月
	 * @param likeFiled 过滤字段
	 * @param page 分页
	 * @return
	 */
	 List<Map<String,Object>> getSheBaoInfo(int policygrouptype,String reportMonth,String likeFiled,Page<Map<String, Object>> page);
	 
	  /**
		 * 批量新增或者更新社保公积金信息，如果 sbId 存在就更新，如果sbId 不存在就新增
		 * <pre>
		 * @author xueping.liu
		 * @date 2016年1月20日 下午6:22:21 
		 * </pre>
		 *
		 * @param List<BdSb>
		 * @return
		 */
	 int insertSbUpdateList(List<BdSb> list);

}
