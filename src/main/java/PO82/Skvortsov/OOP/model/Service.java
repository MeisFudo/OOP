package PO82.Skvortsov.OOP.model;

public class Service {
    private static final String DEFAULT_NAME = "интернет 100мб\\\\сек";
    private static final int DEFAULT_PRICE = 500;

    private String name;
    private int price;

    public Service(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Service() {
        this(DEFAULT_NAME,DEFAULT_PRICE);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
