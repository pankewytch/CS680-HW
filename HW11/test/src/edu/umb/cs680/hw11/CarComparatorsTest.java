package edu.umb.cs680.hw11;

import static org.junit.jupiter.api.Assertions.*;
import edu.umb.cs680.hw11.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CarComparatorsTest {
    private ArrayList<Car> testCarList = null;

    @BeforeEach
    public void createCarList() {
        testCarList = new ArrayList<>();
        testCarList.add(new Car(3000, 1000, 2003, "Toyota", "a", false));
        testCarList.add(new Car(4000, 2000, 2005, "Toyota", "b", false));
        testCarList.add(new Car(5000, 3000, 2002, "Toyota", "c", false));
        testCarList.add(new Car(2000, 2000, 2004, "Toyota", "d", false));
        testCarList.add(new Car(1000, 2000, 2008, "Toyota", "e", false));
        testCarList.add(new Car(6000, 2000, 1993, "Toyota", "g", false));
    }

    @Test
    public void CarDominationCountTest() {
        for(Car car:testCarList) {
            car.setDominationCount(testCarList);
        }
        assertEquals(0, testCarList.get(0).getDominationCount());
        assertEquals(1, testCarList.get(1).getDominationCount());
        assertEquals(4, testCarList.get(2).getDominationCount());
        assertEquals(1, testCarList.get(3).getDominationCount());
        assertEquals(0, testCarList.get(4).getDominationCount());
        assertEquals(4, testCarList.get(5).getDominationCount());
    }

    @Test
    public void carPriceComparatorClassVerification() {
        CarPriceComparator cut = new CarPriceComparator();
        Collections.sort(testCarList, cut);
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(5).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(3).getModel());
        assertEquals("g", testCarList.get(4).getModel());
    }

    @Test
    public void carYearComparatorClassVerification() {
        CarYearComparator cut = new CarYearComparator();
        Collections.sort(testCarList, cut);
        assertEquals("a", testCarList.get(3).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void carMileageComparatorClassVerification() {
        CarMilesComparator cut = new CarMilesComparator();
        Collections.sort(testCarList, cut);
        assertEquals("a", testCarList.get(2).getModel());
        assertEquals("b", testCarList.get(3).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(1).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void carParetoComparatorClassVerification() {
        CarPraretoCopmarator cut = new CarPraretoCopmarator();
        for(Car car:testCarList) {
            car.setDominationCount(testCarList);
        }
        Collections.sort(testCarList, cut);
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(2).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(3).getModel());
        assertEquals("e", testCarList.get(1).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }
}