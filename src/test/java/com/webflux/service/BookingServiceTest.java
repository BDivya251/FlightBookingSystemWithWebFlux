package com.webflux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import com.webflux.entity.Booking;
import com.webflux.entity.Flight;
import com.webflux.exception.BookingNotFoundException;
import com.webflux.repository.BookingRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.repository.PassengerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import com.webflux.dto.BookingDTO;
import com.webflux.util.AppUtils;

import org.mockito.Mockito;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    
    @Mock
    private PassengerRepository passengerRepository;
    
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testGetOnId_success() {

        Booking booking = new Booking();
        booking.setPnr("ABC123");

        BookingDTO dto = new BookingDTO();
        dto.setFlightId("ABC123");

        try (var utils = Mockito.mockStatic(AppUtils.class)) {
            when(bookingRepository.getBookingByPnr("ABC123"))
                    .thenReturn(Mono.just(dto));

            utils.when(() -> AppUtils.entityToDtoB(any()))
                    .thenReturn(dto);

            StepVerifier.create(bookingService.getOnId("ABC123"))
                    .expectNext(dto)
                    .verifyComplete();
        }
    }

    @Test
    void testGetOnId_notFound() {

        when(bookingRepository.getBookingByPnr("XYZ"))
                .thenReturn(Mono.empty());

        StepVerifier.create(bookingService.getOnId("XYZ"))
                .expectError(BookingNotFoundException.class)
                .verify();
    }
    @Test
    void testGetOnEmail_success() {
        Booking booking = new Booking();
        booking.setEmailId("user@mail.com");

        when(bookingRepository.findByEmailId("user@mail.com"))
                .thenReturn(Flux.just(booking));

        StepVerifier.create(bookingService.getOnEmail("user@mail.com"))
                .expectNextMatches(dto -> dto.getEmailId().equals("user@mail.com"))
                .verifyComplete();
    }

    @Test
    void testGetOnEmail_notFound() {
        when(bookingRepository.findByEmailId("x@mail.com"))
                .thenReturn(Flux.empty());

        StepVerifier.create(bookingService.getOnEmail("x@mail.com"))
                .expectError(RuntimeException.class)
                .verify();
    }
    @Test
    void testGetTotalBookings() {
        Booking b = new Booking();
        b.setPnr("P1");

        when(bookingRepository.findAll())
                .thenReturn(Flux.just(b));

        StepVerifier.create(bookingService.getTotalBookings())
                .expectNextCount(1)
                .verifyComplete();
    }
    @Test
    void testCancelBooking_notFound() {

        when(bookingRepository.findById("X"))
                .thenReturn(Mono.empty());

        StepVerifier.create(bookingService.cancelBooking("X"))
                .expectError(BookingNotFoundException.class)
                .verify();
    }
    @Test
    void testProcessOneWayBooking_success() {

        BookingDTO dto = new BookingDTO();
        dto.setSeatsBooked(1);
        dto.setSeatNumbers(List.of(10));
        dto.setPassengers(List.of());

        Flight flight = new Flight();
        flight.setSeatsAvailable(5);
        flight.setPrice(1000f);

        when(flightRepository.save(any()))
                .thenReturn(Mono.just(flight));

        when(bookingRepository.save(any()))
                .thenReturn(Mono.just(new Booking()));

        StepVerifier.create(bookingService.processOneWayBooking(flight, dto, false))
                .expectNextCount(1)
                .verifyComplete();
    }


   



}
