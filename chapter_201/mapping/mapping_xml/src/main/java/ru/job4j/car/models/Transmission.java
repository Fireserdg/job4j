package ru.job4j.car.models;

import java.util.List;
import java.util.Objects;

/**
 * Transmission detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
public class Transmission {

    /**
     * Transmission id
     *
     */
    private Long id;

    /**
     * Transmission name
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
    public Transmission() {

    }

    /**
     * Constructor with car body id
     *
     * @param id id
     */
    public Transmission(Long id) {
        this.id = id;
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public Transmission(String name) {
        this.name = name;
    }

    /**
     * Get Transmission id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set Transmission id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Transmission name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Transmission name
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
        return String.format("Transmission{id=%s, name=%s}", this.id, this.name);
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
        Transmission that = (Transmission) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name);
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
