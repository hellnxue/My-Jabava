package com.jabava.service.employee.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.employee.EhrAssessMapper;
import com.jabava.dao.employee.EhrCertificateMapper;
import com.jabava.dao.employee.EhrContractMapper;
import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.dao.employee.EhrEducationMapper;
import com.jabava.dao.employee.EhrJobpostMapper;
import com.jabava.dao.employee.EhrProjectMapper;
import com.jabava.dao.employee.EhrRelationMapper;
import com.jabava.dao.employee.EhrResumeMapper;
import com.jabava.dao.employee.EhrRewardsMapper;
import com.jabava.dao.employee.EhrTrainMapper;
import com.jabava.dao.employee.EhrTrialMapper;
import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrOrganizationMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.pojo.manage.EhrUserPersonPowerValue;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.service.employee.EhrPersonService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.Page;
import com.jabava.utils.Tools;
import com.jabava.utils.employee.IPersonExport;
import com.jabava.utils.employee.IPersonImport;
import com.jabava.utils.employee.impl.PersonAssess;
import com.jabava.utils.employee.impl.PersonBase;
import com.jabava.utils.employee.impl.PersonCertificate;
import com.jabava.utils.employee.impl.PersonContract;
import com.jabava.utils.employee.impl.PersonEach;
import com.jabava.utils.employee.impl.PersonEducation;
import com.jabava.utils.employee.impl.PersonLeft;
import com.jabava.utils.employee.impl.PersonPost;
import com.jabava.utils.employee.impl.PersonProject;
import com.jabava.utils.employee.impl.PersonRelation;
import com.jabava.utils.employee.impl.PersonResume;
import com.jabava.utils.employee.impl.PersonRewards;
import com.jabava.utils.employee.impl.PersonTrain;
import com.jabava.utils.employee.impl.PersonTransfer;
import com.jabava.utils.employee.impl.PersonTrial;
import com.jabava.utils.excel.ExcelUtil;
import com.jabava.utils.mobile.MobileValidHelp;

@Service("ehrPersonService")
public class EhrPersonServiceImpl implements EhrPersonService {
	private static final Logger log = LoggerFactory.getLogger(EhrPersonServiceImpl.class);
	
	@Resource
	private EhrPersonMapper personMapper;
	@Resource
	private EhrOrganizationMapper orgMapper;
	@Resource
	private EhrBaseDataMapper baseDataMapper;
	@Resource
	private EhrContractMapper contractMapper;
	@Resource
	private EhrUserMapper userMapper;
	@Resource
	private EhrEducationMapper educationMapper;
	@Resource
	private EhrResumeMapper resumeMapper;
	@Resource
	private EhrProjectMapper projectMapper;
	@Resource
	private EhrCertificateMapper certificateMapper;
	@Resource
	private EhrTrainMapper trainMapper;
	@Resource
	private EhrRelationMapper relationMapper;
	@Resource
	private EhrTrialMapper trialMapper;
	@Resource
	private EhrAssessMapper assessMapper;
	@Resource
	private EhrRewardsMapper rewardsMapper;
	@Resource
	private EhrAttendanceMapper attendanceMapper;
	@Resource
	private EhrDimissionMapper leftMapper;
	@Resource
	private EhrJobpostMapper jobPostMapper;
	@Resource
	private EhrOrganizationMapper organizationMapper;
	@Resource
	private EhrCompanyMapper companyMapper;
	
	@Autowired
	private CenterUserClientService centerUserClientService;
	@Resource
	private IEhrRoleService roleService;

	@Override
	public List<EhrPerson> searchPerson(Map<String, Object> map, Long userId,
			Integer start, Integer length, String search, String order,
			String according, int isNumeric, Page<EhrPerson> page)
			throws Exception {
		String where = " and  " + getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("where", where);
		map.put("according", according);
		map.put("search", search);
		map.put("order", order);
		map.put("page", page);

		return personMapper.searchEhrPersonPage(map);
	}

