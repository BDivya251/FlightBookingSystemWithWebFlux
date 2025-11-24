package com.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.webflux.entity.Passengers;

@Repository
public interface PassengerRepository extends ReactiveMongoRepository<Passengers,String> {

}
