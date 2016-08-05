package com.jabava.service.provider;

import java.util.Map;

import net.sf.json.JSONObject;

public interface ProtocolService {
	/**
	 * 同步协议到Jabava
	 * @param paramJson
	 * {"userCode":"happyjabava","pactCode":"ZY-EHR-20160219-0001","contractName":"abcdefg"}
	 * @return
	 * {"resultCode":"0","resultMsg":"同步成功"}
	 */
	public Map<String,Object> syncProtocol(JSONObject paramJson) throws Exception;
	
	public Map<String,Object> queryProtocol(JSONObject paramJson) throws Exception;
}
