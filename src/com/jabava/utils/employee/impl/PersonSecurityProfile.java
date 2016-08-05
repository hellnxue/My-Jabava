package com.jabava.utils.employee.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.pojo.common.EhrCommonData;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.socialsecurity.EhrPersonSecurityProfile;
import com.jabava.utils.IDCard;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.VertifySecurityGongjijinBaseFreeTools;
import com.jabava.utils.constants.CommonDataConstants;
import com.jabava.utils.employee.IPersonImport;

/**
 * 员工社保/公积金档案导入
 * @author zhiyanguser
 *
 */
public class PersonSecurityProfile implements IPersonImport{
	
	public static Logger log = Logger.getLogger(PersonSecurityProfile.class);
	// 产品类型
	private static final int  SECURITY_CATEGORY_SECURITY  = 1; // 社保
	private static final int  SECURITY_CATEGORY_GONGJIJIN = 2; // 公积金
	
	// 产品基数类型
	private static final int PRODUCT_BASE_TYPE_INDIVIDUAL = 1; // 个人基数
	private static final int PRODUCT_BASE_TYPE_COMPANY    = 2; // 公司基数
	
	
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	private static HashMap<String,Integer> columnMapper = new HashMap<String,Integer>();
	
	static{
		
		columnMapper.put("工号",1);
		columnMapper.put("姓名",2);
		columnMapper.put("社保账号",3);
		columnMapper.put("社保缴纳方式",4);
		columnMapper.put("社保起缴月",5);
		columnMapper.put("社保办理月",6);
		columnMapper.put("个人社保基数",7);
		columnMapper.put("企业社保基数",8);
		columnMapper.put("公积金账号",9);
		columnMapper.put("公积金缴纳方式",10);
		columnMapper.put("公积金起缴月",11);
		columnMapper.put("公积金办理月",12);
		columnMapper.put("个人公积金基数",13);
		columnMapper.put("企业公积金基数",14);
		 
	}
	
	private String getColumnString(Row row,String columnName){
		Integer column = columnMapper.get(columnName);
		return JabavaUtil.getCellStr(row.getCell(column));
	}
	

