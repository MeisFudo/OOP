package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

public class Account {

    private long number;
    private Person person;
    private Tariff tariff;

    public Account(long number, Person person, IndividualsTariff tariff) {
        this.number = number;
        this.person = person;
        this.tariff = tariff;
    }

    public Account(long number, Person person) {
        this.number = number;
        this.person = person;
        tariff = new IndividualsTariff(new Service());
    }

    public long getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
