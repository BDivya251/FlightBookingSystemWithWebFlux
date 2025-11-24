package com.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflux.dto.BookingDTO;
import com.webflux.entity.Booking;

import jakarta.validation.constraints.Email;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveMongoRepository<Booking,String>{
//	public Mono<BookingDTO> getFlightById(Integer id);

	public Mono<BookingDTO> getBookingByPnr(String id);

	public Flux<Booking> findByEmailId(String email);

	public Flux<Booking> findByBookingStatus(String a);
}
