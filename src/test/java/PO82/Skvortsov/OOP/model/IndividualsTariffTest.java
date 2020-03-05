package PO82.Skvortsov.OOP.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IndividualsTariffTest {

    private static final double DELTA = 0.1;
    private static final String NAME = "name";

    private IndividualsTariff tariff;
    private IndividualsTariff tariff2;
    private Service[] services;

    @Before
    public void setUp() throws Exception {
        services = new Service[4];
        for (int i = 0; i < services.length; i++) {
            services[i] = new Service(NAME + (i + 1), (i + 1) * 10);
        }
        tariff = new IndividualsTariff(services);
        tariff2 = new IndividualsTariff();
        for (int i = services.length - 1; i >= 0; i--) {
            tariff2.add(services[i]);
        }
    }

    @Test
    public void getServices() {
        Service[] currentServices = tariff2.getServices();
        for (Service currentService : currentServices) {
            Assert.assertNotNull(currentService);
        }
    }

    @Test
    public void set() {
        Assert.assertEquals(services[0], tariff2.set(new Service(NAME, 10), 3));
        Assert.assertEquals(4, tariff2.size());
        Assert.assertTrue(tariff2.hasServices(NAME));
    }

    @Test
    public void add() {
    }

    @Test
    public void testAdd() {
        Assert.assertTrue(tariff2.add(new Service(NAME, 20)));
        Assert.assertEquals(tariff2.size(), 5);
        Assert.assertTrue(tariff2.hasServices(NAME));
    }

    @Test
    public void remove() {
        Assert.assertEquals(tariff2.remove(3), services[0]);
        Assert.assertEquals(3, tariff2.size());
        Assert.assertFalse(tariff2.hasServices(NAME + 1));
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(tariff2.remove(NAME + 1), services[0]);
        Assert.assertEquals(3, tariff2.size());
        Assert.assertFalse(tariff2.hasServices(NAME + 1));
    }

    @Test
    public void sortedServiceByCost() {
        Assert.assertArrayEquals(tariff2.sortedServiceByCost(), tariff.getServices());
    }

    @Test
    public void cost() {
        Assert.assertEquals(tariff2.cost(), 150, DELTA);
    }
}