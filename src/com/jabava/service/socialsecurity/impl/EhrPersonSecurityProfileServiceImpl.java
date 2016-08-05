package com.jabava.service.socialsecurity.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.accumulationfund.AfPaymentBillPersonDetailMapper;
import com.jabava.dao.accumulationfund.AfPaymentBillPersonMapper;
import com.jabava.dao.employee.EhrPositionMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.policygroup.SbGroupDetailMapper;
import com.jabava.dao.socialsecurity.EhrPersonSecurityProfileMapper;
import com.jabava.dao.socialsecurity.EhrSecurityItemMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillPersonDetailMapper;
import com.jabava.dao.socialsecurity.SsPaymentBillPersonMapper;
import com.jabava.dao.socialsecurity.SsSocialSecurityAccountMapper;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonVO;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.pojo.socialsecurity.EhrSecurityItem;
import com.jabava.service.socialsecurity.EhrPersonSecurityProfileService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.employee.IPersonImport;
import com.jabava.utils.employee.impl.PersonSecurityProfile;
import com.jabava.utils.enums.JabavaEnum.SocialSecurityOrAccumulationFundStatus;

@Service
public class EhrPersonSecurityProfileServiceImpl implements EhrPersonSecurityProfileService {
	public static Logger log = Logger.getLogger(EhrPersonSecurityProfileServiceImpl.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	// 产品类型
	private static final Long  SECURITY_CATEGORY_SECURITY  = 1L; // 社保
	private static final Long  SECURITY_CATEGORY_GONGJIJIN = 2L; // 公积金
	@Resource
	private EhrPersonSecurityProfileMapper personSercurityProfileMapper;
	@Autowired
	private SsPaymentBillPersonMapper ssPaymentBillPersonMapper;
	@Autowired
	private AfPaymentBillPersonMapper afPaymentBillPersonMapper;
	@Autowired
	private SsPaymentBillPersonDetailMapper ssPaymentBillPersonDetailMapper;
	@Autowired
	private AfPaymentBillPersonDetailMapper afPaymentBillPersonDetailMapper;
	@Resource
	public EhrPositionMapper positionMapper;
	
	@Resource
	private EhrSecurityItemMapper ehrSecurityItemMapper;
	
	@Resource
	public EhrPersonMapper personMapper;
	
	@Resource
	public SbGroupDetailMapper sbGroupDetailMapper;
	
	@Autowired
	private SsSocialSecurityAccountMapper socialSecurityAccountMapper;	
	
	public List<Map<String,Object>> searchSecurityProfilePage(Map <String,Object> map){
		return personSercurityProfileMapper.getSecurityDetailInfoPage(map);
	}
	
	/**
	 * 依据员工代码查询员工社保档案
	 * @param personId
	 * @return
	 */
	public EhrPersonSecurityProfile getSecurityProfileById(Long personId){
		EhrPersonSecurityProfile profile;
		
		profile = personSercurityProfileMapper.getProfileIdByPerson(personId);	
		EhrPersonVO personVO  = positionMapper.selectPositionByPersonId(personId);	
		if(profile == null){
			profile = new EhrPersonSecurityProfile();
			profile.setPersonid(personId);
			profile.setSecuritySwitch(1);
			profile.setGongjijinSwitch(1);
			profile.setInputHelpSwitch(1);
			
		}
		if(personVO!=null){
			if(personVO.getOrganization()!=null){
				profile.setDepartment(personVO.getOrganization().getOrganizationName());
			}
			
			//工号
			if(StringUtils.isNotBlank(personVO.getJobNumber())){	    		
				profile.setEmployeeNumber(personVO.getJobNumber());
			}
		}
		return profile;
	}
	
	public EhrPersonSecurityProfile saveOrUpdate(EhrPersonSecurityProfile record){
		int ret = 0;
		if(exists(record.getPersonid())){
			personSercurityProfileMapper.updateByPrimaryKey(record);
		}else{
			record.setProfileCode(UUID.randomUUID().toString());
			ret =  personSercurityProfileMapper.insertSelective(record);
		}
		
		EhrPersonSecurityProfile model = personSercurityProfileMapper.getProfileIdByPerson(record.getPersonid());
		
		// 设置状态(如果不缴，则需要关闭相应的选项)
		this.setStatus(model);
		
		personSercurityProfileMapper.updateByPrimaryKey(model);
		
		return model;
	}
	
	public boolean exists(Long personId){
		EhrPersonSecurityProfile model = personSercurityProfileMapper.getProfileIdByPerson(personId);
		return (model != null);
	}
	
	public int updateByPrimaryKey(EhrPersonSecurityProfile record){
		return personSercurityProfileMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 依据当前日期将符合条件的社保档案转变更为在缴状态
	 * @return
	 */
	public int updateRecordStatus(){
		// 符合条件的记录转换为 在缴
		personSercurityProfileMapper.activateGongjijin();
		personSercurityProfileMapper.activateSecurity();
		
		// 符合条件的记录转换为 停缴
		personSercurityProfileMapper.inactivateGongjijin();
		personSercurityProfileMapper.inactivateSecurity();
		
		return 0;
	}

	@Override
	public List<Map<String, Object>> getAllProductsByPersonId(Long personId) {
		return personSercurityProfileMapper.getAllProductsByPersonId(personId);
	}

	@Override
	public List<EhrPersonSecurityProfile> getSecurityProfileByOrgAccount(
			Long securityOrgAccount, Long gongjijinOrgAccount) {
		EhrPersonSecurityProfile personSecurityProfile =new EhrPersonSecurityProfile();
		if(securityOrgAccount!=null){
			personSecurityProfile.setSecurityOrgAccount(securityOrgAccount);
			personSecurityProfile.setSecurityStatus(SocialSecurityOrAccumulationFundStatus.IN_PAY.getValue());
			return personSercurityProfileMapper.getSecurityProfileByOrgAccount(personSecurityProfile);	
		}else if(gongjijinOrgAccount!=null){
			personSecurityProfile.setGongjijinOrgAccount(gongjijinOrgAccount);
			personSecurityProfile.setGongjijinStatus(SocialSecurityOrAccumulationFundStatus.IN_PAY.getValue());
			return personSercurityProfileMapper.getSecurityProfileByOrgAccount(personSecurityProfile);			
		}else{
			return null;
		}
	}
	
	/**
	 * 查询社保档案总数
	 * @return
	 */
	public Long selectProfileTotalCount(Long companyId){
		return personSercurityProfileMapper.selectProfileTotalCount(companyId);
	}
	
	/**
	 * 员工社保档案导入
	 */
	@Override
	public String personSecurityProfileImport(CommonsMultipartFile multipartFile,EhrUser user,Long securityOrgAccount,Long securityType,Long gongjijinOrgAccount,int gongjijinType) throws Exception {
		//errorMsg.clear();
		
		System.out.println(multipartFile.getSize());
		StringBuffer sb = new StringBuffer();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String createTime = sdf.format(new Date());
		if(multipartFile.getSize()==0){
			return "上传文件为空！";
		}
		
		Workbook book = null;
		InputStream inputstream = multipartFile.getInputStream();
		if (".xlsx".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new XSSFWorkbook(inputstream);
		} else if (".xls".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new HSSFWorkbook(inputstream);
		} else {
			throw new JabavaServiceException("员工社保档案导入失败，错误：文件格式错误。");
		}
		
		log.info("导入员工社保公积金档案start..................");
		
		//根据companyId查询员工社保档案表里的所有personId
		Map<Object, Object> existingPerson = new HashMap<Object, Object>();
		List<Map<String,Object>> existingList=getAllPersonIdByCompanyId(user.getCompanyId());
		
		if(existingList!=null&&existingList.size()>0){
			//将本公司下的所有存在的员工社保档案信息放入map中
			for(Map<String,Object> pmap:existingList){
				if(pmap.get("personId")!=null&&!StringUtils.isEmpty(pmap.get("personId").toString())){
//					existingPerson.put(Long.parseLong(pmap.get("personId").toString()),Long.parseLong(pmap.get("personId").toString()));
					existingPerson.put(Long.parseLong(pmap.get("personId").toString()),pmap);
				}
				
				if(pmap.get("jobNumber")!=null&&!StringUtils.isEmpty(pmap.get("jobNumber").toString())){
					existingPerson.put(pmap.get("jobNumber").toString(),Long.parseLong(pmap.get("profileId").toString()));
				}
				
			}
		}else{
			log.info("该公司"+user.getCompanyName()+"："+user.getCompanyName()+" 下，没有员工社保档案记录！");
		}
		
		//获取社保规则下的规则明细
		List<Map<String,Object>> currentSecurityConfig = socialSecurityAccountMapper.getSecurityGroupDetail(securityType, SECURITY_CATEGORY_SECURITY);
		
		//获取社保规则下的规则明细
		List<Map<String,Object>> currentGongjijinProducts = socialSecurityAccountMapper.getSecurityGroupDetail(Long.parseLong(new Integer(gongjijinType).toString()), SECURITY_CATEGORY_GONGJIJIN);
				
		IPersonImport personImporter = null;
		
		//读取EXCLE里的数据
		Map<String, Object> dataChunk = new HashMap<String, Object>();
		
		for (int num = 0; num < book.getNumberOfSheets(); num++) {
			Sheet sheet = book.getSheetAt(num);			 
			personImporter = new PersonSecurityProfile();
			dataChunk=personImporter.importPersonSecurityProfile(num, sheet, null, user, currentSecurityConfig, currentGongjijinProducts);
		}
		
		//excle表格里的员工工号集合 
		List<String> jobNumberList=(ArrayList<String>) dataChunk.get("jobNumberList");
		
		//根据companyId和工号查询员工信息集合
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("companyId", user.getCompanyId());
		params.put("jobNumberList", jobNumberList);
		List<EhrPerson> personList=personMapper.selectByCompanyIdJobNumberList(params);
		
		//判断Excle里的员工是否存在
		if(personList!=null&&personList.size()>0){
			 
			dataChunk.remove("jobNumberList");
			
			//批量插入list
			List<EhrPersonSecurityProfile> insertBachList=new ArrayList<EhrPersonSecurityProfile>();
			
			//批量更新list
			List<EhrPersonSecurityProfile> updateBachList=new ArrayList<EhrPersonSecurityProfile>();
			
			//批量新增personId集合
			List<Long> personIdForInsertBachList=new ArrayList<Long>();
			
			//批量更新profileId集合
			List<Long> profileIdForUpdateBachList=new ArrayList<Long>();
			
			//批量更新personId集合
			List<Long> personIdForUpdateBachList=new ArrayList<Long>();
			
			//根据用一堆工号查询出来的人员，根据从Excle中取得的工号取得personId加到到员工档案实体中
			for(int i=0;i<personList.size();i++){
				EhrPerson person=personList.get(i);//员工
				
				String jobNum=person.getJobNumber();//工号
				
				EhrPersonSecurityProfile securityProfile=(EhrPersonSecurityProfile) dataChunk.get(jobNum);//社保公积金档案
				securityProfile.setPersonid(person.getPersonId());//设置personId
				securityProfile.setSecurityOrgAccount(securityOrgAccount);
				securityProfile.setSecurityType(securityType);
				securityProfile.setGongjijinOrgAccount(gongjijinOrgAccount);
				securityProfile.setGongjijinType(gongjijinType);
			
				dataChunk.remove(jobNum);//取完后删除多余的项
				
				//Long existingPersonId=existingPerson.get(securityProfile.getPersonid());//判断此人在员工档案表里是否存在 Long.parseLong(pmap.get("personId").toString())
				
				//判断此人在员工档案表里是否存在
				Map<String,Object> existingPersonMap=(Map<String, Object>) existingPerson.get(securityProfile.getPersonid());
				//Long existingPersonId=Long.parseLong(existingPersonMap.get("personId").toString());
				
				//插入员工社保公积金档案信息（根据personId判断，有更新，没有插入）
				if(existingPersonMap!=null){
					//存在，收集主键profile_id
					profileIdForUpdateBachList.add(Long.parseLong(existingPerson.get(securityProfile.getJobNumber()).toString()));//根据工号取得已存在的员工档案profileId
					securityProfile.setProfileId(Long.parseLong(existingPerson.get(securityProfile.getJobNumber()).toString()));
					
					/*System.out.println(existingPersonMap.get("profileId").toString());
					profileIdForUpdateBachList.add(Long.parseLong(existingPersonMap.get("profileId").toString()));//根据工号取得已存在的员工档案profileId
					securityProfile.setProfileId(Long.parseLong(existingPersonMap.get("profileId").toString()));
					*/
					
					//根据状态做出不同的判断
					
					//取得已存在的员工社保档案记录的状态 从existingPersonMap取状态 existingPersonMap.get("securityStatus").toString())
					if(existingPersonMap.get("securityStatus")!=null&&!StringUtils.isEmpty(existingPersonMap.get("securityStatus").toString())){
						int existingSecurityStatus= Integer.parseInt(existingPersonMap.get("securityStatus").toString());
						System.out.println("existingSecurityStatus:"+existingSecurityStatus);
						securityProfile.setSecurityStatus(existingSecurityStatus);
					}
					
					if(existingPersonMap.get("securitySwitch")!=null&&!StringUtils.isEmpty(existingPersonMap.get("securitySwitch").toString())){
						securityProfile.setSecuritySwitch(Integer.parseInt(existingPersonMap.get("securitySwitch").toString()));
					}
					
					if(existingPersonMap.get("gongjijinStatus")!=null&&!StringUtils.isEmpty(existingPersonMap.get("gongjijinStatus").toString())){
						int existingGJJStatus=Integer.parseInt(existingPersonMap.get("gongjijinStatus").toString());
						System.out.println("existingGJJStatus:"+existingGJJStatus);
						securityProfile.setGongjijinStatus(existingGJJStatus);
					}
					
					if(existingPersonMap.get("gongjijinSwitch")!=null&&!StringUtils.isEmpty(existingPersonMap.get("gongjijinSwitch").toString())){
						securityProfile.setGongjijinSwitch(Integer.parseInt(existingPersonMap.get("gongjijinSwitch").toString()));
						
					}
					
					personIdForUpdateBachList.add(securityProfile.getPersonid());
					updateBachList.add(securityProfile);
					
				}else{
					
					//不存在，收集personId,用于批量新增后查询主键profile_id
					personIdForInsertBachList.add(securityProfile.getPersonid());
					
					//设置switch(目前默认都为在缴)
					securityProfile.setSecuritySwitch(0);
					securityProfile.setGongjijinSwitch(0);

					//设置状态(如果不缴，则需要关闭相应的选项)
					this.setStatus(securityProfile);
					
					insertBachList.add(securityProfile);
				}
			}
			
			//新增入库
			int  insertTotal=insertBachExtract(  insertBachList,  personIdForInsertBachList,  createTime);
			//sb.append(insertInfo);
			
			//更新入库
			String updateInfo=updateBachExtract( updateBachList,  profileIdForUpdateBachList, personIdForUpdateBachList,existingPerson, createTime,insertTotal);
			sb.append(updateInfo);
			
			return sb.toString();
		}else{
			log.info("匹配不到Excle中的员工！");
			return "匹配不到Excle中的员工！";
		}
	}

	@Override
	public List<Map<String, Object>> loadPaymentBillPersonHeader(Map<String,Object> params, boolean withCount) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		if(withCount){
			result.add(this.getFieldHeader("month", "月份"));
			result.add(this.getFieldHeader("security_account", "社保账号"));
			result.add(this.getFieldHeader("accumulation_fund_account", "公积金账号"));
			result.add(this.getFieldHeader("hj_amount", "汇缴总额"));
			result.add(this.getFieldHeader("hj_amount_e", "企业汇缴总额"));
			result.add(this.getFieldHeader("hj_amount_p", "个人汇缴总额"));
			result.add(this.getFieldHeader("bj_amount", "补缴总额"));
			result.add(this.getFieldHeader("bj_amount_e", "企业补缴总额"));
			result.add(this.getFieldHeader("bj_amount_p", "个人补缴总额"));
		}
		
		//获取distinct员工明细
		List<Map<String,Object>> groupDetailList = new ArrayList<Map<String,Object>>();
		groupDetailList.addAll(ssPaymentBillPersonDetailMapper.listGroupDetailByPerson(params));
		groupDetailList.addAll(afPaymentBillPersonDetailMapper.listGroupDetailByPerson(params));
		for(Map<String,Object> gd : groupDetailList){
			//如果出现重复，需要将sb_group_detail_id从distinct中去掉
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
	public List<Map<String, Object>> listPaymentBillPerson(Map<String,Object> params) {
		Map<String,Map<String,Object>> result = new HashMap<String,Map<String,Object>>();
		
		List<Map<String,Object>> personList = ssPaymentBillPersonMapper.selectBillPersonByPerson(params);
		for(Map<String,Object> person : personList){
			Map<String,Object> record = new HashMap<String,Object>();
			String month = person.get("month").toString();
			record.put("month", person.get("month"));
			record.put("security_account", person.get("security_account"));
			record.put("hj_amount", person.get("hj_amount"));
			record.put("hj_amount_e", person.get("hj_amount_e"));
			record.put("hj_amount_p", person.get("hj_amount_p"));
			record.put("bj_amount", person.get("bj_amount"));
			record.put("bj_amount_e", person.get("bj_amount_e"));
			record.put("bj_amount_p", person.get("bj_amount_p"));
			
			Long ssPaymentBillPersonId = Long.valueOf(person.get("id").toString());
			List<Map<String,Object>> personDetailList = ssPaymentBillPersonDetailMapper.selectByBillPersonId(ssPaymentBillPersonId);
			this.addDetailToPerson(record, personDetailList);
			
			result.put(month, record);
		}
		
		personList = afPaymentBillPersonMapper.selectBillPersonByPerson(params);
		for(Map<String,Object> person : personList){
			Map<String,Object> record = null;
			String month = person.get("month").toString();
			if(result.containsKey(month)){
				record = result.get(month);

				record.put("accumulation_fund_account", person.get("accumulation_fund_account"));
				record.put("hj_amount", ((BigDecimal)record.get("hj_amount")).add(new BigDecimal(person.get("hj_amount").toString())));
				record.put("hj_amount_e", ((BigDecimal)record.get("hj_amount_e")).add(new BigDecimal(person.get("hj_amount_e").toString())));
				record.put("hj_amount_p", ((BigDecimal)record.get("hj_amount_p")).add(new BigDecimal(person.get("hj_amount_p").toString())));
				record.put("bj_amount", ((BigDecimal)record.get("bj_amount")).add(new BigDecimal(person.get("bj_amount").toString())));
				record.put("bj_amount_e", ((BigDecimal)record.get("bj_amount_e")).add(new BigDecimal(person.get("bj_amount_e").toString())));
				record.put("bj_amount_p", ((BigDecimal)record.get("bj_amount_p")).add(new BigDecimal(person.get("bj_amount_p").toString())));
			}else{
				record = new HashMap<String,Object>();
				result.put(month, record);
				
				record.put("month", person.get("month"));
				record.put("accumulation_fund_account", person.get("accumulation_fund_account"));
				record.put("hj_amount", person.get("hj_amount"));
				record.put("hj_amount_e", person.get("hj_amount_e"));
				record.put("hj_amount_p", person.get("hj_amount_p"));
				record.put("bj_amount", person.get("bj_amount"));
				record.put("bj_amount_e", person.get("bj_amount_e"));
				record.put("bj_amount_p", person.get("bj_amount_p"));
			}

			Long afPaymentBillPersonId = Long.valueOf(person.get("af_payment_bill_person_id").toString());
			List<Map<String,Object>> personDetailList = afPaymentBillPersonDetailMapper.selectByBillPersonId(afPaymentBillPersonId);
			this.addDetailToPerson(record, personDetailList);
		}
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(result.values());
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) { 
				return o2.get("month").toString().compareTo(o1.get("month").toString());
			}
		});
		return list;
	}
	
