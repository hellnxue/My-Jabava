package com.jabava.service.hro.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.hro.EfArapDetailEmpNsbMapper;
import com.jabava.dao.hro.EfArapDetailEmpSbMapper;
import com.jabava.dao.hro.EfArapMapper;
import com.jabava.dao.hro.EfReceiptOwnedMapper;
import com.jabava.pojo.hro.EfArap;
import com.jabava.pojo.hro.EfArapDetailEmpNsb;
import com.jabava.pojo.hro.EfArapDetailEmpSb;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.hro.IEfArapService;
import com.jabava.utils.Page;

/**
 * 账单Service实现
 * 
 * @author 郑长山
 * 
 */
@Service
public class EfArapServiceImpl implements IEfArapService {
	@Autowired
	private EfArapMapper efArapMapper;
	@Autowired
	private EfArapDetailEmpNsbMapper efArapDetailEmpNsbMapper;
	@Autowired
	private EfArapDetailEmpSbMapper efArapDetailEmpSbMapper;
	@Autowired
	private EfReceiptOwnedMapper efReceiptOwnedMapper;

	@Override
	public List<EfArap> searchBill(Long CompanyId,String billYm,Page<EfArap> page,String search,String orderBy) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", CompanyId);
		params.put("billYm", billYm);
		params.put("search", search);
		params.put("orderBy", orderBy);
		params.put("page", page);
		List<EfArap> list = efArapMapper.selectBillPage(params);
		return list;
	}

	@Override
	public List<EfArap> searchBill(Long CompanyId, String year, String search, String orderBy) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", CompanyId);
		params.put("year", year);
		params.put("search", search);
		params.put("orderBy", orderBy);
		List<EfArap> list = efArapMapper.selectBill(params);
		return list;
	}

	@Override
	public List<EfArap> searchBill(Long CompanyId,String billYm,String orderBy) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", CompanyId);
		params.put("billYm", billYm);
		//params.put("search", search);
		params.put("orderBy", orderBy);
		List<EfArap> list = efArapMapper.selectBill(params);
		return list;
	}
	
	@Override
	public EfArap findByPrimaryKey(Long billId) {
		return efArapMapper.selectByPrimaryKey(billId);
	}
	
	@Override
	public EfArap findByBillCode(String billCode) {
		return efArapMapper.selectByBillCode(billCode);
	}
	
	@Override
	public List<Map<String,Object>> searchBillDetailHeader(EfArap bill){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("jobNumber", "雇员编号"));
		result.add(this.getFieldHeader("employeeName", "姓名"));
		result.add(this.getFieldHeader("certId", "证件号"));
		result.add(this.getFieldHeader("workLocation", "工作地"));
		result.add(this.getFieldHeader("billYm", "账单年月"));
		result.add(this.getFieldHeader("serverYm", "服务年月"));
		
		result.add(this.getFieldHeader("amountE", "单位小计"));
		result.add(this.getFieldHeader("amountP", "个人小计"));
		result.add(this.getFieldHeader("total", "合计"));
		
		//社保
		//城市/金额/单位基数/单位比例/单位附加额/单位金额/个人基数/个人比例/个人附加额/个人金额
		List<Map<String,Object>> sbHeaderList = efArapDetailEmpSbMapper.selectHeaderListByBillId(bill.getBillId());
		for(Map<String,Object> header : sbHeaderList){
			String prodId = header.get("prodId").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			//cols.add(this.getFieldHeader("cityName_" + prodId, "城市"));
			//cols.add(this.getFieldHeader("amount_" + prodId, "金额"));
			cols.add(this.getFieldHeader("baseE_" + prodId, "单位基数"));
			//cols.add(this.getFieldHeader("ratioE_" + prodId, "单位比例"));
			//cols.add(this.getFieldHeader("addAmtE_" + prodId, "单位附加额"));
			cols.add(this.getFieldHeader("baseP_" + prodId, "个人基数"));
			cols.add(this.getFieldHeader("amountE_" + prodId, "单位金额"));
			//cols.add(this.getFieldHeader("ratioP_" + prodId, "个人比例"));
			//cols.add(this.getFieldHeader("addAmtP_" + prodId, "个人附加额"));
			cols.add(this.getFieldHeader("amountP_" + prodId, "个人金额"));
			cols.add(this.getFieldHeader("amount_" + prodId, "总金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prodId", prodId);
			group.put("prodName",header.get("prodName"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		
		//非社保
		//城市/金额/单位基数/单位比例/单位附加额/单位金额/个人基数/个人比例/个人附加额/个人金额
		List<Map<String,Object>> nsbHeaderList = efArapDetailEmpNsbMapper.selectHeaderListByBillId(bill.getBillId());
		for(Map<String,Object> header : nsbHeaderList){
			String prodId = header.get("prodId").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("amount_" + prodId, "个人金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prodId", prodId);
			group.put("prodName",header.get("prodName"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}
	
	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}

	@Override
	//public List<Object> searchBillDetail(Long billId,String search,Page<EfArap> page,String orderBy){
	public List<Map<String,Object>> searchBillDetailData(EfArap bill,final String order,final String by){
		//社保增加：CITY_ID，SB_YM，POLICY_GROUP_ID，AMOUNT_E，AMOUNT_P，BASE_E，BASE_P，RATIO_E，RATIO_P，ADD_AMT_E，ADD_AMT_P
		//非社保增加：QUOT_ID，QUOT_TYPE
		
		//Map<Long,Map<String,Object>> personMap = new HashMap<Long,Map<String,Object>>();
		Map<String,Map<String,Object>> personMap = new HashMap<String,Map<String,Object>>();
		Map<String,Object> data = null;
		String key = null;
		
		//根据billId获取所有社保，并以serverYm+personId为主键、社保列表为值放入Map中
		List<EfArapDetailEmpSb> sbList = efArapDetailEmpSbMapper.selectByBillId(bill.getBillId());
		for(EfArapDetailEmpSb sb : sbList){
			if(sb.getPersonId() != null){
				key = sb.getServerYm() + sb.getPersonId();
				//if(personMap.containsKey(sb.getPersonId())){
				//	data = personMap.get(sb.getPersonId());
				if(personMap.containsKey(key)){
					data = personMap.get(key);
				}else{
					data = new HashMap<String,Object>();
				    data.put("jobNumber", sb.getEmpId());
					data.put("employeeName", sb.getEmployeeName());
					data.put("certId", sb.getCertId());
					data.put("workLocation", sb.getWorkLocation());
					data.put("billYm", sb.getBillYm());
					data.put("serverYm", sb.getServerYm());
					//personMap.put(sb.getPersonId(), data);
					personMap.put(key, data);
				}
				Long prodId = sb.getProdId();
				data.put("cityName_" + prodId, sb.getCityName());
				data.put("amount_" + prodId, sb.getAmount());
				data.put("baseE_" + prodId, sb.getBaseE());
				data.put("ratioE_" + prodId, sb.getRatioE());
				data.put("addAmtE_" + prodId, sb.getAddAmtE());
				data.put("amountE_" + prodId, sb.getAmountE());
				data.put("baseP_" + prodId, sb.getBaseP());
				data.put("ratioP_" + prodId, sb.getRatioP());
				data.put("addAmtP_" + prodId, sb.getAddAmtP());
				data.put("amountP_" + prodId, sb.getAmountP());
				this.countSb(data, sb.getAmountE(), sb.getAmountP());
			}
		}
		
		//根据billId获取所有非社保，并以personId为主键、非社保列表为值放入Map中
		List<EfArapDetailEmpNsb> nsbList = efArapDetailEmpNsbMapper.selectByBillId(bill.getBillId());
		for(EfArapDetailEmpNsb nsb : nsbList){
			if(nsb.getPersonId() != null){
				key = nsb.getServerYm() + nsb.getPersonId();
				//if(personMap.containsKey(nsb.getPersonId())){
				//	data = personMap.get(nsb.getPersonId());
				if(personMap.containsKey(key)){
					data = personMap.get(key);
				}else{
					data = new HashMap<String,Object>();
					data.put("jobNumber", nsb.getEmpId());
					data.put("employeeName", nsb.getEmployeeName());
					data.put("certId", nsb.getCertId());
					data.put("workLocation", nsb.getWorkLocation());
					data.put("billYm", nsb.getBillYm());
					data.put("serverYm", nsb.getServerYm());
					//personMap.put(nsb.getPersonId(), data);
					personMap.put(key, data);
				}
				Long prodId = nsb.getProdId();
				data.put("amount_" + prodId, nsb.getAmount());
				this.countNsb(data, nsb.getAmount());
			}
		}
		
		//排序
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.addAll(personMap.values());
		
		Collections.sort(result, new Comparator<Map<String,Object>>() {

			@Override
			public int compare(Map<String, Object> m1, Map<String, Object> m2) {
				Object o1 = m1.get(order);
				Object o2 = m2.get(order);
				if(o1 != null && o2 != null){
					if(o1 instanceof BigDecimal){
						return "asc".equalsIgnoreCase(by) ? ((BigDecimal)o1).compareTo((BigDecimal)o2) : ((BigDecimal)o2).compareTo((BigDecimal)o1);
					}else if(o1 instanceof Date){
						return "asc".equalsIgnoreCase(by) ? ((Date)o1).compareTo((Date)o2) : ((Date)o2).compareTo((Date)o1);
					}else{
						return "asc".equalsIgnoreCase(by) ? o1.toString().compareTo(o2.toString()) : o2.toString().compareTo(o1.toString());
					}
				}else if(o1 != null){
					return "asc".equalsIgnoreCase(by) ? 1 : -1;
				}else if(o2 != null){
					return "asc".equalsIgnoreCase(by) ? -1 : 1;
				}
				
				return 0;
			}
			
		});
		
		return result;
	}
	
	private void countSb(Map<String,Object> base,BigDecimal e,BigDecimal p){
		if(base.get("total") == null){
			base.put("total", new BigDecimal(0));
		}
		
		if(e != null){
			if(base.get("amountE") == null){
				base.put("amountE", e);
			}else{
				base.put("amountE", e.add((BigDecimal)base.get("amountE")));
			}
			base.put("total", e.add((BigDecimal)base.get("total")));
		}
		
		if(p != null){
			if(base.get("amountP") == null){
				base.put("amountP", p);
			}else{
				base.put("amountP", p.add((BigDecimal)base.get("amountP")));
			}
			base.put("total", p.add((BigDecimal)base.get("total")));
		}
	}
	
	private void countNsb(Map<String,Object> base,BigDecimal amount){
		if(amount != null){
			if(base.get("total") == null){
				base.put("total", amount);
			}else{
				base.put("total", amount.add((BigDecimal)base.get("total")));
			}
			
			//非社保部分既不计入个人小计，也不计入企业小计
//			if(base.get("amountP") == null){
//				base.put("amountP", amount);
//			}else{
//				base.put("amountP", amount.add((BigDecimal)base.get("amountP")));
//			}
		}
	}
	
	@Override
	public int isExitBill(String billCode) {
		//EfArap efArap = efArapMapper.selectByPrimaryKey(bill_id);
		EfArap efArap = efArapMapper.selectByBillCode(billCode);
		if (efArap != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public int insertSelective(EfArap efArap) {
		efArapMapper.insertSelective(efArap);
		if(efArap.getDetailEmpSbList() != null && !efArap.getDetailEmpSbList().isEmpty()){
			efArapDetailEmpSbMapper.insertOrUpdateList(efArap.getDetailEmpSbList());
		}
		if(efArap.getDetailEmpNsbList() != null && !efArap.getDetailEmpNsbList().isEmpty()){
			efArapDetailEmpNsbMapper.insertOrUpdateList(efArap.getDetailEmpNsbList());
		}
		
		return 1;
	}

	@Override
	public int updateByPrimaryKey(EfArap efArap) {
		efArapMapper.updateByPrimaryKey(efArap);
		
		if(efArap.getDetailEmpSbList() != null && !efArap.getDetailEmpSbList().isEmpty()){
			efArapDetailEmpSbMapper.insertOrUpdateList(efArap.getDetailEmpSbList());
		}
		if(efArap.getDetailEmpNsbList() != null && !efArap.getDetailEmpNsbList().isEmpty()){
			efArapDetailEmpNsbMapper.insertOrUpdateList(efArap.getDetailEmpNsbList());
		}
		
		return 1;
	}

	// **********************************
	@Override
	public int isExitDetailEmp(Long billDetailId) {
		EfArapDetailEmpNsb efArapDetailEmp = efArapDetailEmpNsbMapper
				.selectByPrimaryKey(billDetailId);
		if (efArapDetailEmp != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public int insertSelective(EfArapDetailEmpNsb efArapDetailEmp) {
		return efArapDetailEmpNsbMapper.insertSelective(efArapDetailEmp);
	}

	@Override
	public int updateByPrimaryKey(EfArapDetailEmpNsb efArapDetailEmp) {
		return efArapDetailEmpNsbMapper.updateByPrimaryKey(efArapDetailEmp);
	}

	// **********************************
	@Override
	public int isExitDetailEmpSb(Long billDetailId) {
		EfArapDetailEmpSb efArapDetailEmpSb = efArapDetailEmpSbMapper
				.selectByPrimaryKey(billDetailId);
		if (efArapDetailEmpSb != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public int insertSelective(EfArapDetailEmpSb efArapDetailEmpSb) {
		return efArapDetailEmpSbMapper.insertSelective(efArapDetailEmpSb);
	}

	@Override
	public int updateByPrimaryKey(EfArapDetailEmpSb efArapDetailEmpSb) {
		return efArapDetailEmpSbMapper.updateByPrimaryKey(efArapDetailEmpSb);
	}

	@Override
	public boolean isBillExist(String protocolCode) {
		int count = efArapMapper.countByProtocolCode(protocolCode);
		return count > 0;
	}

	@Override
	public List<Map<String, Object>> queryBalanceList(EhrUser user, String year) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int curYear = c.get(Calendar.YEAR);
		int curMonth = c.get(Calendar.MONTH) + 1;
		if(year == null){
			year = String.valueOf(curYear);
		}
		
		//获取各月应付并放入Map
		Map<String,Object> payableMap = new HashMap<String,Object>();
		List<Map<String,Object>> payableList = efArapMapper.queryAmountByYear(user.getCompanyId(), year);
		for(Map<String,Object> m : payableList){
			payableMap.put(m.get("ym").toString(), m.get("amount"));
		}
		
		//获取各月实付并放入Map
		Map<String,Object> payedMap = new HashMap<String,Object>();
		List<Map<String,Object>> payedList = efReceiptOwnedMapper.queryAmountByYear(user.getCompanyId(), year);
		for(Map<String,Object> m : payedList){
			payedMap.put(m.get("ym").toString(), m.get("amount"));
		}
		
		//获取历史应付
		BigDecimal hisPayable =  efArapMapper.queryAmountBeforeYear(user.getCompanyId(), year);
		if(hisPayable == null){
			hisPayable = BigDecimal.ZERO;
		}
		
		//获取历史实付
		BigDecimal hisPayed =  efReceiptOwnedMapper.queryAmountBeforeYear(user.getCompanyId(), year);
		if(hisPayed == null){
			hisPayed = BigDecimal.ZERO;
		}
		
		String ym = null;
		Map<String,Object> record = null;
		//BigDecimal balance = hisPayable.subtract(hisPayed);
		BigDecimal balance = hisPayed.subtract(hisPayable);
		BigDecimal payable = null;
		BigDecimal payed = null;
		for(int i = 1; i <= 12; i ++){
			ym = year + (i >= 10 ? i : "0" + i);
			record = new HashMap<String,Object>();
			record.put("ym", ym);
			//超过当前月的不需要显示内容
			if(Integer.parseInt(year) < curYear || Integer.parseInt(year) == curYear && i <= curMonth){
				payable = payableMap.get(ym) == null ? BigDecimal.ZERO : (BigDecimal)payableMap.get(ym);
				payed = payedMap.get(ym) == null ? BigDecimal.ZERO : (BigDecimal)payedMap.get(ym);
				//当前月余额 + 历史余额
				//balance = payable.subtract(payed).add(balance);
				balance = payed.subtract(payable).add(balance);
				record.put("payable", payable);
				record.put("payed", payed);
				record.put("balance", balance);
				
				result.add(record);
			}else{
				break;
			}
		}
		
		return result;
	}

	@Override
	public int deleteById(EfArap efArap) {
		efArapDetailEmpNsbMapper.deleteByBillId(efArap.getBillId());
		efArapDetailEmpSbMapper.deleteByBillId(efArap.getBillId());
		return efArapMapper.deleteByPrimaryKey(efArap.getBillId());
	}
	
}
