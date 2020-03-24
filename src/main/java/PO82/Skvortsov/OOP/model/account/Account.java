package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.tariff.Tariff;

public interface Account {

    long getNumber();

    Tariff getTariff();

    void setTariff(Tariff tariff);
}
