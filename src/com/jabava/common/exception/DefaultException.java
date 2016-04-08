package com.jabava.common.exception;

public class DefaultException extends Exception {
	private static final long serialVersionUID = 5345441847274663183L;

	public DefaultException(){
		
	}
	
	public DefaultException(String message){
		super(message);
	}
	
	public DefaultException(Throwable e){
		super(e);
	}
	
	public DefaultException(String message, Throwable e){
		super(message, e);
	}
}
