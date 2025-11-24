package com.webflux.util;


import com.webflux.dto.BookingDTO;
import com.webflux.dto.FlightDTO;
import com.webflux.entity.Booking;
import com.webflux.entity.Flight;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppUtilsTest {

    @Test
    void testEntityToDto_Flight() {

        Flight flight = new Flight();
        flight.setSource("HYD");
        flight.setDestination("DEL");
        flight.setDepartureDate(LocalDate.now());
        flight.setDepartureTime(LocalTime.NOON);
        flight.setTotalSeats(180);
        flight.setSeatsAvailable(100);
        flight.setPrice(5500f);

        FlightDTO dto = AppUtils.entityToDto(flight);

        assertEquals("HYD", dto.getSource());
        assertEquals("DEL", dto.getDestination());
        assertEquals(flight.getDepartureDate(), dto.getDepartureDate());
        assertEquals(flight.getDepartureTime(), dto.getDepartureTime());
        assertEquals(180, dto.getTotalSeats());
        assertEquals(100, dto.getSeatsAvailable());
        assertEquals(5500f, dto.getPrice());
    }

    @Test
    void testDtoToFlight() {

        FlightDTO dto = new FlightDTO();
        dto.setSource("BLR");
        dto.setDestination("PUNE");
        dto.setTotalSeats(150);

        Flight flight = AppUtils.DtoToFlight(dto);

        assertEquals("BLR", flight.getSource());
        assertEquals("PUNE", flight.getDestination());
        assertEquals(150, flight.getTotalSeats());
    }

    @Test
    void testEntityToDtoB_Booking() {

        Booking booking = new Booking();
        booking.setEmailId("test@email.com");
        booking.setSeatsBooked(3);
        booking.setFlightId("F123");

        BookingDTO dto = AppUtils.entityToDtoB(booking);

        assertEquals("test@email.com", dto.getEmailId());
        assertEquals(3, dto.getSeatsBooked());
        assertEquals("F123", dto.getFlightId());
    }

    @Test
    void testDtoToBooking() {

        BookingDTO dto = new BookingDTO();
        dto.setEmailId("mail@test.com");
        dto.setSeatsBooked(2);
        dto.setFlightId("F999");

        Booking booking = AppUtils.DtoToBooking(dto);

        assertEquals("mail@test.com", booking.getEmailId());
        assertEquals(2, booking.getSeatsBooked());
        assertEquals("F999", booking.getFlightId());
    }
}

