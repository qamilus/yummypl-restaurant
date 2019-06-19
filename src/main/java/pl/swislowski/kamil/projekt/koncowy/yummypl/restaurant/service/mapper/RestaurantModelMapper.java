package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

public class RestaurantModelMapper {

    public static Restaurant toEntity(RestaurantModel restaurantModel) {
        Restaurant restaurant = null;

        if (restaurantModel != null) {
            restaurant = new Restaurant();
            restaurant.setName(restaurantModel.getName());

            Location location = LocationModelMapper.toEntity(restaurantModel.getLocationModel());
            restaurant.setLocation(location);
        }

        return restaurant;
    }
}
