package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//S.O.L.I.D.
//S(single responsibility) - RestaurantService jest odpowiedzialny tylko i wyłącznie za przekazywanie danych.
public class RestaurantService {
    private static final Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());
    //kompozycja wykorzystująca implementację DAO.
    private RestaurantDao restaurantDao;

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
}
