package com.webflux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webflux.dto.AirlineDTO;
import com.webflux.entity.Airline;
import com.webflux.repository.AirlineRepository;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {
	@Mock
	private AirlineRepository airlineRepository;
	@InjectMocks
	private AirlineService airlineService;
	@Test
	void testAddline() {
		AirlineDTO dto  = new AirlineDTO();
		dto.setName("Indigo");
		Airline saved =new Airline();
		saved.setId("12345");
		saved.setName("Indigo");
		when(airlineRepository.save(any(Airline.class))).thenReturn(Mono.just(saved));
		Mono<String> result=airlineService.addAirline(dto);
		StepVerifier.create(result)
		.expectNext("12345")
		.verifyComplete();
	}
}
