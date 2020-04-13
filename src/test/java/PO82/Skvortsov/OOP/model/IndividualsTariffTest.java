package PO82.Skvortsov.OOP.model;

import PO82.Skvortsov.OOP.model.tariff.IndividualsTariff;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualsTariffTest {

    private static final double DELTA = 0.1;
    private static final String NAME = "name";
    private static final ServiceTypes serviceTypes = ServiceTypes.PHONE;

    private IndividualsTariff tariff;
    private Service[] services;

    @Before
    public void setUp() {
        services = new Service[4];
        for (int i = 0; i < services.length; i++) {
            services[i] = new Service(NAME + (i + 1), (i + 1) * 10, serviceTypes);
        }
        tariff = new IndividualsTariff();
        for (int i = services.length - 1; i >= 0; i--) {
            tariff.add(services[i]);
        }
    }

    @Test
    public void getServices() {
        Service[] currentServices = tariff.getServices();
        for (Service currentService : currentServices) {
            assertNotNull(currentService);
        }
    }

    @Test
    public void set() {
        assertEquals(services[0], tariff.set(new Service(NAME, 10, serviceTypes), 3));
        assertEquals(4, tariff.size());
        assertTrue(tariff.hasService(NAME));

        assertNull(tariff.set(new Service(NAME, 10, serviceTypes), 5));
        assertEquals(5, tariff.size());
        assertTrue(tariff.hasService(NAME));
    }

    @Test
    public void add() {
        assertTrue(tariff.add(new Service(NAME, 20, serviceTypes), 2));
        assertEquals(tariff.size(), 5);
        assertTrue(tariff.hasService(NAME));
    }

    @Test
    public void testAdd() {
        assertTrue(tariff.add(new Service(NAME, 20, serviceTypes)));
        assertEquals(tariff.size(), 5);
        assertTrue(tariff.hasService(NAME));
    }

    @Test
    public void remove() {
        assertEquals(tariff.remove(3), services[0]);
        assertEquals(3, tariff.size());
        assertFalse(tariff.hasService(NAME + 1));
    }

    @Test
    public void testRemove() {
        assertEquals(tariff.remove(NAME + 1), services[0]);
        assertEquals(3, tariff.size());
        assertFalse(tariff.hasService(NAME + 1));
    }

    @Test
    public void sortedServicesByCost() {
        assertArrayEquals(tariff.sortedServicesByCost(), services);
    }

    @Test
    public void cost() {
        assertEquals(tariff.cost(), 150, DELTA);
    }
}