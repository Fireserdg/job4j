package ru.job4j.tracker;

import org.slf4j.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Tracker Database.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 30.09.2018.
 */
public class TrackerDataBase implements Tracker, AutoCloseable {

    /**
     * Connect to the database.
     *
     */
    private Connection conn = null;

    /**
     * Properties for use configuration.
     *
     */
    private final Properties prop;

    /**
     * Logger for get information about error.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(TrackerDataBase.class);

    /**
     * Constructor Tracker Database.
     *
     * @param config configuration file.
     */
    public TrackerDataBase(final String config) {
        this.prop = new Properties();
        this.loadConfig(config);
        this.start();
    }

    /**
     * Load config in properties.
     * @param config configuration file.
     */
    private void loadConfig(final String config) {
        ClassLoader loader = TrackerDataBase.class.getClassLoader();
        try (final InputStream inputStream = loader.getResourceAsStream(config)) {
            this.prop.load(inputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get connection to the Database.
     *
     */
    private void start() {
        try (final Connection connection = DriverManager.getConnection(prop.getProperty("db.url"),
                prop.getProperty("db.name"), prop.getProperty("db.password"));
             final Statement st = connection.createStatement();
             final ResultSet rs = st.executeQuery(prop.getProperty("db.check"))) {
            if (rs.next() && rs.getString(1).equals("f")) {
                st.execute(prop.getProperty("db.createDB"));
            }
            this.conn = DriverManager.getConnection(
                    prop.getProperty("db.urlDB"), prop.getProperty(
                            "db.name"), prop.getProperty("db.password"));
            try (final Statement statement = this.conn.createStatement()) {
                statement.execute(prop.getProperty("db.crTable"));
                statement.execute(prop.getProperty("db.crTableComm"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Add items in storage.
     *
     * @param item new item.
     * @return new Item add storage.
     */
    @Override
    public Item add(Item item)  {
        try (final PreparedStatement ps = conn.prepareStatement(
                prop.getProperty("db.add"), Statement.RETURN_GENERATED_KEYS);
             final ResultSet rs = ps.getGeneratedKeys()) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setTimestamp(3, new Timestamp(item.getCreate()));
            ps.executeUpdate();
            if (rs.next()) {
                item.setId(rs.getString(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Replace item.
     * @param id functional for find item by id.
     * @param item the item.
     */
    @Override
    public boolean replace(Predicate<String> id, Item item) {
        boolean result = false;
        try (final Statement st = this.conn.createStatement();
             final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                if (id.test(rs.getString("id"))) {
                    try (final PreparedStatement pst = this.conn.prepareStatement(prop.getProperty("db.replace"))) {
                        pst.setString(1, item.getName());
                        pst.setString(2, item.getDescription());
                        pst.setInt(3, rs.getInt("id"));
                        pst.executeUpdate();
                        result = true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Delete item by Id.
     * @param id functional for find item by id.
     */
    @Override
    public boolean delete(Predicate<String> id) {
        boolean result = false;
        try (final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                if (id.test(rs.getString("id"))) {
                    try (final PreparedStatement prep = conn.prepareStatement(prop.getProperty("db.delComment"))) {
                        prep.setInt(1, rs.getInt("id"));
                        prep.executeUpdate();
                    }
                    try (final PreparedStatement ps = conn.prepareStatement(prop.getProperty("db.delete"))) {
                        ps.setInt(1, rs.getInt("id"));
                        ps.executeUpdate();
                        result = true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Get list all items.
     * @return list all items.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (final Statement st = this.conn.createStatement();
             final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                items.add(this.createItem(rs));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * Find item by name.
     * @param key functional for find item by id.
     * @return list of items.
     */
    @Override
    public List<Item> findByName(Predicate<String> key) {
        List<Item> items = new ArrayList<>();
        try (final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                if (key.test(rs.getString("name"))) {
                    Item item = createItem(rs);
                    items.add(item);
                }
            }
            return items;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Item does not exist");
    }

    /**
     * Get item by id.
     * @param id functional for find item by id.
     * @return item.
     */
    @Override
    public Item findById(Predicate<String> id) {
        try (final Statement st = this.conn.createStatement();
        final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                if (id.test(rs.getString("id"))) {
                    return createItem(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Item does not exist");
    }

    /**
     * Close connection to the database.
     *
     */
    @Override
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Add comment for item.
     * @param id functional for find item by id.
     * @return result add comment.
     */
    @Override
    public boolean addComment(Predicate<String> id, String comment) {
        boolean result = false;
        try (final Statement st = this.conn.createStatement();
        final ResultSet rs = st.executeQuery(prop.getProperty("db.findAll"))) {
            while (rs.next()) {
                if (id.test(rs.getString("id"))) {
                    try (final PreparedStatement ps = this.conn.prepareStatement(prop.getProperty("db.addComm"))) {
                        ps.setString(1, comment);
                        ps.setInt(2, Integer.parseInt(rs.getString("id")));
                        ps.executeUpdate();
                        result = true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Create items.
     *
     * @param resultSet resultSet.
     * @return item.
     * @throws SQLException If an error occurs.
     */
    private Item createItem(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String desc = resultSet.getString("description");
        Timestamp create = resultSet.getTimestamp("create_time");
        Item item = new Item(name, desc, create.getTime());
        item.setId(id);
        try (final PreparedStatement ps = this.conn.prepareStatement(prop.getProperty("db.getComment"))) {
            ps.setInt(1, Integer.parseInt(id));
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    item.setComments(rs.getString("comment"));
                }
            }
        }
        return item;
    }
}