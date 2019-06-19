package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusDao {
    private static final String SELECT_SQL = "SELECT id FROM orders_status WHERE UPPER(name) LIKE UPPER(?)";

    public long select(String orderStatus) {
        long id = -1;

        try {
            Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
            statement.setString(1, "%" + orderStatus);

            ResultSet resultSet = statement.executeQuery();
            if ((resultSet.next())) {
                id = resultSet.getLong("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
