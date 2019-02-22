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
public class CarDAOImpl extends AbstractEntityDAO<Car> {


    private static final EntityDAO<Car> INSTANSE = new CarDAOImpl();

    private CarDAOImpl() {

    }

    public static EntityDAO<Car> getInstance() {
        return INSTANSE;
    }

    @Override
    public Long add(Car car) {
        return getTransactionResult(session -> (Long) session.save(car));
    }

    @Override
    public void update(Car car) {
        doTransaction(session -> session.update(car)
        );
    }

    @Override
    public void delete(Long id) {
        doTransaction(session -> session.delete(new Car(id)));
    }

    @Override
    public Car findById(Long id) {
        return getTransactionResult(session -> session.get(Car.class, id));
    }

    @Override
    public List<Car> findAll() {
        return getTransactionResult(session -> session.createQuery(
                "from Car", Car.class).list());
    }
}
