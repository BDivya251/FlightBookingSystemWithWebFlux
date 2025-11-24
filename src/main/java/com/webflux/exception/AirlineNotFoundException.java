package com.webflux.exception;

public class AirlineNotFoundException extends RuntimeException{
	public AirlineNotFoundException(String msg) {
		super(msg);
	}
}
