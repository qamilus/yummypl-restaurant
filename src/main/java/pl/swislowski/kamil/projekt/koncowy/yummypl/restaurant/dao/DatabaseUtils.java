package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Kamil Swislowski
 */
public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());

    private static final String DATA_SOURCE_PROPERTIES = "data-source.properties";
    private static final String DATA_SOURCE_URL = "data.source.url";
    private static final String DATA_SOURCE_USER = "data.source.user";
    private static final String DATA_SOURCE_PASSWORD = "data.source.password";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        LOGGER.info("Acquiring connection ...");
        Properties dataSourceProperties = getDataSourceProperties();
        String url = dataSourceProperties.getProperty(DATA_SOURCE_URL);
        String user = dataSourceProperties.getProperty(DATA_SOURCE_USER);
        String password = dataSourceProperties.getProperty(DATA_SOURCE_PASSWORD);

        if (connection == null) {
//            connection = DriverManager.getConnection(URL);
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

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
