package com.jabava.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jabava.pojo.tools.EhrProvince;
import com.jabava.service.tools.serviceImpl.EhrProvinceServiceImpl;

public class EhrProvinceServiceImplTest {

	EhrProvinceServiceImpl t=new EhrProvinceServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		System.out.println("star");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@Test
	public void testGetAllProvince() {
		System.out.println("test......");
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-mybatis.xml"});
	        context.start();
	        EhrProvinceServiceImpl t=new EhrProvinceServiceImpl();
		EhrProvince ehrProvince=new EhrProvince();
		List<HashMap<String, Object>> list=t.getAllProvince( ehrProvince);
		System.out.println(list);
	}

	 

}
