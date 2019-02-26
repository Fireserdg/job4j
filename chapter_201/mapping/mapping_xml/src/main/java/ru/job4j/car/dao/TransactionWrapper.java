package ru.job4j.car.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * AbstractEntity DAO
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class TransactionWrapper {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TransactionWrapper.class);

    /**
     * Get session factory.
     */
    private final SessionFactory sessionFactory;

    /**
     * Private constructor.
     *
     */
    public TransactionWrapper(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    /**
     * Get result after success transaction.
     *
     * @param command command
     * @return transaction result.
     */
    protected <V> V getTransactionResult(final Function<Session, V> command) {
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                V apply = command.apply(session);
                session.getTransaction().commit();
                return apply;
            } catch (Exception ex) {
                LOG.error("This transaction error", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }

    /**
     * Do transaction
     *
     * @param command command for action
     */
    protected void doTransaction(final Consumer<Session> command) {
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                command.accept(session);
                session.getTransaction().commit();
            } catch (Exception ex) {
                LOG.error("This transaction", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }
}
