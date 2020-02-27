package PO82.Skvortsov.OOP.model;

public class IndividualsTariff {
    private static final int SIZE_FACTOR = 2;

    private Service[] services;
    private int numberService;

    public IndividualsTariff() {
        services = new Service[8];
    }

    public IndividualsTariff(int size) {
        services = new Service[size];
    }

    public IndividualsTariff(Service[] services) {
        this.services = services;
        numberService = services.length;
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

    public void set(Service service, int index) {
        services[index] = service;
    }

    public boolean hasServices(String name) {
        for (Service service : services) {
            if (service.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addService(Service service) {
        ensureCapacity();
        services[numberService] = service;
        numberService++;
        return true;
    }

    public boolean addService(Service service, int index) {
        if (index >= services.length) {
            return false;
        }
        services[index] = service;
        return true;
    }

    private void ensureCapacity() {
        if (numberService >= services.length) {
            grow();
        }
    }

    private void grow() {
        Service[] services = new Service[this.services.length * SIZE_FACTOR];
        System.arraycopy(this.services, 0, services, 0, this.services.length);
        this.services = services;
    }

    public Service remove(int index) {
        return shift(index);
    }

    public Service remove(String name){
        for (int i = 0; i < numberService; i++) {
            if (services[i].getName().equals(name)) {
                return remove(i);
            }
        }
        return null;
    }

    private Service shift(int index) {
        if (index >= services.length){
            return null;
        }
        Service removeService = services[index];
        for (int i = index; i < numberService - 1; i++) {
            services[i] = services[i + 1];
        }
        return removeService;
    }
}
