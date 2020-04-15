package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;

public interface Tariff extends Cloneable {

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

    Service[] getServices(ServiceTypes serviceType);

    Service[] sortedServicesByCost();

    double cost();

    Boolean remove(Service service);

    int indexOf(Service service);

    int lastIndexOf(Service service);

    String toString();

    int hashCode();

    boolean equals(Object obj);

    Tariff clone() throws CloneNotSupportedException;

}
