package com.jabava.utils.excel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class ExcelHeader implements Serializable {

	private static final long serialVersionUID = -8086427261385575495L;

	/**
	 * 每一列的头部显示名称
	 */
	private String name;

	/**
	 * 读取数据时取值的key名称
	 */
	private String valueName;

	/**
	 * 该名称占用几列
	 */
	private int col;

	/**
	 * 该名称占用几行
	 */
	private int row;

	/**
	 * 第几行,默认为第一行
	 */
	private int rowIndex;

	/**
	 * 所处列的位置
	 */
	private int colIndex;

	/**
	 * @param name 表头显示名称
	 * @param valueName 取值名称
	 * @param col 共占几列
	 * @param row 共占几行
	 * @param rowIndex 第几行，序列从1开始
	 * @param colIndex 第几列，序列从1开始
	 */
	public ExcelHeader(String name, String valueName, int row, int col, int rowIndex, int colIndex) {
		super();
		this.name = name;
		this.valueName = valueName;
		this.col = col;
		this.row = row;
		this.rowIndex = rowIndex - 1;
		this.colIndex = colIndex - 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
