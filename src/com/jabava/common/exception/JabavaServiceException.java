package com.jabava.common.exception;

public class JabavaServiceException extends DefaultException {
	private static final long serialVersionUID = 6190540107567432434L;
	
	public JabavaServiceException(){
		
	}
	
	public JabavaServiceException(String message){
		super(message);
	}
	
	public JabavaServiceException(Throwable e){
		super(e);
	}
	
	public JabavaServiceException(String message, Throwable e){
		super(message, e);
	}
	
	/**
	 * 抛出Excel导入时的异常
	 * @param sheetName   异常发生的sheet
	 * @param rowNumber   异常发生的行
	 * @param error       异常信息
	 * @param errorData   异常数据
	 */
	public JabavaServiceException(String sheetName,Integer rowNumber,String error,String errorData){
		super("["+sheetName+"] 第" + rowNumber + "行," + error + " 错误数据:\""+errorData+"\"");
	}
}
