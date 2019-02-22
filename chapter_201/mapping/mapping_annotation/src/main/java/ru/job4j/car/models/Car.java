package ru.job4j.car.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Car
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
@Entity
@Table(name = "CARS")
@AttributeOverride(
        name = "name",
        column = @Column(name = "model", nullable = false)
)
public class Car extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "body_id")
    private CarBody body;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "trans_id")
    private Transmission transmission;

    public Car() {

    }

    public Car(Long id) {
        super(id);
    }

    public Car(String model, CarBody body, Engine engine, Transmission transmission) {
        super(model);
        this.transmission = transmission;
        this.body = body;
        this.engine = engine;
    }


    public CarBody getBody() {
        return body;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + getId()
                + ", model='" + getName() + '\''
                + ", body=" + body
                + ", engine=" + engine
                + ", transmission=" + transmission
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
        Car car = (Car) o;
        return Objects.equals(body, car.body)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, engine, transmission);
    }
}