	private void addDetailToPerson(Map<String,Object> record, List<Map<String,Object>> personDetailList){
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
	}

	private Map<String,Object> getFieldHeader(String fieldName,String showName){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("fieldName",fieldName);
		result.put("showName",showName);
		return result;
	}
	
	/**
	 * 员工档案新增入库
	 * @param insertBachList 批量插入list
	 * @param personIdForInsertBachList 批量新增personId集合
	 * @param createTime
	 * @return
	 */
	private int insertBachExtract(List<EhrPersonSecurityProfile> insertBachList,List<Long> personIdForInsertBachList,String createTime){
		int flag=0;
		//新增入库
		if(insertBachList!=null&&insertBachList.size()>0){
		 
			log.info("批量新增员工档案数据......"+insertBachList.size());
			
			//1.员工社保档案批量入库并返回插入的id  2 根据参保类型查询政策包详情 根据查询出来的数据放入档案详情表中
			
			//一、批量插入
			 flag=personSercurityProfileMapper.insertBachForSecurityProfile(insertBachList);
			
			if(flag>0){
				//二、根据批量新增personId集合查询新增的profile_id
				List<EhrPersonSecurityProfile> insertProfileIdList=personSercurityProfileMapper.selectprofileIdListByPersonIdList(personIdForInsertBachList);
				
				
				//档案详情批量插入list
				List<EhrSecurityItem> itemInsertBach=new ArrayList<EhrSecurityItem>();
				
				//遍历插入的personId，插入到档案详情表中
				for( EhrPersonSecurityProfile  securityProfile:insertProfileIdList){
					
					Long personId= securityProfile.getPersonid() ;//personId 			  员工ID
					Long securityProfileId=securityProfile.getProfileId();//profileId     档案ID
					Long secutityTypeId=securityProfile.getSecurityType();//securityTypeId 社保参保类型
					int gjjTypeId=securityProfile.getGongjijinType();//securityTypeId      公积金参保类型
					
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("sbGroupId", secutityTypeId);
					map.put("policyGroupType", 1);//社保 
					
					//一、根据社保参保类型查询政策包详情信息，并插入到档案详情表中
					List<Map<String, Object>>  sbGroupDetailForSecurityList= sbGroupDetailMapper.selectBySbGroupIdList(map);
					
					 for(Map<String, Object> sbGroupDetailMap:sbGroupDetailForSecurityList){
						 EhrSecurityItem ehrSecurityItem=new EhrSecurityItem();
						  
						 ehrSecurityItem.setPersonId(personId);//personId
						 ehrSecurityItem.setSecurityProfileId(securityProfileId);//员工社保公积金档案ID
						 ehrSecurityItem.setSecurityTypeId(secutityTypeId);//社保类型-社保参保类型
						 
						 //参保类型类别-就是政策包组合类型：社保,公积金
						 int securityTypeCategory=Integer.parseInt(sbGroupDetailMap.get("policyGroupType").toString());//政策包组合类型：1社保;2公积金
						 ehrSecurityItem.setSecurityTypeCategory(securityTypeCategory);
						 
						 //社保项目代码-就是pro_id
						 Long securityItemCode=Long.parseLong(sbGroupDetailMap.get("prodId").toString());
						 ehrSecurityItem.setSecurityItemCode(securityItemCode);
						 
						 //设置社保基数
						 ehrSecurityItem.setSecurityItemBase(securityProfile.getSecurityBase());
						 ehrSecurityItem.setSecurityOrgBase(securityProfile.getSecurityOrgBase());
						 ehrSecurityItem.setCreateTime(createTime);
						 ehrSecurityItem.setIsDeleted(0);
						 
						 itemInsertBach.add(ehrSecurityItem);
					 }
					 
					 //二、根据公积金参保类型查询政策包详情信息，并插入到档案详情表中
					 map.put("sbGroupId", gjjTypeId);
					 map.put("policyGroupType", 2);//公积金
					 
					 List<Map<String, Object>>  sbGroupDetailForGjjList= sbGroupDetailMapper.selectBySbGroupIdList(map);
						
					 for(Map<String, Object> sbGroupDetailMap:sbGroupDetailForGjjList){
						 EhrSecurityItem ehrSecurityItem=new EhrSecurityItem();
						  
						 ehrSecurityItem.setPersonId(personId);//personId
						 ehrSecurityItem.setSecurityProfileId(securityProfileId);//员工社保公积金档案ID
						 ehrSecurityItem.setSecurityTypeId(Long.parseLong((new Integer(gjjTypeId)).toString() ));//社保类型-存公积金参保类型
						 
						 //参保类型类别-就是政策包组合类型：社保,公积金
						 int securityTypeCategory=Integer.parseInt(sbGroupDetailMap.get("policyGroupType").toString());//政策包组合类型：1社保;2公积金
						 ehrSecurityItem.setSecurityTypeCategory(securityTypeCategory);
						 
						 //社保项目代码-就是pro_id
						 Long securityItemCode=Long.parseLong(sbGroupDetailMap.get("prodId").toString());
						 ehrSecurityItem.setSecurityItemCode(securityItemCode);
						 
						 //设置公积金基数
						 ehrSecurityItem.setSecurityItemBase(securityProfile.getGongjijinBase());
						 ehrSecurityItem.setSecurityOrgBase(securityProfile.getGongjijinOrgBase());
						 ehrSecurityItem.setCreateTime(createTime);
						 ehrSecurityItem.setIsDeleted(0);
						 
						 itemInsertBach.add(ehrSecurityItem);
					 }
					 
					//员工社保档案详情批量插入
					int flag2=ehrSecurityItemMapper.addBachSecurityItem(itemInsertBach);
					itemInsertBach.clear();
					if(!(flag2>0)){
						log.info("员工社保公积金档案详情添加失败！");
					}
				}
			}else{
				log.info("员工社保公积金档案添加失败！");
				//info.append("员工社保公积金档案添加失败！");
			}
		}
		
		return flag;
	}
	
