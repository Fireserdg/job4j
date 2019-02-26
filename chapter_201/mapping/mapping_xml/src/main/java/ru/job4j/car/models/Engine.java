package ru.job4j.car.models;

import java.util.List;
import java.util.Objects;

/**
 * Engine detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
public class Engine {

    /**
     * Engine id
     *
     */
    private Long id;

    /**
     * Engine name
     *
     */
    private String name;

    /**
     * List of cars
     *
     */
    private List<Car> cars;

    /**
     * Default constructor
     *
     */
    public Engine() {

    }

    /**
     * Constructor with car body id
     *
     * @param id id
     */
    public Engine(Long id) {
        this.id = id;
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public Engine(String name) {
        this.name = name;
    }

    /**
     * Get Engine id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set Engine id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Engine name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Engine name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get list of cars
     *
     * @return list of cars
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Set list cars
     *
     * @param cars list of cars
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * String representation of an object.
     *
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return String.format("Engine{id=%s, name=%s}", this.id, this.name);
    }

    /**
     * Equals between objects.
     * @param o other object.
     * @return true if the same object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return Objects.equals(id, engine.id)
                && Objects.equals(name, engine.name);
    }

    /**
     * Get hashcode for objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
