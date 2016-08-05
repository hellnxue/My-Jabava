package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
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
	List<HroPactInfo> queryProtocolPage(HroPactInfo hroPactInfo,String search,String order,Page<HroPactInfo> page);
	
	List<HroPactInfo> queryProtocolByCompany(Long companyId);
	
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
	public Map<String,Object> addOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception;
	
	public Map<String,Object> updateOutsourcing(HroPactInfo hroPactInfo, HroPactInfo exist, EhrUser user) throws Exception;
	
	public Map<String,Object> submitOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception;

	public Map<String,Object> addAndSubmitOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception;
	
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
	
	/**
	 * 根据ID更新协议
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月1日 下午3:21:12 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	int updateById(HroPactInfo hroPactInfo);
	
	/**
	 * 根据参数查询协议
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月1日 下午4:04:32 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @return
	 */
	public List<HroPactInfo> queryProtocolByParams(HroPactInfo hroPactInfo);

	Map<String, Object> deleteProtocol(HroPactInfo pactInfo) throws Exception;

}
