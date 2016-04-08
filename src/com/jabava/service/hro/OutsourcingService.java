package com.jabava.service.hro;

import java.util.List;

import com.jabava.core.ReturnS;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.utils.Page;

public interface OutsourcingService {
	/**
	 * 分页查询协议列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月14日 上午11:29:48 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	List<HroPactInfo> queryProtocolPage(HroPactInfo hroPactInfo,Page<HroPactInfo> page);
	
	/**
	 * 申请开通
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 上午9:58:09 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	public  String addOutsourcing(HroPactInfo hroPactInfo,String companyName);
	/**
	 * 查询协议
	 * <pre> 
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:23:33 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	public List<HroPactInfo> queryProtocol(HroPactInfo hroPactInfo);
	/**
	 * 查看协议内容
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:25:53 
	 * </pre>
	 *
	 * @param id
	 * @return HroPactInfo
	 */
	public HroPactInfo getProtocolByPactCode(String pactCode);
	/**
	 * 更新协议状态
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:27:10 
	 * </pre>
	 *
	 * @param pactCode
	 * @return
	 */
	public String updateProtocol(HroPactInfo hroPactInfo);
	ReturnS updateProtocolState(HroPactInfo hroPactInfo);
	/**
	 * 根据ID获取协议
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月14日 下午4:27:32 
	 * </pre>
	 *
	 * @param id
	 * @return
	 */	
	HroPactInfo getProtocolById(Long id);
	/**
	 * 获取协议号
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月26日 下午2:05:09 
	 * </pre>
	 *
	 * @return
	 */
	public List<HroPactInfo> queryPactInfoList();

	

}
