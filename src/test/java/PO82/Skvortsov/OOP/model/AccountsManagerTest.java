package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.account.Account;
import PO82.Skvortsov.OOP.model.account.IndividualAccount;
import PO82.Skvortsov.OOP.model.manager.AccountsManager;
import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountsManagerTest {

    private static final String NAME = "name";

    private AccountsManager accountsManager;
    private IndividualAccount[] accounts;
    private Person person;

    @Before
    public void setUp() {
        accountsManager = new AccountsManager(6);
        accounts = new IndividualAccount[4];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new IndividualAccount(i, new Person("fName" + i, "sName" + i));
            accountsManager.add(accounts[i]);
        }
    }

    @Test
    public void getAccounts() {
        for (Account currentAccount : accountsManager.getAccounts()) {
            assertNotNull(currentAccount);
        }
    }

    @Test
    public void setTariff() {
        assertEquals(accountsManager.getTariff(1) , accountsManager.setTariff(1,new IndividualsTariff()));
    }

    @Test
    public void set() {
        assertEquals(accounts[0], accountsManager.set(
                new IndividualAccount(5, new Person("fName" + 5, "sName" + 5)), 0));
        assertEquals(4, accountsManager.size());

        assertNull(accountsManager.set(
                new IndividualAccount(5, new Person("fName" + 5, "sName" + 5)), 5));
        assertEquals(5, accountsManager.size());
    }

    @Test
    public void add() {
        assertTrue(accountsManager.add(accounts[1]));
        assertEquals(accountsManager.size(),5);
    }

    @Test
    public void testAdd() {
        assertTrue(accountsManager.add(accounts[0],1));
        assertEquals(accountsManager.size(),5);
        assertEquals(accountsManager.get(1), accountsManager.get(0));
    }

    @Test
    public void remove() {
        assertEquals(accountsManager.remove(3),accounts[3]);
        assertEquals(3, accountsManager.size());
    }
}