package PO82.Skvortsov.OOP.model;

public class Service {
    private static final String DEFAULT_NAME = "Internet 100MB\\sec";
    private static final int DEFAULT_PRICE = 300;

    private String name;
    private double cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Service() {
        this(DEFAULT_NAME,DEFAULT_PRICE);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
