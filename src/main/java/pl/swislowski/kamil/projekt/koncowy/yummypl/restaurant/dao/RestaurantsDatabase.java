package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsDatabase {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "kamil";
    private static final String PASSWORD = "kamil";
    private static final String SQL = "select * from restaurants";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String MENU = "menu";
    private static final String LOCATION = "location";
    private static final String INFORMATION = "information";

    public static void main(String[] args) throws SQLException {
        selectData();
        System.out.println();
    }

    private static List<Restaurant> selectData() throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL);

            List<Restaurant> restaurants = new ArrayList<>();

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();

                long id = resultSet.getLong(ID);
                System.out.println("id : " + id);

                String name = resultSet.getString(NAME);
                System.out.println("name : " + name);

                String menu = resultSet.getString(MENU);
                System.out.println("menu : " + menu);

                String location = resultSet.getString(LOCATION);
                System.out.println("location : " + location);

                String information = resultSet.getString(INFORMATION);
                System.out.println("information : " + information);

                restaurant.setId(id);
                restaurant.setName(name);
//                restaurant.setMenu(menu);
//                restaurant.setLocation(location);
//                restaurant.setInformation(information);

                System.out.println(restaurant);
                restaurants.add(restaurant);

                System.out.println(restaurants);
            }
            return restaurants;
        }
    }
}
