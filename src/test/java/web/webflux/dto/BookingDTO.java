package web.webflux.dto;

import com.webflux.dto.BookingDTO;
import com.webflux.entity.Passengers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {

    @Test
    void testGetterSetter() {
        BookingDTO dto = new BookingDTO();
        dto.setFlightId("F1");
        dto.setPassengerName("John");
        dto.setSeatsBooked(2);
        dto.setEmailId("test@mail.com");
        dto.setSeatNumbers(List.of(10, 11));
        dto.setRoundTrip(true);

        assertEquals("F1", dto.getFlightId());
        assertEquals("John", dto.getPassengerName());
        assertEquals(2, dto.getSeatsBooked());
        assertEquals("test@mail.com", dto.getEmailId());
        assertEquals(List.of(10, 11), dto.getSeatNumbers());
        assertTrue(dto.isRoundTrip());
    }

    @Test
    void testAllArgsConstructor() {
        BookingDTO dto = new BookingDTO(
                "F1", "John", 2, "a@mail.com",
                List.of(5, 6), true, List.of()
        );

        assertEquals("F1", dto.getFlightId());
    }

    @Test
    void testEqualsAndHashCode() {
        BookingDTO b1 = new BookingDTO("F1", "John", 2, "a@mail.com",
                List.of(1, 2), false, List.of());

        BookingDTO b2 = new BookingDTO("F1", "John", 2, "a@mail.com",
                List.of(1, 2), false, List.of());

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testNotEquals() {
        BookingDTO b1 = new BookingDTO("F1", "John", 2, "a@mail.com",
                List.of(1, 2), false, List.of());

        BookingDTO b2 = new BookingDTO("F2", "Sam", 1, "b@mail.com",
                List.of(3), false, List.of());

        assertNotEquals(b1, b2);
    }


    @Test
    void testToString() {
        BookingDTO dto = new BookingDTO();
        assertNotNull(dto.toString());
    }

    @Test
    void testGetSeatNumbersBooked() {
        BookingDTO dto = new BookingDTO();
        dto.setSeatNumbers(List.of(10, 20));

        assertEquals(List.of(10, 20), dto.getSeatNumbersBooked());
    }
}
