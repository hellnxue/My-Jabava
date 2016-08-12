package com.jabava.service.salary.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.MapContext;
import com.jabava.common.exception.JabavaServiceException;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.dao.accumulationfund.AfPaymentBillPersonMapper;
import com.jabava.dao.hro.HroPactInfoMapper;
import com.jabava.dao.manage.EhrArgumentInfoMapper;
import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.salary.EhrMonthlySalaryDetailMapper;
import com.jabava.dao.salary.EhrMonthlySalaryMapper;
import com.jabava.dao.salary.EhrMonthlySalaryPersonMapper;
import com.jabava.dao.salary.EhrSalaryChangeDataMapper;
import com.jabava.dao.salary.EhrSalaryChangeDefItemMapper;
import com.jabava.dao.salary.EhrSalaryChangeDefMapper;
import com.jabava.dao.salary.EhrSalaryDetailMapper;
import com.jabava.dao.salary.EhrSalaryItemMapper;
import com.jabava.dao.salary.EhrSalaryMapper;
import com.jabava.dao.salary.EhrSalaryTemplateMapper;
import com.jabava.dao.salary.EhrTaxLevelMapper;
import com.jabava.dao.salary.EhrTaxRateMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillPersonMapper;
import com.jabava.dao.system.EHrMailConfigMapper;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.manage.EhrArgumentInfo;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.salary.EhrMonthlySalary;
import com.jabava.pojo.salary.EhrMonthlySalaryDetail;
import com.jabava.pojo.salary.EhrMonthlySalaryPerson;
import com.jabava.pojo.salary.EhrSalary;
import com.jabava.pojo.salary.EhrSalaryChangeDef;
import com.jabava.pojo.salary.EhrSalaryChangeDefItem;
import com.jabava.pojo.salary.EhrSalaryDetail;
import com.jabava.pojo.salary.EhrSalaryItem;
import com.jabava.pojo.salary.EhrSalaryTemplate;
import com.jabava.pojo.salary.EhrTaxLevel;
import com.jabava.pojo.salary.EhrTaxRate;
import com.jabava.pojo.system.EHrMailConfig;
import com.jabava.service.salary.IMonthlySalaryService;
import com.jabava.service.salary.ISalaryDateService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.JabavaStringUtils;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.SalaryHelper;
import com.jabava.utils.Tools;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.constants.ConfigConstants;
import com.jabava.utils.enums.SalaryEnum;
import com.jabava.utils.mail.MailVO;
import com.jabava.vo.salary.EmpSalaryDetailVO;

@Service
public class MonthlySalaryServiceImpl implements IMonthlySalaryService{
	private static Logger log = Logger.getLogger(MonthlySalaryServiceImpl.class);
	
	@Autowired
	private EhrMonthlySalaryMapper monthlySalaryMapper;
	@Autowired
	private EhrMonthlySalaryPersonMapper monthlySalaryPersonMapper;
	@Autowired
	private EhrMonthlySalaryDetailMapper monthlySalaryDetailMapper;
	@Autowired
	private EhrSalaryMapper salaryMapper;
	@Autowired
	private EhrSalaryDetailMapper salaryDetailMapper;
	@Autowired
	private EhrSalaryItemMapper salaryItemMapper;
	@Autowired
	private EhrSalaryTemplateMapper templateMapper;
	@Autowired
	private EhrSalaryChangeDefMapper salaryChangeDefMapper;
	@Autowired
	private EhrSalaryChangeDefItemMapper salaryChangeDefItemMapper;
	@Autowired
	private EhrSalaryChangeDataMapper salaryChangeDataMapper;
	@Autowired
	private IEhrOrganizationService organizationService;
	@Autowired
	private EhrPersonMapper personMapper;
	@Autowired
	private EhrAttendanceMapper attendanceMapper;
	@Autowired
	private ISalaryDateService salaryDateService;
	@Autowired
	private EhrTaxRateMapper taxRateMapper;
	@Autowired
	private EhrTaxLevelMapper taxLevelMapper;
	@Autowired
	private SsPaymentBillPersonMapper ssPaymentBillPersonMapper;
	@Autowired
	private AfPaymentBillPersonMapper afPaymentBillPersonMapper;
	@Autowired
	private HroPactInfoMapper hroPactInfoMapper;
	@Autowired
	private EhrArgumentInfoMapper argumentInfoMapper;
	@Autowired
	private EHrMailConfigMapper mailConfigMapper;
	
	private HROFetchService hroFetchService;
	private HROFetchService getHROFetchService(){
		if(this.hroFetchService == null){
			this.hroFetchService = new HROFetchService("/open/authorize", "/open/rest");
		}
		return this.hroFetchService;
	}
	
	@Override
	public List<EhrMonthlySalary> listMonthlySalaryPage(Map<String, Object> params) {
		return monthlySalaryMapper.listMonthlySalaryPage(params);
	}

