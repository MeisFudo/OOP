package PO82.Skvortsov.OOP.model;

public class Account {

    private long number;
    private Person person;
    private IndividualsTariff tariff;

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

    public IndividualsTariff getTariff() {
        return tariff;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTariff(IndividualsTariff tariff) {
        this.tariff = tariff;
    }
}
