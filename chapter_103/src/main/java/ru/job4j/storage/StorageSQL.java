package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

/**
 * Storage SQL.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class StorageSQL {

    /**
     * Contains config.
     *
     */
    private final Config config;

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(StorageSQL.class);

    /**
     * Constructor StorageSQL.
     *
     * @param config config for get value.
     */
    public StorageSQL(Config config) {
        this.config = config;
    }

    /**
     * Generate value to database.
     *
     * @param n value.
     */
    public void generate(int n) {
        createTable();
        try (Connection conn = DriverManager.getConnection(this.config.getValue("st.url"))) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.config.getValue("st.add"))) {
                for (int i = 1; i <= n; i++) {
                    ps.setInt(1, i);
                    ps.executeUpdate();
                }
                conn.commit();
                LOG.info("Values were successfully added to the database");
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
                try {
                    conn.rollback();
                } catch (SQLException exc) {
                    LOG.error(exc.getMessage(), exc);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Create table to database.
     *
     */
    private void createTable() {
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(this.config.getValue("st.url"))) {
            LOG.info("Connect to the database successfully");
            try (Statement st = conn.createStatement()) {
                st.execute(this.config.getValue("st.crTable"));
                LOG.info("The table exists");
                result = true;
                try (ResultSet rs = st.executeQuery(this.config.getValue("st.select"))) {
                    if (rs.next()) {
                        st.execute(this.config.getValue("st.deleteAll"));
                        LOG.info("Old values were successfully deleted to the table");
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        if (!result) {
            throw new CreateDataBaseException("Database does not exist");
        }
    }
}