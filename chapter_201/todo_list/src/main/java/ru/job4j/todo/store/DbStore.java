package ru.job4j.todo.store;

import org.hibernate.*;
import org.slf4j.Logger;
import ru.job4j.todo.models.Item;
import ru.job4j.todo.services.SessionUtil;

import java.util.*;

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
    private final SessionFactory factory = SessionUtil.getFactory();

    /**
     * Add Item to database.
     *
     * @param item item.
     * @return item id;
     */
    @Override
    public Item addItem(Item item) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                session.save(item);
                session.getTransaction().commit();
                LOG.info("{} successfully added", item);
                return item;
            } catch (Exception ex) {
                LOG.error("Exception when add Item", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }

    /**
     * Find item by id
     *
     * @param id item id
     * @return item
     */
    @Override
    public Item findItemById(long id) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Item item = session.get(Item.class, id);
                session.getTransaction().commit();
                return item;
            } catch (Exception ex) {
                LOG.error("Exception findById", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }

    /**
     * Delete item
     *
     * @param id item id
     */
    @Override
    public void deleteItem(long id) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Item item = new Item();
                item.setId(id);
                session.delete(item);
                session.getTransaction().commit();
                LOG.info("User with id={} successfully deleted", item.getId());
            } catch (Exception ex) {
                LOG.error("Exception when deleteItem", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }

    /**
     * Update item
     *
     * @param item item
     */
    @Override
    public void updateItem(Item item) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                session.update(item);
                session.getTransaction().commit();
                LOG.info("User with id={} was updated", item.getId());
            } catch (Exception ex) {
                LOG.error("Exception when updateItem", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }

    /**
     * Get all items from database.
     *
     * @return list of items
     */
    @Override
    public List<Item> getAllItems() {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                List<Item> list = session.createQuery("from Item", Item.class).list();
                session.getTransaction().commit();
                return list;
            } catch (Exception ex) {
                LOG.error("Exception when getAllItem", ex);
                session.getTransaction().rollback();
                throw ex;
            }
        }
    }
}
