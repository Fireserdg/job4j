package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import ru.job4j.todo.models.Item;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Data base store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-09
 */
public enum DbStore implements Store {

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
    private final SessionFactory factory;

    /**
     * Constructor Database store.
     *
     */
    DbStore() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public int addItem(Item item) {
        LOG.info("Item before={}", item);
        int result = -1;
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        LOG.info("Start transaction");
        try (session) {
            session.save(item);
            trans.commit();
            result = item.getId();
            LOG.info("Add item {}", item);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            trans.rollback();
        }
        LOG.info("Item after ={}", item);
        return result;
    }

    // Возвращаемый тип Optional<Item>
    @Override
    public Optional<Item> findItemById(int id) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Optional<Item> item = Optional.empty();
        try (session) {
            Item query = session.get(Item.class, id);
//            Query<Item> query = session.createQuery(
//                    "from Item where id=:id", Item.class);
//            query.setParameter("id", id);
            trans.commit();
            item = Optional.of(query);
            LOG.info("This single result {}", item);
        } catch (Exception ex) {
            trans.rollback();
            LOG.error(ex.getMessage(), ex);
        }
        return item;
    }

    @Override
    public void deleteItem(int id) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        try (session) {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            trans.commit();
            LOG.info("Item with id={} was deleted", id);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            trans.rollback();
        }
    }

    @Override
    public void updateItem(Item item) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        try (session) {
            session.update(item);
            trans.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            trans.rollback();
        }
    }

    @Override
    public List<Item> getAllItems() {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        List<Item> list = Collections.emptyList();
        try (session) {
            list = session.createQuery("from Item", Item.class).list();
            trans.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            trans.rollback();
        }
        return list;
    }

    @Override
    public void close() {
        if (factory != null) {
            factory.close();
            LOG.info("SessionFactory close");
        }
    }
}
