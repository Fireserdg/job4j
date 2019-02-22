package ru.job4j.car.dao;

import java.util.List;

/**
 * Detail DAO.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public interface EntityDAO<T> {

    Long add(T type);

    void update(T type);

    void delete(Long id);

    T findById(Long id);

    List<T> findAll();

}
