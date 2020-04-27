package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Consumer;

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
        checkIsNull(services);
        this.services = services;
        size = services.length;
    }

    public Service get(int index) {
        checkIndex(index);
        return services[index];
    }

    public Service set(Service service, int index) {
        checkIsNull(service);
        checkIndex(index);
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

    public boolean add(Service service, int index) {
        checkIsNull(service);
        checkIndex(index);
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
        checkIsNull(service);
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
        checkIndex(index);
        return shift(index);
    }

    public Service remove(String serviceName) {
        checkIsNull(serviceName);
        for (int i = 0; i < size; i++) {
            if (services[i].getName().equals(serviceName)) {
                return shift(i);
            }
        }
        throw new NoSuchElementException();
    }

    private Service shift(int index) {
        checkIndex(index);
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

    @Override
    public Boolean remove(Service service) {
        checkIsNull(service);
        return this.remove(this.indexOf(service)) != null;
    }

    private void checkIsNull(Object o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Service service : this.getServices()) {
            result.append(service.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        for (Service service : this.getServices()) {
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
    public Tariff clone() throws CloneNotSupportedException {
        return (Tariff) super.clone();
    }

    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    private class ServiceIterator implements Iterator<Service> {
        int cursor;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Service next() {
            int i = cursor;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return services[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            IndividualsTariff.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}
