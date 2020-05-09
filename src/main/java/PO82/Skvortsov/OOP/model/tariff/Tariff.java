package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public interface Tariff extends Cloneable, Iterable<Service>, Collection<Service> {
    static final int SERVICE_CHARGE = 50;

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean add(Service service);

    boolean add(Service service, int index);


    default boolean addAll(Collection<? extends Service> c) {
        if (Objects.isNull(c)) {
            throw new NullPointerException();
        }
        boolean result = false;
        for (Service service : c) {
            result = this.add(service);
        }
        return result;
    }

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

    default boolean contains(Object o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
        if (o.getClass() != Service.class) {
            throw new ClassCastException();
        }
        return indexOf((Service) o) >= 0;
    }

    default boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    Service set(Service service, int index);

    Service remove(int index);

    Service remove(String serviceName);

    Boolean remove(Service service);

    default boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size(); index++)
                if (get(index) == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size(); index++)
                if (o.equals(get(index))) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }

    default boolean removeAll(Collection<?> c) {
        if (Objects.isNull(c))
            throw new NullPointerException();
        boolean result = false;
        for (Object e : c) {
            if (e.getClass() == Service.class) {
                result = remove(e);
            } else {
                throw new ClassCastException();
            }
        }
        return result;
    }

    default boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (Object e : c) {
            if (contains(e)) {
                result = remove(e);
            }
        }
        return result;
    }

    default Service[] toArray() {
        Service[] services = new Service[this.size()];
        int index = 0;
        for (Service service : Tariff.this) {
            services[index] = service;
            index++;
        }
        return services;
    }

    default Collection<Service> toArray(ServiceTypes serviceType) {
        LinkedList<Service> services = new LinkedList<>();
        for (Service service : Tariff.this) {
            if (service.getType().equals(serviceType)) {
                services.add(service);
            }
        }
        return services;
    }

    @SuppressWarnings("unchecked")
    default <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(this.toArray(), size(), a.getClass());
        }
        System.arraycopy(this.toArray(), 0, a, 0, size());
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    default List<Service> sortedServicesByCost() {
        Service[] sortedService = toArray();
        Arrays.sort(sortedService);
        return new ArrayList<>(Arrays.asList(sortedService));
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
