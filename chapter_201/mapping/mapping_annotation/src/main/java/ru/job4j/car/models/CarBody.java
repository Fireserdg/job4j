package ru.job4j.car.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Car body
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "CAR_BODY")
@AttributeOverride(
        name = "name",
        column = @Column(name = "body_name", nullable = false)
)
public class CarBody extends AbstractBaseEntity {

    @OneToMany(mappedBy = "body", fetch = FetchType.EAGER)
    private List<Car> cars;

    public CarBody() {

    }

    public CarBody(Long id) {
        super(id);
    }

    public CarBody(String name) {
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
//        return "CarBody{" +
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
        CarBody carBody = (CarBody) o;
        return Objects.equals(cars, carBody.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
