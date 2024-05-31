package com.insilicogen.CRUD_PRJ.bbs.service;

public class BoardNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoardNotFoundException() {


	}

    public BoardNotFoundException(String message) {
        super(message);
    }
}