	/**
	 * 修改员工社保公积金档案信息
     * @param updateBachList 批量更新list
	 * @param profileIdForUpdateBachList 批量更新profileId集合
	 * @param personIdForUpdateBachList  批量更新personId集合
	 * @param existingPerson  已存在的员工档案信息集合
	 * @param createTime
	 * @param insertTotal 新增的条数
	 * @return
	 */
	private String updateBachExtract(List<EhrPersonSecurityProfile> updateBachList,List<Long> profileIdForUpdateBachList,List<Long> personIdForUpdateBachList,Map<Object, Object> existingPerson,String createTime,int insertTotal){
		StringBuffer info=new StringBuffer();
		//更新入库
		if(updateBachList!=null&&updateBachList.size()>0){
			log.info("批量修改员工档案数据......"+updateBachList.size());
			
			//根据社保、公积金状态处理数据导入情况
			int successAll=0;
			int errorAll=0;
			int size=updateBachList.size();
			
			for(int i=0;i<=updateBachList.size()-1;i++){
				EhrPersonSecurityProfile ehrPersonSecurityProfile=updateBachList.get(i);
				if(ehrPersonSecurityProfile.getSecurityStatus()!=null&&ehrPersonSecurityProfile.getGongjijinStatus()!=null){
					int securityStatus=ehrPersonSecurityProfile.getSecurityStatus();	//社保状态
					int gjjStatus=ehrPersonSecurityProfile.getGongjijinStatus();		//公积金状态
					if(securityStatus==0&&gjjStatus==0){
						successAll +=1;
					}else if(securityStatus!=0&&gjjStatus!=0){
						errorAll  +=1;
						//移除社保公积金对象,不做导入操作
						updateBachList.remove(i);
						i--;
					}else if(securityStatus==0&&gjjStatus!=0){
						errorAll  +=1;
						//公积金属性参数还原
						this.setGongjijinStatus(ehrPersonSecurityProfile,existingPerson);
						updateBachList.set(i, ehrPersonSecurityProfile);
					}else if(securityStatus!=0&&gjjStatus==0){
						errorAll  +=1;
						//社保属性参数还原
						this.setSecurityStatus(ehrPersonSecurityProfile,existingPerson);
						updateBachList.set(i, ehrPersonSecurityProfile);
					}
				}else if(ehrPersonSecurityProfile.getSecurityStatus()==null&&ehrPersonSecurityProfile.getGongjijinStatus()!=null){
					int gjjStatus=ehrPersonSecurityProfile.getGongjijinStatus();		//公积金状态
					
					if(gjjStatus==0){
						successAll  +=1;
					}else{
						errorAll  +=1;
						//公积金属性参数还原
						this.setGongjijinStatus(ehrPersonSecurityProfile,existingPerson);
						updateBachList.set(i, ehrPersonSecurityProfile);
					}
				}else if(ehrPersonSecurityProfile.getSecurityStatus()!=null&&ehrPersonSecurityProfile.getGongjijinStatus()==null){
					int securityStatus=ehrPersonSecurityProfile.getSecurityStatus();	//社保状态
					
					if(securityStatus==0){
						successAll  +=1;
					}else{
						errorAll  +=1;
						//社保属性参数还原
						this.setSecurityStatus(ehrPersonSecurityProfile,existingPerson);
						updateBachList.set(i, ehrPersonSecurityProfile);
					}
				}else{
					successAll  +=1;
				}
			}
			
			if(successAll==size){
				info.append("导入成功！");
			}else if(errorAll==size){
				info.append("导入失败，只有草稿状态数据才能导入！");
			}else{
				info.append("导入数据"+(errorAll+insertTotal)+"条，只有草稿状态数据才能导入！");
			} 
			
			//一、根据profile_id集合批量修改员工社保档案		
			int flag=personSercurityProfileMapper.updateBachForSecurityProfile(updateBachList);
			
			if(flag>0){
				
				//二、根据profileId批量删除档案详情信息  
				int deleteFlag=ehrSecurityItemMapper.deleteBachSecurityItem(profileIdForUpdateBachList);
				
				if(deleteFlag>0){
					
					//三、根据批量更新personId集合查询员工档案信息
					List<EhrPersonSecurityProfile> updateProfileIdList=personSercurityProfileMapper.selectprofileIdListByPersonIdList(personIdForUpdateBachList);
					
					//档案详情批量插入list
					List<EhrSecurityItem> itemInsertBach=new ArrayList<EhrSecurityItem>();
					
					for(EhrPersonSecurityProfile securityProfile:updateProfileIdList){
						
						Long personId= securityProfile.getPersonid() ;//personId 			  员工ID
						Long securityProfileId=securityProfile.getProfileId();//profileId     档案ID
						Long secutityTypeId=securityProfile.getSecurityType();//securityTypeId 社保参保类型
						int gjjTypeId=securityProfile.getGongjijinType();//securityTypeId      公积金参保类型
						
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("sbGroupId", secutityTypeId);
						map.put("policyGroupType", 1);
						
						
						//根据参保类型查询政策包详情信息，并插入到档案详情表中
						//List<SbGroupDetail> selectBySbGroupIdList
						List<Map<String, Object>>  sbGroupDetailForSecurityList= sbGroupDetailMapper.selectBySbGroupIdList(map);
						
						 for(Map<String, Object> sbGroupDetailMap:sbGroupDetailForSecurityList){
							 EhrSecurityItem ehrSecurityItem=new EhrSecurityItem();
							  
							 ehrSecurityItem.setPersonId(personId);//personId
							 ehrSecurityItem.setSecurityProfileId(securityProfileId);//员工社保公积金档案ID
							 ehrSecurityItem.setSecurityTypeId(secutityTypeId);//社保类型
							 
							 //参保类型类别-就是政策包组合类型：社保;公积金
							 int securityTypeCategory=Integer.parseInt(sbGroupDetailMap.get("policyGroupType").toString());//政策包组合类型：1社保;2公积金
							 ehrSecurityItem.setSecurityTypeCategory(securityTypeCategory);
							 
							 //社保项目代码-就是pro_id
							 Long securityItemCode=Long.parseLong(sbGroupDetailMap.get("prodId").toString());
							 ehrSecurityItem.setSecurityItemCode(securityItemCode);
							 
							 //根据社保或公积金的区别 设置基数
							 //设置社保基数
							 ehrSecurityItem.setSecurityItemBase(securityProfile.getSecurityBase());
							 ehrSecurityItem.setSecurityOrgBase(securityProfile.getSecurityOrgBase());
							 ehrSecurityItem.setCreateTime(createTime);
							 ehrSecurityItem.setIsDeleted(0);
							 
							 itemInsertBach.add(ehrSecurityItem);
						 }
						 
						 //二、根据公积金参保类型查询政策包详情信息，并插入到档案详情表中
						 map.put("sbGroupId", gjjTypeId);
						 map.put("policyGroupType", 2);
						 List<Map<String, Object>>  sbGroupDetailForGjjList= sbGroupDetailMapper.selectBySbGroupIdList(map);
						 
						 for(Map<String, Object> sbGroupDetailMap:sbGroupDetailForGjjList){
							 EhrSecurityItem ehrSecurityItem=new EhrSecurityItem();
							  
							 ehrSecurityItem.setPersonId(personId);//personId
							 ehrSecurityItem.setSecurityProfileId(securityProfileId);//员工社保公积金档案ID
							 ehrSecurityItem.setSecurityTypeId(Long.parseLong((new Integer(gjjTypeId)).toString() ));//社保类型-存公积金参保类型
							 
							 //参保类型类别-就是政策包组合类型：社保;公积金
							 int securityTypeCategory=Integer.parseInt(sbGroupDetailMap.get("policyGroupType").toString());//政策包组合类型：1社保;2公积金
							 ehrSecurityItem.setSecurityTypeCategory(securityTypeCategory);
							 
							 //社保项目代码-就是pro_id
							 Long securityItemCode=Long.parseLong(sbGroupDetailMap.get("prodId").toString());
							 ehrSecurityItem.setSecurityItemCode(securityItemCode);
							 
							 //设置公积金基数
							 ehrSecurityItem.setSecurityItemBase(securityProfile.getGongjijinBase());
							 ehrSecurityItem.setSecurityOrgBase(securityProfile.getGongjijinOrgBase());
							 ehrSecurityItem.setCreateTime(createTime);
							 ehrSecurityItem.setIsDeleted(0);
							 
							 itemInsertBach.add(ehrSecurityItem);
						 }
						 
					 	//员工社保档案详情批量插入
						int flag2=ehrSecurityItemMapper.addBachSecurityItem(itemInsertBach);
						itemInsertBach.clear();
						if(!(flag2>0)){
							log.info("员工社保公积金档案详情添加失败！");
						}
					}
				}else{
					log.info("批量删除员工档案详情出错");
				}
			}else{
				//info.append("员工社保公积金档案修改失败！");
				log.info("员工社保公积金档案修改失败！");
			}
		}
		
		return info.toString();
	}
	
