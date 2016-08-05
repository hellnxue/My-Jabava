package com.jabava.utils.employee.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.employee.EhrDimissionMapper;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.IDCard;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.employee.IPersonImport;

public class PersonLeft implements IPersonImport {
	public static Logger log = Logger.getLogger(PersonLeft.class);
	private Row row;
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	
	private static HashMap<String,Integer> columnMapper = new HashMap<String,Integer>();
	
	private EhrBaseDataMapper baseDatamapper;
	private EhrDimissionMapper leftMapper;
	
	static {
		columnMapper.put("工号", 1);
		columnMapper.put("姓名", 2);
		columnMapper.put("证件号码", 3);
		columnMapper.put("离职时间", 4);
		columnMapper.put("薪资结算日", 5);
		columnMapper.put("离职原因", 6);
		columnMapper.put("备注", 7);
	}

	private String getColumnString(Row row,String columnName){
		Integer column = columnMapper.get(columnName);
		return JabavaUtil.getCellStr(row.getCell(column));
	}
	
	public PersonLeft(EhrDimissionMapper leftMapper,EhrBaseDataMapper baseDatamapper) {
		this.baseDatamapper = baseDatamapper;
		this.leftMapper = leftMapper;
	}
	
	public Map <String,Object> importPerson(int num, Sheet sheet,Map<String, String> map, EhrUser user) throws Exception {
		int totalRows = sheet.getLastRowNum();
		String sheetName = sheet.getSheetName();
		
		HashMap <String,Object> list = new HashMap <String,Object>();
		for (int i = 1; i <= totalRows; i++) {
			EhrDimission left = new EhrDimission();
			EhrPerson person = new EhrPerson();
			row = sheet.getRow(i);
			if (row == null) {continue;}
			
			// 1. 工号校验
			String jobNumber = getColumnString(row,"工号");
			
			if("".equals(jobNumber)){//第一行的第一个必填项为空直接break
//				 throw new JabavaServiceException(sheetName,i,"工号信息数据错误",jobNumber);
				 break;
			}
			
			left.setJobNumber(jobNumber);

			// 2. 姓名校验
			String name = getColumnString(row,"姓名");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"姓名数据错误",name);
			}
			left.setEmployeeName(name);
			
			
			
			String certId = getColumnString(row,"证件号码");
			
			if(i==1&&"".equals(certId)){//第一行出错时给提示，从第二行判断如果必填项为空时 break
				throw new JabavaServiceException(sheetName,i,"证件号码数据格式错误",certId);
				 
			}else if(i>1&&"".equals(certId)){ 
				break;
			}else{
				if(!IDCard.IDCardValidate(certId.toUpperCase())){
					throw new JabavaServiceException(sheetName,i,"证件号码数据格式错误",certId);
				}
			}
			
			left.setCertId(certId.toUpperCase());
			
			//离职管理中的工号，姓名和证件号码还要根据personid修改一遍，以后改
			
			String personId = map.get(certId);
			String personIdForJobNumber = map.get(jobNumber);
			
//			if(personId == null){
//				continue;
//			}
			
			if (personId != null) {
				person.setPersonId(Long.parseLong(personId));
				//从员工表里查询工号是否存在    若存在要查证这个工号是不是这个人的
				if(personIdForJobNumber!=null){
					 if(Long.parseLong(personId)!=Long.parseLong(personIdForJobNumber)){
						 throw new JabavaServiceException(sheetName,i,"证件号码为"+certId+"的工号错误！",jobNumber);
					 } 
				} else{
					throw new JabavaServiceException(sheetName,i,"工号不存在！",jobNumber);
				}
				
				
			} else{
				throw new JabavaServiceException(sheetName,i,"不存在证件号码为"+certId+"的员工！",certId);
			}
			
			String leftDate = getColumnString(row,"离职时间");
			
			if("".endsWith(leftDate.trim())){
				throw new JabavaServiceException(sheetName,i,"离职时间数据错误",leftDate);
			}
			try{
				Date date = formatDate.parse(leftDate);
				left.setDimissionDate(date);
			}catch(Exception e){
				throw new JabavaServiceException(sheetName,i,"离职时间数据错误",leftDate);
			}
			
			
			
			String salaryDate = getColumnString(row,"薪资结算日");
			if("".endsWith(salaryDate.trim())){
				throw new JabavaServiceException(sheetName,i,"薪资结算日数据错误",salaryDate);
			}
			try{
				Date date = formatDate.parse(salaryDate);
				left.setSalarySettleDate(date);
			}catch(Exception e){
				throw new JabavaServiceException(sheetName,i,"薪资结算日数据错误",salaryDate);
			}
			
			String reason = getColumnString(row,"离职原因");
			if("".endsWith(reason.trim())){
				throw new JabavaServiceException(sheetName,i,"离职原因数据错误",reason);
			}
			List<EhrBaseData> blist = baseDatamapper.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_DIMISSION_REASON, reason);
			if(blist == null || blist.isEmpty()){
				throw new JabavaServiceException(sheetName,i,"离职原因数据错误",reason);
			}
			left.setDimissionCause(blist.get(0).getBaseDataId());
			
			left.setMemo(getColumnString(row,"备注"));// 备注
			
			left.setPersonId(Long.parseLong(personId));
			left.setCreateDate(new Date());
			left.setCreateUserId(user.getUserId());
			left.setCreateUserName(user.getUserName());
			left.setLastModifyDate(new Date());
			left.setLastModifyUserId(user.getUserId());
			left.setLastModifyUserName(user.getUserName());
			
			list.put(certId, left);
		}
		return list;
	}

	public HashMap<String, Integer> duplicatedCertId() {
		return null;
	}

	@Override
	public Map<String, Object> importPersonSecurityProfile(int num,
			Sheet sheet, Map<String, Long> map, EhrUser user,
			List<Map<String, Object>> currentSecurityConfig,
			List<Map<String, Object>> currentGongjijinProducts)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
