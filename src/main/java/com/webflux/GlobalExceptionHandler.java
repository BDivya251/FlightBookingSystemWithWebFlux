package com.webflux;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.webflux.exception.AirlineNotFoundException;
import com.webflux.exception.BookingNotFoundException;
import com.webflux.exception.DuplicateResourceException;
import com.webflux.exception.FlightNotFoundException;
import com.webflux.exception.NoEnoughFlightSeatsException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<String> handleDuplicate(DuplicateResourceException ex) {
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public Mono<ResponseEntity<String>> handleRuntimeException(RuntimeException ex){
		 return Mono.just(
	                ResponseEntity
	                        .badRequest()
	                        .body(ex.getMessage())   
	        );
	}
	
	 @ExceptionHandler(WebExchangeBindException.class)
	    public ResponseEntity<String> handleValidationException(WebExchangeBindException ex) {
	        String errorMessage = ex.getFieldErrors().stream()
	                .findFirst()
	                .map(error -> error.getDefaultMessage())  
	                .orElse("Validation error");

	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	 @ExceptionHandler(FlightNotFoundException.class)
	 public ResponseEntity<String> handleFlightNotFound(FlightNotFoundException ex){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(AirlineNotFoundException.class)
	 public ResponseEntity<String> handleAirlineNotFoundException(AirlineNotFoundException ex){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(BookingNotFoundException.class)
	 public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException ex){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(NoEnoughFlightSeatsException.class)
	 public ResponseEntity<String> handleNoEnoughFlightSeatsException(NoEnoughFlightSeatsException ex){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }
}