	@Override
	public List<String> generateMonthlySalary(EhrMonthlySalary monthlySalary, EhrUser user) throws Exception{
		monthlySalary.setCompanyId(user.getCompanyId());
		if(monthlySalary.getLastFlag() == null){
			monthlySalary.setLastFlag(1);
		}
		if(monthlySalary.getReviewStatus() == null){
			monthlySalary.setReviewStatus(1);
		}
		
		//组织机构判断
		EhrOrganization topOrg = organizationService.findTopOrganization(user.getCompanyId());
		if(monthlySalary.getOrganizationId().equals(topOrg.getOrganizationId())){
			//如果分公司有有效版本，则总公司不能再生成
			List<EhrOrganization> orgList = organizationService.getChildren(topOrg.getOrganizationId());
			if(orgList != null && !orgList.isEmpty()){
				if(monthlySalaryMapper.countValidByOrgList(monthlySalary.getUsageFlag(), monthlySalary.getMonthly(), orgList) > 0){
					throw new Exception("分公司数据已生成，不能再生成总公司数据");
				}
			}
		}else{
			//如果总公司有有效版本，则分公司不能再生成
			List<EhrOrganization> orgList = new ArrayList<EhrOrganization>();
			orgList.add(topOrg);
			if(monthlySalaryMapper.countValidByOrgList(monthlySalary.getUsageFlag(), monthlySalary.getMonthly(), orgList) > 0){
				throw new Exception("总公司数据已生成，不能再生成分公司数据");
			}
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("monthly", monthlySalary.getMonthly());
		params.put("usageFlag", monthlySalary.getUsageFlag());
		params.put("organizationId", monthlySalary.getOrganizationId());
		
		//如果有效的月度工资已经发放，则不允许再生成
		params.put("lastFlag", 1);
		EhrMonthlySalary exist = monthlySalaryMapper.selectByParam(params);
		if(exist != null){
			if(exist.getReviewStatus() == 3){
				throw new Exception("工资已经发放");
			}
			
			//把以前的失效(只能有一个有效的版本)
			exist.setLastFlag(0);
			monthlySalaryMapper.updateByPrimaryKeySelective(exist);
		}
		
		//设置版本号并生成新的有效月度工资
		params.put("lastFlag", null);
		Integer version = monthlySalaryMapper.searchMaxVersion(params);
		monthlySalary.setVersion(version == null ? 1 : (version + 1));
		monthlySalaryMapper.insert(monthlySalary);
		
		return this.generateDetail(monthlySalary);
	}
	
	private List<String> generateDetail(EhrMonthlySalary monthlySalary) throws Exception{
		List<String> result = new CopyOnWriteArrayList<String>();
		int payDayCount = salaryDateService.getPayDayCount(monthlySalary.getMonthly(), monthlySalary.getCompanyId());
		
		//从员工工资表ehr_salary中查找用途匹配的在发人员列表
		List<EhrSalary> salaryList = this.getSalaryList(monthlySalary);
//		//开启线程池
//		List<Future> futureList = new ArrayList<Future>();
//		ExecutorService pool = Executors.newFixedThreadPool(20);
    	for(EhrSalary salary : salaryList){
//    		//添加新任务到线程池
//    		Future<String> future = pool.submit(new CallableSalaryCalculator());
//    		futureList.add(future);
    		
    		//获取人员及工资模板
    		EhrPerson person = personMapper.selectByPrimaryKey(salary.getPersonId());
    		EhrSalaryTemplate template = templateMapper.selectByPrimaryKey(salary.getSalaryTemplateId());
    		
    		//查询员工当前月度、其它月度工资中的 taxable_he_income及withholding_tax
    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("monthly", monthlySalary.getMonthly());
    		params.put("monthlySalaryId", monthlySalary.getMonthlySalaryId());
    		params.put("personId", salary.getPersonId());
    		Map<String,Object> taxHistory = monthlySalaryPersonMapper.getTaxHistory(params);
    		BigDecimal taxableHeIncome = null;
    		BigDecimal withholdingHeTax = null;
    		if(taxHistory != null){
    			taxableHeIncome = (BigDecimal)taxHistory.get("taxableHeIncome");
    			withholdingHeTax = (BigDecimal)taxHistory.get("withholdingHeTax");
    		}

    		//初始化一条ehr_monthly_salary_person，保存并获取其Id
    		EhrMonthlySalaryPerson monthlyPerson = this.createMonthlySalaryPerson(monthlySalary.getMonthlySalaryId(), 
    				person.getPersonId(), person.getEmployeeName());
    		
    		//根据员工工资查找明细
    		List<EhrSalaryDetail> sdList = salaryDetailMapper.loadDetailList(salary.getSalaryId());
    		
    		//工资项目Map
    		Map<Long,EhrSalaryItem> salaryItemMap = this.getSalaryItemMap(sdList);
    		
//    		//设置通用、系统变动表、自定义变动数据
//    		this.prepareContext(ctx,sdList,monthlySalary.getMonthly(),salary.getPersonId(),salaryItemMap);
    		
    		double taxableIncome = 0;
			double taxfreeIncome = 0;
			double takeHomeIncome = 0;
			double coalescentTaxableIncome = 0;
			Map<String,Object> parsedItemMap = new HashMap<String,Object>();
			while(sdList.size() > 0){
				//存放解析失败的公式对应的工资明细
				List<EhrSalaryDetail> tempList = new ArrayList<EhrSalaryDetail>();
				
	    		for(EhrSalaryDetail salaryDetail : sdList){
	    			//初始化上下文
	        		MapContext ctx = new MapContext();
	        		ctx.set("计薪天数", payDayCount);
	        		
		    		//获取ehr_salary_item
		    		EhrSalaryItem salaryItem = salaryItemMap.get(salaryDetail.getSalaryItemId());
	    			
	    			//构建EhrMonthlySalaryDetail
		    		EhrMonthlySalaryDetail monthlyDetail = new EhrMonthlySalaryDetail();
		    		monthlyDetail.setChangeFlag(0);
		    		monthlyDetail.setMonthlySalaryPersonId(monthlyPerson.getMonthlySalaryPersonId());
		    		monthlyDetail.setSalaryItemId(salaryItem.getSalaryItemId());
		    		monthlyDetail.setSalaryItemName(salaryItem.getSalaryItemName());
		    		
		    		//计算试用天数、计薪天数、发放比例(如果前两者相等，则按发放比例发放)
		    		//	计薪日变动表：(正常情况计非周末，节假日需录入调整日期及调整类型)
//		    		int pwd = Tools.getPayDayCountBefore(db, mdl.getMonthly(), mdl.getCompanyId(),person.getPositiveDate());//试用天数
//	    			int fwd = Tools.getPayDayCount(db, mdl.getMonthly(),mdl.getCompanyId());//计薪天数
//	    			double payratio = 1.0;
//	    			if(pwd == fwd){
//	    				payratio = salaryDetail.getPayRadio().doubleValue();
//	    			}
		    		double payRatio = salaryDetail.getPayRadio().doubleValue();
	    			//计算当前工资变动项目-FelEngine
	    			//BigDecimal amount = calSalaryItemAmount(salaryItem, salary, monthlySalary, monthlyPerson, payRatio);
	    			
		    		//无论变量常量，计算完成后放入上下文
		    		BigDecimal amount = null;
		    		if(StringUtils.isEmpty(salaryItem.getChangeFormula())){	//常量(非公式)
	    				//计算入职前天数及离职后天数。试用期离职则重算试用期
	    				//试用期工资计算逻辑？SelectValues.USE_DATE_CAL_ITEMS
//						int nwd = Tools.getPayDayCountBefore(db, mdl.getMonthly(), mdl.getCompanyId(),person.getEntryDate());//入职前天数
//						int lwd = 0;//离职后天数
//						if(person.getLeftDate() != null){
//							lwd = fwd - Tools.getPayDayCountBefore(db, mdl.getMonthly(), mdl.getCompanyId(),Tools.addDay(person.getLeftDate(), 1));
//						}
//						if(pwd + lwd > fwd){
//							pwd = fwd - lwd;
//						}
//						if(SelectValues.USE_DATE_CAL_ITEMS.contains(salaryDetail.getSalaryItemId())){
//		    				if(salaryDetail.getPayRadio().doubleValue() != 1){
//		    					amount = salaryDetail.getAmount().doubleValue() / fwd;
//		    					amount = amount*(pwd-nwd)*salaryDetail.getPayRadio().doubleValue() + amount * (fwd - pwd - lwd);
//		    				} else {
//		    					amount = salaryDetail.getAmount().doubleValue() / fwd;
//		    					amount = amount * (fwd - nwd - lwd);
//		    				}
//						} else {
//	    					amount = salaryDetail.getAmount().doubleValue();
//						}
	    				amount = salaryDetail.getAmount();
	    				amount = (amount == null) ? BigDecimal.ZERO : amount.multiply(salaryDetail.getPayRadio());
	    				
	    				ctx.set(salaryItem.getSalaryItemName(), amount);
	    			} else {
	    				//所有员工都必须有变动数据，否则解析不出来
	    				this.prepareContext(ctx, salaryItem, monthlySalary.getMonthly(), person, parsedItemMap);
	    				amount = this.parseFormula(ctx, salaryItem.getSalaryItemName(), salaryItem.getChangeFormula(), salaryDetail.getPayRadio());
	    				if(amount == null){	//解析失败
	    					tempList.add(salaryDetail);
	    					continue;
	    				}
	    				
	    				
	    				//变动标志置为1(属于变动数据)
	    				monthlyDetail.setChangeFlag(1);
	    			}
		    		
		    		//加入已解析的项目
		    		parsedItemMap.put(salaryItem.getSalaryItemName(), amount == null ? BigDecimal.ZERO : amount);
		    		
	    			monthlyDetail.setAmount(amount);
	    			monthlyDetail.setOriginalAmount(salaryDetail.getAmount());
	    			//保存EhrMonthlySalaryDetail
	    			monthlySalaryDetailMapper.insertSelective(monthlyDetail);
	    			if(salaryItem.getIsTransition()){
	    				//中间项只保存到明细表，不影响工资计算
	    				continue;
	    			}
	    			
	    			//根据yearly_flag计算养老、医疗、失业、公积金等
	    			if(salaryItem.getYearlyFlag() != null && salaryItem.getYearlyFlag().intValue() == 2){
	    				monthlyPerson.setYanglaoAmount(monthlyPerson.getYanglaoAmount().add(amount));
	    			}
	    			else if(salaryItem.getYearlyFlag( )!= null && salaryItem.getYearlyFlag().intValue() == 3){
	    				monthlyPerson.setYiliaoAmount(monthlyPerson.getYiliaoAmount().add(amount));
	    			}
	    			else if(salaryItem.getYearlyFlag() != null && salaryItem.getYearlyFlag().intValue() == 4){
	    				monthlyPerson.setShiyeAmount(monthlyPerson.getShiyeAmount().add(amount));
	    			}
	    			else if(salaryItem.getYearlyFlag() != null && salaryItem.getYearlyFlag().intValue() == 5){
	    				monthlyPerson.setGongjijinAmount(monthlyPerson.getGongjijinAmount().add(amount));
	    			}
	    			
	    			if(salaryItem.getTaxRule().intValue() == 1){//合并计税：根据税前计入/税后计入以及加项/减项累加
	    				if(salaryItem.getCalculateRule().intValue() == 1){//税前计入
	    					if(salaryItem.getItemType().intValue() == 1){//加项
	    						taxableIncome = taxableIncome + amount.doubleValue();
	    						
	    						coalescentTaxableIncome = coalescentTaxableIncome + amount.doubleValue();
	    					} else {//减项
	    						taxableIncome = taxableIncome - amount.doubleValue();
	    						taxfreeIncome = taxfreeIncome + amount.doubleValue();
	    						
	    						coalescentTaxableIncome = coalescentTaxableIncome - amount.doubleValue();
	    					}
	    				} else {//税后计入
	    					if(salaryItem.getItemType().intValue() == 1){//加项
	    						takeHomeIncome = takeHomeIncome + amount.doubleValue();
	    						taxfreeIncome = taxfreeIncome + amount.doubleValue();
	    					} else {//减项
	    						takeHomeIncome = takeHomeIncome - amount.doubleValue();
	    						taxfreeIncome = taxfreeIncome - amount.doubleValue();
	    					}
	    				}
	    			} else {//独立计税：计算setTaxableIncome及setWithholdingTax
	        			//判断ehr_salary_item中的yearly_flag
	        			boolean yearlyFlag = false;
	        			if(salaryItem.getYearlyFlag() != null && salaryItem.getYearlyFlag().intValue() == 1){
	        				yearlyFlag = true;
	        			}
	        			
	    				monthlyPerson.setTaxableIncome(monthlyPerson.getTaxableIncome().add(amount));
//	    				BigDecimal taxAmount = this.calTaxAmount(new EhrMonthlySalaryPerson(),salaryItem.getTaxRateId(), amount,
//								taxableHeIncome == null ? BigDecimal.ZERO : taxableHeIncome, BigDecimal.ZERO, yearlyFlag);
	    				BigDecimal taxAmount = this.calTaxAmount(salaryItem.getTaxRateId(), amount, yearlyFlag);
	    				monthlyPerson.setWithholdingTax(monthlyPerson.getWithholdingTax().add(taxAmount));
	    			}
	    		}
	    		
	    		if(sdList.size() == tempList.size()){
	    			//返回未解析的列表
	    			for(EhrSalaryDetail tempSd: tempList){
	    				result.add("员工工资项目公式解析失败：" + tempSd.getSalaryItemName());
	    			}
	    			//throw new Exception("员工工资项目公式解析失败：" + person.getJobNumber());
	    			return result;
	    		}
	    		
	    		sdList = tempList;
			}
    		
    		//设置ehr_monthly_salary_person的合并计税项，并更新
    		monthlyPerson.setTaxableIncome(monthlyPerson.getTaxableIncome().add(new BigDecimal(taxableIncome)));
    		monthlyPerson.setTaxfreeIncome(new BigDecimal(taxfreeIncome));
    		//monthlyPerson.setTaxableHeIncome(new BigDecimal(taxableIncome));
    		monthlyPerson.setTaxableHeIncome(new BigDecimal(coalescentTaxableIncome));
    		BigDecimal coalescentTaxAmount = this.calCoalescentTaxAmount(monthlyPerson,template.getTaxRateId(), 
    				new BigDecimal(coalescentTaxableIncome), taxableHeIncome == null ? BigDecimal.ZERO : taxableHeIncome,
					withholdingHeTax == null ? BigDecimal.ZERO : withholdingHeTax, false);
    		monthlyPerson.setWithholdingTax(monthlyPerson.getWithholdingTax().add(coalescentTaxAmount));
    		monthlyPerson.setWithholdingHeTax(monthlyPerson.getWithholdingHeTax().add(coalescentTaxAmount));
    		monthlyPerson.setAfterTaxIncome(monthlyPerson.getTaxableIncome().subtract(monthlyPerson.getWithholdingTax()));
    		monthlyPerson.setTakeHomeIncome(monthlyPerson.getAfterTaxIncome().add(new BigDecimal(takeHomeIncome)));
    		//monthlySalaryPersonMapper.updateByPrimaryKey(monthlyPerson);
    		monthlySalaryPersonMapper.updateByPrimaryKeySelective(monthlyPerson);
    	}
    	
//    	//关闭线程池，处理结果
//    	pool.shutdown();
//    	for(Future<String> future : futureList){
//    		if(future.get() != null){
//    			//有错误需要抛出异常，避免部分保存
//    			result.add(future.get());
//    		}
//    	}
    	return result;
	}
	
	private List<EhrSalary> getSalaryList(final EhrMonthlySalary monthlySalary){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", 1);
		params.put("usageFlag", monthlySalary.getUsageFlag());
		params.put("companyId", monthlySalary.getCompanyId());
		//获取组织机构
		if(monthlySalary.getOrganizationId() != null){
			List<EhrOrganization> orgList = organizationService.getTreeByParent(monthlySalary.getOrganizationId());
			params.put("orgList", orgList);
		}
		return salaryMapper.selectByParam(params);
	}
	
	private EhrMonthlySalaryPerson createMonthlySalaryPerson(final Long monthlySalaryId, final Long personId, final String employeeName){
		EhrMonthlySalaryPerson salaryPerson = new EhrMonthlySalaryPerson();
		salaryPerson.setMonthlySalaryId(monthlySalaryId);
		salaryPerson.setPersonId(personId);
		salaryPerson.setPersonName(employeeName);
		salaryPerson.setAfterTaxIncome(BigDecimal.ZERO);
		salaryPerson.setTaxfreeIncome(BigDecimal.ZERO);
		salaryPerson.setTakeHomeIncome(BigDecimal.ZERO);
		salaryPerson.setTaxableIncome(BigDecimal.ZERO);
		salaryPerson.setTaxableHeIncome(BigDecimal.ZERO);
		salaryPerson.setWithholdingTax(BigDecimal.ZERO);
		salaryPerson.setWithholdingHeTax(BigDecimal.ZERO);
		salaryPerson.setWithholdingedTax(BigDecimal.ZERO);
		salaryPerson.setYanglaoAmount(BigDecimal.ZERO);
		salaryPerson.setYiliaoAmount(BigDecimal.ZERO);
		salaryPerson.setShiyeAmount(BigDecimal.ZERO);
		salaryPerson.setGongjijinAmount(BigDecimal.ZERO);
		
		monthlySalaryPersonMapper.insertSelective(salaryPerson);
		return salaryPerson;
	}
	
	private Map<Long,EhrSalaryItem> getSalaryItemMap(List<EhrSalaryDetail> sdList){
		Map<Long,EhrSalaryItem> result = new HashMap<Long,EhrSalaryItem>();
		for(EhrSalaryDetail salaryDetail : sdList){
    		EhrSalaryItem salaryItem = salaryItemMapper.selectByPrimaryKey(salaryDetail.getSalaryItemId());
    		result.put(salaryDetail.getSalaryItemId(), salaryItem);
		}
		return result;
	}
	
//	private void prepareContext(MapContext ctx, List<EhrSalaryDetail> sdList,String monthly, Long personId,
//			Map<Long,EhrSalaryItem> salaryItemMap) throws Exception{
//		//需要加载数据的自定义变动表
//		Map<Long,Long> defIdMap = new HashMap<Long,Long>();
//		
//		//获取需要加载数据的自定义变动表
//		for(EhrSalaryDetail sd : sdList){
//			EhrSalaryItem salaryItem = salaryItemMap.get(sd.getSalaryItemId());
//			if(!StringUtils.isEmpty(salaryItem.getChangeFormula())){
//				if(salaryItem.getSalaryChangeDefId() != null){
//					defIdMap.put(salaryItem.getSalaryChangeDefId(), null);
//				}
//			}
//		}
//		
//		//1.常量(通用、员工)
//		//SalaryHelper.SALARY_CONSTANT_COMMON
//		//SalaryHelper.SALARY_CONSTANT_PERSON
//		
//		//2.系统变动表
//		String yearMonthRecord = monthly.substring(0,4) + "." + monthly.substring(4) + ".01";
//		Map<String,Object> attendanceInfo = attendanceMapper.sumAttendance(personId, yearMonthRecord);
//		for(EhrSalaryChangeDefItem item : SalaryHelper.getAttendanceDefination()){
//			if(item.getDataType().equals(SalaryEnum.SalaryChangeDefItemType.Character.getValue())){
//				continue;
//			}
//			
//			Object v = attendanceInfo.get(item.getColumnName());
//			ctx.set(item.getDisplayName(), v == null ? BigDecimal.ZERO : new BigDecimal(v.toString()));
//		}
//		
//		for(EhrSalaryChangeDefItem item : SalaryHelper.getSocialInsuranceDefination()){
//		
//		}
//		
//		//3.自定义变动表
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("monthly", monthly);
//		params.put("personId", personId);
//		for(Long defId : defIdMap.keySet()){
//			EhrSalaryChangeDef def = salaryChangeDefMapper.selectByPrimaryKey(defId);
//			List<EhrSalaryChangeDefItem> defItemList = salaryChangeDefItemMapper.listByDefId(defId);
//
//			params.put("salaryChangeDefId", defId);
//			List<Map<String,Object>> changeDataList = salaryChangeDataMapper.listSalaryChangeData(params);
//			
//			//累加并设置上下文
//			this.setChangeDataToContext(ctx,def,defItemList,changeDataList);
//		}
//	}
	
	private void prepareContext(MapContext ctx, EhrSalaryItem si,String monthly, EhrPerson person, 
			Map<String,Object> parsedItemMap) throws Exception{
		if(StringUtils.isEmpty(si.getChangeFormula())){
			return ;
		}

		//1.常量(通用、员工)
		ctx.set("薪资月度",monthly);
		//SalaryHelper.SALARY_CONSTANT_COMMON
		//SalaryHelper.SALARY_CONSTANT_PERSON
		//SALARY_CONSTANT_PERSON.put("用工性质",new EhrSalaryChangeDefItem("用工性质", "EmploymentType",1));
		ctx.set("司龄",person.getInCompanyTime());
		ctx.set("年龄",person.getAge());
		//SALARY_CONSTANT_PERSON.put("最高学历",new EhrSalaryChangeDefItem("最高学历", "Degree",1));
		//SALARY_CONSTANT_PERSON.put("最高学位",new EhrSalaryChangeDefItem("最高学位", "Education",1));
		//ctx.set("工作地点",person.getWorkLocation());
		ctx.set("民族",person.getNationality());
		
		if(si.getSalaryChangeDefId() != null){
			Long defId = si.getSalaryChangeDefId();
			//2.自定义变动表
			EhrSalaryChangeDef def = salaryChangeDefMapper.selectByPrimaryKey(defId);
			List<EhrSalaryChangeDefItem> defItemList = salaryChangeDefItemMapper.listByDefId(defId);
			
			Map<String,Object> params = new HashMap<String,Object>();
			if(def.getIsMonthly() == 1){
				params.put("monthly", monthly);
			}
			params.put("personId", person.getPersonId());
			params.put("salaryChangeDefId", defId);
			List<Map<String,Object>> changeDataList = salaryChangeDataMapper.listSalaryChangeData(params);
			
			//累加并设置上下文
			this.setChangeDataToContext(ctx,def,defItemList,changeDataList);
		}else if(!StringUtils.isEmpty(si.getChangeTableName())){
			//3.系统变动表
			List<EhrSalaryChangeDefItem> scdiList = null;
			Map<String,Object> scdMap = null;
			if(SalaryEnum.SystemChangeTable.Attendance.getTableName().equals(si.getChangeTableName())){
				scdiList = SalaryHelper.getAttendanceDefination();
				//String yearMonthRecord = monthly.substring(0,4) + "." + monthly.substring(4) + ".01";
				String yearMonthRecord = monthly.substring(0,4) + "-" + monthly.substring(4);
				scdMap = attendanceMapper.sumAttendance(person.getPersonId(), yearMonthRecord);
			}else if(SalaryEnum.SystemChangeTable.SocialSecurity.getTableName().equals(si.getChangeTableName())){
				scdiList = SalaryHelper.getSocialSecurityDefination();
				scdMap = ssPaymentBillPersonMapper.selectByPersonAndMonth(monthly, person.getPersonId());
			}else if(SalaryEnum.SystemChangeTable.AccumulationFund.getTableName().equals(si.getChangeTableName())){
				scdiList = SalaryHelper.getAccumulationFundDefination();
				scdMap = afPaymentBillPersonMapper.selectByPersonAndMonth(monthly, person.getPersonId());
			}
			
			if(scdMap == null){
				scdMap = new HashMap<String,Object>();
			}
			for(EhrSalaryChangeDefItem item : scdiList){
				Object v = scdMap.get(item.getColumnName());
				if(item.getDataType().equals(SalaryEnum.SalaryChangeDefItemType.Character.getValue())){
					ctx.set(item.getDisplayName(), v == null ? "" : v.toString());
				}else{
					ctx.set(item.getDisplayName(), v == null ? BigDecimal.ZERO : new BigDecimal(v.toString()));
				}
			}
		}
		
		//4.加入已解析的项目
		for(String displayName : parsedItemMap.keySet()){
			ctx.set(displayName, parsedItemMap.get(displayName));
		}
	}
	
	private BigDecimal parseFormula(MapContext ctx, String itemName, String formula, BigDecimal payRatio){
		FelEngine fel = new FelEngineImpl();
		try {
			Object v = fel.eval(formula, ctx);
			BigDecimal result = (v == null ? BigDecimal.ZERO : new BigDecimal(v.toString()).multiply(payRatio));
			//解析成功则放入上下文
			ctx.set(itemName, result);
			return result;
		} catch (Exception e) {
			log.warn("公式解析失败：" + formula);
		}
		
		return null;
	}
	
	private void setChangeDataToContext(MapContext ctx,EhrSalaryChangeDef def, List<EhrSalaryChangeDefItem> defItemList,
			List<Map<String,Object>> changeDataList){
		Map<String,EhrSalaryChangeDefItem> itemMap = new HashMap<String,EhrSalaryChangeDefItem>();
		//选择需要处理的变动字段
		for(EhrSalaryChangeDefItem item : defItemList){
			if(item.getDisplayName().equals(def.getKeyInfo())){
				continue;
			}
			//if(item.getDataType().equals(SalaryEnum.SalaryChangeDefItemType.Character.getValue())){
			//	//不处理字符串类型
			//	continue;
			//}
			
			itemMap.put(item.getColumnName(), item);
		}
		
		//Map<String,BigDecimal> dataMap = new HashMap<String,BigDecimal>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		//遍历变动数据
		for(Map<String,Object> changeData : changeDataList){
			//遍历需要处理的变动字段
			for(String columnName : itemMap.keySet()){
				Object v = changeData.get(columnName);
				EhrSalaryChangeDefItem item = itemMap.get(columnName);
				if(item.getDataType().equals(SalaryEnum.SalaryChangeDefItemType.Number.getValue())){
					//数据为空加默认值，避免公式解析不出
					if(v == null || "".equals(v.toString().trim())){
						//continue;
						v = "0";
					}
					
					try {
						//字段值累加
						if(dataMap.containsKey(item.getDisplayName())){
							dataMap.put(item.getDisplayName(), ((BigDecimal)dataMap.get(item.getDisplayName())).add(new BigDecimal(v.toString())));
						}else{
							dataMap.put(item.getDisplayName(), new BigDecimal(v.toString()));
						}
					} catch (Exception e) {
						dataMap.put(item.getDisplayName(), BigDecimal.ZERO);
						log.warn("变动数据与类型不匹配：" + item.getDisplayName());
					}
				}else{
					dataMap.put(item.getDisplayName(), v == null ? "" : v.toString());
				}
			}
		}
		
		//使用累加值设置上下文
		//for(String displayName : dataMap.keySet()){
		//	ctx.set(displayName, dataMap.get(displayName));
		//}
		
		//对所需要设置值的变动项，如果为空则赋默认值，避免无法解析公式
		for(EhrSalaryChangeDefItem item : itemMap.values()){
			String displayName = item.getDisplayName();
			if(dataMap.containsKey(displayName)){
				ctx.set(displayName, dataMap.get(displayName));
			}else{
				if(item.getDataType().equals(SalaryEnum.SalaryChangeDefItemType.Number.getValue())){
					ctx.set(displayName, BigDecimal.ZERO);
				}else{
					ctx.set(displayName, "");
				}
			}
		}
	}
    
//    private BigDecimal calSalaryItemAmount(EhrSalaryItem salaryItem, EhrSalary salary, EhrMonthlySalary monthlySalary,
//    		EhrMonthlySalaryPerson monthlyPerson, double payratio) throws Exception {
//    	if(salaryItem.getChangeTableName()!=null && !salaryItem.getChangeTableName().trim().equals("") && 
//				salaryItem.getChangeFormula()!=null && !salaryItem.getChangeFormula().trim().equals("")){
//
//			String sqlbase=null;
//			Vector dv=new Vector();
//			if(salaryItem.getChangeTableName().equals("ehr_common_change")){
//    			dv=this.getCommonChangeDefination(salaryItem.getSalaryItemId());
//    			sqlbase=" from "+salaryItem.getChangeTableName()+
//    					" where salary_item_id="+salaryItem.getSalaryItemId().longValue()+" and person_id="+personId.longValue()+
//    					" and "+SelectValues.MONTHLY_COLNAME.get(salaryItem.getChangeTableName())+"="+Tools.strToStr(monthly);
//			}
//			else if(salaryItem.getChangeTableName().equals("ehr_social_insurance")){
//				dv=this.getSocialInsuranceDefination();
//				String tsql="update ehr_social_insurance " +
//						"set salary_month='"+monthly+"' " +
//								" where salary_month is null and person_id="+personId.longValue()+" and "+SelectValues.MONTHLY_COLNAME.get(salaryItem.getChangeTableName())+"<="+monthly;
//				db.execUpdate(tsql);
//				sqlbase=" from "+salaryItem.getChangeTableName()+
//						" where person_id="+personId.longValue()+" and "+SelectValues.MONTHLY_COLNAME.get(salaryItem.getChangeTableName())+"<="+monthly+" and salary_month='"+monthly+"'";
//			}
//			else if(salaryItem.getChangeTableName().equals("ehr_attendance")){
//				dv=this.getAttendanceDefination();
//    			sqlbase=" from "+salaryItem.getChangeTableName()+
//    					" where person_id="+personId.longValue()+
//    					" and "+SelectValues.MONTHLY_COLNAME.get(salaryItem.getChangeTableName())+"="+Tools.strToStr(monthly.substring(0, 4)+"."+monthly.substring(4)+".01");
//			}
//			else{
//				return null;
//			}
//			
//    		MapContext ctx=new MapContext();
//    		boolean flag=true;
//    		String sql="select "+Tools.getFieldStr(EhrMonthlySalaryDetailModel.class)+
//    				" from ehr_monthly_salary_detail " +
//    				"where salary_item_id<>"+salaryItem.getSalaryItemId().longValue()+" and monthly_salary_person_id="+monthlyPersonId.longValue();
//    		Vector iv=db.getVector(sql, EhrMonthlySalaryDetailModel.class);
//    		for(int i=0;i<iv.size();i++){
//    			EhrMonthlySalaryDetailModel sdmodel=(EhrMonthlySalaryDetailModel) iv.get(i);
//    			if(sdmodel.getOriginalAmount()!=null && sdmodel.getOriginalAmount().doubleValue()!=0){
//        			ctx.set(sdmodel.getSalaryItemName(), sdmodel.getOriginalAmount().doubleValue()*payratio);
//    			}
//    			else{
//        			ctx.set(sdmodel.getSalaryItemName(), sdmodel.getAmount().doubleValue());
//    			}
//    		}
//    		for(int i=0;i<dv.size();i++){
//    			EhrSalaryItemDefinationModel dmdl=(EhrSalaryItemDefinationModel)dv.get(i);
//    			if(dmdl.getDataType().intValue()==1)
//    				continue;
//    			String value=db.getString("select sum("+dmdl.getColumnName()+") "+sqlbase);
//    			if(value==null || value.trim().equals("")){
//        			ctx.set(dmdl.getDisplayName(), new Double(0));
//    			}
//    			else{
//        			flag=false;
//        			ctx.set(dmdl.getDisplayName(), new Double(value.trim()));
//    			}
//    		}
//			ctx.set("计薪天数", Tools.getPayDayCount(db, monthly, companyId));
//    		if(flag)
//    			return null;
//    		System.out.println(salaryItem.getChangeFormula()+":"+personId.longValue());
//    		BigDecimal rvalue=new BigDecimal(fel.eval(salaryItem.getChangeFormula(),ctx).toString());
//    		return rvalue;
//		}
//    	return null;
//    }

    private BigDecimal calTaxAmount(Long taxRateId,BigDecimal amount, boolean yearlyFlag) throws Exception {
    	EhrTaxRate taxRate = taxRateMapper.selectByPrimaryKey(taxRateId);
    	
    	double threshold = 0;
    	if(taxRate.getThreshold() != null){
    		threshold = taxRate.getThreshold().doubleValue();
    	}
    	
    	double taxableamount = amount.doubleValue();
    	
    	double lamount = 0;
    	if(!yearlyFlag){
    		lamount = taxableamount - threshold;
    	}else{
    		lamount = taxableamount;
    	}
    	if(lamount <= 0){
    		return BigDecimal.ZERO;
    	}
    	
    	EhrTaxLevel taxLevel = this.chooseTaxLevel(taxRateId, lamount);
    	if(taxLevel == null){
    		throw new Exception("税率信息设置不正确：" + taxRate.getTaxRateName());
    	}
    	
    	Double taxamount = lamount * taxLevel.getTaxRate().doubleValue() - taxLevel.getFastCalculationAmount().doubleValue();
    	taxamount = Math.round(taxamount.doubleValue() * 100) / 100.0;
    	return BigDecimal.valueOf(taxamount);
    }

    private BigDecimal calCoalescentTaxAmount(EhrMonthlySalaryPerson salaryPerson, Long taxRateId,BigDecimal amount,
    		BigDecimal samount, BigDecimal taxedamount, boolean yearlyFlag) throws Exception {
    	EhrTaxRate taxRate = taxRateMapper.selectByPrimaryKey(taxRateId);
    	salaryPerson.setThreshold(taxRate.getThreshold());
    	
    	double threshold = 0;
    	if(taxRate.getThreshold() != null){
    		threshold = taxRate.getThreshold().doubleValue();
    	}
    	
    	double taxableamount = 0;
    	if(!yearlyFlag){
    		taxableamount = samount.doubleValue() + amount.doubleValue();
    	}else{
    		if(samount.doubleValue() < threshold){
    			taxableamount = amount.doubleValue() - threshold + samount.doubleValue();
    		}
    	}
    	
    	double lamount = 0;
    	if(!yearlyFlag){
    		lamount = taxableamount - threshold;
    	}else{
    		lamount = taxableamount;
    	}
    	if(lamount <= 0){
    		return BigDecimal.ZERO;
    	}
    	
    	EhrTaxLevel taxLevel = this.chooseTaxLevel(taxRateId, lamount);
    	if(taxLevel == null){
    		throw new Exception("税率信息设置不正确：" + taxRate.getTaxRateName());
    	}
    	
    	salaryPerson.setTaxRate(taxLevel.getTaxRate());
    	salaryPerson.setFastCalculationAmount(taxLevel.getFastCalculationAmount());
    	salaryPerson.setWithholdingedTax(taxedamount);
    	Double taxamount = lamount * taxLevel.getTaxRate().doubleValue() - taxLevel.getFastCalculationAmount().doubleValue() - taxedamount.doubleValue();
    	taxamount = Math.round(taxamount.doubleValue() * 100) / 100.0;
    	return BigDecimal.valueOf(taxamount);
    }
    
    private EhrTaxLevel chooseTaxLevel(Long taxRateId, double lamount){
    	List<EhrTaxLevel> taxLevelList = taxLevelMapper.queryByTaxRateId(taxRateId);
		EhrTaxLevel taxLevel = null;
		for(EhrTaxLevel tempLevel : taxLevelList){
			if(lamount >= tempLevel.getLevelLower().doubleValue() && 
					lamount <= tempLevel.getLevelLimit().doubleValue()){
				taxLevel = tempLevel;
				break;
			}
		}
		
		return taxLevel;
    }
    
	private class CallableSalaryCalculator implements Callable<String>{
		public CallableSalaryCalculator(){
			
		}
		
		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public EhrMonthlySalary selectByPrimaryKey(Long monthlySalaryId) {
		return monthlySalaryMapper.selectByPrimaryKey(monthlySalaryId);
	}

	@Override
	public List<Map<String, Object>> loadMonthlySalaryPersonHeader(Long monthlySalaryId) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result.add(this.getFieldHeader("monthlySalaryPersonId", "ID"));
		result.add(this.getFieldHeader("monthlySalaryId", "月度工资ID"));
		result.add(this.getFieldHeader("personId", "员工ID"));
		result.add(this.getFieldHeader("jobNumber", "员工工号"));
		result.add(this.getFieldHeader("personName", "员工姓名"));
		result.add(this.getFieldHeader("taxableIncome", "应税工资"));
		result.add(this.getFieldHeader("withholdingTax", "代扣税"));
		result.add(this.getFieldHeader("afterTaxIncome", "税后工资"));
		result.add(this.getFieldHeader("takeHomeIncome", "实发工资"));
		
		List<EhrSalaryItem> itemList = salaryItemMapper.listByMonthlySalaryId(monthlySalaryId);
		for(EhrSalaryItem item : itemList){
			result.add(this.getFieldHeader("item" + item.getSalaryItemId(), item.getSalaryItemName()));
		}
		
		result.add(this.getFieldHeader("sendStatus", "发送状态"));
		
		return result;
	}
	
	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}

