package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import ru.job4j.todo.models.Item;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * DbStore
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
    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();


    @Override
    public boolean addItem(Item item) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        LOG.info("Start transaction");
        try (session) {
            session.save(item);
            trans.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            trans.rollback();
        }
        return false;
    }

    @Override
    public Item findItemById(int id) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        try (session) {
            Query<Item> query = session.createQuery(
                    "from Item where id=:id", Item.class);
            query.setParameter("id", id);
            trans.commit();
            List<Item> list = query.list();
            Item singleResult = query.getSingleResult();
            LOG.info("This list {}", list);
            LOG.info("This single result {}", singleResult);
            return singleResult;
        } catch (Exception ex) {
            trans.rollback();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
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
        try (session) {
            Query<Item> fromItem = session.createQuery("from Item", Item.class);
            trans.commit();
            return fromItem.list();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            System.out.println(trans.isActive());
            trans.rollback();
            throw ex;
        }
    }
}
