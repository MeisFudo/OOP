package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;

public interface Tariff {

    boolean add(Service service);

    boolean add(Service service, int index);

    Service get(int index);

    Service get(String serviceName);

    boolean hasService(String serviceName);

    Service set(Service service, int index);

    Service remove(int index);

    Service remove(String serviceName);

    int size();

    Service[] getServices();

    Service[] sortedServicesByCost();

    double cost();
}
