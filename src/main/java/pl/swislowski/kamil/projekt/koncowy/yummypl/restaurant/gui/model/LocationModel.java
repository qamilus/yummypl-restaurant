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
public class LocationModel {
    private String id;
    private String city;
    private String street;
    private String houseNumber;
}
