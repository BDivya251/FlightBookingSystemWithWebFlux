package com.webflux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Passengers {

	@Id
	private String id;
	@NotBlank(message = "Passenger name is required")
    private String name;
	 @NotNull(message = "Gender is required")
    private Gender gender;
	@Min(value = 1, message = "Age must be at least 1")
	@Max(value = 120, message = "Age cannot be more than 120")
    private int age;
	@NotBlank(message = "Seat number is required")
    private String seatNumber;   
	@NotNull(message = "Meal type is required")
    private MealType mealType;     

}
