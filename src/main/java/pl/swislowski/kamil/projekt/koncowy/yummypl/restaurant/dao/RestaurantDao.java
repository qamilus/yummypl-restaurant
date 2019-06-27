package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO(Data Access Object) zapewnia dostęp do tabeli RESTAURANTS w bazie danych.
 * Udostępnia podstawowe operacje na tabeli np. wstawianie danych do tabeli oraz pobieranie wszystkich wierszy z tabeli.
 *
 * @author Kamil Swislowski
 */
public class RestaurantDao {

    private static final String R_ID = "r_id";
    private static final String NAME = "RESTAURANT_NAME";

    private static final String RI_ID = "ri_id";
    private static final String OPENING_HOURS = "opening_hours";
    private static final String L_ID = "l_id";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";

    private static final String SELECT_SQL =
            "SELECT R.ID AS R_ID, R.NAME AS RESTAURANT_NAME, L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER, LT.NAME AS LOCATION_TYPE, RI.ID AS RI_ID, RI.OPENING_HOURS " +
            "FROM RESTAURANTS R " +
            "LEFT JOIN RESTAURANTS_INFORMATION RI " +
            "    ON R.ID = RI.RESTAURANT_ID " +
            "LEFT JOIN LOCATIONS L " +
            "    ON R.LOCATION_ID = L.ID " +
            "LEFT JOIN LOCATIONS_TYPE LT " +
            "    ON L.LOCATION_TYPE_ID = LT.ID " +
            "WHERE LT.ID = 1 " +
            "ORDER BY R_ID ASC";

    private static final String INSERT_SQL =
            "INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (nextval('RESTAURANTS_ID_SEQ'), ?, ?) ";

    private Connection connection;

    /**
     * Pobiera i zwraca wszystkie wiersze z tabeli RESTAURANTS.
     *
     * @return Lista wszystkich restauracji.
     * @throws SQLException Wyjątek zawierający informacje o błędach z bazy danych.
     */
    public List<Restaurant> list() throws SQLException {
        connection = DatabaseUtils.getConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();

                restaurant.setId(resultSet.getLong(R_ID));
                restaurant.setName(resultSet.getString(NAME));

                final long lId = resultSet.getLong(L_ID);
                if (lId != 0) {
                    Location location = new Location();
                    location.setId(lId);
                    location.setCity(resultSet.getString(CITY));
                    location.setStreet(resultSet.getString(STREET));
                    location.setHouseNumber(resultSet.getString(HOUSE_NUMBER));

                    restaurant.setLocation(location);

                }

                final Long riId = resultSet.getLong(RI_ID);
                if (riId != 0) {
                    RestaurantInformation information = new RestaurantInformation();
                    information.setId(riId);
                    information.setOpeningHours(resultSet.getString(OPENING_HOURS));

                    restaurant.setInformation(information);
                }

                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    /**
     * Zapisuje do tabeli RESTAURANTS i zwraca identyfikator nowego wiersza/klucza głównego dla RESTAURANTS w bazie danych.
     *
     * @param restaurant Obiekt Restaurant, który ma zostać zapisany w bazie w tabeli RESTAURANTS.
     * @param location   Obiekt Location, który ma zostać połączony z tabelą RESTAURANTS i zapisany w bazie w tabeli LOCATIONS.
     * @return Identyfikator nowego wiersza/klucza głównego dla RESTAURANTS w bazie danych.
     */
    public long create(Restaurant restaurant, Location location) {
        long generatedId = -1;

        try {
            connection = DatabaseUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, restaurant.getName());
            statement.setLong(2, location.getId());

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
