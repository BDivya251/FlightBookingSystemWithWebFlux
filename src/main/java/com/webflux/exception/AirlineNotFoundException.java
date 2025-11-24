package com.webflux.exception;

public class AirlineNotFoundException extends Exception{
	public AirlineNotFoundException(String msg) {
		super(msg);
	}
}
