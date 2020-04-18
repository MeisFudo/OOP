package PO82.Skvortsov.OOP.model;


import PO82.Skvortsov.OOP.model.account.IndividualAccount;

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
        IndividualAccount individualAccount = new IndividualAccount(1, new Person("fName","sName"));
        System.out.println(individualAccount.toString());
    }

    private static void print(Service... services) {
        for (Service service : services) {
            System.out.println(service.getName());
        }
    }

}
