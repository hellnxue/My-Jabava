package com.jabava.utils;

import java.util.*;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
/**
 * 把汉字处理成拼音后的处理
 * @author zhiyanguser
 *
 */
public class ZimuSort{

	//private ArrayList<HashMap> arrh = new ArrayList<HashMap>();

	public ZimuSort() {

	}

	// 字母Z使用了两个标签，这里有２７个值
	// i, u, v都不做声母, 跟随前面的字母
	private char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
			'击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌',
			'挖', '昔', '压', '匝', '座' };
	private char[] alphatableb = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	private char[] alphatables = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z' };
	private int[] table = new int[27]; // 初始化
	{
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}

	// 主函数,输入字符,得到他的声母,
	// 英文字母返回对应的大小写字母
	// 其他非简体汉字返回 '0' 按参数
	private char Char2Alpha(char ch, String type) {
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');// 为了按字母排序先返回大写字母
		// return ch;
		if (ch >= 'A' && ch <= 'Z')
			return ch;

		int gb = gbValue(ch);
		if (gb < table[0])
			return '0';

		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}

		if (i >= 26) {
			return '0';
		} else {
			if ("b".equals(type)) {// 大写
				return alphatableb[i];
			} else {// 小写
				return alphatables[i];
			}
		}
	}

	// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	private String String2Alpha(String SourceStr, String type) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Alpha(SourceStr.charAt(i), type);
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	// 根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
	private String String2AlphaFirst(String SourceStr, String type) {
		String Result = "";
		try {
			Result += Char2Alpha(SourceStr.charAt(0), type);
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	private boolean match(int i, int gb) {
		if (gb < table[i])
			return false;
		int j = i + 1;

		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}

	// 取出汉字的编码
	private int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GBK");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	// 按照字母将list中的姓名分组
	// 格式{rec:[{"key":"A","value":[{"CNAME":"adisen","age":10},{"CNAME":"advice","age":20}]},{"key":"H","value":[{"CNAME":"hello"}]},{"key":"W","value":[{"CNAME":"吴亦凡"},{"CNAME":"吴昕"}]},]}
	public String sort(List<HashMap<String, Object>> list, String pcName) {
		System.out.println("list=============================" + list);
		StringBuilder sb = new StringBuilder();
		sb.append("{rec:[");
		List arraylist = new ArrayList();
		String[] alphatableb = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		int x = list.size();
		for (String a : alphatableb) {
			// System.out.println("zimu=="+a+"  "+list.size());
			for (int i = 0; i < list.size(); i++) {// 为了排序都返回大写字母
				// System.out.println("list.get(i)=="+list.get(i));
				if (a.equals(String2AlphaFirst(list.get(i).get(pcName).toString(), "b"))) {
					HashMap<String, Object> map1 = list.get(i);
					arraylist.add(map1);
					// System.out.println("finded=="+list.get(i));
					list.remove(i);
					i--;
					// System.out.println("size+list=="+list.size()+"  list="+list);
				}
			}
			if (arraylist.size() > 0) {
				sb.append("{\"key\"").append(":\"").append(a).append("\",");
				sb.append("\"value\"").append(":").append(
						JSONArray.toJSON(arraylist).toString()).append("},");
			}
			arraylist.clear();
		}

		sb.append("]}");
		// System.out.println("zimu----toString==="+sb.toString());
		return sb.toString();

	}

	/**
	 * 将数据按字母范围分组
	 * @param list 待分组数据
	 * @param pcName Map的key
	 * @return
	 */
	public HashMap<String, Object> sort12(List<HashMap<String, Object>> list,String pcName) {
		String[][] sheng = { 
					{"A", "B", "C", "D" , "E", "F", "G"}, 
					{"H","I", "J", "K" },
					{"L","M", "N", "O", "P", "Q", "R", "S" },
					{"T","U", "V", "W", "X","Y", "Z"}, 
				 };
		List<HashMap<String, Object>> arraylist1 = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hash2 = new HashMap<String, Object>();
		for (int j = 0; j < sheng.length; j++) {
			List<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> hash = new HashMap<String, Object>();
			for (String a : sheng[j]) {
				//System.out.println("sheng[j] ++" +a);
				for (int i = 0; i < list.size(); i++) {// 为了排序都返回大写字母
						
					if (a.equals(String2AlphaFirst(list.get(i).get(pcName).toString(), "b"))) {
						HashMap<String, Object> map1 = list.get(i);
						arraylist.add(map1);
						list.remove(i);
						i--;
					}
				}
			}
			if(arraylist!=null&&arraylist.size()>0){
				hash.put("key",sheng[j][0]+"-"+sheng[j][sheng[j].length-1]);
				hash.put("value",arraylist);
				arraylist1.add(hash);
			}
		}
		hash2.put("reg", arraylist1);
		
		//String abc =JSON.toJSON(hash2).toString();
		//System.out.println("这是结果" +abc);
		return hash2;
	}
	
	/**
	 * 将数据按字母范围分组
	 * @param list 待分组数据
	 * @param pyName 中文拼音名称
	 * @param zwName 中文名称
	 * @return
	 */
	public HashMap<String, Object> sort13(List<HashMap<String, Object>> list,String pyName,String zwName ) {
		
		String[][] sheng = { 
					{"A", "B", "C", "D" }, 
					{"E", "F", "G", "H"},
					{"I","J", "K", "L" },
					{"M", "N", "O", "P"}, 
					{"Q", "R", "S", "T" },
					{"U","V", "W", "X"}, 
					{ "Y", "Z" }, 
				 };
		List<HashMap<String, Object>> arraylist1 = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hash2 = new HashMap<String, Object>();
		for (int j = 0; j < sheng.length; j++) {
			List<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> hash = new HashMap<String, Object>();
			for (String a : sheng[j]) {
				for (int i = 0; i < list.size(); i++) { 
						
					Object tempCityName=list.get(i).get(pyName)==null?list.get(i).get(zwName):list.get(i).get(pyName);
					if (a.equals(String2AlphaFirst(tempCityName.toString(), "b"))) {
						HashMap<String, Object> map1 = list.get(i);
						arraylist.add(map1);
						list.remove(i);
						i--;
					}
				}
			}
			if(arraylist!=null&&arraylist.size()>0){
				hash.put("key",sheng[j][0]+"-"+sheng[j][sheng[j].length-1]);
				hash.put("value",arraylist);
				arraylist1.add(hash);
			}
		}
		hash2.put("reg", arraylist1);
		
		String abc =JSON.toJSON(hash2).toString();
		return hash2;
	}
	public static void main(String[] args) {

//		System.out.println("======================");
//		HashMap<String, Object> m1 = new HashMap<String, Object>();
//		HashMap<String, Object> m11 = new HashMap<String, Object>();
//		HashMap<String, Object> m12 = new HashMap<String, Object>();
//		HashMap<String, Object> m2 = new HashMap<String, Object>();
//		HashMap<String, Object> m3 = new HashMap<String, Object>();
//		HashMap<String, Object> m4 = new HashMap<String, Object>();
//		HashMap<String, Object> m5 = new HashMap<String, Object>();
//		HashMap<String, Object> m6 = new HashMap<String, Object>();
//		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//		m1.put("age", 10);
//		m1.put("CNAME", "北京");
//		list.add(m1);
//
//		m12.put("age", 20);
//		m12.put("CNAME", "安徽省");
//		list.add(m12);
//
//		m11.put("age", 20);
//		m11.put("CNAME", "天津");
//		list.add(m11);
//
//		m2.put("CNAME", "河北省");
//
//		list.add(m2);
//		m3.put("CNAME", "山西省");
//
//		list.add(m3);
//		m4.put("CNAME", "内蒙古自治区");
//
//		list.add(m4);
//		m5.put("CNAME", "山东省");
//		
//		m6.put("CNAME", "重庆");
//		list.add(m6);
//
//		ZimuSort zimusort = new ZimuSort();
//		//String resultStr = zimusort.sort(list, "CNAME");
//		//System.out.println(list);
//
//		//System.out.println("okokkkkkkkkkkkkk=" + list);
//		System.out.println("++++++++++++"+list);
//		//String resultStr1 = zimusort.sort12(list, "CNAME").toString();
//		
//		System.out.println("heheheh="+zimusort.sort(list, "CNAME"));
		 
		//System.out.println(resultStr1);
		// list.add("Kobe");
		// list.add("布丁");
		// list.add("杜甫");
		// list.add("元方");
		// list.add("刘德华");
		// list.add("刘大大");
		// Map map=obj1.sort(list);
		// System.out.println(map);
		// System.out.println("-------分组后的输出-----------");
		// System.out.println(map.get("A"));
		// System.out.println(map.get("B"));
		// System.out.println(map.get("C"));
		// System.out.println(map.get("D"));
		// System.out.println(map.get("Y"));
		// System.out.println(map.get("L"));
		ZimuSort s=new ZimuSort();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		System.out.println( s.String2AlphaFirst(hash.get("d").toString(), "b") );
		List<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
		 
	}

	 
}
