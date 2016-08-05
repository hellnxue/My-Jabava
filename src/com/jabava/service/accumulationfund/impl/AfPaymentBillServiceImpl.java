package com.jabava.service.accumulationfund.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.accumulationfund.AfAccumulationFundAccountMapper;
import com.jabava.dao.accumulationfund.AfPaymentBillMapper;
import com.jabava.dao.accumulationfund.AfPaymentBillPersonDetailMapper;
import com.jabava.dao.accumulationfund.AfPaymentBillPersonMapper;
import com.jabava.dao.policygroup.PolicyProdRatioMapper;
import com.jabava.dao.policygroup.SbGroupDetailMapper;
import com.jabava.dao.socialsecurity.EhrPersonSecurityProfileMapper;
import com.jabava.dao.socialsecurity.EhrSupplementPaymentMapper;
import com.jabava.pojo.accumulationfund.AfAccumulationFundAccountVO;
import com.jabava.pojo.accumulationfund.AfPaymentBill;
import com.jabava.pojo.accumulationfund.AfPaymentBillPerson;
import com.jabava.pojo.accumulationfund.AfPaymentBillPersonDetail;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.policygroup.PolicyProdRatio;
import com.jabava.pojo.policygroup.SbGroupDetail;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.socialsecurity.EhrSecurityItem;
import com.jabava.pojo.socialsecurity.EhrSupplementPayment;
import com.jabava.service.accumulationfund.AfPaymentBillService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.enums.SsAfEnum;

/**
 * 公积金汇缴列表
 */
@Service
public class AfPaymentBillServiceImpl implements AfPaymentBillService {
	private Logger log = Logger.getLogger(AfPaymentBillServiceImpl.class);
	
	@Autowired
	private AfPaymentBillMapper afPaymentBillMapper;
	@Autowired
	private AfPaymentBillPersonMapper afPaymentBillPersonMapper;
	@Autowired
	private AfPaymentBillPersonDetailMapper afPaymentBillPersonDetailMapper;
	@Autowired
	private EhrPersonSecurityProfileMapper personSecurityProfileMapper;
	@Autowired
	private EhrSupplementPaymentMapper supplementPaymentMapper;
	@Autowired
	private AfAccumulationFundAccountMapper afAccumulationFundAccountMapper;
	@Autowired
	private SbGroupDetailMapper sbGroupDetailMapper;
	@Autowired
	private PolicyProdRatioMapper policyProdRatioMapper;

	@Override
	public List<AfPaymentBill> listPaymentBillPage(Map<String,Object> params) {
		return afPaymentBillMapper.listPaymentBillPage(params);
	}

