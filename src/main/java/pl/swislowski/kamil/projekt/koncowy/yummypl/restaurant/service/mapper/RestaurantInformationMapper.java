package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantInformationModel;

public class RestaurantInformationMapper {
    public static RestaurantInformation toEntity(RestaurantInformationModel model) {
        RestaurantInformation restaurantInformation = null;

        if (model != null) {
            restaurantInformation = new RestaurantInformation();
            restaurantInformation.setOpeningHours(model.getOpeningHours());
        }

        return restaurantInformation;
    }
}
