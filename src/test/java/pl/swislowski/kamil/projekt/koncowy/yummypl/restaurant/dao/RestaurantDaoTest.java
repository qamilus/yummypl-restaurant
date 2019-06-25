package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;

public class RestaurantDaoTest {

    private LocationDao locationDao;
    private RestaurantDao restaurantDao;

    @Before
    public void setUp() {
        locationDao = new LocationDao();
        restaurantDao = new RestaurantDao();
    }

    @After
    public void tearDown() {
        locationDao = null;
        restaurantDao = null;
    }

    @Test
    public void create() {
        Location location = new Location("Warszawa - Test", "Marszałkowska - Test", "22 - Test");
        long locationId = locationDao.create(location);
        location.setId(locationId);

        Restaurant restaurant = new Restaurant("Smaczna", null, location, null);
        long restaurantId = restaurantDao.create(restaurant, location);
        Assert.assertTrue("Nie dodano restaurant do bazy.", restaurantId > -1);
    }
}