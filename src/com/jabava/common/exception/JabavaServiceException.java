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
}
