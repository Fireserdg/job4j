package ru.job4j.car.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Transmission
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "TRANSMISSION")

@AttributeOverride(
        name = "name",
        column = @Column(name = "trans_name", nullable = false)
)
public class Transmission extends AbstractBaseEntity {

    @OneToMany(mappedBy = "transmission")
    private List<Car> cars;

    public Transmission() {

    }

    public Transmission(Long id) {
        super(id);
    }

    public Transmission(String name) {
        super(name);
    }


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Transmission{"
                + "id=" + getId()
                + ", name='" + getName() + '\''
                + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return Objects.equals(cars, that.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
