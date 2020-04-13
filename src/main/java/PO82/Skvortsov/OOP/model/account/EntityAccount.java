package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

public class EntityAccount extends AbstractAccount {

    private String name;

    public EntityAccount(long number, String name, Tariff tariff) {
        super(number, tariff);
        this.name = name;
    }

    public EntityAccount(long number, String name) {
        this(number, name, new EntityTariff(new Service()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
