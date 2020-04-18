package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.time.LocalDate;

public interface Account {

    long getNumber();

    Tariff getTariff();

    void setTariff(Tariff tariff);

    LocalDate getRegistrationDate();
}
