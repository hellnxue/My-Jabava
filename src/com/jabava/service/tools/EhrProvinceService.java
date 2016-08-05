package com.jabava.service.tools;

import java.util.HashMap;
import java.util.List;
import com.jabava.pojo.tools.EhrProvince;


public interface EhrProvinceService {
	

	/**
     * 查询所有的省
     */
   List<HashMap<String,Object>> getAllProvince(EhrProvince ehrProvince);
   /**
    * 获取省份列表
    * <pre>
    * @author steven.chen
    * @date 2016年4月13日 下午4:34:07 
    * </pre>
    *
    * @return
    */
   List<EhrProvince> getProvinceList();
}
