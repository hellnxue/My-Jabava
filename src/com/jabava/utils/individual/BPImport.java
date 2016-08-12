package com.jabava.utils.individual;

import java.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.employee.EhrDimission;
import com.jabava.pojo.individual.IdvRosterBatchDetail;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.IDCard;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.constants.CommonDataConstants;

/**
 * BP名单文件上传
 * @author zhiyanguser
 *
 */
public class BPImport {
	
	public static Logger log = Logger.getLogger(BPImport.class);
	
	private static HashMap<String,Integer> columnMapper = new HashMap<String,Integer>();
	
	static {
		columnMapper.put("姓名", 1);
		columnMapper.put("身份证", 2);
	}
	
	private String getColumnString(Row row,String columnName){
		Integer column = columnMapper.get(columnName);
		return JabavaUtil.getCellStr(row.getCell(column));
	}
	
	/**
	 * 遍历本地上传的BP的 Excel，获取人员具体信息
	 * @param num
	 * @param sheet
	 * @param rosterBatchMap 名单批次map(idcard,名单批次)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> iterateBPFile(int num, Sheet sheet,Map<String, Object> rosterBatchDetailMap, EhrUser user) throws Exception {
		int totalRows = sheet.getLastRowNum();
		Row row;
		
		String sheetName = sheet.getSheetName();
		log.error("导入的sheet: " + sheetName + " 总行数:" + totalRows);
		 
		//结果list
		List<Map<String, Object>> resultMapList=new ArrayList<Map<String, Object>>();
		
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {continue;}
		 

			// 1. 姓名 
			String name = getColumnString(row,"姓名");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"姓名数据错误",name);
			}
			
			
			// 2. 证件号码 
			String idCard = getColumnString(row,"身份证");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"证件号码数据错误",name);
			}else{
				//根据idcard获取名单批次具体信息
				if(rosterBatchDetailMap.get(idCard)!=null){
					Map<String, Object> rosterDetail=  (Map<String, Object>) rosterBatchDetailMap.get(idCard);
					resultMapList.add(rosterDetail);
					
				}
				
			}
			
			 
		}
		
		//拆分日期成 columnName_year   columnName_month   columnName_day 的形式
		
		for(Map<String, Object> dataMap:resultMapList){
			handlerColumnDate(dataMap);
			
		}
		
		
		return resultMapList;
	}
	
	
	/**
	 * 拆分日期成 columnName_year   columnName_month   columnName_day 的形式
	 * @param dataMap
	 */
	private void handlerColumnDate(Map<String, Object> dataMap) {
		
		//要处理的日期字段
		String dateKeyArray[]={"sign_date","contract_start_date","contract_end_date","probation_end_date","first_party_stamp_time","second_party_stamp_time"};
		
		for(String dateKey:dateKeyArray){
			
			if(dataMap.get(dateKey)!=null){
				
				String strDate=(String) dataMap.get(dateKey);
				
				if(!strDate.equals("")){
					
					int strLength=strDate.length();
					
					if(strLength==10){
						String ary[]=strDate.split("-");
						
						if(ary.length==3){
							
							dataMap.put(dateKey+"_year", ary[0]);
							
							dataMap.put(dateKey+"_month", ary[1]);
							
							dataMap.put(dateKey+"_day", ary[2]);
						}
					}
					
					
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Map<String, Object> data = new HashMap<>();
		String strDate="2016-08-11";
		int strLength=strDate.length();
		 
		if(strDate!=null&&!strDate.equals("")&&strLength==10){
			String ary[]=strDate.split("-");
			if(ary.length==3){
				data.put("year", ary[0]);
				data.put("month", ary[1]);
				data.put("day", ary[2]);
			}
		}
		
		System.out.println(data);
		
		
	}
	
	
	

}
