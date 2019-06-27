package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantInformationModel;

/**
 * Klasa pomocnicza zamieniająca model <code>{@link RestaurantInformationModel}</code> dla GUI na encje z bazy danych <code>{@link RestaurantInformation}</code>.
 *
 * @author Kamil Swislowski
 */
public class RestaurantInformationMapper {

    /**
     * Zamienia model <code>{@link RestaurantInformationModel}</code> dla GUI na encje z bazy danych <code>{@link RestaurantInformation}</code>.
     * @param model Model, który będzie zamieniony na encję.
     * @return Zamienioną encję z modelu.
     */
    public static RestaurantInformation toEntity(RestaurantInformationModel model) {
        RestaurantInformation restaurantInformation = null;

        if (model != null) {
            restaurantInformation = new RestaurantInformation();
            restaurantInformation.setOpeningHours(model.getOpeningHours());
        }

        return restaurantInformation;
    }
}