	private void setSecurityStatus(EhrPersonSecurityProfile model,Map<Object, Object> existingPerson){
		Map<String,Object> existingPersonMap=(Map<String, Object>) existingPerson.get(model.getPersonid());
		
		if(existingPersonMap.get("security_account")!=null){
			model.setSecurityAccount(existingPersonMap.get("security_account").toString());
		}
		if(existingPersonMap.get("security_create_type")!=null){
			model.setSecurityCreateType(Integer.parseInt(existingPersonMap.get("security_create_type").toString()));
		}
		if(existingPersonMap.get("security_starttime")!=null){
			model.setSecurityStarttime(existingPersonMap.get("security_starttime").toString());
		}
		if(existingPersonMap.get("security_endtime")!=null){
			model.setSecurityEndtime(existingPersonMap.get("security_endtime").toString());
		}
		if(existingPersonMap.get("security_base")!=null){
			model.setSecurityBase(new BigDecimal(existingPersonMap.get("security_base").toString()));
		}
		if(existingPersonMap.get("security_org_base")!=null){
			model.setSecurityOrgBase(new BigDecimal(existingPersonMap.get("security_org_base").toString()));
		}
	}
	
	private void setGongjijinStatus(EhrPersonSecurityProfile model,Map<Object, Object> existingPerson){
		Map<String,Object> existingPersonMap=(Map<String, Object>) existingPerson.get(model.getPersonid());
		if(existingPersonMap.get("gongjijin_account")!=null){
			model.setGongjijinAccount(existingPersonMap.get("gongjijin_account").toString());
		}
		if(existingPersonMap.get("gongjijin_create_type")!=null){
			model.setGongjijinCreateType(Integer.parseInt(existingPersonMap.get("gongjijin_create_type").toString()));
		}
		if(existingPersonMap.get("gongjijin_starttime")!=null){
			model.setGongjijinStarttime(existingPersonMap.get("gongjijin_starttime").toString());
		}
		if(existingPersonMap.get("gongjijin_endtime")!=null){
			model.setGongjijinEndtime(existingPersonMap.get("gongjijin_endtime").toString());
		}
		if(existingPersonMap.get("gongjijin_base")!=null){
			model.setGongjijinBase(new BigDecimal(existingPersonMap.get("gongjijin_base").toString()));
		}
		if(existingPersonMap.get("gongjijin_org_base")!=null){
			model.setGongjijinOrgBase(new BigDecimal(existingPersonMap.get("gongjijin_org_base").toString()));
		}
	}
	
