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
public class OrderModel {
    private String id;
    private String restaurantName;
    private String itemsQuantity;
    private String status;
    private String deliveryAddress;
}
