package com.jabava.utils.employee.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.dao.employee.EhrRewardsMapper;
import com.jabava.pojo.employee.EhrRewards;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonRewards implements IPersonImport {
	private EhrRewardsMapper rewardsMapper;
	private Row row;
	private java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public PersonRewards(EhrRewardsMapper rewardsMapper) {
		this.rewardsMapper = rewardsMapper;
	}

	@Override
	public Map<String, Long> importPerson(int num, Sheet sheet,
			Map<String, Long> map, EhrUser user) throws Exception {
		// TODO Auto-generated method stub
		int totalRows = sheet.getLastRowNum();
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			try { 
				Long personId = map.get(row.getCell(4).toString());
				if (personId != null) {
					EhrRewards rewards = new EhrRewards();
					if (row.getCell(5) != null) {// 奖励时间
						rewards.setRewardDate(formatDate.parse(row.getCell(5)
								.toString()));
					}
					rewards.setRewardType(Integer.valueOf(JabavaUtil.getCellStr(row.getCell(6))));// 奖惩类别
					if (row.getCell(7) != null) {//奖惩金额
						rewards.setRewardCost(new BigDecimal(row.getCell(7)
								.toString()));
					}
					rewards.setMemo(JabavaUtil.getCellStr(row.getCell(8)));// 奖惩描述
					rewards.setPersonId(personId);
					rewards.setCreateDate(new Date());
					rewards.setCreateUserId(user.getUserId());
					rewards.setCreateUserName(user.getUserName());
					rewards.setLastModifyDate(new Date());
					rewards.setLastModifyUserId(user.getUserId());
					rewards.setLastModifyUserName(user.getUserName());
					rewardsMapper.insertSelective(rewards);
				}
			}catch (Exception e) {
				continue;
			}
			
		}
		return map;
	}

}