	@Override
	public List<Map<String, Object>> loadMonthlySalaryPersonPage(Map<String,Object> params) {
		//Long monthlySalaryId = (Long)params.get("monthlySalaryId");
		List<EhrMonthlySalaryPerson> salaryPersonList = monthlySalaryPersonMapper.selectByMonthlySalaryIdPage(params);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	for(EhrMonthlySalaryPerson msp : salaryPersonList){
    		EhrPerson person = personMapper.selectByPrimaryKey(msp.getPersonId());
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("monthlySalaryPersonId", msp.getMonthlySalaryPersonId());
    		map.put("monthlySalaryId", msp.getMonthlySalaryId());
    		map.put("personId", msp.getPersonId());
    		map.put("jobNumber", person.getJobNumber());
    		map.put("personName", msp.getPersonName());
    		map.put("taxableIncome", msp.getTaxableIncome());
    		map.put("withholdingTax", msp.getWithholdingTax());
    		map.put("afterTaxIncome", msp.getAfterTaxIncome());
    		map.put("takeHomeIncome", msp.getTakeHomeIncome());
    		
    		List<EhrMonthlySalaryDetail> detailList = monthlySalaryDetailMapper.selectByMonthlySalaryPersonId(msp.getMonthlySalaryPersonId());
    		for(EhrMonthlySalaryDetail detail : detailList){
    			map.put("item" + detail.getSalaryItemId(), detail.getAmount());
    		}
    		
    		map.put("sendStatus", (msp.getSendStatus() != null && msp.getSendStatus() == 1) ? "已发送" : "未发送");
    		
    		result.add(map);
    	}
    	
    	return result;
	}
	
