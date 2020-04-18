package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.time.LocalDate;
import java.util.Objects;

public class IndividualAccount extends AbstractAccount {

    private Person person;

    public IndividualAccount(long number, Person person, IndividualsTariff tariff, LocalDate registrationDate) {
        super(number,tariff, registrationDate);
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        this.person = person;

    }

    public IndividualAccount(long number, Person person) {
        this(number, person,new IndividualsTariff(new Service()), LocalDate.now());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        this.person = person;
    }

    @Override
    public String toString() {
        return String.format("“Individual account:%1$s holder: %2$s%1$s%3$s", System.lineSeparator(), person, super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IndividualAccount that = (IndividualAccount) o;
        return Objects.equals(person, that.getPerson()) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return 97 * super.hashCode() * person.hashCode();
    }

}
