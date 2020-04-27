package PO82.Skvortsov.OOP.model;


import PO82.Skvortsov.OOP.model.account.Account;
import PO82.Skvortsov.OOP.model.account.EntityAccount;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.manager.AccountsManager;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // lab5tests();
        lab6tests();
    }

    private static void lab5tests() {
        try {
            new Service("null", 10, ServiceTypes.PHONE, LocalDate.now().plusDays(1));
        } catch (Exception e) {
            System.out.println("Date");
        }
        try {
            new Service(null, 10, ServiceTypes.PHONE, LocalDate.now());
        } catch (Exception e) {
            System.out.println("null");
        }
        try {
            new IndividualAccount(1, new Person("fName", "sName"));
        } catch (Exception e) {
            System.out.println("IllegalArgumentException");
        }
        try {
            AccountsManager accountsManager = new AccountsManager(new IndividualAccount(1000000000002L, new Person("fName", "sName")),
                    new EntityAccount(1000000000001L, "name"));
            accountsManager.add(new EntityAccount(1000000000001L, "name"));
        } catch (Exception e) {
            System.out.println("DublicateAccountNumberException");
        }
        try {
            AccountsManager accountsManager = new AccountsManager(new IndividualAccount(1000000000002L, new Person("fName", "sName")),
                    new EntityAccount(1000000000001L, "name"));
            accountsManager.add(null);
        } catch (Exception e) {
            System.out.println("null");
        }
    }

    private static void lab6tests() {
        System.out.println(new Service("", 0.1, ServiceTypes.PHONE, LocalDate.now()).compareTo(new Service("", 0.2, ServiceTypes.PHONE, LocalDate.now())));
        Service[] services = new Service[4];
        for (int i = services.length - 1; i >= 0 ; i--){
            services[i] = new Service("name" + i, new Random().nextDouble() * 10, ServiceTypes.PHONE, LocalDate.now());
        }
        System.out.println("Сортировка entityTariff" + System.lineSeparator());
        EntityTariff entityTariff = new EntityTariff(services);
        print(entityTariff.getServices());
        System.out.println(System.lineSeparator());
        print(entityTariff.sortedServicesByCost());

        System.out.println("Сортировка individualsTariff" + System.lineSeparator());
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        print(individualsTariff.getServices());
        System.out.println(System.lineSeparator());
        print(individualsTariff.sortedServicesByCost());


        System.out.println(System.lineSeparator() + "individualsTariffIterator" );
        Iterator<Service> individualsTariffIterator = individualsTariff.iterator();
        while (individualsTariffIterator.hasNext())
        {
            Service item = individualsTariffIterator.next();
            System.out.println(item.toString());
        }

        System.out.println(System.lineSeparator() + "entityTariffIterator");
        Iterator<Service> entityTariffIterator = entityTariff.iterator();
        while (entityTariffIterator.hasNext())
        {
            Service item = entityTariffIterator.next();
            System.out.println(item.toString());
        }

        System.out.println(System.lineSeparator());
        for (Service item : individualsTariff) {
            System.out.println(item.toString());
        }
        System.out.println(System.lineSeparator());
        for (Service item : entityTariff) {
            System.out.println(item.toString());
        }

        System.out.println(entityTariff.get("name0").toString());
        print(individualsTariff.getServices());

        AccountsManager accountsManager = new AccountsManager(new IndividualAccount(1000000000002L, new Person("fName", "sName")),
                new EntityAccount(1000000000001L, "name"));
        System.out.println(System.lineSeparator() + "accountsManagerIterator");
        Iterator<Account> accountsManagerIterator = accountsManager.iterator();
        while (accountsManagerIterator.hasNext())
        {
            Account item = accountsManagerIterator.next();
            System.out.println(item.toString());
        }
    }


    private static void print(Service... services) {
        for (Service service : services) {
            System.out.println(service.getCost());
        }
    }

}
