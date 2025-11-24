package com.webflux.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.webflux.exception.AirlineNotFoundException;

public class AirlineNotFoundExceptionTest {
	

	@Test
	void testExceptMessage() {
		AirlineNotFoundException ex=new AirlineNotFoundException("Airline not found");
		assertEquals("Airline not found",ex.getMessage());
		assertTrue(ex instanceof RuntimeException);
	}
}
