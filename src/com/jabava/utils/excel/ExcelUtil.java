package com.jabava.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public final class ExcelUtil {
	
	public static void write(Map<String,Object> datas,String template,OutputStream out) throws Exception{
		try(InputStream in = ExcelUtil.class.getResourceAsStream("/com/jabava/resources/template/" + template)) {
        	XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(in, datas);
            workbook.write(out);
	    }
	}
	
	public static void write(Map<String,Object> datas,String template,String destFullPath) throws Exception{
		try(InputStream in = ExcelUtil.class.getResourceAsStream("/com/jabava/resources/template/" + template)) {
	        try (OutputStream out = new FileOutputStream(destFullPath)) {
	        	XLSTransformer transformer = new XLSTransformer();
	            Workbook workbook = transformer.transformXLS(in, datas);
	            workbook.write(out);
	        }
	    }
	}

	private static HSSFWorkbook createWorkbook(List<ExcelConfig> configs) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (configs != null  && !configs.isEmpty()) {
			for (ExcelConfig config : configs) {
				if (config == null) {
					continue;
				}
				createSheet(workbook, config);
			}
		}
		return workbook;
	}

	private static void createSheet(HSSFWorkbook workbook, ExcelConfig config) throws IOException {
		HSSFSheet sheet = workbook.createSheet(config.getSheetName());
		Map<Integer, List<ExcelHeader>> headers = getHeaders(config);
		List<ExcelHeader> dataColumns = getDataColumns(headers.size(), config);
		createSheetHeader(workbook, sheet, headers);
		createSheetData(workbook, sheet, config.getDatas(), dataColumns, headers.size());
		// addPicture(workbook, sheet, config, maxRow);
		// sheet.createFreezePane(dataColumns.size() + 10, headers.size());
	}

	private static int createSheetData(HSSFWorkbook workbook, HSSFSheet sheet, List<JSONObject> datas,
			List<ExcelHeader> dataColumns, int startIndex) {
		if (datas != null && !datas.isEmpty()) {
			for (JSONObject data : datas) {
				if (data != null) {
					HSSFRow row = sheet.getRow(startIndex);
					if (row == null) {
						row = sheet.createRow(startIndex);
					}
					for (ExcelHeader header : dataColumns) {
						if (header != null) {
							HSSFCell cell = row.createCell(header.getColIndex());
							HSSFCellStyle style = createCellStyle(workbook);
							cell.setCellStyle(style);
							Object value =
									data.containsKey(header.getValueName()) ? data.get(header.getValueName()) : "";
							if (value instanceof Integer) {
								cell.setCellValue((Integer) value);
							}
							else if (value instanceof Float) {
								cell.setCellValue((Float) value);
							}
							else if (value instanceof Double) {
								cell.setCellValue((Double) value);
							}
							else if (value instanceof Date) {
								cell.setCellValue((Date) value);
							}
							else if (value instanceof Date) {
								cell.setCellValue((Date) value);
							}
							else {
								cell.setCellValue(value.toString());
							}
						}
					}
					startIndex++;
				}
			}
		}
		return startIndex;
	}

	/**
	 * Discription:根据列描述创建列头
	 */
	private static void createSheetHeader(HSSFWorkbook workbook, HSSFSheet sheet,
			Map<Integer, List<ExcelHeader>> headers) {
		for (Iterator<Integer> keys = headers.keySet().iterator(); keys.hasNext();) {
			Integer rowIndex = keys.next();
			List<ExcelHeader> cols = headers.get(rowIndex);
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			for (ExcelHeader col : cols) {
				if (col != null) {
					HSSFCell cell = row.createCell(col.getColIndex());
					HSSFCellStyle style = createCellStyle(workbook);
					cell.setCellValue(col.getName());
					style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					HSSFFont font = workbook.createFont();
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					font.setFontHeightInPoints((short) 12);
					style.setFont(font);
					cell.setCellStyle(style);
					mergeCell(sheet, col, style);
				}
			}
		}

	}

	private static void mergeCell(HSSFSheet sheet, ExcelHeader col, HSSFCellStyle style) {
		if (col.getCol() == 1 && col.getRow() == 1) {
			return;
		}
		int lastRow = col.getRowIndex() + col.getRow() - 1;
		int lastCol = col.getColIndex() + col.getCol() - 1;
		// int firstRow, int lastRow, int firstCol, int lastCol
		CellRangeAddress range = new CellRangeAddress(col.getRowIndex(), lastRow, col.getColIndex(), lastCol);
		sheet.addMergedRegion(range);
		for (int row = col.getRowIndex(); row <= lastRow; row++) {
			HSSFRow hssfRow = sheet.getRow(row);
			if (hssfRow == null) {
				hssfRow = sheet.createRow(row);
			}
			for (int c = col.getColIndex(); c <= lastCol; c++) {
				HSSFCell cell = hssfRow.getCell(c);
				if (cell == null) {
					cell = hssfRow.createCell(c);
				}
				cell.setCellStyle(style);
			}
		}
	}

	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * Discription:计算出取数据的列
	 */
	private static List<ExcelHeader> getDataColumns(int rowIndex, ExcelConfig config) {
		List<ExcelHeader> allHeaders = config.getHeaders();
		List<ExcelHeader> hList = new ArrayList<ExcelHeader>();
		for (ExcelHeader header : allHeaders) {
			if (header != null) {
				if (header.getRow() + header.getRowIndex() == rowIndex) {
					hList.add(header);
				}
			}
		}
		// 按照列对头部数据进行排序；
		Collections.sort(hList, new Comparator<ExcelHeader>() {
			public int compare(ExcelHeader o1, ExcelHeader o2) {
				return o1.getCol() - o2.getCol();
			}
		});
		return hList;
	}

	private static Map<Integer, List<ExcelHeader>> getHeaders(ExcelConfig config) {
		List<ExcelHeader> allHeaders = config.getHeaders();
		Map<Integer, List<ExcelHeader>> headers = new HashMap<Integer, List<ExcelHeader>>();
		// 按照列对头部数据进行排序；
		Collections.sort(allHeaders, new Comparator<ExcelHeader>() {
			public int compare(ExcelHeader o1, ExcelHeader o2) {
				return o1.getCol() - o2.getCol();
			}
		});
		for (ExcelHeader header : allHeaders) {
			if (header != null) {
				if (headers.containsKey(header.getRowIndex())) {
					headers.get(header.getRowIndex()).add(header);
				}
				else {
					List<ExcelHeader> hList = new ArrayList<ExcelHeader>();
					hList.add(header);
					headers.put(header.getRowIndex(), hList);
				}
			}
		}
		return headers;
	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件流输出到out流中
	 */
	public static void write(OutputStream out, ExcelConfig excelConfig) throws IOException {
		List<ExcelConfig> configs = new ArrayList<ExcelConfig>();
		configs.add(excelConfig);
		write(out, configs);
	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件写到file文件中
	 */
	public static void write(File file, ExcelConfig excelConfig) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		write(out, excelConfig);
		out.flush();
		out.close();
	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件写到fileName文件中
	 */
	public static void write(String fileName, ExcelConfig excelConfig) throws IOException {
		File file = new File(fileName);
		write(file, excelConfig);
	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件流输出到out流中
	 */
	public static void write(OutputStream out, List<ExcelConfig> excelConfigs) throws IOException {
		HSSFWorkbook workbook = createWorkbook(excelConfigs);
		workbook.write(out);

	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件写到file文件中
	 */
	public static void write(File file, List<ExcelConfig> excelConfigs) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		write(out, excelConfigs);
		out.flush();
		out.close();
	}

	/**
	 * Discription:将提供的数据封装成excel文件，并把excel文件写到fileName文件中
	 */
	public static void write(String fileName, List<ExcelConfig> excelConfigs) throws IOException {
		File file = new File(fileName);
		write(file, excelConfigs);
	}
	
	public static void main(String[] args) throws Exception{
		Map<String,Object> datas = new HashMap<String,Object>();
		
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("name", "a");
		data.put("birthDate", "1980-1-1");
		data.put("payment", "1");
		data.put("bonus", "90");
		list.add(data);
		
		data = new HashMap<String,Object>();
		data.put("name", "b");
		data.put("birthDate", "1990-1-1");
		data.put("payment", "2");
		data.put("bonus", "91");
		list.add(data);
		
		datas.put("employees", list);
		datas.put("persons", list);
		
		ExcelUtil.write(datas, "test.xlsx", "E:\\abc.xlsx");
	}
}