	/**
	 * 导入员工社保公积金档案excel
	 * return  Map<String, Object>
	 */
	@Override
	public Map<String, Object> importPersonSecurityProfile(int num, Sheet sheet,Map<String, Long> map, EhrUser user,List<Map<String,Object>> currentSecurityConfig,List<Map<String,Object>> currentGongjijinProducts) throws Exception {
		int totalRows = sheet.getLastRowNum();
		Row row;
		
		String sheetName = sheet.getSheetName();
		log.error("导入的sheet: " + sheetName + " 总行数:" + totalRows);
		
		if(totalRows==0){
			 throw new JabavaServiceException("Excel数据为空！请填写数据再进行导入操作！");
			 
		}
		 
		//接收每一行的数据
		HashMap <String,Object> chuckList = new HashMap <String,Object>();
		
		//收集员工的工号，用户查询员工信息
		List<String> jobNumberList=new ArrayList<String>();
		
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			if (row == null) {continue;}
			
			EhrPersonSecurityProfile personSecurityProfile=new EhrPersonSecurityProfile();
			
			// 1. 工号校验
			String jobNumber = getColumnString(row,"工号");
			
			if("".equals(jobNumber)){
				
				 throw new JabavaServiceException(sheetName,i,"工号数据错误",jobNumber);
				 
			}
			jobNumberList.add(jobNumber);//收集工号
			
 			personSecurityProfile.setJobNumber(jobNumber);
			
			

			// 2. 姓名校验
			String name = getColumnString(row,"姓名");
			if("".equals(name)){
				throw new JabavaServiceException(sheetName,i,"姓名数据错误",name);
			}
			
			// 3. 社保账号校验
			String securityAccount = getColumnString(row,"社保账号");
			if("".equals(securityAccount)){
				throw new JabavaServiceException(sheetName,i,"社保账号数据错误",securityAccount);
			}
			
			personSecurityProfile.setSecurityAccount(securityAccount);
			
			

			// 4. 社保缴纳方式校验
			String securityCreateType = getColumnString(row,"社保缴纳方式");
			if("".equals(securityCreateType)){
				throw new JabavaServiceException(sheetName,i,"社保缴纳方式数据错误",securityCreateType);
			}else {
				if("新开".equals(securityCreateType)){
					personSecurityProfile.setSecurityCreateType(0);
				}else if("续缴".equals(securityCreateType)){
					personSecurityProfile.setSecurityCreateType(1);
				}
				
			}
				
			
			
			// 5.社保起缴月校验
			String securityStarttime = getColumnString(row,"社保起缴月");
			if("".equals(securityStarttime)){
				throw new JabavaServiceException(sheetName,i,"姓名数据错误",securityStarttime);
			}
			
			personSecurityProfile.setSecurityStarttime(securityStarttime);
				
			// 6.社保办理月校验
			String securityActivatetime = getColumnString(row,"社保办理月");
			if("".equals(securityActivatetime)){
				throw new JabavaServiceException(sheetName,i,"社保办理月数据错误",securityActivatetime);
			}
			personSecurityProfile.setSecurityActivatetime(securityActivatetime);
			
			// 7.个人社保基数校验
			String securityIndividualBase = getColumnString(row,"个人社保基数");
			if("".equals(securityIndividualBase)){
				throw new JabavaServiceException(sheetName,i,"个人社保基数数据错误",securityIndividualBase);
			}else{
				//判断基数是否在指定的技术范围
				Map<String,Object> vmap=VertifySecurityGongjijinBaseFreeTools.vertifyBaseFree(Float.parseFloat(securityIndividualBase), SECURITY_CATEGORY_SECURITY, PRODUCT_BASE_TYPE_INDIVIDUAL, currentSecurityConfig, currentGongjijinProducts);
				
				String failed=vmap.get("failed").toString();
				
				if(failed.equals("true")){
					Map<String,Object> product =(Map<String, Object>) vmap.get("product");
					String msg="个人社保基数不在规定范围:"+product.get("policy_group_name").toString()+' '
							+ product.get("prod_name").toString()+' '
							+ "个人社保基数范围：￥"
							+ product.get("individual_base_scope").toString() ;
					
					throw new JabavaServiceException(sheetName,i,msg,securityIndividualBase);
				}
				
			}
			personSecurityProfile.setSecurityBase(new BigDecimal(securityIndividualBase) );//待判断
			
			
			
			// 8.企业社保基数校验
			String securityOrgBase = getColumnString(row,"企业社保基数");
			if("".equals(securityOrgBase)){
				throw new JabavaServiceException(sheetName,i,"企业社保基数数据错误",securityOrgBase);
			}else{
				//判断基数是否在指定的技术范围
				Map<String,Object> vmap=VertifySecurityGongjijinBaseFreeTools.vertifyBaseFree(Float.parseFloat(securityOrgBase), SECURITY_CATEGORY_SECURITY, PRODUCT_BASE_TYPE_COMPANY, currentSecurityConfig, currentGongjijinProducts);
				
				String failed=vmap.get("failed").toString();
				
				if(failed.equals("true")){
					Map<String,Object> product =(Map<String, Object>) vmap.get("product");
					String msg="企业社保基数不在规定范围:"+product.get("policy_group_name").toString()+' '
							+ product.get("prod_name").toString()+' '
							+ "企业社保基数范围：￥"
							+ product.get("company_base_scope").toString() ;
					
					throw new JabavaServiceException(sheetName,i,msg,securityOrgBase);
				}
				
			}
			
			personSecurityProfile.setSecurityOrgBase(new BigDecimal(securityOrgBase));
			
			// 9.企业社保基数校验
			String gongjijinAccount = getColumnString(row,"公积金账号");
			if("".equals(gongjijinAccount)){
				throw new JabavaServiceException(sheetName,i,"公积金账号数据错误",gongjijinAccount);
			}
			personSecurityProfile.setGongjijinAccount(gongjijinAccount);
			
			// 10.公积金缴纳方式校验
			String gjjSecurityCreateType = getColumnString(row,"公积金缴纳方式");
			if("".equals(gjjSecurityCreateType)){
				throw new JabavaServiceException(sheetName,i,"公积金缴纳方式数据错误",gjjSecurityCreateType);
			}else {
				if("新开".equals(gjjSecurityCreateType)){
					personSecurityProfile.setGongjijinCreateType(0);
				}else if("续缴".equals(gjjSecurityCreateType)){
					personSecurityProfile.setGongjijinCreateType(1);
				}
				
			}	
			
			
			// 11.公积金起缴月校验
			String gongjijinStartTime = getColumnString(row,"公积金起缴月");
			if("".equals(gongjijinStartTime)){
				throw new JabavaServiceException(sheetName,i,"公积金起缴月数据错误",gongjijinStartTime);
			}	 
			
			personSecurityProfile.setGongjijinStarttime(gongjijinStartTime);
			
			// 12.公积金办理月校验
			String gongjijinActivateTime = getColumnString(row,"公积金办理月");
			if("".equals(gongjijinActivateTime)){
				throw new JabavaServiceException(sheetName,i,"公积金办理月数据错误",gongjijinActivateTime);
			}	
			personSecurityProfile.setGongjijinActivatetime(gongjijinActivateTime);
			
			// 13.个人公积金基数校验
			String gongjijinIndividualBase = getColumnString(row,"个人公积金基数");
			if("".equals(gongjijinIndividualBase)){
				throw new JabavaServiceException(sheetName,i,"个人公积金基数数据错误",gongjijinIndividualBase);
			}else{
				//判断基数是否在指定的技术范围
				Map<String,Object> vmap=VertifySecurityGongjijinBaseFreeTools.vertifyBaseFree(Float.parseFloat(gongjijinIndividualBase), SECURITY_CATEGORY_GONGJIJIN, PRODUCT_BASE_TYPE_INDIVIDUAL, currentSecurityConfig, currentGongjijinProducts);
				
				String failed=vmap.get("failed").toString();
				
				if(failed.equals("true")){
					Map<String,Object> product =(Map<String, Object>) vmap.get("product");
					String msg="个人公积金基数不在规定范围:"+product.get("policy_group_name").toString()+' '
							+ product.get("prod_name").toString()+' '
							+ "个人公积金基数范围：￥"
							+ product.get("individual_base_scope").toString() ;
					
					throw new JabavaServiceException(sheetName,i,msg,gongjijinIndividualBase);
				}
				
			}
			personSecurityProfile.setGongjijinBase(new BigDecimal(gongjijinIndividualBase) );
			
			// 14.企业公积金基数校验
			String gongjijinOrgBase = getColumnString(row,"企业公积金基数");
			if("".equals(gongjijinOrgBase)){
				throw new JabavaServiceException(sheetName,i,"企业公积金基数数据错误",gongjijinOrgBase);
			}else{
				//判断基数是否在指定的技术范围
				Map<String,Object> vmap=VertifySecurityGongjijinBaseFreeTools.vertifyBaseFree(Float.parseFloat(gongjijinOrgBase), SECURITY_CATEGORY_GONGJIJIN, PRODUCT_BASE_TYPE_COMPANY, currentSecurityConfig, currentGongjijinProducts);
				
				String failed=vmap.get("failed").toString();
				
				if(failed.equals("true")){
					Map<String,Object> product =(Map<String, Object>) vmap.get("product");
					String msg="企业公积金基数不在规定范围:"+product.get("policy_group_name").toString()+' '
							+ product.get("prod_name").toString()+' '
							+ "企业公积金基数范围：￥"
							+ product.get("company_base_scope").toString() ;
					
					throw new JabavaServiceException(sheetName,i,msg,gongjijinOrgBase);
				}
				
			}	  
			personSecurityProfile.setGongjijinOrgBase(new BigDecimal(gongjijinOrgBase) );
			
			chuckList.put(jobNumber,personSecurityProfile);
			
		}
		chuckList.put("jobNumberList", jobNumberList);//员工工号list
		return chuckList;
	}

	@Override
	public HashMap<String, Integer> duplicatedCertId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]){
		String base="1000.0";
		String based="1000";
		String basedd="abc";
		
		System.out.println(JabavaUtil.string2BigDecinal(based) );
		
		String pat="\\d";
		 
		System.out.println(based.matches(pat));
		System.out.println(basedd.matches(pat));
			
		 
		
	}

	@Override
	public Map<String, Object> importPerson(int num, Sheet sheet,
			Map<String, String> map, EhrUser user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

}
