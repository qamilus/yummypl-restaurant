package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;

/**
 * Testy jednostkowe dla <code>{@link LocationDao}</code>.
 *
 * @author Kamil Swislowski
 */
public class LocationDaoTest {
    private LocationDao locationDao;

    /**
     * Metoda wywoływana przez bibliotekę JUnit przed każdą metodą oznaczoną <code>@Test</code>.
     */
    @Before
    public void setUp() {
        locationDao = new LocationDao();
    }

    /**
     * Metoda wywoływana przez bibliotekę JUnit po każdej metodzie oznaczonej <code>@Test</code>.
     */
    @After
    public void tearDown() {
        locationDao = null;
    }

    /**
     * Test jednostkowy weryfikujący tworzenie i zapisywanie do tabeli LOCATIONS.
     */
    @Test
    public void create() {
        Location location = new Location("Warszawa - Test", "Marszałkowska - Test", "22 - Test");
        long locationId = locationDao.create(location);
        Assert.assertTrue("Nie dodano location do bazy.", locationId > -1);
    }
}