package com.webflux.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="flights")
public class Flight {
	@Id
	private String id;
    
    private String airlineId;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private int totalSeats;
    private Integer seatsAvailable;
    private float price;
    private List<Integer> seatNumbersBooked=new ArrayList<>();
}
