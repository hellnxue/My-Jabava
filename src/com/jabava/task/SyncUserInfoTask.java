package com.jabava.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabava.pojo.manage.EhrPerson;
import com.jabava.service.employee.EhrPersonService;
import com.service.provider.CenterUserService;
import com.service.provider.entity.ReturnS;

/**
 * 同步员工数据到 usercenter
 *
 * @version $Id: SyncUserInfo.java, v 0.1 2016年4月26日 下午1:31:39
 * 
 *          <pre>
 * @author steven.chen
 * @date 2016年4月26日 下午1:31:39
 * </pre>
 */

public class SyncUserInfoTask {

	private static final Logger logger = Logger
			.getLogger(SyncUserInfoTask.class);
	@Autowired
	private EhrPersonService ehrPersonService;
	@Autowired
	private CenterUserService centerUserService;

	public void execute() {
		logger.info(" SyncUserInfoTask start。。。。。。。。。。。。。。");
		final String CHANNEL = "3";// 渠道 Jabava
		Integer USER_TYPE = 2;// 用户类型 2 个人用户
		Map<String, Object> data = new HashMap<String, Object>();
		// 获取在职的,有手机号码的员工信息
		List<EhrPerson> personList = ehrPersonService.getAllPersonForSync();
		int success=0;
		int fail = 0;
		int sum=0;
		if (personList != null && personList.size() > 0) {

			for (EhrPerson person : personList) {
				if (!data.isEmpty()) {
					data.clear();
				}
				// 身份证号码
				if (StringUtils.isNotBlank(person.getCertId())) {
					data.put("IDENTITY_CARD", person.getCertId());
				}
				// hroOrgId设为负数
				data.put("ORG_ID",
						person.getCompanyId() - (person.getCompanyId() * 2));
				if (person.getCompany() != null) {
					// 企业 名称
					if (StringUtils.isNotBlank(person.getCompany()
							.getCompanyName())) {
						data.put("ORG_NAME", person.getCompany()
								.getCompanyName());
					} else {
						data.put("ORG_NAME", null);
					}

				}
				if (person.getUser() != null) {
					// 企业联系人
					if (StringUtils.isNotBlank(person.getUser().getUserName())) {
						data.put("NICK_NAME", person.getUser().getUserName());
					} else {
						data.put("NICK_NAME", null);
					}
					// 企业联系人的手机号码
					if (StringUtils.isNotBlank(person.getUser().getMobile())) {
						data.put("MOBILE", person.getUser().getMobile());
					} else {
						data.put("MOBILE", null);
					}
				} else {
					logger.info("  企业联系人为空   personId  " + person.getPersonId()
							+ " companyId " + person.getCompanyId());
				}
				ReturnS res = centerUserService.syncUserInfoByChannel(
						person.getPersonId(), CHANNEL, USER_TYPE,
						person.getMobile(), null, data);
				sum++;
				if (!res.getSuccess()) {
					fail++;
					logger.error("  推送失败  " + person.getPersonId() + "失败原因"
							+ res.getMsg());
				}else{
					success++;
				}

			}

		}
		logger.info(" 本次共推送	 " +sum+"	条 	 成功    "+success+"	条  失败   "+fail +"	条");
		logger.info(" SyncUserInfoTask End。。。。。。。。。");
	}
}
