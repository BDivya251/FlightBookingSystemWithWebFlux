package com.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.dto.AirlineDTO;
import com.webflux.entity.Airline;
import com.webflux.entity.Flight;
import com.webflux.repository.AirlineRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AirlineService {
	
	private final AirlineRepository airlinerepository;
	public Mono<String> addAirline(AirlineDTO airl){
		Airline a = new Airline();
		a.setName(airl.getName());
		Mono<Airline> airline= airlinerepository.save(a);
		return airline.map(Airline::getId);
	}
}
