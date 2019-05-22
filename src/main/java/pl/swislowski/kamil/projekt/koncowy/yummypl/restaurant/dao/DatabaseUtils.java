package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "kamil";
    private static final String PASSWORD = "kamil";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        LOGGER.info("Acquiring connection ...");
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        LOGGER.info("Closing connection ...");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
