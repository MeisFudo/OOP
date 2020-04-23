package PO82.Skvortsov.OOP.model;


import PO82.Skvortsov.OOP.model.account.EntityAccount;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.manager.AccountsManager;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        lab5tests();
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
        }catch (Exception e) {
            System.out.println("IllegalArgumentException");
        }
        try {
            AccountsManager accountsManager = new AccountsManager(new IndividualAccount(1000000000002L, new Person("fName", "sName")),
                    new EntityAccount(1000000000001L, "name"));
            accountsManager.add(new EntityAccount(1000000000001L, "name"));
        }catch (Exception e){
            System.out.println("DublicateAccountNumberException");
        }
        try {
            AccountsManager accountsManager = new AccountsManager(new IndividualAccount(1000000000002L, new Person("fName", "sName")),
                    new EntityAccount(1000000000001L, "name"));
            accountsManager.add(null);
        }catch (Exception e){
            System.out.println("null");
        }
    }

    private static void print(Service... services) {
        for (Service service : services) {
            System.out.println(service.getName());
        }
    }

}
