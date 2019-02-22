package ru.job4j.car.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session factory util.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public class SessionFactoryUtil {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SessionFactoryUtil.class);

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            LOG.error("Error init session factory", ex);
        }
    }


    private SessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

}
