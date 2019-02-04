package ru.job4j.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 /**
 * Connection, which connection all commits.
 * It is used for integration test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ConnectionInit {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionInit.class);

    /**
     * Properties for load configuration.
     *
     */
    private static final Properties PROP = new Properties();

    static {
        ClassLoader loader = ConnectionInit.class.getClassLoader();
        try (final InputStream inputStream = loader.getResourceAsStream("config.properties")) {
            PROP.load(inputStream);
            Class.forName(PROP.getProperty("db.driver"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Create connection with autocommit=false mode and connection call, when conneciton is closed.
     * @param connection connection.
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public static Connection creteRollback(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionInit.class.getClassLoader(),
                new Class[] {Connection.class },
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }

    /**
     * Create and connection call.
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public static Connection init() throws SQLException {
        return DriverManager.getConnection(PROP.getProperty("db.urlDB"),
                PROP.getProperty("db.name"), PROP.getProperty("db.password"));
    }

    /**
     * Get property configuration Db.
     *
     * @return properties.
     */
    public static Properties getProperties() {
        return PROP;
    }
}
