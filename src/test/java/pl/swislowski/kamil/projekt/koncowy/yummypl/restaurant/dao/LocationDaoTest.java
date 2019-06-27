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

    @Before
    public void setUp() {
        locationDao = new LocationDao();
    }

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