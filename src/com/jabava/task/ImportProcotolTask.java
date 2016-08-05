package com.jabava.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;

/**
 * 更新协议的状态
 *
 * @version $Id: ImportProcotolTask.java, v 0.1 2016年1月11日 下午4:14:58
 * 
 *          <pre>
 * @author steven.chen
 * @date 2016年1月11日 下午4:14:58
 * </pre>
 */
@Service
public class ImportProcotolTask {

	@Autowired
	private OutsourcingService outsourcingService;
	@Autowired
	private GetReceiveOrgService getReceiveOrgService;

	private static final Logger logger = Logger
			.getLogger(ImportProcotolTask.class);
	private HROFetchService requestService;
	public ImportProcotolTask(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}

	/**
	 * 获取协议内容后更新协议状态
	 * 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月11日 下午4:16:10
	 * </pre>
	 *
	 * @throws Exception
	 */
	public void execute() throws Exception {
		logger.info(" ImportProcotolTask  Start .................");

		HroPactInfo hroPactInfo = new HroPactInfo();

		// 获取协议
		List<HroPactInfo> hroPactInfoList = outsourcingService
				.queryProtocol(hroPactInfo);
		int sum=0;

		if (hroPactInfoList != null && hroPactInfoList.size() > 0) {
			for (HroPactInfo hroPact : hroPactInfoList) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("protocolCode", hroPact.getPactCode());
				map.put("dataSource", "EHR");

				try {

					Map<String, Object>  result = requestService.invoke("getProtocolCodeState", map);	
					if (!result.isEmpty()
							&& "0".equals(result.get("resultCode").toString())) {

						JSONObject jsonObj = JSONObject.fromObject(result
								.get("resultData"));
						HroPactInfo hroPactInfo3 = (HroPactInfo) JSONObject
								.toBean(jsonObj, HroPactInfo.class);
						hroPactInfo3.setPactCode(jsonObj.get("protocolCode")
								.toString());
						hroPactInfo3.setId(hroPact.getId());
						if ("error".equals(outsourcingService
								.updateProtocol(hroPactInfo3))) {
							logger.error("ImportProcotolTask 更新协议失败 ，协议code "
									+ hroPact.getPactCode());
						}
						//插入接单方ID
						getReceiveOrgService.getReceiveOrgByProtocolCode(hroPactInfo3);
						sum++;

					} else {
						logger.error("ImportProcotolTask 获取协议失败 ，协议code "
								+ hroPact.getPactCode()+  "  失败原因 ： "+result.get("resultMessage").toString());
					}
				} catch (Exception e) {
					logger.error(" 获取协议 任务出错 ", e);
					e.printStackTrace();
				}

			}
		}

		logger.info(" ImportProcotolTask  end ................. 本次更新协议 "+ sum +"条");
	}

}