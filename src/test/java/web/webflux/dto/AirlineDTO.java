package web.webflux.dto;

import org.junit.jupiter.api.Test;

import com.webflux.dto.AirlineDTO;

import static org.junit.jupiter.api.Assertions.*;

class AirlineDTOTest {

    @Test
    void testGetterSetter() {
        AirlineDTO dto = new AirlineDTO();
        dto.setName("Indigo");

        assertEquals("Indigo", dto.getName());
    }

    @Test
    void testAllArgsConstructor() {
        AirlineDTO dto = new AirlineDTO("SpiceJet");
        assertEquals("SpiceJet", dto.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        AirlineDTO a1 = new AirlineDTO("Indigo");
        AirlineDTO a2 = new AirlineDTO("Indigo");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testNotEquals() {
        AirlineDTO a1 = new AirlineDTO("Indigo");
        AirlineDTO a2 = new AirlineDTO("AirAsia");

        assertNotEquals(a1, a2);
    }

    

    @Test
    void testToString() {
        AirlineDTO dto = new AirlineDTO("Indigo");
        assertNotNull(dto.toString());
    }
}
