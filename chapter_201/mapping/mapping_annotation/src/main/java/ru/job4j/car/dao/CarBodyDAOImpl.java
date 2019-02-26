package ru.job4j.car.dao;

import ru.job4j.car.models.Car;
import ru.job4j.car.models.CarBody;

import java.util.List;

/**
 * Car body DAO implementation
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public class CarBodyDAOImpl implements DetailEntityDAO<CarBody> {

    /**
     * Transaction wrapper
     *
     */
    private final TransactionWrapper tx;

    /**
     * Constructor
     *
     * @param wrapper Transaction function
     */
    public CarBodyDAOImpl(TransactionWrapper wrapper) {
        this.tx = wrapper;
    }

    /**
     * Add CarBody
     *
     * @param type CarBody
     * @return identifier
     */
    @Override
    public Long add(CarBody type) {
        return this.tx.getTransactionResult(session -> (Long) session.save(type));
    }

    /**
     * Update CarBody
     *
     * @param type CarBody
     */
    @Override
    public void update(CarBody type) {
        this.tx.doTransaction(session -> session.update(type));
    }

    /**
     * Delete CarBody
     *
     * @param type CarBody
     */
    @Override
    public void delete(CarBody type) {
        this.tx.doTransaction(session -> session.delete(type));
    }

    /**
     * Find CarBody by id
     *
     * @param id CarBody id
     * @return CarBody
     */
    @Override
    public CarBody findById(Long id) {
        return this.tx.getTransactionResult(session -> session.get(CarBody.class, id));
    }

    /**
     * Find all type of CarBody
     *
     * @return list of CarBody
     */
    @Override
    public List<CarBody> findAll() {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from CarBody", CarBody.class).list());
    }

    /**
     * Find element by CarBody name
     *
     * @param name name of parameter
     * @return list of cars
     */
    @Override
    public List<Car> findAllElementsByNameDetail(String name) {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from CarBody where name=:param", CarBody.class)
                .setParameter("param", name)
                .setHint("javax.persistence.fetchgraph",
                        session.getEntityGraph("bodyGraph"))
                .getSingleResult().getCars());
    }
}