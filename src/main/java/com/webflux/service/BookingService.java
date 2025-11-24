package com.webflux.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.dto.BookingDTO;
import com.webflux.entity.Booking;
import com.webflux.entity.BookingStatus;
import com.webflux.entity.Flight;
import com.webflux.exception.BookingNotFoundException;
import com.webflux.exception.DuplicateResourceException;
import com.webflux.exception.FlightNotFoundException;
import com.webflux.exception.NoEnoughFlightSeatsException;
import com.webflux.repository.BookingRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.repository.PassengerRepository;
import com.webflux.util.AppUtils;

import jakarta.validation.constraints.Email;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingrepository;
	@Autowired
	private FlightRepository flightrepository;
	@Autowired
	private PassengerRepository passengerrepository;
	public Mono<BookingDTO> addBooking(Mono<BookingDTO> bookingdto){
		return bookingdto
		.flatMap(dto->
		flightrepository.findById(dto.getFlightId())
		.switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
		.flatMap(flight->{
//			int count=bookingdto.getSeatsBooked()==null ?0:bookingdto.getSeatsBooked().size();
			
			for(Integer seat:dto.getSeatNumbersBooked()) {
				if(flight.getSeatNumbersBooked().contains(seat)) {
					
					return Mono.error(new RuntimeException("Seat Numbers already booked"));
				}
			}
//			if(dto.isRoundTrip()) {
//				dto.setTotalPrice(flight.getPrice()*2);
//			}
			if(flight.getSeatsAvailable()<dto.getSeatsBooked()) {
			return Mono.error(new RuntimeException("No enought seats available"));
		}
			
					flight.getSeatNumbersBooked().addAll(dto.getSeatNumbersBooked());
	flight.setSeatsAvailable(flight.getSeatsAvailable()-dto.getSeatsBooked());
			return Flux.fromIterable(dto.getPassengers()).flatMap(passengerrepository::save)
					.then(flightrepository.save(flight)).thenReturn(dto);
		}))
		.map(AppUtils::DtoToBooking)
		
		
		.map(dto->{dto.setBookingStatus(BookingStatus.BOOKED);
		return dto;})
		
		.flatMap(bookingrepository::save)
		.map(AppUtils::entityToDtoB);
		}
	
	public Mono<BookingDTO> processBooking(Mono<BookingDTO> bookingDtoMono){
		return bookingDtoMono.flatMap(dto->
		flightrepository.findById(dto.getFlightId())
		.switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
		
		.flatMap(flight->{
			for(Integer seat:dto.getSeatNumbersBooked()) {
				if(flight.getSeatNumbersBooked().contains(seat)) {
					
					return Mono.error(new DuplicateResourceException("Seat Numbers already booked"));
				}
			}
			return processOneWayBooking(flight,dto,false)
					.flatMap(savedBooking->{
						if(!dto.isRoundTrip()) {
							return Mono.just(savedBooking);
						}
					return createReturnBooking(flight,dto);
					});
		})
		
				);
	}
	LocalDate depa=LocalDate.now();
	public Mono<BookingDTO> processOneWayBooking(Flight flight,BookingDTO bookingDto,boolean round){
		
		if(flight.getSeatsAvailable()<bookingDto.getSeatsBooked()) {
			return Mono.error(new NoEnoughFlightSeatsException("No enough flight seats"));
		}
		 
		float total=flight.getPrice()*bookingDto.getSeatsBooked();
		return Flux.fromIterable(bookingDto.getPassengers())
				.flatMap(passengerrepository::save)
				.then(
						Mono.defer(()->{
							depa=flight.getDepartureDate();
					flight.setSeatsAvailable(flight.getSeatsAvailable()-bookingDto.getSeatsBooked());
					flight.getSeatNumbersBooked().addAll(bookingDto.getSeatNumbers());
					return flightrepository.save(flight);
				}))
				.then(bookingrepository.save(AppUtils.DtoToBooking(bookingDto)))
				.map(booking->{
				booking.setTotalPrice(total);
				booking.setBookingStatus(BookingStatus.BOOKED);
				if(round) {
					booking.setDepartureDate(depa.plusDays(2));
				}
				else {
					booking.setDepartureDate(depa);
				}
				return (booking);
				}
				).flatMap(bookingrepository::save)
				.map(AppUtils::entityToDtoB);
				
	}
	private Mono<BookingDTO> createReturnBooking(Flight flight, BookingDTO dto) {

	    BookingDTO returnDto = new BookingDTO();

	    returnDto.setFlightId(dto.getFlightId());  
	    returnDto.setPassengers(dto.getPassengers());
	    returnDto.setSeatsBooked(dto.getSeatsBooked());
	    returnDto.setSeatNumbers(dto.getSeatNumbers());
	    returnDto.setEmailId(dto.getEmailId());
	    returnDto.setRoundTrip(true);
//	    returnDto.setDepartureDate(flight.getDepartureDate().plusDays(2));
	    return processOneWayBooking(flight, returnDto,true);
	}

	public Mono<BookingDTO> getOnId(String id){
		return bookingrepository.getBookingByPnr(id)
				.switchIfEmpty(Mono.error(new BookingNotFoundException("no id found")));
	}
	public Flux<BookingDTO> getOnEmail(String email) {
		return bookingrepository.findByEmailId(email)
				.map(AppUtils::entityToDtoB)
				.switchIfEmpty(Flux.error(new RuntimeException("no list found")));
	}
	
	public Flux<BookingDTO> getTotalBookings(){
		return bookingrepository.findAll()
				.map(AppUtils::entityToDtoB);
	}
	
	public Flux<BookingDTO> getConfirmed(String a){
		return bookingrepository.findByBookingStatus(a)
				.map(AppUtils::entityToDtoB);
	}
	private Booking markCancelled(Booking booking) {
	    booking.setBookingStatus(BookingStatus.CANCELLED);
	    return booking;
	}
	private Flight restoreSeats(Flight flight, Booking booking) {
	    flight.setSeatsAvailable(
	        flight.getSeatsAvailable() + booking.getSeatsBooked()
	    );
	    return flight;
	}

	public Mono<BookingDTO> cancelBooking(String id) {

	    return bookingrepository.findById(id)
	        .switchIfEmpty(Mono.error(new BookingNotFoundException("Booking not found")))
	        .map(this::markCancelled)
	        .flatMap(booking ->
	            flightrepository.findById(booking.getFlightId())
	                .switchIfEmpty(Mono.error(new FlightNotFoundException("Flight not found")))
	                .map(flight -> restoreSeats(flight, booking))
	                .flatMap(flightrepository::save)
	                .thenReturn(booking)
	        )
	        .flatMap(bookingrepository::save)
	        .map(AppUtils::entityToDtoB);
	}
	

}



