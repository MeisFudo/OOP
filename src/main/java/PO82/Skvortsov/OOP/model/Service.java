package PO82.Skvortsov.OOP.model;

import java.util.Objects;

public final class Service implements Cloneable {
    private static final String DEFAULT_NAME = "Internet 100MB\\sec";
    private static final int DEFAULT_PRICE = 300;

    private final String name;
    private final double cost;
    private final ServiceTypes type;

    public Service(String name, int cost, ServiceTypes type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Service() {
        this(DEFAULT_NAME, DEFAULT_PRICE, ServiceTypes.INTERNET);
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

    @Override
    public String toString() {
        return String.format("%.40s\\\\%.2fр.", name, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(service.cost, cost) == 0 &&
                Objects.equals(name, service.name) &&
                type == service.type;
    }

    @Override
    public int hashCode() {
        return (int) (name.hashCode() * cost * type.hashCode());
    }

    @Override
    public Service clone() throws CloneNotSupportedException{
        return (Service) super.clone();
    }
}
