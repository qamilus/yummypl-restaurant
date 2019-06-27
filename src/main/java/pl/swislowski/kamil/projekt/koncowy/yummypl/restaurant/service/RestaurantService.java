package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.LocationDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantInformationDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.LocationModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper.LocationModelMapper;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper.RestaurantInformationMapper;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper.RestaurantModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Usługi związane z logiką biznesową dla Restauracji. Łącznik pomiędzy warstwą prezentacji(JavaFX) i DAO.
 *
 * @author Kamil Swislowski
 */
public class RestaurantService {
    private static final Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());

    private RestaurantDao restaurantDao;
    private LocationDao locationDao;
    private RestaurantInformationDao restaurantInformationDao;

    /**
     * Konstruktor do którego przekazujemy DAO dla <code>{@link Restaurant}</code>.
     *
     * @param restaurantDao DAO dla <code>{@link Restaurant}</code>.
     */
    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    /**
     * Zwraca listę wszystkich restauracji w postaci modelu dla GUI.
     * Zamienia encje pochodzące z bazy danych <code>{@link Restaurant}</code> na model <code>{@link RestaurantModel}</code> dla GUI.
     *
     * @return Lista wszystkich restauracji.
     */
    public List<RestaurantModel> list() {
        List<RestaurantModel> restaurantModelList = new ArrayList<>();
        try {
            final List<Restaurant> restaurantEntityList = restaurantDao.list();

            LOGGER.info("RestaurantList from DAO : " + restaurantEntityList);

            for (Restaurant restaurant : restaurantEntityList) {
                RestaurantModel restaurantModel = new RestaurantModel();

                final Long id = restaurant.getId();
                restaurantModel.setId(String.valueOf(id));

                final String name = restaurant.getName();
                restaurantModel.setName(name);

                final Location location = restaurant.getLocation();
                if (location != null) {
                    LocationModel locationModel = LocationModelMapper.fromEntity(location);
                    restaurantModel.setLocationModel(locationModel);
                    restaurantModel.setAddress(location.getCity() + ", " + location.getStreet() + " " + location.getHouseNumber());
                }

                final RestaurantInformation information = restaurant.getInformation();
                if (information != null) {
                    restaurantModel.setOpeningHours(information.getOpeningHours());
                }

                restaurantModelList.add(restaurantModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantModelList;
    }

    /**
     * Zapisuje do bazy danych nową Restaurację.
     * Zamienia model <code>{@link RestaurantModel}</code> dla GUI na encje z bazy danych <code>{@link Restaurant}</code>.
     *
     * @param restaurantModel Dane nowej Restauracji, którą należy zapisać w bazie danych.
     */
    public void create(RestaurantModel restaurantModel) {
        Restaurant restaurant = RestaurantModelMapper.toEntity(restaurantModel);
        Location location = LocationModelMapper.toEntity(restaurantModel.getLocationModel());
        RestaurantInformation restaurantInformation =
                RestaurantInformationMapper.toEntity(restaurantModel.getRestaurantInformationModel());

        long locationId = locationDao.create(location);
        location.setId(locationId);

        long restaurantId = restaurantDao.create(restaurant, location);
        restaurantInformation.setRestaurantId(restaurantId);

        restaurantInformationDao.create(restaurantInformation);
    }

    /**
     * Przekazuje i ustawia DAO dla tabeli LOCATIONS.
     *
     * @param locationDao DAO dla tabeli LOCATIONS.
     */
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    /**
     * Przekazuje i ustawia DAO dla tabeli RESTAURANTS_INFORMATION.
     *
     * @param restaurantInformationDao DAO dla tabeli RESTAURANTS_INFORMATION.
     */
    public void setRestaurantInformationDao(RestaurantInformationDao restaurantInformationDao) {
        this.restaurantInformationDao = restaurantInformationDao;
    }
}
