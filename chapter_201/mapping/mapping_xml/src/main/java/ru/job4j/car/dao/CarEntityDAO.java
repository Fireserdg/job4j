package ru.job4j.car.dao;

import ru.job4j.car.models.Car;

import java.util.List;

/**
 * Detail DAO.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public interface CarEntityDAO {

    /**
     * Add car
     *
     * @param car car
     * @return car
     */
    Long add(Car car);

    /**
     * Update car
     *
     * @param car car for update
     */
    void update(Car car);

    /**
     * Delete car
     *
     * @param car car
     */
    void delete(Car car);

    /**
     * Find car by id
     *
     * @param id car id
     * @return car
     */
    Car findById(Long id);

    /**
     * Find all car
     *
     * @return list of car
     */
    List<Car> findAll();

    /**
     * Find car by name
     *
     * @param name car name
     * @return car
     */
    Car findByName(String name);

}
