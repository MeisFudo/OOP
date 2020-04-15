package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.util.Objects;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;

    protected AbstractAccount(long number, Tariff tariff) {
        this.number = number;
        this.tariff = tariff;
    }

    public long getNumber(){
        return number;
    }

    public Tariff getTariff(){
        return tariff;
    }

    public void setTariff(Tariff tariff){
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return String.format("number: %2$d%1$sservices:%1$s%3$s", System.lineSeparator(), number,  tariff.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return number == that.number &&
                Objects.equals(tariff, that.tariff);
    }

    @Override
    public int hashCode() {
        return (int) number *  tariff.hashCode();
    }

    @Override
    protected Account clone() throws CloneNotSupportedException {
        return (Account) super.clone();
    }
}
