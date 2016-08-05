package com.jabava.service.hro.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.enums.JabavaEnum;

/***
 * HRO客户端-申请开通,查询协议
 *
 * @version $Id: OutsourcingServiceImpl.java, 
 * v 0.1 2016年1月8日 上午9:56:57 
 * <pre>
 * @author steven.chen
 * @date 2016年1月8日 上午9:56:57 
 * </pre>
 */
@Service
public class OutsourcingServiceImpl implements OutsourcingService {
	
	@Resource
	private HroPactInfoMapper pactMapper;
	private HROFetchService requestService;
	public OutsourcingServiceImpl(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}
	
	/**
	 * 分页查询协议列表
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:50:52 
	 * @param hroPactInfo
	 * @return
	 * @see com.jabava.service.hro.OutsourcingService#queryProtocol(com.jabava.pojo.hro.HroPactInfo)
	 */
	@Override
	public List<HroPactInfo> queryProtocolPage(HroPactInfo hroPactInfo,String search,String order,Page<HroPactInfo> page) {
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("pactCode", hroPactInfo.getPactCode());
		map.put("companyId", hroPactInfo.getCompanyId());
		map.put("search", search);
		map.put("orderBy", order);
		map.put("isDeleted", hroPactInfo.getIsDeleted());
		map.put("page", page);
		return pactMapper.queryProtocolPage(map);
	}
	
	/**
	 * 查询协议列表
	 */
	@Override
	public List<HroPactInfo> queryProtocolByCompany(Long companyId) {
		return pactMapper.queryProtocolByCompany(companyId);
	}

	@Override
	public Map<String, Object> addOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception{
		hroPactInfo.setCompanyId(user.getCompany().getCompanyId());
		hroPactInfo.setState(JabavaEnum.ProtocolStatus.NOT_SUBMIT.getValue());
		hroPactInfo.setIsDeleted(0);
		pactMapper.insertSelective(hroPactInfo);
		
		return MessageUtil.successMessage("新增成功");
	}
	
	@Override
	public Map<String, Object> updateOutsourcing(HroPactInfo hroPactInfo, HroPactInfo exist, EhrUser user) throws Exception {
		//只有未提交状态才可以更新
		if(!JabavaEnum.ProtocolStatus.NOT_SUBMIT.getValue().equals(exist.getState())){
			return MessageUtil.errorMessage("当前状态不允许修改");
		}
		
		//hroPactInfo.setCompanyId(user.getCompany().getCompanyId());
		//hroPactInfo.setState(JabavaEnum.ProtocolStatus.NOT_SUBMIT.getValue());
		pactMapper.updateByPrimaryKeySelective(hroPactInfo);
		
		return MessageUtil.successMessage("修改成功");
	}
	
	@Override
	public Map<String, Object> submitOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception {
		//只有未提交状态才可以提交
		if(!JabavaEnum.ProtocolStatus.NOT_SUBMIT.getValue().equals(hroPactInfo.getState())){
			return MessageUtil.errorMessage("当前状态不允许提交");
		}
		
		//调用HRO接口 
		String msg = this.applyContract(hroPactInfo, user);
		if(!StringUtils.isEmpty(msg)){
			return MessageUtil.errorMessage(msg);
		}
		
		//更新状态及协议号
		hroPactInfo.setState(JabavaEnum.ProtocolStatus.NOT_OPEN.getValue());
		pactMapper.updateByPrimaryKeySelective(hroPactInfo);
		
		return MessageUtil.successMessage("提交成功");
	}
	@Override
	public Map<String,Object> addAndSubmitOutsourcing(HroPactInfo hroPactInfo, EhrUser user) throws Exception{
		String msg = this.applyContract(hroPactInfo, user);
		if(!StringUtils.isEmpty(msg)){
			return MessageUtil.errorMessage(msg);
		}
		
		hroPactInfo.setCompanyId(user.getCompany().getCompanyId());
		hroPactInfo.setState(JabavaEnum.ProtocolStatus.NOT_OPEN.getValue());
		hroPactInfo.setIsDeleted(0);
		pactMapper.insertSelective(hroPactInfo);
		
		return MessageUtil.successMessage("保存提交成功");
	}
	
