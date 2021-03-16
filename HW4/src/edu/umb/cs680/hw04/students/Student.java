package edu.umb.cs680.hw04.students;


public class Student {

    private String name;
    private String usAddress;
    private Integer yearsInState;
    private Integer i20Num;
    private String foreignAddress;
    private int tuition;
    private StudentStatus studentStatus;

    protected Student(String name, String usAddress, Integer yearsInState, Integer i20Num, String foreignAddress, int tuition, StudentStatus status) {
        this.name = name;
        this.usAddress = usAddress;
        this.yearsInState = yearsInState;
        this.i20Num = i20Num;
        this.foreignAddress = foreignAddress;
        this.tuition = tuition;
        this.studentStatus = status;
    }

    public int getTuition() {
        return tuition;
    }

    public String getName() {
        return name;
    }

    public String getStudentStatus() {
        return studentStatus.toString();
    }
}
