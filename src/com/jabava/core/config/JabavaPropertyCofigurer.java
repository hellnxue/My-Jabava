package com.jabava.core.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 用于Spring加载配置文件后可以直接在代码中访问
 * @author zbwang
 *
 */
public class JabavaPropertyCofigurer extends PropertyPlaceholderConfigurer {
	private static Map<String,String> propertiesMap = new HashMap<String,String>();
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		
		for(Object key : props.keySet()){
			propertiesMap.put(key.toString(),props.getProperty(key.toString()));
		}
	}
	
	public static String getProperty(String name){
		return propertiesMap.get(name);
	}
	
	public static String getSystemId(){
		return propertiesMap.get("SYSTEM_ID");
	}
	
	public static String getSsoSwitch(){
		return propertiesMap.get("sso.switch");
	}
}
