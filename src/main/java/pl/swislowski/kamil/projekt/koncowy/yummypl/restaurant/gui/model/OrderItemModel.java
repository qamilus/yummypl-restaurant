package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {
    private String id;
    private String name;
    private String price;
    private String quantity;
    private String sum;

}
