package com.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.webflux.exception.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SpringWebFluxMApplicationTests {

	@Test
	void contextLoads() {
	}
	 GlobalExceptionHandler handler = new GlobalExceptionHandler();

	    @Test
	    void testDuplicateHandler() {
	        ResponseEntity<String> res =
	                handler.handleDuplicate(new DuplicateResourceException("Duplicate"));

	        assertEquals(409, res.getStatusCode().value());
	        assertEquals("Duplicate", res.getBody());
	    }

	    @Test
	    void testFlightNotFoundHandler() {
	        ResponseEntity<String> res =
	                handler.handleFlightNotFound(new FlightNotFoundException("Not found"));

	        assertEquals(404, res.getStatusCode().value());
	        assertEquals("Not found", res.getBody());
	    }
}
