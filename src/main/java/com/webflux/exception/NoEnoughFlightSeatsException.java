package com.webflux.exception;

public class NoEnoughFlightSeatsException extends Exception{
	public NoEnoughFlightSeatsException(String msg) {
		super(msg);
	}
}
