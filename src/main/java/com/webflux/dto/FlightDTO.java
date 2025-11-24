package com.webflux.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
	@NotBlank(message = "Airline ID is required")
	    private String airlineId;
	@NotBlank(message = "Source is required")
	    private String source;
	@NotBlank(message = "Destination is required")
	    private String destination;
	@NotNull(message = "Departure date is required")
	@FutureOrPresent(message = "Departure date must be today or future")
	    private LocalDate departureDate;
	@NotNull(message = "Departure time is required")
	    private LocalTime departureTime;
	@Min(value = 1, message = "Total seats must be at least 1")
	    private int totalSeats;
	    private Integer seatsAvailable;
	    @Positive(message = "Price must be greater than 0")
	    private float price;
}
