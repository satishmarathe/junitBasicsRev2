package com.globomantics.productservice.common;

public class MyDataAccessException extends Exception {

	private static final long serialVersionUID = -1530578540919250393L;

	public MyDataAccessException(String arg0) {
		super(arg0);
	}

	public MyDataAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
