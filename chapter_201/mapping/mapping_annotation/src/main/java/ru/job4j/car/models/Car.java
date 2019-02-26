package ru.job4j.car.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

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
        column = @Column(name = "model", nullable = false, unique = true)
)
public class Car extends AbstractBaseEntity {

    /**
     * Car body
     *
     */
    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    @Fetch(value = FetchMode.JOIN)
    private CarBody body;

    /**
     * Engine
     *
     */
    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    @Fetch(value = FetchMode.JOIN)
    private Engine engine;

    /**
     * Transmission
     *
     */
    @ManyToOne
    @JoinColumn(name = "trans_id", nullable = false)
    @Fetch(value = FetchMode.JOIN)
    private Transmission transmission;

    /**
     * Default constructor
     *
     */
    public Car() {

    }

    /**
     * Constructor with name
     *
     * @param name name car
     */
    public Car(String name) {
        super(name);
    }

    /**
     * Constructor with four parameter
     *
     * @param model car model
     * @param body car body
     * @param engine car engine
     * @param transmission car transmission
     */
    public Car(String model, CarBody body, Engine engine, Transmission transmission) {
        super(model);
        this.transmission = transmission;
        this.body = body;
        this.engine = engine;
    }

    /**
     * Get car body
     *
     * @return body
     */
    public CarBody getBody() {
        return body;
    }

    /**
     * Set body
     *
     * @param body car body
     */
    public void setBody(CarBody body) {
        this.body = body;
    }

    /**
     * Get engine
     *
     * @return car engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Set engine
     *
     * @param engine car engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Get transmission
     *
     * @return car transmission
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Set transmission
     *
     * @param transmission car transmission
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
}