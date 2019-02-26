package ru.job4j.car.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "ENGINE")
@AttributeOverride(
        name = "name",
        column = @Column(name = "engine_name", nullable = false, unique = true)
)
@NamedEntityGraph(name = "enGraph",
        attributeNodes = @NamedAttributeNode(value = "cars"))
public class Engine extends AbstractBaseEntity {

    /**
     * List of cars
     *
     */
    @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    /**
     * Default constructor
     *
     */
    public Engine() {

    }

    /**
     * Constructor with engine id
     *
     * @param id id
     */
    public Engine(Long id) {
        super(id);
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public Engine(String name) {
        super(name);
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
}
