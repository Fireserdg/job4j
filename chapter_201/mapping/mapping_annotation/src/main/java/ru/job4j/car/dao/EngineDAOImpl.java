package ru.job4j.car.dao;

import ru.job4j.car.models.Engine;

import java.util.List;

/**
 * Engine DAO implementation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class EngineDAOImpl extends AbstractEntityDAO<Engine> {

    private static final EntityDAO<Engine> INSTANCE = new EngineDAOImpl();

    private EngineDAOImpl() {

    }

    public static EntityDAO<Engine> getInstance() {
        return INSTANCE;
    }

    @Override
    public Long add(Engine type) {
        return getTransactionResult(session -> (Long) session.save(type));
    }

    @Override
    public void update(Engine type) {
        doTransaction(session -> session.update(type));
    }

    @Override
    public void delete(Long id) {
        doTransaction(session -> session.delete(new Engine(id)));
    }

    @Override
    public Engine findById(Long id) {
        return getTransactionResult(session -> session.get(Engine.class, id)
        );
    }

    @Override
    public List<Engine> findAll() {
        return getTransactionResult(session -> session.createQuery(
                "from Engine", Engine.class).list()
        );
    }
}
