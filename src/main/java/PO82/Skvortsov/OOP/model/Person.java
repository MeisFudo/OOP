package PO82.Skvortsov.OOP.model;

import java.util.Objects;

public class Person {
    private String fName;
    private String sName;

    public Person(String fName, String sName) {
        if (Objects.isNull(fName) || Objects.isNull(sName)) {
            throw new NullPointerException();
        }
        this.fName = fName;
        this.sName = sName;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }


    @Override
    public String toString() {
        return String.format("%s %s", fName, sName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(fName, person.fName) &&
                Objects.equals(sName, person.sName);
    }

    @Override
    public int hashCode() {
        return fName.hashCode() * sName.hashCode();
    }

}
