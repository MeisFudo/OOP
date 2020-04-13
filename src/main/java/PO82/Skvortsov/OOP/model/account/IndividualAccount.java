package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

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

}
