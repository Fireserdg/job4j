package ru.job4j.car.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Engine
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "ENGINE")
@AttributeOverride(
        name = "name",
        column = @Column(name = "engine_name", nullable = false)
)
public class Engine extends AbstractBaseEntity {

    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new ArrayList<>();

    public Engine() {

    }

    public Engine(Long id) {
        super(id);
    }
    public Engine(String name) {
        super(name);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

//    @Override
//    public String toString() {
//        return "Engine{" +
//                "id=" + getId() +
//                ", name='" + getName() + '\'' +
//                '}';
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return Objects.equals(cars, engine.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
