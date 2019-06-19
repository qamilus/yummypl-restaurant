package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api.OrderStatusUtils.mapOrderStatus;

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

    private static final String UPDATE_SQL =
            "UPDATE ORDERS " +
                    "SET ORDER_STATUS_ID = ? " +
                    "WHERE ID = ?";
    private static final String O_ID = "o_id";
    private static final String ORDERS_STATUS = "ORDERS_STATUS";
    private static final String ITEMS_COUNT = "ITEMS_COUNT";
    private static final String L_ID = "l_id";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";

    private OrderStatusDao orderStatusDao = new OrderStatusDao();

    public List<Order> list(Long restaurantId) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setLong(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final long oid = resultSet.getLong(O_ID);
                Order order = new Order();
                if (oid != 0) {
                    order.setId(oid);
                    order.setStatus(mapOrderStatus(resultSet.getString(ORDERS_STATUS)));
                    order.setItemsCount(resultSet.getInt(ITEMS_COUNT));
                }

                final long lId = resultSet.getLong(L_ID);
                if (lId != 0) {
                    Location location = new Location();
                    location.setId(lId);
                    location.setCity(resultSet.getString(CITY));
                    location.setStreet(resultSet.getString(STREET));
                    location.setHouseNumber(resultSet.getString(HOUSE_NUMBER));

                    order.setDeliveryLocation(location);
                }
                orders.add(order);
            }
        }
        return orders;
    }

    public void update(Order order) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        long orderId = order.getId();
        long orderStatusId = orderStatusDao.select(order.getStatus().getName());

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, orderStatusId);
            preparedStatement.setLong(2, orderId);

            preparedStatement.executeUpdate();
        }
    }
}
