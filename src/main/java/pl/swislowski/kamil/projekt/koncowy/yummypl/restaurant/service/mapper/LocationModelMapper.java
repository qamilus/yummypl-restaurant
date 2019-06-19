package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.mapper;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.LocationModel;

public class LocationModelMapper {

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

    public static LocationModel fromEntity(Location location) {
        LocationModel locationModel = null;

        if (location != null) {
            locationModel = new LocationModel();
            locationModel.setCity(location.getCity());
        }
        return locationModel;
    }
}