	public Map<String,Object> payoff(EhrUser user, EhrMonthlySalary monthlySalary){
		List<HroPactInfo> pactList = hroPactInfoMapper.queryPactInfoListByCompany(user.getCompanyId());
		if(pactList == null || pactList.isEmpty()){
			return MessageUtil.errorMessage("不存在协议号");
		}
		
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("protocolCode", pactList.get(pactList.size() - 1).getPactCode());
		entity.put("batchCode", monthlySalary.getMonthlySalaryId().toString() + "_" + System.currentTimeMillis());
		entity.put("salaryMonth", monthlySalary.getMonthly());
		entity.put("grantDate", new Date());
		entity.put("remark", null);
		
		List<EmpSalaryDetailVO> empSalaryDetails = new ArrayList<EmpSalaryDetailVO>();
		for(Map<String,Object> msp : monthlySalaryPersonMapper.selectByMonthlySalaryId(monthlySalary.getMonthlySalaryId())){
			EmpSalaryDetailVO vo = new EmpSalaryDetailVO();
			vo.setEmpName((String)msp.get("employee_name"));
			//Jabava与HRO类型桥接
			if(msp.get("cert_type") != null){
				vo.setCardType(Integer.valueOf(msp.get("cert_type").toString()));
			}else{	//默认其他
				vo.setCardType(Integer.valueOf(CommonDataConstants.COMMON_DATA_CERT_TYPE_OTHER));
			}
			vo.setCardId((String)msp.get("cert_id"));
//			vo.setAmountSalary(new BigDecimal(msp.get("after_tax_income").toString()));
//			vo.setAmountSalaried(new BigDecimal(msp.get("take_home_income").toString()));
			vo.setAmountSalary(new BigDecimal(msp.get("taxable_income").toString()));
			vo.setAmountSalaried(new BigDecimal(msp.get("after_tax_income").toString()));
			vo.setTaxType(1);	//1. 正常; 2. 外国; 3. 劳务
			vo.setBankName((String)msp.get("bank_name"));
			vo.setPayCard((String)msp.get("salary_card"));
			empSalaryDetails.add(vo);
		}
		entity.put("empSalaryDetails", empSalaryDetails);
		//entity.put("dataSource", "EHR");

		try {
			Map<String,Object> result = this.getHROFetchService().invoke("importSalaryEmp", entity);
			if ("0".equals(result.get("resultCode").toString())) {
				return MessageUtil.successMessage("发放成功");
			}else {
				if(result.get("resultMessage") != null && StringUtils.isNotBlank(result.get("resultMessage").toString())){
					return MessageUtil.errorMessage(result.get("resultMessage").toString());
				}else{
					return MessageUtil.errorMessage("发放失败，未返回错误信息");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  MessageUtil.errorMessage(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> queryPersonByCertIdAndMonth(String cardId, String month) {
		return monthlySalaryPersonMapper.queryByCertIdAndMonth(cardId, month);
	}

	@Override
	public Map<String, Object> sendSalarySlip(EhrMonthlySalary monthlySalary, String personIds) throws Exception{
		EHrMailConfig mailConfig = mailConfigMapper.findByCompanyId(monthlySalary.getCompanyId());
		if(mailConfig == null){
			return MessageUtil.errorMessage("请先配置企业邮箱");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("monthlySalaryId", monthlySalary.getMonthlySalaryId());
		params.put("sendStatus", 0);
		if(!StringUtils.isEmpty(personIds)){
			params.put("personIds", personIds);
		}
		List<Map<String, Object>> mspList = monthlySalaryPersonMapper.queryPersonsForSalarySlip(params);
		if(mspList == null || mspList.isEmpty()){
			return MessageUtil.errorMessage("没有需要发送工资条的员工");
		}
		
		Map<String, MailVO> mailVOMap = new HashMap<String, MailVO>();
		String subject = ConfigConstants.SUBJECT_SALARY_SLIP;
		String content = FileUtil.readHtmlTemplate(ConfigConstants.TPL_SALARY_SLIP);
		StringBuffer headerSb = new StringBuffer();
		StringBuffer dataSb = new StringBuffer();
		for(Map<String, Object> msp : mspList){
			headerSb.delete(0, headerSb.length());
			dataSb.delete(0, dataSb.length());
			
			//组织公共列
			EhrArgumentInfo ai = argumentInfoMapper.selectByKey(monthlySalary.getCompanyId(), "STATIC_FIELDS_IN_SALARY_SLIP");
			headerSb.append("<td>员工工号</td>");
			dataSb.append("<td>").append(msp.get("job_number")).append("</td>");
			headerSb.append("<td>员工姓名</td>");
			dataSb.append("<td>").append(msp.get("employee_name")).append("</td>");
			if(ai == null || "1".equals(ai.getArgumentValue())){
				headerSb.append("<td>应税工资</td>");
				dataSb.append("<td>").append(msp.get("taxable_income")).append("</td>");
				headerSb.append("<td>代扣税</td>");
				dataSb.append("<td>").append(msp.get("withholding_tax")).append("</td>");
				headerSb.append("<td>税后工资</td>");
				dataSb.append("<td>").append(msp.get("after_tax_income")).append("</td>");
				headerSb.append("<td>实发工资</td>");
				dataSb.append("<td>").append(msp.get("take_home_income")).append("</td>");
			}
			
			Long monthlySalaryPersonId = Long.valueOf(msp.get("monthly_salary_person_id").toString());
			List<EhrMonthlySalaryDetail> detailList = monthlySalaryDetailMapper.selectForSalarySlip(monthlySalaryPersonId);
			for(EhrMonthlySalaryDetail detail : detailList){
				//组织模板列
				headerSb.append("<td>").append(detail.getSalaryItemName()).append("</td>");
				dataSb.append("<td>").append(detail.getAmount()).append("</td>");
			}
			
			//创建MailVO
			Map<String,Object> formatParams = new HashMap<String,Object>();
			formatParams.put("employeeName", msp.get("employee_name"));
			formatParams.put("month", monthlySalary.getMonthly());
			formatParams.put("header", headerSb.toString());
			formatParams.put("data", dataSb.toString());
			
			MailVO mailVo = new MailVO();
			mailVo.setSubject(JabavaStringUtils.formatString(subject, formatParams));
			//mailVo.setFrom(JabavaPropertyCofigurer.getProperty("JABAVA_MAIL"));
			mailVo.setFrom(mailConfig.getSendTo());
			if(!StringUtils.isEmpty((String)msp.get("email_e"))){
				mailVo.setTo(msp.get("email_e").toString());
			}else if(!StringUtils.isEmpty((String)msp.get("email"))){
				mailVo.setTo(msp.get("email").toString());
			}else{
				throw new JabavaServiceException("员工邮箱为空：" + msp.get("job_number"));
			}
			mailVo.setContent(JabavaStringUtils.formatString(content, formatParams));
			
			mailVOMap.put(monthlySalaryPersonId.toString(), mailVo);
		}
		
		//发送并统计结果
		Map<String, Integer> sendResult = Tools.mailSend(mailVOMap, mailConfig);
		int successNum = 0;
		int failNum = 0;
		for(String key : sendResult.keySet()){
			Integer r = sendResult.get(key);
			if(r == null || r == 0){
				failNum ++;
			}else{
				//更新发送结果
				monthlySalaryPersonMapper.updateSendStatus(Long.valueOf(key), r);
				successNum ++;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("发送成功：").append(successNum).append("， 发送失败：").append(failNum);
		if(failNum > 0){
			sb.append("\n").append("请检查未发送员工邮箱后重新发送");
		}
		return MessageUtil.successMessage(sb.toString());
	}
}
