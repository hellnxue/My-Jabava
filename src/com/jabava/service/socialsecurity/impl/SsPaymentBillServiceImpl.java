package com.jabava.service.socialsecurity.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.policygroup.PolicyProdRatioMapper;
import com.jabava.dao.policygroup.SbGroupDetailMapper;
import com.jabava.dao.socialsecurity.EhrPersonSecurityProfileMapper;
import com.jabava.dao.socialsecurity.EhrSecurityItemMapper;
import com.jabava.dao.socialsecurity.EhrSupplementPaymentMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillDetailMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillPersonDetailMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillPersonMapper;
import com.jabava.dao.socialsecurity.SsSocialSecurityAccountMapper;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.policygroup.PolicyProdRatio;
import com.jabava.pojo.policygroup.SbGroupDetail;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.socialsecurity.EhrSecurityItem;
import com.jabava.pojo.socialsecurity.EhrSupplementPayment;
import com.jabava.pojo.socialsecurity.SsPaymentBill;
import com.jabava.pojo.socialsecurity.SsPaymentBillDetail;
import com.jabava.pojo.socialsecurity.SsPaymentBillPerson;
import com.jabava.pojo.socialsecurity.SsPaymentBillPersonDetail;
import com.jabava.pojo.socialsecurity.SsSocialSecurityAccountVO;
import com.jabava.service.socialsecurity.ISsPaymentBillService;
import com.jabava.utils.JabavaDateUtils;
import com.jabava.utils.enums.SsAfEnum;

@Service
public class SsPaymentBillServiceImpl implements ISsPaymentBillService {
	private Logger log = Logger.getLogger(SsPaymentBillServiceImpl.class);
	
	@Autowired
	private SsPaymentBillMapper ssPaymentBillMapper;
	@Autowired
	private SsPaymentBillDetailMapper ssPaymentBillDetailMapper;
	@Autowired
	private SsPaymentBillPersonMapper ssPaymentBillPersonMapper;
	@Autowired
	private SsPaymentBillPersonDetailMapper ssPaymentBillPersonDetailMapper;
	@Autowired
	private EhrPersonSecurityProfileMapper personSecurityProfileMapper;
	@Autowired
	private EhrSecurityItemMapper securityItemMapper;
	@Autowired
	private EhrSupplementPaymentMapper supplementPaymentMapper;
	@Autowired
	private SsSocialSecurityAccountMapper ssSocialSecurityAccountMapper;
	@Autowired
	private SbGroupDetailMapper sbGroupDetailMapper;
	@Autowired
	private PolicyProdRatioMapper policyProdRatioMapper;

	@Override
	public List<SsPaymentBill> listPaymentBillPage(Map<String,Object> params) {
		return ssPaymentBillMapper.listPaymentBillPage(params);
	}

