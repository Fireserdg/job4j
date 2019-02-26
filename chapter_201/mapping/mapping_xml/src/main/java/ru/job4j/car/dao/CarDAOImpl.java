package ru.job4j.car.dao;

import ru.job4j.car.models.Car;

import java.util.List;

/**
 * Car DAO implementation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public class CarDAOImpl implements CarEntityDAO {

    /**
     * Transaction wrapper
     *
     */
    private final TransactionWrapper tx;

    /**
     * Constructor
     *
     * @param wrapper action
     */
    public CarDAOImpl(TransactionWrapper wrapper) {
        this.tx = wrapper;
    }

    /**
     * Add car
     *
     * @param car car
     * @return car
     */
    @Override
    public Long add(Car car) {
        return this.tx.getTransactionResult(session -> (Long) session.save(car));
    }

    /**
     * Update car
     *
     * @param car car for update
     */
    @Override
    public void update(Car car) {
        this.tx.doTransaction(session -> session.update(car));
    }

    /**
     * Delete car
     *
     * @param car car
     */
    @Override
    public void delete(Car car) {
        this.tx.doTransaction(session -> session.delete(car));
    }

    /**
     * Find car by id
     *
     * @param id car id
     * @return car
     */
    @Override
    public Car findById(Long id) {
        return this.tx.getTransactionResult(session -> session.get(Car.class, id));
    }

    /**
     * Find all car
     *
     * @return list of car
     */
    @Override
    public List<Car> findAll() {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from Car", Car.class).list());
    }

    /**
     * Find car by name
     *
     * @param model car name
     * @return car
     */
    @Override
    public Car findByName(String model) {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from Car where model=:param", Car.class)
                .setParameter("param", model)
                .getSingleResult());
    }
}