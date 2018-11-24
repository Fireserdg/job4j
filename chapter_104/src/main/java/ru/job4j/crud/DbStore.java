package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataBaseStore
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 22.11.18
 */
public class DbStore implements Store<User> {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);

    /**
     * Config for use properties.
     *
     */
    private static final Config CONF = Config.getInstance();

    /**
     * Pool for database.
     *
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * Instance class DbStore.
     */
    private static final DbStore INSTANCE = new DbStore();

    /**
     * Contains source.
     */
    private DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(CONF.getValue("get.url"));
        SOURCE.setUsername(CONF.getValue("get.name"));
        SOURCE.setPassword(CONF.getValue("get.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createTable();
    }

    /**
     * Get instance.
     * @return instance.
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Create table to DataBase (PostgreSQL).
     * Table name "users".
     *
     */
    private void createTable() {
        try (Connection conn = SOURCE.getConnection();
                Statement statement
                     = conn.createStatement()) {
            statement.execute(CONF.getValue("get.crTable"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("Table users was created");
    }

    /**
     * Add new user to DataBase.
     * @param user new User.
     * @return true if user was added.
     */
    @Override
    public User add(User user) {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement statement
                     = conn.prepareStatement(CONF.getValue("get.add"), Statement.RETURN_GENERATED_KEYS);
             final ResultSet rs = statement.getGeneratedKeys()) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getEmail());
                statement.setTimestamp(4, new Timestamp(user.getCreate()));
                statement.executeUpdate();
            if (rs.next()) {
                user.setId(String.valueOf(rs.getString(1)));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Update user.
     * @param user new data of user.
     */
    @Override
    public void update(User user) {
        try (Connection conn = SOURCE.getConnection();
              PreparedStatement statement
                      = conn.prepareStatement(
                      CONF.getValue("get.update"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, Integer.valueOf(user.getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    @Override
    public void delete(String id) {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement statement
                     = conn.prepareStatement(
                     CONF.getValue("get.delete"))) {
            statement.setInt(1, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Find all in the DataBase.
     * @return container for user.
     */
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             Statement statement
                     = conn.createStatement()) {
            try (ResultSet rs = statement.executeQuery(CONF.getValue("get.findAll"))) {
                while (rs.next()) {
                    list.add(createUser(rs));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    @Override
    public User findById(String id) {
        User user = null;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement statement
                     = conn.prepareStatement(CONF.getValue("get.findById"))) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = createUser(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Create user.
     *
     * @param resultSet resultSet from statement.
     * @return item.
     * @throws SQLException If an error occurs.
     */
    private User createUser(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(1);
        String name = resultSet.getString(2);
        String login = resultSet.getString(3);
        String email = resultSet.getString(4);
        Timestamp create = resultSet.getTimestamp(5);
        return new User(id, name, login, email, create.getTime());
    }
}