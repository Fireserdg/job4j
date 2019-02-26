package ru.job4j.car.models;


import java.util.Objects;

/**
 * Car
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-18
 */
public class Car {

    /**
     * Car id
     *
     */
    private Long id;

    /**
     * Car model
     *
     */
    private String model;

    /**
     * Car body
     *
     */
    private CarBody carBody;

    /**
     * Engine
     *
     */
    private Engine engine;


    /**
     * Transmission
     *
     */
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
     * @param model name car
     */
    public Car(String model) {
        this.model = model;
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
        this.model = model;
        this.carBody = body;
        this.engine = engine;
        this.transmission = transmission;
    }

    /**
     * Get car id
     *
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set car id
     *
     * @param id car id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get model car
     *
     * @return model car
     */
    public String getModel() {
        return model;
    }

    /**
     * Set model car
     *
     * @param model model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get car body
     *
     * @return body
     */
    public CarBody getCarBody() {
        return carBody;
    }

    /**
     * Set body
     *
     * @param carBody car body
     */
    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
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

    /**
     * String representation of an object.
     *
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return String.format("Car{id=%s, model=%s}", this.id, this.model);
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
        Car car = (Car) o;
        return Objects.equals(this.id, car.id)
                && Objects.equals(this.model, car.model);
    }

    /**
     * Get hashcode for objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }
}
