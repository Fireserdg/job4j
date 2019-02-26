package ru.job4j.car.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Car body detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "CAR_BODY")
@AttributeOverride(
        name = "name",
        column = @Column(name = "body_name", nullable = false, unique = true)
)
@NamedEntityGraph(name = "bodyGraph",
attributeNodes = @NamedAttributeNode(value = "cars"))
public class CarBody extends AbstractBaseEntity {

    /**
     * List of cars
     *
     */
    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

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
        super(id);
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public CarBody(String name) {
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