	private void setStatus(EhrPersonSecurityProfile model){
		// 关闭社保选项
		if(model.getSecuritySwitch() != null && model.getSecuritySwitch() == 1){
			model.setSecurityAccount(null);
			model.setSecurityCity(null);
			model.setSecurityOrgAccount(null);
			model.setSecurityStatus(null);
			model.setSecurityType(null);
		}else{
			//确定 社保档案状态
			this.setStatusForSecurity(model);
		}
		
		// 关闭公积金选项
		if(model.getGongjijinSwitch() != null && model.getGongjijinSwitch() == 1){
			model.setGongjijinAccount(null);
			model.setGongjijinCity(null);
			model.setGongjijinOrgAccount(null);
			model.setGongjijinStatus(null);
			model.setGongjijinType(null);
		}else{
			//确定公积金档案状态
			this.setStatusForGongjijin(model);
		}
	}
	
	private void setStatusForSecurity(EhrPersonSecurityProfile model){
		String endTime = model.getSecurityEndtime();
		String activateTime = model.getSecurityActivatetime();
		String nowTime = sdf.format(new Date());
		
		log.error("security: nowTime=" + nowTime+" activateTime="+activateTime);

		Integer SECURITY_STATUS = 0;
		int result = nowTime.compareTo(activateTime);
		if( result < 0 ){
			SECURITY_STATUS = 0;// 草稿
		}else if(result == 0){
			Long count =  personSercurityProfileMapper.getGeneratedReportCount(model.getPersonid(), nowTime);
			if(count > 0){
				SECURITY_STATUS = 1; // 在缴
			}else{
				SECURITY_STATUS = 0; // 草稿
			}
		}else{
			SECURITY_STATUS = 1;// 在缴
		}
		
		log.error("getting status : " + SECURITY_STATUS);
		if(endTime != null && !"".equals(endTime.trim())){
			result = nowTime.compareTo(endTime);
			if(result < 0){
				//
			}else if(result == 0){
				if(model.getSecurityEndtimeStatus() == 0){
					SECURITY_STATUS = 1; // 在缴
				}else{
					SECURITY_STATUS = 2;// 停缴
				}
			}else{
				SECURITY_STATUS = 2;// 停缴
			}
		}else{
			model.setSecurityEndtime(endTime);
		}
		
		model.setSecurityStatus(SECURITY_STATUS);
	}
	
