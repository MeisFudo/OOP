package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.tariff.EntityTariff;

public class Test {
    public static void main(String[] args) {
        EntityTariff tariff;
        Service[] services = new Service[4];
        for (int i = 0; i < services.length; i++){
            services[i] = new Service("name"+i,10+i);
        }
        tariff = new EntityTariff(services);
        tariff.set(new Service("Name",100),0);
        tariff.add(services[3],1);
        tariff.remove(1);
    }
}
