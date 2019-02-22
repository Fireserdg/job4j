package ru.job4j.car.dao;

import org.hibernate.query.Query;
import ru.job4j.car.models.CarBody;

import java.util.List;

/**
 * Car body DAO implementation
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public class CarBodyDAOImpl extends AbstractEntityDAO<CarBody> {

    private static final EntityDAO<CarBody> INSTANCE = new CarBodyDAOImpl();

    private CarBodyDAOImpl() {

    }

    public static EntityDAO<CarBody> getInstance() {
        return INSTANCE;
    }

    @Override
    public Long add(CarBody type) {
        return getTransactionResult(session -> (Long) session.save(type));
    }

    @Override
    public void update(CarBody type) {
        doTransaction(session -> session.update(type));
    }

    @Override
    public void delete(Long id) {
        doTransaction(session -> session.delete(new CarBody(id)));
    }

    public CarBody findByModel(String model) {
        return getTransactionResult(session -> {
                    Query<CarBody> query = session.createQuery(
                            "from CarBody where name=:paramName", CarBody.class);
                    query.setParameter("paramName", model);
                    return query.getSingleResult();
                }
        );
    }

    @Override
    public CarBody findById(Long id) {
        return getTransactionResult(session -> session.get(CarBody.class, id));
    }

    @Override
    public List<CarBody> findAll() {
        return getTransactionResult(session -> session.createQuery(
                "from CarBody", CarBody.class).list());
    }
}
