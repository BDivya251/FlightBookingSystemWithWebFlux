package com.webflux.exception;

public class NoEnoughFlightSeatsException extends RuntimeException{
	public NoEnoughFlightSeatsException(String msg) {
		super(msg);
	}
}
