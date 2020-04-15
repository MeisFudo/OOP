package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;

import java.util.Arrays;
import java.util.LinkedList;

public class IndividualsTariff implements Tariff {
    private static final int SIZE_FACTOR = 2;
    private static final int SERVICE_CHARGE = 50;

    private Service[] services;
    private int size;

    public IndividualsTariff() {
        services = new Service[8];
    }

    public IndividualsTariff(int size) {
        services = new Service[size];
    }

    public IndividualsTariff(Service... services) {
        this.services = services;
        size = services.length;
    }

    public Service get(int index) {
        return services[index];
    }

    public Service get(String name) {
        for (Service service : services) {
            if (service.getName().equals(name)) {
                return service;
            }
        }
        return null;
    }

    public Service[] getServices() {
        Service[] services = new Service[size];
        for (int i = 0, count = 0; i < this.services.length; i++) {
            if (this.services[i] != null) {
                services[count] = this.services[i];
                count++;
            }
        }
        return services;
    }

    public Service[] getServices(ServiceTypes type) {
        LinkedList<Service> services = new LinkedList<>();
        for (Service service : this.services) {
            if (service != null && service.getType() == type) {
                services.add(service);
            }
        }
        return services.toArray(new Service[0]);
    }


    public Service set(Service service, int index) {
        Service currentService = services[index];
        services[index] = service;
        if (currentService == null) {
            size++;
        }
        return currentService;
    }

    public int size() {
        return size;
    }

    public boolean hasService(String serviceName) {
        for (Service service : services) {
            if (service != null && service.getName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }


    public boolean add(Service service, int index) {
        if (index >= services.length) {
            return false;
        }
        if (size >= services.length) {
            grow();
        }
        System.arraycopy(this.services, index, services, index + 1, this.services.length - (index + 1));
        services[index] = service;
        size++;
        return true;
    }

    public boolean add(Service service) {
        services[nullIndex()] = service;
        size++;
        return true;
    }

    private int nullIndex() {
        int i;
        for (i = 0; i < services.length; i++) {
            if (services[i] == null) {
                return i;
            }
        }
        grow();
        return ++i;
    }

    private void grow() {
        Service[] services = new Service[this.services.length * SIZE_FACTOR];
        System.arraycopy(this.services, 0, services, 0, this.services.length);
        this.services = services;
    }

    public Service remove(int index) {
        return shift(index);
    }

    public Service remove(String serviceName) {
        for (int i = 0; i < size; i++) {
            if (services[i].getName().equals(serviceName)) {
                return shift(i);
            }
        }
        return null;
    }

    private Service shift(int index) {
        if (index >= services.length) {
            return null;
        }
        Service removeService = services[index];
        services[index] = null;
        if (size - 1 - index >= 0) {
            System.arraycopy(services, index + 1, services, index, size - 1 - index);
            services[size - 1] = null;
        }
        size--;
        return removeService;
    }

    public Service[] sortedServicesByCost() {
        Service[] sortedService = getServices();
        for (int i = sortedService.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sortedService[j].getCost() > sortedService[j + 1].getCost()) {
                    Service currentService = sortedService[j];
                    sortedService[j] = sortedService[j + 1];
                    sortedService[j + 1] = currentService;
                }
            }
        }
        return sortedService;
    }

    public double cost() {
        double cost = SERVICE_CHARGE;
        for (Service service : services) {
            if (service != null) {
                cost += service.getCost();
            }
        }
        return cost;
    }

    @Override
    public Boolean remove(Service service) {
        return  this.remove(this.indexOf(service)) != null;
    }

    @Override
    public int indexOf(Service service) {
        for (int i = 0; i < services.length;i++) {
            if (services[i] != null && services[i].equals(service)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Service service) {
        for (int i = services.length - 1; i >= 0; i--) {
            if (services[i] != null && services[i].equals(service)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Service service: this.getServices()) {
            result.append(service.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public int hashCode(){
        int hashCode = 31;
        for (Service service: this.getServices()) {
            hashCode *= service.hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualsTariff that = (IndividualsTariff) o;
        return size == that.size &&
                Arrays.equals(services, that.services);
    }


    @Override
    public Tariff clone() throws CloneNotSupportedException{
        return (Tariff) super.clone();
    }
}
