package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.RestaurantInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "kamil";
    private static final String PASSWORD = "kamil";
    private static final String SQL = "select * from restaurants";
    private static final String R_ID = "r_id";
    private static final String NAME = "name";
    private static final String MENU = "menu";
    private static final String LOCATION = "location";
    private static final String INFORMATION = "information";

    private static final String RI_ID = "ri_id";
    private static final String OPENING_HOURS = "opening_hours";
    private static final String L_ID = "l_id";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";

//    public static void main(String[] args) throws SQLException {
//        list();
//    }

    public List<Restaurant> list() throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();

//                String menu = resultSet.getString(MENU);
//                String location = resultSet.getString(LOCATION);

                restaurant.setId(resultSet.getLong(R_ID));
                restaurant.setName(resultSet.getString(NAME));
//                restaurant.setMenu(menu);
//                restaurant.setLocation(location);

                restaurants.add(restaurant);
            }

        } finally {
            DatabaseUtils.closeConnection();
        }
        System.out.println(connection.isClosed());
        return restaurants;
    }

    public List<Restaurant> listWithJoin() throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT R.ID AS R_ID, R.NAME, L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER, LT.NAME AS LOCATION_TYPE, RI.ID AS RI_ID, RI.OPENING_HOURS " +
                            "FROM RESTAURANTS R " +
                            "LEFT JOIN RESTAURANTS_INFORMATION RI " +
                            "    ON R.ID = RI.RESTAURANT_ID " +
                            "LEFT JOIN LOCATIONS L " +
                            "    ON R.LOCATION_ID = L.ID " +
                            "LEFT JOIN LOCATIONS_TYPE LT " +
                            "    ON L.LOCATION_TYPE_ID = LT.ID");

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();

//                String menu = resultSet.getString(MENU);
//                String location = resultSet.getString(LOCATION);

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

        } finally {
            DatabaseUtils.closeConnection();
        }
        System.out.println(connection.isClosed());
        return restaurants;
    }
}
