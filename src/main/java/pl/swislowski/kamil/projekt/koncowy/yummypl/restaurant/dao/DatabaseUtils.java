package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());

    private static final String URL = "jdbc:postgresql://localhost:5432/Kamil";
    private static final String USER = "Kamil";
    private static final String PASSWORD = "Kamil";

//    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//    private static final String USER = "kamil";
//    private static final String PASSWORD = "kamil";

//    private static final String URL = "jdbc:postgresql://ec2-46-137-187-23.eu-west-1.compute.amazonaws.com:5432/d2tm1rok342quh";
//    private static final String USER = "nsyqsspaiyipmj";
//    private static final String PASSWORD = "c0b5757a7ac21ac3935ffe260eaea91200ac6b33d52bab9ee9713a0379253e88";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        LOGGER.info("Acquiring connection ...");
        if (connection == null) {
//            connection = DriverManager.getConnection(URL);
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
