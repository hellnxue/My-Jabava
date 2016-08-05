package com.jabava.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 校验社保公积金基数
 * @author zhiyanguser
 *
 */
public class VertifySecurityGongjijinBaseFreeTools {
	
	// 产品类型
	private static final int  SECURITY_CATEGORY_SECURITY  = 1; // 社保
	private static final int  SECURITY_CATEGORY_GONGJIJIN = 2; // 公积金
	
	// 产品基数类型
	private static final int PRODUCT_BASE_TYPE_INDIVIDUAL = 1; // 个人基数
	private static final int PRODUCT_BASE_TYPE_COMPANY    = 2; // 公司基数
	
	
	// 当前使用的社保产品列表
//	private static final List<Map<String,Object>> currentSecurityConfig=new ArrayList<Map<String,Object>>();
//	private static final List<Map<String,Object>> currentGongjijinProducts=new ArrayList<Map<String,Object>>();
	
	
	/**
	 * 校验社保公积金基数
	 * @parem base 当前基数
	 * @param fee  产品类型     1 社保 2 公积金
	 * @param type 产品基数类型 1 个人 2 企业
	 * @param currentSecurityConfig, @param currentGongjijinProducts 当前使用的社保产品列表
	 * @private
	 */
	public static Map<String,Object> vertifyBaseFree(Float base,int fee,int type,List<Map<String,Object>> currentSecurityConfig,List<Map<String,Object>> currentGongjijinProducts){
		List<Map<String,Object>> products = (fee == SECURITY_CATEGORY_SECURITY) ?  currentSecurityConfig: currentGongjijinProducts;
		
		 Map<String,Object>  result=new HashMap<String,Object>();
		 result.put("failed", "false");
		 result.put("product", null);
		 
		 for(int idx = 0; idx < products.size(); idx ++){
			 Map<String,Object> product = products.get(idx);

				System.out.println("================== 目标产品 =================");
				System.out.println(product);

				String field = (type == PRODUCT_BASE_TYPE_INDIVIDUAL) ? product.get("individual_base_scope").toString() : product.get("company_base_scope").toString();

				// 同时校验基数的上限和下限
				if(!field.equals("")){
					String[] tuples = field.split("~");
					float sub = Float.parseFloat(tuples[0]);
					float sup = Float.parseFloat(tuples[1]);
					
					if(base < sub || base > sup){
						result.put("failed", "true");
						result.put("product", product);
						return result;
					}
				}
			}
		 
		return result;
	}
	
	
}
