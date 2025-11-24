package com.webflux;



import org.junit.jupiter.api.Test;

import com.webflux.entity.Gender;
import com.webflux.entity.MealType;
import com.webflux.entity.Passengers;

import static org.junit.jupiter.api.Assertions.*;

class PassengersEntityTest {

    @Test
    void testGettersSetters() {
        Passengers p = new Passengers();
        p.setId("P1");
        p.setName("John");
        p.setGender(Gender.MALE);
        p.setAge(25);
        p.setSeatNumber("12A");
        p.setMealType(MealType.VEG);

        assertEquals("P1", p.getId());
        assertEquals("John", p.getName());
        assertEquals(Gender.MALE, p.getGender());
        assertEquals(25, p.getAge());
        assertEquals("12A", p.getSeatNumber());
        assertEquals(MealType.VEG, p.getMealType());
    }

    @Test
    void testEqualsAndHashCode() {
        Passengers p1 = new Passengers("P1","John",Gender.MALE,25,"12A",MealType.VEG);
        Passengers p2 = new Passengers("P1","John",Gender.MALE,25,"12A",MealType.VEG);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testNotEquals() {
        Passengers p1 = new Passengers("P1","John",Gender.MALE,25,"12A",MealType.VEG);
        Passengers p2 = new Passengers("P2","Sam",Gender.FEMALE,30,"15B",MealType.NON_VEG);

        assertNotEquals(p1, p2);
    }

    @Test
    void testToString() {
        Passengers p = new Passengers("P1","John",Gender.MALE,25,"12A",MealType.VEG);
        assertNotNull(p.toString());
    }
    

//    @Test
//    void testCanEqual() {
//        Passengers p = new Passengers();
//        assertFalse(p.canEqual("wrong-type"));
//    }
}
