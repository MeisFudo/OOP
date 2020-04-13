package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.tariff.Tariff;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;

    protected AbstractAccount(long number, Tariff tariff) {
        this.number = number;
        this.tariff = tariff;
    }

    public long getNumber(){
        return number;
    }

    public Tariff getTariff(){
        return tariff;
    }

    public void setTariff(Tariff tariff){
        this.tariff = tariff;
    }
}
