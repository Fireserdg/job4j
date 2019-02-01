package ru.job4j.persistence;

import org.slf4j.*;
import ru.job4j.config.Config;
import ru.job4j.model.*;

import java.sql.*;
import java.util.*;

/**
 * DataBase store
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class DbStore implements Store {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);

    /**
     * Contains config for get properties.
     */
    private static final Config CONFIG = Config.getInstance();

    /**
     * Url database.
     */
    private static final String URL = CONFIG.getValue("db.url");

    /**
     * Database name.
     */
    private static final String DB_NAME = CONFIG.getValue("db.name");

    /**
     * Database password.
     */
    private static final String PASSWORD = CONFIG.getValue("db.password");

    /**
     * Database driver.
     */
    private static final String DB_DRIVER = CONFIG.getValue("db.driver");

    /**
     * Instance DbStore.
     */
    private static final Store STORE = new DbStore();

    /**
     * Constructor DbStore.
     */
    private DbStore() {
        init();
    }

    /**
     * Init driver for database.
     */
    private void init() {
        try {
            Class.forName(DB_DRIVER);
            LOG.info("Driver {} successfully registered", DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOG.error("Error registered driver{}", DB_DRIVER, e, e.getMessage());
        }
    }

    /**
     * Get instance store.
     * @return instance store.
     */
    public static Store getInstance() {
        return STORE;
    }

    /**
     * Add account.
     * @param accounts account.
     * @return message about operation.
     */
    public String addAccount(Accounts accounts) {
        Connection conn = null;
        String msg;
        try {
            conn = DriverManager.getConnection(URL, DB_NAME, PASSWORD);
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(CONFIG.getValue("db.add"))) {
                ps.setInt(1, accounts.getId());
                ps.setString(2, accounts.getUsername());
                ps.setString(3, accounts.getPhone());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement(CONFIG.getValue("db.bookedHall"))) {
                ps.setBoolean(1, true);
                ps.setInt(2, accounts.getId());
                ps.executeUpdate();
            }
            conn.commit();
            LOG.info("Билет приобретен. Добавлен аккаунт {}", accounts);
            msg = "Билет успешно приобретен";
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            msg = "Билет уже купили";
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return msg;
    }

    /**
     * List of halls.
     * @return list of halls.
     */
    public List<Hall> getHalls() {
        List<Hall> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DB_NAME, PASSWORD);
             Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(CONFIG.getValue("db.getHalls"))) {
            while (rs.next()) {
                list.add(createHall(rs));
            }
            LOG.info("Get halls in cinema");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    @Override
    public Hall getHallsById(int id) {
        Hall hall = null;
        try (Connection conn = DriverManager.getConnection(URL, DB_NAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(CONFIG.getValue("db.getHallsById"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hall = createHall(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return hall;
    }

    /**
     * Result set from SQL query.
     * @param resultSet result set.
     * @return Hall
     * @throws SQLException exception.
     */
    private Hall createHall(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int row = resultSet.getInt("number_row");
        int seat = resultSet.getInt("number_seat");
        int price = resultSet.getInt("price");
        boolean booked = resultSet.getBoolean("booked");
        return new Hall(id, row, seat, price, booked);
    }
}
