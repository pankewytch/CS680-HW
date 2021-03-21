package edu.umb.cs680.hw05;

public class Car {
    private String make, model;
    private int miles, year;
    private float price;

    public Car (String make, String model, int miles, int year, float price) {
        this.make = make;
        this.model = model;
        this.miles = miles;
        this.year = year;
        this.price = price;
    }

    public String[] carToStringArray() {
        String year_string = ((Integer) year).toString();
        String[] arr = {this.make, this.model, year_string};
        return arr;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Car)) {
            return false;
        } else {
            return ((Car) o).make == this.make && ((Car) o).model == this.model
                    && ((Car) o).year == this.year;
        }
    }

    @Override
    public int hashCode() {
        return (this.year+17)*13;
    }

    @Override
    public String toString() {
        return this.make + ", " + this.model + ", " + this.year;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Rav4", 1000, 2020, 14999);
        Car car2 = new Car("Toyota", "Rav4", 10, 2020, 17999);
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car1.toString());
        System.out.println(car2.toString());
        System.out.println(car1.equals(car2));
    }
}
