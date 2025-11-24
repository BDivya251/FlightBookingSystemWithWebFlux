package com.webflux;


import org.junit.jupiter.api.Test;

import com.webflux.entity.Airline;

import static org.junit.jupiter.api.Assertions.*;

class AirlineEntityTest {

    @Test
    void testGettersSetters() {
        Airline a = new Airline();
        a.setId("A1");
        a.setName("Indigo");

        assertEquals("A1", a.getId());
        assertEquals("Indigo", a.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Airline a1 = new Airline("A1", "Indigo");
        Airline a2 = new Airline("A1", "Indigo");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testNotEquals() {
        Airline a1 = new Airline("A1", "Indigo");
        Airline a2 = new Airline("A2", "SpiceJet");

        assertNotEquals(a1, a2);
    }

    @Test
    void testToString() {
        Airline a = new Airline("A1", "Indigo");
        assertNotNull(a.toString());
    }


}
