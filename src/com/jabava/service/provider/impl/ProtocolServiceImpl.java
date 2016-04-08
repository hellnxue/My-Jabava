package com.jabava.service.provider.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.provider.ProtocolService;
import com.jabava.task.GetBill;
import com.jabava.task.GetOrder;
import com.jabava.task.ImportBillTask;
import com.jabava.task.ImportOrderTask;

@Service
public class ProtocolServiceImpl implements ProtocolService{
	@Resource
	private HroPactInfoMapper pactInfoMapper;
	
	@Resource
	private EhrUserMapper userMapper;
	
	@Autowired
	private GetOrder getOrder;
	
	@Autowired
	private GetBill getBill;

	/**
	 * 同步协议到Jabava
	 * @param paramJson
	 * {"userCode":"happyjabava","pactCode":"ZY-EHR-20160219-0001","contractName":"abcdefg"}
	 * @return
	 * {"returnCode":"0","returnMessage":"同步成功"}
	 */
	@Override
	public Map<String,Object> syncProtocol(JSONObject paramJson) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("returnCode", "1");
		
		if(paramJson == null){
			result.put("returnMessage", "参数为空");
			return result;
		}
		
		String userCode = paramJson.getString("userCode");
		String pactCode = paramJson.getString("pactCode");
		String contractName = paramJson.getString("contractName");
		if(StringUtils.isEmpty(userCode)){
			result.put("returnMessage", "用户编码为空");
			return result;
		}
		if(StringUtils.isEmpty(pactCode)){
			result.put("returnMessage", "协议号为空");
			return result;
		}
		if(StringUtils.isEmpty(contractName)){
			result.put("returnMessage", "合同名称为空");
			return result;
		}
		
		//新增协议号
		EhrUser user = userMapper.searchUserByUserCode(userCode);
		if(user == null){
			result.put("returnMessage", "用户不存在");
			return result;
		}
		
		HroPactInfo existPact = pactInfoMapper.selectByPactCode(pactCode);
		if(existPact != null){
			result.put("returnMessage", "协议号已存在");
			return result;
		}
		
		HroPactInfo pact = new HroPactInfo();
		pact.setCompanyId(user.getCompanyId());
		pact.setPactCode(pactCode);
		pact.setPactName(pactCode);
		pact.setContactEmp(user.getUserName());
		pact.setContractName(contractName);
		pact.setTelephoneNumber(user.getMobile());
		pact.setIsDeleted(0);
		pact.setState(2);
		pactInfoMapper.insertSelective(pact);

		result.put("returnCode", "0");
		result.put("returnMessage", "同步成功");

		//2016.3.31版本-推送成功后立即同步数据
		//启动新线程同步
		ExecutorService pool = Executors.newFixedThreadPool(2);
		final HroPactInfo pactInfo = new HroPactInfo();
		pactInfo.setCompanyId(user.getCompanyId());
		pactInfo.setPactCode(pactCode);
		
		pool.execute(new Runnable() {
			@Override
			public void run() {	//同步订单
				for(int i = 0; i < 10000; i ++){
					try {
						getOrder.getOrderByProcotolCode(pactInfo);
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		pool.execute(new Runnable() {
			@Override
			public void run() {	//同步账单
				for(int i = 0; i < 10000; i ++){
					try {
						getBill.executeWithPactInfo(pactInfo);
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		return result;
	}
	
}
