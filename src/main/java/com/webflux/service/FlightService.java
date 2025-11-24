package com.webflux.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.webflux.dto.FlightDTO;
import com.webflux.entity.Flight;
import com.webflux.exception.AirlineNotFoundException;
import com.webflux.exception.DuplicateResourceException;
import com.webflux.exception.FlightNotFoundException;
import com.webflux.repository.AirlineRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.util.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private AirlineRepository airlineRepository;
//	public Flux<FlightDTO> getFlights(){
//		return flightRepository.findAll().map(AppUtils::entityToDto);
//	}
	
	public Mono<FlightDTO> getFlight(String flightNumber){
		return flightRepository.findById(flightNumber)
				.switchIfEmpty(Mono.error(new FlightNotFoundException("flight id do not exist")))
				.map(AppUtils::entityToDto);
	}
	
	public Flux<Flight> getFlightBySouAndDes(String source,String destination){
		return flightRepository.findBySourceAndDestination(source, destination);
	}
//	public Flux<FlightDTO> getFlightsInPrice(Float min,Float max){
//		return flightRepository.findByPriceBetween(Range.closed(min, max));
//	}
	
//	public Mono<Object> saveFlight(Mono<FlightDTO> flightdtomono){
//		  return flightdtomono
//				  .flatMap(dto->flightRepository.findByAirlineIdAndSourceAndDestination(dto.getAirlineId(), dto.getSource(), dto.getDestination()))
//				  .flatMap(existing->Mono.error( new RuntimeException("flight already exisited in airline")))
//				  .switchIfEmpty(flightRepository.save(AppUtils.DtoToFlight(flightdtomono))
//				  .map(AppUtils::entityToDto));
//				  
//	}
	public Mono<FlightDTO> saveFlight(Mono<FlightDTO> flightDtoMono) {
			return flightDtoMono
					.flatMap(dto->
					airlineRepository.findById(dto.getAirlineId())
					.switchIfEmpty(Mono.error(new AirlineNotFoundException("Airline not found")))
					.flatMap(airline->
							flightRepository.findByAirlineIdAndSourceAndDestination(
			                        dto.getAirlineId(),
			                        dto.getSource(),
			                        dto.getDestination()
			                )
			                .flatMap(existing ->
			                        Mono.error(new DuplicateResourceException("Flight already exists"))
			                )
			                .switchIfEmpty(
			                      
			                        flightRepository.save(AppUtils.DtoToFlight(dto))
			                )	
							)
							).cast(Flight.class)
					.map(AppUtils::entityToDto);
	}


	
	public Mono<FlightDTO> updateFlight(Mono<FlightDTO> flightDtoMono,String id){
		
		return flightRepository.findById(id)
				.switchIfEmpty(Mono.error(new FlightNotFoundException("Flight not found")))
				.zipWith(flightDtoMono, (existingFlight, incomingDto) -> {
		            BeanUtils.copyProperties(incomingDto, existingFlight, "id");
		            return existingFlight;
		        })
		        .flatMap(flightRepository::save)
		        .map(AppUtils::entityToDto);
	}
	public Mono<Void> deleteFlight(String id){
		return flightRepository.deleteById(id);
	}
}
