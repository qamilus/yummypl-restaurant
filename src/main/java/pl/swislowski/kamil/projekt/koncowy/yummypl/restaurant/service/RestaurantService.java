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

//S.O.L.I.D.
//S(single responsibility) - RestaurantService jest odpowiedzialny tylko i wyłącznie za przekazywanie danych.
/**
 * @author Kamil Swislowski
 */
public class RestaurantService {
    private static final Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());
    //kompozycja wykorzystująca implementację DAO.
    private RestaurantDao restaurantDao;
    private LocationDao locationDao;
    private RestaurantInformationDao restaurantInformationDao;

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public List<RestaurantModel> list() {
        List<RestaurantModel> restaurantModelList = new ArrayList<>();
        try {
            //delegacja wywołania metody list z DAO.
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

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setRestaurantInformationDao(RestaurantInformationDao restaurantInformationDao) {
        this.restaurantInformationDao = restaurantInformationDao;
    }
}
