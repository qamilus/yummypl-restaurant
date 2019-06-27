package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Biblioteka LOMBOK
@Data // Automatycznie generuje getters/setters, toString, hashCode i equals dla POJO.
@NoArgsConstructor // Automatycznie generuje domyślny bezargumentowy konstruktor dla POJO.
@AllArgsConstructor // Automatycznie generuje argumentowy konstruktor dla POJO.
/**
 * POJO przechowujące dane o Lokalizacji dla GUI.
 *
 * @author Kamil Swislowski
 */
public class LocationModel {
    private String id;
    private String city;
    private String street;
    private String houseNumber;
}
