package com.webflux.repository;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.webflux.dto.FlightDTO;
import com.webflux.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface FlightRepository extends ReactiveMongoRepository<Flight,String> {
	Mono<Flight> findByAirlineIdAndSourceAndDestination(String airlineId,String source,String destination);
	Flux<Flight> findBySourceAndDestination(String source,String destination);
}
