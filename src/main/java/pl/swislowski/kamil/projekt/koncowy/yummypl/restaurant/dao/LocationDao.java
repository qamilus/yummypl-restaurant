package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDao {

    private static final String INSERT_SQL =
            "INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, location_type_id) VALUES (nextval('LOCATIONS_ID_SEQ'), ?, ?, ?, 1)";

    public long create(Location location) {
        long generatedId = -1;

        try {
            Connection connection = DatabaseUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, location.getCity());
            statement.setString(2, location.getStreet());
            statement.setString(3, location.getHouseNumber());

            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                generatedId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
