package com.jabava.service.hro.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.jabava.core.ReturnS;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.Config;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.Page;

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
	public List<HroPactInfo> queryProtocolPage(HroPactInfo hroPactInfo,Page<HroPactInfo> page) {
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("pactCode", hroPactInfo.getPactCode());
		map.put("companyId", hroPactInfo.getCompanyId());
		// map.put("state", hroPactInfo.getState());
		map.put("orderBy", hroPactInfo.getOrderBy());
		map.put("isDeleted", hroPactInfo.getIsDeleted());
		map.put("page", page);
		return pactMapper.queryProtocolPage(map);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String addOutsourcing(HroPactInfo hroPactInfo,String companyName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		map.put("clientName", companyName);
		map.put("contractName", String.valueOf(now.getTime()));
		map.put("telephoneNumber",hroPactInfo.getTelephoneNumber());
		map.put("contactEmp", hroPactInfo.getContactEmp());
		map.put("remark", hroPactInfo.getRemark());
		map.put("dataSource", "EHR");
	
		try {
			
			Map<String, Object> result  = requestService.invoke("applyContract", map);
			if(!result.isEmpty() && "0".equals(result.get("resultCode").toString()) ){
				
				Map<String, Object> str = (Map)result.get("resultData");
				hroPactInfo.setPactCode(str.get("protocolCode").toString());
				hroPactInfo.setContractName(Long.toString(now.getTime()));
				if(pactMapper.insertSelective(hroPactInfo)==1){
					return "success";
				}else{
					return "error";
				}
			}else{
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
		
		
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
								
				JSONObject jsonObj=JSONObject.fromObject(result.get("resultData"));  
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
	public ReturnS updateProtocolState(HroPactInfo hroPactInfo) {
		ReturnS returnS =new ReturnS();
		if( pactMapper.updateByPactCode(hroPactInfo)==1){
			return returnS;
		}else{
			return returnS;
		}
	}

	@Override
	public List<HroPactInfo> queryPactInfoList() {
		return pactMapper.queryPactInfoList();
	}

}
