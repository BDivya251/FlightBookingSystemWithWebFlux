**Flight Booking System **

This project implements a reactive Flight Booking System using webflux and mongoDB.

The assignment focuses on designing the data model, exposing REST APIs and applying validations.
<img width="502" height="602" alt="FlightBookingMagm drawio" src="https://github.com/user-attachments/assets/ee88e898-8d14-436b-b411-d76bd99fd0ec" />



**Implemented Services:**

• FlightService – handles flight inventory, flight creation, update, delete, and search by source/destination.  

• BookingService – manages booking creation (one-way & round-trip), seat allocation, return booking generation, cancellation flow, and booking history.

• AirlineService – supports airline registration and validation before adding flights.

**Key Features Delivered:**

• Flight inventory management with seat tracking.

• One-way and round-trip booking processing.

• Passenger handling with meal type and seat validation.

• Ticket retrieval by PNR and booking lookup by email/status.

• Global exception handling with custom errors.

• DTO-entity mapping via AppUtils.

