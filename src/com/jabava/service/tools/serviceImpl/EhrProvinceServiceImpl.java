package com.jabava.service.tools.serviceImpl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.jabava.dao.tools.EhrProvinceMapper;
import com.jabava.pojo.tools.EhrProvince;
import com.jabava.service.tools.EhrProvinceService;


@Service
public class EhrProvinceServiceImpl implements EhrProvinceService {
	
	@Resource
	private EhrProvinceMapper  ehrProvincedao;
	
	/**
     * 查询所有的省
     */
	@Override
	public List<HashMap<String, Object>> getAllProvince(EhrProvince ehrProvince) {
		
		return ehrProvincedao.getAllProvince(ehrProvince);
	}
	

}
