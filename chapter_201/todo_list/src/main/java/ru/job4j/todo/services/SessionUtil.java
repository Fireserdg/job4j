package ru.job4j.todo.services;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.*;

/**
 * Session util.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-13
 */
public final class SessionUtil {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SessionUtil.class);

    /**
     * Session factory.
     *
     */
    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            LOG.error("Build SessionFactory error", ex);
        }
    }

    /**
     * Get session factory
     *
     * @return Session factory
     */
    public static SessionFactory getFactory() {
        return factory;
    }
}
