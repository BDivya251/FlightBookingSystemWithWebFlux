package com.webflux.exceptions;
import org.junit.jupiter.api.Test;

import com.webflux.exception.AirlineNotFoundException;
import com.webflux.exception.NoEnoughFlightSeatsException;

import static org.junit.jupiter.api.Assertions.*;
public class CustomeExceptionTest {
	 @Test
	    void testNoEnoughFlightSeatsException() {
	        NoEnoughFlightSeatsException ex =
	                new NoEnoughFlightSeatsException("No seats available");

	        assertEquals("No seats available", ex.getMessage());
	        assertTrue(ex instanceof RuntimeException);
	    }

	    @Test
	    void testAirlineNotFoundException() {
	        AirlineNotFoundException ex =
	                new AirlineNotFoundException("Airline not found");

	        assertEquals("Airline not found", ex.getMessage());
	        assertTrue(ex instanceof RuntimeException);
	    }
}
