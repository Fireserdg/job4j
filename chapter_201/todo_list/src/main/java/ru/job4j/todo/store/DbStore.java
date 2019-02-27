package ru.job4j.todo.store;

import org.hibernate.*;
import org.slf4j.Logger;
import ru.job4j.todo.models.Item;
import ru.job4j.todo.services.SessionUtil;

import java.util.*;
import java.util.function.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Data base store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-09
 */
public enum DbStore implements Store<Item> {

    /**
     * Instance for DbStore.
     *
     */
    INSTANCE;

    /**
     * Contains logger.
     */
    private static final Logger LOG = getLogger(DbStore.class);


    /**
     * Session factory.
     *
     */
    private final SessionFactory factory = SessionUtil.getInstance().getFactory();

    /**
     * Add Item to database.
     *
     * @param item item.
     * @return item id;
     */
    @Override
    public Item addItem(Item item) {
        return getTransactionResult(session -> {
            session.save(item);
            LOG.info("{} successfully added", item);
            return item;
        });
    }

    /**
     * Find item by id
     *
     * @param id item id
     * @return item
     */
    @Override
    public Item findItemById(long id) {
        return getTransactionResult(session -> session.get(Item.class, id));
    }

    /**
     * Delete item
     *
     * @param id item id
     */
    @Override
    public void deleteItem(long id) {
        doTransaction(session -> {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            LOG.info("User with id={} successfully deleted", item.getId());
        });
    }

    /**
     * Update item
     *
     * @param item item
     */
    @Override
    public void updateItem(Item item) {
        doTransaction(session -> {
            session.update(item);
            LOG.info("User with id={} was updated", item.getId());
        });
    }

    /**
     * Get all items from database.
     *
     * @return list of items
     */
    @Override
    public List<Item> getAllItems() {
        return getTransactionResult(
                session -> session.createQuery(
                        "from Item order by id", Item.class).list());
    }

    /**
     * Get result after success transaction.
     *
     * @param command command
     * @param <T> type of parameter.
     * @return transaction result.
     */
    private <T> T getTransactionResult(final Function<Session, T> command) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                T apply = command.apply(session);
                session.getTransaction().commit();
                return apply;
            } catch (Exception ex) {
                LOG.error("Error for action DB layer", ex);
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
    private void doTransaction(final Consumer<Session> command) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                command.accept(session);
                session.getTransaction().commit();
            } catch (Exception ex) {
                LOG.error("Error for action DB layer", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }
}