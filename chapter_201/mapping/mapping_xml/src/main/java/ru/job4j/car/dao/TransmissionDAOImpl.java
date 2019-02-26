package ru.job4j.car.dao;

import org.hibernate.Hibernate;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.Transmission;

import java.util.List;

/**
 * Transmission DAO implementation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class TransmissionDAOImpl implements DetailEntityDAO<Transmission> {

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
    public TransmissionDAOImpl(TransactionWrapper wrapper) {
        this.tx = wrapper;
    }

    /**
     * Add Transmission
     *
     * @param type Transmission
     * @return identifier
     */
    @Override
    public Long add(Transmission type) {
        return this.tx.getTransactionResult(session -> (Long) session.save(type)
        );
    }

    /**
     * Update Transmission
     *
     * @param type Transmission
     */
    @Override
    public void update(Transmission type) {
        this.tx.doTransaction(session -> session.update(type));
    }

    /**
     * Delete Transmission
     *
     * @param type Transmission
     */
    @Override
    public void delete(Transmission type) {
        this.tx.doTransaction(session -> session.delete(type));
    }

    /**
     * Find Transmission by id
     *
     * @param id Transmission id
     * @return Transmission id
     */
    @Override
    public Transmission findById(Long id) {
        return this.tx.getTransactionResult(session -> session.get(Transmission.class, id));
    }

    /**
     * Find all Transmission
     *
     * @return list of engines
     */
    @Override
    public List<Transmission> findAll() {
        return this.tx.getTransactionResult(session -> session.createQuery(
                "from Transmission", Transmission.class).list()
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
            Transmission transmission = session.createQuery(
                    "from Transmission where name=:param", Transmission.class)
                    .setParameter("param", name)
                    .getSingleResult();
            Hibernate.initialize(transmission.getCars());
            return transmission.getCars();
        });
    }
}
