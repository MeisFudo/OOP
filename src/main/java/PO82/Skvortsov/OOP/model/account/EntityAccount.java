package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.Person;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.tariff.EntityTariff;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.util.Objects;

public class EntityAccount extends AbstractAccount {

    private String name;

    public EntityAccount(long number, String name, Tariff tariff) {
        super(number, tariff);
        this.name = name;
    }

    public EntityAccount(long number, String name) {
        this(number, name, new EntityTariff(new Service()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity account:%1$s entity: %2$s%1$s%3$s", System.lineSeparator(), name, super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityAccount that = (EntityAccount) o;
        return Objects.equals(name, that.getName()) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode() * name.hashCode();
    }
}
