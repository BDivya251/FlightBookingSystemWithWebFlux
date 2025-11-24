package com.webflux;


import org.junit.jupiter.api.Test;

import com.webflux.entity.Booking;
import com.webflux.entity.BookingStatus;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookingEntityTest {

    @Test
    void testGettersSetters() {
        Booking b = new Booking();
        b.setPnr("P1");
        b.setFlightId("F1");
        b.setPassengerName("John");
        b.setSeatsBooked(2);
        b.setBookingStatus(BookingStatus.BOOKED);

        assertEquals("P1", b.getPnr());
        assertEquals("F1", b.getFlightId());
        assertEquals("John", b.getPassengerName());
    }

    @Test
    void testEqualsAndHashCode() {
        Booking b1 = new Booking("P1","F1","John",2,
                BookingStatus.BOOKED,"a@mail.com",
                new ArrayList<>(),false,5000f,
                LocalDate.now(),new ArrayList<>());

        Booking b2 = new Booking("P1","F1","John",2,
                BookingStatus.BOOKED,"a@mail.com",
                new ArrayList<>(),false,5000f,
                b1.getDepartureDate(),new ArrayList<>());

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testNotEquals() {
        Booking b1 = new Booking();
        b1.setPnr("P1");

        Booking b2 = new Booking();
        b2.setPnr("P2");

        assertNotEquals(b1, b2);
    }

    @Test
    void testToString() {
        Booking b = new Booking();
        assertNotNull(b.toString());
    }

//    @Test
//    void testCanEqual() {
//        Booking b = new Booking();
//        assertFalse(b.canEqual("wrong-type"));
//    }
}
