package com.jabava.service.tools;

import java.util.*;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jabava.pojo.tools.EhrProvince;


public interface EhrProvinceService {
	

	/**
     * 查询所有的省
     */
   List<HashMap<String,Object>> getAllProvince(EhrProvince ehrProvince);
}
