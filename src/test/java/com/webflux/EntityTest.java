package com.webflux;
import org.junit.jupiter.api.Test;

import com.webflux.entity.Airline;
import com.webflux.entity.Booking;
import com.webflux.entity.BookingStatus;
import com.webflux.entity.Flight;
import com.webflux.entity.Gender;
import com.webflux.entity.MealType;
import com.webflux.entity.Passengers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class EntityTest {
	 @Test
	    void testAirlineEntity() {
	        Airline airline = new Airline("A1", "Indigo");

	        assertEquals("A1", airline.getId());
	        assertEquals("Indigo", airline.getName());
	    }
	 
	 @Test
	    void testBookingDefaultCollections() {
	        Booking booking = new Booking();

	        assertNotNull(booking.getSeatNumbers());
	        assertNotNull(booking.getPassengers());
	        assertEquals(0, booking.getSeatNumbers().size());
	        assertEquals(0, booking.getPassengers().size());
	    }

	    @Test
	    void testBookingFields() {
	        Booking booking = new Booking();
	        booking.setPnr("PNR1");
	        booking.setFlightId("F1");
	        booking.setEmailId("test@mail.com");
	        booking.setSeatsBooked(2);
	        booking.setBookingStatus(BookingStatus.BOOKED);
	        booking.setDepartureDate(LocalDate.now());

	        assertEquals("PNR1", booking.getPnr());
	        assertEquals("F1", booking.getFlightId());
	        assertEquals("test@mail.com", booking.getEmailId());
	        assertEquals(2, booking.getSeatsBooked());
	        assertEquals(BookingStatus.BOOKED, booking.getBookingStatus());
	    }
	    @Test
	    void testFlightDefaults() {
	        Flight flight = new Flight();

	        assertNotNull(flight.getSeatNumbersBooked());
	        assertEquals(0, flight.getSeatNumbersBooked().size());
	    }
	    @Test
	    void testFlightFields() {
	        Flight flight = new Flight();
	        flight.setId("F1");
	        flight.setSource("HYD");
	        flight.setDestination("DEL");
	        flight.setDepartureDate(LocalDate.now());
	        flight.setDepartureTime(LocalTime.NOON);
	        flight.setSeatsAvailable(100);
	        flight.setPrice(5500f);

	        assertEquals("F1", flight.getId());
	        assertEquals("HYD", flight.getSource());
	        assertEquals("DEL", flight.getDestination());
	        assertEquals(100, flight.getSeatsAvailable());
	        assertEquals(5500f, flight.getPrice());
	    }
	    @Test
	    void testBookingStatusEnum() {
	        assertEquals(2, BookingStatus.values().length);
	        assertEquals(BookingStatus.BOOKED, BookingStatus.valueOf("BOOKED"));
	    }

	    @Test
	    void testGenderEnum() {
	        assertEquals(2, Gender.values().length);
	        assertEquals(Gender.MALE, Gender.valueOf("MALE"));
	    }
	    @Test
	    void testBookingStatusValues() {
	        assertEquals(2, BookingStatus.values().length);
	    }

	    @Test
	    void testMealTypeValues() {
	        assertEquals(2, MealType.values().length);
	    }
	    @Test
	    void testFlightAllArgs() {
	        Flight f = new Flight("F1","A1","HYD","DEL",null,null,180,100,5500f,new ArrayList<>());
	        assertEquals("F1", f.getId());
	    }
	    @Test
	    void testBookingAllArgs() {
	        Booking b = new Booking("PNR1","F1","John",2,BookingStatus.BOOKED,"a@mail.com",
	                new ArrayList<>(),false,5000f,null,new ArrayList<>());
	        assertEquals("PNR1", b.getPnr());
	    }
	    @Test
	    void testPassengersAllArgs() {
	        Passengers p = new Passengers("P1","John",Gender.MALE,25,"12A",MealType.VEG);
	        assertEquals("John", p.getName());
	    }


	    @Test
	    void testAirlineAllArgs() {
	        Airline a = new Airline("A1", "Test");
	        assertEquals("A1", a.getId());
	        assertEquals("Test", a.getName());
	    }

	    @Test
	    void testGenderValues() {
	        assertEquals(2, Gender.values().length);
	    }
	    
}
