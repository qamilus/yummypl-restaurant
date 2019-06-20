package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RestaurantInformationDao {

    private static final String INSERT_SQL =
            "INSERT INTO RESTAURANTS_INFORMATION(ID, opening_hours, restaurant_id) " +
                    "VALUES (nextval('RESTAURANTS_INFORMATION_ID_SEQ'), ?, ?)";

    public long create(RestaurantInformation restaurantInformation) {
        long generatedId = -1;

        try {
            Connection connection = DatabaseUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, restaurantInformation.getOpeningHours());
            statement.setLong(2, restaurantInformation.getRestaurantId());

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
