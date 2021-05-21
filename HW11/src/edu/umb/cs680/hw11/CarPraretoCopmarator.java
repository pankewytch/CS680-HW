package edu.umb.cs680.hw11;

import java.util.Comparator;

public class CarPraretoCopmarator implements CarComparator {
    public int compare(Car car1, Car car2) {
        return car1.getDominationCount()-car2.getDominationCount();
    }
}