	@Override
	public int generatePaymentBill(EhrUser user,
			Long accumulationFundAccountId, String month) throws Exception {
		// 确定未生成(或已作废)
		AfPaymentBill existBill = afPaymentBillMapper.getValidPaymentBill(user.getCompanyId(), accumulationFundAccountId, month);
		if(existBill != null){
			throw new Exception("当前状态不允许生成");
		}
		
		//创建清单
		AfPaymentBill bill = new AfPaymentBill(user.getCompanyId(), accumulationFundAccountId, month);
		AfAccumulationFundAccountVO afAccount = afAccumulationFundAccountMapper.selectByPrimaryKey(accumulationFundAccountId);
		bill.setAccumulationFundAccountName(afAccount.getAccumulationFundAccountName());
		
		// 根据公积金账户查找并遍历员工公积金档案
		//List<EhrPersonSecurityProfile> pspList = personSecurityProfileMapper.getSecurityProfileByCompanyId(user.getCompanyId(), 2L);
		List<EhrPersonSecurityProfile> pspList = personSecurityProfileMapper.getSecurityProfileByAccount(accumulationFundAccountId, 2L);
		if(pspList == null || pspList.isEmpty()){
			throw new Exception("没有找到员工公积金档案数据");
		}
		
		for(EhrPersonSecurityProfile profile : pspList){
			//已停办或未到办理月的不处理
			if((profile.getGongjijinEndtime() != null && profile.getGongjijinEndtime().compareTo(month) < 0) || 
					profile.getGongjijinActivatetime().compareTo(month) > 0){
				continue;
			}
			
			//停办月当月不收费的不处理
			if(profile.getGongjijinEndtime() != null && profile.getGongjijinEndtime().compareTo(month) == 0){
				if(profile.getGongjijinEndtimeStatus() == 1){	//0-缴费，1-不缴费
					continue;
				}
			}
			
			//查找补缴记录，如果有则补缴以此为准(补缴的类型是否肯定存在于当前月的所有公积金类型中？)，没有则判断办理月前面是否有补缴月
			Map<Long,EhrSupplementPayment> spMap = this.getSupplementMap(profile.getProfileId(), month);
			
			//创建员工项
			AfPaymentBillPerson person = new AfPaymentBillPerson(profile.getProfileId(), profile.getPersonid(), profile.getGongjijinAccount());
			person.setHjBaseE(profile.getGongjijinOrgBase());	//取公积金档案数据
			person.setHjBaseP(profile.getGongjijinBase());		//取公积金档案数据
			person.setBjBaseE(profile.getGongjijinOrgBase());	//与汇缴一致
			person.setBjBaseP(profile.getGongjijinBase());		//与汇缴一致
			
			//查找公积金档案明细
			for(EhrSecurityItem si : profile.getEhrSecurityItems()){
				if(si.getSecurityTypeCategory() == 1){
					continue;	//只处理公积金
				}
				
				//查找政策包明细
				//SbGroupDetail groupDetail = sbGroupDetailMapper.selectByPrimaryKey(si.getSecurityItemCode());
				SbGroupDetail groupDetail = sbGroupDetailMapper.selectByGroupAndProd(si.getSecurityTypeId(), si.getSecurityItemCode());
				PolicyProdRatio ratio = policyProdRatioMapper.selectByPrimaryKey(groupDetail.getRatioId());
				
				//创建员工清单明细
				AfPaymentBillPersonDetail pd = new AfPaymentBillPersonDetail(groupDetail.getId());
				pd.setBaseE(profile.getGongjijinOrgBase());		//取当前公积金档案公司数据
				pd.setBaseP(profile.getGongjijinBase());		//取当前公积金档案个人数据
				pd.setRatioE(ratio.getCompanyRatio());		//取政策包明细数据
				pd.setRatioP(ratio.getIndividualRatio());	//取政策包明细数据
				pd.setAmountE(pd.getBaseE().multiply(pd.getRatioE()));
				pd.setAmountP(pd.getBaseP().multiply(pd.getRatioP()));
				
				if(spMap.containsKey(groupDetail.getId())){
					//有补缴记录
					EhrSupplementPayment tempSp = spMap.get(groupDetail.getId());
					pd.setBjMonthStart(tempSp.getStartTime());
					pd.setBjMonthEnd(tempSp.getEndTime());
					pd.setBjMonthNum(JabavaDateUtils.getMonths(tempSp.getStartTime(), tempSp.getEndTime()));
					pd.setBjBaseE(profile.getGongjijinOrgBase());	//取当前公积金档案公司数据
					pd.setBjBaseP(profile.getGongjijinBase());		//取当前公积金档案个人数据
					pd.setBjRatioE(ratio.getCompanyRatio());		//取政策包明细数据
					pd.setBjRatioP(ratio.getIndividualRatio());		//取政策包明细数据
					pd.setBjAmountE(tempSp.getOrgFee() == null ? BigDecimal.ZERO : new BigDecimal(tempSp.getOrgFee()));				//不需要计算
					pd.setBjAmountP(tempSp.getPersonalFee() == null ? BigDecimal.ZERO : new BigDecimal(tempSp.getPersonalFee()));	//不需要计算
				}else{
					//当前是办理月，且补缴月小于办理月
					if(profile.getGongjijinActivatetime().equals(month) &&
							profile.getGongjijinStarttime().compareTo(profile.getGongjijinActivatetime()) < 0){
						//补缴办理月
						pd.setBjMonthStart(profile.getGongjijinStarttime());
						pd.setBjMonthEnd(JabavaDateUtils.getLastMonth(profile.getGongjijinActivatetime()));
						pd.setBjBaseE(profile.getGongjijinOrgBase());	//取当前公积金档案公司数据
						pd.setBjBaseP(profile.getGongjijinBase());		//取当前公积金档案个人数据
						pd.setBjRatioE(ratio.getCompanyRatio());	//取政策包明细数据
						pd.setBjRatioP(ratio.getIndividualRatio());	//取政策包明细数据

						pd.setBjMonthNum(JabavaDateUtils.getMonths(pd.getBjMonthStart(), pd.getBjMonthEnd()));
						pd.setBjAmountE(pd.getBjBaseE().multiply(new BigDecimal(pd.getBjMonthNum())).multiply(pd.getBjRatioE()));
						pd.setBjAmountP(pd.getBjBaseP().multiply(new BigDecimal(pd.getBjMonthNum())).multiply(pd.getBjRatioP()));
					}
				}
				
				//将员工明细数据累加到员工数据
				this.personValueAccumulate(person, pd);
				
				person.addPersonDetail(pd);
			}
			
			//将员工数据累加到清单数据
			this.billValueAccumulate(bill, person);
			bill.addPerson(person);
		}
		
		Date now = new Date();
		bill.setCreateDate(now);
		bill.setCreateUserId(user.getUserId());
		bill.setUpdateDate(now);
		bill.setUpdateUserId(user.getUserId());
		return this.savePaymentBill(bill);
	}
	
