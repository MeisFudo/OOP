package PO82.Skvortsov.OOP.model.account;

import PO82.Skvortsov.OOP.model.IllegalAccountNumber;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;
    private LocalDate registrationDate;

    protected AbstractAccount(long number, Tariff tariff, LocalDate registrationDate) {
        if (Objects.isNull(tariff) || Objects.isNull(registrationDate)) {
            throw new NullPointerException();
        }
        else if(registrationDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException();
        }
        if (number < 1000000000001L || number > 999999999999999L) {
            throw new IllegalAccountNumber();
        }

        this.number = number;
        this.tariff = tariff;
        this.registrationDate = registrationDate;
    }

    public long getNumber(){
        return number;
    }

    public Tariff getTariff(){
        return tariff;
    }

    public LocalDate getRegistrationDate(){
        return registrationDate;
    }

    public void setTariff(Tariff tariff){
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return String.format("number: %2$d%1$sRegistration date: %4$s%1$sservices:%1$s%3$s", System.lineSeparator(), number,  tariff.toString(), registrationDate.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return number == that.number &&
                Objects.equals(tariff, that.tariff) &&
                Objects.equals(registrationDate, that.registrationDate);
    }

    @Override
    public int hashCode() {
        return (int) number *  tariff.hashCode() * registrationDate.hashCode();
    }

    @Override
    protected Account clone() throws CloneNotSupportedException {
        return (Account) super.clone();
    }
}
