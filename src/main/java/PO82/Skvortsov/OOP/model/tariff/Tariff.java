package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public interface Tariff extends Cloneable, Iterable<Service> {
    static final int SERVICE_CHARGE = 50;

    boolean add(Service service);

    boolean add(Service service, int index);

    Service get(int index);

    default Service get(String serviceName) {
        for (Service service : Tariff.this) {
            if (service.getName().equals(serviceName)) {
                return service;
            }
        }
        return null;
    }

    default boolean hasService(String serviceName) {
        for (Service service : Tariff.this) {
            if (service.getName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    Service set(Service service, int index);

    Service remove(int index);

    Service remove(String serviceName);

    int size();

    default Service[] getServices() {
        Service[] services = new Service[this.size()];
        int index = 0;
        for (Service service : Tariff.this) {
            services[index] = service;
            index++;
        }
        return services;
    }

    default Service[] getServices(ServiceTypes serviceType) {
        ArrayList<Service> services = new ArrayList<>();
        for (Service service : Tariff.this) {
            if (service.getType().equals(serviceType)) {
                services.add(service);
            }
        }
        return (Service[]) services.toArray();
    }

    default Service[] sortedServicesByCost() {
        Service[] sortedService = getServices();
        Arrays.sort(sortedService);
        return sortedService;
    }

    default double cost() {
        double cost = SERVICE_CHARGE;
        for (Service service : Tariff.this) {
            if (service != null) {
                if (Period.between(service.getActivationDate(), LocalDate.now()).getMonths() < 1) {
                    cost += service.getCost() *
                            Period.between(service.getActivationDate(), LocalDate.now()).getDays() /
                            service.getActivationDate().lengthOfMonth();
                } else {
                    cost += service.getCost();
                }
            }
        }
        return cost;
    }

    Boolean remove(Service service);

    default int indexOf(Service service) {
        if (Objects.isNull(service)) {
            throw new NullPointerException();
        }
        int counter = 0;
        for (Service currentService : Tariff.this) {
            if (currentService != null && currentService.equals(service)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    default int lastIndexOf(Service service) {
        if (Objects.isNull(service)) {
            throw new NullPointerException();
        }
        int counter = 0;
        int result = -1;
        for (Service currentService : Tariff.this) {
            if (currentService != null && currentService.equals(service)) {
                result = counter;
            }
            counter++;
        }
        return result;
    }

    String toString();

    int hashCode();

    boolean equals(Object obj);

    Tariff clone() throws CloneNotSupportedException;

}
