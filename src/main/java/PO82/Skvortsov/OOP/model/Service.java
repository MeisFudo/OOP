package PO82.Skvortsov.OOP.model;

public final class Service {
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
        this(DEFAULT_NAME,DEFAULT_PRICE, ServiceTypes.INTERNET);
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

//    public void setName(String name) {
//        this.name = name;
//    }

//    public void setCost(double cost) {
//        this.cost = cost;
//    }
}
