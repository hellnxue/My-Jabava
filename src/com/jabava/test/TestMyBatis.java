package com.jabava.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.service.hro.IHroPactInfoService;

/**
 * @author wangyongqiang
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/jabava/resources/spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = Logger.getLogger(TestMyBatis.class);

	@Autowired
	private IHroPactInfoService service = null;
	
	HroPactInfo pact = null;
 
	
	@Test
	public void test11(){
		try {
			HroPactInfo pact = service.selectPactById((long)33);
			System.out.println(pact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
