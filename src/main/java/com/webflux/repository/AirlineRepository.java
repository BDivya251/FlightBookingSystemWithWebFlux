package com.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.webflux.entity.Airline;

import reactor.core.publisher.Mono;

@Repository
public interface AirlineRepository extends ReactiveMongoRepository<Airline,String>{
	public Mono<Airline> findById(String id);
}
