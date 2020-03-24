package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

public class EntityAccount implements Account {
    private long number;
    private Tariff tariff;
    private String name;

    public EntityAccount(long number, String name, Tariff tariff) {
        this.number = number;
        this.name = name;
        this.tariff = tariff;
    }

    public EntityAccount(long number, String name) {
        this(number,name,new EntityTariff(new Service()));
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public Tariff getTariff() {
        return tariff;
    }

    public String getName(){
        return name;
    }

    @Override
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public void setName(String name){
        this.name = name;
    }
}