	private String applyContract(HroPactInfo hroPactInfo, EhrUser user) throws Exception{
		StringBuffer sb = new StringBuffer();
		//城市需要转换为名称
		sb.append("产品：").append(hroPactInfo.getProductName()).append("\n")
			.append("服务商：").append(hroPactInfo.getProviderName()).append("\n")
			.append("服务城市：").append(hroPactInfo.getServiceCity()).append("\n")
			.append("服务人数：").append(hroPactInfo.getServicePersonNum()).append("\n")
			.append("服务起始日期：").append(hroPactInfo.getServiceStartDate()).append("\n");
		hroPactInfo.setRemark(sb.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		map.put("clientName", user.getCompany().getCompanyName());
		map.put("contractName", String.valueOf(now.getTime()));
		map.put("telephoneNumber",hroPactInfo.getTelephoneNumber());
		map.put("contactEmp", hroPactInfo.getContactEmp());
		map.put("remark", hroPactInfo.getRemark());
		map.put("dataSource", "EHR");
	
		Map<String, Object> result  = requestService.invoke("applyContract", map);
		if(!result.isEmpty() && "0".equals(result.get("resultCode").toString()) ){
			Map<String, Object> str = (Map)result.get("resultData");
			hroPactInfo.setPactCode(str.get("protocolCode").toString());
			hroPactInfo.setContractName(Long.toString(now.getTime()));
			return null;
		}
		
		if(result.get("resultMessage") == null || StringUtils.isEmpty(result.get("resultMessage").toString())){
			return "调用申请接口返回为空";
		}else{
			return result.get("resultMessage").toString();
		}
	}


	/**
	 * 查询协议列表 
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:50:52 
	 * @param hroPactInfo
	 * @return 
	 * @see com.jabava.service.hro.OutsourcingService#queryProtocol(com.jabava.pojo.hro.HroPactInfo)
	 */
	@Override
	public List<HroPactInfo> queryProtocol(HroPactInfo hroPactInfo) {
		return pactMapper.queryProtocol(hroPactInfo);
	}

	/**
	 * 根据协议号获取协议信息
	 * @author steven.chen
	 * @date 2016年1月8日 下午1:52:06 
	 * @param pactCode
	 * @return
	 * @see com.jabava.service.hro.OutsourcingService#getProtocolByPactCode(java.lang.String)
	 */		
	@Override
	public HroPactInfo getProtocolByPactCode(String pactCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("protocolCode", pactCode);	
		map.put("dataSource", "EHR");		
	
		try {
			Map<String, Object> result  = requestService.invoke("getProtocolCodeState", map);
			if(!result.isEmpty() && "0".equals(result.get("resultCode").toString()) ){			
				JSONObject jsonObj = JSONObject.fromObject(result.get("resultData"));  
				HroPactInfo hroPactInfo =(HroPactInfo) JSONObject.toBean(jsonObj,HroPactInfo.class);  
				hroPactInfo.setPactCode(jsonObj.get("protocolCode").toString());
				return hroPactInfo;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	@Override
	public HroPactInfo getProtocolById(Long id) {
		return pactMapper.selectByPrimaryKey(id);
	}

	@Override
	public String updateProtocol(HroPactInfo hroPactInfo) {
		if( pactMapper.updateByPactCode(hroPactInfo)==1){
			return "success";
		}else{
			return "error";
		}
	}
	
	@Override
	public int updateById(HroPactInfo hroPactInfo){
		return pactMapper.updateByPrimaryKeySelective(hroPactInfo);
	}

	@Override
	public List<HroPactInfo> queryPactInfoList() {
		return pactMapper.queryPactInfoList();
	}

	@Override
	public List<HroPactInfo> queryProtocolByParams(HroPactInfo hroPactInfo){
		return pactMapper.queryProtocolByParams(hroPactInfo);
	}
	
	@Override
	public Map<String, Object> deleteProtocol(HroPactInfo pactInfo) throws Exception {
		//只有未提交状态才可以删除
		if(!JabavaEnum.ProtocolStatus.NOT_SUBMIT.getValue().equals(pactInfo.getState())){
			return MessageUtil.errorMessage("当前状态不允许删除");
		}
		
		pactMapper.deleteByPrimaryKey(pactInfo.getId());
			
		return MessageUtil.successMessage("删除成功");
	}

}
