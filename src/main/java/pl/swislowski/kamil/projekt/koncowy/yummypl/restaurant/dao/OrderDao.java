package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Order;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final String SELECT_SQL =
            "SELECT O.ID AS O_ID, OS.NAME AS ORDERS_STATUS, L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER, " +
                    "(SELECT COUNT(*) FROM ORDERS_ITEMS OI2 WHERE OI2.ORDER_ID = O.ID) AS ITEMS_COUNT " +
                    "FROM ORDERS O " +
                    "LEFT JOIN locations L " +
                    "    ON o.location_id = l.id " +
                    "LEFT JOIN locations_type LT " +
                    "    ON l.location_type_id = lt.id " +
                    "LEFT JOIN ORDERS_STATUS OS " +
                    "    ON o.order_status_id = os.id " +
                    "WHERE O.RESTAURANT_ID = ?";

    public List<Order> list(Long restaurantId) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setLong(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final long oid = resultSet.getLong("o_id");
                Order order = new Order();
                if (oid != 0) {
                    order.setId(oid);
                    order.setStatus(mapOrderStatus(resultSet.getString("ORDERS_STATUS")));
                    order.setItemsCount(resultSet.getInt("ITEMS_COUNT"));
                }
//
                final long lId = resultSet.getLong("l_id");
                if (lId != 0) {
                    Location location = new Location();
                    location.setId(lId);
                    location.setCity(resultSet.getString("city"));
                    location.setStreet(resultSet.getString("street"));
                    location.setHouseNumber(resultSet.getString("house_number"));

                    order.setDeliveryLocation(location);
                }
                orders.add(order);
            }

        } finally {
//            DatabaseUtils.closeConnection();
        }
        return orders;
    }

    private OrderStatus mapOrderStatus(String orderStatusName) {
        if (orderStatusName != null) {
            if (orderStatusName.toLowerCase().contains("new")) {
                return OrderStatus.NEW;
            } else if (orderStatusName.toLowerCase().contains("progress")) {
                return OrderStatus.IN_PROGRESS;
            } else if (orderStatusName.toLowerCase().contains("done")) {
                return OrderStatus.DONE;
            } else if (orderStatusName.toLowerCase().contains("canceled")) {
                return OrderStatus.CANCELED;
            }
        }
        return null;
    }
}
