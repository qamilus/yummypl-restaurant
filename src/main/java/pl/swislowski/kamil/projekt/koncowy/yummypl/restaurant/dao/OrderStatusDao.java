package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO(Data Access Object) zapewnia dostęp do tabeli ORDERS_STATUS w bazie danych.
 * Tabela ORDERS_STATUS jest tabelą słownikową tzn. przechowuje tylko identyfikator/klucz główny oraz wartość słownikową.
 *
 * @author Kamil Swislowski
 */
public class OrderStatusDao {
    private static final String SELECT_SQL = "SELECT id FROM orders_status WHERE UPPER(name) LIKE UPPER(?)";

    /**
     * Zwraca identyfikator/klucz główny statusu zamówienia z tabeli ORDERS_STATUS.
     * Wynik zwracamy niezależnie od tego czy parametr <code>String orderStatus</code> jest zapisany wielkimi i/lub małymi literami.
     *
     * @param orderStatus
     * @return Identyfikator/klucz główny statusu zamówienia.
     */
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
