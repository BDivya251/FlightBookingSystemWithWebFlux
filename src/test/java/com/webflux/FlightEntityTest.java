package com.webflux;

import org.junit.jupiter.api.Test;

import com.webflux.entity.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlightEntityTest {

    @Test
    void testGettersSetters() {
        Flight f = new Flight();
        f.setId("F1");
        f.setAirlineId("A1");
        f.setSource("HYD");
        f.setDestination("DEL");
        f.setDepartureDate(LocalDate.now());
        f.setDepartureTime(LocalTime.NOON);
        f.setTotalSeats(180);
        f.setSeatsAvailable(100);
        f.setPrice(5000f);

        assertEquals("F1", f.getId());
        assertEquals("A1", f.getAirlineId());
        assertEquals("HYD", f.getSource());
        assertEquals("DEL", f.getDestination());
    }

    @Test
    void testEqualsAndHashCode() {
        Flight f1 = new Flight("F1","A1","HYD","DEL",
                LocalDate.now(), LocalTime.NOON,
                180,100,5000f,new ArrayList<>());

        Flight f2 = new Flight("F1","A1","HYD","DEL",
                f1.getDepartureDate(), f1.getDepartureTime(),
                180,100,5000f,new ArrayList<>());

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void testNotEquals() {
        Flight f1 = new Flight();
        f1.setId("F1");

        Flight f2 = new Flight();
        f2.setId("F2");

        assertNotEquals(f1, f2);
    }

    @Test
    void testToString() {
        Flight f = new Flight();
        assertNotNull(f.toString());
    }

}

