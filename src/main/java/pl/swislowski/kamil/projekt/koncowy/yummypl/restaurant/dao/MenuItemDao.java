package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Restaurant;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.MenuItemModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuItemDao {

    private static final String INSERT_SQL =
            "INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (?, ?, ?, ?, ?) ";

    public void createMenuItem(MenuItemModel menuItemModel) {
        Restaurant restaurant = new Restaurant();
        try {
            Connection connection = DatabaseUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
//            statement.setInt(1, Integer.valueOf(menuItemModel.getId()));
            statement.setString(2, menuItemModel.getName());
            statement.setDouble(3, menuItemModel.getPrice());
            statement.setInt(4, menuItemModel.getQuantity());
//            statement.setInt(5, Integer.valueOf(locationModel.getId()));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
