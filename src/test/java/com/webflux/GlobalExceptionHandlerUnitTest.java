package com.webflux;


import com.webflux.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.BeanPropertyBindingResult;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerUnitTest {

    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleDuplicate() {
        ResponseEntity<String> res =
                handler.handleDuplicate(new DuplicateResourceException("Dup"));

        assertEquals(409, res.getStatusCode().value());
        assertEquals("Dup", res.getBody());
    }

    @Test
    void testHandleRuntime() {
        ResponseEntity<String> res =
                handler.handleRuntimeException(new RuntimeException("Runtime"))
                        .block();

        assertEquals(400, res.getStatusCode().value());
        assertEquals("Runtime", res.getBody());
    }

    @Test
    void testHandleFlightNotFound() {
        ResponseEntity<String> res =
                handler.handleFlightNotFound(new FlightNotFoundException("Not found"));

        assertEquals(404, res.getStatusCode().value());
        assertEquals("Not found", res.getBody());
    }

    @Test
    void testHandleAirlineNotFound() {
        ResponseEntity<String> res =
                handler.handleAirlineNotFoundException(new AirlineNotFoundException("Airline!"));

        assertEquals(404, res.getStatusCode().value());
        assertEquals("Airline!", res.getBody());
    }

    @Test
    void testHandleBookingNotFound() {
        ResponseEntity<String> res =
                handler.handleBookingNotFoundException(new BookingNotFoundException("Booking!"));

        assertEquals(404, res.getStatusCode().value());
        assertEquals("Booking!", res.getBody());
    }

    @Test
    void testHandleNoSeats() {
        ResponseEntity<String> res =
                handler.handleNoEnoughFlightSeatsException(new NoEnoughFlightSeatsException("No seats"));

        assertEquals(404, res.getStatusCode().value());
        assertEquals("No seats", res.getBody());
    }

    @Test
    void testHandleValidationException() {

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(new Object(), "obj");

        bindingResult.addError(new FieldError("obj", "email", "Invalid email"));

        WebExchangeBindException ex =
                new WebExchangeBindException(null, bindingResult);

        ResponseEntity<String> res = handler.handleValidationException(ex);

        assertEquals(400, res.getStatusCode().value());
        assertEquals("Invalid email", res.getBody());
    }
}