	private Map<Long, EhrSupplementPayment> getSupplementMap(Long profileId, String month){
		Map<Long, EhrSupplementPayment> result = new HashMap<Long, EhrSupplementPayment>();
		List<EhrSupplementPayment> supplementList = supplementPaymentMapper.getSupplementPayment(profileId.toString(), month, 2L);
		for(EhrSupplementPayment sp : supplementList){
			SbGroupDetail groupDetail = sbGroupDetailMapper.selectByGroupAndProd(sp.getSecurityTypeId(), sp.getSecurityItemCode());
			result.put(groupDetail.getId(), sp);
		}
		
		return result;
	}
	
	private void personValueAccumulate(AfPaymentBillPerson person, AfPaymentBillPersonDetail pd){
		if(pd.getAmountE().compareTo(BigDecimal.ZERO) > 0 || pd.getAmountP().compareTo(BigDecimal.ZERO) > 0){
			person.setHjAmount(person.getHjAmount().add(pd.getAmountE() == null ? BigDecimal.ZERO : pd.getAmountE()));
			person.setHjAmount(person.getHjAmount().add(pd.getAmountP() == null ? BigDecimal.ZERO : pd.getAmountP()));
			person.setHjAmountE(person.getHjAmountE().add(pd.getAmountE() == null ? BigDecimal.ZERO : pd.getAmountE()));
			person.setHjAmountP(person.getHjAmountP().add(pd.getAmountP() == null ? BigDecimal.ZERO : pd.getAmountP()));
		}
		if(pd.getBjAmountE().compareTo(BigDecimal.ZERO) > 0 || pd.getBjAmountP().compareTo(BigDecimal.ZERO) > 0){
			person.setBjAmount(person.getBjAmount().add(pd.getBjAmountE() == null ? BigDecimal.ZERO : pd.getBjAmountE()));
			person.setBjAmount(person.getBjAmount().add(pd.getBjAmountP() == null ? BigDecimal.ZERO : pd.getBjAmountP()));
			person.setBjAmountE(person.getBjAmountE().add(pd.getBjAmountE() == null ? BigDecimal.ZERO : pd.getBjAmountE()));
			person.setBjAmountP(person.getBjAmountP().add(pd.getBjAmountP() == null ? BigDecimal.ZERO : pd.getBjAmountP()));
		}
	}
	
