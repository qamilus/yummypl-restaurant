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
                    order.setStatus(OrderStatus.NEW);
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
//                final Long oiId = resultSet.getLong("oi_id");
//                if (oiId != 0) {
//                    OrderItem orderItem = new OrderItem();
//                    orderItem.setOrderId(oiId);
//                    orderItem.setQuantity(resultSet.getInt("quantity"));
//
//                    List<OrderItem> orderItems = new ArrayList<>();
//                    orderItems.add(orderItem);
//
//                    order.setOrderItems(orderItems);
//                }
//
                orders.add(order);
            }

        } finally {
//            DatabaseUtils.closeConnection();
        }
        System.out.println(connection.isClosed());

//        orders.add(new Order(null, OrderStatus.NEW, null, 24L));
//        orders.add(new Order(null, OrderStatus.INPROGRESS, null, 24L));
//        orders.add(new Order(null, OrderStatus.DONE, null, 24L));

        return orders;
    }

    private OrderStatus mapOrderStatus(String orderStatusName) {
        if (orderStatusName != null) {
            if (orderStatusName.toLowerCase().contains("new")) {
                return OrderStatus.NEW;
            }
        }
        return null;
    }


//    public List<Order> list() throws SQLException {
//        Connection connection = DatabaseUtils.getConnection();
//        List<Order> orders = new ArrayList<>();
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(
//                    "SELECT O.ID AS O_ID, R.ID AS R_ID, R.NAME, OI.ID AS OI_ID, OI.QUANTITY, OS.NAME AS ORDERS_STATUS,L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER " +
//                            "FROM ORDERS O " +
//                            "LEFT JOIN RESTAURANTS R " +
//                            "    ON o.restaurant_id = r.ID " +
//                            "LEFT JOIN locations L " +
//                            "    ON o.location_id = l.id " +
//                            "LEFT JOIN locations_type LT " +
//                            "    ON l.location_type_id = lt.id " +
//                            "LEFT JOIN ORDERS_STATUS OS " +
//                            "    ON o.order_status_id = os.id " +
//                            "LEFT JOIN ORDERS_ITEMS OI " +
//                            "    ON oi.ORDER_ID = o.ID " +
//                            "WHERE LT.ID = 2");
//
//            while (resultSet.next()) {
//                Restaurant restaurant = new Restaurant();
//
//                restaurant.setId(resultSet.getLong("r_id"));
//                restaurant.setRestaurantName(resultSet.getString("restaurantName"));
//
//                final long lId = resultSet.getLong("l_id");
//                if (lId != 0) {
//                    Location location = new Location();
//                    location.setId(lId);
//                    location.setCity(resultSet.getString("city"));
//                    location.setStreet(resultSet.getString("street"));
//                    location.setHouseNumber(resultSet.getString("house_number"));
//
//                    restaurant.setLocation(location);
//
//                }
//                final long oid = resultSet.getLong("o_id");
//                Order order = new Order();
//                if (oid != 0) {
//                    order.setId(oid);
//                    order.setStatus(OrderStatus.NEW);
//                }
//
//
//                final Long oiId = resultSet.getLong("oi_id");
//                if (oiId != 0) {
//                    OrderItem orderItem = new OrderItem();
//                    orderItem.setOrderId(oiId);
//                    orderItem.setQuantity(resultSet.getInt("quantity"));
//
//                    List<OrderItem> orderItems = new ArrayList<>();
//                    orderItems.add(orderItem);
//
//                    order.setOrderItems(orderItems);
//                }
//
//                orders.add(order);
//            }
//
//        } finally {
////            DatabaseUtils.closeConnection();
//        }
//        System.out.println(connection.isClosed());
//
////        orders.add(new Order(null, OrderStatus.NEW, null, 24L));
////        orders.add(new Order(null, OrderStatus.INPROGRESS, null, 24L));
////        orders.add(new Order(null, OrderStatus.DONE, null, 24L));
//
//        return orders;
//    }
}
