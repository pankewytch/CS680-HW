package edu.umb.cs680.hw04.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StudentTest{

    @Test
    public void enumerationTest_internationalContentsCheck() {
        String inter = StudentStatus.International.toString();
        assertEquals("International", inter);
    }

    @Test
    public void enumerationTest_inStateContentsCheck() {
        String instate = StudentStatus.InState.toString();
        assertEquals("InState", instate);
    }

    @Test
    public void enumerationTest_OutOfStateContentsCheck() {
        String outofstate = StudentStatus.OutOfState.toString();
        assertEquals("OutOfState", outofstate);
    }

    @Test
    public void createInStateStudent_checkTuitionToVerify() {
        String name = "test student";
        String usAddress = "1 main st boston ma 02140";
        Student cut = StudentFactory.createInStateStudent(name, usAddress);
        assertEquals(1000, cut.getTuition());
    }

    @Test
    public void createOutOfStateStudnet_checkTuitionToVerify() {
        String name = "test student";
        String usAddress = "1 main st boston ma 02140";
        int yearsInState = 5;
        Student cut = StudentFactory.createOutOfStateStudent(name, usAddress, yearsInState);
        assertEquals(2000, cut.getTuition());
    }

    @Test
    public void createInternationalStudent_checkTuitionToVerify() {
        String name = "test student";
        String usAddress = "1 main st boston ma 02140";
        int yearsInState = 5;
        int i20Num = 55555;
        String foreignAddress = "1 fichtestrasse freiburg im breisgau 07561";
        Student cut = StudentFactory.createInternationalStudent(name, usAddress, yearsInState, i20Num, foreignAddress);
        assertEquals(3000, cut.getTuition());
    }

    @Test
    public void getNameMethodTest_StudentClass() {
        String name = "test student";
        String usAddress = "1 main st boston ma 02140";
        int yearsInState = 5;
        int i20Num = 55555;
        String foreignAddress = "1 fichtestrasse freiburg im breisgau 07561";
        Student cut = StudentFactory.createInternationalStudent(name, usAddress, yearsInState, i20Num, foreignAddress);
        assertEquals(name, cut.getName());
    }

    @Test
    public void getStatusMethodTest_StudentClass() {
        String name = "test student";
        String usAddress = "1 main st boston ma 02140";
        int yearsInState = 5;
        int i20Num = 55555;
        String foreignAddress = "1 fichtestrasse freiburg im breisgau 07561";
        Student cut = StudentFactory.createInternationalStudent(name, usAddress, yearsInState, i20Num, foreignAddress);
        assertEquals(StudentStatus.International.toString(), cut.getStudentStatus().toString());
    }
}