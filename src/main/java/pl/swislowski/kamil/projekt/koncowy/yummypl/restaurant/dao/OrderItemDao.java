package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO(Data Access Object) zapewnia dostęp do tabeli ORDERS_ITEMS w bazie danych.
 * Udostępnia podstawowe operacje na tabeli np. pobieranie wszystkich wierszy z tabeli.
 *
 * @author Kamil Swislowski
 */
public class OrderItemDao {

    private static final String SELECT_SQL =
            "SELECT OI.ID AS OI_ID, OI.NAME, OI.PRICE, OI.QUANTITY, O.ID AS O_ID " +
            "FROM ORDERS O " +
            "         LEFT JOIN ORDERS_ITEMS OI " +
            "                   ON OI.ORDER_ID = O.ID " +
            "WHERE O.ID = ?";

    /**
     * Pobiera i zwraca odpowiednie(LEFT JOIN) wiersze z tabel ORDERS I ORDERS_ITEMS.
     *
     * @param orderId Identyfikator/klucz główny Zamówienia dla którego będą zwracane OrderItems.
     * @return Lista wszystkich OrderItem dla wybranego zamówienia.
     * @throws SQLException Wyjątek zawierający informacje o błędach z bazy danych.
     */
    public List<OrderItem> list(long orderId) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setLong(1,orderId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long oiId = resultSet.getLong("oi_id");
                if (oiId != 0) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(oiId);
                    orderItem.setName(resultSet.getString("name"));
                    orderItem.setPrice(resultSet.getDouble("price"));
                    orderItem.setQuantity(resultSet.getInt("quantity"));
                    orderItem.setOrderId(resultSet.getLong("o_id"));

                    orderItems.add(orderItem);
                }

            }
        }
        return orderItems;
    }
}
