package edu.umb.cs680.hw14;

import java.util.Comparator;

public interface CarComparator extends Comparator<Car> {
    public int compare(Car car1, Car car2);
}
