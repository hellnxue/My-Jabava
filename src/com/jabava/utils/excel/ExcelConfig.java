package com.jabava.utils.excel;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class ExcelConfig implements Serializable {

	private static final long serialVersionUID = -3775876872629740778L;

	/**
	 * sheet的名字
	 */
	private String sheetName;

	/**
	 * 
	 */
	private List<ExcelHeader> headers;

	/**
	 * 内容数据
	 */
	private List<JSONObject> datas;

	/**
	 * @param sheetName sheet的显示名称
	 * @param headers excel表头
	 * @param datas excel填充的数据
	 */
	public ExcelConfig(String sheetName, List<ExcelHeader> headers, List<JSONObject> datas) {
		super();
		this.sheetName = sheetName;
		this.headers = headers;
		this.datas = datas;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<ExcelHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ExcelHeader> headers) {
		this.headers = headers;
	}

	public List<JSONObject> getDatas() {
		return datas;
	}

	public void setDatas(List<JSONObject> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
