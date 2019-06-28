package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO(Data Access Object) zapewnia dostęp do tabeli RESTAURANTS_INFORMATION w bazie danych.
 * Udostępnia podstawowe operacje na tabeli np. wstawianie danych do tabeli oraz pobieranie wszystkich wierszy z tabeli.
 *
 * @author Kamil Swislowski
 */
public class RestaurantInformationDao {

    private static final String INSERT_SQL =
            "INSERT INTO RESTAURANTS_INFORMATION(ID, opening_hours, restaurant_id) " +
                    "VALUES (nextval('RESTAURANTS_INFORMATION_ID_SEQ'), ?, ?)";

    /**
     * Zapisuje do tabeli RESTAURANTS_INFORMATION i zwraca identyfikator nowego wiersza/klucza głównego dla RESTAURANTS_INFORMATION w bazie danych.
     *
     * @param restaurantInformation Obiekt RestaurantInformation, który ma zostać zapisany w bazie w tabeli RESTAURANTS_INFORMATION.
     * @return Identyfikator nowego wiersza/klucza głównego dla RESTAURANTS_INFORMATION w bazie danych.
     */
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
