package PO82.Skvortsov.OOP.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class PersonTest {
    private static final String F_NAME = "fName", S_NAME = "sName";
    private Person testPerson;

    @Before
    public void setUp() throws Exception {
        testPerson = new Person(F_NAME,S_NAME);
    }

    @Test
    public void getFName() {
        assertEquals(testPerson.getFName(),F_NAME);
    }

    @Test
    public void getSName() {
        assertEquals(testPerson.getSName(),S_NAME);
    }
}