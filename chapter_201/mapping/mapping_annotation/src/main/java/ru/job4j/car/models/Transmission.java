package ru.job4j.car.models;

import javax.persistence.*;
import java.util.List;

/**
 * Transmission detail
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "TRANSMISSION")
@AttributeOverride(
        name = "name",
        column = @Column(name = "trans_name", nullable = false, unique = true)
)
@NamedEntityGraph(name = "transGraph",
        attributeNodes = @NamedAttributeNode(value = "cars"))
public class Transmission extends AbstractBaseEntity {

    /**
     * List of cars
     *
     */
    @OneToMany(mappedBy = "transmission", cascade = CascadeType.ALL)
    private List<Car> cars;

    /**
     * Default constructor
     *
     */
    public Transmission() {

    }

    /**
     * Constructor with transmission id
     *
     * @param id id
     */
    public Transmission(Long id) {
        super(id);
    }

    /**
     * Constructor with name
     *
     * @param name name
     */
    public Transmission(String name) {
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