	public String getPersonPowerSqlStr(Long userId, String prefix,
			int functionId) throws Exception {
		if (!prefix.equals(""))
			prefix += ".";
		String result = "";
		List<EhrUserBusinessPower> list = personMapper.userBusinessPower(
				userId, functionId);
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				EhrUserBusinessPower mdl = list.get(i);
				List<EhrUserPersonPowerValue> items = personMapper
						.getPowerValue(mdl.getUserPersonPowerId());
				if (mdl.getFieldType().intValue() == 1) {
					EhrPersonField personMdl = personMapper.getPersonField(mdl
							.getFieldId());
					if (personMdl.getFieldType().intValue() >= 4) {
						if (!items.isEmpty()) {
							result += " " + prefix + personMdl.getFieldName()
									+ " in(";
							for (int j = 0; j < items.size(); j++) {
								if (j == 0) {
									result += Tools.strToStr(items.get(j)
											.getFieldKey());
								} else {
									result += ","
											+ Tools.strToStr(items.get(j)
													.getFieldKey());
								}
							}
							result += ")";
						}
					} else {
						if (!items.isEmpty()) {
							result += " " + prefix + personMdl.getFieldName();
							if (mdl.getOperateType() == 1) {
								result += "="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 2) {
								result += ">"
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 3) {
								result += "<"
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 4) {
								result += ">="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 5) {
								result += "<="
										+ Tools.strToStr(mdl.getFieldValue());
							} else if (mdl.getOperateType() == 6) {
								result += " like "
										+ Tools.strToStr("%"
												+ mdl.getFieldValue());
								;
							} else if (mdl.getOperateType() == 7) {
								result += " like "
										+ Tools.strToStr(mdl.getFieldValue()
												+ "%");
								;
							} else if (mdl.getOperateType() == 8) {
								result += " like "
										+ Tools.strToStr("%"
												+ mdl.getFieldValue() + "%");
								;
							} else if (mdl.getOperateType() == 9) {
								result += " in(" + mdl.getFieldValue() + ")";
							}
						}
					}
				} else if (mdl.getFieldType().intValue() == 2
						&& !items.isEmpty()) {
					result += " and";
				} else if (mdl.getFieldType().intValue() == 3
						&& !items.isEmpty()) {
					result += " or";
				} else if (mdl.getFieldType().intValue() == 4
						&& !items.isEmpty()) {
					result += "(";
				} else if (mdl.getFieldType().intValue() == 5
						&& !items.isEmpty()) {
					result += ")";
				}
			}
		}
		if (result.trim().equals(""))
			return " 1=1";
		return "(" + result + ")";
	}

	@Override
	public List<EhrBaseData> searchBaseData() {
		List<EhrBaseData> list = null;
		list = personMapper.searchBaseData();
		return list;
	}

	@Override
	public boolean deletePerson(Long personId) throws Exception {
		boolean result = false;
		int code = personMapper.isDeletePerson(personId, 1);
		userMapper.deleteUser(personId, 1);
		result = (1 == code);
		return result;
	}

	@Override
	public int searchSalary(Long personId) throws Exception {
		return personMapper.searchSalary(personId);
	}

	@Override
	public Map<String, Object> addPerson(EhrPerson person, EhrUser user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		person.setLastModifyDate(new Date());
		person.setLastModifyUserId(user.getUserId());
		person.setLastModifyUserName(user.getUserName());
		//List<EhrPerson> list = personMapper.getPersonByMobile(person.getMobile());
		String password = Tools.getInitialPassword();
		//if(!list.isEmpty()){	//修改
		//	EhrPerson ep = list.get(0);
		//	person.setPersonId(ep.getPersonId());
		if(person.getPersonId() != null){
			if(person.getIsOpen() == 1){
				person.setPersonPassword(Tools.encryptPassword(password));
				person.setCompanyId(user.getCompanyId());
				personMapper.updateByPrimaryKeySelective(person);
			}
			
			addOrUpdateUser(person,password);
		}else{		//新增
			person.setStatus((byte)0);
			person.setCompanyId(user.getCompanyId());
			person.setCreateUserId(user.getUserId());
			person.setCreateUserName(user.getUserName());
			person.setCreateDate(new Date());
			person.setPersonPassword(Tools.encryptPassword(password));
			personMapper.insertSelective(person);		//添加员工
//			Long userId = userMapper.searchUserByMobile(person.getMobile());
//			if(userId == null){
//				userId = addOrUpdateUser(person,password);//添加用户
//			}
			addOrUpdateUser(person,password);//添加用户
		}
		
		if(person.getIsOpen() == 1){
			int result = sendMsgAndEmail(person, user, password);
			if(result == 3){
				map.put("msg", "密码无法通过邮件或信息发出");
				map.put("success", false);
				return map;
			}
		}
		
		map.put("personId", person.getPersonId());
		map.put("msg", "员工添加成功！");
		map.put("success", true);
		return map;
	}
	
	public Long addOrUpdateUser(EhrPerson person,String password) throws Exception{
		EhrUser user = new EhrUser();
		user.setUserName(person.getEmployeeName());
		user.setMailAddress(person.getEmail());
		user.setIsValid(Byte.valueOf(person.getIsOpen().toString()));
		user.setLastModifyDate(person.getLastModifyDate());
		user.setLastModifyUserId(person.getLastModifyUserId());
		user.setLastModifyUserName(person.getLastModifyUserName());
		
		Long userId = personMapper.getUserId(person.getPersonId());
		if(StringUtils.isEmpty(person.getMobile())){
			throw new JabavaServiceException("手机号为空");
		}
		EhrUser existOfMobile = userMapper.searchUserByUserMobile(person.getMobile());
		if(existOfMobile != null){
			if(userId == null || !existOfMobile.getUserId().equals(userId)){
				throw new JabavaServiceException("手机号已存在");
			}
		}

		if(!StringUtils.isEmpty(person.getEmail())){
			EhrUser existOfEmail = userMapper.searchUserByUserEmail(person.getEmail());
			if(existOfEmail != null){
				if(userId == null || !existOfMobile.getUserId().equals(userId)){
					throw new JabavaServiceException("邮箱已存在");
				}
			}
		}
		
		if(userId == null){		//新增
			user.setUserCode(person.getMobile());
			user.setMobile(person.getMobile());
			user.setFailtureTime(0);
			user.setLoginTime(0);
			user.setUserType((byte)3);
			user.setSex(1);
			user.setIsLocked((byte)0);
			user.setPassword(person.getPersonPassword());
			user.setLastChangePasswordDate(person.getLastModifyDate());
			user.setCompanyId(person.getCompanyId());
			user.setCreateDate(person.getCreateDate());
			user.setCreateUserId(person.getCreateUserId());
			user.setCreateUserName(person.getCreateUserName());
			userMapper.insertSelective(user);
			roleService.addCommonRoleUser(user);
			personMapper.insertUserPerson(user.getUserId(), person.getPersonId());
			//personMapper.insertRoleUser(userId);
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				//用户中心开通
				Map<String,Object> rr = centerUserClientService.openUser(user,password);
				if("false".equals(rr.get("success"))){
					throw new JabavaServiceException(rr.get("msg").toString());
				}
			}
		}else{
			user.setUserId(userId);
			if(person.getIsOpen() == 1){
				user.setPassword(person.getPersonPassword());
				user.setLastChangePasswordDate(person.getLastModifyDate());
			}
			userMapper.updateByPrimaryKeySelective(user);
			
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				//更新用户中心信息
				Map<String,Object> rr = centerUserClientService.updateUser(user);
				if("false".equals(rr.get("success"))){
					throw new JabavaServiceException(rr.get("msg").toString());
				}
			}
		}
		
		return user.getUserId();
	}
	
	public int sendMsgAndEmail(EhrPerson person, EhrUser user, String password) throws Exception{
		int returnValue = 0;
		try{
			returnValue = 1;
			String subject = "您在Jabava的帐号开通啦~";
			String content = person.getEmployeeName()+", 您好,<br><br>"
					+ user.getCompany().getCompanyName()+user.getUserName()+"通过智阳HR Saas（Jabava）系统邀请您填写个人信息资料。<br>"
					+"您的登录密码为："+password+"，请尽快登录" + JabavaPropertyCofigurer.getProperty("JABAVA_URL") + "补全资料。<br><br><br>"
					+ "祝您工作愉快!<br><br>"
					+ "Jabava系统邮件组<br><br>"
					+ "******本邮件由系统自动发送，请勿直接回复。******";
			String msgContent = "您好!"+user.getUserName()+"通过Jabava系统邀请您填写个人信息。"
					+"您的登录密码为："+password+"，请尽快登录" + JabavaPropertyCofigurer.getProperty("JABAVA_URL_SHORT") + "补全资料。";
			Tools.mailSend(person.getEmail(), subject, content, null);
			MobileValidHelp.sendMsg(person.getMobile(), msgContent);
			returnValue = 2;
		} catch (Exception ex) {
			returnValue = 3;
			ex.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public EhrPerson getByPersonId(Long personId) throws Exception {
		return personMapper.getById(personId);
	}

	@Override
	public EhrPerson getByJobNumber(Long companyId, String jobNumber){
		return personMapper.searchPersonByJobNumber(companyId, jobNumber);
	}

	@Override
	public int searchPositive(int day, Long companyId, String distinguish,
			Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and  positive_date<"
				+ Tools.dateToStr(Tools.addDay(now, 10))
				+ " and positive_date >" + Tools.dateToStr(now) + " and  "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		map.put("day", day);
		return personMapper.searchPositive(map);
	}

	@Override
	public int searchContract(int day, int flag, Long companyId,
			String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String dateSql = "flag=" + flag + " and  contract_end_date<"
				+ Tools.dateToStr(Tools.addDay(now, day))
				+ " and contract_end_date >" + Tools.dateToStr(now);
		String where = " and  " + getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("day", day);
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("dateSql", dateSql);
		map.put("where", where);
		return personMapper.searchContract(map);
	}

	@Override
	public int searchBirth(int day, Long userId, Long companyId,
			String distinguish) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and DATE_FORMAT(birth_date, '%m-%d')='"
				+ Tools.formatAll(Tools.addDay(now, day), "MM-dd") + "' and "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		return personMapper.searchBirth(map);
	}

	@Override
	public List<EhrPerson> searchBirthList(int day, Long userId,
			Long companyId, String distinguish) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String where = "and  ep.birth_date "
				+ Tools.strToLikeStr(Tools.formatAll(Tools.addDay(now, day),
						"MM-dd")) + " and "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		return personMapper.searchBirthList(map);
	}

	@Override
	public List<EhrPerson> searchPositiveList(int day, Long companyId,
			String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		//List<EhrPerson> list = null;
		Date now = new Date();
		String where = "and  ep.positive_date<"
				+ Tools.dateToStr(Tools.addDay(now, 10))
				+ " and ep.positive_date >" + Tools.dateToStr(now) + " and  "
				+ getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("where", where);
		map.put("day", day);
		return personMapper.searchPositiveList(map);
	}

	@Override
	public List<EhrContract> searchContractList(int day, int flag,
			Long companyId, String distinguish, Long userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Date now = new Date();
		String dateSql = "flag=" + flag + " and  contract_end_date<"
				+ Tools.dateToStr(Tools.addDay(now, day))
				+ " and contract_end_date >" + Tools.dateToStr(now);
		String where = " and  " + getPersonPowerSqlStr(userId, "", 1) + "";
		map.put("day", day);
		map.put("companyId", companyId);
		map.put("distinguish", distinguish);
		map.put("dateSql", dateSql);
		map.put("where", where);
		return contractMapper.searchContractList(map);
	}

	@Override
	public void importPerson(CommonsMultipartFile multipartFile, EhrUser user)
			throws Exception {
		errorMsg.clear();
		Workbook book = null;
		InputStream inputstream = multipartFile.getInputStream();
		if (".xlsx".equals(JabavaUtil.getExtension(multipartFile
				.getOriginalFilename()))) {
			book = new XSSFWorkbook(inputstream);
		} else if (".xls".equals(JabavaUtil.getExtension(multipartFile
				.getOriginalFilename()))) {
			book = new HSSFWorkbook(inputstream);
		} else {
			return;
		}
		IPersonImport pi = null;
		Map<String, Long> map = new HashMap<String, Long>();
		List<EhrPerson> list = personMapper.getAllPersonCertId(user
				.getCompanyId());
		for (EhrPerson person : list) {
			if (!"".equals(person.getCertId()) && person.getCertId() != null) {
				map.put(person.getCertId(), person.getPersonId());
			}
		}
		for (int num = 0; num < book.getNumberOfSheets(); num++) {
			Sheet sheet = book.getSheetAt(num);
			if (num == 0) {// 个人信息
				pi = new PersonEach(personMapper, baseDataMapper);
			} else if (num == 1) {// 基本信息
				pi = new PersonBase(personMapper, companyMapper, baseDataMapper);
			} else if (num == 2) {// 岗位信息
				pi = new PersonPost(personMapper, baseDataMapper,
						organizationMapper);
			} else if (num == 3) {// 岗位调动
				pi = new PersonTransfer(jobPostMapper, baseDataMapper,
						organizationMapper);
			} else if (num == 4) {// 教育背景
				pi = new PersonEducation(educationMapper, baseDataMapper);
			} else if (num == 5) {// 工作经验
				pi = new PersonResume(resumeMapper, baseDataMapper);
			} else if (num == 6) {// 项目经验
				pi = new PersonProject(projectMapper);
			} else if (num == 7) {// 获得证书
				pi = new PersonCertificate(certificateMapper);
			} else if (num == 8) {// 培训经历
				pi = new PersonTrain(trainMapper);
			} else if (num == 9) {// 家庭成员
				pi = new PersonRelation(relationMapper);
			} else if (num == 10) {// 劳动合同
				pi = new PersonContract(contractMapper, baseDataMapper);
			} else if (num == 11) {// 试用情况
				pi = new PersonTrial(trialMapper);
			} else if (num == 12) {// 绩效考核
				pi = new PersonAssess(assessMapper);
			} else if (num == 13) {// 奖惩记录
				pi = new PersonRewards(rewardsMapper);
			} 
//			else if (num == 14) {// 考勤记录
//				pi = new PersonAttendance(attendanceMapper);
//			}
			else if (num == 14) {// 离职管理
				pi = new PersonLeft(leftMapper);
			} else {
				return;
			}
			map = pi.importPerson(num, sheet, map, user);
		}
	}

	@Override
	public List<EhrPerson> selectAllPerson(Long companyId) throws Exception {
		List<EhrPerson> list = new ArrayList<EhrPerson>();
		list = personMapper.selectAllPerson(companyId);
		return list;
	}

	@Override
	public void exportPerson(List<EhrPerson> persons, EhrUser user,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		try {
			IPersonExport pe = null;
			// 个人信息
			pe = new PersonEach(personMapper, baseDataMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonEach", data);
			// 基本信息
			pe = new PersonBase(personMapper, companyMapper, baseDataMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonBase", data);
			// 岗位信息
			pe = new PersonPost(personMapper, baseDataMapper,
					organizationMapper);
			data = pe.exportPerson(persons, user);
			datas.put("PersonPost", data);
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String("all_persons.xlsx"));
			OutputStream out = response.getOutputStream();
			ExcelUtil.write(datas, "tpl_person.xlsx", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, Object> updatePerson(HttpServletRequest request, EhrPerson person, EhrUser user) throws Exception{
		Map<String, Object> map = new HashMap<>();
		person.setLastModifyDate(new Date());
		person.setLastModifyUserId(user.getUserId());
		person.setLastModifyUserName(user.getUserName());
		personMapper.updateByPrimaryKeySelective(person);
		Long userId = personMapper.getUserId(person.getPersonId());
		if(userId != null && !"".equals(userId)){
			EhrUser u = new EhrUser();
			u.setSex(person.getSex() == 0 ? 1 : 2);
			u.setTelephone(person.getPhone());
			u.setLastModifyDate(new Date());
			u.setLastModifyUserId(user.getUserId());
			u.setLastModifyUserName(user.getUserName());
			userMapper.updateByPrimaryKeySelective(u);
		}
		map.put("success", true);
		map.put("msg", "修改成功！");
		return map;
	}
}