	private void setStatusForGongjijin(EhrPersonSecurityProfile model){
		String endTime = model.getGongjijinEndtime();
		String activateTime = model.getGongjijinActivatetime();
		String nowTime = sdf.format(new Date());
		
		Integer GONGJIJIN_STATUS = 0;
		int result = nowTime.compareTo(activateTime);
		if( result < 0 ){
			GONGJIJIN_STATUS = 0;// 草稿
		}else if(result == 0){
			Long count =  personSercurityProfileMapper.getGeneratedReportCount(model.getPersonid(), nowTime);
			if(count > 0){
				GONGJIJIN_STATUS = 1; // 在缴
			}else{
				GONGJIJIN_STATUS = 0; // 草稿
			}
		}else{
			GONGJIJIN_STATUS = 1;// 在缴
		}
		
		if(endTime != null && !"".equals(endTime.trim())){
			result = nowTime.compareTo(endTime);
			if(result < 0){
				//
			}else if(result == 0){
				if(model.getSecurityEndtimeStatus() == 0){
					GONGJIJIN_STATUS = 1; // 在缴
				}else{
					GONGJIJIN_STATUS = 2;// 停缴
				}
			}else{
				GONGJIJIN_STATUS = 2;// 停缴
			}
		}else{
			model.setGongjijinEndtime(endTime);
		}

		model.setGongjijinStatus(GONGJIJIN_STATUS);
	}
	
