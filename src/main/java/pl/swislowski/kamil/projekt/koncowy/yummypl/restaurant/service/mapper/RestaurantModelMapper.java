package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

/**
 * Klasa pomocnicza zamieniająca model <code>{@link RestaurantModel}</code> dla GUI na encje z bazy danych <code>{@link Restaurant}</code>.
 *
 * @author Kamil Swislowski
 */
public class RestaurantModelMapper {

    /**
     * Zamienia model <code>{@link RestaurantModel}</code> dla GUI na encje z bazy danych <code>{@link Restaurant}</code>.
     *
     * @param restaurantModel Model, który będzie zamieniony na encję.
     * @return Zamienioną encję z modelu.
     */
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
