package ru.job4j.vacancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * Sql storage for contains vacancy
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */

public class SqlStorage {

    /**
     * Contains configuration
     *
     */
    private Config conf;

    /**
     * Contains start data and time from database
     *
     */
    private LocalDateTime minDate;

    /**
     * Contains parse document.
     *
     */
    ParseDocument parseDocument;

    /**
     * Contains logger
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(SqlStorage.class);

    /**
     * Constructor
     *
     * @param conf configuration object
     */
    public SqlStorage(Config conf) {
        this.conf = conf;
        this.init();
        this.parseDocument = new ParseDocument(this.conf, getInfoMinDate());
    }

    /**
     * Start and create table
     *
     */
    private void init() {
        try {
            Class.forName(conf.getValue("jdbc.driver"));
            createTable();
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Create table to DataBase
     *
     */
    private void createTable() {
        try (Connection conn = DriverManager.getConnection(conf.getValue("jdbc.url"),
                conf.getValue("jdbc.username"), conf.getValue("jdbc.password"));
             Statement st = conn.createStatement()) {
            st.execute(conf.getValue("jdbc.Table"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get info about start date and time from database
     *
     * @return start date and time from database
     */
    private LocalDateTime getInfoMinDate() {
        try (Connection conn = DriverManager.getConnection(conf.getValue("jdbc.url"),
                conf.getValue("jdbc.username"), conf.getValue("jdbc.password"));
             Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(conf.getValue("jdbc.lastDate"))) {
            if (rs.next() && rs.getTimestamp("max") != null) {
                this.minDate = rs.getTimestamp("max").toLocalDateTime();
                System.out.println(minDate);
            } else {
                this.minDate = LocalDate.now().with(
                        TemporalAdjusters.firstDayOfYear()).atStartOfDay();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.minDate;
    }

    /**
     * Add vacancy to the database.
     *
     */
    public void addVacancy() {
        try (Connection conn = DriverManager.getConnection(conf.getValue("jdbc.url"),
                conf.getValue("jdbc.username"), conf.getValue("jdbc.password"));
             PreparedStatement prep = conn.prepareStatement(conf.getValue("jdbc.insert"), Statement.RETURN_GENERATED_KEYS)) {
            List<Item> items = this.parseDocument.getItems();
            for (Item item : items) {
                prep.setString(1, item.getVacancy());
                prep.setString(2, item.getUrl());
                prep.setTimestamp(3, item.getCreate());
                prep.addBatch();
                LOG.info(String.format("Add vacancy[%s], data and time [%s]", item.getVacancy(),
                        item.getCreate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm"))));
            }
            if (items.isEmpty()) {
                LOG.info("No new vacancies");
            }
            prep.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}