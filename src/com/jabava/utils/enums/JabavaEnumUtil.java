package com.jabava.utils.enums;

import java.util.HashMap;
import java.util.Map;

public class JabavaEnumUtil {
	private static Map<String,String> cardTypeNameMap = new HashMap<String,String>();
	private static Map<String,String> cardNameTypeMap = new HashMap<String,String>();
	
	static{
		for(JabavaEnum.HroCardType ct : JabavaEnum.HroCardType.values()){
			cardTypeNameMap.put(ct.getValue(), ct.getName());
			cardNameTypeMap.put(ct.getName(), ct.getValue());
		}
	}
	
	public static String getCardTypeName(String cardType){
		if(cardTypeNameMap.containsKey(cardType)){
			return cardTypeNameMap.get(cardType);
		}else{
			return JabavaEnum.HroCardType.OTHER.getName();
		}
	}
	
	public static String getCardTypeByName(String cardTypeName){
		if(cardNameTypeMap.containsKey(cardTypeName)){
			return cardNameTypeMap.get(cardTypeName);
		}else{
			return JabavaEnum.HroCardType.OTHER.getValue();
		}
	}
	
	/**
	 * 如果可以在Map中找到与源证件类型一致的则返回，否则返回其它 
	 * @param cardType
	 * @return
	 */
	public static String transformCardType(String cardType){
		if(cardTypeNameMap.containsKey(cardType)){
			return cardType;
		}else{
			return JabavaEnum.HroCardType.OTHER.getValue();
		}
	}
}