	@Override
	public int generatePaymentBill(EhrUser user,
			Long socialSecurityAccountId, String month) throws Exception {
		// 确定未生成(或已作废)
		SsPaymentBill existBill = ssPaymentBillMapper.getValidPaymentBill(user.getCompanyId(), socialSecurityAccountId, month);
		if(existBill != null){
			throw new Exception("当前状态不允许生成");
		}
		
		//创建清单
		SsPaymentBill bill = new SsPaymentBill(user.getCompanyId(), socialSecurityAccountId, month);
		SsSocialSecurityAccountVO ssAccount = ssSocialSecurityAccountMapper.selectByPrimaryKey(socialSecurityAccountId);
		bill.setSocialSecurityAccountName(ssAccount.getSocialSecurityAccountName());
		
		//创建N条清单明细Map(动态，按社保类型及企业、个人比例分组统计)
		Map<String,SsPaymentBillDetail> billDetailMap = new HashMap<String,SsPaymentBillDetail>();
		
		// 根据社保账户查找并遍历员工社保档案
		//List<EhrPersonSecurityProfile> pspList = personSecurityProfileMapper.getSecurityProfileByCompanyId(user.getCompanyId(), 1L);
		List<EhrPersonSecurityProfile> pspList = personSecurityProfileMapper.getSecurityProfileByAccount(socialSecurityAccountId, 1L);
		if(pspList == null || pspList.isEmpty()){
			throw new Exception("没有找到员工社保档案数据");
		}
		
		for(EhrPersonSecurityProfile profile : pspList){
			//已停办或未到办理月的不处理
			if((profile.getSecurityEndtime() != null && profile.getSecurityEndtime().compareTo(month) < 0) || 
					profile.getSecurityActivatetime().compareTo(month) > 0){
				continue;
			}
			
			//停办月当月不收费的不处理
			if(profile.getSecurityEndtime() != null && profile.getSecurityEndtime().compareTo(month) == 0){
				if(profile.getSecurityEndtimeStatus() == 1){	//0-缴费，1-不缴费
					continue;
				}
			}
			
			//查找补缴记录，如果有则补缴以此为准(补缴的类型是否肯定存在于当前月的所有社保类型中？)，没有则判断办理月前面是否有补缴月
			Map<Long,EhrSupplementPayment> spMap = this.getSupplementMap(profile.getProfileId(), month);
			
			//创建员工项
			SsPaymentBillPerson person = new SsPaymentBillPerson(profile.getProfileId(), profile.getPersonid(), profile.getSecurityAccount());
			person.setHjBaseE(profile.getSecurityOrgBase());	//取社保档案公司数据
			person.setHjBaseP(profile.getSecurityBase());		//取社保档案个人数据
			person.setBjBaseE(profile.getSecurityOrgBase());	//与汇缴一致
			person.setBjBaseP(profile.getSecurityBase());		//与汇缴一致
			
			//查找社保档案明细
			for(EhrSecurityItem si : profile.getEhrSecurityItems()){
				if(si.getSecurityTypeCategory() == 2){
					continue;	//只处理社保
				}
				
				//查找政策包明细
				//SbGroupDetail groupDetail = sbGroupDetailMapper.selectByPrimaryKey(si.getSecurityItemCode());
				SbGroupDetail groupDetail = sbGroupDetailMapper.selectByGroupAndProd(si.getSecurityTypeId(), si.getSecurityItemCode());
				PolicyProdRatio ratio = policyProdRatioMapper.selectByPrimaryKey(groupDetail.getRatioId());
				
				//创建员工清单明细
				SsPaymentBillPersonDetail pd = new SsPaymentBillPersonDetail(groupDetail.getId());
				pd.setBaseE(profile.getSecurityOrgBase());	//取当前社保档案公司数据
				pd.setBaseP(profile.getSecurityBase());		//取当前社保档案个人数据
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
					pd.setBjBaseE(profile.getSecurityOrgBase());	//取当前社保档案公司数据
					pd.setBjBaseP(profile.getSecurityBase());		//取当前社保档案个人数据
					pd.setBjRatioE(ratio.getCompanyRatio());		//取政策包明细数据
					pd.setBjRatioP(ratio.getIndividualRatio());		//取政策包明细数据
					pd.setBjAmountE(tempSp.getOrgFee() == null ? BigDecimal.ZERO : new BigDecimal(tempSp.getOrgFee()));				//不需要计算
					pd.setBjAmountP(tempSp.getPersonalFee() == null ? BigDecimal.ZERO : new BigDecimal(tempSp.getPersonalFee()));	//不需要计算
				}else{
					//当前是办理月，且补缴月小于办理月
					if(profile.getSecurityActivatetime().equals(month) &&
							profile.getSecurityStarttime().compareTo(profile.getSecurityActivatetime()) < 0){
						//补缴办理月
						pd.setBjMonthStart(profile.getSecurityStarttime());
						pd.setBjMonthEnd(JabavaDateUtils.getLastMonth(profile.getSecurityActivatetime()));
						pd.setBjBaseE(profile.getSecurityOrgBase());	//取当前社保档案公司数据
						pd.setBjBaseP(profile.getSecurityBase());		//取当前社保档案个人数据
						pd.setBjRatioE(ratio.getCompanyRatio());	//取政策包明细数据
						pd.setBjRatioP(ratio.getIndividualRatio());	//取政策包明细数据

						pd.setBjMonthNum(JabavaDateUtils.getMonths(pd.getBjMonthStart(), pd.getBjMonthEnd()));
						pd.setBjAmountE(pd.getBjBaseE().multiply(new BigDecimal(pd.getBjMonthNum())).multiply(pd.getBjRatioE()));
						pd.setBjAmountP(pd.getBjBaseP().multiply(new BigDecimal(pd.getBjMonthNum())).multiply(pd.getBjRatioP()));
					}
				}
				
				//创建明细项
				SsPaymentBillDetail detail = null;
				String key = groupDetail.getId().toString() + ratio.getCompanyRatio() + ratio.getIndividualRatio();
				if(billDetailMap.containsKey(key)){
					detail = billDetailMap.get(key);
				}else{
					detail = new SsPaymentBillDetail(groupDetail.getId(), ratio.getCompanyRatio(), ratio.getIndividualRatio());
					
					//将员工明细数据累加到清单明细数据
					this.detailValueAccumulate(detail, pd);
					billDetailMap.put(key, detail);
				}
				
				//将员工明细数据累加到员工数据
				this.personValueAccumulate(person, pd);
				
				person.addPersonDetail(pd);
			}
			
			//将员工数据累加到清单数据
			this.billValueAccumulate(bill, person);
			bill.addPerson(person);
		}
		
