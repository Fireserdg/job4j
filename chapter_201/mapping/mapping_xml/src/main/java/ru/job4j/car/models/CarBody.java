package ru.job4j.car.models;

import java.util.List;
import java.util.Objects;


/**
 * Car body detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
public class CarBody {

    /**
     * CarBody id
     *
     */
    private Long id;

    /**
     * CarBody name
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
    public CarBody() {

    }

    /**
     * Constructor with car body id
     *
     * @param id id
     */
    public CarBody(Long id) {
        this.id = id;
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public CarBody(String name) {
        this.name = name;
    }

    /**
     * Get CarBody id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set CarBody id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get CarBody name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set CarBody name
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
        return  String.format("CarBody{id=%s, name=%s}", this.id, this.name);
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
        CarBody carBody = (CarBody) o;
        return Objects.equals(this.id, carBody.id)
                && Objects.equals(this.name, carBody.name);
    }


    /**
     * Get hashcode for objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
