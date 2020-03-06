package PO82.Skvortsov.OOP.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {

    private static final String NAME = "name";

    private AccountManager accountManager;
    private Account[] accounts;
    private Person person;

    @Before
    public void setUp() {
        accountManager = new AccountManager(6);
        accounts = new Account[4];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, new Person("fName" + i, "sName" + i));
            accountManager.add(accounts[i]);
        }
    }

    @Test
    public void getAccounts() {
        for (Account currentAccount : accountManager.getAccounts()) {
            assertNotNull(currentAccount);
        }
    }

    @Test
    public void setTariff() {
        assertEquals(accountManager.getTariff(1) ,accountManager.setTariff(1,new IndividualsTariff()));
    }

    @Test
    public void set() {
        assertEquals(accounts[0], accountManager.set(
                new Account(5, new Person("fName" + 5, "sName" + 5)), 0));
        assertEquals(4, accountManager.size());

        assertNull(accountManager.set(
                new Account(5, new Person("fName" + 5, "sName" + 5)), 5));
        assertEquals(5, accountManager.size());
    }

    @Test
    public void add() {
        assertTrue(accountManager.add(accounts[1]));
        assertEquals(accountManager.size(),5);
    }

    @Test
    public void testAdd() {
        assertTrue(accountManager.add(accounts[0],1));
        assertEquals(accountManager.size(),5);
        assertEquals(accountManager.get(1),accountManager.get(0));
    }

    @Test
    public void remove() {
        assertEquals(accountManager.remove(3),accounts[3]);
        assertEquals(3,accountManager.size());
    }
}