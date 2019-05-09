package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller.RestaurantListController;

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
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String MENU = "menu";
    private static final String LOCATION = "location";
    private static final String INFORMATION = "information";

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
//                String information = resultSet.getString(INFORMATION);

                restaurant.setId(resultSet.getLong(ID));
                restaurant.setName(resultSet.getString(NAME));
//                restaurant.setMenu(menu);
//                restaurant.setLocation(location);
//                restaurant.setInformation(information);

                restaurants.add(restaurant);
            }

        } finally {
            DatabaseUtils.closeConnection();
        }
        System.out.println(connection.isClosed());
        return restaurants;
    }
}
