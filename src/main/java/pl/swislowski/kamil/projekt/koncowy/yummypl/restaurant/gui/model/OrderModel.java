package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Biblioteka LOMBOK
@Data // Automatycznie generuje getters/setters, toString, hashCode i equals dla POJO.
@NoArgsConstructor // Automatycznie generuje domyślny bezargumentowy konstruktor dla POJO.
@AllArgsConstructor // Automatycznie generuje argumentowy konstruktor dla POJO.
/**
 * POJO przechowujące dane o Zamówieniu dla GUI.
 *
 * @author Kamil Swislowski
 */
public class OrderModel {
    private String id;
    private String restaurantName;
    private String itemsQuantity;
    private String status;
    private String deliveryAddress;
}
