package PO82.Skvortsov.OOP.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Service implements Cloneable, Comparable<Service> {
    private static final String DEFAULT_NAME = "Internet 100MB\\sec";
    private static final int DEFAULT_PRICE = 300;

    private final String name;
    private final double cost;
    private final ServiceTypes type;
    private LocalDate activationDate;

    public Service(String name, double cost, ServiceTypes type, LocalDate activationDate) {
        if (Objects.isNull(name) || Objects.isNull(type) || Objects.isNull(activationDate)) {
            throw new NullPointerException();
        }
        else if( cost < 0 || activationDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.activationDate = activationDate;
    }

    public Service() {
        this(DEFAULT_NAME, DEFAULT_PRICE, ServiceTypes.INTERNET, LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public ServiceTypes getType() {
        return type;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    @Override
    public String toString() {
        return String.format("%.40s\\\\%.2fр.\\\\%s", name, cost, activationDate.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(service.cost, cost) == 0 &&
                Objects.equals(name, service.name) &&
                type == service.type &&
                activationDate.equals(service.activationDate);
    }

    @Override
    public int hashCode() {
        return (int) (name.hashCode() * cost * type.hashCode() * activationDate.hashCode());
    }

    @Override
    public Service clone() throws CloneNotSupportedException {
        return (Service) super.clone();
    }

    @Override
    public int compareTo(Service anotherService) {
        return Double.compare(this.getCost(), anotherService.getCost());
    }
}
