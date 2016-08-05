package com.jabava.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;

/**
 * 根据协议号获取接更新HroPactInfo 的 单方ID 
 *
 * @version $Id: GetReceiveOrgService.java, v 0.1 2016年4月1日 下午2:57:36
 * 
 *          <pre>
 * @author steven.chen
 * @date 2016年4月1日 下午2:57:36
 * </pre>
 */
@Service
public class GetReceiveOrgService {
	
	@Autowired
	private OutsourcingService outsourcingService;

	private static final Logger logger = Logger
			.getLogger(GetReceiveOrgService.class);
	private HROFetchService requestService;

	public GetReceiveOrgService() {
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");// "http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize",
				JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server + "/open/rest", fetchToken);
	}
	/**
	 * 根据协议号获取接更新HroPactInfo 的 单方ID 
	 * id  和 pactCode 必传
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月1日 下午3:52:36 
	 * </pre>
	 *
	 * @param hroPactInfo
	 * @throws Exception
	 */
	public void getReceiveOrgByProtocolCode(HroPactInfo hroPactInfo)
			throws Exception {
		
		if(hroPactInfo.getId()==null && StringUtils.isBlank(hroPactInfo.getPactCode())){
			logger.info("缺少ID或者 协议号");
			return ;
		}
		//根据ID更新接单方ID
		HroPactInfo pactInfo = new HroPactInfo();
		pactInfo.setId(hroPactInfo.getId());
		JSONArray jsonList = null;
		Map<String, Object> result = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> page = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		page.put("pageSize", 99999);
		data.put("page", page);
		parameter.put("PROTOCOL_CODE", hroPactInfo.getPactCode());
		parameter.put("code", "queryreceiveorgidbyprotocolcode");
		data.put("parameter", parameter);
		try {
			result = requestService.invoke("SqlQuery", data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取结单方ID出错 协议号：  "+hroPactInfo.getPactCode());

		}
		jsonList = TaskUtil.getJSONArray(result);
		//获取接单方ID
		if (jsonList != null && jsonList.size() > 0) {
			com.alibaba.fastjson.JSONObject json = (com.alibaba.fastjson.JSONObject) jsonList
					.get(0);
			if (json != null && json.size() > 0) {
				if (StringUtils.isNotBlank(json.getString("RECEIVE_ORG_ID"))) {
					pactInfo.setReceiveOrgId(Long.parseLong(json.getString(
							"RECEIVE_ORG_ID").toString()));
					//插入协议的结单方ID
					outsourcingService.updateById(pactInfo);

				}
			}

		}
	}

}
