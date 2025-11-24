package com.webflux.service;

import com.webflux.dto.FlightDTO;
import com.webflux.entity.Airline;
import com.webflux.entity.Flight;
import com.webflux.exception.AirlineNotFoundException;
import com.webflux.exception.DuplicateResourceException;
import com.webflux.exception.FlightNotFoundException;
import com.webflux.repository.AirlineRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.util.AppUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void testGetFlight_success() {

        Flight flight = new Flight();
        flight.setId("F1");

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        try (var util = org.mockito.Mockito.mockStatic(AppUtils.class)) {
            when(flightRepository.findById("F1"))
                    .thenReturn(Mono.just(flight));

            util.when(() -> AppUtils.entityToDto(flight))
                    .thenReturn(dto);

            StepVerifier.create(flightService.getFlight("F1"))
                    .expectNext(dto)
                    .verifyComplete();
        }
    }

    @Test
    void testGetFlight_notFound() {

        when(flightRepository.findById("X"))
                .thenReturn(Mono.empty());

        StepVerifier.create(flightService.getFlight("X"))
                .expectError(FlightNotFoundException.class)
                .verify();
    }

    @Test
    void testGetFlightBySouAndDes_success() {

        Flight f = new Flight();
        f.setSource("HYD");
        f.setDestination("DEL");

        when(flightRepository.findBySourceAndDestination("HYD", "DEL"))
                .thenReturn(Flux.just(f));

        StepVerifier.create(flightService.getFlightBySouAndDes("HYD", "DEL"))
                .expectNext(f)
                .verifyComplete();
    }

    @Test
    void testSaveFlight_success() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");
        dto.setSource("HYD");
        dto.setDestination("DEL");

        Flight flight = new Flight();
        flight.setId("F1");

        try (var util = org.mockito.Mockito.mockStatic(AppUtils.class)) {

            when(airlineRepository.findById("A1"))
                    .thenReturn(Mono.just(new Airline()));

            when(flightRepository.findByAirlineIdAndSourceAndDestination("A1", "HYD", "DEL"))
                    .thenReturn(Mono.empty());

            util.when(() -> AppUtils.DtoToFlight(dto))
                    .thenReturn(flight);

            when(flightRepository.save(flight))
                    .thenReturn(Mono.just(flight));

            util.when(() -> AppUtils.entityToDto(flight))
                    .thenReturn(dto);

            StepVerifier.create(flightService.saveFlight(Mono.just(dto)))
                    .expectNext(dto)
                    .verifyComplete();
        }
    }

    @Test
    void testSaveFlight_airlineNotFound() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        when(airlineRepository.findById("A1"))
                .thenReturn(Mono.empty());

        StepVerifier.create(flightService.saveFlight(Mono.just(dto)))
                .expectError(AirlineNotFoundException.class)
                .verify();
    }

//    @Test
//    void testSaveFlight_duplicateExists() {
//
//        FlightDTO dto = new FlightDTO();
//        dto.setAirlineId("A1");
//        dto.setSource("HYD");
//        dto.setDestination("DEL");
//
//        Airline airline = new Airline();
//        airline.setId("A1");
//
//        when(airlineRepository.findById("A1"))
//                .thenReturn(Mono.just(airline));
//
//        when(flightRepository.findByAirlineIdAndSourceAndDestination("A1","HYD","DEL"))
//                .thenReturn(Mono.just(new Flight())); // ✅ correct for Mono
//
//        StepVerifier.create(flightService.saveFlight(Mono.just(dto)))
//                .expectError(DuplicateResourceException.class)
//                .verify();
//    }


    @Test
    void testUpdateFlight_success() {

        Flight existing = new Flight();
        existing.setId("F1");

        FlightDTO incoming = new FlightDTO();
        incoming.setSource("BLR");

        Flight updated = new Flight();
        updated.setId("F1");
        updated.setSource("BLR");

        try (var util = org.mockito.Mockito.mockStatic(AppUtils.class)) {

            when(flightRepository.findById("F1"))
                    .thenReturn(Mono.just(existing));

            when(flightRepository.save(existing))
                    .thenReturn(Mono.just(updated));

            util.when(() -> AppUtils.entityToDto(updated))
                    .thenReturn(incoming);

            StepVerifier.create(flightService.updateFlight(Mono.just(incoming), "F1"))
                    .expectNext(incoming)
                    .verifyComplete();
        }
    }

    @Test
    void testUpdateFlight_notFound() {

        when(flightRepository.findById("X"))
                .thenReturn(Mono.empty());

        StepVerifier.create(flightService.updateFlight(Mono.just(new FlightDTO()), "X"))
                .expectError(FlightNotFoundException.class)
                .verify();
    }

    @Test
    void testDeleteFlight_success() {

        when(flightRepository.deleteById("F1"))
                .thenReturn(Mono.empty());

        StepVerifier.create(flightService.deleteFlight("F1"))
                .verifyComplete();
    }
}
