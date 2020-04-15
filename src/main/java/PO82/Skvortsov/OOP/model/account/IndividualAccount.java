package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.util.Objects;

public class IndividualAccount extends AbstractAccount {

    private Person person;

    public IndividualAccount(long number, Person person, IndividualsTariff tariff) {
        super(number,tariff);
        this.person = person;

    }

    public IndividualAccount(long number, Person person) {
        super(number,new IndividualsTariff(new Service()));
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
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
