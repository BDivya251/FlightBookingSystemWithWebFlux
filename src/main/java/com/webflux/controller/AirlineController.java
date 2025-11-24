package com.webflux.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.AirlineDTO;
import com.webflux.service.AirlineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flight")
public class AirlineController {
	
	private final AirlineService airlineService;
	@PostMapping()
	public Mono<String> addAirline(@Valid @RequestBody AirlineDTO a) { 
		return airlineService.addAirline(a);
	}
}
