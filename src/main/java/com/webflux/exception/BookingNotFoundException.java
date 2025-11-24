package com.webflux.exception;

public class BookingNotFoundException extends RuntimeException{
	public BookingNotFoundException(String msg) {
		super(msg);
	}
}
