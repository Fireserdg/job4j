package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  Config from get properties values.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.11.2018.
 */
public class Config {

    private final static Config INSTANCE = new Config("app.properties");

    /**
     * Contains properties.
     *
     */
    private final Properties prop = new Properties();

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    /**
     * Constructor
     *
     * @param properties name of properties file
     */
    private Config(String properties) {
        this.loadConfig(properties);
    }

    /**
     * Instance for config
     *
     * @return class instance
     */
    public static Config getInstance() {
        return INSTANCE;
    }

    /**
     * Load config from property file.
     *
     * @param config name of file.
     */
    private void loadConfig(final String config) {
        ClassLoader cs = Config.class.getClassLoader();
        try (InputStream is = cs.getResourceAsStream(config)) {
            this.prop.load(is);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get value by key
     *
     * @param key key.
     * @return value.
     */
    public String getValue(String key) {
        return this.prop.getProperty(key);
    }
}