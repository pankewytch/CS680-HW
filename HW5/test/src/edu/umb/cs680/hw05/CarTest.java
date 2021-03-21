package edu.umb.cs680.hw05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    public void CarConstructorTest_viaInstanceOf() {
        Car cut = new Car("Toyota", "Rav4", 100, 2020, 14999);
        assertTrue(cut instanceof Car);
    }

    @Test
    public void verifyCarEqualityWithMakeModelYear() {
        Car cut = new Car("Toyota", "Rav4", 100, 2020, 14999);
        String[] expected = {"Toyota", "Rav4", "2020"};
        assertArrayEquals(expected, cut.carToStringArray());
    }

    @Test
    public void verifyCarEquality_equalsOverride() {
        Car cut1 = new Car("Toyota", "Rav4", 100, 2020, 14999);
        Car cut2 = new Car("Toyota", "Rav4", 10000, 2020, 10999);
        assertTrue(cut1.equals(cut2));
    }

    @Test
    public void verifyCarEquality_equalsOverride_differentObject() {
        Car cut = new Car("Toyota", "Rav4", 100, 2020, 14999);
        Integer testObject = 5;
        assertFalse(cut.equals(testObject));
    }

    @Test
    public void verifyCarEquality_equalsOverride_sameObjectType_false() {
        Car cut1 = new Car("Toyota", "Rav4", 100, 2020, 14999);
        Car cut2 = new Car("Toyota", "Rav8", 10000, 2020, 10999);
        assertFalse(cut1.equals(cut2));
    }

    @Test
    public void verifyCarEquality_hashCodeOverride_true() {
        Car cut1 = new Car("Toyota", "Rav4", 100, 2020, 14999);
        Car cut2 = new Car("Toyota", "Rav4", 10000, 2020, 10999);
        assertTrue(cut1.hashCode() == cut2.hashCode());
    }

    @Test
    public void verifyCarEquality_hashCodeOverride_false() {
        Car cut1 = new Car("Toyota", "Rav4", 100, 2021, 14999);
        Car cut2 = new Car("Toyota", "Rav4", 10000, 2020, 10999);
        assertFalse(cut1.hashCode() == cut2.hashCode());
    }

    @Test
    public void verifyCarToString() {
        Car cut = new Car("Toyota", "Rav4", 100, 2021, 14999);
        String expected = "Toyota, Rav4, 2021";
        assertTrue(expected.equals(cut.toString()));
    }
}