package com.webflux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.any;
import com.webflux.dto.BookingDTO;
import com.webflux.entity.Flight;
import com.webflux.repository.BookingRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.repository.PassengerRepository;
import static org.mockito.ArgumentMatchers.eq;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest2 {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Spy
    @InjectMocks
    private BookingService bookingService;

    @Test
    void testProcessBooking_oneWay() {

        BookingDTO dto = new BookingDTO();
        dto.setFlightId("F1");
        dto.setRoundTrip(false);
        dto.setSeatNumbers(List.of(1));   
        dto.setSeatsBooked(1);            

        Flight flight = new Flight();
        flight.setId("F1");
        flight.setSeatNumbersBooked(new ArrayList<>()); 
        flight.setSeatsAvailable(10);

        when(flightRepository.findById("F1"))
                .thenReturn(Mono.just(flight));

       
        doReturn(Mono.just(dto))
                .when(bookingService)
                .processOneWayBooking(any(), any(), eq(false));

        StepVerifier.create(bookingService.processBooking(Mono.just(dto)))
                .expectNext(dto)
                .verifyComplete();
    }
}
