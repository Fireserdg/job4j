package ru.job4j.car.dao;

import ru.job4j.car.models.Car;

import java.util.List;

/**
 * Interface DetailEntityDAO
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-23
 */
public interface DetailEntityDAO<T> {

    /**
     * Add detail
     *
     * @param type type of detail
     * @return identifier
     */
    Long add(T type);

    /**
     * Update detail
     *
     * @param type type of detail
     */
    void update(T type);

    /**
     * Delete detail
     *
     * @param type type of detail
     */
    void delete(T type);

    /**
     * Find detail by id
     *
     * @param id detail id
     * @return detail
     */
    T findById(Long id);

    /**
     * Find all type of details
     *
     * @return list of details
     */
    List<T> findAll();

    /**
     * Find element
     *
     * @param param name of parameter
     * @return list of cars
     */
    List<Car> findAllElementsByNameDetail(String param);

}
