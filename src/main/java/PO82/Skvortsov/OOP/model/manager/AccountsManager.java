package PO82.Skvortsov.OOP.model.manager;

import PO82.Skvortsov.OOP.model.DublicateAccountNumberException;
import PO82.Skvortsov.OOP.model.IllegalAccountNumber;
import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;
import PO82.Skvortsov.OOP.model.account.Account;
import PO82.Skvortsov.OOP.model.account.EntityAccount;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.tariff.Tariff;

import java.util.*;

public class AccountsManager implements Iterable<Account> {
    private static final int SIZE_FACTOR = 2;
    private static final int SERVICE_CHARGE = 50;

    private Account[] accounts;
    private int size;


    public AccountsManager(int size) {
        accounts = new Account[size];
    }

    public AccountsManager(Account... accounts) {
        checkIsNull(accounts);
        this.accounts = accounts;
        size = accounts.length;
    }

    public Account get(int index) {
        return accounts[index];
    }

    public Account[] getAccounts() {
        Account[] accounts = new Account[size];
        int count = 0;
        for (Account account : AccountsManager.this) {
            if (account != null) {
                accounts[count] = account;
                count++;
            }
        }
        return accounts;
    }

    public Set<Account> getAccounts(String serviceName) {
        checkIsNull(serviceName);
        HashSet<Account> accounts = new HashSet<>();
        for (Account account : AccountsManager.this) {
            if (checkServiceName(account.getTariff(), serviceName)) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    private boolean checkServiceName(Tariff tariff, String name) {
        for (Service service : tariff.toArray()) {
            if (service.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Set<Account> getAccounts(ServiceTypes serviceType) {
        checkIsNull(serviceType);
        HashSet<Account> accounts = new HashSet<>();
        for (Account account : AccountsManager.this) {
            if (checkServiceType(account.getTariff(), serviceType)) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    private boolean checkServiceType(Tariff tariff, ServiceTypes type) {
        for (Service service : tariff.toArray()) {
            if (service.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public List<Account> getEntityAccount() {
        LinkedList<Account> accounts = new LinkedList<>();
        for (Account account : AccountsManager.this) {
            if (account.getClass() == EntityAccount.class) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    public List<Account> getIndividualAccount() {
        LinkedList<Account> accounts = new LinkedList<>();
        for (Account account : AccountsManager.this) {
            if (account.getClass() == IndividualAccount.class) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    public Account getAccount(long accountNumber) {
        if (accountNumber < 1000000000001L || accountNumber > 999999999999999L) {
            throw new IllegalAccountNumber();
        }
        for (Account account : AccountsManager.this) {
            if (account != null && accountNumber == account.getNumber()) {
                return account;
            }
        }
        throw new NoSuchElementException();
    }

    public Tariff getTariff(long accountNumber) {
        if (accountNumber < 1000000000001L || accountNumber > 999999999999999L) {
            throw new IllegalAccountNumber();
        }
        for (Account account : AccountsManager.this) {
            if (account != null && accountNumber == account.getNumber()) {
                return account.getTariff();
            }
        }
        throw new NoSuchElementException();
    }

    public Tariff setTariff(long accountNumber, Tariff tariff) {
        checkIsNull(tariff);
        if (accountNumber < 1000000000001L || accountNumber > 999999999999999L) {
            throw new IllegalAccountNumber();
        }
        for (Account account : AccountsManager.this) {
            if (account != null && accountNumber == account.getNumber()) {
                Tariff currentTariff = account.getTariff();
                account.setTariff(tariff);
                return currentTariff;
            }
        }
        throw new NoSuchElementException();
    }

    public Account set(Account account, int index) throws DublicateAccountNumberException {
        checkAccountNumber(account.getNumber());
        checkIsNull(account);
        checkIndex(index);
        Account currentAccount = accounts[index];
        accounts[index] = account;
        if (currentAccount == null) {
            size++;
        }
        return currentAccount;
    }

    public int size() {
        return size;
    }

    public boolean add(Account account, int index) throws DublicateAccountNumberException {
        checkAccountNumber(account.getNumber());
        checkIsNull(account);
        checkIndex(index);
        if (index >= accounts.length) {
            return false;
        }
        if (size >= accounts.length) {
            grow();
        }
        System.arraycopy(this.accounts, index, accounts, index + 1, this.accounts.length - (index + 1));
        accounts[index] = account;
        size++;
        return true;
    }

    public boolean add(Account account) throws DublicateAccountNumberException {
        checkAccountNumber(account.getNumber());
        checkIsNull(account);
        accounts[nullIndex()] = account;
        size++;
        return true;
    }

    private void checkAccountNumber(long accountNumber) throws DublicateAccountNumberException {
        for (Account account : AccountsManager.this) {
            if (account.getNumber() == accountNumber){
                throw new DublicateAccountNumberException();
            }
        }
    }

    private int nullIndex() {
        int i;
        for (i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                return i;
            }
        }
        grow();
        return ++i;
    }

    private void grow() {
        Account[] accounts = new Account[this.accounts.length * SIZE_FACTOR];
        System.arraycopy(this.accounts, 0, accounts, 0, this.accounts.length);
        this.accounts = accounts;
    }

    public Account remove(int index) {
        checkIndex(index);
        return shift(index);
    }

    private Account shift(int index) {
        checkIndex(index);
        if (index >= accounts.length) {
            return null;
        }
        Account removeAccount = accounts[index];
        accounts[index] = null;
        if (size - 1 - index >= 0) {
            System.arraycopy(accounts, index + 1, accounts, index, size - 1 - index);
            accounts[size - 1] = null;
        }
        size--;
        return removeAccount;
    }

    public boolean remove(Account account) {
        checkIsNull(account);
        return this.remove(this.indexOf(account)) != null;
    }

    public int indexOf(Account account) {
        checkIsNull(account);
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
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
        for (Account account : accounts) {
            if (account != null) {
                result.append(account).append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    private class AccountIterator implements Iterator<Account> {
        int cursor;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Account next() {
            int i = cursor;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return accounts[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            AccountsManager.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}
