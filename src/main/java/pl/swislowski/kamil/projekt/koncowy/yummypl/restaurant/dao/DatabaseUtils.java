package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Klasa narzędziowa do obsługi bazy danych.
 * <ul>
 * <li>tworzenie połączenia z bazą danych</li>
 * <li>zamykanie połączenia z bazą danych</li>
 * </ul>
 *
 * @author Kamil Swislowski
 */
public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());

    private static final String DATA_SOURCE_PROPERTIES = "data-source.properties";
    private static final String DATA_SOURCE_URL = "data.source.url";
    private static final String DATA_SOURCE_USER = "data.source.user";
    private static final String DATA_SOURCE_PASSWORD = "data.source.password";

    private static Connection connection;

    /**
     * Tworzy i zwraca połączenie <code>{@link Connection}</code> z bazą danych
     *
     * @return Połączenie z bazą danych.
     * @throws SQLException Wyjątek zawierający informacje o błędach z bazy danych.
     */
    public static Connection getConnection() throws SQLException {
        LOGGER.info("Acquiring connection ...");
        Properties dataSourceProperties = getDataSourceProperties();

        String url = dataSourceProperties.getProperty(DATA_SOURCE_URL);
        String user = dataSourceProperties.getProperty(DATA_SOURCE_USER);
        String password = dataSourceProperties.getProperty(DATA_SOURCE_PASSWORD);

        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    /**
     * Zamyka połączenie z bazą danych w bezpieczny sposób.
     */
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

    /**
     * Pobiera i zwraca informacje o źródle danych(URL, USERNAME, PASSWORD) z pliku properties.
     * Plik properties zawiera informacje typu klucz=wartość np. data.source.user=Kamil.
     * Zwracany jest obiekt klasy <code>{@link Properties}</code>, który przechowuje klucz=wartość.
     *
     * @return Obiekt klasy <code>{@link Properties}</code>, wypełnione danymi z pliku <code>data-source.properties</code>.
     */
    private static Properties getDataSourceProperties() {
        InputStream propertiesInputStream = DatabaseUtils.class.getClassLoader().getResourceAsStream(DATA_SOURCE_PROPERTIES);
        Properties properties = new Properties();

        try {
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
