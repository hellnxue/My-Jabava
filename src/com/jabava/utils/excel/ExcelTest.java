package com.jabava.utils.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class ExcelTest {

	public static void main(String[] args) {
		List<ExcelHeader> headers = getHeaders();
		List<JSONObject> datas = new ArrayList<JSONObject>();
		for (int j = 0; j < 50; j++) {
			JSONObject json = new JSONObject();
			for (int i = 0; i <= 17; i++) {
				json.put("val" + i, "val" + i);
			}
			datas.add(json);
		}

		
			ExcelConfig config = new ExcelConfig("这是一个测试", headers, datas);
			List<ExcelConfig> configs = new ArrayList<ExcelConfig>();
			configs.add(config);
			String fileName = "Test.xls";
			// ExcelUtil.write(fileName, configs);
		
		

	}

	private static List<ExcelHeader> getHeaders() {
		// String name, String valueName, int row,int col, int rowIndex, int colIndex
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
		ExcelHeader h1 = new ExcelHeader("H1", "val1", 3, 1, 1, 1);
		headers.add(h1);
		ExcelHeader h2 = new ExcelHeader("H2", "val2", 3, 1, 1, 2);
		headers.add(h2);
		ExcelHeader h3 = new ExcelHeader("H3", "val3", 1, 2, 1, 3);
		headers.add(h3);
		ExcelHeader h4 = new ExcelHeader("H4", "val4", 2, 1, 2, 3);
		headers.add(h4);
		ExcelHeader h5 = new ExcelHeader("H5", "val5", 2, 1, 2, 4);
		headers.add(h5);
		ExcelHeader h6 = new ExcelHeader("H6", "val6", 1, 4, 1, 5);
		headers.add(h6);
		ExcelHeader h7 = new ExcelHeader("H7", "val7", 1, 2, 2, 5);
		headers.add(h7);
		ExcelHeader h8 = new ExcelHeader("H8", "val8", 1, 2, 2, 7);
		headers.add(h8);
		ExcelHeader h9 = new ExcelHeader("H9", "val9", 1, 1, 3, 5);
		headers.add(h9);
		ExcelHeader h10 = new ExcelHeader("H10", "val10", 1, 1, 3, 6);
		headers.add(h10);
		ExcelHeader h11 = new ExcelHeader("H11", "val11", 1, 1, 3, 7);
		headers.add(h11);
		ExcelHeader h12 = new ExcelHeader("H12", "val12", 1, 1, 3, 8);
		headers.add(h12);
		ExcelHeader h13 = new ExcelHeader("H13", "val13", 3, 1, 1, 9);
		headers.add(h13);
		ExcelHeader h14 = new ExcelHeader("H14", "val14", 2, 2, 1, 10);
		headers.add(h14);
		ExcelHeader h15 = new ExcelHeader("H15", "val15", 1, 1, 3, 10);
		headers.add(h15);
		ExcelHeader h16 = new ExcelHeader("H16", "val16", 1, 1, 3, 11);
		headers.add(h16);
		ExcelHeader h17 = new ExcelHeader("H17", "val17", 3, 1, 1, 12);
		headers.add(h17);
		return headers;
	}

}
