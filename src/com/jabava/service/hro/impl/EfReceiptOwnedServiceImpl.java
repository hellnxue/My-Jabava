package com.jabava.service.hro.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.hro.EfArapMapper;
import com.jabava.dao.hro.EfReceiptOwnedMapper;
import com.jabava.pojo.hro.EfReceiptOwned;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfReceiptOwnedService;
import com.jabava.utils.JabavaUtil;

/**
 * 付款Service实现
 * 
 * @author 王振波
 * 
 */
@Service
public class EfReceiptOwnedServiceImpl implements IEfReceiptOwnedService {
	@Autowired
	private EfReceiptOwnedMapper receiptOwnedMapper;
	
	@Autowired
	private EfArapMapper efArapMapper;
	
	@Override
	public int insertOrUpdateList(List<EfReceiptOwned> recordList) {
		return receiptOwnedMapper.insertOrUpdateList(recordList);
	}

	@Override
	public List<Map<String, Object>> queryReceiptOwnedList(EhrUser user,
			String ym, String amount) throws Exception{
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("ym", ym);
		List<EfReceiptOwned> roList = receiptOwnedMapper.queryReceiptOwnedList(params);
		if(roList != null && !roList.isEmpty()){
			Map<String,Object> record = null;
			EfReceiptOwned ro = null;
			Integer serviceNums = efArapMapper.queryPersonCount(user.getCompanyId(), ym);
			for(int i = 0; i < roList.size(); i ++){
				ro = roList.get(i);
				record = new HashMap<String,Object>();
				record.put("ym", ym);
				record.put("amount", amount);
				record.put("serviceNums", serviceNums);
				record.put("transDate", JabavaUtil.parseDate(ro.getTransDate(), "yyyy-MM-dd"));
				record.put("matchAmount", ro.getMatchAmount());
				if(i == 0){
					record.put("rowspan", roList.size());
				}
				
				result.add(record);
			}
		}
		
		return result;
	}
	
}
