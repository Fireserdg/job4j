package ru.job4j.car.dao;

import org.hibernate.Hibernate;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.Engine;

import java.util.List;

/**
 * Engine DAO implementation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class EngineDAOImpl implements DetailEntityDAO<Engine> {

    /**
     * Transaction wrapper
     *
     */
    private final TransactionWrapper tx;

    /**
     * Constructor
     *
     * @param wrapper wrapper
     */
    public EngineDAOImpl(TransactionWrapper wrapper) {
        this.tx = wrapper;
    }

    /**
     * Add Engine
     *
     * @param type Engine
     * @return identifier
     */
    @Override
    public Long add(Engine type) {
        return  this.tx.getTransactionResult(session -> (Long) session.save(type));
    }

    /**
     * Update Engine
     *
     * @param type engine
     */
    @Override
    public void update(Engine type) {
        this.tx.doTransaction(session -> session.update(type));
    }

    /**
     * Delete Engine
     *
     * @param type engine
     */
    @Override
    public void delete(Engine type) {
        this.tx.doTransaction(session -> session.delete(type));
    }

    /**
     * Find Engine by id
     *
     * @param id Engine id
     * @return Engine id
     */
    @Override
    public Engine findById(Long id) {
        return this.tx.getTransactionResult(session -> session.get(Engine.class, id)
        );
    }

    /**
     * Find all Engine
     *
     * @return list of engines
     */
    @Override
    public List<Engine> findAll() {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from Engine", Engine.class).list()
        );
    }

    /**
     * Find Cars by name if engine
     *
     * @param name name of parameter
     * @return list of cars
     */
    @Override
    public List<Car> findAllElementsByNameDetail(String name) {
        return this.tx.getTransactionResult(session -> {
            Engine engine = session.createQuery(
                    "from Engine where name=:param", Engine.class)
                    .setParameter("param", name)
                    .getSingleResult();
            Hibernate.initialize(engine.getCars());
            return engine.getCars();
        });
    }


}
