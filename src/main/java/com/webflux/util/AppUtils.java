package com.webflux.util;

import org.springframework.beans.BeanUtils;

import com.webflux.dto.BookingDTO;
import com.webflux.dto.FlightDTO;
import com.webflux.entity.Booking;
import com.webflux.entity.Flight;

import reactor.core.publisher.Mono;

public class AppUtils {
	public static FlightDTO entityToDto(Flight flight) {

	    FlightDTO dto = new FlightDTO();

//	    dto.setId(flight.getId());
//	    dto.setAirlineId(flight.getAirlineId());

	    dto.setSource(flight.getSource());
	    dto.setDestination(flight.getDestination());

	    dto.setDepartureDate(flight.getDepartureDate());
	    dto.setDepartureTime(flight.getDepartureTime());

	    dto.setTotalSeats(flight.getTotalSeats());
	    dto.setSeatsAvailable(flight.getSeatsAvailable());

	    dto.setPrice((float) flight.getPrice());

	    return dto;
	}


	
	public static Flight DtoToFlight(FlightDTO flightdtomono) {
		Flight flight = new Flight();
		BeanUtils.copyProperties(flightdtomono, flight);
		return flight;
	}
	
	public static BookingDTO entityToDtoB(Booking booking) {
		BookingDTO bookingdto = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingdto);
		return bookingdto;
	}
	
	public static Booking DtoToBooking(BookingDTO bookingdto) {
		Booking booking = new Booking();
		BeanUtils.copyProperties(bookingdto, booking);
		return booking;
	}
}
