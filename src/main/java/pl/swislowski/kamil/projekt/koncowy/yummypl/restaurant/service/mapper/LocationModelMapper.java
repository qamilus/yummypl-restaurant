package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.LocationModel;

/**
 * Klasa pomocnicza zamieniająca model <code>{@link LocationModel}</code> dla GUI na encje z bazy danych <code>{@link Location}</code> i odwrotnie.
 *
 * @author Kamil Swislowski
 */
public class LocationModelMapper {

    /**
     * Zamienia model <code>{@link LocationModel}</code> dla GUI na encje z bazy danych <code>{@link Location}</code>.
     *
     * @param locationModel Model, który będzie zamieniony na encję.
     * @return Zamienioną encję z modelu.
     */
    public static Location toEntity(LocationModel locationModel) {
        Location location = null;

        if (locationModel != null) {
            location = new Location();
            location.setCity(locationModel.getCity());
            location.setStreet(locationModel.getStreet());
            location.setHouseNumber(locationModel.getHouseNumber());
        }
        return location;
    }

    /**
     * Zamienia encje z bazy danych <code>{@link Location}</code> na model <code>{@link LocationModel}</code> dla GUI.
     *
     * @param location Encja, która będzie zamieniona na model.
     * @return Zamieniony model z encji.
     */
    public static LocationModel fromEntity(Location location) {
        LocationModel locationModel = null;

        if (location != null) {
            locationModel = new LocationModel();
            locationModel.setCity(location.getCity());
        }
        return locationModel;
    }
}
