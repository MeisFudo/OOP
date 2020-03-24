package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.account.EntityAccount;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.manager.AccountsManager;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;

public class Test {
    public static void main(String[] args) {
        EntityTariff entityTariff;
        Service[] services = new Service[4];
        for (int i = 0; i < services.length; i++){
            services[i] = new Service("name"+i,10+i);
        }
        entityTariff = new EntityTariff(services);
        entityTariff.set(new Service("Name",100),0);
        entityTariff.add(services[3],1);
        entityTariff.remove(1);

        IndividualsTariff individualsTariff;
        individualsTariff = new IndividualsTariff(services);
        individualsTariff.set(new Service("Name",100),0);
        individualsTariff.add(services[3],1);
        individualsTariff.remove(1);

        IndividualAccount individualAccount = new IndividualAccount(1, new Person("fName","sName"), individualsTariff);
        EntityAccount entityAccount = new EntityAccount(1, "Name", entityTariff);

        AccountsManager accountsManager = new AccountsManager(individualAccount, entityAccount);
    }
}
