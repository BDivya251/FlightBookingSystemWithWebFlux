package com.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.FlightDTO;
import com.webflux.entity.Flight;
import com.webflux.repository.AirlineRepository;
import com.webflux.service.FlightService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flight/airline")
public class FlightController {
	
	private final FlightService service;
	@GetMapping("/{id}")
	public Mono<FlightDTO> getFlight(@PathVariable String id){
		return service.getFlight(id);
	}
	@GetMapping()
	public Mono<FlightDTO> getFlightByID(@RequestParam String id){
		return service.getFlight(id);
	}
	

	@PostMapping("/inventory")
	public Mono<FlightDTO> saveFlight(@Valid @RequestBody Mono<FlightDTO> flightdto){
		 return service.saveFlight(flightdto);
	}
	
	@PutMapping("/update/{id}")
	public Mono<FlightDTO> saveFlight(@Valid @RequestBody Mono<FlightDTO> flightdto,@PathVariable String id){
		return service.updateFlight(flightdto, id);
	}
	
	@DeleteMapping("/del")
	public Mono<Void> saveFlight(@RequestParam String id){
		return service.deleteFlight(id);
	}
	@GetMapping("/sorDes")
	public Flux<Flight> getFlightBySrcAndDes(@RequestParam String source,@RequestParam String destination){
		return service.getFlightBySouAndDes(source,destination);
	}
}
