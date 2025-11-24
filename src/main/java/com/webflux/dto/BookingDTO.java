package com.webflux.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.webflux.entity.BookingStatus;
import com.webflux.entity.Passengers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
		@NotBlank(message="flight id is required")
	    private String flightId;
		@NotBlank(message="passenger name is required")
	    private String passengerName;
		@NotNull(message="enter how many seats are required")
	    private Integer seatsBooked;
		@NotBlank(message="email id required")
	    @Email(message="Invalid email address")
	    private String emailId;
		@NotNull(message="list of seat numbers required")
		@Size(min = 1, max = 200, message = "You can select between 1 and 200 seats")
	    private List<Integer> seatNumbers;
		
	    private boolean roundTrip;
	    private List<Passengers> passengers;
	    
	    public List<Integer> getSeatNumbersBooked() {
	    	return this.seatNumbers;
	    }
	    public Integer getSeatsBooked() {
	    	return this.seatsBooked;
	    }
}