		for(SsPaymentBillDetail pbd : billDetailMap.values()){
			bill.addDetail(pbd);
		}
		
		Date now = new Date();
		bill.setCreateDate(now);
		bill.setCreateUserId(user.getUserId());
		bill.setCreateUserName(user.getUserName());
		bill.setLastModifyDate(now);
		bill.setLastModifyUserId(user.getUserId());
		bill.setLastModifyUserName(user.getUserName());
		return this.savePaymentBill(bill);
	}
	
	private Map<Long, EhrSupplementPayment> getSupplementMap(Long profileId, String month){
		Map<Long, EhrSupplementPayment> result = new HashMap<Long, EhrSupplementPayment>();
		List<EhrSupplementPayment> supplementList = supplementPaymentMapper.getSupplementPayment(profileId.toString(), month, 1L);
		for(EhrSupplementPayment sp : supplementList){
			SbGroupDetail groupDetail = sbGroupDetailMapper.selectByGroupAndProd(sp.getSecurityTypeId(), sp.getSecurityItemCode());
			result.put(groupDetail.getId(), sp);
		}
		
		return result;
	}
	
	private void personValueAccumulate(SsPaymentBillPerson person, SsPaymentBillPersonDetail pd){
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
	
	private void detailValueAccumulate(SsPaymentBillDetail detail, SsPaymentBillPersonDetail pd){
		if(pd.getAmountE().compareTo(BigDecimal.ZERO) > 0 || pd.getAmountP().compareTo(BigDecimal.ZERO) > 0){
			detail.setBqNum(detail.getBqNum() + 1);
			detail.setBqBaseE(detail.getBqBaseE().add(pd.getBaseE() == null ? BigDecimal.ZERO : pd.getBaseE()));
			detail.setBqBaseP(detail.getBqBaseP().add(pd.getBaseP() == null ? BigDecimal.ZERO : pd.getBaseP()));

			detail.setBqAmountE(detail.getBqAmountE().add(pd.getAmountE() == null ? BigDecimal.ZERO : pd.getAmountE()));
			detail.setBqAmountP(detail.getBqAmountP().add(pd.getAmountP() == null ? BigDecimal.ZERO : pd.getAmountP()));
		}
		if(pd.getBjAmountE().compareTo(BigDecimal.ZERO) > 0 || pd.getBjAmountP().compareTo(BigDecimal.ZERO) > 0){
			detail.setBjNum(detail.getBjNum() + 1);
			detail.setBjBaseE(detail.getBjBaseE().add(pd.getBjBaseE() == null ? BigDecimal.ZERO : pd.getBaseE()));
			detail.setBjBaseP(detail.getBjBaseP().add(pd.getBjBaseP() == null ? BigDecimal.ZERO : pd.getBjBaseP()));
			
			detail.setBjAmountE(detail.getBjAmountE().add(pd.getBjAmountE() == null ? BigDecimal.ZERO : pd.getAmountE()));
			detail.setBjAmountP(detail.getBjAmountP().add(pd.getBjAmountP() == null ? BigDecimal.ZERO : pd.getBjAmountP()));
		}
		detail.setSubtotal(detail.getBqAmountE().add(detail.getBjAmountE()).add(detail.getBqAmountP()).add(detail.getBjAmountP()));
	}
	
	private void billValueAccumulate(SsPaymentBill bill, SsPaymentBillPerson person){
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
	
	private int savePaymentBill(SsPaymentBill bill) throws Exception{
		ssPaymentBillMapper.insert(bill);
		for(SsPaymentBillDetail pbd : bill.getDetailList()){
			pbd.setSsPaymentBillId(bill.getId());
			ssPaymentBillDetailMapper.insert(pbd);
		}
		for(SsPaymentBillPerson person : bill.getPersonList()){
			person.setSsPaymentBillId(bill.getId());
			ssPaymentBillPersonMapper.insert(person);
			for(SsPaymentBillPersonDetail pd : person.getPersonDetailList()){
				pd.setSsPaymentBillPersonId(person.getId());
				ssPaymentBillPersonDetailMapper.insert(pd);
			}
		}
		
		return 1;
	}

	@Override
	public int changeStatus(EhrUser user, Long id, Integer operType) throws Exception {
		// 确定状态合法
		SsPaymentBill bill = ssPaymentBillMapper.selectById(user.getCompanyId(), id);
		if(bill == null){
			throw new Exception("社保清单不存在");
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
		
		bill.setLastModifyDate(new Date());
		bill.setLastModifyUserId(user.getUserId());
		bill.setLastModifyUserName(user.getUserName());
		return ssPaymentBillMapper.updateByPrimaryKey(bill);
	}

	@Override
	public List<Map<String, Object>> listPaymentBillDetail(Long companyId, Long ssPaymentBillId) {
		List<Map<String, Object>> result = ssPaymentBillDetailMapper.listPaymentBillDetail(companyId, ssPaymentBillId);
		
		Map<String, Object> sum = ssPaymentBillDetailMapper.sumPaymentBillDetail(companyId, ssPaymentBillId);
		
		if(sum != null){
			sum.put("colspan", 9);
			result.add(sum);
		}
		
		return result;
	}

	@Override
	public SsPaymentBill findByPrimaryKey(Long ssPaymentBillId) {
		return ssPaymentBillMapper.selectByPrimaryKey(ssPaymentBillId);
	}

	@Override
	public SsPaymentBill findByMonthAndSsAccount(Long companyId, String month, Long socialSecurityAccountId) {
		return ssPaymentBillMapper.getValidPaymentBill(companyId, socialSecurityAccountId, month);
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonHeader(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "社保账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("bj_amount", "补缴总额"));
		result.add(this.getFieldHeader("bj_amount_e", "企业补缴总额"));
		result.add(this.getFieldHeader("bj_amount_p", "个人补缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));

		//获取distinct员工明细
		List<Map<String,Object>> groupDetailList = ssPaymentBillPersonDetailMapper.listGroupDetail(bill.getId());
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
	public List<Map<String, Object>> listPaymentBillPerson(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<SsPaymentBillPerson> personList = ssPaymentBillPersonMapper.selectByBillId(bill.getId());
		for(SsPaymentBillPerson person : personList){
			Map<String,Object> record = new HashMap<String,Object>();
			record.put("person_name", person.getPersonName());
			record.put("security_account", person.getSecurityAccount());
			record.put("hj_amount", person.getHjAmount());
			record.put("hj_amount_e", person.getHjAmountE());
			record.put("hj_amount_p", person.getHjAmountP());
			record.put("bj_amount", person.getBjAmount());
			record.put("bj_amount_e", person.getBjAmountE());
			record.put("bj_amount_p", person.getBjAmountP());
			record.put("secretary_status", null);
			
			List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectByBillPersonId(person.getId());
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
	public List<Map<String, Object>> loadPaymentBillPersonZyHeader(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "社保账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));
		
		//获取distinct员工明细
		//增员：本月有，上月没有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		List<Map<String,Object>> groupDetailList = ssPaymentBillPersonDetailMapper.listGroupDetailZy(bill.getId(),
				lastBill == null ? null : lastBill.getId());
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
	public List<Map<String, Object>> listPaymentBillPersonZy(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//增员：本月有，上月没有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectZyByBillId(bill.getId(), 
				lastBill == null ? null : lastBill.getId());
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
			record.put("security_account", pd.get("security_account"));
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
			SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "社保账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		result.add(this.getFieldHeader("secretary_status", "小秘书状态"));
		
		//获取distinct员工明细
		//减员：本月没有，上月有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		
		List<Map<String,Object>> groupDetailList = ssPaymentBillPersonDetailMapper.listGroupDetailJy(bill.getId(), lastBill.getId());
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
	public List<Map<String, Object>> listPaymentBillPersonJy(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//减员：本月没有，上月有
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectJyByBillId(bill.getId(), lastBill.getId());
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
			record.put("security_account", pd.get("security_account"));
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
	public List<Map<String, Object>> loadPaymentBillPersonTjHeader(
			SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "社保账号"));
		result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
		result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
		
		//获取distinct员工明细
		//变更1：上月有，但基数(其中一项或多项)调整
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		
		List<Map<String,Object>> groupDetailList = ssPaymentBillPersonDetailMapper.listGroupDetailTj(bill.getId(), lastBill.getId());
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
	public List<Map<String, Object>> listPaymentBillPersonTj(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//变更1：上月有，但基数(其中一项或多项)调整
		String lastMonth = JabavaDateUtils.getLastMonth(bill.getMonth());
		SsPaymentBill lastBill = ssPaymentBillMapper.getValidPaymentBill(bill.getCompanyId(), bill.getSocialSecurityAccountId(), lastMonth);
		if(lastBill == null){	//如果上月没有，则直接返回
			return result;
		}
		List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectTjByBillId(bill.getId(), lastBill.getId());
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
			record.put("security_account", pd.get("security_account"));
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
	public List<Map<String, Object>> loadPaymentBillPersonBjHeader(
			SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("person_name", "姓名"));
		result.add(this.getFieldHeader("security_account", "社保账号"));
		result.add(this.getFieldHeader("bj_amount", "补缴总额"));
		result.add(this.getFieldHeader("bj_amount_e", "企业补缴总额"));
		result.add(this.getFieldHeader("bj_amount_p", "个人补缴总额"));
		
		//获取distinct员工明细
		//变更2：补缴(补缴办理月)，可能是增员，也可能不是
		List<Map<String,Object>> groupDetailList = ssPaymentBillPersonDetailMapper.listGroupDetailBj(bill.getId());
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
	public List<Map<String, Object>> listPaymentBillPersonBj(SsPaymentBill bill) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//变更2：补缴(补缴办理月)，可能是增员，也可能不是
		List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectBjByBillId(bill.getId());
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
			record.put("security_account", pd.get("security_account"));
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
		return ssPaymentBillPersonMapper.queryByCertIdAndMonth(cardId, month);
	}

	@Override
	public List<Map<String, Object>> listPersonDetail(Long ssPaymentBillPersonId) {
		return ssPaymentBillPersonDetailMapper.listPersonDetail(ssPaymentBillPersonId);
	}

}
