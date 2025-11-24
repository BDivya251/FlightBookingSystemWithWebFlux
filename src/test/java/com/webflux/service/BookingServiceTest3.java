package com.webflux.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import com.webflux.dto.BookingDTO;
import com.webflux.entity.Booking;
import com.webflux.entity.Flight;
import com.webflux.exception.BookingNotFoundException;
import com.webflux.repository.BookingRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.util.AppUtils;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest3 {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testCancelBooking_success() {

        Booking booking = new Booking();
        booking.setPnr("B1");
        booking.setFlightId("F1");
        booking.setSeatsBooked(2);

        Flight flight = new Flight();
        flight.setId("F1");
        flight.setSeatsAvailable(10);

        BookingDTO dto = new BookingDTO();
        dto.setFlightId("F1"); // expected returned value

        when(bookingRepository.findById("B1"))
                .thenReturn(Mono.just(booking));

        when(flightRepository.findById("F1"))
                .thenReturn(Mono.just(flight));

        when(flightRepository.save(any()))
                .thenReturn(Mono.just(flight));

        when(bookingRepository.save(any()))
                .thenReturn(Mono.just(booking));

        try (var utils = mockStatic(AppUtils.class)) {
            utils.when(() -> AppUtils.entityToDtoB(any()))
                    .thenReturn(dto);

            StepVerifier.create(bookingService.cancelBooking("B1"))
                    .expectNext(dto)
                    .verifyComplete();
        }
    }

    @Test
    void testCancelBooking_notFound() {

        when(bookingRepository.findById("X"))
                .thenReturn(Mono.empty());

        StepVerifier.create(bookingService.cancelBooking("X"))
                .expectErrorMatches(ex -> ex instanceof BookingNotFoundException)
                .verify();
    }
}
