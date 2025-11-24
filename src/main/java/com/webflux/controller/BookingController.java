package com.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.BookingDTO;
import com.webflux.service.BookingService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class BookingController {
	@Autowired
	private BookingService bookingservice;
	@PostMapping("/booking")
	public Mono<BookingDTO> addBook(@Valid @RequestBody Mono<BookingDTO> book){
		return bookingservice.processBooking(book);
	}
	@GetMapping("/ticket/{id}")
	public Mono<BookingDTO> getBook(@PathVariable String id){
		return bookingservice.getOnId(id);
	}
	@GetMapping("/email/{email}")
	public Flux<BookingDTO> getBookingOnEmail(@PathVariable String email){
		return bookingservice.getOnEmail(email);
	}
	@GetMapping
	public Flux<BookingDTO> getBookings(){
		return bookingservice.getTotalBookings();
	}
	
	@GetMapping("/status/{status}")
	public Flux<BookingDTO> getBookingsByStatus(@PathVariable String status){
		return bookingservice.getConfirmed(status);
	}
	
	@DeleteMapping("/cancel/{id}")
	public Mono<ResponseEntity<Void>> cancelBooking(@PathVariable String id){
		return bookingservice.cancelBooking(id)
				.then(Mono.just(ResponseEntity.noContent().build()));
	}
	
}