	private void billValueAccumulate(AfPaymentBill bill, AfPaymentBillPerson person){
		if(person.getHjAmount().compareTo(BigDecimal.ZERO) > 0){
			bill.setHjNum(bill.getHjNum() + 1);
			bill.setHjBaseE(bill.getHjBaseE().add(person.getHjBaseE()));
			bill.setHjBaseP(bill.getHjBaseP().add(person.getHjBaseP()));
			bill.setHjAmountE(bill.getHjAmountE().add(person.getHjAmountE()));
			bill.setHjAmountP(bill.getHjAmountP().add(person.getHjAmountP()));
			bill.setHjAmount(bill.getHjAmount().add(person.getHjAmount()));
		}
		if(person.getBjAmount().compareTo(BigDecimal.ZERO) > 0){
			bill.setBjNum(bill.getBjNum() + 1);
			bill.setBjBaseE(bill.getBjBaseE().add(person.getBjBaseE()));
			bill.setBjBaseP(bill.getBjBaseP().add(person.getBjBaseP()));
			bill.setBjAmountE(bill.getBjAmountE().add(person.getBjAmountE()));
			bill.setBjAmountP(bill.getBjAmountP().add(person.getBjAmountP()));
			bill.setBjAmount(bill.getBjAmount().add(person.getBjAmount()));
		}
		bill.setAmountE(bill.getBjAmountE().add(bill.getHjAmountE()));
		bill.setAmountP(bill.getBjAmountP().add(bill.getHjAmountP()));
	}
	
	private int savePaymentBill(AfPaymentBill bill) throws Exception{
		afPaymentBillMapper.insert(bill);
		for(AfPaymentBillPerson person : bill.getPersonList()){
			person.setAfPaymentBillId(bill.getAfPaymentBillId());
			afPaymentBillPersonMapper.insert(person);
			for(AfPaymentBillPersonDetail pd : person.getPersonDetailList()){
				pd.setAfPaymentBillPersonId(person.getAfPaymentBillPersonId());
				afPaymentBillPersonDetailMapper.insert(pd);
			}
		}
		
		return 1;
	}

	@Override
	public int changeStatus(EhrUser user, Long id, Integer operType) throws Exception {
		// 确定状态合法
		AfPaymentBill bill = afPaymentBillMapper.selectById(user.getCompanyId(), id);
		if(bill == null){
			throw new Exception("公积金清单不存在");
		}
		
		if(operType == 1){			//锁定(只有生成状态可以)
			if(!bill.getStatus().equals(SsAfEnum.PaymentBillStatus.Created.getValue())){
				throw new Exception("当前状态不允许此操作");
			}
			bill.setStatus(SsAfEnum.PaymentBillStatus.Locked.getValue());
		}else if(operType == 2){	//解锁(只有锁定状态可以)
			if(!bill.getStatus().equals(SsAfEnum.PaymentBillStatus.Locked.getValue())){
				throw new Exception("当前状态不允许此操作");
			}
			bill.setStatus(SsAfEnum.PaymentBillStatus.Created.getValue());
		}else if(operType == 3){	//作废(只有生成状态可以)
			if(!bill.getStatus().equals(SsAfEnum.PaymentBillStatus.Created.getValue())){
				throw new Exception("当前状态不允许此操作");
			}
			bill.setStatus(SsAfEnum.PaymentBillStatus.Cancelled.getValue());
		}else{
			throw new Exception("无效操作");
		}
		
		bill.setUpdateDate(new Date());
		bill.setUpdateUserId(user.getUserId());
		return afPaymentBillMapper.updateByPrimaryKey(bill);
	}

	@Override
	public AfPaymentBill findByPrimaryKey(Long afPaymentBillId) {
		return afPaymentBillMapper.selectByPrimaryKey(afPaymentBillId);
	}

