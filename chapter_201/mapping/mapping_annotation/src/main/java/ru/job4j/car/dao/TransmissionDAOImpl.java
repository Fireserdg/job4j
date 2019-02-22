package ru.job4j.car.dao;

import ru.job4j.car.models.Transmission;

import java.util.List;

/**
 * Transmission DAO implementation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class TransmissionDAOImpl extends AbstractEntityDAO<Transmission> {

    private static final EntityDAO<Transmission> INSTANCE = new TransmissionDAOImpl();

    private TransmissionDAOImpl() {

    }

    public static EntityDAO<Transmission> getInstance() {
        return INSTANCE;
    }

    @Override
    public Long add(Transmission type) {
        return getTransactionResult(session -> (Long) session.save(type)
        );
    }

    @Override
    public void update(Transmission type) {
        doTransaction(session -> session.update(type));
    }

    @Override
    public void delete(Long id) {
        doTransaction(session -> session.delete(new Transmission(id)));
    }

    @Override
    public Transmission findById(Long id) {
        return getTransactionResult(session -> session.get(Transmission.class, id));
    }

    @Override
    public List<Transmission> findAll() {
        return getTransactionResult(session -> session.createQuery(
                "from Transmission", Transmission.class).list()
        );
    }
}
