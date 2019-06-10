package com.globomantics.productservice.common;

public class MyServiceException extends Exception {

	private static final long serialVersionUID = 1110517910654342737L;

	public MyServiceException(String message) {
		super(message);
	}

	public MyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
