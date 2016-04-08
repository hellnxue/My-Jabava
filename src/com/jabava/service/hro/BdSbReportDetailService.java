package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.utils.Page;

public interface BdSbReportDetailService {
	    /**
		 *  查询社保/公积金信息
		  
		 * @param policygrouptype 政策包类型：1-社保 2-公积金
		 * @param reportMonth 年月
		 * @param orderBy 排序
		 * @param page 分页
		 * @return
		 */
		 List<Map<String,Object>> getSheBaoGongJiJinInfoPage(Long companyId,String search,int policygrouptype,String reportMonth,String orderBy,Page<Map<String, Object>> page);
		 

		/**
		 * 查询社保公积金详情
		 * @param employeeId 员工ID
		 * @param policyGroupType 社保公积金：1社保  2公积金
		 * @param payType  状态：1.汇缴；2.补缴   
		 * @param backpayType  补缴类型： 1.补缴 2.补差 
		 * @param orderBy 排序
		 * @return
		 */
	   	List<Map<String,Object>> getSheBaoGongJiJinInfoDetailPage(Long companyId,String reportMonth,Long employeeId,int policyGroupType,int payType,Integer backpayType,String orderBy,Page<Map<String, Object>> page);
	   	
		/**
	   	 * 查询每个年月缴纳社保或公积金的总人数
	   	 * @param map
	   	 * @return
	   	 */
	   	List<Map<String,Object>> getSbGjjTotalByReportMonthPage(int policyGroupType,String reportMonth,Long companyId,Page<Map<String, Object>> page);
}
