package com.jabava.utils;

import java.util.List;

/**
 * datatable分页实体类
 * 
 * @author 郑长山
 * 
 * @param <T>
 *            返回泛型类型
 */
public class Page<T> {
	/**
	 * 起始行号
	 */
	private Integer start = 0;

	/**
	 * 每页显示行数
	 */
	private Integer length;

	/**
	 * 总数据行数
	 */
	private Integer recordsTotal;
	private Integer recordsFiltered;

	/**
	 * 数据内容
	 */
	private List<T> data;

	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	// /**
	// *
	// * @param draw
	// * 当前页
	// * @param recordsFiltered
	// * 当前数据行数
	// */
	// public Page(Integer draw, Integer recordsFiltered) {
	// this.draw = draw;
	// this.recordsFiltered = recordsFiltered;
	// }
	/**
	 * @param start
	 *            开始行数
	 * @param length
	 *            每页显示行数
	 */
	public Page(Integer start, Integer length) {
		// 判断开始行数不能为空并且不能小于0
		if (start == null || start < 0) {
			// 当前页为：开始行数/每页显示行数+1
			start = 0;
		}
		// 判断每页显示行数不能为空并且不能小于零
		if (length == null || length < 0) {
			// 默认每页显示数据
			length = DEFAULT_PAGE_SIZE;
		}
		this.start = start;
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