	@Override
	public AfPaymentBill findByMonthAndAfAccount(Long companyId, String month, Long accumulationFundAccountId) {
		return afPaymentBillMapper.getValidPaymentBill(companyId, accumulationFundAccountId, month);
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonHeader(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "公积金账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("bj_amount", "补缴总额"));
		result.add(this.getFieldHeader("bj_amount_e", "企业补缴总额"));
		result.add(this.getFieldHeader("bj_amount_p", "个人补缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));

		//获取distinct员工明细
		List<Map<String,Object>> groupDetailList = afPaymentBillPersonDetailMapper.listGroupDetail(bill.getAfPaymentBillId());
		for(Map<String,Object> gd : groupDetailList){
			//distinct pbd.sb_group_detail_id, gd.prod_id, gd.prod_name
			String prodId = gd.get("prod_id").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("base_e_" + prodId, "企业基数"));
			cols.add(this.getFieldHeader("base_p_" + prodId, "个人基数"));
			cols.add(this.getFieldHeader("ratio_e_" + prodId, "企业比例"));
			cols.add(this.getFieldHeader("ratio_p_" + prodId, "个人比例"));
			cols.add(this.getFieldHeader("amount_e_" + prodId, "企业汇缴金额"));
			cols.add(this.getFieldHeader("amount_p_" + prodId, "个人汇缴金额"));
			cols.add(this.getFieldHeader("bj_amount_e_" + prodId, "企业补缴金额"));
			cols.add(this.getFieldHeader("bj_amount_p_" + prodId, "个人补缴金额"));
			cols.add(this.getFieldHeader("bj_month_num_" + prodId, "补缴月数"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prod_id", prodId);
			group.put("prod_name",gd.get("prod_name"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> listPaymentBillPerson(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<AfPaymentBillPerson> personList = afPaymentBillPersonMapper.selectByBillId(bill.getAfPaymentBillId());
		for(AfPaymentBillPerson person : personList){
			Map<String,Object> record = new HashMap<String,Object>();
			record.put("person_name", person.getPersonName());
			record.put("security_account", person.getAccumulationFundAccount());
			record.put("hj_amount", person.getHjAmount());
			record.put("hj_amount_e", person.getHjAmountE());
			record.put("hj_amount_p", person.getHjAmountP());
			record.put("bj_amount", person.getBjAmount());
			record.put("bj_amount_e", person.getBjAmountE());
			record.put("bj_amount_p", person.getBjAmountP());
			record.put("secretary_status", null);
			
			List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectByBillPersonId(person.getAfPaymentBillPersonId());
			for(Map<String,Object> personDetail : personDetailList){
				String prodId = personDetail.get("prod_id").toString();
				record.put("base_e_" + prodId, personDetail.get("base_e"));
				record.put("base_p_" + prodId, personDetail.get("base_p"));
				record.put("ratio_e_" + prodId, personDetail.get("ratio_e"));
				record.put("ratio_p_" + prodId, personDetail.get("ratio_p"));
				record.put("amount_e_" + prodId, personDetail.get("amount_e"));
				record.put("amount_p_" + prodId, personDetail.get("amount_p"));
				record.put("bj_amount_e_" + prodId, personDetail.get("bj_amount_e"));
				record.put("bj_amount_p_" + prodId, personDetail.get("bj_amount_p"));
				record.put("bj_month_num_" + prodId, personDetail.get("bj_month_num"));
			}
			
			result.add(record);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonZyHeader(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "公积金账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));
		
		//获取distinct员工明细
		//增员：本月有，上月没有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		List<Map<String,Object>> groupDetailList = afPaymentBillPersonDetailMapper.listGroupDetailZy(bill.getAfPaymentBillId(),
				lastBill == null ? null : lastBill.getAfPaymentBillId());
		for(Map<String,Object> gd : groupDetailList){
			//distinct pbd.sb_group_detail_id, gd.prod_id, gd.prod_name
			String prodId = gd.get("prod_id").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("base_e_" + prodId, "企业基数"));
			cols.add(this.getFieldHeader("base_p_" + prodId, "个人基数"));
			cols.add(this.getFieldHeader("ratio_e_" + prodId, "企业比例"));
			cols.add(this.getFieldHeader("ratio_p_" + prodId, "个人比例"));
			cols.add(this.getFieldHeader("amount_e_" + prodId, "企业金额"));
			cols.add(this.getFieldHeader("amount_p_" + prodId, "个人金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prod_id", prodId);
			group.put("prod_name",gd.get("prod_name"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> listPaymentBillPersonZy(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//增员：本月有，上月没有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectZyByBillId(bill.getAfPaymentBillId(), 
				lastBill == null ? null : lastBill.getAfPaymentBillId());
		Map<String,Map<String,Object>> personMap = new HashMap<String,Map<String,Object>>();
		Map<String,Object> record = null;
		for(Map<String,Object> pd : personDetailList){
			String key = pd.get("person_id").toString();
			if(personMap.containsKey(key)){
				record = personMap.get(key);
			}else{
				record = new HashMap<String,Object>();
				personMap.put(key, record);
			}
			record.put("person_name", pd.get("person_name"));
			record.put("security_account", pd.get("accumulation_fund_account"));
			record.put("hj_amount", pd.get("bp_hj_amount"));
			record.put("hj_amount_e", pd.get("bp_hj_amount_e"));
			record.put("hj_amount_p", pd.get("bp_hj_amount_p"));
			record.put("secretary_status", null);
			
			String prodId = pd.get("prod_id").toString();
			record.put("base_e_" + prodId, pd.get("base_e"));
			record.put("base_p_" + prodId, pd.get("base_p"));
			record.put("ratio_e_" + prodId, pd.get("ratio_e"));
			record.put("ratio_p_" + prodId, pd.get("ratio_p"));
			record.put("amount_e_" + prodId, pd.get("amount_e"));
			record.put("amount_p_" + prodId, pd.get("amount_p"));
		}
		
		result.addAll(personMap.values());
		return result;
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonJyHeader(
			AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "公积金账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));
		
		//获取distinct员工明细
		//减员：本月没有，上月有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		
		List<Map<String,Object>> groupDetailList = afPaymentBillPersonDetailMapper.listGroupDetailJy(bill.getAfPaymentBillId(), lastBill.getAfPaymentBillId());
		for(Map<String,Object> gd : groupDetailList){
			//distinct pbd.sb_group_detail_id, gd.prod_id, gd.prod_name
			String prodId = gd.get("prod_id").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("base_e_" + prodId, "企业基数"));
			cols.add(this.getFieldHeader("base_p_" + prodId, "个人基数"));
			cols.add(this.getFieldHeader("ratio_e_" + prodId, "企业比例"));
			cols.add(this.getFieldHeader("ratio_p_" + prodId, "个人比例"));
			cols.add(this.getFieldHeader("amount_e_" + prodId, "企业金额"));
			cols.add(this.getFieldHeader("amount_p_" + prodId, "个人金额"));
			
			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prod_id", prodId);
			group.put("prod_name",gd.get("prod_name"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> listPaymentBillPersonJy(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//减员：本月没有，上月有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectJyByBillId(bill.getAfPaymentBillId(), lastBill.getAfPaymentBillId());
		Map<String,Map<String,Object>> personMap = new HashMap<String,Map<String,Object>>();
		Map<String,Object> record = null;
		for(Map<String,Object> pd : personDetailList){
			String key = pd.get("person_id").toString();
			if(personMap.containsKey(key)){
				record = personMap.get(key);
			}else{
				record = new HashMap<String,Object>();
				personMap.put(key, record);
			}
			record.put("person_name", pd.get("person_name"));
			record.put("security_account", pd.get("accumulation_fund_account"));
			record.put("hj_amount", pd.get("bp_hj_amount"));
			record.put("hj_amount_e", pd.get("bp_hj_amount_e"));
			record.put("hj_amount_p", pd.get("bp_hj_amount_p"));
			record.put("secretary_status", null);
			
			String prodId = pd.get("prod_id").toString();
			record.put("base_e_" + prodId, pd.get("base_e"));
			record.put("base_p_" + prodId, pd.get("base_p"));
			record.put("ratio_e_" + prodId, pd.get("ratio_e"));
			record.put("ratio_p_" + prodId, pd.get("ratio_p"));
			record.put("amount_e_" + prodId, pd.get("amount_e"));
			record.put("amount_p_" + prodId, pd.get("amount_p"));
		}
		
		result.addAll(personMap.values());
		return result;
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonTjHeader(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "公积金账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		
		//获取distinct员工明细
		//变更1：上月有，但基数(其中一项或多项)调整
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		
		List<Map<String,Object>> groupDetailList = afPaymentBillPersonDetailMapper.listGroupDetailTj(bill.getAfPaymentBillId(), lastBill.getAfPaymentBillId());
		for(Map<String,Object> gd : groupDetailList){
			//distinct pbd.sb_group_detail_id, gd.prod_id, gd.prod_name
			String prodId = gd.get("prod_id").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("base_old_" + prodId, "原基数"));
			cols.add(this.getFieldHeader("base_new_" + prodId, "现基数"));

			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prod_id", prodId);
			group.put("prod_name",gd.get("prod_name"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> listPaymentBillPersonTj(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//变更1：上月有，但基数(其中一项或多项)调整
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		AfPaymentBill lastBill = afPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getAccumulationFundAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectTjByBillId(bill.getAfPaymentBillId(), lastBill.getAfPaymentBillId());
		Map<String,Map<String,Object>> personMap = new HashMap<String,Map<String,Object>>();
		Map<String,Object> record = null;
		for(Map<String,Object> pd : personDetailList){
			String key = pd.get("person_id").toString();
			if(personMap.containsKey(key)){
				record = personMap.get(key);
			}else{
				record = new HashMap<String,Object>();
				personMap.put(key, record);
			}
			record.put("person_name", pd.get("person_name"));
			record.put("security_account", pd.get("accumulation_fund_account"));
			record.put("hj_amount", pd.get("bp_hj_amount"));
			record.put("hj_amount_e", pd.get("bp_hj_amount_e"));
			record.put("hj_amount_p", pd.get("bp_hj_amount_p"));
			
			String prodId = pd.get("prod_id").toString();
			record.put("base_old_" + prodId, pd.get("base_old"));
			record.put("base_new_" + prodId, pd.get("base_new"));
		}
		
		result.addAll(personMap.values());
		return result;
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonBjHeader(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "公积金账号"));
		result.add(this.getFieldHeader("bj_amount", "补缴总额"));
		result.add(this.getFieldHeader("bj_amount_e", "企业补缴总额"));
		result.add(this.getFieldHeader("bj_amount_p", "个人补缴总额"));
		
		//获取distinct员工明细
		//变更2：补缴(补缴办理月)，可能是增员，也可能不是
		List<Map<String,Object>> groupDetailList = afPaymentBillPersonDetailMapper.listGroupDetailBj(bill.getAfPaymentBillId());
		for(Map<String,Object> gd : groupDetailList){
			//distinct pbd.sb_group_detail_id, gd.prod_id, gd.prod_name
			String prodId = gd.get("prod_id").toString();
			List<Map<String,Object>> cols = new ArrayList<Map<String,Object>>();
			cols.add(this.getFieldHeader("bj_month_start_" + prodId, "补缴起始月"));
			cols.add(this.getFieldHeader("bj_month_end_" + prodId, "补缴结束月"));
			cols.add(this.getFieldHeader("bj_amount_e_" + prodId, "企业补缴金额"));
			cols.add(this.getFieldHeader("bj_amount_p_" + prodId, "个人补缴金额"));

			Map<String,Object> group = new HashMap<String,Object>();
			group.put("prod_id", prodId);
			group.put("prod_name",gd.get("prod_name"));
			group.put("cols",cols);
			
			result.add(group);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> listPaymentBillPersonBj(AfPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//变更2：补缴(补缴办理月)，可能是增员，也可能不是
		List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectBjByBillId(bill.getAfPaymentBillId());
		Map<String,Map<String,Object>> personMap = new HashMap<String,Map<String,Object>>();
		Map<String,Object> record = null;
		for(Map<String,Object> pd : personDetailList){
			String key = pd.get("person_id").toString();
			if(personMap.containsKey(key)){
				record = personMap.get(key);
			}else{
				record = new HashMap<String,Object>();
				personMap.put(key, record);
			}
			record.put("person_name", pd.get("person_name"));
			record.put("security_account", pd.get("accumulation_fund_account"));
			record.put("bj_amount", pd.get("bp_bj_amount"));
			record.put("bj_amount_e", pd.get("bp_bj_amount_e"));
			record.put("bj_amount_p", pd.get("bp_bj_amount_p"));
			
			String prodId = pd.get("prod_id").toString();
			record.put("bj_month_start_" + prodId, pd.get("bj_month_start"));
			record.put("bj_month_end_" + prodId, pd.get("bj_month_end"));
			record.put("bj_amount_e_" + prodId, pd.get("bj_amount_e"));
			record.put("bj_amount_p_" + prodId, pd.get("bj_amount_p"));
		}
		
		result.addAll(personMap.values());
		return result;
	}

	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}

	@Override
	public Map<String, Object> queryPersonByCertIdAndMonth(String cardId, String month) {
		return afPaymentBillPersonMapper.queryByCertIdAndMonth(cardId, month);
	}

	@Override
	public List<Map<String, Object>> listPersonDetail(Long afPaymentBillPersonId) {
		return afPaymentBillPersonDetailMapper.listPersonDetail(afPaymentBillPersonId);
	}

}
