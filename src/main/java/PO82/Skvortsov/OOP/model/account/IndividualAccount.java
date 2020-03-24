package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

public class IndividualAccount implements Account{

    private long number;
    private Person person;
    private Tariff tariff;

    public IndividualAccount(long number, Person person, IndividualsTariff tariff) {
        this.number = number;
        this.person = person;
        this.tariff = tariff;
    }

    public IndividualAccount(long number, Person person) {
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
