package PO82.Skvortsov.OOP.model;

public class Person {
    String fName;
    String sName;

    public Person(String fName, String sName) {
        this.fName = fName;
        this.sName = sName;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }
}
