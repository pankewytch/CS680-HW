package edu.umb.cs680.hw14;

import java.util.ArrayList;
import java.util.Collections;

import edu.umb.cs680.hw14.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarLambdaComparatorTest {
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
    public void CarPriceLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, (Car car1, Car car2) ->
            car1.getPrice()-car2.getPrice()
        );
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(5).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(3).getModel());
        assertEquals("g", testCarList.get(4).getModel());
    }
    @Test
    public void CarPriceStoredLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, Car.getPriceComparator());
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(5).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(3).getModel());
        assertEquals("g", testCarList.get(4).getModel());
    }

    @Test
    public void CarYearLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, (Car car1, Car car2) ->
                car2.getYear()-car1.getYear());
        assertEquals("a", testCarList.get(3).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void CarYearStoredLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, Car.getYearComparator());
        assertEquals("a", testCarList.get(3).getModel());
        assertEquals("b", testCarList.get(1).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(2).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void CarMileageLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, (Car car1, Car car2) ->
                car1.getMiles()-car2.getMiles());
        assertEquals("a", testCarList.get(2).getModel());
        assertEquals("b", testCarList.get(3).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(1).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void CarMileageStoredLambdaExpressionComparatorVerification() {
        Collections.sort(testCarList, Car.getMilesComparator());
        assertEquals("a", testCarList.get(2).getModel());
        assertEquals("b", testCarList.get(3).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(1).getModel());
        assertEquals("e", testCarList.get(0).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void CarParetoLambdaExpressionComparatorVerification() {
        for(Car car:testCarList) {
            car.setDominationCount(testCarList);
        }
        Collections.sort(testCarList, (Car car1, Car car2) ->
                car1.getDominationCount()-car2.getDominationCount());
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(2).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(3).getModel());
        assertEquals("e", testCarList.get(1).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }

    @Test
    public void CarParetoStoredLambdaExpressionComparatorVerification() {
        for(Car car:testCarList) {
            car.setDominationCount(testCarList);
        }
        Collections.sort(testCarList, Car.getParetoComparator());
        assertEquals("a", testCarList.get(0).getModel());
        assertEquals("b", testCarList.get(2).getModel());
        assertEquals("c", testCarList.get(4).getModel());
        assertEquals("d", testCarList.get(3).getModel());
        assertEquals("e", testCarList.get(1).getModel());
        assertEquals("g", testCarList.get(5).getModel());
    }
}
