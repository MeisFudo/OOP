package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.account.EntityAccount;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.manager.AccountsManager;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

public class Test {
    public static void main(String[] args) {
        //lab2Tests();
        //lab3Tests();
        lab4Tests();
    }

    private static void lab2Tests() {
        EntityTariff entityTariff;
        Service[] services = new Service[4];
        for (int i = 0; i < services.length; i++) {
            services[i] = new Service("name" + i, 10 + i, ServiceTypes.PHONE);
        }
        entityTariff = new EntityTariff(services);
        entityTariff.set(new Service("Name", 100, ServiceTypes.PHONE), 0);
        entityTariff.add(services[3], 1);
        entityTariff.remove(1);

        IndividualsTariff individualsTariff;
        individualsTariff = new IndividualsTariff(services);
        individualsTariff.set(new Service("Name", 100, ServiceTypes.PHONE), 0);
        individualsTariff.add(services[3], 1);
        individualsTariff.remove(1);

        IndividualAccount individualAccount = new IndividualAccount(1, new Person("fName", "sName"), individualsTariff);
        EntityAccount entityAccount = new EntityAccount(1, "Name", entityTariff);

        AccountsManager accountsManager = new AccountsManager(individualAccount, entityAccount);
    }

    private static void lab3Tests() {
        EntityTariff entityTariff;
        Service[] services = new Service[4];
        for (int i = 0; i < services.length; i++) {
            services[i] = new Service("name" + i, 10 + i, i % 2 == 0 ? ServiceTypes.PHONE : ServiceTypes.INTERNET);
        }
        entityTariff = new EntityTariff(services);
        entityTariff.set(new Service("Name", 100, ServiceTypes.PHONE), 0);
        entityTariff.add(services[3], 1);
        entityTariff.remove(1);
        print(entityTariff.getServices(ServiceTypes.PHONE));

        IndividualsTariff individualsTariff;
        individualsTariff = new IndividualsTariff(services);
        individualsTariff.set(new Service("Name", 100, ServiceTypes.PHONE), 0);
        individualsTariff.add(services[3], 1);
        individualsTariff.remove(1);
        print(individualsTariff.getServices(ServiceTypes.PHONE));

        IndividualAccount individualAccount = new IndividualAccount(0, new Person("fName", "sName"), individualsTariff);
        EntityAccount entityAccount = new EntityAccount(1, "Name", entityTariff);

        AccountsManager accountsManager = new AccountsManager(individualAccount, entityAccount);
        accountsManager.getIndividualAccount();
        accountsManager.getEntityAccount();
    }

    private static void lab4Tests() {
        Service[] services = new Service[4];
        for (int i = 0; i < services.length; i++) {
            services[i] = new Service("name" + i, 10 + i, i % 2 == 0 ? ServiceTypes.PHONE : ServiceTypes.INTERNET);
        }
        System.out.println(services[0].toString());

        System.out.println("Тест клонирования:" + System.lineSeparator());
        try {
            Service cloneService = services[0].clone();
            System.out.println(services[0] == cloneService);
            System.out.println(services[0].equals(cloneService));
        }
        catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }


        EntityTariff entityTariff = new EntityTariff(services);
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        try {
            EntityTariff cloneEntityTariff = (EntityTariff) entityTariff.clone();
            System.out.println(cloneEntityTariff == entityTariff);
            System.out.println(cloneEntityTariff.equals(entityTariff));
        }
        catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }


        IndividualAccount individualAccount = new IndividualAccount(0, new Person("fName", "sName"), individualsTariff);
        EntityAccount entityAccount = new EntityAccount(1, "Name", entityTariff);
        System.out.println(System.lineSeparator());
        System.out.println(individualAccount.toString());
        System.out.println(System.lineSeparator());
        System.out.println(entityAccount.toString());

        System.out.println(System.lineSeparator());

        System.out.println(entityAccount.equals(new EntityAccount(1, "Name", entityTariff)));

        AccountsManager accountsManager = new AccountsManager(individualAccount, entityAccount);
        System.out.println(accountsManager.toString());
        System.out.println(String.format("%d %d",entityTariff.lastIndexOf(services[1]), individualsTariff.lastIndexOf(services[0])));
    }

    private static void print(Service... services) {
        for (Service service : services) {
            System.out.println(service.getName());
        }
    }

}