	@Override
	public List<Map<String, Object>> getAllPersonIdByCompanyId(Long companyId) {
		return personSercurityProfileMapper.getAllPersonIdByCompanyId(companyId);
	}

	@Override
	public int insertBachForSecurityProfile(List<EhrPersonSecurityProfile> list) {
		return personSercurityProfileMapper.insertBachForSecurityProfile(list);
	}

	@Override
	public List<EhrPersonSecurityProfile> selectprofileIdListByPersonIdList(
			List<Long> list) {
		return personSercurityProfileMapper.selectprofileIdListByPersonIdList(list);
	}

	@Override
	public int updateBachForSecurityProfile(List<EhrPersonSecurityProfile> list) {
		return personSercurityProfileMapper.updateBachForSecurityProfile(list);
	}

	@Override
	public Map<String,Object> getSecurityProfileInfoByPersonId( Long personId) {
		Map<String, Object> map = new HashMap<>();	
		EhrPersonSecurityProfile profile = personSercurityProfileMapper.getProfileIdByPerson(personId);	
		if(profile!=null){
			map.put("status", 1);
			map.put("data",profile);
		}else{
			map.put("status", 0);
			map.put("data",profile);
		}
		return map;
	}

	@Override
	public Map<String, Object> insertSelective(EhrPersonSecurityProfile record) {
		Map<String, Object> map = new HashMap<>();
		
		//社保状态 和公积金状态统一设置为草稿状态
		record.setSecurityStatus(0);//社保状态 
		record.setGongjijinStatus(0);//公积金状态
		boolean flag=personSercurityProfileMapper.insertSelective(record)>0?true:false;
		if(flag){
			map.put("success", true);
			map.put("profileId", record.getProfileId());
			map.put("msg", "添加社保信息成功！");
			return map;
		}
		
		map.put("success", false);
		map.put("msg", "添加社保信息失败！");
		
		return map;
	}

	@Override
	public Map<String, Object> updateByPrimaryKeySelective( EhrPersonSecurityProfile record) {
		Map<String, Object> map = new HashMap<>();	
		boolean flag=personSercurityProfileMapper.updateByPrimaryKeySelective(record) >0?true:false;
		if(flag){
			map.put("success", true);
			map.put("msg", "修改社保信息成功！");
			return map;
		}
		
		map.put("success", false);
		map.put("msg", "修改社保信息失败！");
		
		return map;
	}
	
	

	 
}
