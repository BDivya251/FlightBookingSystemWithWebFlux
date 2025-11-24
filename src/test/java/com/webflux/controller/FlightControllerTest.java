package com.webflux.controller;

import com.webflux.dto.FlightDTO;
import com.webflux.entity.Flight;
import com.webflux.service.FlightService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebFluxTest(controllers = FlightController.class)
class FlightControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FlightService flightService;

    @Test
    void testGetFlightByPath() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        Mockito.when(flightService.getFlight("F1"))
                .thenReturn(Mono.just(dto));

        webTestClient.get()
                .uri("/api/flight/airline/F1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(FlightDTO.class)
                .isEqualTo(dto);
    }

    @Test
    void testGetFlightByRequestParam() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        Mockito.when(flightService.getFlight("F1"))
                .thenReturn(Mono.just(dto));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flight/airline")
                        .queryParam("id", "F1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(FlightDTO.class)
                .isEqualTo(dto);
    }

    @Test
    void testSaveFlight() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        Mockito.when(flightService.saveFlight(any()))
                .thenReturn(Mono.just(dto));

        webTestClient.post()
                .uri("/api/flight/airline/inventory")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(FlightDTO.class)
                .isEqualTo(dto);
    }

    @Test
    void testUpdateFlight() {

        FlightDTO dto = new FlightDTO();
        dto.setAirlineId("A1");

        Mockito.when(flightService.updateFlight(any(), eq("F1")))
                .thenReturn(Mono.just(dto));

        webTestClient.put()
                .uri("/api/flight/airline/update/F1")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(FlightDTO.class)
                .isEqualTo(dto);
    }

    @Test
    void testDeleteFlight() {

        Mockito.when(flightService.deleteFlight("F1"))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flight/airline/del")
                        .queryParam("id", "F1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void testGetFlightBySrcAndDes() {

        Flight f = new Flight();
        f.setSource("HYD");
        f.setDestination("DEL");

        Mockito.when(flightService.getFlightBySouAndDes("HYD", "DEL"))
                .thenReturn(Flux.just(f));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flight/airline/sorDes")
                        .queryParam("source", "HYD")
                        .queryParam("destination", "DEL")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Flight.class)
                .hasSize(1);
    }
}
