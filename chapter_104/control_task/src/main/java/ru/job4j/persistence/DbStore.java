package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
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
     * Pool for database.
     *
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * Instance DbStore.
     */
    private static final Store STORE = new DbStore();

    /**
     * Constructor DbStore.
     */
    private DbStore() {
        SOURCE.setDriverClassName(CONFIG.getValue("db.driver"));
        SOURCE.setUrl(CONFIG.getValue("db.url"));
        SOURCE.setUsername(CONFIG.getValue("db.name"));
        SOURCE.setPassword(CONFIG.getValue("db.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
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
            conn = SOURCE.getConnection();
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
            LOG.info(ADD_ACCOUNT, accounts);
            msg = BUY_HALL;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            msg = NOT_HALL;
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
        try (Connection conn = SOURCE.getConnection();
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
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement ps = conn.prepareStatement(CONFIG.getValue("db.getHallsById"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return createHall(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalArgumentException(String.format("Hall with ID=%s not found", id));
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
