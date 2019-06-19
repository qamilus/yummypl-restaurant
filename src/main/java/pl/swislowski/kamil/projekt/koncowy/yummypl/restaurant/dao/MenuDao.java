package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.MenuModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuDao {

    private static final String INSERT_SQL =
            "INSERT INTO MENU(ID, NAME, RESTAURANT_ID) VALUES (?, ?, ?) ";

    public void createMenu(MenuModel menuModel) {
        try {
            Connection connection = DatabaseUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
//            statement.setInt(1, Integer.valueOf(menuModel.getId()));
            statement.setString(2, menuModel.getName());
            statement.setInt(3, menuModel.getNumberOfItems());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
