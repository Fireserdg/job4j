package ru.job4j.todo.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Session util.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-13
 */
public class SessionUtil {

    /**
     * Instance for SessionUtil
     */
    private static final SessionUtil INSTANCE = new SessionUtil();

    /**
     * Session factory.
     */
    private final SessionFactory factory;

    /**
     * Default private constructor
     */
    private SessionUtil() {
            this.factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
    }

    /**
     * Get instance Session Util
     *
     */

    public static SessionUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Get session factory
     *
     * @return Session factory
     */
    public SessionFactory getFactory() {
        return factory;
    }
}
