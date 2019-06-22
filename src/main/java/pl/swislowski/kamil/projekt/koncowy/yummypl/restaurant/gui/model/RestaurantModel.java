package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @author Kamil Swislowski
 */
public class RestaurantModel {
    private String id;
    private String name;
    private String address;
    private String information;
    private String openingHours;

    private LocationModel locationModel;
    private RestaurantInformationModel restaurantInformationModel;
}
