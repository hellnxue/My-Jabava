package com.jabava.utils;

import java.io.InputStream;
import java.util.Properties;


public class Config implements java.io.Serializable{
//	private final static String _propertyFile = "../resources/config.properties";
//	  private static Properties _props = null;
//	  private static String systemId = null;
//	  private static final String SYSTEM_ID = "SYSTEM_ID";
//
//	  public Config()
//	  {
//		if( _props == null)
//		  init();
//	  }
//
//	  private void init( )
//	  {
//		_props = new Properties();
//		try
//		{
//		  InputStream in =Config.class.getResourceAsStream(_propertyFile);
//		  _props.load(in);
//		}
//		catch(Exception e)
//		{
//					System.err.println(e);
//					e.printStackTrace();
//		}
//	  }
//
//	  public static Properties getProperties()
//	  {
//		if( _props == null )
//		{
//			Config config = new Config();
//		  return config.getProperties();
//		}
//		return _props;
//	  }
//
//	  public static String getSystemId(){
//		  return getProperties().getProperty(SYSTEM_ID);
//	  }
}