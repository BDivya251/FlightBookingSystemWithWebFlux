package com.webflux.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String pnr;
    private String flightId;  
    private String passengerName;
    private Integer seatsBooked;
    private BookingStatus bookingStatus;
    @Email(message="Invalid email address")
    private String emailId;
    private List<Integer> seatNumbers=new ArrayList<>();
    private boolean roundTrip;
    private float totalPrice;
    private LocalDate departureDate;
    private List<Passengers> passengers=new ArrayList<>();
}

