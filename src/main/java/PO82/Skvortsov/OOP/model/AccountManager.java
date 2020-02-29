package PO82.Skvortsov.OOP.model;

public class AccountManager {
    private static final int SIZE_FACTOR = 2;
    private static final int SERVICE_CHARGE = 50;

    private Account[] accounts;
    private int size;


    public AccountManager(int size) {
        accounts = new Account[size];
    }

    public AccountManager(Account... accounts) {
        this.accounts = accounts;
        size = accounts.length;
    }

    public Account get(int index) {
        return accounts[index];
    }


    public Account[] getAccounts() {
        Account[] accounts = new Account[size];
        int count = 0;
        for (Account account: accounts) {
            if (account != null){
                accounts[count] = account;
                count++;
            }
        }
        return accounts;
    }

    public IndividualsTariff getTariff(long accountNumber){
        for (Account account: accounts) {
            if (accountNumber == account.getNumber()){
                return account.getTariff();
            }
        }
        return null;
    }

    public IndividualsTariff setTariff(long accountNumber, IndividualsTariff tariff){
        for (int i = 0; i < accounts.length; i++) {
            if (accountNumber == accounts[i].getNumber()){
                IndividualsTariff currentTariff = accounts[i].getTariff();
                accounts[i].setTariff(tariff);
                return currentTariff;
            }
        }
        return null;
    }

    public Account set(Account account, int index) {
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

    public boolean add(Account account, int index) {
        if (index >= accounts.length) {
            return false;
        }
        if (accounts[index] == null) {
            size++;
        }
        accounts[index] = account;
        return true;
    }

    public boolean add(Account account) {
        accounts[nullIndex()] = account;
        size++;
        return true;
    }

    private int nullIndex(){
        int i;
        for (i = 0; i < accounts.length; i++){
            if (accounts[i] == null){
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
        return shift(index);
    }

    private Account shift(int index) {
        if (index >= accounts.length) {
            return null;
        }
        Account removeAccount = accounts[index];
        for (int i = index; i < size - 1; i++) {
            accounts[i] = accounts[i + 1];
        }
        size--;
        return removeAccount;
    }
}
