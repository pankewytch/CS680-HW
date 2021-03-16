package edu.umb.cs680.hw04.students;

public class StudentFactory {

    //no need to inlcude anything in the body of the constructor
    private StudentFactory(){}

    public static Student createInStateStudent(String name, String usAddress) {
        return new Student(name, usAddress, null, null, null, 1000, StudentStatus.InState);
    }

    public static Student createOutOfStateStudent(String name, String usAddress, int yearsInState) {
        return new Student(name, usAddress, yearsInState, null, null, 2000, StudentStatus.OutOfState);
    }

    public static Student createInternationalStudent(String name, String usAddress, int yearsInState, int i20Num, String foreignAddress) {
        return new Student(name, usAddress, yearsInState, i20Num, foreignAddress, 3000, StudentStatus.International);
    }
}
